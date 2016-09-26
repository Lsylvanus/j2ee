<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="head.jsp"%>
    <script type="text/javascript">
       //去掉  大于小于包括,并改变顺序
	    $.ligerDefaults.Filter.operators['string'] =
	    $.ligerDefaults.Filter.operators['text'] =
	    ["like" , "equal", "notequal", "startwith", "endwith" ];
        var manager;
        if(${books}+"!"!="!"){
        var customersData={Rows:${books}};
        } else{
        	var customersData;
        }
        $(function ()
        {
            manager = window['g'] = 
            $("#maingrid4").ligerGrid({
            columns: [
            { display: 'ID', name: 'id', align: 'right', width: 120},
            { display: '书籍ID', name: 'cardNumber', minWidth: 60, align: 'right', id:'bookID',
            editor: { type: 'text',onChanged:inputInt("#bookID div input")}
            },
            { display: '书籍名称', name: 'name', width: 120, align: 'right', editor: { type: 'text'}},
            { display: '借阅数量', name: 'stock', align: 'right', editor: { type: 'text' }},
            { display: '总数量', name: 'AStrongerStock', width: 170, align: 'right', editor: { type: 'text' }},
            { display: '书籍分类', name: 'classify', width: 170, align: 'right', editor: { type: 'text' }} 
                ], data: $.extend(true,{},customersData), pageSize: 20,
                toolbar: { items: [{ text: '自定义查询', click: itemclick, icon: 'search2'}
                					]
                }, enabledEdit: true, clickToEdit: false, 
                width: '99.5%', height: '98%', checkbox: true
            });
            $("#pageloading").hide();
        });
        //高级自定义查询
        function itemclick()
        {
            g.options.data = $.extend(true,{}, customersData);
            g.showFilter();
        }
       
        function f_tip(value) {
            $.ligerDialog.tip({  title: '提示信息',content:value});
        }
	</script>
    <style type="text/css">
    	.l-text-wrapper{ float:left;}
    	.fd{ width:205px;}
    	.fd span {width: 60px; float:left;margin-right: 10px;}
    	.l-button{ float:right}
    </style>
  </head>
  
<body>
	<div class="l-loading" style="display: block" id="pageloading"></div> 
	<div id="maingrid4" style="margin: 0; padding: 0"></div>
	<div style="display: none;"></div>
</body>
<script>
	$(function(){
		if(window.parent.$("#top").html() == window.parent.$("#logoTop").html()){
			$(".l-panel-btn").css("display","block");
		} else{
			$(".l-panel-btn").css("display","none");
			$(".l-toolbar-item-hasicon").css("display","block");
		}
	})
</script>
</html>
