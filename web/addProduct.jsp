<%--
  Created by IntelliJ IDEA.
  User: 李耀鹏
  Date: 2019/5/1
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <link href="addProduct.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<script type="text/javascript">
    function check() {
        var name=$("#productName").val();
        if(name==""||$.trim(name)==""){
            $("#nameErrorMsg").html("商品名不能为空");
            $("#nameErrorMsg").show();
            console.log(name)
        }
        else{
            $("#nameErrorMsg").hide();
        }
        if($("#productPrice").val()==""||$.trim($("#productPrice").val())==""){
            $("#priceErrorMsg").html("商品售价不能为空");
            $("#priceErrorMsg").show();
        }
        else{
            $("#priceErrorMsg").hide();
        }
        if($("#productAuthor").val()==""||$.trim($("#productAuthor").val())==""){
            $("#authorErrorMsg").html("商品作者不能为空");
            $("#authorErrorMsg").show();
        }
        else{
            $("#authorErrorMsg").hide();
        }
        if($("#productDes").val()==""||$.trim($("#productDes").val())==""){
            $("#desErrorMsg").html("商品描述信息不能为空");
            $("#desErrorMsg").show();
        }
        else{
            $("#desErrorMsg").hide();
        }
    }

    function countDown(secs){
        $("#sucAppointment").html("添加商品成功，"+secs+"秒后返回主界面");
        $("#sucAppointment").show();
        //alert("注册成功，"+secs+"秒后返回登录界面")
        if(--secs>0){
            setTimeout("countDown("+secs+")",1000);
        }else{
            window.location.href="bookServlet";
        }
    }

    function ajaxAll() {
        var productName=$("#productName").val();
        if(productName==""||$.trim(productName)==""
            ||$("#productPrice").val()==""||$.trim($("#productPrice").val())==""
            ||$("#productAuthor").val()==""||$.trim($("#productAuthor").val())==""
            ||$("#productDes").val()==""||$.trim($("#productDes").val())=="")
            check();
        else{
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/addProServlet",
                data: $('#productInfo').serialize(),
                dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {
                    if(data.error ==="1"){
                        $("#priceErrorMsg").html("商品价格数据格式不正确");
                        $("#priceErrorMsg").show();
                    }
                    //注册成功2秒后跳转返回登录界面
                    else{
                        countDown(2);
                    }
                }//endsuccess
            });//endajax
        }//endelse
    }//endfunction
</script>


<form class="needs-validation" id="productInfo"  novalidate>
    <h1 class="titleInfo">添加新的商品</h1>
    <br><br>
    <div class="mb-3">
        <label for="productName">商品名</label>
        <div class="input-group">
            <input type="text" class="form-control" id="productName" name="productName"
                   onclick="check()" onblur="check()" placeholder="商品名" required>
            <div class="invalid-feedback" style="display:none" id="nameErrorMsg"></div>
        </div>
    </div>
    <br>
    <div class="mb-3">
        <label for="productPrice">商品价格</label>
        <div class="input-group">
            <input type="text" class="form-control" id="productPrice" name="productPrice"
                   onclick="check()" onblur="check()" placeholder="售价" required>
            <div class="invalid-feedback" style="display:none" id="priceErrorMsg"></div>
        </div>
    </div>
    <br>
    <div class="mb-3">
        <label for="productAuthor">作者</label>
        <div class="input-group">
            <input type="text" class="form-control" id="productAuthor" name="productAuthor"
                   onclick="check()" onblur="check()" placeholder="商品作者" required>
            <div class="invalid-feedback" style="display:none" id="authorErrorMsg"></div>
        </div>
    </div>
    <br>
    <div class="mb-3">
        <label for="productDes">商品描述</label>
        <div class="input-group">
            <input type="text" class="form-control" id="productDes" name="productDes"
                   onclick="check()" onblur="check()" placeholder="商品描述信息" required>
            <div class="invalid-feedback" style="display:none" id="desErrorMsg"></div>
        </div>
    </div>

    <br>
    <hr class="mb-4">
    <input type="button" class="btn btn-primary btn-lg btn-block" id="signup" value="添加商品" onclick="ajaxAll()">
    <div class="invalid-feedback" style="color:red" id="sucAppointment"></div>

</form>
</body>
</html>
