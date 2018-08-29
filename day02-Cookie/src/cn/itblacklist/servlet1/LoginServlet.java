package cn.itblacklist.servlet1;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        /**
         *  需求：接受登录信息， 判断登录成功就显示页面上的信息
         *  1. 请求乱码问题
         *  2. 接收数据，进行判断
         *  3. 输出邮件列表的页面，输出乱码问题解决
         */
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals("admin") && password.equals("admin")){
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().print(username+"先生：<br/>");
            res.getWriter().print("<a href='/servlet/main?username="+username+"'>未读邮件有10封!</a>");
        }


    }
}
