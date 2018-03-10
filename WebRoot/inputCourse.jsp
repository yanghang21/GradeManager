<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
   <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
   <script type="text/javascript">
   function checkNull(){
	   var cname= $("#cname").val();
	   var credit= $("#credit").val();
	   if(cname==''){
		   alert("课程名不能为空！");
		   return false;
	   }
	   if(credit==''){
		   alert("学分不能为空！");
		   return false;
	   }
	   if(credit>10||credit<0||!IsNum(credit)){
		   alert("学分超过范围！");
		   return false;
	   }
	   
	   
   }
   function IsNum(num){
	   var reNum=/^\d*$/;
	   return(reNum.test(num));
	  }
   
   </script>
    
    <title>课程添加</title>
  

  </head>
  
  <body>
  <s:form action="course_add.action" onsubmit="return checkNull()">
  <table class="datalist">
  <tr>
 <th>课程名:</th><td><input type="textfield" name="cname" id="cname"/></td></tr>
<th> 学&nbsp;&nbsp;&nbsp;分:</th><td><input type="textfield"  name="credit" id="credit"/></td></tr>
<tr> <td></td><td><s:submit value="确认添加"></s:submit>
 <s:reset value="重置"></s:reset></td>
 </tr>
 </table>
 </s:form>
  </body>
</html>
