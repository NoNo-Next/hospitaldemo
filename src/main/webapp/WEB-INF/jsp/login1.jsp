<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
        <script  src="/js/jquery/jquery-3.4.1.js" ></script>
        <script  src="/js/jquery/jquery.md5.js" ></script>
		<script type="text/javascript" src="/js/jquery/bootstrap.min.js" ></script>
		<link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css" />
        <link rel="stylesheet" href="/css/login/login.css">
	</head>
	<body  style=" background: url('${pageContext.request.contextPath}/img/login/loginback.jpg') no-repeat center center fixed; background-size: 100%;">
		<div class="modal-dialog" style="margin-top: 10%;">
        <div class="modal-content">
            <div class="modal-header">
 
                <h4 class="modal-title text-center" id="myModalLabel">晨轩医院管理系统登录</h4>
            </div>
            <div class="modal-body" id = "model-body">
                <div class="form-group">
 
                    <input type="text" id="username" name="username" class="form-control"placeholder="用户名" autocomplete="off">
                </div>
                <div class="form-group">
 
                    <input type="password" id="password" name="password" class="form-control" placeholder="密码" autocomplete="off">
                </div>
                <div>
                	<input type="text" id="verifyCodeId" name="verifyCode" required="required" class="codecss" placeholder="验证码" style="width: 350px;" /><img id="vimg" alt="" title="验证码" class="passcode" style="height:35px;cursor:pointer;" src="${pageContext.request.contextPath}/code/getCode?random=0.14335069776783593" width="100px" height="50px">

                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button type="button" id="loginBtn" class="btn btn-primary form-control" onclick="denglu()">登录</button>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-default form-control" onclick="zhuce()">注册</button>
                </div>
 
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

        <script>
            $(function () {
                //按回车键的时候，触发登录按钮的点击事件
                $(document).keydown(function(event){
                    event = event ? event : window.event;
                    if (event.keyCode === 13){
                        $("#loginBtn").trigger("click");
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
