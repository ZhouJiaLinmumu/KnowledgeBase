package com.xy.lr.ml.kb.profile;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Element;

/**
 * ProfileExample 解析一个Profile文件
 * */
public class ProfileExample {
	//Profile 的 ID
	private String profileId;
	
	//Profile 的 类型
	private String profileType;
	
	//Profile 的 子类型
	private String profileSubType;
	
	//Profile 的 名字
	private String profileName;
	
	//sourceDocument
	private SourceDocument sourceDocument;

	//mergeCount
	private long mergeCount;
	
	//mentions
	private long mentions;
	
	//Vip
	private String profileVip;
	
	//Relations
	private ArrayList<ProfileRelation> profileRelations;

	/**
	 * 构造函数
	 * */
	public ProfileExample () {
		this.profileId = new String();
		this.profileType = new String();
		this.profileSubType = new String();
		this.profileName = new String();
		this.sourceDocument = new SourceDocument();
		this.mergeCount = 0;
		this.mentions = 0;
		this.profileVip = new String();
		this.profileRelations = new ArrayList<ProfileRelation>();
	}
	
	/**
	 * 设置ID，Type，SubType
	 * */
	private void setProfileIDAndTypeAndSubType(String profileId, 
			String profileType, String profileSubType) {
		this.profileId = profileId;
		this.profileType = profileType;
		this.profileSubType = profileSubType;
	}

	/**
	 * 通过Element 对象设置 Profile中数据
	 * */
	public void setProfile( Element ProfileRoot ) {
		Iterator ProfileIt = ProfileRoot.elementIterator();
    	
		//设置ID、Type、SubType
		setProfileIDAndTypeAndSubType(ProfileRoot.attributeValue("id"), 
				ProfileRoot.attributeValue("type"), 
				ProfileRoot.attributeValue("subtype"));
    	
    	//遍历迭代器，获取根节点中的信息
    	while (ProfileIt.hasNext()){
    		Element profile = ( Element ) ProfileIt.next();
    		if (profile.getName().equals("name")) {//添加name属性
    			this.profileName = profile.getStringValue();
    		}
    		if(profile.getName().equals("source")) {//添加source属性
    			SourceDocument sourceDocument = new SourceDocument();
    			sourceDocument.setSourceDocument(profile);
    			this.sourceDocument = sourceDocument;
    		}
    		if(profile.getName().equals("mergeCount")) {//添加mergeCount属性
    			this.mergeCount = Long.valueOf( profile.getStringValue() );
    		}
    		if(profile.getName().equals("mentions")) {//添加mentions属性
    			this.mentions = Long.valueOf( profile.getStringValue() );
    		}
    		if(profile.getName().equals("vip")) {//添加vip属性
    			this.profileVip = profile.getStringValue();
    		}
    		if(profile.getName().equals("relation")) {//添加relation属性
    			ProfileRelation profileRelation = new ProfileRelation();
    			profileRelation.setProfileRelation(profile);
    			profileRelations.add(profileRelation);
    		}
    	}
	}
	
	/**
	 * 输出所有的数据
	 * */
	public void printlnAll () {
		System.out.println( "profileId : " + profileId + " , profileType : " +  profileType + " , profileSubType"
				+ " : " + profileSubType);
		System.out.println("\t" + "profileName : " + profileName);
		sourceDocument.printlnAll();
		System.out.println( "\t" + "mergeCount : " + mergeCount );
		System.out.println( "\t" + "mentions : " + mentions );
		System.out.println( "\t" + "profileVip : " + profileVip );
		for (ProfileRelation profileRelation : profileRelations) {
			profileRelation.printlnAll();
		}
	}
	
	/**
	 * get方法
	 * */
	public String getProfileId() {
		return profileId;
	}

	/**
	 * set方法
	 * */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	/**
	 * get方法
	 * */
	public String getProfileType() {
		return profileType;
	}

	/**
	 * set方法
	 * */
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	/**
	 * get方法
	 * */
	public String getProfileSubType() {
		return profileSubType;
	}

	/**
	 * set方法
	 * */
	public void setProfileSubType(String profileSubType) {
		this.profileSubType = profileSubType;
	}

	/**
	 * get方法
	 * */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * set方法
	 * */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * get方法
	 * */
	public long getMergeCount() {
		return mergeCount;
	}

	/**
	 * set方法
	 * */
	public void setMergeCount(long mergeCount) {
		this.mergeCount = mergeCount;
	}

	/**
	 * get方法
	 * */
	public long getMentions() {
		return mentions;
	}

	/**
	 * set方法
	 * */
	public void setMentions(long mentions) {
		this.mentions = mentions;
	}

	/**
	 * get方法
	 * */
	public String getProfileVip() {
		return profileVip;
	}

	/**
	 * set方法
	 * */
	public void setProfileVip(String profileVip) {
		this.profileVip = profileVip;
	}

	/**
	 * get方法
	 * */
	public ArrayList<ProfileRelation> getProfileRelations() {
		return profileRelations;
	}

	/**
	 * set方法
	 * */
	public void setProfileRelations(ArrayList<ProfileRelation> profileRelations) {
		this.profileRelations = profileRelations;
	}
	
	/**
	 * get方法
	 * */
	public SourceDocument getSourceDocument() {
		return sourceDocument;
	}

	/**
	 * set方法
	 * */
	public void setSourceDocument(SourceDocument sourceDocument) {
		this.sourceDocument = sourceDocument;
	}
}
