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
        var customersData={Rows:${students}};
        $(function ()
        {
            manager = window['g'] = 
            $("#maingrid4").ligerGrid({
                columns: [
            { display: 'ID', name: 'id', align: 'right', width: 120},
            { display: '卡号', name: 'cardNumber', width: 120, align: 'right', editor: { type: 'text' }},
            { display: '名称', name: 'name', width: 120, align: 'right', editor: { type: 'text' }},
            { display: '院系', name: 'department',minWidth: 60, align: 'right', editor: { type: 'text' }}
                ], data: $.extend(true,{},customersData), pageSize: 20,
                toolbar: { items: [{ text: '自定义查询', click: itemclick, icon: 'search2'},
                					{ text: '添加用户', click: addNewRow,icon:'add'},
                					{ text: '修改用户', click: beginEdit, icon: 'modify'},
                					{ text: '取消修改', click: cancelEdit,icon:'communication'},
                					{ text: '提交修改', click: endEdit,icon:'ok'},
                					{ text: '取消全部', click: cancelAllEdit,icon:'prev'},
                					{ text: '删除选择的用户', click: deleteRow,icon:'delete'}]
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
        //修改用户
        function beginEdit() {
           	var rows = manager.getSelectedRows();
            if (!rows[0]) {alert('请选择修改的学生'); return; }
           	for(i=0;i<rows.length;i++){
           		manager.beginEdit(rows[i]);
          	}
        }
        //取消修改
        function cancelEdit() {
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择修改的学生'); return; }
            manager.cancelEdit(row);
        }
        //取消全部
        function cancelAllEdit()
        {
            manager.cancelEdit();
        }
        //提交修改
        function endEdit()
        {
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择修改的学生'); return; }
            manager.endEdit(row);
            $.post(path() + "/student/UpdateStudentServlet", {id: row.id,name:row.name,department:row.department,cardNumber:row.cardNumber}, function(data){
				if(data=="修改成功"){
					
          		}else{
          			
          		}
          		f_tip(data);
			});
        }
        //删除选择的用户
        
        function deleteRow()
        { 
        	var rows = manager.getSelectedRows();
			if (!rows) { alert('请选择删除的学生'); return; }
			else if(rows==1){
	        	$.ligerDialog.confirm('是否删除','是否删除用户', function (yes)
			    {
					if (yes){
						var rows = manager.getSelectedRows();
						alert(rows.length);
			        	$.post(path() + "/student/DeleteStudentServlet", {id: rows.id}, function(data){
							f_tip(data);
						});
			            manager.deleteSelectedRow();
					}
			    });
		    } else{
		    	$.ligerDialog.confirm('是否删除','是否删除多个学生', function (yes)
			    {
					if (yes){
						var rows = manager.getSelectedRows();
						for(i = 0;i<rows.length;i++){
				        	$.post(path() + "/student/DeleteStudentServlet", {id: rows[i].id}, function(data){
								f_tip(data);
							});
				            manager.deleteSelectedRow();
			            } 
					}
			    });
		    }
        }
        var newrowid = 100;
        //添加用户
        function addNewRow()
        {
            $("#sl1").ligerComboBox();
            $("#d1").ligerDateEditor();
            $.ligerDialog.open({ target: $("#target1"),width: 250,height:230,title:'添加用户' });
        	if("${value}"+"x"!="x"){
           		f_tip("${value}");
            }
        } 
         
        function getSelected()
        { 
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择修改的学生'); return; }
            alert(JSON.stringify(row));
        }
        function getData()
        { 
            var data = manager.getData();
            alert(JSON.stringify(data));
        } 
        function f_tip(value) {
            $.ligerDialog.tip({  title: '提示信息',content:value});
        }
        
        $(function (){
       		var error = ${error};
	    	if(error=="11"){
	    		addNewRow();
	    	}
    	})
	</script>
	<script type="text/javascript">
     $(function ()
        {
            $("#sl1").ligerTree({
            data: [
                { text: '节点1', children: [
                    { text: '节点1.1' },
                    { text: '节点1.2' },
                    { text: '节点1.3', children: [
                         { text: '节点1.3.1' },
                         { text: '节点1.3.2' }
                    ]
                    },
                    { text: '节点1.4' },
                    { text: '节点2' },
                	{ text: '节点3' },
                	{ text: '节点4' }
                 ]
                }
            ],checkbox :false
            });
            
            obj = $("#sl1").ligerGetTreeManager(); 
            
     	});
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
    	.fd{ width:205px;}
    	.fd span {width: 60px; float:left;margin-right: 10px;}
    	.l-button{ float:right}
    	.l-text{width: 130px;float: left;}
    	input{width: 130px;}
    </style>
  </head>
  
  <body>
	<div class="l-loading" style="display: block" id="pageloading"></div> 
	<div id="maingrid4" style="margin: 0; padding: 0"></div>
	<div style="display: none;"></div>
	<div id="target1" style="width:200px; margin:3px; display:none;">
		<h3>添加用户</h3>
		<div>
		<form action="/student/SaveStudentServlet" method="post" id="form">
			<div class="fd" id="cardNumber" title="">
				<span style="text-align:right">卡号:</span>
				<input type="text" id="studentCardNumber" validate="{studentIdLength:[2,15],studentIdChecking:[studentCardNumber,'StudentChecking']}" name='cardNumber' class='l-text'/>
			</div>
			<div class="fd">
				<span style="text-align:right">学生名称:</span>
				<input type="text" id="books" name='name' validate="{studentIdLength:[2,6]}" class='l-text'/>
			</div>
			<div class="fd">
				<span style="text-align:right">院系:</span>
				<input type="text" id="txtName" name='department' validate="{notnull:true}" nullText="不能为空!" class='l-text'/>
			</div>
			<div class="fd">
				<input class="l-button" type="submit" value="提交" id="l-button"/>
			</div>
		</form>
		</div>
		<script type="text/javascript">
			$(".l-text").css("width","130px");
		</script>
	</div>
	<div style="display:none;"></div>
</body>
</html>
