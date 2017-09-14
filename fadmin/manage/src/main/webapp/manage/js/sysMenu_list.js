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
	edit: {
		enable: true,
		drag: {
			isCopy: false,
			isMove: true
		},
		showRemoveBtn:false,	//不展示删除按钮
		showRenameBtn:false,	//不展示修改名称按钮
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
		onClick:treeOnClick,
		beforeDrag: beforeDrag,	//在移动节点前
		beforeDrop: beforeDrop		//在移动节点到目标节点前
	}
}

//移动完节点后
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	if(targetNode == null){
		return false;
	}
	var targetNodeName = targetNode?targetNode.name:"根节点";
	confirm("是否移动到节点“"+targetNodeName+"”下？",{
		btn:["移动","取消"],
		btn1:function(index, layero){
			var menuBtnTree = $.fn.zTree.getZTreeObj("menuBtnTree");
			menuBtnTree.moveNode(targetNode,treeNodes[0], "inner");
			
			var param = {};
			param.parent_id = targetNode?targetNode.id:"-1";
			param.menu_id = treeNodes[0].id;
			
			$.ajax({
				type: 'post', // 提交方式 get/post
				dataType:"json",
				data:param,
		        url: "/SysMenu/menuUpdateParent.do", // 需要提交的 url
		        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
		        	alert(data.msg);
		        	layer.close(index);
		        }
			});
		},
		btn2:function(index, layero){
			layer.close(index);
		}
	})
	return false;
}


//如果返回 false，zTree 将不删除节点，也无法触发 onRemove 事件回调函数
function beforeDrag(treeId, treeNode){
	console.log(treeNode);
	if(treeNode[0].type == "btn"){
		alert("按钮不能拖拽移动！");
		return false;
	}
	return true;
}

$(function(){
	initMenuBtnTree();
	$("#MenuShowForm").fform("setDisabled");
	$("#BtnShowForm").fform("setDisabled");
})