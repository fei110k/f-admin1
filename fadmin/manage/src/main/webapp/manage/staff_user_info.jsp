<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>

<title>用户个人信息</title>
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="SysStaff">
		<div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-9">
				<input disabled="disabled" type="text" class="input-text" name="staff_name"/>
				<input type="hidden" class="input-text" id="staff_name" name="staff_name"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>登录账号：</label>
			<div class="formControls col-xs-9">
				<input disabled="disabled" type="text" class="input-text" name="staff_code"/>
				<input type="hidden" class="input-text" id="staff_code" name="staff_code"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>归属组织：</label>
			<div class="formControls col-xs-9">
				<input disabled="disabled" type="text" class="input-text" id="org_name" name="org_name"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>联系电话：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text" id="phone" name="phone"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>紧急联络人电话：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text" id="family_phone" name="family_phone"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>身份证号：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text" id="card" name="card"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-3">年龄：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text" id="age" name="age"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-xs-9">
				<span class="select-box">
					<select disabled="disabled" class="select" name="sex" id="sex" size="1">
						<option value="0">女</option>
						<option value="1">男</option>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3">学历：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text" id="education" name="education"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-3">毕业学校：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text" id="school_name" name="school_name"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3">所学专业：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text" id="major" name="major"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-3">创建时间：</label>
			<div class="formControls col-xs-9">
				<input type="text" class="input-text Wdate" id="create_date" name="create_date" disabled="disabled"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
		</div>
		
		<div class="row cl" style="display: none;">
			<label class="form-label col-xs-3"><span class="c-red">*</span>状态：</label>
			<div class="formControls col-xs-9">
				<span class="select-box">
					<select class="select" name="status" size="1">
						<option value="0">正常</option>
						<option value="1">停用</option>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<div style="text-align: center;margin-bottom: 20px;">
				<button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
		</div>
		<input type="hidden" name="edit_type" id="edit_type"/>
		
		<input type="hidden" name="staff_id" id="staff_id"/>
		<input type="hidden" name="org_id" id="org_id"/>
	</form>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->

<script type="text/javascript" src="js/staff_user_info.js"></script>
</body>
</html>