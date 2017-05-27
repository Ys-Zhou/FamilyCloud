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

<title>My JSP 'airconditioner.jsp' starting page</title>
<meta http-equiv="refresh" content="3; url=servlet/LightServ">
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
	<h1>电灯</h1>
	<%!SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
	<%
		request.setCharacterEncoding("gb2312");
		if (session.getAttribute("login") == null) {
			session.setAttribute("login", "disconnect");
		}
		if (session.getAttribute("brightness") == null) {
			session.setAttribute("brightness", "50");
		}
		if (session.getAttribute("type") == null) {
			session.setAttribute("type", "Light");
		}
		if (session.getAttribute("time") == null) {
			session.setAttribute("time", df.format(new Date()));
		}
	%>
	<table border=1px bordercolor=green cellspacing=0 cellpadding="5"
		width=220px>
		<tr>
			<td width=50px>亮度</td>
			<td width=170px>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("brightness"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>状态</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("switch"));
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
	<button name="brightness_add" onclick="brightness_add()"
		style="height:30px; width:60px ">亮度加</button>
	<script type="text/javascript">
		function brightness_add() {
			var test1 = '1';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="brightness_subtract" onclick="brightness_subtract()"
		style="height:30px; width:60px ">亮度减</button>
	<script type="text/javascript">
		function brightness_subtract() {
			var test1 = '2';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="switch_" onclick="switch_()"
		style="height:30px; width:60px ">开关</button>
	<br>
	<script type="text/javascript">
		function switch_() {
			var test1 = '3';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<form method="post" action="Light.jsp" id="passForm" name="form1">
		<input id='test2' type='hidden' name="test2">
	</form>
	<%
		int kind = 0;
		if (request.getParameter("test2") != null) {
			kind = Integer.parseInt(request.getParameter("test2"));
		}

		if (kind == 1) {
			int temp = Integer.parseInt((String) session
					.getAttribute("brightness"));
			temp++;
			String t = String.valueOf(temp);
			session.setAttribute("brightness", t);
			session.setAttribute("time", df.format(new Date()));
		}
		if (kind == 2) {
			int temp1 = Integer.parseInt((String) session
					.getAttribute("brightness"));
			temp1--;
			String t1 = String.valueOf(temp1);
			session.setAttribute("brightness", t1);
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
	%>
</body>
</html>
