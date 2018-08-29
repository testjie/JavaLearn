package cn.itblacklist.dao;

import java.sql.*;
import cn.itblacklist.bean.User;


public class BaseDao {
    private final String url = "jdbc:mysql://localhost:3306/java?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public int insert(User user){
        int i = 0;
        Connection conn = getConnection();
        String sql = "insert into tbl_user values(?, ?, ?, ?)";
        PreparedStatement ps ;

        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, null);
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getSex());
            i = ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    public int update(User user){
        return 0;
    }

    public static void main(String[] args) {
        BaseDao jdbcUtils = new BaseDao();
        System.out.println(jdbcUtils.getConnection());
    }
}

