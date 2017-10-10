function querySysMessage() {
	var msg_title = $("#msg_title").val();
	$("#sysMessageDataTable").ftable({
		url:"/SysMessage/findSysMessage.do",
		param:{msg_title:msg_title},
		singleSelect:true,
		rowClass:"",
		//pageSize:2,
		onLoad:function(data){
//			console.log(data.list);
		},
		onSelect:function(e,rowId,rowData){
//			console.log(rowId);
		},
		onClickRow:function(e,rowId,rowData){
			$("#sysMessageDataTable").ftable("selectRow", {
				rowId : rowId
			});
		},
		columns:[
			{name:"ID",data:"msg_id",hidden:true,},
			{name:"标题",data:"msg_title",dataformat:function(data,rowData){
				var is_top = rowData.is_top;
				var msg_state = rowData.msg_state;
				var html = "";
				if(msg_state == "1"){//1:未读 ; 2:已阅
					html += "<strong class=\"c-blue\">"+data+"</strong>";
				}else if(msg_state == "2"){//1:未读状态
					html += data;
				}
				
				if(is_top == "0"){   //0;置顶	;1:不置顶
					html += "<span class=\"label label-danger radius ml-5\">" +
								"<i class=\"Hui-iconfont\">&#xe684;</i>置顶" +
							"</span>";
				}
				
				html = "<a href='javascript:void(0);' onclick='queryMessageDetails(\""+rowData.msg_id+
						"\",\""+rowData.msg_title+"\")'>"+html+"</a>";
				return html;
			}},
			{name:"消息内容",data:"msg_content",hidden:true},
		]
	});
}

/**
 * 查询消息详情
 */
function queryMessageDetails(msg_id,msg_title){
	
	var url = "/manage/sysMessage_query.jsp?msg_id="+msg_id;
	
	var index = layer.open({
		title:msg_title,
		area: [500+'px', 400 +'px'],
		content:url,
		type: 2,
	});
	layer.full(index);
}

function addSysMessage(){
	var url = "sysMessage_edit.jsp?edit_type=A";
	updateSysMessage("添加角色",url);
}

function editSysMessage(){
	var rows = $("#sysMessageDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个用户再进行操作");
		return;
	}
	var url = "sysMessage_edit.jsp?edit_type=E&msg_id="+rows[0].msg_id;
	updateSysMessage("修改角色信息",url);
}
function editRolePrivilege(){
	var rows = $("#sysMessageDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个用户再进行操作");
		return;
	}
	var url = "sysMessagePrivilege_edit.jsp?msg_id="+rows[0].msg_id;
	layer.open({
		title:"角色赋权",
		area: [300+'px', 400 +'px'],
		content:url,
		type: 2,
		btn: ['确定', '取消'],
		btn1: function(index, layero){
			var data = getTopWindow()["layui-layer-iframe" + index].callback();
			$.ajax({
				data:{json:JSON.stringify(data),msg_id:rows[0].msg_id},
				type: 'post', // 提交方式 get/post
				dataType:"json",
		        url: '/SysMessage/editRolePrivilege.do', // 需要提交的 url
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
function updateSysMessage(title,url){
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
			var url = edit_type == "A"?"insertSysMessage":"updateSysMessageById";
			$.ajax({
				data:data,
				type: 'post', // 提交方式 get/post
				dataType:"json",
		        url: '/SysMessage/'+url+'.do', // 需要提交的 url
		        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
		        	if (data.code == '0000') {
		        		alert(data.msg);
		        		layer.close(index); //关闭窗口
		        		querySysMessage();
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

function deleteSysMessage(){
	var rows = $("#sysMessageDataTable").ftable("getSelected");
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
	        	querySysMessage();
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定删除用户\""+rows[0].msg_title+"\"吗？",{
		btn:["删除","取消"],
		btn1:function(index){
			url = "/SysMessage/deleteSysMessageById.do";
			param.msg_id = rows[0].msg_id;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
	
	
}

$(function(){
	querySysMessage();
})