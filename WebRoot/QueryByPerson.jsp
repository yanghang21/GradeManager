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
    <title>按个人</title>
   

  </head>
  
  <body>
  <s:form target="right" action="query_byPerson.action" onsubmit="return test()">
 <s:if test="#session.user.role=='学生'" >
  学号:<s:textfield name="sno" value="%{#session.user.number}" readonly = "true"></s:textfield>*(必填项)
 </s:if>
 <s:else>
  学号:<s:textfield name="sno"></s:textfield>*(必填项)
  </s:else>
  <br><br>
 科目:<s:textfield name="course"></s:textfield>
  <br><br>
  <input type="submit" value="查询" onclick="javascript:window.close();" />
  </s:form>
  </body>
   <Script>
  function test(){
	  var sno =$(":textfield[name='sno']").val();
	  if(sno==''){
		  alert("学号为必填项!");
		  return false;
	  }
		if(!isInteger(sno)) {
    		alert("学号格式错误!"); //取checkbox选中的值
    		return false;
    	}
	  return true;
	  
  }
  function isInteger(obj) {
		 return obj%1 === 0;
		}
	   
  
  </Script>
</html>
