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

import bean.response.GetPrivilegeResponse;
import bean.response.Response;
import common.util.Object2JsonUtil;
import db.dao.RoleOperationPrivilegeMapper;
import db.dao.UserMapper;
import db.dao.UserRoleMapper;
import db.pojo.RoleOperationPrivilege;
import db.pojo.User;
import db.pojo.UserRole;
import global.constant.Constant;
import global.constant.Reason;
import permission.Privilege;

@Controller
@RequestMapping("/ajax/user")
public class UserController extends AbsController{

	private static Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;
	@Resource
	private RoleOperationPrivilegeMapper roleOperationPrivilegeMapper;

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password, HttpSession httpSession,
			@CookieValue(value = "account", required = false) String cookieUserName) {
		User user = this.userMapper.selectByAccount(account);
		Response response = new Response();
		if (user != null) {
			if (user.getPassword().equals(password)) {
				UserRole userRole = userRoleMapper.selectByUserId(user.getId());
				List<Integer> privileges = roleOperationPrivilegeMapper.selectByRoleId(userRole.getRoleId());
				httpSession.setAttribute(Constant.USER, user);
				httpSession.setAttribute(Constant.PRIVILEGE, privileges);
			} else {
				response.setReason(Reason.PASSW0RD_ERROR);
			}
		} else {
			response.setReason(Reason.USER_NOT_EXIST);
		}
		return response.toJsonString();
	}
	@RequestMapping(value = "/privilege", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String privilege(HttpSession httpSession) {
		Response response = new Response();
		response.setObject((List<Integer>) httpSession.getAttribute(Constant.PRIVILEGE));
		return response.toJsonString();
	}

	@RequestMapping(value = "/all", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.USER_MANAGE)
	public String all(String key) {
		List<Map> allUsers = userMapper.selectAllUsers();
		Response response = new Response();
		response.setObject(allUsers);
		return response.toJsonString();
	}

}
