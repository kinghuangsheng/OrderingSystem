package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Response;
import common.util.StringUtil;
import db.dao.RoleDao;
import db.dao.UserDao;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
@RequestMapping("/ajax/user")
public class UserController extends AbsController{

	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	
	private User user;

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password, HttpSession httpSession, Response response) {
//		@CookieValue(value = "account", required = false) String cookieUserName,
		user = this.userDao.selectByAccount(account);
		if (user != null) {
			response = new Response();
			if (user.getPassword().equals(password)) {
				List<Integer> permissions = roleDao.selectRoleInterface(user.getRoleId());
				httpSession.setAttribute(Constant.MapKey.USER, user);
				httpSession.setAttribute(Constant.MapKey.PERMISSION, permissions);
			} else {
				response.setReason(Reason.PASSW0RD_ERROR);
			}
		} else {
			response.setReason(Reason.USER_NOT_EXIST);
		}
		return response.toJsonString();
	}

	@RequestMapping(value = "/restaurantUserList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.RESTAURANT_USER_LIST)
	public String restaurantUserList(HttpSession httpSession, String key, Response response) {
		String notInRoleId = "(" + Constant.Role.SYSTEM_MANAGER + ", " + Constant.Role.RESTAURANT_MANAGER + ")";
		response.setData(userDao.selectRestaurantUser(user.getRestaurantId(), key, notInRoleId));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/addRestaurantUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.ADD_RESTAURANT_USER)
	public String addRestaurantUser(HttpSession httpSession, User newUser, Response response) {
		String errorArg = checkArg(newUser);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			if(newUser.getRoleId() == null){
				response.setReason(Reason.ERR_ARG);
				response.setData("roleId");
				return response.toJsonString();
			}
			int roleIdNum = roleDao.selectRoleIdNum(user.getRestaurantId(), newUser.getRoleId());
			if(roleIdNum == 0){
				response.setReason(Reason.ERR_ARG);
				response.setData("roleId");
				return response.toJsonString();
			}
			newUser.setRestaurantId(user.getRestaurantId());
			int rowNum = userDao.insertUser(newUser);
			if(rowNum == 0){
				response.setReason(Reason.ACCOUNT_REPEATED);
			}else{
				response = new Response();
				response.setData(newUser);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/addRestaurantManager", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.ADD_RESTAURANT_MANAGER)
	public String addRestaurantManager(User newUser, Response response) {
		String errorArg = checkArg(newUser);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			newUser.setRoleId(Constant.Role.RESTAURANT_MANAGER);
			int rowNum = userDao.insertUser(newUser);
			if(rowNum == 0){
				response.setReason(Reason.ACCOUNT_REPEATED);
			}else{
				response = new Response();
				response.setData(newUser);
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
