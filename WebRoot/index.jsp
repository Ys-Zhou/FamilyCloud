<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'index.jsp' starting page</title>

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
	<h2>智能家庭设备模拟器</h2>
	<form action="servlet/Choice" method="post">
		输入所属家庭<input type="text" name="familyName" /><br> 选择设备类型 <select
			name="type">
			<option selected="selected">请选择</option>
			<option value="Aircondition">空调</option>
			<option value="Curtain">窗帘</option>
			<option value="Icebox">冰箱</option>
			<option value="Light">电灯</option>
			<option value="Sensor">传感器</option>
			<option value="Television">电视</option>
		</select> <br> 选择设备楼层 <select name="floor">
			<option selected="selected">请选择</option>
			<option value="0">地下一层</option>
			<option value="1">一层</option>
			<option value="2">二层</option>
			<option value="3">三层</option>
		</select><br>选择设备房间 <select name="room">
			<option selected="selected">请选择</option>
			<option value="living">客厅</option>
			<option value="master">主卧</option>
			<option value="guest">客卧</option>
			<option value="rest">卫生间</option>
			<option value="kitchen">厨房</option>
			<option value="dinding">餐厅</option>
			<option value="balcony">阳台</option>
		</select><br> <input type="submit" value="确认" />
	</form>
</body>
</html>