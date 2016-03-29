package com.xy.lr.knowledgebase.process;


import com.xy.lr.java.nlp.wordsegment.XMLParser;
import com.xy.lr.java.nlp.wordsegment.entity.ChineseWordSegment;
import com.xy.lr.java.nlp.wordsegment.entity.XMLDocument;
import com.xy.lr.knowledgebase.constant.ConstantProperty;
import com.xy.lr.knowledgebase.profile.ProfileExample;
import com.xy.lr.knowledgebase.profile.ProfileParser;
import org.dom4j.DocumentException;

import java.io.File;
import java.util.ArrayList;

/**
 * @author xylr 2015-12-23
 * 
 * */
public class AfterChineseInfoXtract {
	/**
	 * 
	 * @param args
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException{
		new AfterChineseInfoXtract().f();
	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws DocumentException
	 */
	public ArrayList<ProfileExample> parserProfile( String filePath ) throws DocumentException {
		ProfileParser profileTest = new ProfileParser();
//		ArrayList<ProfileExample> profileExamples = profileTest.parseProfile(new File("data/OrgProfile/"));
		ArrayList<ProfileExample> profileExamples = profileTest.parseProfile(new File(filePath));
		return profileExamples;

//		System.out.println(profileExamples.size());
//		for (ProfileExample profileExample : profileExamples) {
//			profileExample.printlnAll();
//			System.out.println(profileExample.getProfileName());
//		}
	}
	
	/**
	 * 解析xml文件
	 * @param filePath
	 * @param profileID
	 * @return
	 * @throws DocumentException
	 */
	public String parserXMLDocument( String filePath, String profileID ) throws DocumentException {
		XMLParser xmlParser = new XMLParser();
		XMLDocument xmlDocument =
				xmlParser.xmlParser( filePath + "/" + profileID +".xml");
		
		ChineseWordSegment chineseWordSegment = xmlParser.wordSegment(xmlDocument);
//		chineseWordSegment.printAll();
//		chineseWordSegment.saveChineseWordSegmentToFile("output/wordSegment.txt");
		String wordSegment = chineseWordSegment.getWordList();
		return wordSegment;
	}
	
	/**
	 * 
	 * @param profilePath
	 * @param xmlDocPath
	 * @param outputPath
	 * @throws DocumentException
	 */
	public void combineProfileXMLDoc( String profilePath, 
			String xmlDocPath, String outputPath ) throws DocumentException {
		ProfileParser profileTest = new ProfileParser();
		
		ArrayList<ProfileExample> profileExamples = parserProfile(profilePath);
		for ( ProfileExample profileExample : profileExamples ) {
			String profileID = profileExample.getProfileId();
			//分词结果
			String wordSegment = parserXMLDocument(xmlDocPath, profileID);
			
			profileTest.saveProfileAsFile(profileExample, 
					outputPath + "/" + profileID, wordSegment);
		}
	}
	
	/**
	 * 
	 * @throws DocumentException
	 */
	public void f() throws DocumentException {
		//LocationProfile
		System.out.println("Processing LocationProfile...");
		combineProfileXMLDoc(ConstantProperty.LocationProfileInPath, 
				ConstantProperty.LocationProfileOutPath, ConstantProperty.LocationProfileWordSegmentPath);
		//OrgProfile
		System.out.println("Processing OrgProfile...");
		combineProfileXMLDoc(ConstantProperty.OrgProfileInPath, 
				ConstantProperty.OrgProfileOutPath, ConstantProperty.OrgProfileWordSegmentPath);
		//PersonProfile
		System.out.println("Processing PersonProfile...");
		combineProfileXMLDoc(ConstantProperty.PersonProfileInPath, 
				ConstantProperty.PersonProfileOutPath, ConstantProperty.PersonProfileWordSegmentPath);
		System.out.println("All done!");
	}
}