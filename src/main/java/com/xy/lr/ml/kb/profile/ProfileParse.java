package com.xy.lr.ml.kb.profile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xy.lr.mavenTest.FindAllFileOnCatalogue;

/**
 * Profile 文件处理类，提供多种处理形式
 * */
public class ProfileParse {
	//创建SAXReader的对象saxReader
	private SAXReader saxReader = new SAXReader();
	
	/**
	 * 处理一个 profile 文件
	 * */
	public ProfileExample parseProfile ( String fileName ) throws DocumentException {
    	//通过saxReader对象的
    	Document document = saxReader.read( new File( fileName ) );
    	//通过document对象获取根节点orgProfileStore
    	Element ProfileRoot = document.getRootElement();
    	
    	ProfileExample Profile = new ProfileExample();
    	
    	Profile.setProfile(ProfileRoot);
    	
    	return Profile;
	}
	
	/**
	 * 处理一个文件路径下的所有文件
	 * */
	public ArrayList<ProfileExample> parseProfile ( File filePaths ) throws DocumentException {
		ArrayList<ProfileExample> profileExamples = new ArrayList<ProfileExample>();
		
		FindAllFileOnCatalogue fileAFOC = new FindAllFileOnCatalogue();
		//文件路径下的所有文件
		List<File> fileList = fileAFOC.getCatalogueList(filePaths);
		
		profileExamples = parseProfile(fileList);
		
		return profileExamples;
	}
	
	/**
	 * 处理一个list，里面有所有的文件路径
	 * */
	public ArrayList<ProfileExample> parseProfile ( List<File> paths ) throws DocumentException {
		ArrayList<ProfileExample> profileExamples = new ArrayList<ProfileExample>();
		for ( File path : paths ) {
			profileExamples.add(parseProfile(path.getAbsolutePath()));
		}
		return profileExamples;
	}
}
