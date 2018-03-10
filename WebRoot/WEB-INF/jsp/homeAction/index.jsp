<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>StuMa主页面</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>

</head>

	<frameset rows="90,*,55" framespacing=0 border=1 frameborder="1">
		
		<frame noresize name="TopMenu" scrolling="no" src="${pageContext.request.contextPath}/home_top.action">
		<frameset cols="150,*" id="resize">
		<frame noresize name="menu" scrolling="yes" src="${pageContext.request.contextPath}/home_left.action">
			
			<frame noresize name="right" scrolling="yes" src="${pageContext.request.contextPath}/home_right.action">
			
		</frameset>
		    <frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/home_bottom.action">
		
	</frameset>

	<noframes><body>
</body>
</noframes></html>



