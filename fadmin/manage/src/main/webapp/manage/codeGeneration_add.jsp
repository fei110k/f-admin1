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
	<div class="row Huialert Huialert-info">
		数据库：<span id="table_schema_span"></span>
		表名：<span id="table_name_span"></span>
	</div>
  	<form class="form form-horizontal" id="codeGenerationForm">
  		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">
				项目路径：
			</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="code_path" name="code_path" 
					autocomplete="off" placeholder="必须先输入项目路径" value="E:\workspaces\fadmin\fadmin">
			</div>
		</div>
		
  		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">
				<input type="checkbox" name="generate_vo" value="true">
				是否生成VO：
			</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="vo_path" name="vo_path" autocomplete="off" placeholder="VO生成路径">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">
				<input type="checkbox" name="generate_bo" value="true">
				是否生成BO：
			</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="bo_path" name="bo_path" autocomplete="off" placeholder="BO生成路径">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">
				<input type="checkbox" name="generate_controller" value="true">
				是否生成Controller：
			</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="controller_path" name="controller_path" 
				autocomplete="off" placeholder="Controller生成路径">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">
				<input type="checkbox" name="generate_html" value="true">
				是否生成HTML及JS：
			</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="html_path" name="html_path" 
				 autocomplete="off" placeholder="HTML及JS生成路径">
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		
		<input name="table_name" id="table_name" type="hidden">
		<input name="table_schema" id="table_schema" type="hidden">
  	</form>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<script type="text/javascript" src="js/codeGeneration_add.js"></script>
</body>
</html>
