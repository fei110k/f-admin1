$(function(){
	$("#table_name_span").html(getUrlParam("table_name"));
	$("#table_schema_span").html(getUrlParam("table_schema"));
	
	$("#table_name").val(getUrlParam("table_name"));
	$("#table_schema").val(getUrlParam("table_schema"));
	
	function loadCodePath(){
		$.ajax({
			url:"/CodeGeneration/loadCodePath.do",
			success:function(data, textStatus, jqXHR){
				console.log(data);
				$("#bo_path").val(data.manageCodePath);
			}
		});
	}
	
	$("#code_path").unbind("blur").blur(function(){
		$("#vo_path").val($(this).val()+"\\common\\src\\main\\java\\com\\dtf\\admin\\manage\\vo");
	});
	
	//表单验证
	$("#codeGenerationForm").validate({
		rules:{
			vo_path:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			
			$(form).ajaxSubmit({
				type: 'post', // 提交方式 get/post
	            url: '/CodeGeneration/codeGeneration.do', // 需要提交的 url
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	                alert('提交成功！');
	            }
			});
			
			 
			
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			//parent.layer.close(index);
		}
	});
	
})