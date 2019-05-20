package servlet;

import Dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:根据用户的ID清空购物车
 */

@WebServlet(name = "deleteCartServlet",urlPatterns = {"/deleteCartServlet"})
public class deleteCartServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDao cartDao = new CartDao();
        int userID = (int)(session.getAttribute("UserID"));
        cartDao.deleteAllCartItem(userID);      //清空该用户的购物车
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
