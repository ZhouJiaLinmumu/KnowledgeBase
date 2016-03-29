package com.xy.lr.knowledgebase.tfidf;



import com.xy.lr.java.tools.time.DateTime;

import java.io.*;
import java.util.*;

/**
 * TFIDF
 * @author xylr 2016-01-02
 *
 */
public class TFIDF {
	/**
	 * sentence 列表
	 */
	private List<Sentence> sentences;
	
	/**
	 * tf 值
	 */
	private List<Map<String, Double>> tfs;
	
	/**
	 * idf 值
	 */
	private List<Map<String, Double>> idfs;
	
	/**
	 * 语料库的文档总数
	 */
	private Long sum;
	
	/**
	 * wordList，文档单词词表
	 */
	private Set<String> wordList;
	
	/**
	 * 构造函数
	 */
	public TFIDF() {
		this.sentences = new ArrayList<Sentence>();
		this.sum = 0L;
		this.wordList = new HashSet<String>();
		this.tfs = new ArrayList<Map<String,Double>>();
		this.idfs = new ArrayList<Map<String,Double>>();
	}
	
	/**
	 * 
	 * @param file
	 */
	private BufferedWriter getBufferWriter( String file ) {
		BufferedWriter writer = null;
		FileOutputStream writerStream;
		try {
			writerStream = new FileOutputStream(file + ".txt", true);
			writer = new BufferedWriter(
					new OutputStreamWriter(writerStream, "UTF-8"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer;
	}
	
	/**
	 * 
	 * @param type
	 */
	public void calAll( String type ) {
		calTFIDF();
		
		BufferedWriter writer = getBufferWriter(type);
		
		for ( Sentence sentence : this.sentences ) {
			Map<String, Double> sentenceTfidfs = sentence.getTFIDF();
			
			String string = new String();
			for ( String word : this.wordList ) {
				if( sentenceTfidfs.get(word) == null ) {
					string += "0.0,";
				}else{
					string += sentenceTfidfs.get(word) + ",";
				}
			}
			if(string.length() > 0){
				string = string.substring(0, string.length() - 1);
			}
			System.out.println(string);
			try {
				writer.write(string + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		writerClose(writer);
	}
	
	/**
	 * 关闭文件写入对象
	 * @param writer BufferedWriter
	 */
	private void writerClose(BufferedWriter writer) {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 返回文档单词词表
	 * @return 文档单词词表
	 */
	public Set<String> getWordList() {
		return wordList;
	}

	/**
	 * 设置文档单词词表
	 * @param wordList 文档单词词表
	 */
	public void setWordList(HashSet<String> wordList) {
		this.wordList = wordList;
	}
	
	/**
	 * 计算词频
	 */
	public void caltf() {
		for ( Sentence sentence : this.sentences ) {
			//每个句子计算词频
			sentence.caltf();
		}
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	public List<Map<String, Double>> getTFs() {
//		for( Sentence sentence : this.sentences ) {
//			this.tfs.add(sentence.getTfs());
//		}
//		return this.tfs;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public List<Map<String, Double>> getIDFs() {
//		for( Sentence sentence : this.sentences ) {
//			this.idfs.add(sentence.getIdfs());
//		}
//		return this.idfs;
//	}
	
	/**
	 * 计算逆向词频
	 */
	public void calidf() {
		for ( Sentence sentence : this.sentences ) {
			sentence.calidf(sentences, this.sum);
		}
	}
	
	/**
	 * 计算TFIDF
	 * @return
	 */
	public void calTFIDF() {
		System.out.println("Starting TF...");
		Long time1 = System.currentTimeMillis();
		//TF
		caltf();
		Long time2 = System.currentTimeMillis();
		System.out.println("TF Cost time : " + DateTime.formatTime((time2 - time1)));
		System.out.println("Ending TF...");
		System.out.println("Starting IDF...");
		//IDF
		calidf();
		Long time3 = System.currentTimeMillis();
		System.out.println("IDF Cost time : " + DateTime.formatTime((time3 - time2)));
		System.out.println("Ending IDF...");
		
		List<Map<String, Double>> tfidfs = new ArrayList<Map<String, Double>>();
		
		//TF-IDF
		for ( Sentence sentence : this.sentences ) {
			sentence.calTFIDF();
			tfidfs.add(sentence.getTfidfs());
		}
	}

	/**
	 * 返回句子
	 * @return 句子
	 */
	public List<Sentence> getSentences() {
		return sentences;
	}

	/**
	 * 设置句子
	 * @param sentences 句子
	 */
	public void setSentences(ArrayList<Sentence> sentences) {
		this.sentences = sentences;
		//总文档数
		this.sum = Long.valueOf( sentences.size() );
		//单词个数
		for ( Sentence sentence : sentences ) {
			List<String> words = sentence.getWords();
			this.wordList.addAll(words);
		}
	}
	
	/**
	 * 添加句子
	 * @param sentences 句子
	 */
	public void addSentences(ArrayList<Sentence> sentences) {
		this.sum += sentences.size();
		for ( Sentence sentence : sentences ) {
			this.sentences.add(sentence);
			List<String> words = sentence.getWords();
			this.wordList.addAll(words);
		}
	}
	
	/**
	 * 添加句子
	 * @param sentences 句子
	 */
	public void addSentence(List<String> sentences) {
		Long time1 = System.currentTimeMillis();
		this.sum += sentences.size();
		for( String sentence : sentences ) {
			Sentence sen = new Sentence();
			sen.setWords( sentence );
			this.sentences.add(sen);
			this.wordList.addAll(sen.getWords());
		}
		Long time2 = System.currentTimeMillis();
		System.out.println("TFIDF addSentence Cost time : " + DateTime.formatTime((time2 - time1)));
	}
	
	/**
	 * 添加句子
	 * @param sentences 句子
	 */
	public void addSentences( Sentence sentences ) {
		this.sentences.add(sentences);
		this.sum++;
		this.wordList.addAll(sentences.getWords());
	}
	
	/**
	 * 添加单个句子
	 * @param sentences 句子
	 */
	public void addSentences( String sentences ) {
		Sentence sentence = new Sentence();
		sentence.setWords( sentences );
		//添加句子
		this.sentences.add( sentence );
		this.sum++;
		this.wordList.addAll(sentence.getWords());
	}
	
	/**
	 * 获得语料库文档总数
	 * @return 语料库文档总数
	 */
	public Long getSum() {
		return sum;
	}
	
	/**
	 * 重新设置语料库文档总数
	 * @param sum 语料库文档总数
	 */
	public void resetSum( Long sum ) {
		this.sum = sum;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TFIDF tfidf = new TFIDF();
		tfidf.addSentences("one two three one");
		tfidf.addSentences("two three four");
		
		Sentence sentence = new Sentence();
		sentence.setWords("three four five");
		tfidf.addSentences(sentence);
		
		tfidf.calAll( "test" );

//		List<Map<String, Double>> tfidfs = tfidf.calTFIDF();
//		
//		System.out.println("TFIDF");
//		for(Map<String, Double> map : tfidfs) {
//			for(Map.Entry<String, Double> idf : map.entrySet()) {
//				System.out.print(idf.getKey() + "\t" + idf.getValue() + "\t");
//			}
//			System.out.println();
//		}
	}
}