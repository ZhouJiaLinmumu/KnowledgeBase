package com.xy.lr.knowledgebase.news;

import com.xy.lr.java.tools.file.FindAllFileOnCatalogue;
import com.xy.lr.java.tools.file.JFile;
import com.xy.lr.knowledgebase.profile.ProfileExample;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xylr on 16-3-4.
 */
public class NewsParser {
    //创建SAXReader的对象saxReader
    private SAXReader saxReader = new SAXReader();

    /**
     * 处理一个 news 文件
     */
    public void parseProfile(String fileName, ArrayList<NewsProfile> profileList) throws DocumentException {
        //通过saxReader对象的
        Document document = saxReader.read(new File(fileName));
        //通过document对象获取根节点orgProfileStore
        Element ProfileRoot = document.getRootElement();

        ProfileRoot.element("fea");

        Iterator ProfileIt = ProfileRoot.elementIterator();
        while (ProfileIt.hasNext()) {
            Element news = (Element) ProfileIt.next();


            NewsProfile newsProfile = new NewsProfile(news.attributeValue("id"));
            newsProfile.setNewsProflie(news);

            profileList.add(newsProfile);
            //保存到数据库中去
//            newsProfile.saveNewsToMysql();
        }
    }

    /**
     * 遍历文件目录
     * @param fileList
     */
    public ArrayList<NewsProfile> parseProfile(File fileList) {
        FindAllFileOnCatalogue fileAFOC = new FindAllFileOnCatalogue();
        //文件路径下的所有文件
        List<File> filelist = fileAFOC.getCatalogueList(fileList);

        ArrayList<NewsProfile> profileList = new ArrayList<NewsProfile>();

        for(File file : filelist) {
            try {
                parseProfile(file.getAbsolutePath(), profileList);
            } catch (DocumentException e) {
            }
        }

//        System.out.println(profileList.size());

        return profileList;
    }

    /**
     * 数据预先处理
     * @param filePaths
     */
    public void beforeParser(String filePaths) {
        FindAllFileOnCatalogue fileAFOC = new FindAllFileOnCatalogue();
        //文件路径下的所有文件
        List<File> fileList = fileAFOC.getCatalogueList(new File(filePaths));
        System.out.println(fileList.size());

        for (File file : fileList) {
            String lines = JFile.getAllLines(file);
//            System.out.println(file.getName());

            String fileName = "data_parsed/" + file.getName();
            String temp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<document>" + lines + "</document>";
            JFile.saveFiles(fileName, temp);
        }
    }

    public static void main(String[] args) {
        NewsParser newsParser = new NewsParser();
        newsParser.beforeParser("data/");
    }
}