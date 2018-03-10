<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/table.css" />
<style type="text/css">
.menu {
	font-family: "方正舒体";
	font-size: 18pt;
	background-color: #9AFF9A;
	border: 1px dashed #000066;
	color: #CCCCCC;
}
</style>
<html>
  <head>

    <title>个人信息修改</title>
    <script  src="${pageContext.request.contextPath}/script/jquery.js"></script>
    
    
	

  </head>
  
  <body style="margin:0px;" background="images/gradeshow.jpg" >
  <%!  //加!就是全局了
    int count = 0;
%>
  <form name="gradeForm" action="student_modify.action" onsubmit="return testNull()">
					
  <table  border="1" class="datalist">
  <tr>
 
  <th>学号</th> <td><s:textfield name="sno" value="%{#student.sno}"  readonly  = "true"></s:textfield></td>
  </tr>
  <tr>
 
  <th>姓名</th> <td><s:textfield name="name" value="%{#student.name}"  ></s:textfield></td>
  </tr>
  <tr>
 
  <th>性别</th> <td><s:textfield name="sex" value="%{#student.sex}"  ></s:textfield></td>
  </tr>
  <tr>
 
  <th>年龄</th> <td><s:textfield id="age" name="age" value="%{#student.age}"  ></s:textfield></td>
  </tr>
  <tr>
 
  <th>系别</th> <td><s:textfield name="dept" value="%{#student.dept}"  ></s:textfield></td>
  </tr>
  <tr>
 
  <th>班级</th> <td><s:textfield name="bj" value="%{#student.bj}"  ></s:textfield></td>
  </tr>
  <tr>
 
  <th>联系电话</th> <td><s:textfield name="phoneNum" value="%{#student.phoneNum}"  ></s:textfield></td>
  </tr>
  <tr>
  <td></td>
 <td>
 <s:submit value="确认修改"></s:submit>&nbsp;&nbsp;<input type="button"  value="返回上一页" onclick="javascript:history.go(-1);"/>
 </td>
  </tr>
  </table>
  </form>

  </body>

  <Script>
function testNull(){
	var txt = $(":textfield");
	var age = $("#age").val();
	for(var i=0; i<txt.length; i++){
    	if(txt[i].value=="") {
    		alert("请正确填写!"); //取checkbox选中的值
    		return false;
    	}
    	}
	if(!isInteger(age)){
		alert("年龄格式不正确！");
		return false;
	}
	return true;
}
function isInteger(obj) {
	 return obj%1 === 0;
	}
   
    </Script> 
</html>
