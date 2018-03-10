<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
      <script  src="${pageContext.request.contextPath}/script/jquery.js"></script>

  </head>
  
  <body>
  <form action="student_add.action" onsubmit="javascript:return test();">
  <table class="datalist">
 <tr> <th>学号:</th><td><s:textfield name="sno"></s:textfield></td></tr>
 <tr> <th>姓名:</th><td><s:textfield name="name"></s:textfield></td></tr>
 <tr> <th>性别:</th><td><s:textfield name="sex"></s:textfield></td></tr>
 <tr> <th>年龄:</th><td><s:textfield name="age"></s:textfield></td></tr>
 <tr> <th>系别:</th><td><s:textfield name="dept"></s:textfield></td></tr>
 <tr> <th>班级:</th><td><s:textfield name="bj"></s:textfield></td></tr>
 <tr> <th>电话:</th><td><s:textfield name="phoneNum"></s:textfield></td></tr>
 <tr><td></td><td><s:submit value="确认添加" style="background-color:#FF8C69"></s:submit>
 <s:reset value="重置"></s:reset></td></tr>
 </table>
 </form>
  </body>
  <Script>
function test(){
	var txt = $(":textfield");
	var age = $(":textfield[name='age']").val();
	var sno = $(":textfield[name='sno']").val();
	for(var i=0; i<txt.length; i++){
    	if(txt[i].val()=="") {
    		alert("请正确填写!"); //取checkbox选中的值
    		return false;
    	}
    	}
	if(!isInteger(age)){
		alert("年龄格式不正确!");
		return false;
	}
	if(!isInteger(sno)){
		alert("学号格式不正确!");
		return false;
	}
	return true;
}
function isInteger(obj) {
	 return obj%1 === 0;
	}
  

 </Script>  
</html>
