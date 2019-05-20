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
 *@Description:用户的购物车
 */

@WebServlet(name = "CartServlet",urlPatterns= {"/CartServlet"})
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDao cartDao = new CartDao();
        int userID = (int)(session.getAttribute("UserID"));
        ArrayList<CartItem> cartItemList = cartDao.getItemsByUserID(userID);
        //计算所选中的商品的总金额
        float AllItemPrice = 0.0f;
        for(CartItem cartItemTemp : cartItemList){
            if(cartItemTemp.getCartItemChecked() == true){
                AllItemPrice+= Float.parseFloat(cartItemTemp.getCartItemAllPrice());
            }

        }
        session.setAttribute("cartItemList", cartItemList);
        session.setAttribute("AllItemPrice",AllItemPrice);
        request.getRequestDispatcher("Cart.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
