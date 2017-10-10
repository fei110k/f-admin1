<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>

<title></title>
</head>
<body>
<!-- <div class="cl bg-2 pd-5">
	<a class="btn btn-success radius" href="javascript:window.history.go(-1);" >
		<i class="Hui-iconfont">&#xe66b;</i> 返回
	</a>
</div> -->
<div class="cl bg-1 bk-gray pd-10">
	发件人：<strong id="receive_staff_name"></strong><br/>
	时&nbsp;&nbsp;&nbsp;间：<span id="receive_time"></span><br/>
</div>
<!-- 消息正文 -->
<div class="pd-10" id="msg_content"></div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/sysMessage_query.js"></script>
</body>
</html>