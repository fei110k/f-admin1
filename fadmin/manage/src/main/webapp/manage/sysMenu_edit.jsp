<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<style type="text/css">
.Huialert{
	margin: 3px 0px;
	padding: 4px;
	background: white;
    border-color: #ddd;
    color: #31708f;
}
.Huialert .Hui-iconfont{top: 4px;}
.input-button{
	position: absolute;right:15px;top: 0px;
}
</style>
<title>菜单管理</title>
</head>
<body>
<div>
	<div class="col-xs-12">
		<form class="form form-horizontal" id="MenuEditForm">
			<div class="row cl">
				<label class="form-label col-xs-3">父菜单名称：</label>
				<div class="formControls col-xs-9">
					<input disabled="disabled" type="text" class="input-text" id="parent_name" name="parent_name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">菜单名称<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="menu_name" name="menu_name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">菜单URL<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="menu_url" name="menu_url">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">状态<span class="c-red">*</span>：</label>
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
				<label class="form-label col-xs-4 col-sm-3">排列顺序：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" id="sort_order" title="数字越小越靠前展示" name="sort_order" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					菜单图标<a href="javascrip:void" onclick="window.open('http://www.h-ui.net/Hui-3.7-Hui-iconfont.shtml')"><i class="Hui-iconfont">&#xe633;</i></a>:
				</label>
				<div class="formControls col-xs-8 col-sm-9" >
					<input type="text" class="input-text" id="class_style" name="class_style">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					子菜单:
				</label>
				<div class="formControls col-xs-8 col-sm-9" >
					<div >
						<a href="javascript:;" onclick="addMenuChildrens()" class="btn btn-primary  input-button">
							<i class="Hui-iconfont">&#xe600;</i>
						</a>
						<input type="text" class="input-text" id="menu_children_url">
						<input type="hidden" name="menu_children_urls" id="menu_children_urls">
					</div>
					<div id="menu_childrens">
					</div>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
			<input type="hidden" value="" name="edit_type" id="edit_type">
			<input type="hidden" value="" name="parent_id" id="parent_id">
			
			<input type="hidden" value="" name="menu_id" id="menu_id">
			<input type="hidden" value="" name="menu_path" id="menu_path">
			<input type="hidden" value="" name="menu_level" id="menu_level">
		</form>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->

<script type="text/javascript" src="js/sysMenu_edit.js"></script>
</body>
</html>