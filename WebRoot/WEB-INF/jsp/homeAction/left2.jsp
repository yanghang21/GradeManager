<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
		
<html>

<head>

<script  src="${pageContext.request.contextPath}/script/jquery.js"></script>   
<style>

ul li a
{
  color:#000000;
  background:#f6f6f6;
  margin-right:5px; 
  margin-top:2px; 
  font-size:12px;
  font-family:verdana;
  text-decoration:none;
  display:block;
  width:60px;
  height:25px;
  line-height:25px;
  text-align:center;
  -webkit-border-radius:5px;
  -moz-border-radius:5px;
  border: 1px solid #560E00;
}


input
{
border:2px solid;
border-radius:25px;
-moz-border-radius:25px; /* Old Firefox */
color:red;
}
input:active {                         <!-- 这边是按下后的效果-->
    background-image: linear-gradient(bottom, rgb(62,184,229) 0%, rgb(44,160,202) 100%);
    background-image: -o-linear-gradient(bottom, rgb(62,184,229) 0%, rgb(44,160,202) 100%);
    background-image: -moz-linear-gradient(bottom, rgb(62,184,229) 0%, rgb(44,160,202) 100%);
    background-image: -webkit-linear-gradient(bottom, rgb(62,184,229) 0%, rgb(44,160,202) 100%);
    background-image: -ms-linear-gradient(bottom, rgb(62,184,229) 0%, rgb(44,160,202) 100%);
    background-image: -webkit-gradient(
    linear,
    left bottom,
    left top,
    color-stop(0, rgb(0,162,255)),
    color-stop(1, rgb(0,162,255))
    );}
</style>

</head>

<script >
    //层级效果
	function menuClick( menu ){
		$(menu).next().slideToggle(300);
	}

	//新开一个窗口
	function queryByClass() {
		window.open("QueryByClass.jsp", "", "location=no,toolbar=no,status=no,height=250,width=500,top=200px,left=430px");
	}
	//新开一个窗口
	function queryCredits() {
		window.open("QueryCredits.jsp", "", "location=no,toolbar=no,status=no,height=250,width=500,top=200px,left=430px");
	}
	
	//为精确查询新开一个窗口
	function queryByPerson(){
		window.open("QueryByPerson.jsp", "", "location=no,toolbar=no,status=no,height=250,width=500,top=200px,left=430px");
	}
	
</script>

<body style="background-color:#F0F8FF; margin:0px;">

<div class="title" style="color:#0A0A0A">功能列表:</div>

<ul >

<s:iterator value="#session.user.privileges">
<li>

<div  onClick="menuClick(this);">
<input type="button" value="${name}" />
</div>
							
<ul >
<s:iterator value="privilegeMap">
<li ><a  href="${value } " target="right">${key }</a></li>
</s:iterator>
</ul>

</li>
</s:iterator>
 
</ul>

</body>
</html>
