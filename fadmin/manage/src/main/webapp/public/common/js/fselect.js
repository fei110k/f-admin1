/**
 * 根据data_code从后台获取数据
 * 调用方式：
 * 1.调用对象必须为select
 * 2.对象必须包含data_code属性
 * 3.调用代码   $("#select").fselect();
 * @param $
 */
(function ($){
	$.fn.fselect = function(options, params){
		var _self = this;
		var method = $.fn.fselect.methods[options];
		if($.isFunction(method)){
			return method(_self, params);
		}
		
		$.fn.fselect.methods.joinAttrValue(_self,options);
	}
	
	$.fn.fselect.methods={
		joinHtml:function(jq,options,data){
			
			$(jq).html("");
			if(data && data.attrValues && data.attrValues.length > 0){
				for (var i = 0; i < data.attrValues.length; i++) {
					var html = "<option value=\""+data.attrValues[i].attr_value+"\">"+data.attrValues[i].attr_value_name+"</option>";
					$(jq).append(html);
				}
			}
		},
		//
		joinAttrValue:function(jq,options,param){
			//覆盖默认属性
			options = $.extend({}, $.fn.fselect.defaults, options);
			
			var data_code = $(jq).attr("data_code");
			if(!data_code){
				return;
			}
			options.data = {data_code:data_code};
			
			var value = Cache.get(data_code);
			if(value){
				$.fn.fselect.methods.joinHtml(jq,options,value);
				return;
			}
			$.ajax({
				url: options.url,
				data: options.data,
				dataType:"json",
				type:"post",
				async:options.async,
				success: function(data){
					$.fn.fselect.methods.joinHtml(jq,options,data);
					Cache.set(data_code,data);
				}
			 });
		},
		//获取多个select，一次性将多个select的值传到后台再来进行组装
		joinAttrValues:function(jq,param){
			var selects = $(jq);
			if(!selects || selects.length == 0){
				return;
			}
			var data_codes = [];
			for (var i = 0; i < selects.length; i++) {
				var data_code = $(selects[i]).attr("data_code");
				if(data_code){
					var value = Cache.get(data_code);
					if(value){
						$.fn.fselect.methods.joinHtml(jq,$.fn.fselect.defaults,value);
					}else{
						data_codes.push(data_code);
					}
				}
			}
			
			if(data_codes.length == 0){
				return;
			}
			
			data_codes = $.unique(data_codes);
			
			$.ajax({
				url: "/SysAttr/findSysAttrByCodes.do",
				data: {data_codes:data_codes.toString()},
				dataType:"json",
				type:"post",
				async:false,	//采用同步方式请求后台
				success: function(data){
					if(data && data.length > 0){
						for (var i = 0; i < data.length; i++) {
							var jq = $("select[data_code='"+data[i].attr_code+"']");
							Cache.set(data[i].attr_code,data[i]);
							$.fn.fselect.methods.joinHtml(jq,$.fn.fselect.defaults,data[i]);
						}
					}
				}
			 });
		}
	}
	
	//默认属性
	$.fn.fselect.defaults = $.extend({}, {
		url : "/SysAttr/findSysAttrByCode.do",//请求的SPRING注解方法
		async:true,//默认为异步请求true/false
	});
})(jQuery);