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
<!--   <p align="center">  
<img src="<s:property value='src'/>" border=0 usemap="#map0">  
</p> -->

  <%!  //加!就是全局了
    int count = 0;
%>
<%session.setAttribute("flag", "all"); %>
  <form name="gradeForm" action="">
  <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="text">
					<tr>
						<!--平均分-->
						<td width="120" height="25" align="center" class="menu">
							<a href="javascript:modify();">求平均</a>
						</td>
						<!--排名-->
						<td width="120" align="center" class="menu">
						
							<a href="query_byGradeDesc.action?order=yes&bj=${bj}&course=${course}">排名</a>
						</td>
						<td width="120" align="center" class="menu">
						<a href="javascript:createCaption()"  >及格率</a>
						</td>
						<!-- 导出excel -->
						<td width="120" align="center" class="menu">
						<a href="" id="export" >导出excel</a>
						</td>
						<td class="menu">
							
						</td>
					</tr>
					</table>
					
  <table  border="1" style="boder-color:red" class="datalist" id="gradeTable">
  <tr>
  <th><input type="checkbox" id="checkAll"/>全选</th>
   <s:if test="order!=null">
  <th><font color='red'>排名</font></th></s:if>
  <th>学号</th>
  <th>姓名</th>
  <th>班级</th>
  <th>科目</th>
  <th>分数</th>
 
  <th>操作</th>
   <img id="img" style="float:right;display:inline-block;" alt="统计图" src="">  
  
  </tr>
 	<input id="bj" type="hidden" name="bj" value="%{#grades.student.bj}">
 	<input id= "course" type="hidden" name="course" value="%{#grades.course}">
 

 	<s:iterator value="#grades">
				<tr>
				<td><input type="checkbox" name="gradeInfo" value="${grade }"/></td>
				<s:if test="order!=null">
					<td>
					
					 <%= ++count%>
					</td></s:if>
					<input type="hidden" name="id" value="${id }">
					<input type="hidden" name="grade" value="${grade }">
					<td>${student.sno }</td>
					<td>${student.name }</td>
					<td>${student.bj }</td>
					<td>${course }</td>
					<td><font color="red">${grade }</font></td>
					<!-- javascript:if(confirm('确定删除？')) location.href='grade_delete.action?id=%{id}&bj=%{bj}&course=%{course}' -->
					 <td><a href="javascript:if(confirm('确定删除${student.name }的${course }成绩？')) location.href='grade_delete.action?id=%{id}&bj=%{bj}&course=%{course}'" ><font style="text-decoration:none;">删除</font></a>
					  |<s:a action="grade_beforeModify?id=%{id}" ><font style="text-decoration:none;">修改</font></s:a>
					 </td>
					
					
					
				</tr>
	</s:iterator>
				<% count=0;%>
  </table>
  
   <h2><s:fielderror></s:fielderror></h2>
  </form>
 
  </body>

  <Script>
  $("#checkAll").click(
		  function(){
		    if(this.checked){
		        $("input[name='gradeInfo']").attr('checked', true);
		    }else{
		        $("input[name='gradeInfo']").attr('checked', false);
		    }
		  }
		);
  </Script>
  <Script>
  window.onload=function(){
	  var obj = $("input[name='id']");
		var s='';
  	for(var i=0; i<obj.length; i++){
  	 s+=obj[i].value+','; //取checkbox选中的值
  	}
  	$('#export').attr('href','grade_export.action?ids='+s);
  	$('#img').attr('src','<%=basePath%>/chart2_bar.action?ids='+s);
  	}   
    	
    	 
  	  
  function modify()
  {
  	
  //document.gradeForm.action="grade_avg.action";
  //document.gradeForm.submit();
  	var obj = $("input[name='gradeInfo']:checked");
  	var s=0;
  	for(var i=0; i<obj.length; i++){
  	if(obj[i].checked) s+=parseInt(obj[i].value); //取checkbox选中的值
  	}
  	window.wxc.xcConfirm(s/obj.length);
	   
	   
  };
 
  
 
  function createCaption()
  {
	var obj = $("input[name='grade']");
	var count=0;
	for(var i=0; i<obj.length; i++){
		if(obj[i].value>60){
		 count++;
	 }
	}
	var pass=count/obj.length;
  var x=document.getElementById('gradeTable').createCaption();
  x.innerHTML="<font color='red' align='center'>及格率:"+pass+"</font>";
  }

    </Script> 
   
  
</html>
