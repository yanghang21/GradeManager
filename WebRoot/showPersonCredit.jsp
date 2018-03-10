<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
   <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
   <%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  
    
    <title><%=request.getParameter("sno") %>的折线图</title>
  

  </head>
  
  <body>

  
 <img id="img" style="display:inline-block;" alt="统计图" src="">  
  </body>
   <script>
   window.onload=function(){
		 
		
		var sno = ${param.sno};//"<%=request.getParameter("sno") %>";
	  	$('#img').attr('src','<%=basePath%>/chart2_creditLine.action?snos='+sno);
	  	} ;  
   
   </script>
</html>
