<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
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

    <title>教学关联</title>
   
    
    
	

  </head>
  
  <body style="margin:0px;" background="images/gradeshow.jpg" >

  <form name="gradeForm">  
					
  <table  border="1" class="datalist">
  <tr>
  <th>教师名</th>
  <th>所教课程</th>
  <th>操作</th> 
  </tr>
 	
 
 	<s:iterator value="#teacherList">
				
				<tr>
					<td>${name }</td>
					<td>${course }</td>
					
					<td><a href="course_conTeacher.action?cname=${cname }&id=${id}" target="right" onclick="javascript:window.close();">关联</a>
					|<a href="course_removeTeacher.action?cname=${cname }&id=${id}" target="right" onclick="javascript:window.close();">解除关联</a></td>
				
					
					
				</tr>
				
	</s:iterator>
  </table>
  </form>
  </body>

  <Script>

  </Script>
</html>
