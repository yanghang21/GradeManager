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
  <th>操作</th> 
  </tr>
 	
 
 	<s:iterator value="#courseList">
				
				<tr>
					<td>${cname }</td>
					<td>${credit }</td>
					<td>
				<s:iterator value="teachers">
                		${name}、
                	</s:iterator>
                	</td>
					<td><s:a href="#" >删除课程</s:a><!-- course_delete?id=%{id}此功能暂时废除 -->
					|<a href="JavaScript:window.open ('course_addTeacher.action?
					cname=${cname }',
					'', 'location=no,toolbar=no,status=no,height=250,width=500,top=200px,left=430px')" >关联教师</a>
					</td>
					<!-- %=java.net.URLEncoder.encode("世界杯","utf-8") tomcat 默认 iso8891,配置server.xml改掉或者url编码直接改% -->
				</tr>
				
	</s:iterator>
  </table>
  </form>
  </body>

  <Script>

  </Script>
</html>
