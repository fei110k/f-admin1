function initSysStaff() {
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
            url: '/SysStaff/getStaffUserInfo.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	$("#SysStaff").fform("setData",data)
            }
		});
}

function submitSysStaff(){
	//表单验证
	$("#SysStaff").validate({
		rules:{
			staff_name:{
				required:true,
			},
			staff_code:{
				required:true,
			},
			org_name:{
				required:true,
			},
			phone:{
				required:true,
			},
			family_phone:{
				required:true,
			},
			card:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post', // 提交方式 get/post
	            url: '/SysStaff/updateSysStaffByConn.do', // 需要提交的 url
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	            	if (data.code == '0000') {
	            		alert(data.msg);
            			getTopWindow().layer.close(index);
					}else{
						alert(data.msg);
					}
	            }
			});
			
			//var index = parent.layer.getFrameIndex(window.name);
		}
	});
}

$(function(){
	initSysStaff();
	submitSysStaff();
})