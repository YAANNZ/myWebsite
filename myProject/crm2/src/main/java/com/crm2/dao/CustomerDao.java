package com.crm2.dao;

import com.crm2.bean.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public List<Customer> list() {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/zyn";
        String username = "root";
        String password = "zhu1990nan09";

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Customer> customers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM customer";
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    Integer age = rs.getInt("age");
                    Double height = rs.getDouble("height");
                    Customer customer = new Customer(name, age, height);
                    customers.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    public boolean save(Customer customer) {

        String url = "jdbc:mysql://localhost:3306/zyn";
        String username = "root";
        String password = "zhu1990nan09";

        try {
            String sql = "INSERT INTO customer (name, age, height) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, customer.getName());
                pstmt.setInt(2, customer.getAge());
                pstmt.setDouble(3, customer.getHeight());
                return pstmt.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
