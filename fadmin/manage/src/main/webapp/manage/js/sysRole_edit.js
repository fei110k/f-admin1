function initSysRole() {
	var edit_type = getUrlParam("edit_type");
	$("#edit_type").val(edit_type);
	
	if (edit_type == "E") {
		var role_id = getUrlParam("role_id");
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:{role_id:role_id},
            url: '/SysRole/findSysRoleById.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	$("#SysRole").fform("setData",data)
            }
		});
	}else{
	}
}

function submitSysRole(){
	//表单验证
	$("#SysRole").validate({
		rules:{
			role_name:{
				required:true,
			},
			role_desc:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
	});
}

function callback(){
	var v = $("#SysRole").valid();
	if(!v){
		return false;
	}
	return $("#SysRole").fform("getData");
}

$(function(){
	initSysRole();
	submitSysRole();
})