<%--
  Created by IntelliJ IDEA.
  User: 李耀鹏
  Date: 2019/4/25
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.CartItem" %>
<%@ page import="java.text.DecimalFormat" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <base href="<%=basePath%>">
    <title>购物车</title>
    <link href="Cart.css" rel="stylesheet">

</head>

<body>
<h1 class="titleInfo">我的购物车</h1>
<hr>
<script type="text/javascript">


    /*//禁用浏览器的返回键
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });*/

    /**
     *@Author:李耀鹏
     *@param: * @param null
     *@Description:清空该用户的购物车
     *@Date: 17:57 2019/4/27
     */
    function deleteAllItemByUserID(){
        //userID在servlet中获取
        var msg = "您确认清空购物车吗";
        if (confirm(msg)==true){
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/deleteCartServlet",
                /*dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",*/
                success: function (data) {
                    alert("购物车已清空");
                    window.location.href = "CartServlet";
                },//endsuccess
            });//endajax
        }
    }

    /**
     *@Author:李耀鹏
     *@param: * @cartItemID
     *@Description:从购物车中移除某一项商品
     *@Date: 17:57 2019/4/27
     */
    function deleteOneItemByID(cartItemID,ItemCount){
        var msg = "您确认从购物车移出该商品吗";
        if (confirm(msg)==true){
            //userID在servlet中获取
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/removeFromCartServlet",
                data: {"cartItemID":cartItemID},
               /* dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",*/
                success: function (data) {
                    window.location.href = "CartServlet"        //相当于刷新当前页面
                }//endsuccess
            });//endajax
        }//end if
    }

    /**
     *@Author:李耀鹏
     *@param: i，itemPrice
     *@Description:更新各控件的值i传过来的是cartItemID,用于对各控件的id进行标识区分
     * itemPrice是该cartItem的价格，用于更新价格显示
     *@Date: 17:54 2019/4/27
     */
    function addNum(cartItemID,itemPrice) {
        var updateNum = 1;
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/updateCartItemNumServlet",
            data: {"cartItemID":cartItemID,
                    "updateNum":updateNum
            },
           /* dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",*/
            success: function (data) {
                //$('#Item' + cartItemID).html(data);
                window.location.href = "CartServlet"        //相当于刷新当前页面
               /* //更新购物车中该商品数量
                document.getElementById("cartItemNum"+cartItemID).value = data.cartItemNum;
                //设置每一项商品的总金额
                document.getElementById("ItemAllPrice"+cartItemID).innerHTML =data.cartItemAllPrice;*/

            }//endsuccess
        });//endajax

        /*//当前购物车中该商品数量
        var currentNum =parseFloat(document.getElementById("cartItemNum"+i).value);
        //按下"+"后商品数量加1
        var afterAddNum = currentNum + 1.0;
        //更新购物车中该商品数量
        document.getElementById("cartItemNum"+i).value = afterAddNum.toString();
        //设置每一项商品的总金额
        document.getElementById("ItemAllPrice"+i).innerHTML =(afterAddNum * itemPrice).toFixed(2);
        //在每一项商品的总金额更新后取得当前所有商品的总价格
        var currentPrice1 = currentPrice();
        //设置购物车中所有商品的总金额
        document.getElementById("allItemPrice").innerHTML =currentPrice1.toFixed(2);*/
    }

    //
    function MinusNum(cartItemID,itemPrice) {

        //当前购物车中该商品数量
        var currentNum =parseFloat(document.getElementById("cartItemNum"+cartItemID).value);
        //按下"-"后商品数量减1
        var afterMinusNum = currentNum - 1.0;
        if(afterMinusNum < 1){      //商品数量至少为1（弹出提示框）
            alert("该商品已经减至最小数量")
        }
        else
        {
            var updateNum = -1;
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/updateCartItemNumServlet",
                data: {"cartItemID":cartItemID,
                    "updateNum":updateNum
                },
                success: function (data) {
                    window.location.href = "CartServlet"        //相当于刷新当前页面
                }//endsuccess
            });//endajax
        }

       /* //当前购物车中该商品数量
        var currentNum =parseFloat(document.getElementById("cartItemNum"+i).value);
        //按下"+"后商品数量减1
        var afterMinusNum = currentNum - 1.0;
        if(afterMinusNum < 1){      //商品数量至少为1（为0的情况等价于移出购物车或者是不选中）
            alert("该商品已经减至最小数量")
        }
        else
        {
            //更新购物车中该商品数量
            document.getElementById("cartItemNum"+i).value = afterMinusNum.toString();
            //设置每一项商品的总金额(单价*数目)
            document.getElementById("ItemAllPrice"+i).innerHTML =(afterMinusNum * itemPrice).toFixed(2);
            //在每一项商品的总金额更新后取得当前所有商品的总价格
            var currentPrice1 = currentPrice();
            //设置购物车中所有商品的总金额
            document.getElementById("allItemPrice").innerHTML =currentPrice1.toFixed(2);
        }*/
    }

    /**
     *@Author:李耀鹏
     *@param: * @param null
     *@Description:根据checkBox的变化更新商品的总金额
     *@Date: 20:20 2019/4/27
     */
    function checkboxOnclick(checkbox,cartItemID) {
       /* var currentPrice1 = currentPrice();
        document.getElementById("allItemPrice").innerHTML = currentPrice1.toFixed(2);*/
        var updateState = 0;
        if(checkbox.checked == true){
            updateState = 1;
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/checkBoxServlet",
            data: {"cartItemID":cartItemID,
                "updateState":updateState
            },
            success: function (data) {
                window.location.href = "CartServlet"        //相当于刷新当前页面
                //$('#allItemPrice').html(data);
            }//endsuccess
        });//endajax

    }

    /**
     *@Author:李耀鹏
     *@param: * @param null
     *@Description:根据checkBox的情况计算当前所选中的商品的总金额并返回
     *@Date: 20:18 2019/4/27
     */
    function  currentPrice() {
        //alert("currentPrice")
        var checkItems = document.getElementsByClassName("checkItem");
        var currentPrice = 0;
        if(checkItems.length > 0){
            for(var i = 0;i < checkItems.length;i++){
                if(checkItems[i].checked == true){
                    currentPrice += parseFloat(document.getElementById("ItemAllPrice"+checkItems[i].value).innerHTML);
                }
            }
        }
        return currentPrice;
    }

    function payCheckedItem() {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/OrderServlet",
            //data: {checkedItems : JSON.stringify(checkedItems)},
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                if(data.isgeOneCheckedItem === "0"){
                    alert("您还没选中任何商品呢，请选好商品后再前往支付")
                }
                else{
                    window.location.href = "Order.jsp"
                }
            }//endsuccess
        });//endajax
    }

    function goBack() {
        window.location.href = "bookServlet";
    }

</script>
<div class="part">

<center>
    <%
        float AllItemPrice = (Float)(session.getAttribute("AllItemPrice"));
        DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足
        String Price = decimalFormat.format(AllItemPrice);

        //System.out.println(AllItemPrice);
    %>
    <%--清空购物车--%>
    <div class="part1">
    <input class="button_first" type="button" value="清空购物车" onclick="deleteAllItemByUserID()">
    <input class="button_first" type="button" value="继续购物" onclick="goBack()">
    <br/>
    <br>
    总价:￥<span id="allItemPrice"><%=Price%></span>
    <button class="button_first" onclick="payCheckedItem()">去支付</button>
        <br><br>
    </div>
    <div class="part2">
    <table  align="center" width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
                <!-- 商品循环开始 -->
                <% ArrayList<CartItem> cartItemList = (ArrayList<CartItem>)session.getAttribute("cartItemList");
                    if(cartItemList == null || cartItemList.size() == 0){ %>
                        <br>
                <br>
                <br>
                        <div class="container">
							<div class="row">
							<font color="darkgray" size="5">
                                    购物车空空如也~~
                                <br/>去淘一些自己喜欢的商品吧</font>
							</div>
							</div>

                <%   cartItemList = new ArrayList<CartItem>();
                    //response.sendRedirect("CartServlet");
                    }
                    else
                    {
                        for(int i=0;i<cartItemList.size();i++)
                        {
                            CartItem cartItem = cartItemList.get(i);
                %>
                <div id = "Item<%=cartItem.getCartItemID()%>">
                    <dl >
                        <%--<dt>
                            <a href="details.jsp?id=<%=book.getId()%>"><img src="images/<%=item.getPicture()%>" width="120" height="90" border="1"/></a>
                        </dt>--%>
                        <dd class="dd_name">
                            商品: <span id="cartItemName<%=cartItem.getCartItemID()%>"><%=cartItem.getCartItemName()%></span>
                        </dd>
                        <dd class="dd_otherinfo">
                            单价:￥ <span id="ItemPrice<%=cartItem.getCartItemID()%>"><%=cartItem.getCartItemPrice()%></span>
                        </dd>
                        <dd class="dd_otherinfo">
                            <button class="button_second" onclick="MinusNum(<%=cartItem.getCartItemID()%>,<%=cartItem.getCartItemPrice()%>)">-</button>
                            <input type="text" id="cartItemNum<%=cartItem.getCartItemID()%>"
                                   style="width:50px; height:20px;" value="<%=cartItem.getCartItemNum()%>">
                            <button class="button_second" onclick="addNum(<%=cartItem.getCartItemID()%>,<%=cartItem.getCartItemPrice()%>)">+</button>
                        </dd>
                        <dd>
                            价格:￥<span id="ItemAllPrice<%=cartItem.getCartItemID()%>"><%=cartItem.getCartItemAllPrice()%></span>
                        </dd>
                        <dd>
                            <%
                                if(cartItem.getCartItemChecked() == true){
                            %>
                                <input type = "checkbox" class="checkItem" id = "checkItem<%=cartItem.getCartItemID()%>"
                                       checked
                                   onclick="checkboxOnclick(this,<%=cartItem.getCartItemID()%>)">
                            <%}else {
                            %>
                            <input type = "checkbox" class="checkItem" id = "checkItem<%=cartItem.getCartItemID()%>"
                                   onclick="checkboxOnclick(this,<%=cartItem.getCartItemID()%>)">
                            <%    }
                            %>
                        </dd>
                        <dd class="dd_otherinfo">
                            <button class="button_third"  onclick="deleteOneItemByID(<%=cartItem.getCartItemID()%>)">移出购物车</button>
                        </dd>
                    </dl>
                </div>
                <!-- 商品循环结束 -->
                <%
                        }
                    }
                %>
            </td>
        </tr>
    </table>
    </div>
</center>
    </div>

</body>
</html>

