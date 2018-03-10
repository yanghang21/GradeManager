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

    <title>学分绩显示</title>
 <style type="text/css">

/* CSS For Dropdown Menu Start */
ul
{
  list-style:none;
  padding:0px;
  margin:0px
}

ul li
{
  display:inline;
  float:left;
}

ul li a
{
  color:#ffffff;
  background:green;
  margin-right:5px;
  font-weight:bold;
  font-size:12px;
  font-family:verdana;
  text-decoration:none;
  display:block;
  width:100px;
  height:25px;
  line-height:25px;
  text-align:center;
  -webkit-border-radius:5px;
  -moz-border-radius:5px;
  border: 1px solid #560E00;
}

ul li a:hover
{
  color:#cccccc;
  background:#560E00;
  font-weight:bold;
  text-decoration:none;
  display:block;
  width:100px;
  text-align:center;
  -webkit-border-radius:5px;
  -moz-border-radius:5px;
  border: 1px solid #000000;
}

ul li.sublinks a
{
  color:#000000;
  background:#f6f6f6;
  border-bottom:1px solid #cccccc;
  font-weight:normal;
  text-decoration:none;
  display:block;
  width:100px;
  text-align:center;
  margin-top:2px;
}

ul li.sublinks a:hover
{
  color:#000000;
  background:#FFEFC6;
  font-weight:normal;
  text-decoration:none;
  display:block;
  width:100px;
  text-align:center;
}

ul li.sublinks
{
	display:none;
}

/* CSS For Dropdown Menu End */



#container
{
  margin:0px auto;
  width:100px;
}

.clear
{
  clear:both;
}

.left
{
  float:left;
}

.right
{
  float:right;
}
</style>
    
    
	

  </head>
  
  <body style="margin:0px;" background="images/gradeshow.jpg" >
  <% //加!就是全局了
  HttpSession s = request.getSession(); 
 	s.setAttribute("i",0);
%>
<%!int count=0; %>
  <form name="gradeForm" action="">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="text">
					<tr>
						<!--平均分-->
						<td width="120" height="25" align="center" class="menu">
						<ul>	<li><a href="credit_byCreditDesc.action?bj=${bj}&condition=CREDIT">学分绩排名</a><li>
						</ul>
						</td>
						<td width="120" height="25" align="center" class="menu">
						<ul>
							<li><a href="credit_byCreditDesc.action?bj=${bj}&condition=_SUM">总分排名</a><li>
						</ul>
						</td>
						
						<td width="120" height="25" align="center" class="menu">
						<ul>
						<li>	<a href="credit_byCreditDesc.action?bj=${bj}&condition=_AVG">平均分排名</a></li>
						</ul>
						</td>
						<td width="120" height="25" align="center" class="menu">
							 <div id="container" >
	
	<!-- First Menu Start -->
	<ul>
		<li><a href="#" class="dropdown">学科排名</a></li>
		
		<li class="sublinks">
			<a href="credit_byCreditDesc.action?bj=${bj}&condition=yw">语文</a>
			<a href="credit_byCreditDesc.action?bj=${bj}&condition=sx">数学</a>
			<a href="credit_byCreditDesc.action?bj=${bj}&condition=yy">英语</a>
			<a href="credit_byCreditDesc.action?bj=${bj}&condition=wl">物理</a>
			<a href="credit_byCreditDesc.action?bj=${bj}&condition=hx">化学</a>
			<a href="credit_byCreditDesc.action?bj=${bj}&condition=sw">生物</a>
		</li>
		
	</ul>
	</div>
						</td>
						
						<!-- 导出excel -->
						<td width="120" align="center" class="menu">
						<a href="credit_export.action?bj=${bj }&condition=_SUM" id="export">导出excel</a>
						</td>
						<td class="menu">&nbsp;
							
						</td>
					</tr>
					</table>
					
  <table  border="1" style="boder-color:red" class="datalist" id="table1">
  <thead >
  <th>序号</th>
  <th>学号</th>
  <th>姓名</th>
  <th>班级</th>
  <th>语文</th>
  <th>英语</th>
  <th>生物</th>
  <th>物理</th>
  <th>数学</th>
  <th>化学</th>
  <th>总分</th>
  <th>平均分</th>
  <th>学分绩</th>
</thead>  
    
       <s:iterator value="grades" >
      <input type="hidden" name="id" value="${id }">
      <tr>
    <td ><%=(++count) %></td>
    <td >${sno }</td>
    <td>${name }</td>
    <td>${bj }</td>
    <td>${yw }</td>
    <td>${yy }</td>
    <td>${sw }</td>
    <td>${wl }</td>
    <td>${sx }</td>
    <td>${hx }</td>
    <td>${_sum }</td>
    <td>${_avg }</td>
    <td>${credit }</td>
    
      
     
      </tr>
       </s:iterator>	
 
<%count=0; %>

				
  </table>
   <h2><s:fielderror></s:fielderror></h2>
  </form>

  </body>

  <Script>

 
  $("table tr:gt(0)").each(function(i){
	    $(this).children("td").each(function(i){
	       var text=$(this).text();
	        if(text==-1){
	        	$(this).html("<font color='purple' size='3'>缺考</font>");	        	
	        }
	        if(text<60&text>=0){
	        	$(this).html("<font color='red'>"+text+"</font>");
	        	 	
	        }
	        
	    });
	});
  $(document).ready(function(){
	  $("#table1 tr").bind("click",function(){
		  var sno =$(this).find("td").eq(1).text();		  
		  if(sno!='')
		  window.open("showPersonCredit.jsp?sno="+sno, "_blank", "height=420,width=470,top=200px,left=430px,toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	  });
	  });
    </Script> 
  <script type="text/javascript">

$(function(){
	$('.dropdown').mouseenter(function(){
		$('.sublinks').stop(false, true).hide();
	
		var submenu = $(this).parent().next();

		submenu.css({
			position:'absolute',
			top: $(this).offset().top + $(this).height() + 'px',
			left: $(this).offset().left + 'px',
			zIndex:1000
		});
		
		submenu.stop().slideDown(300);
		
		submenu.mouseleave(function(){
			$(this).slideUp(300);
		});
	});
});

window.onload=function(){
	  var obj = $("input[name='id']");
		var s='';
	for(var i=0; i<obj.length; i++){
	 s+=obj[i].value+','; //取checkbox选中的值
	$('#export').attr('href','credit_export.action?ids='+s);
	} 
};
  	
</script>
</html>
