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
})