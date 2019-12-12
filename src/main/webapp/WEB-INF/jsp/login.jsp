<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019.12.11
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <script  src="/js/jquery/jquery-3.4.1.js" ></script>
</head>
<body>
<form action="/login" method="post">
    <input type="text" id="username" name="username">
    <input type="password" id="password" name="password">
    <input type="submit" value="登陆" >
    <input type="button" value="注册" onclick="zhuce()">
</form>
<button id="zhuce" onclick="zhuce()" value="注1">注册1</button>
<button id="btn">按钮</button>
<script>
    $("#btn").click(function(){
        alert("???")
    })
</script>

</body>
</html>
