package com.xy.lr.ml.kb.profile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ProfileTest {
	
	public static void main(String[] args) throws DocumentException {
		ProfileParse ProfileTest = new ProfileParse();
		ProfileExample Profile = ProfileTest.parseProfile("data/OrgProfile/1449048303068178.xml");
		
//		OrgProfileParse asd = new OrgProfileParse(new ArrayList<String>());
//		Profile.printlnAll();
		
		ArrayList<ProfileExample> profileExamples = ProfileTest.parseProfile( new File("data/OrgProfile/") );
		
//		System.out.println(profileExamples.size());
//		for ( ProfileExample profileExample : profileExamples ) {
//			profileExample.printlnAll();
//		}
		
	}
}
