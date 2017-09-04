function initMenuForm() {
	var edit_type = getUrlParam("edit_type");

	$("#edit_type").val(edit_type);
	
	if (edit_type == "E") {
		var job_name = getUrlParam("job_name");
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:{job_name:job_name},
            url: '/QuartzJob/queryJobInfoByConn.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	$("#jobEditForm").fform("setData",data)
            	$("#job_name").attr("readonly","readonly");
            	$("#job_class_name").attr("readonly","readonly");
            }
		});
	}
}

function openCron(){
	layer_show("CRON表达式参考","quartzJob_cron.jsp",800,410);
}

function submitJobForm(){
	var edit_type = $("#edit_type").val();
	var url = edit_type == "A"?"insertSysMenu":"updateSysMenuByConn";
	//表单验证
	$("#jobEditForm").validate({
		rules:{
			job_name:{
				required:true,
			},
			job_name:{
				required:true,
			},
			cron_expression:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
//		submitHandler:function(form){
//			$(form).ajaxSubmit({
//				type: 'post', // 提交方式 get/post
//	            url: '/SysMenu/'+url+'.do', // 需要提交的 url
//	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
//	                // 此处可对 data 作相关处理
//	                alert('提交成功！');
//	                layer_close();
//	            }
//			});
//		}
	});
}

function callback(){
	if(!$("#jobEditForm").valid()){
		return;
	}
	var data = $("#jobEditForm").fform("getData");
	return data;
}

function addMenuChildrens(){
	var menu_children_url = $("#menu_children_url").val();
	if(!menu_children_url){
		return;
	}
	var html = "<div class=\"Huialert\"><i class=\"Hui-iconfont\" onclick='deleteMenuChildrens(this)'>&#xe6a6;</i><span>"+menu_children_url+"</span></div>";
	$("#menu_childrens").append(html);
	$("#menu_children_url").val("");
}

function deleteMenuChildrens(obj){
	$(obj).parent().remove();
}

$(function(){
	initMenuForm();
	submitJobForm();
})