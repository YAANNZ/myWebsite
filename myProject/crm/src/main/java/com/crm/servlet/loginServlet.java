package com.crm.servlet;

import com.crm.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 设置解析请求参数的编码
        req.setCharacterEncoding("UTF-8");

        // 2. 读取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 3. 设置响应数据的编码
        resp.setContentType("text/html; charset=UTF-8");

        // 4. 拿到输入流
        PrintWriter out = resp.getWriter();

        // 5. 判断
        if ("123".equals(username) && "123".equals(password)) {
            success(out);
        } else {
            failed(out);
        }

    }

    private void success(PrintWriter out) {
        out.write("<h1 style=\"color: blue; border: 1px solid black;\">登录成功</h1>");
        out.write("<table>");
        out.write("<thead>");
        out.write("<tr>");
        out.write(" <th>姓名</th>");
        out.write("<th>电话</th>");
        out.write("<th>性别</th>");
        out.write("</tr>");
        out.write("</thead>");
        out.write("<tbody>");

        List<Customer> customers = getCustomers();
        for (Customer customer : customers) {
            out.write("<tr>");
            out.write("<td>"+ customer.getName() +"</td>");
            out.write("<td>" + customer.getSex() + "</td>");
            out.write("<td>" + customer.getPhone() + "</td>");
            out.write("</tr>");
        }

        out.write("</tbody>");
        out.write("</table>");
    }

    private void failed(PrintWriter out) {
        out.write("<h1 style=\"color: red; border: 1px solid black;\">登录失败</h1>");
        out.write("<ul>");
        out.write("<a href=\"http://localhost:8080/crm/login.html\">重新登录</a>");
        out.write("</ul>");
    }

    private List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer("张三" + i, "12345" + 1, (i & 1) == 1 ? "男":"女"));
        }
        return customers;
    }
}
