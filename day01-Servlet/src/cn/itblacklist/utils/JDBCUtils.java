package cn.itblacklist.utils;

import java.sql.*;

public class JDBCUtils {
    private final String url = "jdbc:mysql://localhost:3306/java?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

    public Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }



    public static void main(String[] args){
        JDBCUtils jdbcUtils = new JDBCUtils();
        System.out.println(jdbcUtils.getConnection());
    }
}


