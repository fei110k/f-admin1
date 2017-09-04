var setting = {
		check: {
			enable: true,
			chkboxType:{ "Y" : "s", "N" : "s" }
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
	            pIdKey: "pid",
	            rootPId: ""
			},
			key:{name:"name"}
		},
		callback:{
			//单击事件
			//onClick:treeOnClick
		}
	}

function initMenuBtnTree() {
	var edit_type = getUrlParam("edit_type");
	$.ajax({
		url :"/SysMenu/findMenuBtns.do",
		type:"post",
		dataType:"json",
		success : function(data) {
			$.fn.zTree.init($("#menuBtnTree"), setting, data);
			initTreeChecked();	//先加载完整个树，再加载选中数据
		}
	});
}

function initTreeChecked(){
	var role_id = getUrlParam("role_id");
	$.ajax({
		type: 'post', // 提交方式 get/post
		dataType:"json",
		data:{role_id:role_id},
		url: '/SysRole/findRolePrivilege.do', // 需要提交的 url
		success : function(data) {
			if(data && data.length > 0){
				var treeObj = $.fn.zTree.getZTreeObj("menuBtnTree");
				for (var i = 0; i < data.length; i++) {
					var node = treeObj.getNodeByParam("id",data[i].privilege_obj_id);
					treeObj.checkNode(node,true);
				}
			}
		}
	});
}

function callback(){
	var treeObj = $.fn.zTree.getZTreeObj("menuBtnTree");
	var nodes = treeObj.getCheckedNodes(true);
	var data = [];
	if(nodes && nodes.length > 0){
		for (var i = 0; i < nodes.length; i++) {
			var node = nodes[i];
			data.push({id:node.id,type:node.type});
		}
	}
	return data;
}

$(function(){
	initMenuBtnTree();
})