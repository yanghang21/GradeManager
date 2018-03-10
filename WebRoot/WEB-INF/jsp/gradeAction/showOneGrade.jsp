<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<style type="text/css">
.menu{
	font-family: "方正舒体";
	font-size: 18pt;
	background-color: #9AFF9A;
	border: 1px dashed #000066;
	color: #CCCCCC;
}
font[name='caption'] {
	font-family: "方正舒体";
	font-size: 18pt;
	border: 0px dashed #000066;
	color: green;
}
</style>
<html>
  <head>

    <title>成绩显示</title>
 
    
    
	

  </head>
  
  <body style="margin:0px;" background="images/gradeshow.jpg" >
  <%!  //加!就是全局了
    int count = 0;
%>
<%session.setAttribute("flag", "one"); %>
  <form name="gradeForm" action="">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="text">
					<tr>
						<!--平均分-->
						<td width="120" height="25" align="center" class="menu">
							<a href="javascript:modify();">求平均</a>
						</td>
						
						<!-- 导出excel -->
						<td width="120" align="center" class="menu">
						<a href="" id="export" >导出excel</a>
						</td>
						<td class="menu">&nbsp;
							
						</td>
					</tr>
					</table>
					
  <table  border="1" style="boder-color:red" class="datalist">
  <tr>
  <th><input type="checkbox" id="checkAll"/>全选</th>
   <s:if test="order!=null">
  <th><font color='red'>排名</font></th></s:if>
  <th>学号</th>
  <th>姓名</th>
  <th>班级</th>
  <th>科目</th>
  <th>分数</th>
  <s:if test="#session.user.role!='学生'" >
  <th>操作</th>
  </s:if>
  
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
					<td>${student.sno }</td>
					<td>${student.name }</td>
					<td>${student.bj }</td>
					<td>${course }</td>
					<td><font color="red">${grade }</font></td>
					  <s:if test="#session.user.role!='学生'" >
					 <td><a href="javascript:if(confirm('确定删除【${student.name }】的【${course }】成绩？')) location.href='grade_delete.action?id=${id}&sno=${sno}'" ><font style="text-decoration:none;">删除</font></a>
					  |<s:a action="grade_beforeModify?id=%{id}" ><font style="text-decoration:none;">修改</font></s:a>
					 </td>
					 </s:if>
					
					
					
				</tr>
	</s:iterator>
				<% count=0;%>
  </table>
  <br>
   <table  border="1" style="boder-color:red" class="datalist">
   <caption><font name="caption">单科班级排名</font></caption>
   <tr><th>语文</th><th>数学</th><th>英语</th><th>物理</th><th>化学</th><th>生物</th></tr>
   <tr><td>${credit.ywR==0?'缺考':credit.ywR}</td>
   <td>${credit.sxR==0?'缺考':credit.sxR }</td>
   <td>${credit.yyR==0?'缺考':credit.yyR }</td>
   <td>${credit.wlR==0?'缺考':credit.wlR }</td>
   <td>${credit.hxR==0?'缺考':credit.hxR }</td>
   <td>${credit.swR==0?'缺考':credit.swR }</td></tr>
   </table>
   <br>
   <table  border="1" style="boder-color:red" class="datalist">
   <caption><font name="caption">总成绩班级排名</font></caption>
   <tr><th>平均分</th><th>总分</th><th>学分绩</th><th>班级总人数</th></tr>
   <tr>
   <td><font color="red">${credit._avgR==0?'这个人':credit._avgR }</font></td>
   <td><font color="red">${credit._sumR==0?'啥都':credit._sumR }</font></td>
   <td><font color="red">${credit.creditR==0?'没有考':credit.creditR }</font></td>
   <td>${count }</td>
   </tr>
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
	var s='';
	var obj = $("input[name='id']");
  	for(var i=0; i<obj.length; i++){
  	 s+=obj[i].value+','; //取checkbox选中的值
  	}
  	$('#export').attr('href','grade_export.action?ids='+s);
  	$('#img').attr('src','<%=basePath%>/chart2_spider.action?ids='+s);

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
   
   
    </Script> 
  
</html>
