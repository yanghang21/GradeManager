<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <script  src="${pageContext.request.contextPath}/script/jquery.js"></script>  
    <title>按班级</title>
   

  </head>
  
  <body>
  <s:form target="right" action="credit_credits.action" >
  班级:<s:textfield name="bj"></s:textfield>*(若不填，查询出全年级学分绩!)
  <br><br>
  <input type="submit" value="查询" onclick="javascript:window.close();" />
  </s:form>
  </body>

</html>
