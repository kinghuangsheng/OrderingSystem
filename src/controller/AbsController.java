package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.response.Response;
import global.constant.Reason;

public class AbsController {
	private static Logger logger = Logger.getLogger(AbsController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(HttpServletRequest request, HttpServletResponse  
            httpResponse, Exception e) {  
		Response response = new Response();
		logger.error(request.getRequestURL(), e);
		response.setReason(Reason.INTERNAL_ERROR);
	    return response.toJsonString();  
	}  
	
}
