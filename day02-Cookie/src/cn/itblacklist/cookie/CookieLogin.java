package cn.itblacklist.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieLogin")
public class CookieLogin extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 需求：接收页面的数据，将用户名存储到cookie中
         *      1.post的请求乱码问题
         *      2.将数据设置到cookie中去
         *      3.输出到页面
         */
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        Cookie cookie = new Cookie("username", username);
        resp.addCookie(cookie);

        // 输出到页面
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(username + "先生：<br/>");
        resp.getWriter().print("<a href='/cookie/cookieMail'>10封未读邮件!<a/>");
    }
}
