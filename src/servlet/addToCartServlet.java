package servlet;

import Bean.Book;
import Bean.CartItem;
import Dao.BookDao;
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
import java.util.ArrayList;
/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:用户向自己的购物车中添加商品
 */
@WebServlet(name = "addToCartServlet",urlPatterns= {"/addToCartServlet"})
public class addToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int cartItemID = Integer.parseInt(request.getParameter("id"));
        BookDao bookDao = new BookDao();
        Book product = bookDao.getBookById(cartItemID);
        CartDao cartDao = new CartDao();
        int userID = (int)(session.getAttribute("UserID"));
        //System.out.println(userID);

        ArrayList<CartItem> cartItemList = cartDao.getItemsByUserID(userID);
        ArrayList<String> cartItemNameList = new ArrayList<>();
            //收集某一用户的购物车已经存在哪些Item了
            for (CartItem cartTemp : cartItemList) {
                cartItemNameList.add(cartTemp.getCartItemName());
            }

        JSONObject jsonObject = new JSONObject();
        PrintWriter out = response.getWriter();
        if(!cartItemNameList.contains(product.getBookName())){        //如果该用户物车没有该cartItemID则 添加
            cartDao.addProductToCart(product,userID);
            jsonObject.put("isAddToCart", "1");             //返回的Json数据用于判断是否添加成功
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
        }
        else{
            jsonObject.put("isAddToCart", "0");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
