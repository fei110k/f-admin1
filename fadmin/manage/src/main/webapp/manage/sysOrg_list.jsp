<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/base" prefix="base"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<!-- 树控件样式 -->
<link rel="stylesheet" href="/public/lib/H-ui.admin/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

<title>组织管理</title>
</head>
<body>
<div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<base:button code="SYS_ORG_ADD">
			<a href="javascript:;" onclick="addSysOrg()" class="btn btn-primary radius">
				<i class="Hui-iconfont">&#xe600;</i>新增
			</a>
		</base:button>
		<base:button code="SYS_ORG_EDIT">
			<a href="javascript:;" onclick="editSysOrg()" class="btn btn-success radius">
				<i class="Hui-iconfont">&#xe60c;</i>修改
			</a>
		</base:button>
		<base:button code="SYS_ORG_DEL">
			<a href="javascript:;" onclick="deleteSysOrg()" class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i>删除
			</a>
        </base:button>
		<span class="c-red">提示：请先选择一个组织再进行操作！</span>
	</div>
	<div class="cl">
		<div class="col-xs-3">
			<ul id="orgTree" class="ztree"></ul>
		</div>
		<div class="col-xs-9">
			<form class="form form-horizontal" id="OrgForm">
				<div class="row cl">
					<label class="form-label col-xs-3">组织名称：</label>
					<div class="formControls col-xs-9">
						<input type="text" class="input-text" value="" placeholder="" id="org_name" name="org_name">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">状态：</label>
					<div class="formControls col-xs-9">
						<span class="select-box">
							<select class="select" name="status" size="1">
								<option value="0">有效</option>
								<option value="1">无效</option>
							</select>
						</span>
					</div>
				</div>
			</form>
			<form class="form form-horizontal" id="BtnShowForm" style="display: none;">
				<div class="row cl">
					<label class="form-label col-xs-3">按钮名称：</label>
					<div class="formControls col-xs-9">
						<input type="text" class="input-text" value="" placeholder="" id="name" name="name">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">按钮编码：</label>
					<div class="formControls col-xs-9">
						<input type="text" class="input-text" id="btn_code" name="btn_code">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">状态：</label>
					<div class="formControls col-xs-9">
						<span class="select-box">
							<select class="select" name="status" size="1">
								<option value="0">有效</option>
								<option value="1">无效</option>
							</select>
						</span>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->
<script type="text/javascript" src="/public/lib/H-ui.admin/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 

<script type="text/javascript" src="js/sysOrg_list.js"></script>
</body>
</html>