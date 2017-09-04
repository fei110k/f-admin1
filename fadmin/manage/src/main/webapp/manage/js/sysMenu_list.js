function initMenuBtnTree() {
	$.ajax({
		url :"/SysMenu/findMenuBtns.do",
		type:"post",
		dataType:"json",
		success : function(data) {
			$.fn.zTree.init($("#menuBtnTree"), setting, data);
		}
	});
}

function addSysMenu(){
	var treeObj = $.fn.zTree.getZTreeObj("menuBtnTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请先选中一个菜单或者按钮再进行操作");
		return;
	}
	
	var url = "sysMenu_edit.jsp?edit_type=A&parent_id="+nodes[0].id+"&parent_name="+nodes[0].name;
	layer_show("添加菜单",url,800,410);
}

function addSysButton(){
	var treeObj = $.fn.zTree.getZTreeObj("menuBtnTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请先选中一个菜单进行操作");
		return;
	}
	
	if (nodes[0].type != 'menu') {
		alert("不能在按钮下边增加按钮，只能选中菜单进行操作");
		return;
	}
	
	var url = "sysBtn_edit.jsp?edit_type=A&menu_id="+nodes[0].id+"&menu_name="+nodes[0].name;
	layer_show("添加按钮",url,700,350);
}


function editMenuBtn(){
	var treeObj = $.fn.zTree.getZTreeObj("menuBtnTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请先选中一个菜单或者按钮再进行操作");
		return;
	}
	if(nodes[0].type == "menu"){
		var url = "sysMenu_edit.jsp?edit_type=E&menu_id="+nodes[0].id;
		layer_show("修改菜单",url,800,410);
	}else{
		var url = "sysBtn_edit.jsp?edit_type=E&btn_id="+nodes[0].id;
		layer_show("修改按钮",url,700,350);
	}
}


function deleteMenuBtn(){
	var treeObj = $.fn.zTree.getZTreeObj("menuBtnTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请先选中一个菜单或者按钮再进行操作");
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
	        	initMenuBtnTree();
	        }
		});
	}
	
	var param = {};
	var url = "";
	if(nodes[0].type == "menu"){
		confirm("您确定删除菜单\""+nodes[0].name+"\"吗？<br/>如果确认删除，将删除该菜单的子菜单及按钮。",{
			btn:["删除","取消"],
			btn1:function(index){
				url = "/SysMenu/deleteSysMenuById.do";
				param.menu_id = nodes[0].id;
				callBack(param, url);
				layer.close(index);
			},btn2:function(index){
				layer.close(index);
			}
		});
	}else{
		confirm("您确定删除按钮\""+nodes[0].name+"\"吗？",{
			btn:["删除","取消"],
			btn1:function(index){
				url = "/SysButton/deleteSysButtonById.do";
				param.btn_id = nodes[0].id;
				callBack(param, url);
				layer.close(index);
			},btn2:function(index){
				layer.close(index);
			}
		});
		
		
	}
	
	
}

function treeOnClick(event, treeId, treeNode) {
	$("#class_style").html("");
	if(treeNode.type == "menu"){
		$("#BtnShowForm").hide();
		$("#MenuShowForm").show();
		$("#MenuShowForm").fform("setData",treeNode);
		$("#class_style").html(treeNode.class_style);
	}else{
		$("#BtnShowForm").show();
		$("#MenuShowForm").hide();
		$("#BtnShowForm").fform("setData",treeNode);
	}
	
}

var setting = {
	/*check: {
		enable: true,
		chkboxType:{ "Y" : "s", "N" : "s" }
	},*/
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
		onClick:treeOnClick
	}
}

$(function(){
	initMenuBtnTree();
	$("#MenuShowForm").fform("setDisabled");
	$("#BtnShowForm").fform("setDisabled");
})