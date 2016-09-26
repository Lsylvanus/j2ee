var wincount = 0;
function title(title) {
	return title;
}

function path() {
	return base_path;
}

var aboutUS;
function f_open(url,obj) {
	if(aboutUS!=null){
		aboutUS.close();
		aboutUS.show();
	}
	aboutUS = $.ligerDialog.open({ height: 310, width: 340, url:url, showMax: false, showToggle: false, showMin: true, isResize: true, modal: false, title: "关于我们",isHidden:true });
}

//打开一个windos样式的窗口
var list = new Array(); 
var title = new Array(); 
function f_open1(url,obj) {
	for(i=0;i<title.length;i++){
		if(title[i]==$(obj).html()||title[i]==$(obj).children().html()){
			list[i].close();
		}
	}
	if($(obj).html()!="登陆系统"){
		if($(obj).children().html()=="借阅书籍"){
			list[list.length] = $.ligerDialog.open({ height: 600, url:url+"?values=1", width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: $(obj).children().html() });
		} else if($(obj).children().html()=="归还书籍"){
			list[list.length] = $.ligerDialog.open({ height: 600, url:url+"?values=2", width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: $(obj).children().html() });
		} else {
			list[list.length] = $.ligerDialog.open({ height: 600, url:url, width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: $(obj).children().html() });
		}
		title[i] = $(obj).children().html();
	} else {
		list[list.length] = $.ligerDialog.open({ height: 570, url:url, width: 990, showMax: false, showToggle: true, showMin: true, isResize: true, modal: false, title: "登陆系统" });
		title[i] = $(obj).html();
	}
}

function logoClose(){
	parent.$.ligerDialog.close(); 
	parent.checkingLogo();
}

function checkingLogo(){
	
	$.post("logo/LogoChecking", function(data){
		var index = 0;
		if(data=="n"&&location.pathname == path() + "/index.jsp"){
			window.location.href = "student.jsp";
			return;
		}
		var flag = data.substr(0,1);
		var name = data.substr(1,data.length);
		if(flag=="y"&&location.pathname != path() + "/index.jsp") {
			window.location.href = path() + "/index.jsp"; 
		} else if(flag=="n"&&location.pathname == path() + "/index.jsp"){
			window.location.href = path() + "/student.jsp";
		}
	},"html");
}

//弹出退出系统窗口
function f_open2(url,obj) {
	$.ligerDialog.confirm('退出系统','退出系统', function (yes)
    {
		if (yes)
			var url = path() + "/logo/quitBookManage";
			$.post(url, function(data){
				window.location.href = path() + "/student.jsp"; 
			});
    });
}


//只能输入数值
function inputInt(obj){
	$(obj).keydown(function(e){　　　
　　　　　　　　// 注意此处不要用keypress方法，否则不能禁用　Ctrl+V 与　Ctrl+V,具体原因请自行查找keyPress与keyDown区分，十分重要，请细查
                        if ($.browser.msie) {  // 判断浏览器
                               if ( ((event.keyCode > 47) && (event.keyCode < 58)) || (event.keyCode == 8) ) { 　// 判断键值 
                                      return true; 
                                } else { 
                                      return false; 
                               }
                         } else { 
                            if ( ((e.which > 47) && (e.which < 58)) || (e.which == 8) || (event.keyCode == 17) ) { 
                                     return true; 
                             } else { 
                                     return false; 
                             } 
                         }});
}

//验证学生信息
var oldTip=null;
function addStudentChecking(obj){
	var url = path() + "/student/StudentChecking";
	$.post(url, {value:obj.value}, function(data){
		
		if(oldTip==null){
			oldTip = $("#cardNumber").ligerTip({content:data});
		}else {
			$("#Tip1011 .l-verify-tip-content").html(data);
		}
		
		if(data=="卡号可用"){
			document.getElementById("l-button").type="submit";
		} else {
			document.getElementById("l-button").type="button";
		}
	});
}

//验证添加书籍信息
function addBookChecking(obj){
	var url = path() + "/book/BookChecking"
	$.post(url, {value:obj.value,title:obj.name}, function(data){
		if(obj.name=="cardNumber"){
			$("#Tip1017").css("display","block");
			$("#Tip1017 .l-verify-tip-content").html(data);
			if(data==""){
				$("#Tip1017").css("display","none");
			}
		}else if(obj.name=="bookNumber") {
			if(data=="成功"){
				$("#Tip1018").css("display","none");
			}else{
				$("#Tip1018").css("display","block");
			}
			$("#Tip1018 .l-verify-tip-content").html(data);
		}
		if($("#Tip1017 .l-verify-tip-content").html()=="书籍编号可用"&&$("#Tip1018 .l-verify-tip-content").html()=="成功"){
			document.getElementById("l-button").type="submit";
		} else {
			document.getElementById("l-button").type="button";
		}
	});
}

//验证借阅信息
function addBorrowChecking(obj,title){
	var url = path() + "/borrow/BorrowChecking";
	$.post(url, {title:title,value:obj.value}, function(data){
		if(title=="学生卡号"){
			$("#Tip1015").css("display","block");
			$("#Tip1015 .l-verify-tip-content").html(data);
		}
			
		if(data!="未找到该学生"){
			document.getElementById("l-button").type="submit";
		} else {
			document.getElementById("l-button").type="button";
		}
	});
}

//验证归还书籍
var studentChecking = null;
function addReturnChecking(obj,title){
	var error = ""; 
	$.ajax({
         type: 'post',
         url: path() + '/borrow/ReturnChecking',
         cache: false,
         data: "title="+title+"&value="+obj.value,
         dataType: 'json',
         success: function (jsonData)
         {
			if(jsonData[0].error=="400"&&jsonData!=[]){
				bookName.setData(jsonData);
			} else {
				error = jsonData[0].error;
			}
         }
     });
	return error;
}

function LikeChecking(obj){
	if(obj.value==""){
		return;
	}
	var url = path() + "/student/StudentLikeServlet";
	$.post(url, {value:obj.value}, function(_data){
		if(_data[0].id=="400"){
			student.setData(_data);
		}
	},"json");
}


var isie6 = window.XMLHttpRequest?false:true;
window.onload = function(){
var a = document.getElementById('top');
if(isie6){
a.style.position = 'absolute';
window.onscroll = function(){
d.innerHTML = '';
}
}else{
a.style.position = 'fixed';
}
a.style.right = '5';
a.style.bottom = '7';
}


