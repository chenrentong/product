<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="<%=basePath%>/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="__H-UI__/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>后台登录 - 产品管理系统</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header" style="background-image:none!important;letter-spacing: 4px;font-size: 35px;color: #eff0ef"><span style="margin-left: 150px">得实产品后台管理系统</span></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" action="loginSubmit" method="post">
        <p style="color: red;">${LOGIN_ERROR_MESSAGE}</p>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input name="username" type="text" placeholder="账户" class="input-text size-L" value="${username }">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input name="password" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input class="input-text size-L" name="validateCode" type="text" placeholder="验证码" style="width:150px;">
                    <img id="validateCode" onclick="changeImg();" src="validateCode" height="41px">&nbsp;&nbsp;&nbsp;&nbsp;<a id="kanbuq"  onclick="changeImg();" href="javascript:;">换一张</a> </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <!--<label for="online">
                        <input type="checkbox" name="online" id="online" value="">
                        使我保持登录状态</label>-->
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="button" onclick="loginSubmit()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">版权所有 广州得实</div>
<script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/h-ui/js/H-ui.min.js"></script>
<script>
	$(function (){
		$(function(){
			document.onkeydown = function(e){ 
			    var ev = document.all ? window.event : e;
			    if(ev.keyCode==13) {
			    	loginSubmit();

			     }
			}
			});  
	});
    //更换验证码
    function changeImg(){
            $("#validateCode").attr("src","<%=basePath%>/validateCode?data=" + new Date() + Math.floor(Math.random()*24));
        }
    function loginSubmit(){
    	$.ajax({
			type: "post",
			async: true,
			url: "loginSubmit",
			data:  $("form").serialize(),
			dataType: "json",
			success: function(res) {
				console.log(res);
				if(res.code=="1000"){
					window.location.href = "<%=basePath%>/index";
				}
				if(res.code!="1000"){
					alert(res.msg);
					<%-- window.location.href = "<%=basePath%>/loginHint?hint="+res.msg; --%>
				}
			},
			error: function() {
				console.log("发生错误！");
			}
		});
    }
    
    
</script>
</body>
</html>