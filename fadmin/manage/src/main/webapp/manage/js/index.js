/*个人信息*/
function myselfinfo(){
	layer_show("个人信息","/manage/staff_user_info.jsp",800,410);
}
/**
 * 退出登录
 */
function loginout(){
	$.ajax({
		type: 'post', // 提交方式 get/post
        url: '/loginout.do', // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
        	window.location.href = "/manage/login.jsp";
        }
	});
}

function clearCache(){
	BrowserStorage.api.clearAll();
}