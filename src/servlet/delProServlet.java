package servlet;

import Dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:管理员删除商品
 */

@WebServlet(name = "delProServlet",urlPatterns = {"/delProServlet"})
public class delProServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("id"));
        BookDao bookDao = new BookDao();
        bookDao.delProductByID(productID);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
