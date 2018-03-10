<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Login OA</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css" />
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
<link
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			document.forms[0].loginName.focus();
		});
		
		//在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
	</script>

</head>
<body>
<script  type="text/javascript">
var msg = "${error}";
if(msg!=''){
   
alert(msg);
msg='';
<% session.removeAttribute("error"); %>
}
</script>
<!-- 员工添加的模态框 -->
<div class="modal fade" id="userAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">用户注册</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label" for="empName_add_input">用户名</label>
		    <div class="col-sm-10">
		      <input type="text" name="loginName" class="form-control" id="loginName_add_input" placeholder="username">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">密码</label>
		    <div class="col-sm-10">
		      <input type="text" name="password" class="form-control" id="password_add_input" placeholder="password">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">角色</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		      <select class="form-control" name="role" id="role">
		       <option   value="辅导员">辅导员</option>   
			   <option   value="学生">学生</option>   
			   <option   value="教师">教师</option>  
		      </select>
		    </div>
		  </div>
		    <div class="form-group" style="display:none" id="num">
		    <label class="col-sm-2 control-label">编号</label>
		    <div class="col-sm-10">
		      <input type="text" name="number" class="form-control" id="number_add_input" placeholder="学号或教师号">
		      <span class="help-block"></span>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="user_save_btn">注册</button>
      </div>
    </div>
  </div>
</div>




<h1 align="right" style="font-family:楷体"><s:fielderror/></h1>
      
	<div class="lg-container">
		<h1 style="font-family:Arial">用户登陆</h1>
		<form action="user_login.action" id="lg-form" name="lg-form" method="post" onsubmit="return check()">
			
			<div style='width: 260px;white-space: nowrap;'>
				<label for="username">Username:</label>
				<input type="text" name="loginName" id="username" placeholder="username" />
			<span   class="glyphicon glyphicon-user" aria-hidden="true"></span>
			</div>
			
			<div style='width: 260px;white-space: nowrap;'>
				<label for="password">Password:</label>
				<input type="password" name="password" id="password" placeholder="password" />
			<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
			</div>
			<!-- 验证码 -->
			<div style='width: 50px;white-space: nowrap;'>
				<input name="code" id="code" placeholder="验证码" />
				<img id="img" alt="" src="<%=basePath%>VerifyServlet">
				<a href="javascript:_change();">换一张</a>
			</div>
			
			<div align="center">				
				<button type="submit" id="login" >登录</button>
			</div>
			<div>				
				<a href="javascript:viod(0)" id="user_add_modal_btn"><font size="4" color="blue"><span class="glyphicon glyphicon-registration-mark" aria-hidden="true"></span>注册新用户？</font></a>
			</div>
			
		</form>
		<div id="message"></div>
	</div>
</body>
<script>
		//清空表单样式及内容
		var user_check_info;
		function reset_form(ele){
			$(ele)[0].reset();
			//清空表单样式
			$(ele).find("#num").css("display","none");
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
		}
//点击新增按钮弹出模态框。
		$("#user_add_modal_btn").click(function(){
			//清除表单数据（表单完整重置（表单的数据，表单的样式））
			reset_form("#userAddModal form");
			//s$("")[0].reset();
			//发送ajax请求，查出部门信息，显示在下拉列表中
			
			//弹出模态框
			$("#userAddModal").modal({
				backdrop:"static"
			});
		});
		//校验表单数据
		function validate_add_form(){
			//1、拿到要校验的数据，使用正则表达式
			var role = $("#role").val();
			var number =$("#number_add_input").val();
			var loginName = $("#loginName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			if(!regName.test(loginName)){
				//alert("用户名可以是2-5位中文或者3-16位英文和数字的组合");
				show_validate_msg("#loginName_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
				return false;
				
			}
			/**else{
				show_validate_msg("#loginName_add_input", "success", "");
			};
			if($("#user_save_btn").attr("ajax-va")=="error")
			{
			show_validate_msg("#loginName_add_input", "error",user_check_info);
			return false;
			}*/
		
		
			//2、非导员注册，校验编号信息
			if(role!='辅导员'){
			if(!isInteger(number)|number==''){
				show_validate_msg("#number_add_input", "error", "编号只能是纯数字!");
				return false;
			}
			
			}
			
			
			//3、校验密码
			var password = $("#password_add_input").val();
			var regEmail = /^[0-9a-zA-Z_]{6,15}$/;
			if(!regEmail.test(password)){
				show_validate_msg("#password_add_input", "error", "密码可以是6-16位英文和数字的组合");
				return false;
			}else{
				show_validate_msg("#password_add_input", "success", "密码符合要求√");
			}
		   if(role=='辅导员'){//辅导员到此就能注册了
			   $("#user_save_btn").attr("ajax-va","success");
			   return true;
			   }
			if($(".has-error").length>0){//非辅导员检测是否还有框是error
				return false;
			};
			return true;
		}
		
		//显示校验结果的提示信息
		function show_validate_msg(ele,status,msg){
			//清除当前元素的校验状态
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if("success"==status){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}else if("error" == status){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		//角色选择切换，编号框重置
		$("#role").change(function reset(){
			$("#number_add_input").parent().removeClass("has-success has-error");
			$("#number_add_input").next("span").text("");
			$("#number_add_input").val("");
			
		});
		$("#number_add_input").change(function checkuser(){
			//4、校验教师和学生编号
			var number =$("#number_add_input").val();
			var role = $("#role").val();
			if(role!='辅导员'){
				if(!isInteger(number)){
					show_validate_msg("#number_add_input", "error", "编号只能是纯数字!");
					return;
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/user_checkNum.action",
					type:"GET",
					data:"_role="+role+"&num="+number,
					success:function(result){
						if(result.indexOf("true")==-1){
						show_validate_msg("#number_add_input","error","不存在的编号");
						$("#user_save_btn").attr("ajax-va","error");
						}
						else{
						show_validate_msg("#number_add_input","success","合法编号");	
						$("#user_save_btn").attr("ajax-va","success");
						}
					}
				});

				
			}
			
		});
		//校验用户名是否可用
		$("#loginName_add_input").change(function checkuser(){
			//发送ajax请求校验用户名是否可用
			var loginName = this.value;
			$.ajax({
				url:"${pageContext.request.contextPath}/user_check.action",
				data:"username="+loginName,
				type:"GET",
				success:function(result){
					user_check_info = result;
					if(result.indexOf("可以使用")==-1){
						show_validate_msg("#loginName_add_input","error",result);
						$("#user_save_btn").attr("ajax-va","error");
					}else{
						show_validate_msg("#loginName_add_input","success",result);
						$("#user_save_btn").attr("ajax-va","success");
					};				
				}
			});
		});
		//校验密码
		$("#password_add_input").change(function(){
			var password = $("#password_add_input").val();
			var regEmail = /^[0-9a-zA-Z_]{6,15}$/;
			if(!regEmail.test(password)){
				//应该清空这个元素之前的样式
				show_validate_msg("#password_add_input", "error", "密码可以是6-16位英文和数字的组合");
				/* $("#email_add_input").parent().addClass("has-error");
				$("#email_add_input").next("span").text("邮箱格式不正确"); */
				return false;
			}else{
				show_validate_msg("#password_add_input", "success", "密码符合要求√");
			}
		});
		//编号框隐藏显示条件
		$("#role").change(function(){
			var role = $("#role").val();
			if($("#num").css("display")=="none"&role!='辅导员'){
			     $("#num").css("display","");
			   }else{
			if(role=='辅导员'){		   
			   $("#num").css("display","none");
			}
			   }			
		});
		//点击保存，保存员工。
		$("#user_save_btn").click(function(){
			//1、模态框中填写的表单数据提交给服务器进行保存
			//1、先对要提交给服务器的数据进行校验
			if(!validate_add_form()){
				return false;
			};
			//1、判断之前的ajax用户名校验是否成功。如果成功。
			if($(this).attr("ajax-va")=="error"){
				return false;
			}
			alert("注册成功");
			//2、发送ajax请求保存员工
			$.ajax({
				url:"${pageContext.request.contextPath}/user_add.action",
				type:"POST",
				data:$("#userAddModal form").serialize(),
				success:function(result){
					//alert(result.msg);
					
						//员工保存成功；
						//1、关闭模态框				
				}
			});
			$("#userAddModal").modal('hide');
		});
		function isInteger(obj) {
			 return obj%1 === 0;
			}
</script>
<script>
function check(){
	var password = $("#password").val();	
	var username = $("#username").val();	
	if(password==""|username==""){
	alert("登陆项不能为空！");
	$("#username").focus();
	return false;
	}
}
</script>
<script>
function _change(){
	var img = $("#img");
	img.attr("src","<%=basePath%>VerifyServlet?a="+new Date().getTime());
	
}
</script>
</html>