package controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import bean.User;
import bean.response.LoginResponse;
import common.util.LogUtil;
import common.util.Object2JsonUtil;
import global.constant.Constant;
import global.constant.Reason;
import jdk.nashorn.api.scripting.JSObject;

@Controller
@RequestMapping("/user")
public class UserController {

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
		if ("admin".equals(username) && "password".equals(password)) {
			return true;
		}
		return false;
	}
}
