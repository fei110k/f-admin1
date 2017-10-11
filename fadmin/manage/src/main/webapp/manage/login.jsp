<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<%@ page import="com.dtf.admin.common.Consts" %>
<link href="/public/lib/H-ui.admin/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<title>登录</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header">
	<h1 style=""><%=Consts.SYSTEM_NAME %> <%=Consts.SYSTEM_VERSION %></h1>
</div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" id="loginForm" method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="staff_code" name="staff_code" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          	<input class="input-text size-L" type="text" placeholder="验证码" 
          		value="" style="width:150px;"
          		name="verificationCode" id="verificationCode">
          	<img src="/Verification.do" id="VerificationImage" onclick="changeVerifyCode()">
			<a id="kanbuq" href="javascript:changeVerifyCode();" class="white">看不清，换一张</a>
		</div>
      </div>
      <div class="row cl white">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">使我保持登录状态</label>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright <%=Consts.SYSTEM_NAME %> <%=Consts.SYSTEM_COPYRIGHT %></div>
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="/public/lib/H-ui.admin/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/layer/3.1.0/layer.js"></script>
<!--表单相关插件-->
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/jquery.validation/1.14.0/messages_zh.js"></script> 

<script type="text/javascript" src="/public/common/js/common.js"></script>

<script type="text/javascript">
function changeVerifyCode() {  
    var time=new Date().getTime();  
    document.getElementById("VerificationImage").src="/Verification.do?d="+time;//为了不让验证码缓存，为了安全起见，需要次次都刷新  
}  
$(function(){
	$("#loginForm").validate({
		rules:{
			staff_code:{
				required:true,
			},
			password:{
				required:true,
			},
			verificationCode:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			
			$(form).ajaxSubmit({
				type: 'post', // 提交方式 get/post
		        url: '/login.do', // 需要提交的 url
		        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
		            // 此处可对 data 作相关处理
		        	if(data.code == '0000'){
		        		window.location.href = "/manage/index.jsp";
		        	}else{
		        		alert(data.msg);
		        	}
		        }
			});
		}
	});
})
</script>
</body>
</html>