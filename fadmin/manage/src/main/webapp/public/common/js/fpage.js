/**
 * 通过jquery的分页插件：jqPaginator动态插表数据
 * 相对于ftable.js，此插件只实现了分页功能，可以更灵活的添加表数据，而对table的样式无任何要求
 * 要使用此插件必须在此插件前引入
 * 
 * 	<script type="text/javascript" src="/public/lib/page/jqPaginator.js"></script> 
	<link type="text/css" rel="stylesheet" href="/public/common/css/common.css"/>
 * 使用方法：
 *     1.申明一个DIV：
 *         <div id="pager_div1"></div>
 *     2.在JS中调用（可传入的参数见$.fn.fpage.defaults）：
    	$("#pager_div1").fpage({
			url:"/SysStaff/querySysStaff",
			param:{name:"张三"},	//在请求中要传到后台的值
			onLoad:function(data){
				console.log(data.list);
			}
		});
		
 * @param $
 */
(function ($){
	$.fn.fpage = function(options, params){
		var _self = this;
		
		//覆盖默认属性
		options = $.extend({}, $.fn.fpage.defaults, options);
		if (!_self.data("fpage")) {
			_self.data("fpage",{options:options});
		}
		//添加关键性的分页插件ul
		var page_div_id = _self.attr("id");
		_self.removeClass("pagination_parent");
		_self.addClass("pagination_parent");
		_self.html("查询结果数据 <strong id=\""+page_div_id+"_strongstrong\" style=\"color:#dd514c;\">0</strong> 条" +
				"<ul class=\"pagination\" id=\""+page_div_id+"_ulul\"></ul>");
		
		var jqPage = $(_self.find("ul:first"));
		
		jqPage.jqPaginator({
    	    totalPages: options.totalPages,
    	    visiblePages: options.visiblePages,
    	    currentPage: options.pageNum,
            first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev"><a href="javascript:void(0);">上一页<\/a><\/li>',
            next: '<li class="next"><a href="javascript:void(0);">下一页<\/a><\/li>',
            last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
    	    onPageChange: function (num) {
    	        param = options.param;
    	        param.pageNum = num;	//添加第几页参数
    	        param.pageSize = options.pageSize;
    	        $.ajax({
    			    url: options.url,    //向springboot请求数据的url
    			    data: param,
    			    type: options.type,
    			    dataType:options.dataType,
    			    async:options.async,
    			    
    			    success: function (data) {
    			    	var currentPage = data.pageNum?data.pageNum:1;
    			    	var totalPages = data.pages?data.pages:1;
    			    	//设置分页参数
    			    	jqPage.jqPaginator('option', {
    			    	    currentPage: currentPage,	//设置当前选中第几页
    			    	    totalPages:totalPages		//设置当前一共有几页
    			    	});
    			    	jqPage.parent().find("#"+page_div_id+"_strongstrong").html(data.total);
    			    	options.onLoad(data);
    			    }
    			});
    	    }
    	});
		
	}
	
	//默认属性
	$.fn.fpage.defaults = $.extend({}, {
		url : "",//请求的SPRING注解方法
		param : {},//AJAX调用时传递的参数
		async:true,//默认为异步请求true/false
		dataType:"json",//默认返回的数据类型为json,目前也只支持json
		type:"post",	//使用post请求数据
		visiblePages:5,//分页插件最多展示多少页按钮
		totalPages:1,//默认初始总页数
		pageNum:1,//默认初始选中第几页
		pageSize:10,//默认每一页10条数据
		onLoad : function(container){},//加载数据完毕时触发的事件，参数container为表格本身的jQuery对象
	});
	
})(jQuery);