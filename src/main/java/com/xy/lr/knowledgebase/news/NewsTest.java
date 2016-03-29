package com.xy.lr.knowledgebase.news;

import com.xy.lr.java.nlp.stopwords.StopWords;
import com.xy.lr.java.simhash.ChineseInfoWordSeg;
import com.xy.lr.java.simhash.Simhash;
import com.xy.lr.java.tools.file.FindAllFileOnCatalogue;
import com.xy.lr.java.tools.file.GetFileSize;
import com.xy.lr.java.tools.file.JFile;
import com.xy.lr.knowledgebase.mysql.MySQLUtil;
import org.dom4j.DocumentException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Pack200;

/**
 * Created by xylr on 16-3-4.
 */
public class NewsTest {
	
	public static void ff() {
		ArrayList<String> files = JFile.getAllLines(new File("IndexOfFile.txt"), "utf-8");
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		for (String file : files) {
			if(file.split("\t").length != 2)
				System.out.println("error");
			else{
				Integer id = Integer.valueOf(file.split("\t")[0]);
				String s = file.split("\t")[1];
				String ss = s.substring(0, s.indexOf("qq.com.") + 6);
				map.put(id, ss);
			}
		}
		
		FindAllFileOnCatalogue find = new FindAllFileOnCatalogue();
    	List<File> lists = find.getCatalogueList(new File("data/"));
    	
    	for (File f : lists) {
    		String s = JFile.getAllLines(f);
    		String ss = s.replace("\n", "");
    		
    		int i = f.getName().indexOf("_") + 1;
    		int j = f.getName().indexOf(".");
    		
    		Integer in = Integer.valueOf(f.getName().substring(i, j));
    		
    		String result = map.get(in);
    		
    		JFile.appendFile("cluster/" + result, in + "\t" + ss);
    		
//    		System.out.println(result);
    	}
    	
    	System.out.println(lists.size());
		
		System.out.println(files.size());
	}
	
    public static void main(String[] args) {
//        NewsParser parserTest = new NewsParser();
//
//        ArrayList<NewsProfile> count = parserTest.parseProfile(new File("data_parsed"));
////        new MySQLUtil().insertData(count);
//        saveProfile(count);
////        System.out.println(count.size());

//        fenciToSimhash();
        /*removeStopWord();*/
//    	f();
    	ff();
    	
    	/*FindAllFileOnCatalogue findAllFileOnCatalogue = 
    			new FindAllFileOnCatalogue();
    	
    	List<File> list = findAllFileOnCatalogue.getCatalogueList(new File("data/"));
    	
    	System.out.println(FindAllFileOnCatalogue.FormetFileSize(findAllFileOnCatalogue.getMinimumSize()));*/
    }
    
    public static void f() {
    	FindAllFileOnCatalogue find = new FindAllFileOnCatalogue();
    	List<File> lists = find.getCatalogueList(new File("data/"));
    	
    	int count = 0;
    	int n = 0;
    	
    	for(File file : lists) {
    		String s = JFile.getAllLines(file);
    		
    		String ss = s.replace("\n", "");
    		
    		/*System.out.println(file.getName() + "\t" + n + "\t" + ss.length() + "\t" + ss);*/
//    		System.out.println(s);
    		if(ss.split(" ").length <= 15){
    			System.out.println(file.getName());
    			
    			file.renameTo(new File("data_文件太小/" + file.getName()));
    			count++;
    			/*System.out.println(n);*/
    		}
    		n++;
    	}
    	
    	System.out.println(count);
//    	System.out.println(lists.size());
    }

    public static void saveProfile(ArrayList<NewsProfile> profile) {
        for(NewsProfile p : profile) {
            String text = p.getNewsText();
            String fileName = "data/" + p.getMetaInfo().getNewsHost() +
                    "." + p.getMetaInfo().getNewsTitle().replace("/","\\") + ".txt";
            //保存文件
            JFile.saveFiles(fileName, p.getNewsText(), "utf-8");
        }
    }

    public static void fenciToSimhash() {
        List<String> lists = JFile.getAllLines(new File("rightLinesWordSeg.txt"), "utf-8");
//        Simhash simhash = new Simhash(new ChineseInfoWordSeg());

        System.out.println(lists.size());
        int count = 0;

        for(String list : lists) {
            String name = list.split("\t")[0];
            String wordSeg = list.split("\t")[1];

//            String hash = simhash.simhash64(wordSeg);

            JFile.appendFile("IndexOfFile.txt", count + "\t" + name);

            JFile.appendFile("IndexOfFileWordSeg.txt", count + "\t" + wordSeg);

//            JFile.appendFile("IndexOfSimhash.txt", count + "\t" + hash);

//            wordSeg = new StopWords().removeStopWord(wordSeg, " ");

//            JFile.appendFile("data/WordSeg" + count + ".txt", wordSeg);

            count++;
        }
    }

    
    
    public static void removeStopWord() {
        List<String> lists = JFile.getAllLines(new File("IndexOfFileWordSeg.txt"), "utf-8");
        List<String> results = new ArrayList<String>();

        StopWords stopWords = new StopWords();

        int count = 0;

        for(String list : lists) {
            String[] strs = list.split("\t");
            String id = strs[0];
            String wordSeg = stopWords.removeStopWord(strs[1], " ");

//            System.out.println(id + "\t" + wordSeg);

            JFile.appendFile("data/WordSeg_" + count + ".txt", wordSeg);

            count++;

//            results.add(id + "\t" + wordSeg);
        }
    }
}
