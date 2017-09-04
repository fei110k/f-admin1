function initTable(list){
	if(!list || list.length == 0){
		return;
	}
	$("#sysAttrValueTable tbody").html("");
	for (var i = 0; i < list.length; i++) {
		addAttrValue(list[i]);
	}
}

function addAttrValue(attrValue){
	if(!attrValue){
		attrValue = {};
		attrValue.attr_value = $("#add_attr_value").val();
		attrValue.attr_value_name = $("#add_attr_value_name").val();
		if(!attrValue.attr_value || !attrValue.attr_value_name){
			alert("值与显示名称为必填，请填写完整后再进行添加操作！");
			return;
		}
		$("#add_attr_value").val("");
		$("#add_attr_value_name").val("");
	}
	var tr =  
		"<tr>"+
			"<td>"+attrValue.attr_value+"</td>"+
			"<td>"+attrValue.attr_value_name+"</td>"+
			"<td>"+
				"<a class=\"btn btn-danger size-MINI radius\" onClick=\"deleteAttrValue(this)\" href=\"javascript:void(0);\" >"+
					"<i class=\"Hui-iconfont\">&#xe609;</i> 删除"+
				"</a>"+
			"</td>"+
		"</tr>";
	$("#sysAttrValueTable tbody").append(tr);
	$("#sysAttrValueTable tbody tr:last").data("data",attrValue);
}

function deleteAttrValue(obj){
	$(obj).parent().parent().remove();
}

function initMenuForm() {
	var edit_type = getUrlParam("edit_type");

	$("#edit_type").val(edit_type);
	
	if (edit_type == "E") {
		var attr_id = getUrlParam("attr_id");
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:{attr_id:attr_id},
            url: '/SysAttr/findSysAttrById.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	initTable(data.attr_values);
            	$("#AttrEditForm").fform("setData",data);
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
	var url = edit_type == "A"?"insertSysAttr":"updateSysAttrByConn";
	//表单验证
	$("#AttrEditForm").validate({
		rules:{
			attr_code:{
				required:true,
				checkCode:true,
			},
			attr_name:{
				required:true,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			
			var trs = $("#sysAttrValueTable tbody tr");
			var attr_values = [];
			if(trs && trs.length > 0){
				for (var i = 0; i < trs.length; i++) {
					var data = $(trs[i]).data("data");
					attr_values.push(data);
				}
			}
			var attr_values_str = JSON.stringify(attr_values);
			$("#attr_values").val(attr_values_str);
			$(form).ajaxSubmit({
				type: 'post', // 提交方式 get/post
	            url: '/SysAttr/'+url+'.do', // 需要提交的 url
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	            	if (data.code == '0000') {
	            		alert(data.msg);
					}else{
						alert(data.msg);
					}
	            }
			});
		}
	});
}

$(function(){
	//初始默认选中第一个
	initMenuForm();
	submitMenuForm();
})