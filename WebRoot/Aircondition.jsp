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

<title>My JSP 'Aircondition.jsp' starting page</title>
<meta http-equiv="refresh" content="3; url=servlet/AirconditionServ">
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
	<h1>空调</h1>
	<%!SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	int model_num = 1;
	int wind_num = 1;
	int windrate_num = 1;%>
	<%
		request.setCharacterEncoding("gb2312");
		if (session.getAttribute("login") == null) {
			session.setAttribute("login", "disconnect");
		}
		if (session.getAttribute("temperature") == null) {
			session.setAttribute("temperature", "22");
		}
		if (session.getAttribute("model") == null) {
			session.setAttribute("model", "cold");
		}
		if (session.getAttribute("wind") == null) {
			session.setAttribute("wind", "null");
		}
		if (session.getAttribute("windrate") == null) {
			session.setAttribute("windrate", "one");
		}
		if (session.getAttribute("set_time") == null) {
			session.setAttribute("set_time", "00:00");
		}
		if (session.getAttribute("type") == null) {
			session.setAttribute("type", "Aircondition");
		}
		if (session.getAttribute("time") == null) {
			session.setAttribute("time", df.format(new Date()));
		}
	%>
	<table border=1px bordercolor=green cellspacing=0 cellpadding="5"
		width=220px>
		<tr>
			<td width=50px>温度</td>
			<td width=170px>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("temperature"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>风向</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("wind"));
					}
				%>
			</td>
		</tr>
		<tr>
			<td>风速</td>
			<td>
				<%
					if (session.getAttribute("switch") != null
							&& session.getAttribute("switch").equals("open")) {
						out.println(session.getAttribute("windrate"));
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
			<td>状态</td>
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
	<br>
	<script type="text/javascript">
		function switch_() {
			var test1 = '3';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="wind" onclick="wind()" style="height:30px; width:60px ">风向</button>
	<script type="text/javascript">
		function wind() {
			var test1 = '4';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="windrate" onclick="windrate()"
		style="height:30px; width:60px ">风速</button>
	<script type="text/javascript">
		function windrate() {
			var test1 = '6';
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
	<button name="model" onclick="set_time_add()"
		style="height:30px; width:60px ">定时+</button>
	<script type="text/javascript">
		function set_time_add() {
			var test1 = '7';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<button name="model" onclick="set_time_subtract()"
		style="height:30px; width:60px ">定时-</button>
	<script type="text/javascript">
		function set_time_subtract() {
			var test1 = '8';
			document.form1.test2.value = test1;
			var formObj = document.getElementById('passForm');
			formObj.submit();
		}
	</script>
	<form method="post" action="Aircondition.jsp" id="passForm"
		name="form1">
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
			if (temp >= 32) {
				temp = 32;
			}
			String t = String.valueOf(temp);
			session.setAttribute("temperature", t);
			session.setAttribute("time", df.format(new Date()));
		}
		if (kind == 2) {
			int temp1 = Integer.parseInt((String) session
					.getAttribute("temperature"));
			temp1--;
			if (temp1 <= 16) {
				temp1 = 16;
			}
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
		if (kind == 4) {
			/* if(!session.getAttribute("wind").equals("true")){
			session.setAttribute("wind","true");
			session.setAttribute("time", df.format(new Date()));
			}else{
			session.setAttribute("wind","false");
			session.setAttribute("time", df.format(new Date()));
			} */
			switch (wind_num) {
			case 1:
				session.setAttribute("wind", "H");
				session.setAttribute("time", df.format(new Date()));
				wind_num++;
				break;
			case 2:
				session.setAttribute("wind", "V");
				session.setAttribute("time", df.format(new Date()));
				wind_num++;
				break;
			case 3:
				session.setAttribute("wind", "H+V");
				session.setAttribute("time", df.format(new Date()));
				wind_num++;
				break;
			case 4:
				session.setAttribute("wind", "null");
				session.setAttribute("time", df.format(new Date()));
				wind_num = 1;
				break;
			default:
				session.setAttribute("wind", "null");
				session.setAttribute("time", df.format(new Date()));
				wind_num = 1;
				break;
			}
		}
		if (kind == 5) {
			/* if(!session.getAttribute("model").equals("warm")){
			session.setAttribute("model","warm");
			session.setAttribute("time", df.format(new Date()));
			}else{
			session.setAttribute("model","cold");
			session.setAttribute("time", df.format(new Date()));
			} */
			switch (model_num) {
			case 1:
				session.setAttribute("model", "warm");
				session.setAttribute("time", df.format(new Date()));
				model_num++;
				break;
			case 2:
				session.setAttribute("model", "dying");
				session.setAttribute("time", df.format(new Date()));
				model_num++;
				break;
			case 3:
				session.setAttribute("model", "wind");
				session.setAttribute("time", df.format(new Date()));
				model_num++;
				break;
			case 4:
				session.setAttribute("model", "auto");
				session.setAttribute("time", df.format(new Date()));
				model_num++;
				break;
			case 5:
				session.setAttribute("model", "cold");
				session.setAttribute("time", df.format(new Date()));
				model_num = 1;
				break;
			default:
				session.setAttribute("model", "cold");
				session.setAttribute("time", df.format(new Date()));
				model_num = 1;
				break;
			}
		}
		if (kind == 6) {
			switch (windrate_num) {
			case 1:
				session.setAttribute("windrate", "two");
				session.setAttribute("time", df.format(new Date()));
				windrate_num++;
				break;
			case 2:
				session.setAttribute("windrate", "three");
				session.setAttribute("time", df.format(new Date()));
				windrate_num++;
				break;
			case 3:
				session.setAttribute("windrate", "four");
				session.setAttribute("time", df.format(new Date()));
				windrate_num++;
				break;
			case 4:
				session.setAttribute("windrate", "five");
				session.setAttribute("time", df.format(new Date()));
				windrate_num++;
				break;
			case 5:
				session.setAttribute("windrate", "one");
				session.setAttribute("time", df.format(new Date()));
				windrate_num = 1;
				break;
			default:
				session.setAttribute("windrate", "one");
				session.setAttribute("time", df.format(new Date()));
				model_num = 1;
				break;
			}
		}
		if (kind == 7) {
			String set_time = (String) session.getAttribute("set_time");
			char minute;
			minute = set_time.charAt(3);
			String hours;
			int temp;
			switch (minute) {
			case '0':
				hours = set_time.substring(0, 2);
				temp = Integer.parseInt(hours);
				out.println(hours);
				out.println(temp);
				if (temp <= 9) {
					session.setAttribute("set_time", "0" + temp + ":30");
					session.setAttribute("time", df.format(new Date()));
				} else {
					session.setAttribute("set_time", temp + ":30");
					session.setAttribute("time", df.format(new Date()));
				}
				out.println(session.getAttribute("set_time"));
				break;
			case '3':
				hours = set_time.substring(0, 2);
				temp = Integer.parseInt(hours);
				temp++;
				out.println(hours);
				out.println(temp);
				if (temp >= 24) {
					temp = 0;
				}
				if (temp <= 9) {
					session.setAttribute("set_time", "0" + temp + ":00");
					session.setAttribute("time", df.format(new Date()));
				} else {
					session.setAttribute("set_time", temp + ":00");
					session.setAttribute("time", df.format(new Date()));
				}
				out.println(session.getAttribute("set_time"));
				break;
			default:
				session.setAttribute("set_time", "00:00");
				session.setAttribute("time", df.format(new Date()));
				break;
			}
		}
		if (kind == 8) {
			String set_time = (String) session.getAttribute("set_time");
			char minute;
			minute = set_time.charAt(3);
			String hours;
			int temp;
			switch (minute) {
			case '0':
				hours = set_time.substring(0, 2);
				temp = Integer.parseInt(hours);
				temp--;
				if (temp <= 0) {
					temp = 0;
					session.setAttribute("set_time", "00:00");
					session.setAttribute("time", df.format(new Date()));
					break;
				}
				if (temp <= 9) {
					session.setAttribute("set_time", "0" + temp + ":30");
					session.setAttribute("time", df.format(new Date()));
				} else {
					session.setAttribute("set_time", temp + ":30");
					session.setAttribute("time", df.format(new Date()));
				}
				break;
			case '3':
				hours = set_time.substring(0, 2);
				temp = Integer.parseInt(hours);
				if (temp <= 9) {
					session.setAttribute("set_time", "0" + temp + ":00");
					session.setAttribute("time", df.format(new Date()));
				} else {
					session.setAttribute("set_time", temp + ":00");
					session.setAttribute("time", df.format(new Date()));
				}
				break;
			default:
				session.setAttribute("set_time", "00:00");
				session.setAttribute("time", df.format(new Date()));
				break;
			}
		}
	%>
</body>
</html>
