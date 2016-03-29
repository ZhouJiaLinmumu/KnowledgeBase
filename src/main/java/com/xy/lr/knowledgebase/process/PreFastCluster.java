package com.xy.lr.knowledgebase.process;

import com.xy.lr.java.tools.time.DateTime;
import com.xy.lr.knowledgebase.constant.ConstantProperty;
import com.xy.lr.knowledgebase.profile.ProfileParser;
import com.xy.lr.knowledgebase.profile.ProfileWithWordSegment;
import com.xy.lr.knowledgebase.tfidf.TFIDF;
import com.xy.lr.knowledgebase.tfidf.TfIdf;
import org.dom4j.DocumentException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PreFastCluster 类计算每个Profile中分词后的文本TF-IDF值
 * @author xylr 2016-01-02
 *
 */
public class PreFastCluster {
	public static void main(String[] args) {
		try {
			new PreFastCluster().profileAll();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws DocumentException 
	 * 
	 */
	public void profileAll() throws DocumentException {
		//LocationProfileWordSegmentPath
		System.out.println("Processing LocationProfile...");
//		parserProfile(ConstantProperty.LocationProfileWordSegmentPath, "LocationProfile");
		//OrgProfileWordSegmentPath
		System.out.println("-------------------------------------------------------------"
				+ "\nProcessing OrgProfile...");
		parserProfile(ConstantProperty.OrgProfileWordSegmentPath, "OrgProfile");
		//PersonProfileWordSegmentPath
		System.out.println("-------------------------------------------------------------"
				+ "\nProcessing PersonProfile...");
//		parserProfile(ConstantProperty.PersonProfileWordSegmentPath, "PersonProfile");
		System.out.println("-------------------------------------------------------------"
				+ "\nAll done!");
	}
	
	/**
	 * 
	 * @param filePath
	 * @throws DocumentException 
	 */
	public void parserProfile( String filePath, String type ) throws DocumentException {
		Long time1 = System.currentTimeMillis();
		TFIDF tfidf = new TFIDF();
		ProfileParser parser = new ProfileParser();
		ArrayList<ProfileWithWordSegment> profiles = parser.parserProfileWithWord(new File(filePath));
		
		List<String> list = new ArrayList<String>();
		for(ProfileWithWordSegment profile : profiles) {
			String wordSegment = profile.getSourceDocumentWordSegment();
			list.add(wordSegment);
		}
		Long time2 = System.currentTimeMillis();
		System.out.println("Profile Cost time(ms) : " + DateTime.formatTime((time2 - time1)));
		System.out.println("Starting TFIDF...");
		tfidf.addSentence(list);
		System.out.println(tfidf.getSum());
		tfidf.calAll( type );
		System.out.println("Ending TFIDF...");
		Long time3 = System.currentTimeMillis();
		System.out.println("TFIDF Cost time(ms) : " + DateTime.formatTime((time3 - time2)));
		
		
		
		
//		System.out.println(list.size());
		//计算出来的TF-IDF值
//		Map<String, Double> tfidf = calTfIdf(list);
//		System.out.println(tfidf.size());
		
//		f(profiles, tfidf, type);
	}
	
	/**
	 * 
	 * @param profiles
	 * @param tfidf
	 * @param type
	 */
	public void f(ArrayList<ProfileWithWordSegment> profiles, Map<String, Double> tfidf, String type) {
		int sum = profiles.size();
		int number = 0;
		for ( ProfileWithWordSegment profile : profiles ) {
			number ++;
			double rate = number * 1.0 / sum;
			System.out.println(rate);
			if(rate % 10 == 0){
				System.out.println(rate);
			}
			String profileID = profile.getProfileId();
			String wordSegment = profile.getSourceDocumentWordSegment();
//			System.out.println(wordSegment);
			
			String word = new String();
			for( Map.Entry<String, Double> entry : tfidf.entrySet()) {
//				System.out.println( entry.getKey() + "\t" + entry.getValue() );
				if(wordSegment.contains( entry.getKey()) ) {
					word += entry.getValue() + ",";
				}else{
					word += Double.valueOf(1.0) + ",";
				}
			}
//			word = word.substring(word.length() - 2);
//			System.out.println(word);
//			word = new String();
			saveAsTextFile(profileID, word, type);
		}
		
	}
	
	public void saveAsTextFile(String profileId, String word, String type) {
		BufferedWriter writer = null;
//		if(!profileOutPath.endsWith("/")){
//			profileOutPath = profileOutPath+"/";
//		}
		
		FileOutputStream writerStream;
		try {
			writerStream = new FileOutputStream(type + ".txt", true);
			writer = new BufferedWriter(
					new OutputStreamWriter(writerStream, "UTF-8"));
			writer.write(profileId + "\t" + word + "\n");
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 通过文章列表计算TF-IDF值
	 * @param list 文章列表
	 * @return TF-IDF计算值
	 */
	public Map<String, Double> calTfIdf(List<String> list) {
		Map<String, Double> tfidfData = new HashMap<String, Double>();
		TfIdf tfidf = new TfIdf(list);
		//计算TF-IDF值
		tfidfData = tfidf.tfidf_tweak1();
//		tfidfData = tfidf.tfidf();
		
		return tfidfData;
	}
}
