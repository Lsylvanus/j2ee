<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="head.jsp"%>
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	font-size: 12px;
	background: url("image/logn.jpg");
}

.form {
	width: 390px;
	height: 160px;
	float: left;
}

form {
	width: 565px;
	height: 270px;
	margin: 0 auto;
	float: left;
}

.h125 {
	height: 230px;
	width: 100px;
}

.h20 {
	height: 22px;
}

.h25 {
	height: 25px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit, .l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.submitLogn {
	cursor: pointer;
}

.submit {
	width: 100px;
	overflow: hidden;
	cursor: pointer;
}
</style>
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
            $(".l-text").css({width:"263px",border:"none",height:"25px"});
            $("input").css("width","263px");
			$("#Button1").css({width:"100px"});
        });
    </script>
</head>

<body>
	<div class="h125"></div>
	<div class="form"></div>
	<form id="form" method="post" action="/logo/LogoServlet">
		<div class="h100"></div>
		<div class="logn">
			<input name="logo" id="logo"
				validate="{studentIdLength:[2,10],lognChecking:true}" type="text" />
		</div>
		<div class="h20"></div>
		<div class="password">
			<input name="password" id="password"
				validate="{studentIdLength:[2,10]}" type="password" />
		</div>
		<div class="h25"></div>
		<div class="submit">
			<input type="submit" value=""
				validate="{studentIdLength:[2,10],lognChecking:true}" id="Button1"
				class="submitLogn" style="width: 100px" />
		</div>
	</form>
	<div style="display: none">
		<!--  数据统计代码 -->
	</div>
</body>
</html>