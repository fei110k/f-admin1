<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../public/common/jsp/include-up.jsp"></jsp:include>
<title>CRON表达式参考</title>
</head>
<body>
<div class="page-container">
	<pre>
	*/5 * * * * ?     每隔5秒执行一次
	0 */1 * * * ?    每隔1分钟执行一次
	0 0 12 * * ?    每天中午十二点触发
	0 0 23 * * ?    每天23点执行一次
	0 0 1 * * ?    每天凌晨1点执行一次
	0 0 1 1 * ?    每月1号凌晨1点执行一次
	0 0 23 L * ?    每月最后一天23点执行一次
	0 0 1 ? * L    每周星期天凌晨1点实行一次
	0 26,29,33 * * * ?    在26分、29分、33分执行一次
	0 0 0,13,18,21 * * ?    每天的0点、13点、18点、21点都执行一次
	0 15 10 ? * *    每天早上10：15触发
	0 15 10 * * ?    每天早上10：15触发
	0 15 10 * * ? *    每天早上10：15触发
	0 15 10 * * ? 2005    2005年的每天早上10：15触发
	“0 * 14 * * ?    每天从下午2点开始到2点59分每分钟一次触发
	0 0/5 14 * * ?    每天从下午2点开始到2：55分结束每5分钟一次触发
	0 0/5 14,18 * * ?    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
	0 0-5 14 * * ?    每天14:00至14:05每分钟一次触发
	0 10,44 14 ? 3 WED    三月的每周三的14：10和14：44触发
	0 15 10 ? * MON-FRI    每个周一、周二、周三、周四、周五的10：15触发
	</pre>
</div>
<jsp:include page="../public/common/jsp/include-down.jsp"></jsp:include>
</body>
</html>