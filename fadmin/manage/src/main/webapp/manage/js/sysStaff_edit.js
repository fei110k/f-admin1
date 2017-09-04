function initOrgStaff() {
	var edit_type = getUrlParam("edit_type");

	$("#edit_type").val(edit_type);
	
	if (edit_type == "E") {
		var staff_id = getUrlParam("staff_id");
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:{staff_id:staff_id},
            url: '/SysStaff/findSysStaffById.do', // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            	$("#SysStaff").fform("setData",data)
            }
		});
	}else{
		$("#roleTab").hide();
		
		var parent_id = getUrlParam("parent_id");
		var parent_name = getUrlParam("parent_name");
		$("#parent_id").val(parent_id);
		$("#parent_name").val(parent_name);
	}
}

function addStaffRole(){
	var url = "sysRole_select.jsp";
	layer.open({
		title:"选择要添加的角色",
		content:url,
		area: ['400px', '450px'],
		type: 2,
		btn: ['选择', '取消'],
		btn1: function(index, layero){
			var rows = getTopWindow()["layui-layer-iframe" + index].callback();
			if(!rows || rows.length == 0){
				alert("请先选中一个角色再进行操作");
				return;
			}
			rows[0].staff_id = getUrlParam("staff_id");
			$.ajax({
				type: 'post', // 提交方式 get/post
				dataType:"json",
				data:rows[0],
	            url: '/SysStaff/insertStaffRole.do', // 需要提交的 url
	            success: function(data) { //data 保存提交后返回的数据，一般为 json 数据
	            	if(data.code == '0000'){
	            		alert(data.msg);
	            		layer.close(index); //关闭窗口
	            		initSysStaffRoleTable();
	            	}else{
	            		alert(data.msg);
	            	}
	            }
			});
			
		},
		btn2: function(index, layero){
			layer.close(index); //关闭窗口
		}
	});
}

function deleteStaffRole(){
	var rows = $("#sysStaffRoleTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个用户再进行操作");
		return;
	}
	
	function callBack(param,url){
		$.ajax({
			type: 'post', // 提交方式 get/post
			dataType:"json",
			data:param,
	        url: url, // 需要提交的 url
	        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	        	alert(data.msg);
	        	initSysStaffRoleTable();
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定删除用户关联的角色\""+rows[0].role_name+"\"吗？" +
			"<br/>删除后用户将无法访问该角色下的菜单或者按钮。",{
		btn:["删除","取消"],
		btn1:function(index){
			url = "/SysStaff/deleteStaffRole.do";
			param.rel_id = rows[0].rel_id;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
}
function initSysStaffRoleTable(){
	var staff_id =  getUrlParam("staff_id");
	$("#sysStaffRoleTable").ftable({
		url:"/SysStaff/findStaffRole.do",
		param:{staff_id:staff_id},
		singleSelect:true,
		//pageSize:2,
		onLoad:function(data){
//			console.log(data.list);
		},
		onSelect:function(e,rowId,rowData){
//			console.log(rowId);
		},
		onClickRow:function(e,rowId,rowData){
			$("#sysStaffRoleTable").ftable("selectRow", {
				rowId : rowId
			});
		},
		columns:[
			{name:"ID",data:"rel_id",hidden:true},
			{name:"角色名称",data:"role_name"},
			{name:"角色说明",data:"role_desc"}
		]
	});
}

function selectSysOrg(){
	var url = "sysOrg_tree.jsp";
	layer.open({
		title:"选择组织",
		content:url,
		type: 2,
		btn: ['选择', '取消'],
		btn1: function(index, layero){
			var node = getTopWindow()["layui-layer-iframe" + index].callback();
			if(node != null){
				$("#org_id").val(node.org_id);
				$("#org_name").val(node.org_name);
			}
			layer.close(index); //关闭窗口
		},
		btn2: function(index, layero){
			layer.close(index); //关闭窗口
		}
	});
}

function submitSysStaff(){
	var edit_type = $("#edit_type").val();
	var url = edit_type == "A"?"insertSysStaff":"updateSysStaffByConn";
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
	            url: '/SysStaff/'+url+'.do', // 需要提交的 url
	            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
	            	if (data.code == '0000') {
	            		if(edit_type == 'A'){
	            			confirm(data.msg, {
	            				btn: ['返回列表'], // 可以无限个按钮
	            				btn1: function(index, layero){
	            				    // 按钮【按钮三】的回调
	            					window.history.go(-1);
	            					getTopWindow().layer.close(index);
	            				}
	            			});
	            		}else{
	            			alert(data.msg);
	            		}
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
	//初始默认选中第一个
	$("#tab-staff").Huitab({
		index:0
	});
	initOrgStaff();
	submitSysStaff();
	initSysStaffRoleTable();
})