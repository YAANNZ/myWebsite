package com.crm2.servlet;

import com.crm2.Data;
import com.crm2.bean.Customer;
import com.crm2.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.*;

@WebServlet("/save")
public class save extends HttpServlet {
    private CustomerDao dao = new CustomerDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String height = req.getParameter("height");

        Customer customer = new Customer(name, Integer.valueOf(age), Double.valueOf(height));

        if (dao.save(customer)) {
            resp.sendRedirect("/crm2/list");
        } else {
            req.setAttribute("error", "保存用户信息失败");
            req.getRequestDispatcher("/page/error.jsp").forward(req, resp);
        }
    }

}
