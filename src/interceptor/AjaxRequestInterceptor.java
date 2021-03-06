package interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import annotation.Permission;
import bean.Response;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;

public class AjaxRequestInterceptor implements HandlerInterceptor {

	
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception ex)
            throws Exception
    {
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView)
            throws Exception
    {
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
    	HttpSession httpSession = request.getSession();	
    	User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		if(null != user){
			if (handler instanceof HandlerMethod) {  
				Permission perssion = ((HandlerMethod) handler).getMethod().getAnnotation(Permission.class);  
	            if (perssion != null) {// 有权限控制的就要检查  
	            	ArrayList<String> userPrivileges = (ArrayList<String>)httpSession.getAttribute(Constant.MapKey.INTERFACES);
	            	if(userPrivileges.contains(perssion.value())){
	            		return true;
	            	}else{
	            		sendResponse(response);
	            		return false;
	            	}
	            }else{
	            	return true;
	            }
			}
			return true;
		}else{
    		sendResponse(response);
			return false;
		}
        
    }
    
    public void sendResponse(HttpServletResponse response) throws IOException{
    	response.setContentType("text/html; charset=utf-8");
    	Response responseData = new Response();
    	responseData.setReason(Reason.HAS_NO_PERSSION);
		PrintWriter out = response.getWriter();
        out.print(responseData.toJsonString());
        out.flush();
    }
    
}
