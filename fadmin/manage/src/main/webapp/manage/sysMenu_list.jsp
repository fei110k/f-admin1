<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<!-- 树控件样式 -->
<link rel="stylesheet" href="/public/lib/H-ui.admin/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

<title>菜单管理</title>
</head>
<body>
<div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<a href="javascript:;" onclick="addSysMenu()" class="btn btn-primary radius">
			<i class="Hui-iconfont">&#xe600;</i>新增菜单
		</a>
		<a href="javascript:;" onclick="addSysButton()" class="btn btn-secondary radius">
			<i class="Hui-iconfont">&#xe600;</i>添加按钮
		</a>
		<a href="javascript:;" onclick="editMenuBtn()" class="btn btn-success radius">
			<i class="Hui-iconfont">&#xe60c;</i>修改
		</a>
		<a href="javascript:;" onclick="deleteMenuBtn()" class="btn btn-danger radius">
			<i class="Hui-iconfont">&#xe6e2;</i>删除
		</a>
		<span class="c-red">提示：请先选择一个菜单或按钮再进行操作！</span>
	</div>
	<div class="col-xs-3">
		<ul id="menuBtnTree" class="ztree"></ul>
	</div>
	<div class="col-xs-9">
		<form class="form form-horizontal" id="MenuShowForm">
			<div class="row cl">
				<label class="form-label col-xs-3">菜单名称：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">菜单URL：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="menu_url" name="menu_url">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">状态：</label>
				<div class="formControls col-xs-9">
					<span class="select-box">
						<select class="select" name="status" size="1">
							<option value="0">有效</option>
							<option value="1">无效</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">排列顺序：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" id="sort_order" title="数字越小越靠前展示" name="sort_order" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">菜单图标：</label>
				<div class="formControls col-xs-9" >
					<i class="Hui-iconfont" id="class_style"></i>
				</div>
			</div>
		</form>
		<form class="form form-horizontal" id="BtnShowForm" style="display: none;">
			<div class="row cl">
				<label class="form-label col-xs-3">按钮名称：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">按钮编码：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" id="btn_code" name="btn_code">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">状态：</label>
				<div class="formControls col-xs-9">
					<span class="select-box">
						<select class="select" name="status" size="1">
							<option value="0">有效</option>
							<option value="1">无效</option>
						</select>
					</span>
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 

<script type="text/javascript" src="js/sysMenu_list.js"></script>
</body>
</html>