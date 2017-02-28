package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import global.constant.Constant;

public class PageRequestInterceptor implements HandlerInterceptor {

	
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception ex)
            throws Exception
    {
        System.out.println("afterCompletion");
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView)
            throws Exception
    {
        System.out.println("postHandle");
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle)
            throws Exception
    {
    	
    	String uri = request.getRequestURI();
    	System.out.println("preHandle uri =  " + uri);
    	HttpSession httpSession = request.getSession();	
    	if(uri.contains("index")){
    		return true;
    	}else if(("/"+ Constant.PROJECT_NAME + "/user/login").equals(uri)){
    		return true;
    	}
    	else{
    		String username = (String) httpSession.getAttribute(Constant.USER_NAME);
    		if(null != username){
    			System.out.println("session username = " + username);
    			return true;
    		}else{
    			System.out.println("session username = " + username);
    			response.sendRedirect("http://localhost:8080/" + Constant.PROJECT_NAME + "/page/index.html");
    			return false;
    		}
    	}
    	
        
    }
    
}
