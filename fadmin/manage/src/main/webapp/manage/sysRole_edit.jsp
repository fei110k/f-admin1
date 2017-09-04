<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>

<title></title>
</head>
<body>
<div>
	<div class="col-xs-12">
		<form class="form form-horizontal" id="SysRole">
			<div class="row cl">
				<label class="form-label col-xs-4"><span class="c-red">*</span>角色名：</label>
				<div class="formControls col-xs-8">
					<input type="text" class="input-text" id="role_name" name="role_name"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4"><span class="c-red">*</span>角色说明：</label>
				<div class="formControls col-xs-8">
					<input type="text" class="input-text" id="role_desc" name="role_desc"/>
				</div>
			</div>
			<input type="hidden" name="edit_type" id="edit_type"/>
			<input type="hidden" name="role_id" id="role_id"/>
		</form>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript" src="js/sysRole_edit.js"></script>
</body>
</html>