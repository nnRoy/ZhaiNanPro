package servlet;

import Bean.Book;
import Bean.User;
import Dao.BookDao;
import Dao.UserDao;

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
  *@Description:书籍商品的主界面
 */
@WebServlet(name = "bookServlet",urlPatterns= {"/bookServlet"})
public class bookServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDao bookDao = new BookDao();
        ArrayList<Book> bookList = bookDao.getAllBookItems();
        HttpSession session = request.getSession();

        int userID= 0;      //初始化为0表示未登入的游客状态

        if(session.getAttribute("UserID") != null){
            userID = (int)(session.getAttribute("UserID"));
        }
        UserDao userDao = new UserDao();
        User user = userDao.getUserByID(userID);
        session.setAttribute("bookList", bookList);
        session.setAttribute("User",user);
        request.getRequestDispatcher("Book.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
