package servlet;

import Bean.CartItem;
import Dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:购物车中复选框状态改变时更新用户购物车中商品的选中状态
 */
@WebServlet(name = "checkBoxServlet",urlPatterns = {"/checkBoxServlet"})
public class checkBoxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDao cartDao = new CartDao();
        int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));
        //购物车中该商品更新的数目
        int updateState = Integer.parseInt(request.getParameter("updateState"));
        int userID = (int)(session.getAttribute("UserID"));
        cartDao.updeteCheckBoxStateByID(cartItemID,userID,updateState);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
