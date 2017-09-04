function queryQuartzJob() {
	var job_name = $("#job_name").val();
	$("#quartzJobDataTable").ftable({
		url:"/QuartzJob/queryJobInfo.do",
		param:{job_name:job_name},
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
//			{name:"ID",data:"staff_id",hidden:true},
			{name:"任务名",data:"job_name"},
//			{name:"任务调度类",data:"job_class_name"},
			{name:"状态",data:"trigger_state",data_code:"JOB_STATE"},
			{name:"上次调度时间",data:"prev_fire_time"},
			{name:"下次调度时间",data:"next_fire_time"},
			{name:"Cron表达式",data:"cron_expression"}
//			{name:"状态",data:"status",width:35,dataformat:function(data,rowData){
//				if(data == "0"){
//					return '<span class="label label-success radius">正常</span>';
//				}else{
//					return '<span class="label label-error radius">停用</span>';
//				}
//			}}
		]
	});
}

function addJob(){
	var url = "quartzJob_edit.jsp?edit_type=A";
	layer.open({
		title:"添加定时任务",
		content:url,
		area: ['800px', '450px'],
		type: 2,
		btn: ['确定', '取消'],
		btn1: function(index, layero){
			var data = getTopWindow()["layui-layer-iframe" + index].callback();
			if(!data){
				alert("请先选中一个角色再进行操作");
				return;
			}
			data.edit_type = "A";
			$.ajax({
				type: 'post', // 提交方式 get/post
				dataType:"json",
				data:data,
	            url: '/QuartzJob/saveJobInfo.do', // 需要提交的 url
	            success: function(data) { //data 保存提交后返回的数据，一般为 json 数据
	            	alert(data.msg);
	            	if(data.code == '0000'){
	            		layer.close(index); //关闭窗口
	            		queryQuartzJob();
	            	}
	            }
			});
			
		},
		btn2: function(index, layero){
			layer.close(index); //关闭窗口
		}
	});
}


function editJob(){
	var rows = $("#quartzJobDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个任务再进行操作");
		return;
	}


	var url = "quartzJob_edit.jsp?edit_type=E&job_name="+rows[0].job_name;
	layer.open({
		title:"添加定时任务",
		content:url,
		area: ['800px', '450px'],
		type: 2,
		btn: ['确定', '取消'],
		btn1: function(index, layero){
			var data = getTopWindow()["layui-layer-iframe" + index].callback();
			if(!data){
				alert("请先选中一个角色再进行操作");
				return;
			}
			data.edit_type = "E";
			$.ajax({
				type: 'post', // 提交方式 get/post
				dataType:"json",
				data:data,
	            url: '/QuartzJob/saveJobInfo.do', // 需要提交的 url
	            success: function(data) { //data 保存提交后返回的数据，一般为 json 数据
	            	alert(data.msg);
	            	if(data.code == '0000'){
	            		layer.close(index); //关闭窗口
	            		queryQuartzJob();
	            	}
	            }
			});
			
		},
		btn2: function(index, layero){
			layer.close(index); //关闭窗口
		}
	});
}
//暂停任务
function triggerJob(){
	var rows = $("#quartzJobDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个任务再进行操作");
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
	        	if(data.code == '0000'){
	        		queryQuartzJob();
	        	}
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定立即执行任务\""+rows[0].job_name+"\"吗？",{
		btn:["执行","取消"],
		btn1:function(index){
			url = "/QuartzJob/triggerJob.do";
			param.job_name = rows[0].job_name;
			param.job_group = rows[0].job_group;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
}

//暂停任务
function pauseJob(){
	var rows = $("#quartzJobDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个任务再进行操作");
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
	        	if(data.code == '0000'){
	        		queryQuartzJob();
	        	}
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定暂停任务\""+rows[0].job_name+"\"吗？",{
		btn:["暂停","取消"],
		btn1:function(index){
			url = "/QuartzJob/pauseJob.do";
			param.job_name = rows[0].job_name;
			param.job_group = rows[0].job_group;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
}

//恢复任务
function resumeJob(){
	var rows = $("#quartzJobDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个任务再进行操作");
		return;
	}
	var param = {};
	param.job_name = rows[0].job_name;
	param.job_group = rows[0].job_group;
	$.ajax({
		type: 'post', // 提交方式 get/post
		dataType:"json",
		data:param,
        url: "/QuartzJob/resumeJob.do", // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
        	alert(data.msg);
        	if(data.code == '0000'){
        		queryQuartzJob();
        	}
        }
	});
}

function deleteJob(){
	var rows = $("#quartzJobDataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert("请先选中一个任务再进行操作");
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
	        	if(data.code == '0000'){
	        		queryQuartzJob();
	        	}
	        }
		});
	}
	
	var param = {};
	var url = "";
	confirm("您确定删除任务\""+rows[0].job_name+"\"吗？",{
		btn:["删除","取消"],
		btn1:function(index){
			url = "/QuartzJob/deleteJobInfo.do";
			param.job_name = rows[0].job_name;
			param.job_group = rows[0].job_group;
			callBack(param, url);
			layer.close(index);
		},btn2:function(index){
			layer.close(index);
		}
	});
}

$(function(){
	queryQuartzJob();
})