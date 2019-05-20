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
import java.util.ArrayList;

/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:用户生成订单
 */

@WebServlet(name = "OrderServlet",urlPatterns= {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDao cartDao = new CartDao();
        int userID = (int)(session.getAttribute("UserID"));
        ArrayList<CartItem> checkedCartItemList = cartDao.getCheckedItemListByUserID(userID);

        JSONObject jsonObject = new JSONObject();
        PrintWriter out = response.getWriter();
        if(checkedCartItemList == null || checkedCartItemList.size() == 0){
            jsonObject.put("isgeOneCheckedItem", "0");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
        }
        else{
            jsonObject.put("isgeOneCheckedItem", "1");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
        }
        session.setAttribute("orderItemList",checkedCartItemList);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
