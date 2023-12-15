package com.crm2.servlet;

import com.crm2.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list")
public class list extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 1. 设置解析请求参数的编码
//        req.setCharacterEncoding("UTF-8");
//
//        // 2. 读取参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        // 3. 设置响应数据的编码
//        resp.setContentType("text/html; charset=UTF-8");

        List<Customer> customers = getCustomers();
        req.setAttribute("customers", customers);

        req.getRequestDispatcher("/page/list.jsp").forward(req, resp);

    }

    private List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer("张三" + i, 10 + i, 1.55 + 1));
        }
        return customers;
    }
}
