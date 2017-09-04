function initOrgTree() {
	$.ajax({
		url :"/SysOrg/findSysOrg.do",
		type:"post",
		dataType:"json",
		success : function(data) {
			$.fn.zTree.init($("#orgTree"), setting, data);
		}
	});
}

function addSysOrg(){
	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请先选中一个组织再进行操作");
		return;
	}
	
	var url = "sysOrg_edit.jsp?edit_type=A&parent_id="+nodes[0].org_id+"&parent_name="+nodes[0].org_name;
	layer_show("添加组织",url,800,410);
}


function editSysOrg(){
	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请先选中一个组织再进行操作");
		return;
	}
	var url = "sysOrg_edit.jsp?edit_type=E&org_id="+nodes[0].org_id;
	layer_show("修改组织",url,800,410);
}


function deleteSysOrg(){
	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请先选中一个组织再进行操作");
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
	        	initOrgTree();
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定删除组织\""+nodes[0].org_name+"\"吗？<br/>如果确认删除，将删除该组织下的所有组织。",{
		btn:["删除","取消"],
		btn1:function(index){
			url = "/SysOrg/deleteSysOrgById.do";
			param.org_id = nodes[0].org_id;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
	
	
}

function treeOnClick(event, treeId, treeNode) {
	$("#class_style").html("");
	$("#OrgForm").fform("setData",treeNode);
}

var setting = {
	/*check: {
		enable: true,
		chkboxType:{ "Y" : "s", "N" : "s" }
	},*/
	data: {
		simpleData: {
			enable: true,
			idKey: "org_id",
            pIdKey: "parent_id",
            rootPId: ""
		},
		key:{name:"org_name"}
	},
	callback:{
		//单击事件
		onClick:treeOnClick
	}
}

$(function(){
	initOrgTree();
	$("#OrgForm").fform("setDisabled");
})