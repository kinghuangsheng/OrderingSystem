package controller;

import java.util.ArrayList;
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
import common.util.StringUtil;
import db.dao.RoleDao;
import db.dao.RoleOperationPrivilegeDao;
import db.dao.UserDao;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Privilege;

@Controller
@RequestMapping("/ajax/user")
public class UserController extends AbsController{

	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private RoleOperationPrivilegeDao roleOperationPrivilegeDao;
	
	private User user;

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password, HttpSession httpSession,
			@CookieValue(value = "account", required = false) String cookieUserName) {
		user = this.userDao.selectByAccount(account);
		Response response = null;
		if (user != null) {
			response = new Response();
			if (user.getPassword().equals(password)) {
				List<Integer> privileges = roleOperationPrivilegeDao.selectPrivilegeId(user.getRoleId());
				httpSession.setAttribute(Constant.MapKey.USER, user);
				httpSession.setAttribute(Constant.MapKey.PRIVILEGE, privileges);
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
		response.setObject(roleOperationPrivilegeDao.selectPrivilegeInfo(user.getRoleId()));
		return response.toJsonString();
	}

	@RequestMapping(value = "/restaurantUserList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.USER_MANAGE)
	public String restaurantUserList(HttpSession httpSession, String key) {
		Response response = new Response();
		String notInRoleId = "(" + Constant.Role.SYSTEM_MANAGER + ", " + Constant.Role.RESTAURANT_MANAGER + ")";
		response.setObject(userDao.selectRestaurantUser(user.getRestaurantId(), key, notInRoleId));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/addRestaurantUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.USER_MANAGE)
	public String addRestaurantUser(HttpSession httpSession, User newUser) {
		String errorArg = checkArg(newUser);
		Response response = null;
		if(errorArg != null){
			response = new Response(Reason.ERR_ARG);
			response.setObject(errorArg);
		}else{
			if(newUser.getRoleId() == null){
				response = new Response(Reason.ERR_ARG);
				response.setObject("roleId");
				return response.toJsonString();
			}
			int roleIdNum = roleDao.selectRoleIdNum(user.getRestaurantId(), newUser.getRoleId());
			if(roleIdNum == 0){
				response = new Response(Reason.ERR_ARG);
				response.setObject("roleId");
				return response.toJsonString();
			}
			newUser.setRestaurantId(user.getRestaurantId());
			int rowNum = userDao.insertUser(newUser);
			if(rowNum == 0){
				response = new Response(Reason.ACCOUNT_REPEATED);
			}else{
				response = new Response();
				response.setObject(newUser);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/addRestaurantManager", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.RESTAURANT_MANAGE)
	public String addRestaurantManager(User newUser) {
		String errorArg = checkArg(newUser);
		Response response = null;
		if(errorArg != null){
			response = new Response(Reason.ERR_ARG);
			response.setObject(errorArg);
		}else{
			newUser.setRoleId(Constant.Role.RESTAURANT_MANAGER);
			int rowNum = userDao.insertUser(newUser);
			if(rowNum == 0){
				response = new Response(Reason.ACCOUNT_REPEATED);
			}else{
				response = new Response();
				response.setObject(newUser);
			}
		}
		return response.toJsonString();
	}
	
	public String checkArg(User newUser){
		if(StringUtil.checkFail(newUser.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(newUser.getAccount(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "account";
		}
		if(StringUtil.checkFail(newUser.getPassword(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "password";
		}
		return null;
	}

}
