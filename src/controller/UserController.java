package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.response.Response;
import db.dao.UserMapper;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;

@Controller
@RequestMapping("/ajax/user")
public class UserController extends AbsController{

	private static Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserMapper userMapper;

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password, HttpSession httpSession,
			@CookieValue(value = "account", required = false) String cookieUserName) {
		logger.error("  account = " + account + "  password = " + password + "  cookieUserName = " + cookieUserName);
		User user = this.userMapper.selectByAccount(account);
		Response response = new Response();
		if (user != null) {
			if (user.getPassword().equals(password)) {
				httpSession.setAttribute(Constant.USER_NAME, account);
			} else {
				response.setReason(Reason.PASSW0RD_ERROR);
			}
		} else {
			response.setReason(Reason.USER_NOT_EXIST);
		}
		return response.toJsonString();
	}

}
