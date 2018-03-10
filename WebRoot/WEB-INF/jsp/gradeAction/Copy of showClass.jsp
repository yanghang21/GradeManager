<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
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
  <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="text">
					<tr>					
						<td width="120" align="center" class="menu">					
							<a href="javascript:pass()">及格率</a>					
						</td>			
						<td width="120" align="center" class="menu">					
							<a href="javascript:credit()">学分绩</a>					
						</td>			
					</tr>
					</table>
	<div id="div1" style="display:none;">
	<s:iterator value="#classes" var="bj">				
 <img id="img${bj}" style="float:left;display:inline-block;" alt="统计图" src="<%=basePath%>/chart2_spiderForClass.action?bj=${bj}">  
	</s:iterator>
	<img id="img" style="display:inline-block;" alt="统计图" src="<%=basePath%>/chart2_spiderForAllClass.action">  
	<br>（注：前面是各班的及格率，最后是各班级的及格率放在同一坐标系的对比）
	</div>			
					
	<div id="div2" style="display:none;">
	<img id="img" style="float:left;display:inline-block;" alt="统计图" src="<%=basePath%>/chart2_lineForAllClass.action"> 
	<br>
	<img id="img" style="float:left;display:inline-block;" alt="统计图" src="<%=basePath%>/chart2_barForAllClass.action">  
	</div>			
  </body>
  <Script>   	 
  	  
  function pass()
  {
  	
  //document.gradeForm.action="grade_avg.action";
  //document.gradeForm.submit();
  	
	$('#div1').attr('style','style=""');
	$('#div2').attr('style','display:none;');
  
	   
	   
  };
  	  
  function credit()
  {
  	
  //document.gradeForm.action="grade_avg.action";
  //document.gradeForm.submit();
  	
	$('#div1').attr('style','display:none;');
	$('#div2').attr('style','style=""');
  
	   
	   
  };
 

    </Script> 
   
  
</html>
