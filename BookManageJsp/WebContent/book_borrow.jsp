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
        var customersData;
        var bookName;
        if(${borrow}+"x"!="x"){
        	customersData={Rows:${borrow}};
        }
        
        $(function ()
        {
            manager = window['g'] = 
            $("#maingrid4").ligerGrid({
            columns: [
            { display: 'ID', name: 'id', align: 'right', width: 120},
            { display: '借阅人编号', name: 'studentID', minWidth: 60, align: 'right',editor: { type: 'text'}},
            { display: '借阅人', name: 'studentName', width: 120, align: 'right', editor: { type: 'text'}}, 
            { display: '书籍名称', name: 'bookID', width: 120, align: 'right', editor: { type: 'text'}},
            { display: '借阅时间', name: 'beginTIme', width: 170, align: 'right', editor: { type: 'text' }},
            { display: '结束时间', name: 'endTime', width: 170, align: 'right', editor: { type: 'text' }},
            { display: '是否归还', name: 'returnBook', width: 100, align: 'center', editor: { type: 'text' }}
            ], data: $.extend(true,{},customersData), pageSize: 10,
                toolbar: { items: [ { text: '自定义查询', click: itemclick, icon: 'search2'},
                					{ text: '借阅书籍', click: function(){borrowBook("#target1","借阅书籍")},icon:'customers',id:'borrow'}]
                }, enabledEdit: true, clickToEdit: false, 
                width: '99.5%', height: '98%', checkbox: true
            });
            $("#pageloading").hide();
            student = $("#studentID").ligerComboBox({  
		        data:"",
		        hideOnLoseFocus :false,
		        onSelected: function (value, text)
		        {
					var index = 0;
					for(var i=0,k; i<text.length; i++) {
						k = text.charAt(i);
						if(k == "<") {
							index = i;
							break;
						}
					}
					text = text.substr(0,index);
					$("#studentID").val(text);
					$("#studentID").removeAttr("readonly");
					$(".l-text-combobox").removeClass("l-text-invalid");
		        }
		    });
		    $("#timeBegin").ligerDateEditor();
            $("#timeEnd").ligerDateEditor();
            $("#bookName").ligerComboBox({data:${books}});
		    $("#studentID").removeAttr("readonly");
        });
        //高级自定义查询
        function itemclick()
        {
            g.options.data = $.extend(true,{}, customersData);
            g.showFilter();
        }
        
        //借阅书籍
        function borrowBook(obj,title)
        {
            $("#d4").ligerDateEditor();
            $.ligerDialog.open({ target: $(obj),width: 270,height:250,title:title});
            $(".l-text").css("width","130px");
			$("input").css("width","130px");
			$(".l-text-wrapper").css("width","130px");
	    	$(".l-verify-tip").css("display","none");
            if("${value}"+"x"!="x"){
           		f_tip("${value}");
            }
        } 
         
        function getSelected()
        { 
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
        function getData()
        { 
            var data = manager.getData();
        } 
        function f_tip(value) {
            $.ligerDialog.tip({  title: '提示信息',content:value});
        }
        
        $(function (){
       		var error = ${error};
	    	if(error>"0"&&error<="10"){
	    		if(error>"0"&&error<"5"){
	    			borrowBook("#target1","借阅书籍");
	    		} else {
	    			borrowBook("#target2","归还书籍");
	    		}
	    	} else if(error=="11"){
	    		addNewRow();
	    	} else{
	    		if("${value}"+"x"!="x"){
           			f_tip("${value}");
           		}
	    	}
    	})
	</script>
    <script type="text/javascript">
        var eee;
        $(function ()
        {
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                //调试状态，不会提交数据的
                debug: true,
                errorPlacement: function (lable, element)
                {

                    if (element.hasClass("l-textarea"))
                    {
                        element.addClass("l-textarea-invalid");
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().addClass("l-text-invalid");
                    }
                    $(element).removeAttr("title").ligerHideTip();
                    $(element).attr("title", lable.html()).ligerTip();
                },
                success: function (lable)
                {
                    var element = $("#" + lable.attr("for"));
                    if (element.hasClass("l-textarea"))
                    {
                        element.removeClass("l-textarea-invalid");
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().removeClass("l-text-invalid");
                    }
                    $(element).removeAttr("title").ligerHideTip();
                },
                submitHandler: function ()
                {
                	$("#form").attr("action",path()+$("#form").attr("action"));  
                    document.getElementById("form").submit();
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                alert(v.element($("#txtName")));
            });
        });
    </script>
    <style type="text/css">
    	.l-text-wrapper{ float:left;}
    	.fd{ width:225px;}
    	.fd span {width: 80px; float:left;margin-right: 10px;}
    	.l-button{ float:right}
    	.l-text{width: 130px;float: left;}
    	input{width: 130px;}
    </style>
  </head>
  
  <body>
	<div class="l-loading" style="display: block" id="pageloading"></div> 
	<div id="maingrid4" style="margin: 0; padding: 0"></div>
	<div id="target1" style="margin:3px; display:none;">
		<h3>借阅书籍</h3>
		<form id="form" action="/borrowServlet/BorrowServlet?values=1" method="post" autocomplete="off">
			<div class="fd">
				<span style="text-align:right">学生卡号:</span>
				<div id="student"></div>
				<input type="text" id="studentID" validate="{studentIdLength:[2,15],studentIdChecking:[studentID,'BorrowChecking']}" onkeyup="LikeChecking(this)" name='cardNumber' class="l-text"/>
			</div>
			<div class="fd">
				<span style="text-align:right">借阅书籍:</span>
				<input type="text" name='bookName' validate="{studentIdLength:[1],borrowName:['#bookName','BorrowChecking','#studentID']}" id="bookName" class="l-text"/>
			</div>
			<div class="fd">
				<span style="text-align:right; float:left">借阅开始时间:</span>
				<input type="text" id='timeBegin' validate="{studentIdLength:[1],timeChecking:['BorrowChecking']}" name='timeBegin'/>
			</div>
			<div class="fd">
				<span style="text-align:right; float:left">借阅结束时间:</span>
				<input type="text" id='timeEnd' validate="{studentIdLength:[1],timeChecking:['BorrowChecking']}" name='timeEnd'/>
			</div>
			<div class="fd">
				<div style="width:75px; height:20px; float:right"></div>
				<input class="l-button" type="submit" value="提交"/>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	
	 $(".l-text-wrapper").each(function(i){
		$(this).css("width","130px");
		$(this).css("float","left");
	 });
</script>
</html>
