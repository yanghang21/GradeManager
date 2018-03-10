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

    <title>课程列表</title>
   
    
    
	

  </head>
  
  <body style="margin:0px;" background="images/gradeshow.jpg" >

  <form name="gradeForm">  
					
  <table  border="1" class="datalist">
  <tr>
  <th>课程名</th>
  <th>学分</th>
  <th>任课教师</th>
  <th>状态</th>
  <th>操作</th> 
  </tr>
 	
 
 	<s:iterator value="#courseList" var="first">
				
				<tr>
					<td>${cname }</td>
					<td>${credit }</td>
					<td>
				   <s:iterator value="teachers">
                		${name}、
                	</s:iterator>
                	</td>
					<td>
				   <s:iterator value="students">
                		${sno==sessionScope.user.number?'已选':''}  
                	</s:iterator>
                	</td>
                	
                	<td>
					<a href="student_selectCourse.action?id=${id }" >选定课程</a>
					|&nbsp;<a href="student_removeCourse.action?id=${id }" >退选课程</a>
					</td>
					
				</tr>
				
	</s:iterator>
  </table>
  </form>
  </body>

  <Script>

  </Script>
</html>
