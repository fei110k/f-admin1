<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>

<title>修改密码</title>
</head>
<body>
<div>
	<div class="col-xs-12">
		<form class="form form-horizontal" id="passwordForm">
			<div class="row cl">
				<label class="form-label col-xs-3">原密码<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="password" class="input-text" id="old_password" name="old_password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">新密码<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="password" class="input-text" id="new_password" name="new_password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">确认密码<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="password" class="input-text" id="q_password">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->

<script type="text/javascript">

//表单验证
$("#passwordForm").validate({
	rules:{
		old_password:{
			required:true,
		},
		new_password:{
			required:true,
		},
		q_password:{
			required:true,
			equalTo:"#new_password"
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		$(form).ajaxSubmit({
			type: 'post', // 提交方式 get/post
            url: '/SysStaff/updateSysStaffPassword.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	if (data.code == '0000') {
            		alert(data.msg);
            		layer_close();
				}else{
					alert(data.msg);
				}
            }
		});
		
		//var index = parent.layer.getFrameIndex(window.name);
	}
});
</script>
</body>
</html>