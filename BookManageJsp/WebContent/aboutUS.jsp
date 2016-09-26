<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'aboutUS.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	margin: 0;
	margin-left: 3px;
	padding-top: 10px;
}

#about {
	width: 320px;
	line-height: 22px;
}

#about .title {
	height: 22px;
	text-align: center;
}

#about .title h3 {
	float: left;
	margin: 0;
	font-weight: bold;
	color: red;
}

#about .title span {
	float: left;
	margin-left: 5px;
}

#about .hr {
	border: 5px #0070D4 solid;
}

.h10 {
	width: 10px;
	height: 10px;
}

#about .img {
	width: 320px;
	text-align: center;
}

#about .body {
	width: 260px;
	margin: 0 auto;
	height: 20px;
}

#about .body span {
	font-size: 12px;
	font: 宋体
}

#about .body .span {
	float: right;
}
</style>
</head>

<body>
	<div id="about">
		<div class="title">
			<h3>图书管理系统</h3>
			<span>版本号:1.0</span>
		</div>
		<div class="h10"></div>
		<div class="hr"></div>
		<div class="h10"></div>
		<div class="img">
			<img src="image/title.jpg" alt="Power By Lsylvanus" />
		</div>
		<div class="h10"></div>
		<div class="body">
			<span>朱世强</span>
		</div>
		<div class="body">
			<span>让编程融入生活</span>
		</div>
		<div class="body">
			<span>QQ:604486559</span>
		</div>
		<div class="body">
			<span>wechat:_lightsylvanus</span>
		</div>
		<div class="body">
			<a target="_blank" href="http://blog.csdn.net/u012902677"><span
				class="span">http://blog.csdn.net/u012902677</span></a>
		</div>
	</div>
</body>
</html>
