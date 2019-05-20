package servlet;

import Bean.Book;
import Dao.BookDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:管理员修改商品信息
 */

@WebServlet(name = "modifyProServlet",urlPatterns = {"/modifyProServlet"})
public class modifyProServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String productAuthor = request.getParameter("productAuthor");
        String productDes = request.getParameter("productDes");

        BookDao bookDao = new BookDao();
        //productTemp若不存在返回的是初始化的Book对象
        Book product = new Book();


        //匹配商品价格格式(数字以及小数点)
        String check = "((^[1-9]\\d*)|^0)(\\.\\d{0,2}){0,1}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(productPrice);
        Boolean priceFlag = matcher.matches();


        JSONObject jsonObject = new JSONObject();
        PrintWriter out = response.getWriter();
        //商品价格格式不正确设置error为"1"
        if(priceFlag == false){
            jsonObject.put("error", "1");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
            return;
        }
        //格式正确则设置error为"0"
        else{
            product.setBookName(productName);
            product.setBookPrice(productPrice);
            product.setBookAuthor(productAuthor);
            product.setBookDescription(productDes);
            bookDao.modifyProInfo(product);

            jsonObject.put("error", "0");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
