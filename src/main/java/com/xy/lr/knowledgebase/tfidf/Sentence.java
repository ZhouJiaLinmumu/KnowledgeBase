package com.xy.lr.knowledgebase.tfidf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xylr 2016-01-02
 *
 */
public class Sentence {
	/**
	 * 句子，单词序列
	 */
	private List<String> words;
	
	/**
	 * 每个单词的tf值
	 */
	private Map<String, Double> tfs;
	
	/**
	 * 每个单词的idf值
	 */
	private Map<String, Double> idfs;
	
	/**
	 * 每个单词的tfidf值
	 */
	private Map<String, Double> tfidfs;
	
	/**
	 * 文章的总词数
	 */
	private Long sum;
	
	/**
	 * 构造函数
	 */
	public Sentence() {
		this.words = new ArrayList<String>();
		this.tfs = new HashMap<String, Double>();
		this.idfs = new HashMap<String, Double>();
		this.tfidfs = new HashMap<String, Double>();
		this.sum = 0L;
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Double> getTFIDF() {
		return this.tfidfs;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, Double> getTfidfs() {
		return tfidfs;
	}

	/**
	 * 
	 * @param tfidfs
	 */
	public void setTfidfs(Map<String, Double> tfidfs) {
		this.tfidfs = tfidfs;
	}

	/**
	 * 返回句子
	 * @return 句子
	 */
	public List<String> getWords() {
		return words;
	}

	/**
	 * 设置句子
	 * @param sentences 句子
	 */
	public void setWords( List<String> words ) {
		this.words = words;
		this.sum = Long.valueOf(words.size());
	}
	
	/**
	 * 设置句子
	 * @param sentences 句子
	 */
	public void setWords( String words ) {
		String[] strs = words.split(" ");
		for( String str : strs ) {
			this.words.add( str );
		}
		this.sum = Long.valueOf(strs.length);
	}

	/**
	 * 返回每个单词的词频
	 * @return 单词词频
	 */
	public Map<String, Double> getTfs() {
		return tfs;
	}

	/**
	 * 设置单词词频
	 * @param tfs 单词词频
	 */
	public void setTfs(Map<String, Double> tfs) {
		this.tfs = tfs;
	}

	/**
	 * 返回单词逆向词频
	 * @return 单词逆向词频
	 */
	public Map<String, Double> getIdfs() {
		return idfs;
	}

	/**
	 * 设置单词逆向词频
	 * @param idfs 单词逆向词频
	 */
	public void setIdfs(Map<String, Double> idfs) {
		this.idfs = idfs;
	}
	
	/**
	 * 返回单词总数
	 * @return 单词总数
	 */
	public Long getSum() {
		return sum;
	}

	/**
	 * 设置单词总数
	 * @param sum 单词总数
	 */
	public void setSum(Long sum) {
		this.sum = sum;
	}
	
	/**
	 * 计算单词词频
	 */
	public void caltf() {
		for( String word : words ) {//循环每个单词
			if(tfs.containsKey(word)) {
				Double tf = tfs.get(word);
				tfs.put(word, ++tf);
			}else{
				tfs.put(word, 1.0);
			}
		}
	}
	
	/**
	 * 计算TFIDF
	 */
	public void calTFIDF() {
		for ( Map.Entry<String, Double> entry : this.tfs.entrySet() ) {
			String tfWord = entry.getKey();
			Double tfDouble = entry.getValue();
			Double idfDouble = this.idfs.get(tfWord);
			
			Double tfidfDouble = tfDouble * idfDouble;
			this.tfidfs.put(tfWord, tfidfDouble);
		}
	}
	
	/**
	 * 计算逆向词频
	 * @param sentences 语料库
	 * @param documentSum 语料库文档的总数
	 */
	public void calidf(List<Sentence> sentences, Long documentSum) {
		for ( String word : this.words ) {
			if (!idfs.containsKey(word)) {
				Double a = (documentSum * 1.0) / ( countWordInSentence(sentences, word) + 1 );
//				System.out.println(documentSum + "\t" + f(sentences, word) + "\t" + word);
				Double idf = Math.log( a );
				idfs.put(word, idf);
			}
		}
	}
	
	/**
	 * 
	 * @param sentences 语料库
	 * @param word 单词
	 * @return 包含单词的语料库中的文档个数
	 */
	private Long countWordInSentence ( List<Sentence> sentences, String word ) {
		Long num = 0L;
		for ( Sentence sentence : sentences ) {
			if(sentence.containWord(word))
				num++;
		}
		return num;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.words.toString();
	}
	
	/**
	 * 判断句子是否包含该单词
	 * @param word 单词
	 * @return 句子是否包含该单词
	 */
	public boolean containWord( String word ) {
		return this.words.contains(word);
	}

	/**
	 * main
	 * @param args 参数列表
	 */
	public static void main(String[] args) {
		Sentence sentence = new Sentence();
		sentence.setWords("one two three one");
		System.out.println(sentence.getSum());
		System.out.println(sentence.containWord("four"));
		
		sentence.caltf();
		
		Map<String, Double> map = sentence.getTfs();
		for( Map.Entry<String, Double> entry : map.entrySet() ) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
		
	}
}