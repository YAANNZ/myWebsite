package com.zyn.website.home;


import javax.servlet.*;
import java.io.IOException;

public class ServletHome implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 初始化
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 提供服务
        System.out.println("Hello, servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        // 销毁：服务器正常关闭时执行
        
    }
}
