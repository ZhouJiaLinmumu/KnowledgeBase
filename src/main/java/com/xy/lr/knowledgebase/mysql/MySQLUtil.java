package com.xy.lr.knowledgebase.mysql;

import com.xy.lr.knowledgebase.news.NewsProfile;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by xylr on 16-3-5.
 */
public class MySQLUtil {
    public static String url = "jdbc:mysql://localhost:3306/kbase?" +
            "user=root&password=110";
    public static Connection conn = null;

    /**
     * 构造函数
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("成功加载MySQL驱动");
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     */
    private void createTable() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("Create table news(\n" +
                    "newID char(30) primary key,\n" +
                    "newHost char(30),\n" +
                    "newDate date,\n" +
                    "newURL char(100),\n" +
                    "newTitle char(50),\n" +
                    "News varchar(255));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向数据库里面插入一条数据
     * @param sql
     * @return
     */
    public boolean insertData(String sql) {
        try{
            Statement stmt = conn.createStatement();
            //执行添加操作
            stmt.execute(sql);
        } catch (SQLException e){
            System.err.println("mysql insert error!");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertData(ArrayList<NewsProfile> profileList) {
        try{
            Statement stmt = conn.createStatement();
            //执行添加操作
            for(NewsProfile profile : profileList) {
                String sql = profile.getInsertSQL();
                stmt.execute(sql);
            }
        } catch (SQLException e){
            System.err.println("mysql insert error!");
//            e.printStackTrace();

            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {

        for(int i = 100; i < 1000000; i++) {
            boolean result = new MySQLUtil().insertData("insert into student values('"+ i + "', 24)");
        }
//        boolean result1 = select();
    }

    /**
     * 查询语句
     * @return
     */
    public static boolean select() {
        String sql;
        try{
            Statement stmt = conn.createStatement();

            sql = "select * from student";
            ResultSet rs = stmt.executeQuery(sql);
//            System.out.println("姓名\t年龄");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }
        } catch (SQLException e){
            System.err.println("mysql select error!");
//            e.printStackTrace();
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }
}