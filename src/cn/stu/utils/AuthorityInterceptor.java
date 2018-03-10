package cn.stu.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import cn.stu.base.BaseAction;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
@Override
public String intercept(ActionInvocation invocation) throws Exception {
ActionContext ctx = invocation.getInvocationContext();
Map<String,Object> session = ctx.getSession();
Object object = session.get("user");
HttpServletRequest request=(HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);     
String url=request.getServletPath();
String contextPath=request.getContextPath();   
if(url.equals("")) 
	url+="/"; 
if(object != null||url.startsWith("/user")){
return invocation.invoke();
}else{
return Action.LOGIN;
}
}

}