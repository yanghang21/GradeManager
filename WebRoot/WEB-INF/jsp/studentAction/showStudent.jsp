<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/table.css" />
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

    <title>学生信息</title>
    <script  src="${pageContext.request.contextPath}/script/jquery.js"></script>
    
    
	

  </head>
  
  <body style="margin:0px;" background="images/gradeshow.jpg" >
  <%!  //加!就是全局了
    int count = 0;
%>
  <form name="gradeForm" action="">
					
  <table  border="1" class="datalist">
  <tr>
  <th>序号</th>
  <th>学号</th>
  <th>姓名</th>
  <th>性别</th>
  <th>年龄</th>
  <th>系别</th>
  <th>班级</th>
  <th>联系电话</th>
  <th>操作</th>
  
  
  </tr>
 
 	<s:iterator value="#studentList">
				<tr>
				    <td><%=++count %>	</td>			
					<td>${sno }</td>
					<td>${name }</td>
					<td>${sex }</td>
					<td>${age }</td>
					<td>${dept }</td>
					<td>${bj }</td>
					<td>${phoneNum }</td>
					<td><a href="javascript:if(confirm('确定删除学生【${name }】？')) location.href='student_delete.action?id=%{id}'" >删除</a>
					|<s:a action="student_beforeModify?id=%{id}" >修改</s:a></td>
					
					
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
		        $("input[name='gradeInfo']").attr('checked', true);
		    }else{
		        $("input[name='gradeInfo']").attr('checked', false);
		    }
		  }
		);
  </Script>
  <Script>

    function modify()
    {
    //document.gradeForm.action="grade_avg.action";
    //document.gradeForm.submit();
    	var obj = $("input[name='gradeInfo']:checked");
    	var s='';
    	for(var i=0; i<obj.length; i++){
    	if(obj[i].checked) s+=obj[i].value+','; //取checkbox选中的值
    	}
 	    //传统的ajax校验  
 	    //1.创建异步交互对象，  
 	    var xhr = createXmlHttp();    
 	    //2设置监听  
 	    xhr.onreadystatechange = function(){  
 	        if(xhr.readyState == 4){  
 	            if(xhr.status == 200){  
 	            	if(xhr.responseText!="")
 	                alert(xhr.responseText);
 	            }  
 	        }  
 	    }
 	      
 	    //3打开连接  
 	    
 	    xhr.open("GET","${pageContext.request.contextPath}/grade_avg.action?gradeInfo="+s,true);  
 	    //4发送
 	    if(s!='')//请求条件:有勾选
 	    xhr.send(null);       
 	    }     
 	          
 	    //创建XmlHttp对象  
 	    function createXmlHttp(){  
 	        var xmlHttp;  
 	        try{  
 	            xmlHttp= new XMLHttpRequest();  
 	        }catch(e){  
 	            try{  
 	                xmlHttp=new ActiveXObject("Msxm12.XMLHTTP");  
 	            }catch(e){  
 	                try{  
 	                    xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");  
 	                }catch(e){}  
 	            }             
 	        }  
 	          
 	         return xmlHttp;      
    };
   
   
    </Script> 
</html>
