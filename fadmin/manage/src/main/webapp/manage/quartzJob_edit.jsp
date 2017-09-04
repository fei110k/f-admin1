<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<style type="text/css">
.Huialert{
	margin: 3px 0px;
	padding: 4px;
	background: white;
    border-color: #ddd;
    color: #31708f;
}
.Huialert .Hui-iconfont{top: 4px;}
.input-button{
	position: absolute;right:15px;top: 0px;
}
</style>
<title>菜单管理</title>
</head>
<body>
<div>
	<div class="col-xs-12">
		<form class="form form-horizontal" id="jobEditForm">
			<div class="row cl">
				<label class="form-label col-xs-3">任务名称<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" id="job_name" name="job_name"
					placeholder="任务名称必须唯一,添加后不可修改" title="任务名称必须唯一,添加后不可修改" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">任务处理类<span class="c-red">*</span>：</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" id="job_class_name" name="job_class_name"
						placeholder="任务处理类不能为空,添加后不可修改" title="任务处理类不能为空,添加后不可修改">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3">
					cron表达式<span class="c-red">*</span>：
				</label>
				<div class="formControls col-xs-9">
					<input type="text" class="input-text" placeholder="cron表达式" 
						id="cron_expression" name="cron_expression">
				</div>
			</div>
			<input type="hidden" value="" name="edit_type" id="edit_type">
			<input type="hidden" id="job_group" name="job_group">
			<input type="hidden" id="trigger_name" name="trigger_name">
			<div class="row cl">
				<label class="form-label col-xs-3">常见cron表达式
					<a href="javascript:void(0);" title="点击查看更多cron表达式类型" onclick="openCron()">
						<i class="Hui-iconfont">&#xe633;</i>
					</a>
					：
				</label>
				<div class="formControls col-xs-9">
					<pre>
*/5 * * * * ?         每隔5秒执行一次
0 */1 * * * ?         每隔1分钟执行一次
0 0 23 * * ?          每天23点执行一次
0 0 1 1 * ?           每月1号凌晨1点执行一次
0 26,29,33 * * * ?    在26分、29分、33分执行一次
0 15 10 ? * MON-FRI   每个周一、周二、周三、周四、周五的10：15触发
					</pre>
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<!-- 树控件JS文件 -->

<script type="text/javascript" src="js/quartzJob_edit.js"></script>
</body>
</html>