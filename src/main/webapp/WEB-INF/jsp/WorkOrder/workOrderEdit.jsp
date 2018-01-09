<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="__H-UI__/lib/html5shiv.js"></script>
    <script type="text/javascript" src="__H-UI__/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="__H-UI__/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->
    <link href="<%=basePath %>/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
    <title>修改资源</title>
</head>
<body>
<article class="page-container">
    <form  action="" modelAttribute="res" method="post" class="form form-horizontal" id="form-member-add" enctype="multipart/form-data">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>电子邮箱：</label>
            <div class="formControls col-xs-8 col-sm-9">
            	<input type="hidden" value="${order.id }" name="id" id="oid">
                <input type="text" class="input-text" disabled="true" value="${order.eMail }" style="width: 250px"  placeholder="" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>联系人名字：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" disabled="true"  value="${order.name }" style="width: 250px" placeholder="" id="username" name="title">
            </div>
        </div>
       
       	<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>手机号码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" disabled="true"  value="${order.phone }" style="width: 250px"  placeholder="" id="username" name="title">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>问题主题：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" disabled="true"  value="${order.theme }" style="width: 250px"  placeholder="" id="username" name="title">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>提交日期：</label>
            <div class="formControls col-xs-8 col-sm-9">
            	<fmt:formatDate value="${order.startTime  }" pattern="yyyy-MM-dd"/>
            </div>
        </div>
            <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">附件：</label>
            <div class="formControls col-xs-8 col-sm-9">
            <a href="downloadOrderAttachment?id=${order.id }">下载</a>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="describe" cols="" rows="" disabled="true"  class="textarea"  placeholder="说点什么...">${order.content}</textarea>
                <!--<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>-->
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="solveProject" id="solveProject" cols="" rows="" class="textarea"  placeholder="说点什么...">${order.solveProject}</textarea>
                <!--<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>-->
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="button" onclick="soleAddSubmit(1)" value="&nbsp;&nbsp;标记为已解决&nbsp;&nbsp;">
            </div>
            <br/>
            <br/>
            
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="button" onclick="soleAddSubmit(0)" value="&nbsp;&nbsp;标记为未解决&nbsp;&nbsp;">
            </div>
        </div>
    </form >
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript" src="<%=basePath %>/lib/jquery.validation/1.14.0/jquery.validate.js"></script>

<script type="text/javascript" src="<%=basePath %>/lib/webuploader/0.1.5/webuploader.min.js"></script>
<!--<script type="text/javascript" src="__PUBLIC__/js/started.js"></script>-->
<script type="text/javascript">
	var id='${order.id}';
	function soleAddSubmit(isSolve){
		var content=$("#solveProject").val();
		$.ajax({
	        type: "post",
	        url: "workOrderEditSubmit",
	    	data:{"id":id,"isSolve":isSolve,"solveProject":content},
	        dataType: "json",
	        success: function (date )
	        {
	        	console.log(date);
	           if(date.code=='1001'){
	        	   alert(date.msg);
	           }
	           if(date.code=='1000'){
					alert(date.msg);
					window.location.href="workOrderEdit?id="+id;
	           }
	        },
	        error:function (date) {     
	        	console.log("请求失败!");
	        }
	     });
	}
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-member-add").validate({
            rules:{
                name:{
                    required:true,
                    minlength:2,
                    maxlength:16
                },
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>