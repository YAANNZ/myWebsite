package com.crm2.servlet;

import com.crm2.Data;
import com.crm2.bean.Customer;
import com.crm2.dao.CustomerDao;
import com.mysql.cj.protocol.Resultset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.CSS;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list")
public class list extends HttpServlet {
    private CustomerDao dao = new CustomerDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        List<Customer> customers = null;
//        try {
//            customers = getCustomers();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        req.setAttribute("customers", dao.list());

        req.getRequestDispatcher("/page/list.jsp").forward(req, resp);
    }

//    private List<Customer> getCustomers() throws Exception {
////        String driverClassName = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/zyn";
//        String username = "root";
//        String password = "zhu1990nan09";
//
////        Class.forName(driverClassName);
//
//        List<Customer> customers = new ArrayList<>();
//
//        try {
//            String sql = "SELECT * FROM customer";
//            try (Connection conn = DriverManager.getConnection(url, username, password);
//                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
//                ResultSet rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    String name = rs.getString("name");
//                    Integer age = rs.getInt("age");
//                    Double height = rs.getDouble("height");
//                    Customer customer = new Customer(name, age, height);
//                    customers.add(customer);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return customers;
//    }


//    private List<Customer> getCustomers() {
//        List<Customer> customers = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            customers.add(new Customer("张三" + i, 10 + i, 1.55 + 1));
//        }
//        return customers;
//    }
}
