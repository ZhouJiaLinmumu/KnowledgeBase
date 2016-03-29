package com.xy.lr.knowledgebase.news;

import com.xy.lr.java.nlp.wordsegment.XMLParser;
import com.xy.lr.java.nlp.wordsegment.entity.ChineseWordSegment;
import com.xy.lr.java.nlp.wordsegment.entity.XMLDocument;
import com.xy.lr.java.tools.file.FindAllFileOnCatalogue;
import com.xy.lr.java.tools.file.JFile;
import com.xy.lr.knowledgebase.mysql.MySQLUtil;
import org.dom4j.DocumentException;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xylr on 16-3-12.
 */
public class NewsChineseWordSeg {
    /**
     * 找出有问题的数据
     */
    public void errorFind() {
        ArrayList<String> lines = JFile.getAllLines(new File("wordSegment1.txt"), "utf-8");

        int count = 0;
        ArrayList<String> errorLines = new ArrayList<String>();

        for(String line : lines) {
            String[] sp = line.split("\t");
            if(sp.length != 2) {
//                System.out.println();
                count++;
                errorLines.add(line);
            }else{
                if(sp[1].equals("")) {
                    count++;
                    errorLines.add(line);
                }
            }
        }

        System.out.println(count);

        JFile.appendFile("errorLinesWordSeg.txt", errorLines);
    }

    public void rightFind(){
        ArrayList<String> lines = JFile.getAllLines(new File("wordSegment1.txt"), "utf-8");

        int count = 0;
        ArrayList<String> errorLines = new ArrayList<String>();
        ArrayList<String> rightLines = new ArrayList<String>();

        for(String line : lines) {
            String[] sp = line.split("\t");
            if(sp.length != 2) {
//                System.out.println();
                count++;
                errorLines.add(line);
            }else{
                if(sp[1].equals("")) {
                    count++;
                    errorLines.add(line);
                }else {
                    rightLines.add(line);
                }
            }
        }
        JFile.appendFile("rightLinesWordSeg.txt", rightLines);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> lines = JFile.getAllLines(new File("rightLinesWordSeg.txt"), "utf-8");

//        new MySQLUtil().insertData()
        for(String line : lines) {
            String[] sp = line.split("\t");

            String sql = "insert into news_parsed values('" + sp[0] + "','" +
                    sp[1] + "')";

            new MySQLUtil().insertData(sql);
        }
    }

    /**
     * 合并之前处理文件的两个部分
     */
    public void merge() {
        FindAllFileOnCatalogue find = new FindAllFileOnCatalogue();

        //输入文件
        List<File> chinese1 = find.getCatalogueList(new File("data_Chinese"));
        List<File> chinese2 = find.getCatalogueList(new File("data_Chinese2"));

//        System.out.println(chinese1.size());
//        System.out.println(chinese2.size());

        for(File chinese : chinese2) {
//            System.out.println(chinese.getName());

            XMLParser xmlParser = new XMLParser();
            XMLDocument xmlDocument = null;
            try {
                xmlDocument = xmlParser.xmlParser(chinese.getAbsolutePath());
            } catch (DocumentException e) {
                e.printStackTrace();
            }

//            System.out.println("----------------------------分割线----------------------------");
//		xmlParser.printAll(xmlDocument);

            ChineseWordSegment chineseWordSegment = xmlParser.wordSegment(xmlDocument);
//		chineseWordSegment.printAll();
            chineseWordSegment.appendChineseWordSegmentToFile(chinese.getName(), "wordSegment1.txt");
//            String result = chineseWordSegment.getWordList();
//            System.out.println(result);
        }
    }
}
