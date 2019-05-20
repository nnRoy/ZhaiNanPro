<%@ page import="java.util.List" %>
<%@ page import="Bean.CartItem" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: 李耀鹏
  Date: 2019/4/28
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单支付</title>

</head>
<body>
<h1>生成订单</h1>



<script type="text/javascript" >


  /*  //禁用浏览器的返回键
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });*/

    function toBeContinue() {
        alert("后序转账业务请联系睿智的翟种");
    }

    function goBack() {
        window.location.href = "bookServlet";
    }
</script>
<center>
    <table>
        <tr>
            <td>收货人信息 </td>
            <td>
                <textarea cols="80" rows="5" name="receiverinfo"></textarea>
            </td>
        </tr>
        <tr>
            <td>付款方式 </td>
            <td style="color: red">
                <input type="radio" name="paymethod" value="alipay" checked>支付包
                <input type="radio" name="paymethod" value="weChat">微信支付
                <input type="radio" name="paymethod" value="yinhangka">银行卡
            </td>
        </tr>
    </table>
    <h3>购买商品详情</h3>
    <table border="1" width="70%">

        <tr>
            <td>商品名称</td>
            <td>商品单价</td>
            <td>购买数量</td>
            <td>商品总价</td>
        </tr>
        <!--  Ma<> -->
        <%float totalPrice = 0.0f;%>
        <% List<CartItem> orderItemList = (List<CartItem>)session.getAttribute("orderItemList");
            if(orderItemList == null || orderItemList.size() == 0){
                System.out.println("写代码要自信之我不可能被执行！！！" );
            }
            else
            {
                for(int i=0;i<orderItemList.size();i++)
                {
                    CartItem orderItem = orderItemList.get(i);
                    totalPrice += Float.parseFloat(orderItem.getCartItemAllPrice());
        %>
            <tr>
                <td><%=orderItem.getCartItemName()%></td>
                <td>￥<%=orderItem.getCartItemPrice()%></td>
                <td><%=orderItem.getCartItemNum()%></td>
                <td>￥<%=orderItem.getCartItemAllPrice()%></td>
               <%-- <td>
                    <c:if test="${entry.key.pnum>=entry.value }">
                        <font color="red">现货充足</font>
                    </c:if>
                    <c:if test="${entry.key.pnum<entry.value }">
                        <font color="gray">库存紧张</font>
                    </c:if>
                </td>
                <c:set var="totalprice" value="${totalprice+ entry.key.price*entry.value }"></c:set>--%>
            </tr>
    <%
            }
        }
    %>
    <%
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        String Price = decimalFormat.format(totalPrice);
    %>
        <tr>
            <td colspan="4" align="right" style="color: red">
                商品总价:￥ <%=Price%>
                <input type="hidden" name="money" value="<%=Price%>">
            </td>
        </tr>
    </table>
    <input type="button" value="确认" onclick="toBeContinue()">
    <br>
    <input type="button" value="继续购物" onclick="goBack()">
</center>
</body>
</html>
