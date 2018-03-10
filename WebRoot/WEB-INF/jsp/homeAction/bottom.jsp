<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<script  src="${pageContext.request.contextPath}/script/jquery.js"></script>  
<style>
span.date
{
position:relative;
left:1000px;
}
</style>
   
</head>

<body style="background-color:#F5F5F5; margin:0px;">

<h1></h1>

  <div >
当前用户:<span  style="font-size:20px;font-family:'楷体';color:red;">&nbsp;${user.loginName }</span>
<br>
角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:<span  style="font-size:20px;font-family:'楷体';color:red;">&nbsp;${user.role }</span>

<span  class="date" text-align="center" id="DIV1" style="font-size:20px;font-family:'楷体';color:red;" ></span>
</div>




</body >
  <SCRIPT TYPE="text/JAVASCRIPT">
	  function tim()
	  {
	  var tm=new Date().toLocaleString();
	  var div2=document.getElementById("DIV1");
	  div2.innerHTML=tm;
	  }
  tim();
  setInterval("tim();",1000);
  </SCRIPT>
</html>
