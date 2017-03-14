package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.response.Response;
import db.dao.RoleOperationPrivilegeDao;
import db.dao.UserDao;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Privilege;

@Controller
@RequestMapping("/ajax/user")
public class UserController extends AbsController{

	private static Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserDao userDao;
	@Resource
	private RoleOperationPrivilegeDao roleOperationPrivilegeDao;

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password, HttpSession httpSession,
			@CookieValue(value = "account", required = false) String cookieUserName) {
		User user = this.userDao.selectByAccount(account);
		Response response = null;
		if (user != null) {
			response = new Response();
			if (user.getPassword().equals(password)) {
				List<Integer> privileges = roleOperationPrivilegeDao.selectByRoleId(user.getRoleId());
				httpSession.setAttribute(Constant.USER, user);
				httpSession.setAttribute(Constant.PRIVILEGE, privileges);
			} else {
				response = new Response(Reason.PASSW0RD_ERROR);
			}
		} else {
			response = new Response(Reason.USER_NOT_EXIST);
		}
		return response.toJsonString();
	}
	@RequestMapping(value = "/privilege", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String privilege(HttpSession httpSession) {
		Response response = new Response();
		response.setObject(httpSession.getAttribute(Constant.PRIVILEGE));
		return response.toJsonString();
	}

	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.USER_MANAGE)
	public String list() {
		Response response = new Response();
		response.setObject(userDao.selectAllUsers());
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/managerList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.RESTAURANT_MANAGER_MANAGE)
	public String managerList(String account, String name) {
		Response response = new Response();
		response.setObject(userDao.selectManagerByAccountName(account, name));
		return response.toJsonString();
	}

	@RequestMapping(value = "/addManager", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.RESTAURANT_MANAGE)
	public String addManager(User user) {
		user.setRoleId(Constant.Role.RESTAURANT_MANAGER);
		Response response = null;
		int rowNum = userDao.insertUser(user);
		if(rowNum == 0){
			response = new Response(Reason.ACCOUNT_REPEATED);
		}else{
			response = new Response();
			response.setObject(user);
		}
		return response.toJsonString();
	}

}
