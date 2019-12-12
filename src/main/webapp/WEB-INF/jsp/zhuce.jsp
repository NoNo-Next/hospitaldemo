<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019.12.12
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>注册</title>
    <script src="/js/jquery/jquery-3.4.1.js"></script>
    <script src="/js/jquery/jquery.md5.js"></script>
</head>
<body>
<form action="" method="post">
    <input type="text" id="username" name="username">
    <input type="password" id="password" name="password">
    <input type="button" id="zhuce" value="提交"onclick="zhuce()">
</form>
<button id="zhuce1" onclick="zhuce()">提交</button>


    <button id="btn">按钮</button>

        <script>
        $("#btn").click(function(){
            alert("???")
        })
        </script>


</body>
</html>
