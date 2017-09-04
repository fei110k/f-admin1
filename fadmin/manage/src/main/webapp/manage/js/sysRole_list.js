function querySysRole() {
	var role_name = $("#role_name").val();
	$("#sysRoleDataTable").ftable({
		url:"/SysRole/findSysRole.do",
		param:{role_name:role_name},
		singleSelect:true,
		//pageSize:2,
		onLoad:function(data){
//			console.log(data.list);
		},
		onSelect:function(e,rowId,rowData){
//			console.log(rowId);
		},
		onClickRow:function(e,rowId,rowData){
			$("#sysRoleDataTable").ftable("selectRow", {
				rowId : rowId
			});
		},
		columns:[
			{name:"ID",data:"role_id",hidden:true},
			{name:"角色名",data:"role_name"},
			{name:"角色说明",data:"role_desc"},
		]
	});
}

function addSysRole(){
	var url = "sysRole_edit.jsp?edit_type=A";
	updateSysRole("添加角色",url);
}

function editSysRole(){
	var rows = $("#sysRoleDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个用户再进行操作");
		return;
	}
	var url = "sysRole_edit.jsp?edit_type=E&role_id="+rows[0].role_id;
	updateSysRole("修改角色信息",url);
}
function editRolePrivilege(){
	var rows = $("#sysRoleDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个用户再进行操作");
		return;
	}
	var url = "sysRolePrivilege_edit.jsp?role_id="+rows[0].role_id;
	layer.open({
		title:"角色赋权",
		area: [300+'px', 400 +'px'],
		content:url,
		type: 2,
		btn: ['确定', '取消'],
		btn1: function(index, layero){
			var data = getTopWindow()["layui-layer-iframe" + index].callback();
			$.ajax({
				data:{json:JSON.stringify(data),role_id:rows[0].role_id},
				type: 'post', // 提交方式 get/post
				dataType:"json",
		        url: '/SysRole/editRolePrivilege.do', // 需要提交的 url
		        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
		        	if (data.code == '0000') {
		        		alert(data.msg);
		        		layer.close(index); //关闭窗口
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
function updateSysRole(title,url){
	layer.open({
		title:title,
		area: [400+'px', 230 +'px'],
		content:url,
		type: 2,
		btn: ['确定', '取消'],
		btn1: function(index, layero){
			var data = getTopWindow()["layui-layer-iframe" + index].callback();
			if(data == false){
				return ;
			}
			
			var edit_type = data.edit_type;
			var url = edit_type == "A"?"insertSysRole":"updateSysRoleById";
			$.ajax({
				data:data,
				type: 'post', // 提交方式 get/post
				dataType:"json",
		        url: '/SysRole/'+url+'.do', // 需要提交的 url
		        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
		        	if (data.code == '0000') {
		        		alert(data.msg);
		        		layer.close(index); //关闭窗口
		        		querySysRole();
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

function deleteSysRole(){
	var rows = $("#sysRoleDataTable").ftable("getSelected");
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
	        	querySysRole();
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定删除用户\""+rows[0].role_name+"\"吗？",{
		btn:["删除","取消"],
		btn1:function(index){
			url = "/SysRole/deleteSysRoleById.do";
			param.role_id = rows[0].role_id;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
	
	
}

$(function(){
	querySysRole();
})