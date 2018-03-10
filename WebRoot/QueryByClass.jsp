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
  
  
  <s:form target="right" action="query_byClass.action" onsubmit="return test()">
  <s:if test="#session.user.role=='教师'" >
  科目:<s:textfield name="course" value="%{#session.teaCourse}" readonly="true"></s:textfield>*(必填项)
  </s:if>
  <s:else>科目:<s:textfield name="course"></s:textfield>*(必填项)</s:else>
  <br><br>
  班级:<s:textfield name="bj"></s:textfield>
  <br><br>
  <input type="submit" value="查询" onclick="javascript:window.close();" />
  </s:form>
  </body>
   <Script>
  function test(){
	  var course =$(":textfield[name='course']").val();
	  if(course==''){
		  alert("科目为必填项!");
		  return false;
	  }
	  return true;
	  
  }
  
  </Script>
</html>
