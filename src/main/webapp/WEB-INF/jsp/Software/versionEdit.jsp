<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>添加用户 - H-ui.admin v3.1</title>
</head>
<body>
<article class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-member-add" enctype="multipart/form-data">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>版本名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
            	<input type="hidden" value="${soft.id }" name="id">
            	<input type="hidden" value="${soft.rid }" name="rid">
                <input type="text" class="input-text" value="${soft.version }" placeholder="" id="username" name="version">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>版本号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${soft.versionNum }" placeholder="" name="versionNum">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">文件：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div id="uploader" class="wu-example">
                    <!--用来存放文件信息-->
                    <div id="thelist" class="uploader-list"></div>
                    <div class="btns">
                        <div id="picker">选择文件</div>
                        <button id="ctlBtn" type="button" class="btn btn-default">开始上传</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="url" id="FileUrl" value="${soft.url }">
            <input type="hidden" name="size" id="size"" value="${soft.size }">
            <input type="hidden" name="suffix" id="suffix" value="${soft.suffix }" >
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">支持系统：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" name="system">
                    <option value="">请选择 </option>
                    <c:if test="${soft.system=='Windows'}">
                    <option value="Windows"  selected >Windows</option>
                    </c:if>
                    <c:if test="${soft.system!='Windows'}">
                    <option value="Windows"   >Windows</option>
                    </c:if>
                    <c:if test="${soft.system=='Android'}">
                    <option value="Android"  selected >Android</option>
                    </c:if>
                    <c:if test="${soft.system!='Android'}">
                    <option value="Android"   >Android</option>
                    </c:if>
                    <c:if test="${soft.system=='iPhone'}">
                    <option value="iPhone"  selected >iPhone</option>
                    </c:if>
                    <c:if test="${soft.system!='iPhone'}">
                    <option value="iPhone"   >iPhone</option>
                    </c:if>
                    <!-- <option value="Windows"  >Windows</option>
                    <option value="Android" >Android</option>
                    <option value="iPhone" >iPhone</option> -->
                </select>
				</span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="describe" cols="" rows="" class="textarea"  placeholder="说点什么...">${soft.describe}</textarea>
                <!--<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>-->
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">设为最新版：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="check-box">
                	<c:if test="${soft.isNew==1 }">
                	<input type="checkbox" id="checkbox-2" name="isNew" checked value="1" >
                	</c:if>
                	<c:if test="${soft.isNew==0 }">
                	 <input type="checkbox" id="checkbox-2" name="isNew"  value="1" >
                	</c:if>
                    <!-- <input type="checkbox" id="checkbox-2" name="isNew" value="1" > -->
                    <label for="checkbox-1">&nbsp;</label>
                </div>
            </div>
        </div>
        <!-- <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">添加到实用工具：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="check-box">
                    <input type="checkbox" id="checkbox-1" name="tool" value="1" >
                    <label for="checkbox-1">&nbsp;</label>
                </div>
            </div>
        </div> -->
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="button" onclick="editVersionSubmit()" value="&nbsp;&nbsp;修改&nbsp;&nbsp;">
            </div>
        </div>
    </form>
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
	//提交
	function editVersionSubmit(){
		var form = new FormData(document.getElementById("form-member-add"));
		console.log($('form').serialize());
		$.ajax({
	        type: "post",
	        url: "editVersionSubmit",
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
					window.location.href="editVersion?id="+'${soft.id}';
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
		//前端数据校验
        $("#form-member-add").validate({
            rules:{
                name:{
                    required:true,
                    minlength:2,
                    maxlength:16
                },
                name_num:{
                    required:true,
                    minlength:2,
                    maxlength:16
                },
                system:{
                    required:true
                } 
            }
        });
    });
    //文件上传
    jQuery(function() {
        var $ = jQuery,
                $list = $('#thelist'),
                $btn = $('#ctlBtn'),
                state = 'pending',
                uploader;

        uploader = WebUploader.create({

            // 不压缩image
            resize: false,

            // swf文件路径
            swf: '<%=basePath %>/lib/webuploader/0.1.5/Uploader.swf',

            // 文件接收服务端。
            server: 'uploadSoftware',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',
            fileNumLimit: 1, //限制上传个数
        });

        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
            $list.append( '<div id="' + file.id + '" class="item">' +
                    '<h4 class="info">' + file.name + '</h4>' +
                    '<p class="state">等待上传...</p>' +
                    '</div>' );
        });
		
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });
		//上传成功的方法
        uploader.on( 'uploadSuccess', function( file,data ) {
            if (data.code=="1000") {
                $('#size').val(data.date.size);
                $('#suffix').val(data.date.suffix);
                    $('#FileUrl').val(data.date.path);
                $( '#'+file.id ).find('p.state').text('已上传');
            }else{
                $( '#'+file.id ).find('p.state').text(date.msg);
            }
            console.log(data.date.path);
        });
		//上传出错的方法
        uploader.on( 'uploadError', function( file ) {
        	$( '#'+file.id ).find('p.state').text("上传出错");
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        uploader.on( 'all', function( type ) {
            if ( type === 'startUpload' ) {
                state = 'uploading';
            } else if ( type === 'stopUpload' ) {
                state = 'paused';
            } else if ( type === 'uploadFinished' ) {
                state = 'done';
            }

            if ( state === 'uploading' ) {
                $btn.text('暂停上传');
            } else if(state='done'){
                $btn.text('开始上传');
            }else{
            	
            }
        });

        $btn.on( 'click', function() {
            if ( state === 'uploading' ) {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>