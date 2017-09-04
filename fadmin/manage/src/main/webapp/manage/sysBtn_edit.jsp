<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>

<title>按钮管理</title>
</head>
<body>
<div>
	<div class="col-xs-12">
		<form class="form form-horizontal" id="BtnEditForm">
			<div class="row cl">
				<label class="form-label col-xs-3">所属菜单名称：</label>
				<div class="formControls col-xs-9">
					<input disabled="disabled" type="text" class="input-text" id="menu_name" name="menu_name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">按钮名称<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="btn_name" name="btn_name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">按钮编码<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="btn_code" name="btn_code">
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
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
			<input type="hidden" value="" name="edit_type" id="edit_type">
			
			<input type="hidden" value="" name="btn_id" id="btn_id">
			<input type="hidden" value="" name="menu_id" id="menu_id">
		</form>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->

<script type="text/javascript" src="js/sysBtn_edit.js"></script>
</body>
</html>