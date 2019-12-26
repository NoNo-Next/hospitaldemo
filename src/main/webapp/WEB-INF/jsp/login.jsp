<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019.12.11
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登陆</title>
    <script  src="/js/jquery/jquery-3.4.1.js" ></script>
    <script  src="/js/jquery/jquery.md5.js" ></script>
</head>
<body>
<form action="" method="post">
    <input type="text" id="username" name="username">
    <input type="password" id="password" name="password"><br />
    <input type="text" id="verifyCodeId" name="verifyCode" required="required" placeholder="请输入验证码" >
    <img id="vimg" alt="" title="验证码" class="passcode" style="height:25px;cursor:pointer;" src="${pageContext.request.contextPath}/code/getCode?random=0.14335069776783593" width="45px" height="25px">
    <br>
    <input type="button" id="denglubtn" value="登陆" onclick="denglu()" >
    <input type="button" value="注册" onclick="zhuce()">
    <input type="button" id="load" value="测试下载按钮" >
</form>

<script>
    $(function () {
        //按回车键的时候，触发登录按钮的点击事件
        $(document).keydown(function(event){
            event = event ? event : window.event;
            if (event.keyCode === 13){
                $("#denglubtn").trigger("click");
            }
        })
        $("#vimg").click(function () {
            getNewCode();
        })
    
        $("#load").click(function () {
            alert("???")
            window.location.href="/export", rel="external nofollow" ;
            <!--这里不能用ajax请求，ajax请求无法弹出下载保存对话框-->
        })

    })
    function zhuce(){
        window.location.href = "/zhuce"
    }
    function denglu(){
            var username = $("#username").val()
            var pwd = $("#password").val()
            var code = $("#verifyCodeId").val()
            if ( !code){
                alert("验证码不能为空！！！")
                $("#verifyCodeId").focus()
            }else if(!username){
                alert("用户名不能为空！！！")
                $("#username").focus()
            }else if(!pwd){
                alert("密码不能为空!!!")
                $("#pwd").focus()
            }else {
                $.ajax({
                    type: 'post',
                    data: {
                        username:username,
                        password:$.md5(pwd),
                        verifyCodeId : $("#verifyCodeId").val().trim()
                    },
                    url: "${pageContext.request.contextPath}/login",
                    success: function (data) {
                        var result = data.res;
                        if ("登录成功" == result){
                            window.location.href = "${pageContext.request.contextPath}/index";
                        } else {
                            alert(result)
                            getNewCode()
                        }
                    }
                })
            }

    }
    function getNewCode() {
        $("#vimg").attr("src", "${pageContext.request.contextPath}/code/getCode?random=" + Math.random());
    }
</script>

</body>
</html>
