<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>

<title>组织管理</title>
</head>
<body>
<div>
	<div class="col-xs-12">
		<form class="form form-horizontal" id="OrgForm">
			<div class="row cl">
				<label class="form-label col-xs-3">父组织名称：</label>
				<div class="formControls col-xs-9">
					<input disabled="disabled" type="text" class="input-text" id="parent_name" name="parent_name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">组织名称<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" value="" placeholder="" id="org_name" name="org_name">
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
			<input type="hidden" name="edit_type" id="edit_type">
			
			<input type="hidden" name="parent_id" id="parent_id">
			
			<input type="hidden" name="org_id" id="org_id">
			<input type="hidden" name="org_level" id="org_level">
			<input type="hidden" name="org_path" id="menu_path">
		</form>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->

<script type="text/javascript" src="js/sysOrg_edit.js"></script>
</body>
</html>