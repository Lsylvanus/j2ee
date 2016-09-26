<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@include file="head.jsp"%>
    <script type="text/javascript">
       function addNewRow(obj,title)
        {
            $("#timeBegin").ligerDateEditor();
            $("#timeEnd").ligerDateEditor();
            $("#d4").ligerDateEditor();
            $("#selectClassify").ligerComboBox(
            {tree:{data:[
			    { "text": "节点1", "children": [
			        { "text": "节点1.1" },
			        { "text": "节点1.2" },
			        { "text": "节点1.3", "children": [
			                { "text": "节点1.3.1" ,"children": [
								{ "text": "节点1.3.1.1" },
								{ "text": "节点1.3.1.2" }]
							},
			                { "text": "节点1.3.2" }
			        ]
			        },
			        { "text": "节点1.4" }
			        ]
			    },
			    { "text": "节点2" },
			    { "text": "节点3" },
			    { "text": "节点4" }
			], checkbox: false,width:200}
            });
            $.ligerDialog.open({ target: $(obj),width: 520,height:360,title:title });
            if("${value}"+"x"!="x"){
           		f_tip("${value}");
            }
        }
    </script>
    
    <style type="text/css">
       .padding-bottom:{ padding-bottom: 40px;}
       #top{position: absolute;right:10px; bottom:10px;}
    </style>
  </head>
  
  <body id="abouts">
	<div class="top"></div>
	<div id="logoTop"><div class="logo text">欢迎登陆${name}图书管理系统</div></div>
	<div class="option">
		<div class="more" onclick="f_open1('book/QueryBookServlet?logo=1',this)" style="background: url('image/book.png') no-repeat;">	
			<div class="text">图书管理</div>
		</div>
	</div>
	<div id="top">
		<div class="about" onclick="f_open('aboutUS.jsp',this)">关于我们</div>
		<div class="logo" onclick="f_open1('logo.jsp',this)">登陆系统</div>
	</div>
  	<script>
   		checkingLogo();
		$.ligerui.win.removeTaskbar = function () { }; //不允许移除
  		$.ligerui.win.createTaskbar(); //页面加载时创建任务栏	
	</script>
  </body>
</html>
