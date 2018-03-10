<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>


<style type="text/css" media="screen">
.div{  display:inline}
 #login_click{ margin-top:2px;}  
#login_click input   
{  
      
  
    text-decoration:none;  
    background:#2f435e;  
    color:#f2f2f2;  
      
    padding: 5px 10px 5px 10px;  
    font-size:10px;  
    font-family: 微软雅黑,宋体,Arial,Helvetica,Verdana,sans-serif;  
    
    border-radius:3px;  
      
    -webkit-transition:all linear 0.30s;  
    -moz-transition:all linear 0.30s;  
    transition:all linear 0.30s;  
      
    }  
   #login_click a:hover { background:#385f9e; }  
  
</style> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script  src="${pageContext.request.contextPath}/script/jquery.js"></script>  
 
    <title></title>

  </head>
  
  <body>
<!-- 显示添加成功信息 -->
 <s:if test="error != ''"> 
<script  type="text/javascript">
var msg = "${error}";
if(msg!=''){
   
window.wxc.xcConfirm(msg, window.wxc.xcConfirm.typeEnum.info);
msg='';
<% session.removeAttribute("error"); %>
}

</script>
</s:if> 
  
  <form action="grade_add.action" name="hello" onsubmit="javascript:return testNull();">
  <div id="form1">
  学号:<input type="text" id="sno" name="sno" style="border:solid 1px #cc66cc" /><br><br>
 科目:<input name="course" style="border:solid 1px #cc66cc" /><br><br>
 分数:<input name="grade" id="grade" style="border:solid 1px #cc66cc" /><br><br>
 </div>
 <div id="login_click">  
 <input type="submit" class="button" value="逐条添加" />
 </div>  
 <hr>
 <span id="message" style="display:none;">
<s:actionmessage />
</span>
 <span style="font-size:20px;font-family:'楷体';color:red;">格式：学号——科目——分数</span>
 </form>
 
 <s:form action="grade_addMany.action" onsubmit="return testArea()">
 <div class="div">
<s:textarea id="txtarea" name= "manyGrade" rows="18" cols="40"style="border:solid 1px #8B2323"></s:textarea>
</div>
 <div id="login_click" class="div">  
 <input type="submit" class="button" value="批量添加" />
 </div> 
  </s:form>

  </body>
    <Script>
function testNull(){
	var txt = $("#form1").find("input");
	for(var i=0; i<txt.length; i++){
    	if(txt[i].value=='') {
    		alert("表单不能为空!"); //取checkbox选中的值
    		return false;
    	}
    	}

	var sno = $("#sno").val();
	var grade = $("#grade").val();
	if(!isInteger(sno)||sno==''){
		alert("学号格式不正确!");
		return false;
	}
	if(!isInteger(grade)||grade>150||grade<0){
		alert("成绩格式不正确!");
		return false;
	}
	return true;
}

   
function testArea(){
	var txt = $("#txtarea").val();
    	if(txt=='') {
    		alert("批量添加表单不能为空!"); //取checkbox选中的值
    		return false;
    	}
  
    	
	return true;
}
function isInteger(obj) {
	 return obj%1 === 0;
	}
 
   
    </Script> 
</html>
