package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Response;
import global.constant.Reason;

public class AbsController {
	private static Logger logger = Logger.getLogger(AbsController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(HttpServletRequest request, HttpServletResponse  
            httpResponse, Exception e) {  
		
		Response response = new Response();
		if(e instanceof BindException){
			response.setReason(Reason.ERR_ARG);
		}else{
			response.setReason(Reason.INTERNAL_ERROR);
		}
		logger.error(request.getRequestURL(), e);
	    return response.toJsonString();  
	}  
	
}
