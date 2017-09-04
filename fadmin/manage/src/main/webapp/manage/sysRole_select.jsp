<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<title>用户管理</title>
</head>
<body>
<div class="pd-20">
  <div class="text-c" style="margin-bottom: 10px;">
    <input type="text" class="input-text" style="width:250px" placeholder="可输入部分角色名称模糊查询" id="role_name">
    <button type="button" class="btn btn-success" onclick="querySysRole()"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
  </div>
  <table id="sysRoleDataTable"></table>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
function initSysRoleDataTable(){
	var role_name = $("#role_name").val();
	$("#sysRoleDataTable").ftable({
		url:"/SysRole/findSysRole.do",
		param:{role_name:role_name},
		singleSelect:true,
		pageSize:5,
		onClickRow:function(e,rowId,rowData){
			$("#sysRoleDataTable").ftable("selectRow", {
				rowId : rowId
			});
		},
		columns:[
			{name:"ID",data:"role_id",hidden:true},
			{name:"角色名",data:"role_name"}
		]
	});
}

function callback(){
	var rows = $("#sysRoleDataTable").ftable("getSelected");
	return rows;
}
$(function(){
	initSysRoleDataTable();
});
</script>
</body>
</html>