function initOrgForm() {
	var edit_type = getUrlParam("edit_type");

	$("#edit_type").val(edit_type);
	
	if (edit_type == "E") {
		var org_id = getUrlParam("org_id");
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:{org_id:org_id},
            url: '/SysOrg/findSysOrgById.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	$("#OrgForm").fform("setData",data)
            }
		});
	}else{
		var parent_id = getUrlParam("parent_id");
		var parent_name = getUrlParam("parent_name");
		$("#parent_id").val(parent_id);
		$("#parent_name").val(parent_name);
	}
}

function submitOrgForm(){
	var edit_type = $("#edit_type").val();
	var url = edit_type == "A"?"insertSysOrg":"updateSysOrgByConn";
	//表单验证
	$("#OrgForm").validate({
		rules:{
			org_name:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post', // 提交方式 get/post
	            url: '/SysOrg/'+url+'.do', // 需要提交的 url
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	            	if (data.code == '0000') {
	            		alert(data.msg);
	            		window.parent.initOrgTree();
	            		layer_close();
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
	initOrgForm();
	submitOrgForm();
})