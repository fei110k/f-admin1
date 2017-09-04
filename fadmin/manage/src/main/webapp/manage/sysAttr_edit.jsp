<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<title>字典管理</title>
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="AttrEditForm">
		<div class="cl pd-5 bg-1 bk-gray mb-20">
			<a class="btn btn-success radius" href="javascript:window.history.go(-1);" >
				<i class="Hui-iconfont">&#xe66b;</i> 返回列表
			</a>
			<button class="btn btn-primary radius" type="submit">
				<i class="Hui-iconfont">&#xe632;</i> 保存修改
			</button>
		</div>
		<div class="panel panel-default">
			<div class="panel-header">
				基本信息
			</div>
			<div class="panel-body">
				<div class="row cl">
					<label class="form-label col-xs-3">字典编码<span class="c-red">*</span>：</label>
					<div class="formControls col-xs-9">
						<input type="text" class="input-text" value="" placeholder="" id="attr_code" name="attr_code">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">字典名称<span class="c-red">*</span>：</label>
					<div class="formControls col-xs-9">
						<input type="text" class="input-text" value="" placeholder="" id="attr_name" name="attr_name">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">状态<span class="c-red">*</span>：</label>
					<div class="formControls col-xs-9">
						<span class="select-box">
							<select class="select" name="struts" size="1">
								<option value="0">有效</option>
								<option value="1">无效</option>
							</select>
						</span>
					</div>
				</div>
				<input type="hidden" name="edit_type" id="edit_type">
				<input type="hidden" name="attr_id" id="attr_id">
				
				<input type="hidden" name="attr_values" id="attr_values">
				
			</div>
		</div>
		<table id="sysAttrValueTable" class="table table-border table-bordered table-bg table-hover mt-20">
			<thead>
				<tr>
					<th colspan="3">
						下拉属性
						<div class="r">
							值：<input type="text" style="width:200px" class="input-text" id="add_attr_value">
							显示名称：<input type="text" style="width:200px" class="input-text" id="add_attr_value_name">
							<button class="btn btn-primary radius size-S" type="button" onclick="addAttrValue()">
								<i class="Hui-iconfont">&#xe600;</i> 添加下拉值
							</button>
						</div>
					</th>
				</tr>
				<tr>
					<th>值</th>
					<th>显示名称</th>
					<th width="30">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</form>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript" src="js/sysAttr_edit.js"></script>
</body>
</html>