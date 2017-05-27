<%-- <%@page import="javax.jms.Session"%> --%>
<%@ page language="java" import="java.util.*"
	import="java.text.SimpleDateFormat"
	contentType="text/html;charset=gb2312" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'sensor.jsp' starting page</title>
<meta http-equiv="refresh" content="3; url=servlet/SensorServ">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<body>
	<h1>传感器</h1>
	<%!SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
	<%
		request.setCharacterEncoding("gb2312");
		if (session.getAttribute("login") == null) {
			session.setAttribute("login", "disconnect");
		}
		if (session.getAttribute("alarm") == null) {
			session.setAttribute("alarm", "true");
		}
		if (request.getParameter("set_threshold") != null) {
			session.setAttribute("set_threshold",
					request.getParameter("set_threshold"));
		}
		if (session.getAttribute("type") == null) {
			session.setAttribute("type", "Sensor");
		}
		if (session.getAttribute("time") == null) {
			session.setAttribute("time", df.format(new Date()));
		}
	%>
	<table border=1px bordercolor=green cellspacing=0 cellpadding="5"
		width=220px>
		<tr>
			<td width=50px>状态</td>
			<td width=170px>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("switch"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>报警</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("alarm"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>阈值</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("set_threshold"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>网络</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("login"));
					}
				%>
			</td>
		</tr>
	</table>
	<br>
	<button name="switch_" onclick="switch_()"
		style="height:30px; width:60px ">开关</button>
	<script type="text/javascript">
		function switch_() {
			var test1 = '3';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="alarm" onclick="alarm()" style="height:30px; width:60px ">报警</button>
	<br>
	<script type="text/javascript">
		function alarm() {
			var test1 = '4';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<form action="Sensor.jsp" method="post" id="passForm1" name="form2">
		<input type="submit" name="set_time" value="阈值"
			style="height:30px; width:60px "> <input id="set_time1"
			type="text" name="set_threshold" value="set_time here">
	</form>
	<form method="post" action="Sensor.jsp" id="passForm" name="form1">
		<input id='test2' type='hidden' name="test2">
	</form>
	<%
		int kind = 0;
		if (request.getParameter("test2") != null) {
			kind = Integer.parseInt(request.getParameter("test2"));
		}
		if (kind == 3) {
			if (session.getAttribute("switch") == null) {
				session.setAttribute("switch", "close");
				session.setAttribute("time", df.format(new Date()));
			}
			if (!session.getAttribute("switch").equals("open")) {
				session.setAttribute("switch", "open");
				session.setAttribute("time", df.format(new Date()));
			} else {
				session.setAttribute("switch", "close");
				session.setAttribute("time", df.format(new Date()));
			}
		}
		if (kind == 4) {
			if (!session.getAttribute("alarm").equals("true")) {
				session.setAttribute("alarm", "true");
				session.setAttribute("time", df.format(new Date()));
			} else {
				session.setAttribute("alarm", "false");
				session.setAttribute("time", df.format(new Date()));
			}
		}
	%>
</body>
</html>
