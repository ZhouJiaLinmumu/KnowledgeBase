package com.xy.lr.ml.kb.profile;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Element;

/**
 * ProfileRelationLink 解析如下的一个实例:
 * 
 * <link>
 * 	<ne id="1449048706190719">http://whwb.cjn.cn/html/2014</ne>
 * 	<source doc="2014-11-01//武汉晚报/1414803989600.xml" id="3" pubtime="20151202173146" 
 * 		realtime="20151202173146">http://whwb.cjn.cn/html/2014</source>
 * </link>
 * 
 * */
public class ProfileRelationLink {
	//linkId
	private String linkNeId;
	
	//linkName
	private String linkNeName;
	
	//sourceDocument
	private ArrayList<SourceDocument> sourceDocuments;
	
	/**
	 * 构造函数，创建一个新的对象
	 * */
	public ProfileRelationLink(){
		linkNeId = new String();
		linkNeName = new String();
		sourceDocuments = new ArrayList<SourceDocument>();
	}
	
	/**
	 * 输出ProfileRelationLink中的所有数据
	 * */
	public void printlnAll () {
		System.out.println( "\t\t" + "linkNeId : " + linkNeId + " , linkNeName : " + linkNeName );
		for (SourceDocument sourceDocument : sourceDocuments){
			sourceDocument.printlnAAll();
		}
	}
	
	/**
	 * 通过一个Element 对象设置ProfileRelationLink数据
	 * */
	public void setProfileRelationLink ( Element relationLink ) {		
		//遍历link中的数据
		Iterator relationLinkSourceIt = relationLink.elementIterator();
		while(relationLinkSourceIt.hasNext()) {
			Element relationLinkSource = ( Element ) relationLinkSourceIt.next();
			//如果name是ne，则解析出id和data
			if( relationLinkSource.getName().equals("ne") ) {
				this.linkNeId = relationLinkSource.attributeValue("id");
				this.linkNeName = relationLinkSource.getStringValue();
			}
			//否则解析出source
			if( relationLinkSource.getName().equals("source") ) {    		    			
    			SourceDocument sourceDocument = new SourceDocument();
    			sourceDocument.setSourceDocument(relationLinkSource);
    			//添加到ArrayList<SourceDocument>中，因为可能不只一个SourceDocument
    			this.sourceDocuments.add(sourceDocument);
			}
		}
	}

	/**
	 * get方法
	 * */
	public String getLinkNeId() {
		return linkNeId;
	}

	/**
	 * set方法
	 * */
	public void setLinkNeId(String linkNeId) {
		this.linkNeId = linkNeId;
	}

	/**
	 * get方法
	 * */
	public String getLinkNeName() {
		return linkNeName;
	}

	/**
	 * set方法
	 * */
	public void setLinkNeName(String linkNeName) {
		this.linkNeName = linkNeName;
	}

	/**
	 * get方法
	 * */
	public ArrayList<SourceDocument> getSourceDocument() {
		return sourceDocuments;
	}

	/**
	 * set方法
	 * */
	public void setSourceDocument(ArrayList<SourceDocument> sourceDocuments) {
		this.sourceDocuments = sourceDocuments;
	}
}
