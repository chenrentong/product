<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="__H-UI__/lib/html5shiv.js"></script>
    <script type="text/javascript" src="__H-UI__/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/page.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/js/asset/bootstrap/dist/css/bootstrap.css" />
		
    
    <!--[if IE 6]>
    <script type="text/javascript" src="__H-UI__/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>解决方案</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 方案管理 <span class="c-gray en">&gt;</span> 解决方案 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <form class="findAppPage" method="post" action="" target="_self">
            <input type="text" class="input-text" style="width:250px" placeholder="解决方案名称" name="title" value="${title }">
            <button type="submit" class="btn btn-success" name=""><i class="Hui-iconfont">&#xe665;</i> 搜解决方案</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            <a href="javascript:;" onclick="classify_add('添加解决方案','addSolves','1300','700')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加解决方案</a>
        </span>
        <span class="r">共有数据：<strong>${solveList.total }</strong> 条</span>
    </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="8">解决方案列表</th>
        </tr>
        <tr class="text-c">
            <th width="5%"><input type="checkbox" name="" value=""></th>
            <th width="10%">序列号</th>
            <th width="25%">标题</th>
            <th width="15%">缩略图</th>
            <th width="16%">上传时间</th>
            <th width="7%">是否置顶</th>
            <th width="7%">是否启用</th>
            <th >操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${solveList.dataList}" var="item" varStatus="status">
            <tr class="text-c">
                <td><input type="checkbox" value="${item.id}" name="batch"></td>
                <td>${status.index+1}</td>
                <td>${item.title}</td>
                <td>
                    <c:if test="${item.coverUrl!= null}">
                    	<img src="${item.coverUrl}" height="50" alt="">
                    </c:if>
                </td>
                <th width="16%"><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd"/></th>
                <td>
                <c:if test="${item.topper==1}">
                	是
                </c:if>
                <c:if test="${item.topper!=1}">
                	否
                </c:if>
                </td>
                <td>
                <c:if test="${item.prof==1}">
                	是
                </c:if>
                <c:if test="${item.prof!=1}">
                	否
                </c:if>
                </td>
                <td>
                    <a title="编辑" href="javascript:;" onclick="classify_edit('软件编辑','editSolves','${item.id}','1300','700')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                    <a title="删除" href="javascript:;" onclick="classify_del(this,'${item.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
<script type="text/javascript" src="<%=basePath %>/js/asset/bootstrap/dist/js/bootstrap.min.js"></script>
		
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*软件-添加*/
    function classify_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*软件-编辑*/
    function classify_edit(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }
    /*软件-编辑*/
    function versionList(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }

    /*软件-删除*/
    function classify_del(obj,id){
    	
        $count=$('.r strong').html();
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: 'delSolves',
                data:{"id":id},
                dataType: 'json',
                success: function(data){
                	console.log(data);
                    if (data.code=="1000") {
                        if ($.isArray(obj)) {
                            for (var i=0;i<obj.length;i++) {
                                $(obj[i]).parents("tr").remove();
                            }
                            $('.r strong').html($count-obj.length);
                        }else{
                            $(obj).parents("tr").remove();
                            $('.r strong').html($count-1)
                        }
                        layer.msg(data.msg,{icon:1,time:1500});
                    }else{
                        layer.msg(data.msg,{icon:2,time:1500});
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }
    /*软件-批量删除*/
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
        classify_del(arr,id)
    }
    
    $(function() {
		/* 加载分页  */
		//当前页
		var pageNumber = parseInt('${solveList.pageNo}');
		//页面大小
		var pageSize = parseInt('${solveList.pageSize}');
		//总页数
		var pages = parseInt('${solveList.pages}');
		//总条数
		var pageTotal = parseInt('${solveList.total}');
		//页面地址
		var pageStart = "href='findsolveList?pageNumber=";
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