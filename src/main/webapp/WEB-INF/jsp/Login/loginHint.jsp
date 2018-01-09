<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			* {
				padding: 0;
				margin: 0;
			}
			.system-message {
    			padding: 24px 48px;
			}
			.system-message h1 {
				font-size: 100px;
				font-weight: normal;
				line-height: 120px;
				margin-bottom: 12px;
			}
			.system-message .error {
			    line-height: 1.8em;
			    font-size: 36px;
			}
			.system-message h1 {
				display: block;
				font-size: 6em;
			}
			.system-message .jump {
   				padding-top: 10px;
			}	
		</style>
	</head>

	<body>
		<div class="system-message">
			<h1>:(</h1>
			<c:if test="${hint!=null }">
			<p class="error">${hint }</p>
			</c:if>
			<c:if test="${hint==null }">
			<p class="error">请登录</p>
			</c:if>
			
			<p class="detail"></p>
			<p class="jump">
				页面自动
				<a id="href" href="">跳转</a> 等待时间： <b id="wait">3</b>
			</p>
		</div>

		<script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript">
		//银行认证成功跳转
		var time=setInterval (showTime, 1000);
		var second=2;
		if(window.parent.document.referrer==""){
			$("#href").attr("href","login"); 
		}else{
			$("#href").attr("href","login"); 
		}
		
		function showTime(){ 
			if(second==0){
				window.location="login";
				clearInterval(time);
			}
		$("#wait").html(second);
		second--;
		}
		</script>
	</body>
</html>