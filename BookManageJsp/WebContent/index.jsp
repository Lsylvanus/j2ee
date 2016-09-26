<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@include file="head.jsp"%>
    <script type="text/javascript">
    	logoClose();
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
       .padding-bottom: 40px;{ padding-bottom: 40px;}
       #top{position: absolute;right:10px; bottom:10px;}
    </style>
  </head>
  
  <body id="abouts">
  	<div class="top"></div>
	<div id="logoTop"><div class="logo"></div></div>
	<div class="option">
		<div class="more" style="background-position: -0px 0px;" onclick="f_open1('student/QueryStudentServlet',this)">	
			<div class="text">用户管理</div>
		</div>
		<div class="h30"></div>
		<div class="more" style="background-position: -225px -0px;" onclick="f_open1('book/QueryBookServlet?logo=2',this)">	
			<div class="text">图书管理</div>
		</div>
		<div class="h40"></div>
		<div class="more" style="background-position: -437px -0px;"  onclick="f_open1('borrow/QueryBorrow',this)">	
			<div class="text">借阅书籍</div>
		</div>
		<div class="h40"></div>
		<div class="more" style="background-position: -650px -0px;"  onclick="f_open1('borrow/QueryBorrow',this)">	
			<div class="text">归还书籍</div>
		</div>
		<div class="h30"></div>
		<div class="more" style="background-position: -877px -0px;" onclick="f_open2()">	
			<div class="text">退出系统</div>
		</div>
	</div>
	<div style="display:none;"></div>
	<div id="top">
		<div class="about" onclick="f_open('aboutUS.jsp',this)">关于我们</div>
		<div class="logo">欢迎登陆${sessionScope.name}图书管理系统</div>
	</div>
	
	<script>
		$(".logo").css("cursor","none");
        $.ligerui.win.removeTaskbar = function () { }; //不允许移除
  		$.ligerui.win.createTaskbar(); //页面加载时创建任务栏
		checkingLogo();
	</script>
  </body>
</html>
