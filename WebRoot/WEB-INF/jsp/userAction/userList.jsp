<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/table.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script  src="${pageContext.request.contextPath}/script/jquery.js"></script>
    
    <title>My JSP 'userList.jsp' starting page</title>
  
  </head>
  
  <body>
    <%!  //加!就是全局了
    int count = 0;
%>
  <form action="">
  <table  border="1" class="datalist">
  <tr>
  <th>序号</th>
  <th>用户名</th>
  <th>角色</th>
  <th>操作</th>
  </tr>
 
 	<s:iterator value="#userList">
				<tr>
				<td><%=++count %></td>
			
					<td>${loginName }</td>
					<td>${role }</td>
					<td>
					<!--  user_delete.action?username=%{loginName}-->
					
					<a href="javascript:if(confirm('确定删除【${loginName }】？')) location.href='user_delete.action?username=%{loginName}'" >删除</a>&nbsp;|
					<s:a href="user_initialPassword.action?id=%{id}" >初始化密码</s:a>(123456)
					</td>
					
					
				</tr>
	</s:iterator>
	<%count = 0; %>			
  </table>
  </form>
  </body>
    <Script>
  $("#checkAll").click(
		  function(){
		    if(this.checked){
		        $("input[name='username']").attr('checked', true);
		    }else{
		        $("input[name='username']").attr('checked', false);
		    }
		  }
		);
  </Script>
</html>
