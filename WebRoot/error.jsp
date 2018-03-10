<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>error page</title>
    
	

  </head>
  
  <body>
 <h2>请严格按照<font color="red" size="20">学号--课程--成绩</font>格式要求进行批量录入！！！</h2><br>
 (4秒后自动跳回到上一个页面.....)
     <% response.setHeader("Refresh","3;URL=inputGrade.jsp");%>
  </body>
</html>
