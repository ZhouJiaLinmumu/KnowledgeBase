package com.xy.lr.knowledgebase.process;


import com.xy.lr.knowledgebase.constant.ConstantProperty;
import com.xy.lr.knowledgebase.profile.ProfileExample;
import com.xy.lr.knowledgebase.profile.ProfileParser;
import org.dom4j.DocumentException;

import java.io.*;
import java.util.ArrayList;

/**
 * @author xylr 2015-12-17
 * 
 * 通过前一步处理得到的Profile
 * */
public class PreChineseInfoXtract {
	//profile 文件解析类
	private ProfileParser profileParser;
	
	/**
	 * 构造函数
	 * */
	public PreChineseInfoXtract() {
		profileParser = new ProfileParser();
	}
	
	/**
	 * 提取之前处理的Profile文件中下面的信息
	 * <source doc="...">西安晚报;</source>
	 * */
	public void extractInfoByProfile(String profileInPath, String profileOutPath) {
		//profile 列表
		ArrayList<ProfileExample> profileExamples = new ArrayList<ProfileExample>();
		try {
			profileExamples = profileParser.parseProfile( new File(profileInPath) );
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		//遍历profile 列表
		for ( ProfileExample profileExample : profileExamples ) {
			//保存文件
			try {
				saveAsFile(profileExample, profileOutPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理所有的Profile
	 * */
	public void extractAllInfoByProfile (){
		//处理LocationProfile
		extractInfoByProfile(ConstantProperty.LocationProfileInPath,
				ConstantProperty.LocationProfileOutPath);
		
		//处理OrgProfile
		extractInfoByProfile(ConstantProperty.OrgProfileInPath, 
				ConstantProperty.OrgProfileOutPath);
		
		//处理PersonProfile
		extractInfoByProfile(ConstantProperty.PersonProfileInPath, 
				ConstantProperty.PersonProfileOutPath);
	}
	
	/**
	 * 保存文件
	 * @throws IOException 
	 * */
	public void saveAsFile(ProfileExample profileExample,
			String profileOutPath) throws IOException {
		BufferedWriter writer = null;
		if(!profileOutPath.endsWith("/")){
			profileOutPath = profileOutPath+"/";
		}
		
		FileOutputStream writerStream = new FileOutputStream(
				profileOutPath + profileExample.getProfileId());
		writer = new BufferedWriter(
				new OutputStreamWriter(writerStream, "UTF-8"));
		writer.write(profileExample.getSourceDocument().getSourceValue());
		writer.close();
		
//		System.out.println(profileExample.getProfileName());
//		System.out.println(profileExample.getProfileId());
//		System.out.println(profileExample.getProfileType());
//		System.out.println(profileExample.getSourceDocument().getSourceValue());
	}
	
	/**
	 * 
	 * */
	public static void main(String[] args){
		PreChineseInfoXtract pChinese = new PreChineseInfoXtract();
		pChinese.extractAllInfoByProfile();
	}
}