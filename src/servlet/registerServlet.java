package servlet;

import Bean.User;
import Dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@Author:李耀鹏
 *@param: * @param null
 *@Description:用户注册信息验证
 */

@WebServlet(name = "registerServlet",urlPatterns = {"/registerServlet"})
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String UserName=request.getParameter("username");
        String PassWord=request.getParameter("password");
        String Email = request.getParameter("email");
        String Telephone = request.getParameter("telephone");

        JSONObject jsonObject = new JSONObject();
        PrintWriter out = response.getWriter();

        //匹配用户名密码
        String check = "^[A-Za-z0-9\\-]+$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(UserName);
        Boolean userNameFlag = matcher.matches();
        Matcher matcher0 = regex.matcher(PassWord);
        Boolean PassWordFlag =  matcher0.matches();

        //匹配邮箱
        String check1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex1 = Pattern.compile(check1);
        Matcher matcher1 = regex1.matcher(Email);
        Boolean emailFlag = matcher1.matches();

        //匹配电话号
        String check2 = "^1[34578]\\d{9}$";
        Pattern regex2 = Pattern.compile(check2);
        Matcher matcher2 = regex2.matcher(Telephone);
        boolean telePhoneFlag = matcher2.matches();


        UserDao userDao = new UserDao();
        //getUserByName返回的不为null即userTemp存在
        User userTemp = userDao.getUserByName(UserName);
        //该用户名存在设置error为"1"
        if(userTemp.getUserName() != ""){
            jsonObject.put("error", "1");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();

            return;
        }
        //用户名只能是数字、字母、"-"否则设置error为"2"
        else if(userNameFlag == false){
            jsonObject.put("error", "2");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
            return;
        }

        //密码只能是数字、字母、"-"否则设置error为"3"
        else if(PassWordFlag == false){
            jsonObject.put("error", "3");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
            return;
        }


        //若邮箱格式不对则设置error为"4"
        else if(emailFlag == false){
            jsonObject.put("error", "4");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
            return;
        }

        //若电话格式不对则设置error为"5"
        else if(telePhoneFlag == false){
            jsonObject.put("error", "5");
            response.setContentType("application/json");
            out.print(jsonObject);
            out.flush();
            out.close();
            return;
        }
        //所有信息的格式均正确且用户名不重复
        //注册成功设置erroe为"0"
        else{
            User user = new User(UserName,PassWord,1,Email,Telephone);//UserType为1表示注册为用户
            userDao.addUser(user);
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
