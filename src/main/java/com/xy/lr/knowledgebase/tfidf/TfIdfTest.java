package com.xy.lr.knowledgebase.tfidf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import java.util.Map;

public class TfIdfTest {
	public static void main(String[] args) {
		String document1 = "one two one one";
		String document2 = "three four";
		
		List<String> list = new ArrayList<String>();
		TfIdf tfIdf = new TfIdf(list);
		list.add(document1);
		list.add(document2);
		
		List<String> list1 = tfIdf.getDocuments();
		Map<String, Double> tI = tfIdf.tfidf_tweak1();
		
		for(Map.Entry<String, Double> entry : tI.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
		
//		for(String str : list1) {
//			System.out.println(str);
//		}
	}

//	private static final double EPSILON = 1e-100;

	// @Test
	// public void testDocumentsListNotNull() {
	// String document1 = "one two";
	// String document2 = "three four";
	// List<String> list = new ArrayList<String>();
	// list.add(document1);
	// list.add(document2);
	// TfIdf tfIdf = new TfIdf(list);
	// assertNotNull("list", tfIdf.getDocuments());
	// }
	//
	// @Test
	// public void testDocumentsListElements() {
	// String document1 = "one two";
	// String document2 = "three four";
	// List<String> list = new ArrayList<String>();
	// list.add(document1);
	// list.add(document2);
	// TfIdf tfIdf = new TfIdf(list);
	// assertEquals(tfIdf.getDocuments().size(), 2);
	// }
	//
	// @Test
	// public void testTf() throws Exception {
	// String document1 = "one one two";
	// String document2 = "three three three four five five";
	// List<String> list = new ArrayList<String>();
	// list.add(document1);
	// list.add(document2);
	// TfIdf tfIdf = new TfIdf(list);
	// Map<String, MutableInt> map = tfIdf.tf();
	// assertNotNull(map);
	// assertEquals(map.get("one").getCounter(), 2);
	// assertEquals(map.get("two").getCounter(), 1);
	// assertEquals(map.get("three").getCounter(), 3);
	// assertEquals(map.get("four").getCounter(), 1);
	// assertEquals(map.get("five").getCounter(), 2);
	// }
	//
	// @Test
	// public void testDf() throws Exception {
	// String document1 = "one two one";
	// String document2 = "one four five two";
	// List<String> list = new ArrayList<String>();
	// list.add(document1);
	// list.add(document2);
	// TfIdf tfIdf = new TfIdf(list);
	// Map<String, MutableInt> map = tfIdf.df(list);
	// assertNotNull(map);
	// assertEquals(map.get("one").getCounter(), 2);
	// assertEquals(map.get("two").getCounter(), 2);
	// assertEquals(map.get("four").getCounter(), 1);
	// }
	//
	// @Test
	// public void testIdf() throws Exception {
	// String document1 = "one two one";
	// String document2 = "one four five";
	// String document3 = "one six seven two";
	// List<String> list = new ArrayList<String>();
	// list.add(document1);
	// list.add(document2);
	// list.add(document3);
	// TfIdf tfIdf = new TfIdf(list);
	// Map<String, Double> map = tfIdf.idf();
	// assertNotNull(map);
	// assertEquals(map.get("one"), 0.3333333333333333, EPSILON);
	// assertEquals(map.get("two"), 0.5, EPSILON);
	// assertEquals(map.get("four"), 1.0, EPSILON);
	// }
	//
	// @Test
	// public void testTfidf() throws Exception {
	// String document1 = "one two one";
	// String document2 = "one four five";
	// String document3 = "one six seven two";
	// List<String> list = new ArrayList<String>();
	// list.add(document1);
	// list.add(document2);
	// list.add(document3);
	// TfIdf tfIdf = new TfIdf(list);
	// Map<String, Double> map = tfIdf.idf();
	// assertNotNull(map);
	// assertEquals(map.get("two"), 0.5, EPSILON);
	// assertEquals(map.get("one"), 0.3333333333333333, EPSILON);
	// assertEquals(map.get("four"), 1.0, EPSILON);
	// }
	//
	// @Test
	// public void testTfidf_tweak1() throws Exception {
	// String document1 = "one two one";
	// String document2 = "one four five";
	// String document3 = "one six seven two";
	// List<String> list = new ArrayList<String>();
	// list.add(document1);
	// list.add(document2);
	// list.add(document3);
	// TfIdf tfIdf = new TfIdf(list);
	// Map<String, Double> map = tfIdf.idf();
	// assertNotNull(map);
	// assertEquals(map.get("one"), 0.3333333333333333, EPSILON);
	// assertEquals(map.get("four"), 1.0, EPSILON);
	// }
}
