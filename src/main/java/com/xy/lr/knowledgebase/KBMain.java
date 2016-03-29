package com.xy.lr.knowledgebase;

import com.xy.lr.java.jni.ChineseInfoXtract4j;
import com.xy.lr.java.jni.FirstDemo;

/**
 * Hello world!
 *
 */
public class KBMain {
    public static void main( String[] args ) {
//        System.out.println( "Hello World!" );
//        FirstDemo demo = new FirstDemo();
//        System.out.println("num:"+demo.getNum()+",string:"+demo.getString());

        ChineseInfoXtract4j.ChineseProcessor("/opt/ChineseInfoXtract/bin" +
                "/ChineseProcessor -i ~/Working/IdeaProjects/KnowLedgeBase/1.txt" +
                " -o ~/Working/IdeaProjects/KnowLedgeBase/1.xml --xmlOut");

    }
}
