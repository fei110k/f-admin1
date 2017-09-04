<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<title>代码生成</title>
</head>
<body>
<div class="pd-20">
  <div class="text-c">
	数据库：
	<input type="text" id="table_schema" value="fadmin" class="input-text" style="width:250px" placeholder="数据库名称" >
	表名：
    <input type="text" id="table_name" value="" class="input-text" style="width:250px" placeholder="数据库名称" >
    <button type="button" class="btn btn-success" id="" name="" onclick="queryAllTable()">
    	<i class="icon-search"></i>搜索
    </button>
  </div>
  
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l"><a href="javascript:;" onclick="openCodeGeneration()" class="btn btn-primary radius">生成代码</a>
  </div>
  
  <table id="dataTable"></table>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/codeGeneration_list.js"></script>
</body>
</html>
