function initMenuForm() {
	var edit_type = getUrlParam("edit_type");

	$("#edit_type").val(edit_type);
	
	if (edit_type == "E") {
		var menu_id = getUrlParam("menu_id");
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:{menu_id:menu_id},
            url: '/SysMenu/findSysMenuById.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	$("#MenuEditForm").fform("setData",data)
            	
            	console.log(data);
            	var menu_children_urls = data.menu_children_urls;
            	for (var i = 0; i < menu_children_urls.length; i++) {
            		var html = "<div class=\"Huialert\"><i class=\"Hui-iconfont\" onclick='deleteMenuChildrens(this)'>&#xe6a6;</i><span>"
            						+menu_children_urls[i].menu_children_url
            					+"</span></div>";
            		$("#menu_childrens").append(html);
				}
            }
		});
	}else{
		var parent_id = getUrlParam("parent_id");
		var parent_name = getUrlParam("parent_name");
		$("#parent_id").val(parent_id);
		$("#parent_name").val(parent_name);
	}
}

function submitMenuForm(){
	var edit_type = $("#edit_type").val();
	var url = edit_type == "A"?"insertSysMenu":"updateSysMenuByConn";
	//表单验证
	$("#MenuEditForm").validate({
		rules:{
			menu_name:{
				required:true,
			},
			menu_url:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			
			var menu_childrens = [];
			var cs = $("#menu_childrens").find("div.Huialert");
			if(cs.length > 0){
				for (var i = 0; i < cs.length; i++) {
					menu_childrens.push($(cs[i]).find("span").html());
				}
			}
			$("#menu_children_urls").val(menu_childrens.join(","));
			$(form).ajaxSubmit({
				type: 'post', // 提交方式 get/post
	            url: '/SysMenu/'+url+'.do', // 需要提交的 url
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	                // 此处可对 data 作相关处理
	                alert('提交成功！');
	                layer_close();
	            }
			});
			
			 
			
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			//parent.layer.close(index);
		}
	});
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
	submitMenuForm();
})