<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
<link
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	
<html>
<head></head>
<body style="background-color:#F0F8FF; margin:2px;">
<div class="list-group">
<s:iterator value="#session.user.privileges">
                <a href="javascript:void(0)" onclick="menuClick(this);" class="list-group-item nav-header" show="false" data-toggle="collapse" target="menuFrame">
                <span class="glyphicon glyphicon-triangle-right"  aria-hidden="true"></span>${name}</a>
                <ul id="dashboard-menu" class="nav nav-list collapse in" style="display:none">
                <s:iterator value="privilegeMap">
                    <li><a href="${value }" target="right"><span class="glyphicon glyphicon-share-alt" aria-hidden="true" ></span>${key }</a></li>
                </s:iterator>
                </ul>
  </s:iterator>          
            </div>
            </body>
<script >
    //层级效果
	function menuClick( menu ){
		if($(menu).attr("show")=="true"){
			$(menu).children("span:eq(0)").attr("class","glyphicon glyphicon-triangle-right");
			$(menu).attr("show","false");
		}else{
			$(menu).children("span:eq(0)").attr("class","glyphicon glyphicon-triangle-bottom");
			$(menu).attr("show","true");
		}
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
</html>