<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script type="text/javascript" src="../../../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../../../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>./static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/js/asset/bootstrap/dist/css/bootstrap.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../../../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <form action="findUserList" method="post">
            <input type="text" class="input-text" style="width:250px" placeholder="输入管理员名称" id="" value="${like }" name="like">
            <button type="submit" class="btn btn-success" id="like" name=""><i class="Hui-iconfont">&#xe665;</i> 搜管理员</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="admin_add('添加管理员','addUser','','600')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a></span> <span class="r">共有数据：<strong>${userList.total}</strong> 条</span> </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="9">管理员列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value=""></th>
            <th width="40">序列号</th>
            <th width="150">登录名</th>
            <th width="90">手机</th>
            <th width="150">邮箱</th>
            <!-- <th>角色</th> -->
            <th width="130">加入时间</th>
            <th width="100">是否已启用</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList.dataList }" var="vo" varStatus="status" >
	        <tr class="text-c">
	            <td><input type="checkbox" value="${vo.id}" name="batch"></td>
	            <td>${status.index+1}</td>
	            <td>${vo.username}</td>
	            <td>${vo.phone}</td>
	            <td>${vo.email}</td>
	            <%-- <td> ${vo.title}</td> --%>
	            <td><fmt:formatDate value="${vo.registerTime}" pattern="yyyy-HH-dd"/></td>
	            <td class="td-status">
	            	<c:if test="${vo.status}">
	            	已启用
	            	</c:if>
	            	<c:if test="${!vo.status}">
	            	已停用
	            	</c:if>
		            <%-- <span class="label <?php if($vo['status']==1){echo 'label-success';}?> radius">
		            	<?php if($vo['']==1){echo '已启用';}else{echo '已停用';}?>
		            </span> --%>
	            </td><!--<span class="label radius">已停用</span>-->
	            <td class="td-manage">
	                <%-- <a style="text-decoration:none" onClick="" href="javascript:;" title="<?php if($vo['status']==1){echo '停用';}else{echo '启用';}?>"><i class="Hui-iconfont"></i></a> --%>
	               <!--  <a title="重置密码" href="javascript:;" onclick="reset_pwd('{$vo.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe63f;</i></a> -->
	                <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','updateUser','${vo.id}','800','600')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
	                <a title="删除" href="javascript:;" onclick="admin_del(this,'${vo.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
	            </td>
	        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div id="paging"></div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath %>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*管理员-增加*/
    function admin_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    /*管理员-编辑*/
    function admin_edit(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }
    /*管理员-删除*/
    function admin_del(obj,id){
        $count=$('.r strong').html();
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: 'delUser',
                data:{id:id},
                dataType: 'json',
                success: function(data){
                    if (data.code==1000) {
                        if ($.isArray(obj)) {
                            for (var i=0;i<obj.length;i++) {
                                $(obj[i]).parents("tr").remove();
                            }
                            $('.r strong').html($count-obj.length);
                        }else{
                            $(obj).parents("tr").remove();
                            $('.r strong').html($count-1);
                        }
                        layer.msg(data.msg,{icon:1,time:1500});
                    }else{
                        layer.msg(data.msg,{icon:2,time:1500});
                    }
                },
                error:function(data) {
                    layer.msg('系统繁忙',{icon:2,time:1000});
                }
            });
        });
    }
    /*管理员-批量删除*/
    function datadel() {
        var id='';
        var arr=[];
        $("input[name='batch']").each(function () {
            var obj=$(this);
            if (obj.get(0).checked) {
                id+=','+obj.val();
                arr.push(obj);
            }
        });
        if (id=='') {
            layer.msg('请选择删除项');
            return false;
        }
        id=id.substring(1);
        admin_del(arr,id)
    }
    /*管理员-停用*/
    function admin_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                url:'{:U("banUser")}',
                type:'post',
                data:{id:id},
                dataType:'json',
                success: function (data) {
                    if (data.status==1) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,{$vo.id})" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label radius">已停用</span>');
                        $(obj).remove();
                        layer.msg(data.info,{icon: 6,time:1500});
                    }else{
                        layer.msg(data.info,{icon: 5,time:1500});
                    }
                },
                error: function () {
                    layer.msg('系统繁忙',{icon: 5,time:1500});
                }
            });
        });
    }

    /*管理员-启用*/
    function admin_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                url:'{:U("banUser")}',
                type:'post',
                data:{id:id},
                dataType:'json',
                success: function (data) {
                    if (data.status==1) {
                        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,{$vo.id})" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                        $(obj).remove();
                        layer.msg(data.info, {icon: 6,time:1000});
                    }else{
                        layer.msg(data.info,{icon: 5,time:1500});
                    }
                },
                error: function () {
                    layer.msg('系统繁忙',{icon: 5,time:1500});
                }
            });
        });
    }
    /*管理员-重置密码*/
    function reset_pwd(id) {
        layer.confirm('确认要重置密码吗？',function(index) {
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                url:'{:U("resetPwd")}',
                type:'post',
                data:{id:id},
                dataType:'json',
                success: function (data) {
                    if (data.status==1) {
                        layer.msg(data.info, {icon: 6,time:1000});
                    }else {
                        layer.msg(data.info, {icon: 5,time:1000});
                    }
                },
                error: function () {
                    layer.msg('系统繁忙',{icon: 5,time:1500});
                }
            });
        });
    }
    
    $(function() {
		/* 加载分页  */
		//当前页
		var pageNumber = parseInt('${userList.pageNo}');
		//页面大小
		var pageSize = parseInt('${userList.pageSize}');
		//总页数
		var pages = parseInt('${userList.pages}');
		//总条数
		var pageTotal = parseInt('${userList.total}');
		//页面地址
		var like='${like}';
		var pageStart = "href='findUserList?like="+like+"&pageNumber=";
		var pageEnd = "'";

		if(pages == 0) pages = 1;

		var paging = "" +
			"<div class='pull-right'  style='padding: 0px 10%  0px 10%'' >" +
			"<form class='form-inline' role='form'>" +
			"<div class='form-group'>";
		if(pages == pageNumber) {
			paging = paging + "当前页" + (((pageNumber - 1) * pageSize) + 1) + "~" + pageTotal + "条 ,共 " + pageTotal + " 条";
		} else {
			paging = paging + "当前页" + (((pageNumber - 1) * pageSize) + 1) + "~" + (pageNumber * pageSize) + "条 ,共 " + pageTotal + " 条";
		}
		paging = paging +
			"</div>" +
			"<div class='form-group'>" +
			"<ul class='pagination'>";
		if(pageNumber <= 1) {
			paging = paging + "<li class='disabled'><a >上一页</a></li>";
		} else {
			paging = paging + "<li ><a " + pageStart + (pageNumber - 1) + pageEnd + " >上一页</a></li>";
		}
		if(pageNumber <= 3) {
			for(var i = 1; i <= pages && i <= 5; i++) {
				if(i == pageNumber) {
					paging = paging + "<li class='active' ><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				} else {
					paging = paging + "<li><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				}

			}
		} else if(pageNumber >= 3 && pageNumber <= (pages - 2)) {
			for(var i = (pageNumber - 2); i <= (pageNumber + 2); i++) {
				if(i == pageNumber) {
					paging = paging + "<li class='active' ><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				} else {
					paging = paging + "<li><a " + pageStart + i + pageEnd + ">" + i + "</a></li>";
				}
			}
		} else if(pageNumber >= (pages - 2)) {
			for(var i = 4; i >= 0; i--) {
				if((pages - i) == pageNumber) {

					paging = paging + "<li class='active' ><a " + pageStart + (pages - i) + pageEnd + " >" + (pages - i) + "</a></li>";

				} else {
					if((pages - i) > 0) {
						paging = paging + "<li><a " + pageStart + (pages - i) + pageEnd + " >" + (pages - i) + "</a></li>";
					}
				}
			}
		}

		if(pageNumber >= (pages)) {
			paging = paging + "<li class='disabled'><a>下一页</a></li>";
		} else {
			paging = paging + "<li ><a " + pageStart + (pageNumber + 1) + pageEnd + " >下一页</a></li>";
		}
		paging = paging +
			"</ul>" +
			"</div>" +
			"</form>" +
			"</div>";
		$("#paging").html(paging);
		/*alert (pageNumber+""+pageSize+""+pages+""+pageTotal);*/
	});
    
    
</script>
</body>
</html>