(function ($){
	$.fn.ftable = function(options, params){
		var _self = this;
		
		//执行方法
		if(typeof options == "string"){
			if(!_self.data("ftable")){
				//没初始化，不执行
				return null;
			}
			
			var method = $.fn.ftable.methods[options];
			if($.isFunction(method)){
				return method(_self, params);
			}

			return null;
		}
		
		//覆盖默认属性
		options = $.extend({}, $.fn.ftable.defaults, options);
		options.isinit = true;
		if (!_self.data("ftable")) {
			options.isinit = false;
			_self.data("ftable",{options:options});
		}
		if (!options.isinit) {
			_self.addClass("table table-border table-bordered table-hover table-bg table-sort");
		}
		
		var singleSelect = options.columns;
		//先清空table中的数据
		//_self.html("");
		/**************************************组装thead开始***************************************************/
		if (!options.isinit) {
			$.fn.ftable.methods.assembleThead(_self,options);
		}
		/**************************************组装thead结束***************************************************/
		
		var tableid = _self.attr("id");
		
		if (!options.isinit) {
			//添加fpage.js需要的初始化元素
			_self.after("<div id=\""+tableid+"_pager_div\"></div>");
		}
		
		
		/**************************************组装tbody开始***************************************************/
		if (!options.isinit) {
			tbodyHtml = "<tbody></tbody>";
			_self.append(tbodyHtml);
		}
		
		//使用fpage.js来实现分页功能
		//将用户的onLoad方法重新重写，在把数据加载到table后再进行回调
		var tableOnLoad = options.onLoad;
		options.onLoad = function(data){
			_self.find("tbody").html("");
			//每次翻页时，将全选去掉
			_self.find("input:checked").prop("checked",false);
			
			var fdata = _self.data("ftable");
			fdata.data = data;
			_self.data("ftable",fdata);
			
			if(data.list){
				for (var i = 0; i < data.list.length; i++) {
					var dataRow = data.list[i];
					$.fn.ftable.methods.assembleTbody(_self,options,dataRow);
				}
			}
			tableOnLoad(data);
		}
		
		$("#"+tableid+"_pager_div").fpage(options);
		
		
	}
	
	$.fn.ftable.methods={
		getAttrValue:function(data,value){
			if(data && data.attrValues && data.attrValues.length > 0){
				for (var i = 0; i < data.attrValues.length; i++) {
					if(value == data.attrValues[i].attr_value){
						return data.attrValues[i].attr_value_name;
					}
				}
			}
			return null;
		},
		assembleThead:function(_self,options){
			var singleSelect = options.singleSelect;
			var rowClass = options.rowClass;	//一行的样式
			/**************************************组装thead开始***************************************************/
			var theadHtml = "<thead><tr class='"+rowClass+"'>";
			
			var columns = options.columns;
			for (var i = 0; i < columns.length; i++) {
				
				//如果是在第一列的时候，在第一列前边加上单选或者复选框
				if (i == 0) {
					if(singleSelect){
						theadHtml +="<th width=\"10\"></th>";
					}else{
						theadHtml +="<th width=\"10\"><input type=\"checkbox\"/></th>";
					}
				}
				
				var column = columns[i];
				
				var className = column.className;	//设置列的class属性值
				var name = column.name;				//设置列的描述性名称
				var data = column.data;				//设置列取值的数据库字段名
				var hidden = column.hidden;			//是否隐藏  true/false，默认为false
				var defaultContent = column.defaultContent;	//设置列如果为空的时候，默认值
				var data_code = column.data_code;				//数据库字典表的Key
				var dataformat = column.dataformat;	//是一个function类型的参数，数据转换，或者特殊处理的方法
				var width = column.width;
				//如果要隐藏，直接就不展示了
				if (hidden == true) {
					continue;
				}
				
				if(data_code && data_code != ""){
					var value = Cache.get(data_code);
					if(!value){
						$.ajax({
							url: "/SysAttr/findSysAttrByCodes.do",
							data: {data_code:data_code},
							dataType:"json",
							type:"post",
							async:false,
							success: function(data){
								Cache.set(data_code,data);
							}
						});
					}
				}
				var widthHtml = width?"width=\""+width+"\"":"";
				var th_class = className?"class=\""+className+"\"":"";
				theadHtml +="<th "+th_class+" "+widthHtml+">"+column.name+"</th>"
			}
			
			theadHtml += "</tr></thead>";
			/**************************************组装thead结束***************************************************/
			_self.append(theadHtml);
			_self.find("tr:first input:first").click(function(){
				var _checked = $(this).is(":checked");
				var trs = $(this).parent().parent().parent().parent().find("tr");
				for (var i = 1; i < trs.length; i++) {
					var tr = $(trs[i]);
					tr.find("input:first").prop("checked",_checked);
					
					if (_checked) {
						tr.removeClass("active").addClass("active");
					}else{
						tr.removeClass("active");
					}
				}
				
			});
		},
		assembleTbody:function(_self,options,rowData){
			/**************************************组装tbody开始***************************************************/
			var tableId = _self.attr("id");
			var checkName = tableId+"_ck_n"
			var columns = options.columns;
			var rowClass = options.rowClass;	//一行的样式
			var rowId = null;
			var tr = "";
			for (var i = 0; i < columns.length; i++) {
				
				var column = columns[i];
				
				var className = column.className;	//设置列的class属性值
				var name = column.name;				//设置列的描述性名称
				var data = column.data;				//设置列取值的数据库字段名
				var hidden = column.hidden;			//是否隐藏  true/false，默认为false
				var defaultContent = column.defaultContent;	//设置列如果为空的时候，默认值
				var data_code = column.data_code;				//数据库字典表的Key
				var dataformat = column.dataformat;	//是一个function类型的参数，数据转换，或者特殊处理的方法
				
				var data = rowData[column.data];
				
				//如果是在第一列的时候，在第一列前边加上单选或者复选框
				if (i == 0) {
					rowId = data;
					tr += "<tr class='"+rowClass+"' id='"+data+"_tr'>"
					tr +="<td width=\"10\"><input type=\"checkbox\" name=\""+checkName+"\" rowId='"+rowId+"'/></td>"
				}
				
				//如果要隐藏，直接就不展示了
				if (hidden == true) {
					continue;
				}
				
				if ((data == undefined || data == null) && defaultContent) {
					data = defaultContent;
				}
				if (typeof dataformat == "function") {
					data = dataformat(data,rowData);
				}
				if(data_code){
					var value = Cache.get(data_code);
					data = this.getAttrValue(value,data);
				}
				var td_class = className?"class=\""+className+"\"":"";
				data = data?data:"";
				tr +="<td "+td_class+">"+data+"</td>";
			}
			tr += "</tr>";
			_self.find("tbody").append(tr);
			_self.find("tbody tr:last input[rowId='"+rowId+"']").click(function(){
				var ck = $(this);
				_self.ftable("selectRow",{rowId:ck.attr("rowId"),checkboxClick:true});
			});
			
			_self.find("tbody tr:last").click(function(){
				var ck = $(this).find("input[type='checkbox']");
				_self.ftable("selectRow",{rowId:ck.attr("rowId"),fromClick:true,checkboxClick:true});
				options.onClickRow(this,rowId,rowData);
			});
			
			_self.find("tbody tr:last").data("rowData",rowData);
			
			/**************************************组装tbody结束***************************************************/
		},
		selectRow:function(jq,params){
			var rowId = params.rowId;
			var fromClick = params.fromClick;	//从单击一行过来的事件
			var ck = jq.find("#"+rowId+"_tr input:first");
			if(ck.length == 0 ){
				return ;
			}
			var options = jq.data("ftable").options;
			var singleSelect = options.singleSelect;
			var checkBoxChecked = ck.is(":checked");
			if (singleSelect) {
				jq.find("input[name$='_ck_n']").prop("checked",false);
				jq.find("tr").removeClass("active");
				if(fromClick || checkBoxChecked){
					ck.prop("checked",true);
					ck.parent().parent().removeClass("active").addClass("active");
				}
			}else{
				//去掉全选
				jq.find("input[type='checkbox']:first").prop("checked",false);
			}
			if(params && params.checkboxClick){
				if(ck.is(":checked")){
					ck.parent().parent().removeClass("active").addClass("active");
				}else{
					ck.parent().parent().removeClass("active");
				}
			}else{
				ck.prop("checked",true);
				ck.parent().parent().removeClass("active").addClass("active");
			}
		
			if (typeof options.onSelect == "function") {
				var rowData = ck.parent().parent().data("rowData");
				options.onSelect(ck[0],rowId,rowData);
			}
		},
		getSelected:function(jq,params){
			var cks = jq.find("input[name$='_ck_n']:checked");
			var datas = [];
			for (var i = 0; i < cks.length; i++) {
				var tr = $(cks[i]).parent().parent();
				var rowData = tr.data("rowData");
				datas.push(rowData);
			}
			return datas;
		}
	}
	
	//默认属性
	$.fn.ftable.defaults = $.extend({}, {
		url : "",//请求的SPRING注解方法
		param : {},//AJAX调用时传递的参数
		async:true,//默认为异步请求true/false
		dataType:"json",//默认返回的数据类型为json,目前也只支持json
		type:"post",	//使用post请求数据
		onLoad : function(container){},//加载数据完毕时触发的事件，参数container为表格本身的jQuery对象
		visiblePages:5,//分页插件最多展示多少页按钮
		totalPages:1,//默认初始总页数
		pageNum:1,//默认初始选中第几页
		pageSize:10,//默认每一页10条数据
		
		
		columns : [],//定义表头对象，如果指定的TABLE标签内有表头<thead>，则以<thead>为准，否则以此生成。如果没有<thead>也没有指定column则会出错
		singleSelect : true,//是否单选，false则加入选择框checkbox
		rowClass:"text-c",
		onSelectClass : null,//记录被选中时呈现的样式，应用于TR标签，如果不配置则默认
		
		onSelect : function(e,rowId,rowData){},//选中记录时触发的事件
		onClickRow:function(e,rowId,rowData){},
		onLoad : function(data){},//加载数据完毕时触发的事件，参数container为表格本身的jQuery对象
	});
	
})(jQuery);