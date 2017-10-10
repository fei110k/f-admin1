function initMessagePage() {
	var msg_id = getUrlParam("msg_id");
	
	$.ajax({
		type: 'post', // 提交方式 get/post
		dataType:"json",
		data:{msg_id:msg_id},
        url: '/SysMessage/findSysMessageById.do', // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
        	$("#receive_staff_name").html(data.receive_staff_name);
        	$("#receive_time").html(data.receive_time);
        	$("#msg_content").html(data.msg_content);
        }
	});
}

$(function(){
	initMessagePage();
})