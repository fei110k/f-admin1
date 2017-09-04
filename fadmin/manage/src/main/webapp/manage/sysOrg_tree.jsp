<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<!-- 树控件样式 -->
<link rel="stylesheet" href="/public/lib/H-ui.admin/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

<title>菜单管理</title>
</head>
<body>
<div>
	<ul id="orgTree" class="ztree"></ul>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 

<script type="text/javascript">
function initOrgTree() {
	$.ajax({
		url :"/SysOrg/findSysOrg.do",
		type:"post",
		dataType:"json",
		success : function(data) {
			$.fn.zTree.init($("#orgTree"), setting, data);
		}
	});
}
var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "org_id",
            pIdKey: "parent_id",
            rootPId: ""
		},
		key:{name:"org_name"}
	}
}

function callback(){
	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	var nodes = treeObj.getSelectedNodes();
	if(nodes && nodes.length > 0){
		return nodes[0];
	}
	return null;
}

$(function(){
	initOrgTree();
});
</script>
</body>
</html>