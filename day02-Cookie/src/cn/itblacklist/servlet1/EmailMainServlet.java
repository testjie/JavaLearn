package cn.itblacklist.servlet1;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/main")
public class EmailMainServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        /**
         *  需求：接受登录信息， 判断登录成功就显示页面上的信息
         *  1. 请求乱码问题
         *  2. 输出邮件列表页面
         */
        String username = req.getParameter("username");

        res.setContentType("text/html;charset=utf-8");
        res.getWriter().print(username + "先生：<br/>");
        for (int i = 0; i < 11; i++){
            res.getWriter().print("<a href='/servlet/info?username="+username+"'>第"+i+"封未读邮件</a><br/>");
        }
    }
}
