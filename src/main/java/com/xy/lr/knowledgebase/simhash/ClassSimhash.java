package com.xy.lr.knowledgebase.simhash;

import com.xy.lr.java.simhash.ChineseInfoWordSeg;
import com.xy.lr.java.simhash.Simhash;
import com.xy.lr.java.tools.file.JFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xylr on 16-3-29.
 */
public class ClassSimhash {
    public static void main(String[] args) {
//        mkSimhash();
//        haming();
        removeNewCityHistory();
    }

    public static void removeNewCityHistory() {
        List<String> news = JFile.getAllLines(
                new File("cluster/simhash/news.qq.com"), "utf-8");
        List<String> city = JFile.getAllLines(
                new File("cluster/simhash/city.qq.com"), "utf-8");
        List<String> history = JFile.getAllLines(
                new File("cluster/simhash/history.news.qq.com"),"utf-8");

        File[] files = new File("data/").listFiles();
        ArrayList<String> lists = new ArrayList<String>();
        for (File file : files) {
            String name = file.getName();
//            System.out.println(file.getName());
            lists.add(name.substring(name.indexOf("_") + 1, name.indexOf(".")));
        }

//        System.out.println(lists.size());

        /*for (String string : history) {
//            System.out.println(string);
            String id = string.split("\t")[0];
            System.out.println(id);

            if (lists.contains(id)) {
                File old = new File("data/WordSeg_" + id + ".txt");
                File f = new File("data_new_city_histoty/" + id);
                old.renameTo(f);
            }
        }*/

        for (String string : city) {
//            System.out.println(string);
            String id = string.split("\t")[0];
            System.out.println(id);

            if (lists.contains(id)) {
                File old = new File("data/WordSeg_" + id + ".txt");
                File f = new File("data_new_city_histoty/" + id);
                old.renameTo(f);
            }
        }

        for (String string : news) {
//            System.out.println(string);
            String id = string.split("\t")[0];
            System.out.println(id);

            if (lists.contains(id)) {
                File old = new File("data/WordSeg_" + id + ".txt");
                File f = new File("data_new_city_histoty/" + id);
                old.renameTo(f);
            }
        }

//        System.out.println(files.length);


    }

    public static void haming() {
        File files = new File("cluster/simhash/");
        File[] fileList = files.listFiles();
        Simhash simhash = new Simhash(new ChineseInfoWordSeg());//simhash

        if (fileList != null) {
            Map<String, String> indexSimhash = new HashMap<String, String>();

            double result = 0;
            double number = 0;

            for (File file : files.listFiles()) {
                if (file.isDirectory()) {
                    continue;
                }

                System.out.println("--------------------------------------------------\n" +
                        file.getName());
                List<String> lists = JFile.getAllLines(file, "utf-8");

                for (String line : lists) {
                    String integer = line.split("\t")[0];//编号
                    String wordSeg = line.split("\t")[1];//分词结果

                    indexSimhash.put(integer, wordSeg);
                }

                double count = 0;
                double max = 0;
                double min = 0;
                int n = 0;
                for (Map.Entry<String, String> entry : indexSimhash.entrySet()) {
                    for (Map.Entry<String, String> entry1 : indexSimhash.entrySet()) {
                        if (!entry.getKey().equals(entry1.getKey())) {
                            int temp = simhash.hammingDistance(entry.getValue(),
                                    entry1.getValue());

                            if (temp == 0) {
//                                System.out.println(entry.getKey() + "\t" + entry1.getKey());
                            }
                            if (n == 0) {
                                max = temp;
                                min = temp;
                            } else {
                                if (max < temp)
                                    max = temp;//最大相似度
                                if (min > temp)
                                    min = temp;//平均相似度
                            }

                            count += temp;
                            n++;
                        }
                    }
                }

                if((count / n) > 20) {
                    result += (count / n);
                    number++;
                }


                System.out.println("平均相似度：" + (count / n) + "\t最大相似度：" + max + "" +
                        "\t最小相似度：" + min);

                indexSimhash.clear();
            }

            System.out.println("\n\n全局平均相似度：" + (result / number));
        }
    }

    public static void mkSimhash() {
        File files = new File("cluster/");
        File[] fileList = files.listFiles();
        Simhash simhash = new Simhash(new ChineseInfoWordSeg());//simhash

        if (fileList != null) {
//            Map<Integer, String> indexSimhash = new HashMap<Integer, String>();
            for (File file : files.listFiles()) {//遍历所有的文件
                if (file.isDirectory()) {
                    continue;
                }

                System.out.println(file.getName());
                List<String> lists = JFile.getAllLines(file, "utf-8");

                for (String line : lists) {
                    String integer = line.split("\t")[0];//编号
                    String wordSeg = line.split("\t")[1];//分词结果

//                    indexSimhash.put(integer, simhash.simhash64(wordSeg));

                    JFile.appendFile("cluster/simhash/" + file.getName(),
                            integer + "\t" + simhash.simhash64(wordSeg));
                }

//                System.out.println(indexSimhash.size());
//                indexSimhash.clear();
            }
        }
    }
}