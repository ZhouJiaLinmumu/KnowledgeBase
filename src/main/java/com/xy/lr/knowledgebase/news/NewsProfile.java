package com.xy.lr.knowledgebase.news;

import com.xy.lr.knowledgebase.mysql.MySQLUtil;
import org.dom4j.Element;

import java.util.Iterator;

/**
 * Created by xylr on 16-3-4.
 */
public class NewsProfile {
    //
    private String newID;
    private MetaInfo metaInfo;
    private String newsText;

    public NewsProfile (String id) {
        this.newID = id;
    }

    /**
     *
     * @param ProfileRoot
     */
    public void setNewsProflie(Element ProfileRoot) {
        //遍历根节点
        Iterator ProfileIt = ProfileRoot.elementIterator();
//        System.out.println("asdasd");
        while (ProfileIt.hasNext()) {
            Element profile = (Element) ProfileIt.next();
//            System.out.println(profile.getName());
            if(profile.getName().equals("meta-info")) {
//                System.out.println("asd");
                MetaInfo metaInfo = new MetaInfo();
                metaInfo.setMetaInfo(profile);
                this.metaInfo = metaInfo;
            }
            if(profile.getName().equals("text")) {
//                System.out.println(profile.getStringValue());
                this.newsText = profile.getStringValue();
            }
        }
    }

    public void saveNewsToMysql() {
//        System.out.println(this.newID);
//        this.metaInfo.print();
//        System.out.println(this.newsText);
        String sql =
                "insert into news values ('" +
                        this.newID + "','" +
                        this.metaInfo.getNewsHost() + "','" +
                        this.metaInfo.getNewsDate() + "','" +
                        this.metaInfo.getNewsURL() + "','" +
                        this.metaInfo.getNewsTitle() + "','" +
                        this.newsText +
                "')";

        boolean result = new MySQLUtil().insertData(sql);
    }

    public String getInsertSQL() {
        String sql =
                "insert into news values ('" +
                        this.newID + "','" +
                        this.metaInfo.getNewsHost() + "','" +
                        this.metaInfo.getNewsDate() + "','" +
                        this.metaInfo.getNewsURL() + "','" +
                        this.metaInfo.getNewsTitle() + "','" +
                        this.newsText +
                        "')";
        return sql;
    }

    /**
     * getter and setter
     * */
    public String getNewID() {
        return newID;
    }

    public void setNewID(String newID) {
        this.newID = newID;
    }

    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }
}
