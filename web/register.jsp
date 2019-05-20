<%--
  Created by IntelliJ IDEA.
  User: 李耀鹏
  Date: 2019/4/30
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <link href="register.css" rel="stylesheet">
</head>
<body>
<script type="text/javascript">
    function check() {
        var name=$("#username").val();
        if(name==""||$.trim(name)==""){
            $("#nameErrorMsg").html("用户名不能为空");
            $("#nameErrorMsg").show();
            console.log(name)
        }
        else{
            $("#nameErrorMsg").hide();
        }
        if($("#password").val()==""||$.trim($("#password").val())==""){
            $("#passwordErrorMsg").html("密码不能为空");
            $("#passwordErrorMsg").show();
        }
        else if($("#password").val()!=$("#passwordConfirm").val()){
            $("#passwordErrorMsg").html("密码不一致");
            $("#passwordErrorMsg").show();
        }
        else{
            $("#passwordErrorMsg").hide();
        }
        if($("#email").val()==""||$.trim($("#email").val())==""){
            $("#emailErrorMsg").html("邮箱不能为空");
            $("#emailErrorMsg").show();
        }
        else{
            $("#emailErrorMsg").hide();
        }
        if($("#telephone").val()==""||$.trim($("#telephone").val())==""){
            $("#telephoneErrorMsg").html("联系电话不能为空");
            $("#telephoneErrorMsg").show();
        }
        else{
            $("#telephoneErrorMsg").hide();
        }
    }

    function countDown(secs){
        $("#sucAppointment").html("注册成功，"+secs+"秒后返回登录界面");
        $("#sucAppointment").show();
        //alert("注册成功，"+secs+"秒后返回登录界面")
        if(--secs>0){
            setTimeout("countDown("+secs+")",1000);
        }else{
            window.location.href="login.jsp";
        }
    }

    function ajaxAll() {
        var userName=$("#username").val();
        if(userName==""||$.trim(userName)==""||$("#password").val()!=$("#passwordConfirm").val()
            ||$("#password").val()==""||$.trim($("#password").val())==""
            ||$("#email").val()==""||$.trim($("#email").val())==""
            ||$("#telephone").val()==""||$.trim($("#telephone").val())=="")
            check();
        else{
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/registerServlet",
                data: $('#userinfo').serialize(),
                dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {
                    if(data.error ==="1"){
                        $("#nameErrorMsg").html("用户名已存在");
                        $("#nameErrorMsg").show();
                    }
                    else if(data.error ==="2"){
                        $("#nameErrorMsg").html("用户名只能包含字母或数字或'-'");
                        $("#nameErrorMsg").show();
                    }
                    else if(data.error ==="3"){
                        $("#passwordErrorMsg").html("密码只能包含字母或数字或'-'");
                        $("#passwordErrorMsg").show();
                    }
                    else if(data.error ==="4"){
                        $("#emailErrorMsg").html("邮箱格式不正确");
                        $("#emailErrorMsg").show();
                    }
                    else if(data.error ==="5"){
                        $("#telephoneErrorMsg").html("联系电话格式不正确");
                        $("#telephoneErrorMsg").show();
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
<div class="jumbotron">
    <h2 class="h4">注册你的账户</h2>
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <form class="needs-validation" id="userinfo"  novalidate>
                    <div class="mb-3">
                        <label for="username">用户名</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="username" name="username"
                                   onclick="check()" onblur="check()" placeholder="用户名(数字、字母、-)" required>
                            <div class="invalid-feedback" style="display:none" id="nameErrorMsg"></div>
                        </div>
                    </div>
                    <br>
                    <div class="mb-3">
                        <label for="password">密码</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="password" name="password"
                                   onclick="check()" onblur="check()" placeholder="密码(数字、字母、-)" required>

                        </div>
                    </div>
                    <br>
                    <div class="mb-3">
                        <label for="passwordConfirm">确认密码</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm"
                                   onclick="check()" onblur="check()" placeholder="确认密码" required>
                            <div class="invalid-feedback" style="display:none" id="passwordErrorMsg"></div>
                        </div>
                    </div>
                    <br>

                    <div class="mb-3">
                        <label for="email">邮箱</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="email" name="email"
                                   onclick="check()" onblur="check()" placeholder="email@mail.com" required>
                            <div class="invalid-feedback" style="display:none" id="emailErrorMsg"></div>
                        </div>
                    </div>
                    <br>
                    <div class="mb-3">
                        <label for="telephone">联系电话</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="telephone" name="telephone"
                                   onclick="check()" onblur="check()" placeholder="1xx xxxx xxxx" required>
                            <div class="invalid-feedback" style="display:none" id="telephoneErrorMsg"></div>
                        </div>
                    </div>
                    <br>
                    <hr class="mb-4">
                    <div class="button">
                        <input align="center" type="button" class="btn btn-primary btn-lg btn-block" id="signup" value="注册" onclick="ajaxAll()">
                    </div>
                    <div class="invalid-feedback" style="color:red" id="sucAppointment"></div>
                </form>

            </div>

        </div>
    </div><!--end container-->
</div><!--end jumbotron-->
</body>
</html>



