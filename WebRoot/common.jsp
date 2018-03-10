<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    

  </head>
  
  <body>
  <br><span id="error" style=" color: red ;font-size: 14px;" >${error } </span>
  sfadf 
<s:if test="error == null"> 
<script  type="text/javascript">
var msg = "${error}";

alert(msg);
</script>
</s:if> 
  <s:debug></s:debug>
  </body>
</html>
