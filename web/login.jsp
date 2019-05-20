<%--
  Created by IntelliJ IDEA.
  User: 李耀鹏
  Date: 2019/4/29
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="login.css" rel="stylesheet">


</head>
<body class="text-center">
<form class="form-login" action="${pageContext.request.contextPath}/LoginServlet" method="post">
    <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
    <input type="text" name="username" class="form-control"id="inputName" placeholder="用户名" required autofocus></input>
    <br>
    <input type="password" name="password"id="inputPassword" class="form-control" placeholder="密码" required></input>
    <br>
    <label for="selectUserType" class="sr-only">登入类型</label>
    <select class="form-control" id="selectUserType" name="UserType">
        <option value="1">用户</option>
        <option value="2">管理员</option>
    </select>
    <br><br>
    <div class="checkbox mb-3">
        <a href="register.jsp" class="btn btn-secondary my-2">注册</a>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </div>
</form>
</body>
</html>
