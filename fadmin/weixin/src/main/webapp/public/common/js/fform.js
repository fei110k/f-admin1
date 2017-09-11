(function ($){
	$.fn.fform = function(options, params){
		var _self = this;
		var method = $.fn.fform.methods[options];
		if($.isFunction(method)){
			return method(_self, params);
		}
	}
	
	$.fn.fform.methods={
		setData:function(jq,data){
			var getData = function(dataMap,obj){
				var name = obj.attr("name");
				if(!name){
					return "";
				}
				name = name.split(".");
				if(name.length > 1){
					name = name[name.length-1];
				}
				var val = dataMap[name];
				return val;
			};
			var textInputs = jq.find("input:not(:button):not(:submit)");
			for(var i=0;i < textInputs.length ; i++){
				var obj = $(textInputs[i]);
				obj.val(getData(data,obj));
			}
			
			var selects = jq.find("select");
			for(var i=0;i < selects.length ; i++){
				var obj = $(selects[i]);
				obj.val(getData(data,obj));
			}
		},
		getData:function(jq,data){
			var d = {};
		    var t = jq.serializeArray();
		    $.each(t, function() {
		      d[this.name] = this.value;
		    });
		    return d;
		},
		setDisabled:function(jq,data){
			jq.find("input:not(:button)").attr("disabled",true);
			jq.find("textarea").attr("disabled",true);
			
			jq.find("select").attr("disabled",true);
			jq.find("select").parent().removeClass("select-disabled").addClass("select-disabled");
		},
		removeDisabled:function(jq,data){
			jq.find("input:not(:button)").attr("disabled",false);
			jq.find("textarea").attr("disabled",false);
			
			jq.find("select").attr("disabled",false);
			jq.find("select").parent().removeClass("select-disabled");
		}
	}
})(jQuery);