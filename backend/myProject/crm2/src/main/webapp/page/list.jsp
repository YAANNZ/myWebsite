
<%@ page import="java.util.List" %>
<%@ page import="com.crm2.bean.Customer" %><%--
  Created by IntelliJ IDEA.
  User: zhuyn
  Date: 2023/12/13
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>客户列表</title>
    <style>
        th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<a href="/crm2/page/add.html">添加</a>

<table>
    <thead>
    <tr>
        <th>姓名</th>
        <th>电话</th>
        <th>性别</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.age}</td>
            <td>${customer.height}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
