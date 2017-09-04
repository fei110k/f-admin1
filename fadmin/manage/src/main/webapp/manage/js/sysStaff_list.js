function querySysStaff() {
	var staff_name = $("#staff_name").val();
	$("#sysStaffDataTable").ftable({
		url:"/SysStaff/findSysStaff.do",
		param:{staff_name:staff_name},
		singleSelect:true,
		//pageSize:2,
		onLoad:function(data){
//			console.log(data.list);
		},
		onSelect:function(e,rowId,rowData){
//			console.log(rowId);
		},
		onClickRow:function(e,rowId,rowData){
			
		},
		columns:[
			{name:"ID",data:"staff_id",hidden:true},
			{name:"用户名",data:"staff_name"},
			{name:"登录账号",data:"staff_code"},
			{name:"联系电话",data:"phone"},
			{name:"组织名称",data:"org_name"},
			{name:"状态",data:"status",width:35,dataformat:function(data,rowData){
				if(data == "0"){
					return '<span class="label label-success radius">正常</span>';
				}else{
					return '<span class="label label-error radius">停用</span>';
				}
			}}
		]
	});
}

function addSysStaff(){
	var url = "sysStaff_edit.jsp?edit_type=A";
	window.location.href = url;
}


function editSysStaff(){
	var rows = $("#sysStaffDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个用户再进行操作");
		return;
	}
	var url = "sysStaff_edit.jsp?edit_type=E&staff_id="+rows[0].staff_id;
	window.location.href = url;
}


function deleteSysStaff(){
	var rows = $("#sysStaffDataTable").ftable("getSelected");
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
	        	querySysStaff();
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定删除用户\""+rows[0].staff_name+"\"吗？",{
		btn:["删除","取消"],
		btn1:function(index){
			url = "/SysStaff/deleteSysStaffById.do";
			param.staff_id = rows[0].staff_id;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
	
	
}

$(function(){
	querySysStaff();
})