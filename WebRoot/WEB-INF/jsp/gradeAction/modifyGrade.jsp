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

    <title>成绩显示</title>
 
    
    
	

  </head>
  
  <body style="margin:0px;" background="images/gradeshow.jpg" >
  <s:form  action="grade_modify.action" method="post" onsubmit="return test()">
 
  
  <table  border="1" style="boder-color:red" class="datalist">
  <tr>
  <th>学号:</th><td><s:textfield name="sno" value="%{#grade.student.sno}"  readonly  = "true"></s:textfield></td>
  </tr>
  <tr>
  <th>班级:</th><td><s:textfield  name="bj" value="%{#grade.student.bj}" readonly = "true"></s:textfield></td>
  </tr>
  <tr>
  <th>科目:</th><td><s:textfield  name="course" value="%{#grade.course}" readonly = "true"></s:textfield></td>
  </tr>
  <tr>
  <th>分数:</th><td><s:textfield  name="grade" value="%{#grade.grade}"></s:textfield></td>
  </tr>	
  <tr><td></td>
  <td>
  <input type="submit" value="提交"/>&nbsp;&nbsp;
  <input type="button"  value="返回上一页" onclick="javascript:history.go(-1);"/></td>
  </tr>			
  </table>
  </s:form>

  </body>
  <Script>
function test(){
	var gra = $(":textfield[name='grade']").val();
    	if(!isInteger(gra)||gra<0||gra>150) {
    		alert("分数超过范围!"); //取checkbox选中的值
    		return false;
    	}
    	
	return true;
}
function isInteger(obj) {
	 return obj%1 === 0;
	}
   
    </Script> 

  
 
  
</html>
