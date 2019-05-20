<%--
  Created by IntelliJ IDEA.
  User: 李耀鹏
  Date: 2019/4/25
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="Dao.BookDao" %>
<%@ page import="Bean.Book" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <base href="<%=basePath%>">
    <link href="BookInfo.css" rel="stylesheet">
    <title>商品详情页</title>

</head>

<body>
<div class="lay">
<h1 class="titleInfo">商品详情</h1>
<hr>

    <br><br><br>
<script type="text/javascript">


   /* //禁用浏览器的返回键
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });*/

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
function goBack() {
       window.location.href = "bookServlet";
   }
</script>

<center>
    <table align="center" width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <!-- 商品详情 -->
            <%
                Book book = new Book();
                String notfound = (String) session.getAttribute("notfound");
                if(notfound == "1"){
                    book = (Book)session.getAttribute("book");
                }
            %>
            <td width="70%" valign="top">
                <table>
                    <%--   <tr>
                        <td rowspan="4"><img src="images/<%=item.getPicture()%>" width="200" height="160"/></td>
                    </tr>--%>
                    <tr>
                        <td class="booktitle"><B><%=book.getBookName() %></B></td>
                    </tr>
                    <tr>
                        <td>作者：<%=book.getBookAuthor()%></td>
                    </tr>
                    <tr>
                        <td>价格：￥<%=book.getBookPrice() %></td>
                    </tr>
                    <tr>
                        <td>描述：<%=book.getBookDescription() %></td>
                    </tr>
                    <tr>
                        <td>
                            <button class="button-first" id ="addToCart" onclick="addToCart(<%=book.getBookID()%>)">加入购物车</button>
                        </td>
                    </tr>

                </table>
            </td>
        </tr>
    </table>
    <br><Br><Br>
    <input class="button-second" type="button" value="继续购物" onclick="goBack()">
</center>
</div>
</body>
</html>

