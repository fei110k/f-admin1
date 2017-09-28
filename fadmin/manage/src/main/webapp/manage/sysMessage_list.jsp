<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<title>我的消息</title>
</head>
<body>
<div class="pd-20">
  <div class="text-c mb-20">
    <input type="text" class="input-text" style="width:250px" placeholder="输入角色名称模糊查询" id="role_name">
    <button type="button" class="btn btn-success" onclick="querySysRole()"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
  </div>
  
  <table id="sysMessageDataTable"></table>
  
</div>

<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/sysMessage_list.js"></script>
</body>
</html>