package com.xy.lr.knowledgebase.news;

import org.dom4j.Element;

import java.util.Iterator;

/**
 * Created by xylr on 16-3-4.
 */
public class MetaInfo {
    private int id;
    private String newsHost;
    private String newsDate;
    private String newsURL;
    private String newsTitle;
    private String newsSourceencoding;

    public void print() {
        System.out.println(this.newsHost);
        System.out.println(this.newsDate);
        System.out.println(this.newsURL);
        System.out.println(this.newsTitle);
        System.out.println(this.newsSourceencoding);
    }

    public void setMetaInfo(Element profile) {
        Iterator ProfileIt = profile.elementIterator();

        while (ProfileIt.hasNext()) {
            Element prof = (Element) ProfileIt.next();
//            System.out.println(prof.getName());
            if(prof.attributeValue("name").equals("host")) {
                this.newsHost = prof.getStringValue();
            }
            if(prof.attributeValue("name").equals("date")) {
                this.newsDate = prof.getStringValue();
            }
            if(prof.attributeValue("name").equals("url")) {
                this.newsURL = prof.getStringValue();
            }
            if(prof.attributeValue("name").equals("title")) {
                this.newsTitle = prof.getStringValue();
            }
            if(prof.attributeValue("name").equals("source-encoding")) {
                this.newsSourceencoding = prof.getStringValue();
            }
        }

    }

    /**
     * getter and setter
     *
     * */
    public String getNewsHost() {
        return newsHost;
    }

    public void setNewsHost(String newsHost) {
        this.newsHost = newsHost;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSourceencoding() {
        return newsSourceencoding;
    }

    public void setNewsSourceencoding(String newsSourceencoding) {
        this.newsSourceencoding = newsSourceencoding;
    }
}
