<%--
  Created by IntelliJ IDEA.
  User: 李耀鹏
  Date: 2019/4/24
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Bean.Book"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   // System.out.println(path);
   // System.out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

    <base href="<%=basePath%>">

    <title>书籍商品页</title>
    <link href="Book.css" rel="stylesheet">
</head>

<body>
<script type="text/javascript">

   /* //禁用浏览器的返回键
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });*/
   ///普通用户绑定的按钮事件
    function addToCart(id) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/addToCartServlet",
            data: {"id":id},
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                if(data.isAddToCart==="0"){
                    alert("该商品已在购物车")
                }//endif
                else{
                    alert("加入购物车成功");
                }
            }//endsuccess
        });//endajax
    }

    ///普通用户绑定的按钮事件
    function showCart() {
        window.location.href = "CartServlet";
    }

    //游客界面绑定的按钮事件
    function goToLogin() {
       window.location.href = "login.jsp";
   }


   //管理员界面绑定的按钮事件
   function addProduct () {
       window.location.href = "addProduct.jsp"
   }

   function deleteProduct(id) {
       var msg = "您确认下架该商品吗";
       if (confirm(msg)==true){
           $.ajax({
               type: "POST",
               url: "${pageContext.request.contextPath}/delProServlet",
               data: {"id":id},
              /* dataType: "json",
               contentType: "application/x-www-form-urlencoded; charset=utf-8",*/
               success: function (data) {
                  window.location.href = "bookServlet";     //刷新当前页面
               }//endsuccess
           });//endajax
       }
   }

   function modifyProduct(id) {
       var msg = "您确认修改该商品的信息吗";
       if (confirm(msg)==true){
           window.location.href ="modifyPro.jsp?id="+id;
       }
   }
   function changeUser() {
       var msg = "您确认更换用户吗";
       if (confirm(msg)==true){
           window.location.href ="login.jsp";
       }
   }

</script>

<%
    User user = (User)session.getAttribute("User");
    String title;
    int UserType;   //获取用户类型1表示用户，2表示管理员，0表示游客
    if(user.getUserName().equals("")){

        title = "亲爱的游客，欢迎光临";
        UserType = 0;
    }
    else {
        title = "亲爱的 "+user.getUserName()+ ",欢迎光临";
        UserType = user.getUserType();
    }
%>
<h1 class="titleInfo">商品展示</h1>
<br>
<h2 class="user"><%=title%></h2>
<hr>
<br>
<br>
<br>
<center>
    <%if (UserType == 1){%>
    <button class="button-first" onclick="showCart()">我的购物车</button>
    <button class="button-first" onclick="changeUser()">切换用户</button>
    <%
    }
    else if(UserType == 2){ %>
    <button  class="button-first" onclick="addProduct()">添加商品</button>
    <button class="button-first" onclick="changeUser()">切换用户</button>
    <%
        }
    //UserType == 0
    else{%>
    <button class="button-first"  onclick="goToLogin()">去登入</button>
    <%
        }
    %>

    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
                <!-- 商品循环开始 -->
                <% ArrayList<Book> bookList = (ArrayList<Book>)session.getAttribute("bookList");
                    if(bookList == null || bookList.size() == 0){%>
                <br><br><br>
                        <div class="container">
							<div class="row">
							<font color="darkgray" size="5">
                                商城空空如也~~
                                <br/>等待管理员上架商品</font>
							</div>
							</div>
                    <%
                        bookList = new ArrayList<Book>();
                        response.sendRedirect("bookServlet");
                    }
                    else
                    {
                        for(int i=0;i<bookList.size();i++)
                        {
                            Book book = bookList.get(i);
                %>
                <div>
                    <dl>
                        <%--<dt>
                            <a href="details.jsp?id=<%=book.getId()%>"><img src="images/<%=item.getPicture()%>" width="120" height="90" border="1"/></a>
                        </dt>--%>
                        <%if(UserType == 1){%>
                            <dd class="dd_name"><a href="bookInfoServlet?id=<%=book.getBookID()%>"> <%=book.getBookName()%></a></dd>
                                <%
                            }
                        else{%>
                            <dd class="dd_name"><%=book.getBookName()%></dd>
                        <%
                            }
                        %>
                        <dd class="dd_otherinfo">作者:<%=book.getBookAuthor()%></dd>
                        <dd class="dd_otherinfo">价格:￥ <%=book.getBookPrice()%></dd>
                        <%if (UserType == 1){%>
                            <dd><button class="button-second" id ="addToCart" onclick="addToCart(<%=book.getBookID()%>)">加入购物车</button></dd>
                        <%
                            }
                        else if(UserType == 2){%>
                            <dd>
                                <button class="button-second"  onclick="modifyProduct(<%=book.getBookID()%>)">修改商品</button>
                                <button class="button-second" onclick="deleteProduct(<%=book.getBookID()%>)">删除商品</button>
                            </dd>
                        <%
                            }
                        //UserType == 0
                        else{%>
                        <%
                            }
                        %>

                    </dl>
                </div>
                <br><br>
                <!-- 商品循环结束 -->
                <%
                        }
                    }
                %>
            </td>
        </tr>
    </table>
</center>
</body>
</html>
