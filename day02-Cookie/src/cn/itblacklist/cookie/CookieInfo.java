package cn.itblacklist.cookie;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/cookieInfo")
public class CookieInfo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String username = "";
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("username")){
                username = cookie.getValue();
            }
        }

        // 输出
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(username + "先生：<br/>");
        resp.getWriter().print("邮件详情");
    }
}
