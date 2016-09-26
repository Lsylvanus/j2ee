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
            { display: '借阅状态', name: 'checking', align: 'right', editor: { type: 'text' }},
            { display: '书籍分类', name: 'classify', width: 170, align: 'right', editor: { type: 'text' }} 
                ], data: $.extend(true,{},customersData), pageSize: 20,
                toolbar: { items: [{ text: '自定义查询', click: itemclick, icon: 'search2'},
                					{ text: '添加书籍', click: addNewRow,icon:'add'},
                					{ text: '修改书籍', click: beginEdit, icon: 'modify'},
                					{ text: '提交修改', click: endEdit,icon:'ok'},
                					{ text: '取消修改', click: cancelEdit,icon:'communication'},
                					{ text: '取消全部', click: cancelAllEdit,icon:'prev'},
                					{ text: '删除选择的书籍', click: deleteRow,icon:'delete'}
                					]
                }, enabledEdit: true, clickToEdit: false, 
                width: '99.5%', height: '98%', checkbox: true
            });
            $("#pageloading").hide();
            $("#timeBegin").ligerDateEditor();
            $("#timeEnd").ligerDateEditor();
            $("#selectBook").ligerComboBox(
            {tree:{data:[
			    { "text": "计算机类", "children": [
			        { "text": "java" },
			        { "text": "c#" },
			        { "text": "c语言" }
			        ]
			    },
			    { "text": "语文" },
			    { "text": "数学" },
			    { "text": "英语" }
			], checkbox: false,width:200}
            });
        });
        //高级自定义查询
        function itemclick()
        {
            g.options.data = $.extend(true,{}, customersData);
            g.showFilter();
        }
        //修改书籍
        function beginEdit() {
           	var rows = manager.getSelectedRows();
            if (!rows[0]) { alert('请选择修改的学生'); return; }
           	for(i=0;i<rows.length;i++){
           		manager.beginEdit(rows[i]);
          	}
        }
        //取消修改
        function cancelEdit() {
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
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
            if (!row) { alert('请选择行'); return; }
            manager.endEdit(row);
            $.post(path() + "/book/UpdateBookServlet", {id:row.id,cardNumber:row.cardNumber,name:row.name,stock:row.stock,AStrongerStock:row.AStrongerStock,classify:row.classify}, function(data){
           		f_tip(data);
			});
        }
        //删除选择的书籍
        function deleteRow()
        {	
        	var rows = manager.getSelectedRows();
			if (!rows) { alert('请选择删除的书籍'); return; }
			var temp = "是否删除书籍";
			
			if(rows.length==1){
				$.get(path() + "/book/DeleteBookServlet", {id: rows[0].id}, function(data){
					if(data!=''){
						temp = data;
					}
					$.ligerDialog.confirm(temp, function (yes){
						if (yes){
							var rows = manager.getSelectedRows();
				        	$.post(path() + "/book/DeleteBookServlet", {id: rows[0].id+"and"}, function(data){
								f_tip(data);
							});
				            manager.deleteSelectedRow();
						}
			   		});
				});
			} else {
				var ids;
				for(i = 0;i<rows.length;i++){
					if(i==0){
						ids = rows[i].id;
					} else {
						ids = ids + "and" + rows[i].id;
					}
				}
				$.get(path() + "/book/DeleteBookServlet", {id:ids}, function(data){
						var tempChecking;
						var rows = manager.getSelectedRows();
						if(data!=''){
							tempChecking = "删除有借阅书籍,是否继续删除"
						} else{
							tempChecking = "是否删除本页所有数据"
						}
						
						$.ligerDialog.confirm(tempChecking,'是否删除', function (yes){
							var rows = manager.getSelectedRows();
							for(j = 0;j<rows.length;j++){
								if (yes){
									var rows = manager.getSelectedRows();
						        	$.post(path() + "/book/DeleteBookServlet", {id: rows[j].id}, function(data){
										f_tip(data);
									});
						            manager.deleteSelectedRow();
								}
							}
					    });
					});
			 }
        }
       
       	function deleteChecking(data){
       		
       	}
       
        //添加书籍
        function addNewRow()
        {
            var dialog = $.ligerDialog.open({ target: $("#target3"),width: 260,height:240,title:'添加书籍' });
        } 
        
        function close(dialog){
	        if(dialog == null){
	        	$("#Tip1017").css("display","none");
	        	$("#Tip1018").css("display","none");
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
	</script>
<script type="text/javascript">
	var eee;
	$(function() {
		$.metadata.setType("attr", "validate");
		var v = $("form").validate({
			//调试状态，不会提交数据的
			debug : true,
			errorPlacement : function(lable, element) {
				if (element.hasClass("l-textarea")) {
					element.addClass("l-textarea-invalid");
				}
				else if (element.hasClass("l-text-field")) {
					element.parent().addClass("l-text-invalid");
				}
				$(element).removeAttr("title").ligerHideTip();
				$(element).attr("title", lable.html()).ligerTip();
			},
			success : function(lable) {
				var element = $("#" + lable.attr("for"));
				if (element.hasClass("l-textarea")) {
					element.removeClass("l-textarea-invalid");
				}
				else if (element.hasClass("l-text-field")) {
					element.parent().removeClass("l-text-invalid");
				}
				$(element).removeAttr("title").ligerHideTip();
			},
			submitHandler : function() {
				$("#form").attr("action", path() + $("#form").attr("action"));
				document.getElementById("form").submit();
			}
		});
		$("form").ligerForm();
		$(".l-button-test").click(function() {
			alert(v.element($("#txtName")));
		});
	});
</script>
<style type="text/css">
.l-text-wrapper {
	float: left;
}

.fd {
	width: 205px;
}

.fd span {
	width: 60px;
	float: left;
	margin-right: 10px;
}

.l-text {
	width: 130px;
	float: left;
}

input {
	width: 130px;
}

.l-button {
	float: right
}

#target3 {
	width: 225px;
	margin: 3px;
	display: none;
}
</style>
</head>

<body>
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="maingrid4" style="margin: 0; padding: 0"></div>
	<div style="display: none;"></div>

	<div id="target3">
		<div>
			<form id="form" action="/book/SaveBookServlet?value=1" method="post">
				<div class="fd">
					<span style="text-align: right; float: left">书籍编号:</span> <input
						type="text" name='cardNumber' id="cardNumber"
						validate="{studentIdLength:[2,15],studentIdChecking:[cardNumber,'BookChecking']}"
						class="l-text" />
				</div>
				<div class="fd">
					<span style="text-align: right; float: left">书籍名称:</span> <input
						type="text" name='bookName' id="bookByName"
						validate="{studentIdLength:[2,15],studentIdChecking:[bookByName,'BookChecking']}"
						class="l-text" />
				</div>
				<div class="fd">
					<span style="text-align: right; float: left">书籍数量:</span> <input
						type="text" name='bookNumber'
						validate="{studentIdLength:[1,6],studentIdChecking:[bookNumber,'BookChecking']}"
						id="bookNumber" class="l-text" />
				</div>
				<div class="fd">
					<span style="text-align: right; float: left">书籍分类:</span> <input
						type="text" id="selectBook" validate="{notnull:true}"
						nullText="不能为空!" class="l-text" name='classify' />
				</div>
				<div class="fd">
					<input class="l-button" id="l-button" type="submit" value="提交" />
				</div>
			</form>
		</div>
	</div>
	<div class="l-verify-tip" id="Tip1017"
		style="left: 583px; top: 185px; display: none">
		<div class="l-verify-tip-corner"></div>
		<div class="l-verify-tip-content" style="width: 142px;"></div>
	</div>
	<div class="l-verify-tip" id="Tip1018"
		style="left: 583px; top: 245px; display: none">
		<div class="l-verify-tip-corner"></div>
		<div class="l-verify-tip-content" style="width: 142px;"></div>
	</div>
</body>
<script>
	$(function() {
		$(".l-text").css("width", "130px");
		$(".l-text-wrapper").css({
			float : "left",
			width : "130px"
		});
		if (window.parent.$("#top").html() == window.parent.$("#logoTop").html()) {
			$(".l-panel-btn").css("display", "block");
		}
		else {
			$(".l-panel-btn").css("display", "none");
			$(".l-toolbar-item-hasicon").css("display", "block");
		}
	})
</script>
</html>
