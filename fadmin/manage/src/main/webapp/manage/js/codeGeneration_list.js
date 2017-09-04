function queryAllTable() {
	var table_schema = $("#table_schema").val();
	if (!table_schema) {
		return;
	}
	var table_name = $("#table_name").val();
	$("#dataTable").ftable({
		url : "/CodeGeneration/queryAllTable.do",
		param : {
			table_schema : table_schema,
			table_name:table_name
		},
		// singleSelect:false,
		pageSize : 10,
		onLoad : function(data) {
		},
		onSelect : function(e, rowId, rowData) {
		},
		onClickRow : function(e, rowId, rowData) {
		},
		columns : [ {
			name : "表名",
			data : "table_name"
		}, {
			name : "表类型",
			data : "table_type"
		}, {
			name : "数据库",
			data : "table_schema"
		} ]
	});
}

function openCodeGeneration(){
	var rows = $("#dataTable").ftable("getSelected");
	if(!rows || rows.length == 0){
		alert('请行选中一个数据库表再进行代码生成！');
		return;
	}
	var url = "codeGeneration_add.jsp?table_name="+rows[0].table_name+"&table_schema="+rows[0].table_schema;
	layer_show("生成代码",url,800,410);
}