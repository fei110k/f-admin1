function initMenuForm() {
	var edit_type = getUrlParam("edit_type");

	$("#edit_type").val(edit_type);
	
	if (edit_type == "E") {
		var btn_id = getUrlParam("btn_id");
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:{btn_id:btn_id},
            url: '/SysButton/findSysButtonById.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	$("#BtnEditForm").fform("setData",data)
            }
		});
	}else{
		var menu_id = getUrlParam("menu_id");
		var menu_name = getUrlParam("menu_name");
		$("#menu_id").val(menu_id);
		$("#menu_name").val(menu_name);
	}
}

function submitMenuForm(){
	var edit_type = $("#edit_type").val();
	var url = edit_type == "A"?"insertSysButton":"updateSysButtonByConn";
	//表单验证
	$("#BtnEditForm").validate({
		rules:{
			btn_name:{
				required:true,
			},
			btn_code:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post', // 提交方式 get/post
	            url: '/SysButton/'+url+'.do', // 需要提交的 url
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	            	if (data.code == '0000') {
	            		alert(data.msg);
					}else{
						alert(data.msg);
					}
	            }
			});
			
			 
			
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			//parent.layer.close(index);
		}
	});
}

$(function(){
	initMenuForm();
	submitMenuForm();
})