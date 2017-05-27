<%@ page language="java" import="java.util.*"
	import="net.sf.json.JSONObject" import="net.sf.json.JSONArray"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>


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
<META http-equiv="Content-Type" content="text/html; charset=gb2312">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>

	<%
		request.setCharacterEncoding("utf-8");
	%>

	<b>管理员权限用户注册</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'AdminSin','name':'111','email':'111','password':'111','familyName':'111','familyKey':'111','ipAddress':''}" />
		<input type="submit" value="submit" />
	</form>

	<b>普通权限用户注册</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'MemberSin','name':'222','email':'222','password':'222','familyName':'111','familyKey':'111'}" />
		<input type="submit" value="submit" />
	</form>

	<b>用户登录</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'MemberLog','email':'111','password':'111'}" /> <input
			type="submit" value="submit" />
	</form>

	<b>获取FTP信息</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetFtp','email':'111','type':'file','familyName':'111'}" />
		<input type="submit" value="submit" />
	</form>

	<b>获取HTTP信息</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetHttp','email':'123@qq.com','type':'file','familyName':'Zero','fileName'='爱上大声地.txt'}" />
		<input type="submit" value="submit" />
	</form>

	<b>删除文件</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'DeleteFile','email':'111','type':'file','fileName':'['111.txt','222.txt']','familyName':'111'}" />
		<input type="submit" value="submit" />
	</form>

	<b>获取文件信息</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetFileInfo','familyName':'111'}" /> <input
			type="submit" value="submit" />
	</form>
	<b>获取云平台信息</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetServerInfo','familyName':'111'}" /> <input
			type="submit" value="submit" />
	</form>

	<b>修改权限</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'ChangePermission','email':'222','permission':true}" />
		<input type="submit" value="submit" />
	</form>

	<b>获取家庭用户</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetFamilyMembers','familyName':'Zero'}" /> <input
			type="submit" value="submit" />
	</form>

	<b>删除用户</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'DeleteMember','email':'222'}" /> <input
			type="submit" value="submit" />
	</form>

	<b>获取日志</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'Log','familyName':'111'}" /> <input type="submit"
			value="submit" />
	</form>

	<b>获取已连接家具</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetRunFur','familyName':'Zero'}" /> <input
			type="submit" value="submit" />
	</form>

	<b>获取注册家具</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetSinFur','familyName':'Zero'}" /> <input
			type="submit" value="submit" />
	</form>

	<b>添加家具</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'AddFurniture','floor':'1','room':'living','familyName':'111','type':'Aircondition'}" />
		<input type="submit" value="submit" />
	</form>

	<b>删除家具</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'DeleteFurniture','floor':'1','room':'living','familyName':'Zero','type':'Television'}" />
		<input type="submit" value="submit" />
	</form>

	<b>获取家具状态</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'GetFurInfo','floor':'1','room':'living','familyName':'111','type':'Television'}" />
		<input type="submit" value="submit" />
	</form>

	<b>设置家具状态</b>
	<form action="servlet/Serverlet" method="post">
		<input type="hidden" name="jsonIn"
			value="{'service':'SetFurInfo','floor':'1','room':'living','familyName':'111','type':'Television','temperature':'233','switch':'open','wind':'false','model':'cold','set_time':null,'brightness':'233','alarm':false,'set_threshold':'233','channel':'233','voice':'233'}" />
		<input type="submit" value="submit" />
	</form>
</body>
</html>