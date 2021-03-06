<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
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
    <%-- <link rel="stylesheet" href="<%=basePath %>/newdascome/css/base.css" /> --%>
	<%-- <link rel="stylesheet" href="<%=basePath %>/newdascome/css/forum.css" /> --%>
	<%-- <link rel="stylesheet" href="<%=basePath %>/newdascome/css/public-style.css" /> --%>
    <title>添加用户 - H-ui.admin v3.1</title>
</head>
<body>
<article >
    <form  action="" modelAttribute="res" method="post" class="form form-horizontal" id="form-member-add" enctype="multipart/form-data">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>软件名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="username" name="title">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">封面图：</label>
            <!--<input type="file" name="file">-->
            <div class="formControls col-xs-8 col-sm-9">
                <span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="uploadfile" id="uploadfile" readonly nullmsg="请添加图片！" style="width:200px">
				<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览图片</a>
				<input type="file" multiple name="imgFile" class="input-file">
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">是否置顶：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="check-box">
                    <input type="checkbox" id="checkbox-2" name="topper" value="1" >
                    <label for="checkbox-1">&nbsp;</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">不启用：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="check-box">
                    <input type="checkbox" id="checkbox-2" name="prof" value="0" >
                    <label for="checkbox-1">&nbsp;</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">描述：</label> 
            <%-- <div class="formControls col-xs-8 col-sm-9">
                <textarea name="describe" cols="" rows="" class="textarea"  placeholder="说点什么...">${data.describe}</textarea>
                <!--<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>-->
            </div> --%>
           
			<!-- <textarea id="description" name="description" c > </textarea> -->
        </div>
         <textarea id="context" cols="20" rows="2" name="context" class="ckeditor"></textarea>
        


        
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="button" onclick="Submit()" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
<script src="<%=basePath %>/ckeditor_4.7.3_standard/ckeditor/ckeditor.js"></script>
<!--<script type="text/javascript" src="__PUBLIC__/js/started.js"></script>-->
<script type="text/javascript">





	function Submit(){
		if(CKEDITOR.instances.context.getData()==""){
			alert("内容不能为空！");
			return false;
			}else{
				$("#context").val(CKEDITOR.instances.context.getData());
				
			}
		var form = new FormData(document.getElementById("form-member-add"));
		console.log($('form').serialize());
		$.ajax({
	        type: "post",
	        
	        url: "addSolvesSubmit",
	    	data:form,
	    	contentType: false,
	    	processData: false,
	        dataType: "json",
	        success: function (date )
	        {
	        	console.log(date);
	           if(date.code=='1001'){
	        	   alert(date.msg);
	           }
	           if(date.code=='1000'){
					alert(date.msg);
					window.location.href="addSolves";
	           }
	        },
	        error:function (date) {      
	            alert("请求失败！");
	        }
	     });
	}
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
		//简单的表单验证
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