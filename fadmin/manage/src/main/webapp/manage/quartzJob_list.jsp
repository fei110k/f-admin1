<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<title>定时任务管理</title>
</head>
<body>
<div class="pd-20">
  <div class="text-c">
    <input type="text" class="input-text" style="width:250px" placeholder="输入全账号或者部分名称模糊查询" id="job_name">
    <span class="select-box" style="width: 150px;">
		<select class="select" name="struts" size="1" data_code="JOB_STATE"></select>
	</span>
    <button type="button" class="btn btn-success" onclick="queryQuartzJob()"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <a href="javascript:;" onclick="addJob()" class="btn btn-primary radius">
    	<i class="Hui-iconfont">&#xe600;</i>添加任务
    </a>
    <a href="javascript:;" onclick="editJob()" class="btn btn-success radius">
    	<i class="Hui-iconfont">&#xe60c;</i>修改任务
    </a>
    <a href="javascript:;" onclick="deleteJob()" class="btn btn-danger radius">
    	<i class="Hui-iconfont">&#xe6e2;</i>删除任务
    </a>
    <a href="javascript:;" onclick="pauseJob()" class="btn btn-warning radius">
    	<i class="Hui-iconfont">&#xe6e5;</i>暂停
    </a>
    <a href="javascript:;" onclick="resumeJob()" class="btn btn-secondary radius">
    	<i class="Hui-iconfont">&#xe6e6;</i>恢复
    </a>
    <a href="javascript:;" onclick="triggerJob()" class="btn btn-primary radius">
    	<i class="Hui-iconfont">&#xe601;</i>立即执行
    </a>
  </div>
  
  <table id="quartzJobDataTable"></table>
  
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/quartzJob_list.js"></script>
</body>
</html>