package com.crm2.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dbs2 {
    private static String url;
    private static String username;
    private static String password;
    private static String driverClassName;
    static { // IO
        try {
            InputStream is = Dbs2.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(is);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driverClassName = properties.getProperty("driverClassName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static  <T> List<T> query(String sql, RowMapper<T> mapper, Object ...arg) {
        if (mapper == null) return null;

        try {
            Class.forName(driverClassName);
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < arg.length; i++) {
                    pstmt.setObject(i+1, arg[i]);
                }
                ResultSet rs = pstmt.executeQuery();
                List<T> array = new ArrayList<>();
                int row = 0;
                while (rs.next()) {
                    array.add(mapper.map(rs, row++));
                }
                return array;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean save(String sql, Object ...arg) {
        try {
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < arg.length; i++) {
                    pstmt.setObject(i+1, arg[i]);
                }
                return pstmt.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public interface RowMapper<T> {
        T map(ResultSet rs, int row) throws SQLException;
    }
}
