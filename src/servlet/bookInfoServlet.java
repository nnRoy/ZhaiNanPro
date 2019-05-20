package servlet;

import Bean.Book;
import Dao.BookDao;

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
 *@Description:书籍商品的具体信息
 */

@WebServlet(name = "bookInfoServlet",urlPatterns= {"/bookInfoServlet"})
public class bookInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int bookID = Integer.parseInt(request.getParameter("id"));
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBookById(bookID);
        if(book ==null){
            String str="很抱歉，未找到"+book.getBookName()+"这本书";
            session.setAttribute("notfound", str);
        }
        else{
            session.setAttribute("notfound", "1");
            session.setAttribute("book",book);
        }
        request.getRequestDispatcher("BookInfo.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
