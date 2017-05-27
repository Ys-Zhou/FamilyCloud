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

<title>My JSP 'refrigerater.jsp' starting page</title>
<meta http-equiv="refresh" content="3; url=servlet/IceboxServ">
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
	<h1>冰箱</h1>
	<%!SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
	<%
		request.setCharacterEncoding("gb2312");
		if (session.getAttribute("login") == null) {
			session.setAttribute("login", "disconnect");
		}
		if (session.getAttribute("temperature") == null) {
			session.setAttribute("temperature", "0");
		}
		if (session.getAttribute("model") == null) {
			session.setAttribute("model", "freeze");
		}
		if (request.getParameter("set_time") != null) {
			session.setAttribute("time", df.format(new Date()));
			session.setAttribute("set_time",
					request.getParameter("set_time1"));
		}
		if (session.getAttribute("type") == null) {
			session.setAttribute("type", "Icebox");
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
			<td>温度</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("temperature"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>模式</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("model"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>定时</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("set_time"));
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

	<button name="temperature_add" onclick="temperature_add()"
		style="height:30px; width:60px ">升温</button>
	<script type="text/javascript">
		function temperature_add() {
			var test1 = '1';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="temperature_subtract" onclick="temperature_subtract()"
		style="height:30px; width:60px ">降温</button>
	<br>
	<script type="text/javascript">
		function temperature_subtract() {
			var test1 = '2';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
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
	<button name="model" onclick="model()" style="height:30px; width:60px ">模式</button>
	<br>
	<script type="text/javascript">
		function model() {
			var test1 = '5';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<form action="Icebox.jsp" method="post" id="passForm1" name="form2">
		<input type="submit" name="set_time" value="定时"
			style="height:30px; width:60px "> <input id="set_time1"
			type="text" name="set_time1" value="set_time here">
	</form>
	<form method="post" action="Icebox.jsp" id="passForm" name="form1">
		<input id='test2' type='hidden' name="test2">
	</form>
	<%
		int kind = 0;
		if (request.getParameter("test2") != null) {
			kind = Integer.parseInt(request.getParameter("test2"));
		}

		if (kind == 1) {
			int temp = Integer.parseInt((String) session
					.getAttribute("temperature"));
			temp++;
			String t = String.valueOf(temp);
			session.setAttribute("temperature", t);
			session.setAttribute("time", df.format(new Date()));
		}
		if (kind == 2) {
			int temp1 = Integer.parseInt((String) session
					.getAttribute("temperature"));
			temp1--;
			String t1 = String.valueOf(temp1);
			session.setAttribute("temperature", t1);
			session.setAttribute("time", df.format(new Date()));
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
		if (kind == 5) {
			if (!session.getAttribute("model").equals("refrigerate")) {
				session.setAttribute("model", "refrigerate");
				session.setAttribute("time", df.format(new Date()));
			} else {
				session.setAttribute("model", "freeze");
				session.setAttribute("time", df.format(new Date()));
			}
		}
	%>
</body>
</html>
