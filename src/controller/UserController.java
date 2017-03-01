package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.response.LoginResponse;
import common.util.LogUtil;
import common.util.Object2JsonUtil;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import service.IUserService;

@Controller
@RequestMapping("/ajax/user")
public class UserController {

	
	@Resource  
    private IUserService userService;  
	
	
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String username, String password, HttpSession httpSession,
			@CookieValue(value = "username", required = false) String cookieUserName) {
		LogUtil.info("  username = " + username + "  password = " + password + "  cookieUserName = " + cookieUserName);
		if (isValid(username, password)) {
			httpSession.setAttribute(Constant.USER_NAME, username);
			LoginResponse response = new LoginResponse();
			return Object2JsonUtil.toJsonString(response);
		} else {
			LoginResponse response = new LoginResponse(Reason.USER_NOT_EXIST);
			return Object2JsonUtil.toJsonString(response);
		}

	}

	private boolean isValid(String username, String password) {
		User user = this.userService.getUserById(1);  
		if (user != null && user.getAccount().equals(username) &&  user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
