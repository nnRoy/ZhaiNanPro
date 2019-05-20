package servlet;

import Dao.CartDao;

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
 *@Description:用户从购物车中移除某商品
 */

@WebServlet(name = "removeFromCartServlet" ,urlPatterns = {"/removeFromCartServlet"})
public class removeFromCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));   //商品ID
        CartDao cartDao = new CartDao();
        int userID = (int)(session.getAttribute("UserID"));
        cartDao.removeCartItemByID(userID,cartItemID);
        //int CartItemNum = cartDao.getCartItemNumByUserID(userID);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
