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

<title>My JSP 'Television.jsp' starting page</title>
<meta http-equiv="refresh" content="3; url=servlet/TelevisionServ">
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
	<h1>电视</h1>
	<%!SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
	<%
		request.setCharacterEncoding("gb2312");
		if (session.getAttribute("login") == null) {
			session.setAttribute("login", "disconnect");
		}
		if (session.getAttribute("channel") == null) {
			session.setAttribute("channel", "1");
		}
		if (session.getAttribute("voice") == null) {
			session.setAttribute("voice", "20");
		}
		if (session.getAttribute("model") == null) {
			session.setAttribute("model", "ordinary");
		}
		if (session.getAttribute("type") == null) {
			session.setAttribute("type", "Television");
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
			<td>频道</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("channel"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>声音</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("voice"));
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

	<button name="channel_add" onclick="channel_add()"
		style="height:30px; width:60px ">频道+</button>
	<script type="text/javascript">
		function channel_add() {
			var test1 = '1';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="channel_subtract" onclick="channel_subtract()"
		style="height:30px; width:60px ">频道-</button>
	<script type="text/javascript">
		function channel_subtract() {
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
	<button name="voice_add" onclick="voice_add()"
		style="height:30px; width:60px ">声音+</button>
	<script type="text/javascript">
		function voice_add() {
			var test1 = '4';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="voice_subtrack" onclick="voice_subtrack()"
		style="height:30px; width:60px ">声音-</button>
	<script type="text/javascript">
		function voice_subtrack() {
			var test1 = '5';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="model" onclick="model()" style="height:30px; width:60px ">模式</button>
	<br>
	<script type="text/javascript">
		function model() {
			var test1 = '6';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<form method="post" action="Television.jsp" id="passForm" name="form1">
		<input id='test2' type='hidden' name="test2">
	</form>
	<%
		int kind = 0;
		if (request.getParameter("test2") != null) {
			kind = Integer.parseInt(request.getParameter("test2"));
		}

		if (kind == 1) {
			int temp = Integer.parseInt((String) session
					.getAttribute("channel"));
			temp++;
			String t = String.valueOf(temp);
			session.setAttribute("channel", t);
			session.setAttribute("time", df.format(new Date()));
		}
		if (kind == 2) {
			int temp1 = Integer.parseInt((String) session
					.getAttribute("channel"));
			temp1--;
			String t1 = String.valueOf(temp1);
			session.setAttribute("channel", t1);
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
		if (kind == 4) {
			int temp1 = Integer.parseInt((String) session
					.getAttribute("voice"));
			temp1++;
			String t1 = String.valueOf(temp1);
			session.setAttribute("voice", t1);
			session.setAttribute("time", df.format(new Date()));
		}
		if (kind == 5) {
			int temp1 = Integer.parseInt((String) session
					.getAttribute("voice"));
			temp1--;
			String t1 = String.valueOf(temp1);
			session.setAttribute("voice", t1);
			session.setAttribute("time", df.format(new Date()));
		}
		if (kind == 6) {
			if (!session.getAttribute("model").equals("ordinary")) {
				session.setAttribute("model", "ordinary");
				session.setAttribute("time", df.format(new Date()));
			} else {
				session.setAttribute("model", "cinema");
				session.setAttribute("time", df.format(new Date()));
			}
		}
	%>
</body>
</html>
