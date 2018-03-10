package cn.stu.utils;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

import com.opensymphony.xwork2.ActionContext;

import cn.stu.domain.User;
 /**
  * �жϷ��ʷǵ�½ҳ��ʱ�Ƿ��½ 
  * @author yh
  *
  */
public class LoginFilter extends HttpServlet implements Filter {  
    public void destroy() {  
    }  
  
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse,        
            FilterChain filterChain) throws IOException, ServletException{  
          
        HttpServletRequest request = (HttpServletRequest) sRequest;        
        HttpServletResponse response = (HttpServletResponse) sResponse;        
        HttpSession session = request.getSession();        
        String url=request.getServletPath();    
        String contextPath=request.getContextPath();   
        if(url.equals("")) 
        	url+="/"; 
        if(!url.startsWith("/index.jsp")){//�����ʺ�̨��Դ ���˷ǵ�½����    
             User user=(User)ActionContext.getContext().getSession().get("user");
             if(user==null){//ת�����Ա��½ҳ��    
                  response.sendRedirect(contextPath+"/index.jsp");   
                  return;    
             }    
        }    
          filterChain.doFilter(sRequest, sResponse);      
    }    
  
    public void init(FilterConfig arg0) throws ServletException {  
  
    }  
}  