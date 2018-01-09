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
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../../..//html5shiv.js"></script>
    <script type="text/javascript" src="../../..//respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../../..//DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>得实产品管理系统</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs">得实产品管理系统</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs"></span>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li><%-- ${cpUser.cpAuthGroup.title} --%></li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">${cpUser.username}<i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="login">切换账户</a></li>
                            <li><a href="logout">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <!-- <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe616;</i> 用户中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="../User/personal-manasege.html" data-title="个人信息" href="javascript:void(0)">个人信息</a></li>
                    <li><a data-href="../User/updatePwd.html" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
                </ul>
            </dd>
        </dl> -->
        <dl id="menu-resource">
            <dt><i class="Hui-iconfont">&#xe63e;</i> 资源管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                   <!--  <li><a data-href="../Resource/settingList.html" data-title="资源设置" href="javascript:void(0)">资源设置</a></li>
                    <li><a data-href="../Resource/imgList.html" data-title="图片管理" href="javascript:void(0)">图片管理</a></li>
                    <li><a data-href="../Resource/videoList.html" data-title="视频管理" href="javascript:void(0)">视频管理</a></li>
                    <li><a data-href="../Resource/driveList.html" data-title="驱动管理" href="javascript:void(0)">驱动管理</a></li>
                    <li><a data-href="../Resource/firmwareList.html" data-title="固件管理" href="javascript:void(0)">固件管理</a></li>
                    <li><a data-href="../Resource/toolList.html" data-title="管理工具" href="javascript:void(0)">管理工具</a></li>
                    <li><a data-href="../Resource/guideList.html" data-title="用户指南" href="javascript:void(0)">用户指南</a></li>
                     -->
                    <li><a data-href="findAppPage" data-title="应用软件" href="javascript:void(0)">应用软件</a></li>
                    <li><a data-href="findSDKPage" data-title="二次开发包" href="javascript:void(0)">二次开发包</a></li>
                    <li><a data-href="/DascomBack/videoList" data-title="视频管理" href="javascript:void(0)">视频管理</a></li>
                    <li><a data-href="/DascomBack/downloadList" data-title="下载管理" href="javascript:void(0)">下载管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-solves">
            <dt><i class="Hui-iconfont">&#xe63e;</i> 产品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/DascomBack/classifyList" data-title="分类管理" href="javascript:void(0)">分类管理</a></li>
                    <li><a data-href="/DascomBack/productList" data-title="产品管理" href="javascript:void(0)">产品管理</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-solves">
            <dt><i class="Hui-iconfont">&#xe63e;</i> 合作伙伴<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/DascomBack/shareInformationList" data-title="资源分享" href="javascript:void(0)">资源分享</a></li>
                    <li><a data-href="/DascomBack/partnersList" data-title="公司列表" href="javascript:void(0)">公司列表</a></li>
                </ul>
            </dd>
        </dl>
        
        <dl id="menu-solves">
            <dt><i class="Hui-iconfont">&#xe63e;</i> 方案管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="findsolveList" data-title="解决方案" href="javascript:void(0)">解决方案</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-solves">
            <dt><i class="Hui-iconfont">&#xe63e;</i> 工单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="findWorkOrderList" data-title="客户工单" href="javascript:void(0)">客户工单</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-solves">
            <dt><i class="Hui-iconfont">&#xe63e;</i>  客服后台<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                	<li><a data-href="/DascomChat/userControl.do" data-title="客服管理" href="javascript:void(0)">客服管理</a></li>
                	<li><a data-href="/DascomChat/PrinterProcedureOrderList.do" data-title="后台工单" href="javascript:void(0)">后台工单</a></li>
                    <li><a data-href="/DascomChat/login2Page" data-title="得实客服" href="javascript:void(0)">得实客服</a></li>
                </ul>
            </dd>
        </dl>
       
        
       <!--  <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe620;</i> 产品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="../Product/classifyList.html" data-title="分类管理" href="javascript:void(0)">分类管理</a></li>
                    <li><a data-href="../Product/productList.html" data-title="产品管理" href="javascript:void(0)">产品管理</a></li>
                </ul>
            </dd>
        </dl> -->
        
        
        
        
        
        
        
        
        
        <dl id="menu-admin">
            <dt><i class="Hui-iconfont">&#xe62d;</i> 权限管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <!-- <li><a data-href="../Admin/jurisdiction.html" data-title="权限管理" href="javascript:void(0)">权限管理</a></li>
                    <li><a data-href="../Admin/role.html" data-title="角色管理" href="javascript:void(0)">角色管理</a></li> -->
                    <li><a data-href="findUserList" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
                </ul>
            </dd>
        </dl>
        <!-- <dl id="menu-system">
            <dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="{:U('System/system')}" data-title="系统设置" href="javascript:void(0)">系统设置</a></li>
                    <li><a data-href="{:U('System/column')}" data-title="栏目管理" href="javascript:void(0)">栏目管理</a></li>
                    <li><a data-href="{:U('System/dictionary')}" data-title="数据字典" href="javascript:void(0)">数据字典</a></li>
                    <li><a data-href="../System/log.html" data-title="操作日志" href="javascript:void(0)">操作日志</a></li>
                </ul>
            </dd>
        </dl> -->
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="wel1come">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="welcome"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前 </li>
        <li id="closeall">关闭全部 </li>
    </ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
    $(function(){

    });
    /*个人信息*/
    function myselfinfo(){
        layer.open({
            type: 1,
            area: ['300px','200px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4,
            title: '查看信息',
            content: '<div>管理员信息</div>'
        });
    }

    /*资讯-添加*/
    function article_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*图片-添加*/
    function picture_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*产品-添加*/
    function product_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*用户-添加*/
    function member_add(title,url,w,h){
        layer_show(title,url,w,h);
    }


</script>
</body>
</html>