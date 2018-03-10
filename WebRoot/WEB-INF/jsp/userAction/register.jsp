<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>注册新用户</title>
	<style type="text/css">
	span{
	font-weight:bold;
	}
	#div1 {
  
  height: 130px;
  width: 600px;
  margin:2px;
  position: relative;
  top: 130px;
  left: 504px;
}
body{
	font-family: 'Oleo Script', cursive;
	font-size:20px
}


	</style>

	
<script  src="${pageContext.request.contextPath}/script/jquery.js"></script>   
	<script type="text/javascript">
	function check(){
		var password = document.getElementById("password").value;	
		var username = document.getElementById("username").value;	
		if(password==""|username==""){
		alert("请正确注册！");
		document.getElementById("username").focus;
		return false;
		}
		var number =$("#number").val();
		if(number!=''&&!isInteger(number)){
			alert("编号格式错误!");
			return false;
		}
		
		if(document.getElementById("div3").style.display!="none"&number.replace(/(^s*)|(s*$)/g, "").length ==0){
			alert("编号不能为空!");
			return false;
		}
		return true;
	}
	
	function checkPassword(){
		var password = document.getElementById("password").value;
		if(password.length==0){
			document.getElementById("span2").innerHTML = "<font color='red'>密码不能为空!</font>"; 
			document.getElementById("password").value = ""; 
			return;
		}
		if(password.length<6){
			document.getElementById("span2").innerHTML = "<font color='red'>密码长度过短!</font>";
			document.getElementById("password").value = ""; 
			return;
		}
		document.getElementById("span2").innerHTML = "<font color='green'>密码强度检测通过√</font>";
	}
	function checkUserName(){  
	    var username = document.getElementById("username").value;  
	    //传统的ajax校验  
	    //1.创建异步交互对象，  
	    var xhr = createXmlHttp();    
	    //2设置监听  
	    xhr.onreadystatechange = function(){  
	        if(xhr.readyState == 4){  
	            if(xhr.status == 200){  
	                document.getElementById("span1").innerHTML = xhr.responseText; 
	                if(xhr.responseText.indexOf("可以使用")==-1){
	                	document.getElementById("username").value="";
	                }
	            }  
	        }  
	    }
	      
	    //3打开连接  
	    xhr.open("GET","${pageContext.request.contextPath}/user_check.action?username="+username,true);  
	    //4发送  
	    xhr.send(null);       
	    }     
	          
	    //创建XmlHttp对象  
	    function createXmlHttp(){  
	        var xmlHttp;  
	        try{  
	            xmlHttp= new XMLHttpRequest();  
	        }catch(e){  
	            try{  
	                xmlHttp=new ActiveXObject("Msxm12.XMLHTTP");  
	            }catch(e){  
	                try{  
	                    xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");  
	                }catch(e){}  
	            }             
	        }  
	          
	         return xmlHttp;      
	    }  
	</script>
<!-- 学生老师有编号 -->
<script type="text/javascript">
function roleChange(){

	var  myselect=document.getElementById("role");

	var index=myselect.selectedIndex ;             // selectedIndex代表的是你所选中项的index

	var role = myselect.options[index].value;
	if(document.getElementById("div3").style.display=="none"&role!='辅导员'){
	     document.getElementById("div3").style.display="";
	   }else{
	if(role=='辅导员'){
		   
	   document.getElementById("div3").style.display ="none";
	}
	   }
	
}
</script>


</head>

<body background="images/register.jpg">

<div id="div1">

   <s:form action="user_add.action" onsubmit="return check()">
   <h3><font color="green">-------用户注册-------</font></h3>
   <td>
用户名: <s:textfield name="loginName" label="登陆名" id="username" onblur="checkUserName()"></s:textfield>*
<span id="span1"></span>
</td>
<br><br>
密&nbsp;&nbsp;码:  <s:password name="password" label="密码"  id="password" onblur="checkPassword()"></s:password>*

<span id="span2"></span><br><br>
<div id="div2">
角&nbsp;&nbsp;色:  <select id="role"  name="role" onchange="javascript:roleChange()">   
  <option   value="辅导员">辅导员</option>   
  <option   value="学生">学生</option>   
  <option   value="教师">教师</option>   
 </select><br><br>
<span id="div3" style="display:none"> 编&nbsp;&nbsp;号:&nbsp;<input id="number" type="text" name="number" /> *(必填！)</span>
</div>
        <!-- js无法找到该元素,尽量避免
<s:select  name="role" list="{'学生','教师','辅导员'}" id="role" onchange="roleChange()"/>*
        -->
        <br><input type="submit" value="确认注册" align="left"  />
       <input type="reset" value="重置" align="right" />
      
	</s:form>
</div>

</body>
<script>

function isInteger(obj) {
	 return obj%1 === 0;
	}
 </script>  
</html>



