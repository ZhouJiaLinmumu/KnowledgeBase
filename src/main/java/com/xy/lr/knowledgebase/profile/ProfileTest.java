package com.xy.lr.knowledgebase.profile;


import com.xy.lr.java.nlp.wordsegment.XMLParser;
import com.xy.lr.java.nlp.wordsegment.entity.XMLDocument;
import org.dom4j.DocumentException;

public class ProfileTest {
	
	public static void main(String[] args) throws DocumentException {
		ProfileParser profileTest = new ProfileParser();
		ProfileExample Profile = 
				profileTest.parseProfile("data/OrgProfile/1449048303068178.xml");
		
//		Profile.printlnAll();
		
		XMLParser xmlParser = new XMLParser();
		XMLDocument xmlDocument = xmlParser.xmlParser("data/document.xml");
		
		profileTest.saveProfileAsFile(Profile, "rssnews.xml", "1 2 3");
		
		
		
//		ArrayList<ProfileExample> profileExamples = 
//				profileTest.parseProfile( new File("data/OrgProfile/") );
//		
//		System.out.println(profileExamples.size());
//		for ( ProfileExample profileExample : profileExamples ) {
////			profileExample.printlnAll();
//			System.out.println(profileExample.getProfileName());
//		}
		
	}
}
