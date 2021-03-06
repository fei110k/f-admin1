function querySysAttr() {
	var attr_name = $("#attr_name").val();
	$("#sysAttrDataTable").ftable({
		url:"/SysAttr/findSysAttr.do",
		param:{attr_name:attr_name},
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
			{name:"ID",data:"attr_id",hidden:true},
			{name:"字典编码",data:"attr_code"},
			{name:"字典名称",data:"attr_name"},
			{name:"状态",data:"struts",width:35,dataformat:function(data,rowData){
				if(data == "0"){
					return '<span class="label label-success radius">正常</span>';
				}else{
					return '<span class="label label-error radius">停用</span>';
				}
			}}
		]
	});
}

function addSysAttr(){
	var url = "sysAttr_edit.jsp?edit_type=A";
	window.location.href = url;
}


function editSysAttr(){
	var rows = $("#sysAttrDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个用户再进行操作");
		return;
	}
	var url = "sysAttr_edit.jsp?edit_type=E&attr_id="+rows[0].attr_id;
	window.location.href = url;
}


function deleteSysAttr(){
	var rows = $("#sysAttrDataTable").ftable("getSelected");
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
	        	querySysAttr();
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定删除用户\""+rows[0].attr_name+"\"吗？",{
		btn:["删除","取消"],
		btn1:function(index){
			url = "/SysAttr/deleteSysAttrById.do";
			param.attr_id = rows[0].attr_id;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
	
	
}

$(function(){
	
	querySysAttr();
})