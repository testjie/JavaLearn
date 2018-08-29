package cn.itblacklist.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import cn.itblacklist.bean.User;
import cn.itblacklist.dao.impl.UserDaoImpl;

public class ServletDemo3 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("this is method init...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("这是service");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.out.println("this is method do get");
        System.out.println(req.getParameter("username"));
        System.out.println(req.getRequestURL());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        String username =  req.getParameter("username");
        String password =  req.getParameter("password");
        String sex = req.getParameter("sex");

        User user = new User();
        user.setSex(sex);
        user.setUsername(username);
        user.setPassword(password);

        PrintWriter out = resp.getWriter();
        try {
            int isSuccess = regesitUser(user);
            if(isSuccess == 1){
                out.write("成功!");
            }else{
                out.write("失败!");
            }
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private int regesitUser(User user) throws Exception{
        UserDaoImpl userDao  = new UserDaoImpl();
        return userDao.save(user);
    }


    @Override
    public void destroy() {
        super.destroy();
        System.out.println("this is method destory...");
    }
}
