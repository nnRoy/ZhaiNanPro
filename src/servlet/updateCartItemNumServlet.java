package servlet;

import Bean.CartItem;
import Dao.CartDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:用户根据购物车中"+""-"商品数更新购物车数据库
 */

@WebServlet(name = "updateCartItemNumServlet",urlPatterns = {"/updateCartItemNumServlet"})
public class updateCartItemNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDao cartDao = new CartDao();
        int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));
        //购物车中该商品更新的数目
        int updateNum = Integer.parseInt(request.getParameter("updateNum"));
        int userID = (int)(session.getAttribute("UserID"));
        CartItem cartItem = cartDao.getCartItemByID(cartItemID,userID);
        //更新后购物车中该商品的数目
        int CartItemNum = cartItem.getCartItemNum() + updateNum ;
        //更新后购物车中该商品的总金额(保留两位小数)
        float tempPrice = Float.parseFloat(cartItem.getCartItemPrice()) * CartItemNum;
        DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足
        String CartItemAllPrice = decimalFormat.format(tempPrice);
        //更新数据库中该商品在该用户购物车中的情况
        cartDao.updateCartItemNumByID(cartItemID,userID,CartItemNum,CartItemAllPrice);

        //request.getRequestDispatcher("Cart.jsp").forward(request,response);

      /*  //设置更新数量关系后的商品在购物车中的信息
        CartItem cartItem1 = cartDao.getCartItemByID(cartItemID,userID);
        JSONObject jsonObject = new JSONObject();
        PrintWriter out = response.getWriter();
        jsonObject.put("cartItemNum",cartItem1.getCartItemNum());
        jsonObject.put("cartItemAllPrice",cartItem1.getCartItemAllPrice());
        response.setContentType("application/json");
        out.print(jsonObject);
        out.flush();
        out.close();*/

      /*  CartItem cartItem1 = cartDao.getCartItemByID(cartItemID,UserID);
        HttpSession session = request.getSession();
        session.setAttribute("cartItemNum",cartItem1.getCartItemNum());
        session.setAttribute("cartItemAllPrice",cartItem1.getCartItemAllPrice());*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
