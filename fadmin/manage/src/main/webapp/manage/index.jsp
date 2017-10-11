<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.dtf.admin.common.Consts" %>
<%@ taglib uri="/base" prefix="base"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/html5shiv.js"></script>
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/public/lib/H-ui.admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/public/lib/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/public/lib/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/public/lib/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/public/lib/H-ui.admin/static/h-ui.admin/css/style.css" />

<style type="text/css">
/* 左侧菜单两边不留白 */
.Hui-aside .menu_dropdown dd ul{padding: 3px 0px;}
.Hui-aside .menu_dropdown dd li a{padding-left: 34px;}
</style>

<!-- 鬼畜抖动插件 -->
<!-- <link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/csshake/1.5.2/csshake-default.min.css" /> -->

<!--[if IE 6]>
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title><%=Consts.SYSTEM_NAME %></title>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
			<span class="logo navbar-logo f-l mr-10 hidden-xs"><%=Consts.SYSTEM_NAME %></span>
			<span class="logo navbar-slogan f-l mr-10 hidden-xs"><%=Consts.SYSTEM_VERSION %></span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li class="dropDown dropDown_hover">
						<a href="#" class="dropDown_A">${sessionScope.staff_name}<i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
							<li><a href="javascript:void(0)" onclick="loginout()">退出</a></li>
							<li><a href="javascript:;" onClick="clearCache()">清理缓存</a></li>
						</ul>
					</li>
					<li id="Hui-msg"> 
						<a href="javascript:void(0);" title="消息">
							<span class="badge badge-danger" style="display: none;" id="staffMessageCount">0</span>
							<i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i>
						</a>
					</li>
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="green" title="默认（绿色）">默认（绿色）</a></li>
							<li><a href="javascript:;" data-val="default" title="黑色">黑色</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
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
	<!-- 左侧菜单 -->
	<base:rightMenu />
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="welcome.jsp">我的桌面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome.jsp"></iframe>
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
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/layer/3.1.0/layer.js"></script>
<script type="text/javascript" src="/public/lib/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/public/lib/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!-- 本地缓存插件 -->
<script type="text/javascript" src="/public/lib/cache/browser-storage.js"></script> 

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>

<script type="text/javascript" src="/public/common/js/common.js"></script> 
<script type="text/javascript">
	var system_version = "<%=Consts.SYSTEM_VERSION %>";
</script>
<script type="text/javascript" src="js/index.js"></script> 

</body>
</html>