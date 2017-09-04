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
    <input type="text" class="input-text" style="width:250px" placeholder="输入角色名称模糊查询" id="role_name">
    <button type="button" class="btn btn-success" onclick="querySysRole()"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <a href="javascript:;" onclick="addSysRole()" class="btn btn-primary radius">
    	<i class="Hui-iconfont">&#xe600;</i>添加角色
    </a>
    <a href="javascript:;" onclick="editSysRole()" class="btn btn-success radius">
    	<i class="Hui-iconfont">&#xe60c;</i>编辑角色
    </a>
    <a href="javascript:;" onclick="deleteSysRole()" class="btn btn-danger radius">
    	<i class="Hui-iconfont">&#xe6e2;</i>删除角色
    </a>
    <a href="javascript:;" onclick="editRolePrivilege()" class="btn btn-secondary radius">
    	<i class="Hui-iconfont">&#xe63c;</i>角色赋权
    </a>
  </div>
  
  <table id="sysRoleDataTable"></table>
  
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/sysRole_list.js"></script>
</body>
</html>