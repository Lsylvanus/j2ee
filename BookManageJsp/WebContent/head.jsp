<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<script>
    	var base_path = "<%=path%>";
    </script>
<title>图书管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=path + "/css/styles.css"%>">
<link href="<%=path + "/lib/ligerUI/skins/Aqua/css/ligerui-all.css"%>"
	rel="stylesheet" type="text/css" />
<link href="<%=path + "/lib/ligerUI/skins/ligerui-icons.css"  %>"
	rel="stylesheet" type="text/css" />
<script src="<%=path +"/js/jquery-1.8.3.js"  %>" type="text/javascript"></script>
<script src="<%=path +"/lib/ligerUI/js/ligerui.all.js" %>"
	type="text/javascript"></script>
<script src="<%=path +"/js/jquery.js" %>" type="text/javascript"></script>
<script src="<%=path +"/js/ligerGrid.showFilter.js" %>"
	type="text/javascript"></script>
<script
	src="<%=path +"/lib/jquery-validation/jquery.validate.min.js" %>"
	type="text/javascript"></script>
<script src="<%=path +"/lib/jquery-validation/jquery.metadata.js" %>"
	type="text/javascript"></script>
<script src="<%=path +"/lib/jquery-validation/messages_cn.js"  %>"
	type="text/javascript"></script>
<script src="<%=path +"/js/checkingJS.js"%>" type="text/javascript"></script>
