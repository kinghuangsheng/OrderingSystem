package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import db.pojo.User;
import global.constant.Constant;

public class PageRequestInterceptor implements HandlerInterceptor {

	
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
    	response.setContentType("text/html; charset=utf-8");
    	HttpSession httpSession = request.getSession();	
    	User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		if(null != user){
			return true;
		}else{
			response.sendRedirect("/" + Constant.PROJECT_NAME);
			return false;
		}
        
    }
    
}
