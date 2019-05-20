package servlet;

import Bean.User;
import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:对登录信息的处理
 */

@WebServlet(name = "LoginServlet",urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username=request.getParameter("username");       //jsp中的设置必不为空
        String password=request.getParameter("password");
        int userType = Integer.parseInt(request.getParameter("UserType"));
        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(username);
        //该用户名对应的用户的密码和类型均匹配（User中对各数据进行了初始化防止空指针错误）
        if (user.getPassWord().equals(password) && user.getUserType() == userType) {
            session.setAttribute("UserID",user.getUserID());
            session.setAttribute("UserType", user.getUserType());
            response.sendRedirect(request.getContextPath()+"/bookServlet");
        }
        else{
            response.sendRedirect("error.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
