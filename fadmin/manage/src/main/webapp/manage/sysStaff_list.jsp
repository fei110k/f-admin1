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
  <div class="text-c">
    <input type="text" class="input-text" style="width:250px" placeholder="输入全账号或者部分名称模糊查询" id="staff_name">
    <button type="button" class="btn btn-success" onclick="querySysStaff()"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <a href="javascript:;" onclick="addSysStaff()" class="btn btn-primary radius">
    	<i class="Hui-iconfont">&#xe600;</i>添加用户
    </a>
    <a href="javascript:;" onclick="editSysStaff()" class="btn btn-success radius">
    	<i class="Hui-iconfont">&#xe60c;</i>查看或编辑
    </a>
    <a href="javascript:;" onclick="deleteSysStaff()" class="btn btn-danger radius">
    	<i class="Hui-iconfont">&#xe6e2;</i>删除用户
    </a>
  </div>
  
  <table id="sysStaffDataTable"></table>
  
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/sysStaff_list.js"></script>
</body>
</html>