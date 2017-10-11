$(function(){
	/**
	 * 获取从URL上边的参数
	 * @param name 参数名
	 * @returns
	 */
	window.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if ( r != null ){
	       return decodeURI(r[2]);
	    }else{
	       return null;
	    } 
	}
	
	window.getTopWindow = function(){
		return window.top.window;
	}
	//重写自带alert
	window.layer = getTopWindow().layer;
	window.alert = function(msg){
		return getTopWindow().layer.alert(msg, {shadeClose : true});
	}
	window.confirm = function(title,btns){
		return getTopWindow().layer.confirm(title, btns);
	}
	
	//所有的checkbox自动加载
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if($.validator){
		//添加编码CODE的验证字母数据或下划线
		$.validator.addMethod("checkCode",function(value,element,params){  
			var checkName = /^\w{3,50}$/g;  
			return this.optional(element)||(checkName.test(value));  
		},"*只允许3-50位英文字母、数字或者下划线！");  
	}
	function setCache(key,value,expires){
		try {
			value = JSON.stringify(value);
		} catch (e) {}
		expires = expires?expires:86400*5;	//默认5天
		BrowserStorage.api.set({
			"key" : key,
			"value" : value,
			"expires" : expires // Number：(ms)有效期为一天
		});
	}
	
	window.Cache = {
		set:function(key,value,expires){
			try {
				value = JSON.stringify(value);
			} catch (e) {}
			expires = expires?expires:86400*1000*5;	//默认5天
			BrowserStorage.api.set({
				"key" : key,
				"value" : value,
				"expires" : expires // Number：(ms)有效期为一天
			});
		},
		get:function(key){
			var value = BrowserStorage.api.get(key);
			if(value && value.value){
				value = value.value;
				try {
					value = JSON.parse(value);
				} catch (e) {}
			}else{
				value = null;
			}
			return value;
		},
		clear:function(){
			BrowserStorage.api.clearAll();
		}
	}
	
	//自动将页面上包含data_code的select下值值补全
	if($("select[data_code]").fselect){
		$("select[data_code]").fselect("joinAttrValues");
	}
	//初始化layer的一些全局变量，比如出场动画
	layer.config({
		anim: 2,		//动画
	});

	//设置ajax请求的默认值
	$.ajaxSetup({
		//将所有后台错误，集中到此来进行展示
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			var status = XMLHttpRequest.status;
			var responseText = XMLHttpRequest.responseText;
			var tipsHtml = "<div class=''>系统在请求服务器过程中出现异常"+status+",请联系系统管理员！</div>"
			layer.alert(tipsHtml,{
				type : 0,
				title : '系统请求异常',
				shade : 0,		//不用遮罩层
				offset : 'rb',	//右下角
				area : [ '300px', '175px' ],
				//time:5000,
				//maxmin:true,	//最大最小化按钮
				icon: 5,
				btn:['查看详细错误信息'],			//不要按钮
				btn1:function(index, layero){
					var index2 = layer.open({
						type : 1,
						title : '系统异常错误信息',
						shade : 0,		//不用遮罩层
						maxmin:true,	//最大最小化按钮
						content:responseText
					});
					//最大化详细错误信息窗口
					layer.full(index2);
					//关闭当前提示窗口
					layer.close(index);
				}
			});
			
		}
	});
});