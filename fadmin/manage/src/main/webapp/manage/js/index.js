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

/*获取皮肤cookie*/
function getskincookie(){
//	var v = $.cookie("Huiskin");
	var v = Cache.get("Huiskin");
	var hrefStr=$("#skin").attr("href");
	if(v==null||v==""){
		v="green";
	}
	if(hrefStr!=undefined){
		var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
		$("#skin").attr("href",hrefRes);
	}
}

/*换肤*/
$("#Hui-skin .dropDown-menu a").click(function(){
	var v = $(this).attr("data-val");
//	$.cookie("Huiskin", v);
	Cache.set("Huiskin",v,new Date("2099-04-01"));	//设置到缓存中，永不过期
	var hrefStr=$("#skin").attr("href");
	var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
	$(window.frames.document).contents().find("#skin").attr("href",hrefRes);
});

function setSystemVersion(){
	var v =  Cache.get("SYSTEM_VERSION");
	if(v && system_version && system_version != v){
		clearCache();
	}
	if(system_version){
		Cache.set("SYSTEM_VERSION",system_version,new Date("2099-04-01"));
	}
}

function findStaffSysMessageCount(){
	$.ajax({
		type: 'post', // 提交方式 get/post
        url: '/SysMessage/findStaffSysMessageCount.do', // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
        	var count = data.messageCount;
        	if(count){
        		$("#staffMessageCount").html(count);
        		$("#staffMessageCount").show();
        	}
        }
	});
	//绑定按钮事件
	$("#Hui-msg").unbind("click").click(function(){
		open_Hui_admin_tab("/manage/sysMessage_list.jsp","我的消息");
		$("#staffMessageCount").hide();
	});
}

$(function(){
	getskincookie();
	setSystemVersion();
	findStaffSysMessageCount();
})