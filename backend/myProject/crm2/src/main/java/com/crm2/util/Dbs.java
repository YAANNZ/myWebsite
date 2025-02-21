package com.crm2.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dbs {
    private static DataSource ds;
    static { // IO
        try {
            InputStream is = Dbs.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static  <T> List<T> query(String sql, RowMapper<T> mapper, Object ...arg) {
        if (mapper == null) return null;

        try {
            Connection conn = ds.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
            Connection conn = ds.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
