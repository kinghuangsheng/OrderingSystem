package controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Page;
import bean.Response;
import common.util.StringUtil;
import db.dao.RoleDao;
import db.dao.UserDao;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
public class UserController extends AbsController{

	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	
	private User user;
	
	@RequestMapping(value = "/ajax/user/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password, Integer restaurantId,HttpSession httpSession, Response response) {
//		@CookieValue(value = "account", required = false) String cookieUserName,
		
		if(restaurantId == null){
			response.setReason(Reason.ERR_ARG);
			response.setData("restaurantId");
			return response.toJsonString();
		}

		user = this.userDao.selectByAccount(account, restaurantId);
		if (user != null) {
			if(user.getState() == Constant.Table.User.State.NORMAL){
				if (user.getPassword().equals(password)) {
					//检查餐厅是否停用
					int restaurantState = userDao.selectRestaurantState(user.getRestaurantId());
					if(restaurantState == Constant.Table.Restaurant.State.NORMAL){
						List<String> interfaces = roleDao.selectRoleInterface(user.getRoleId());
						user.setPassword(null);
						response.setData(user);
						httpSession.setAttribute(Constant.MapKey.USER, user);
						httpSession.setAttribute(Constant.MapKey.INTERFACES, interfaces);
					}else if(restaurantState == Constant.Table.Restaurant.State.FORBIDDEN){
						response.setReason(Reason.RESTAURANT_FORBIDDEN);
					}
				} else {
					response.setReason(Reason.PASSW0RD_ERROR);
				}
			}else if(user.getState() == Constant.Table.User.State.FORBIDDEN){
				response.setReason(Reason.ACCOUNT_FORBIDDEN);
			}
		} else {
			response.setReason(Reason.USER_NOT_EXIST);
		}
		return response.toJsonString();
	}

	@RequestMapping(value = "/ajax/user/restaurantUserList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/user/restaurantUserList")
	public String restaurantUserList(HttpSession httpSession, String key, Integer state, Page page, Response response) {
		if(page.checkSortNameSuccess("name", "license", "id")){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.MapKey.COUNT, userDao.selectRestaurantUserCount(user.getRestaurantId(), key, state, Constant.Table.User.Type.NORMAL));
			map.put(Constant.MapKey.LIST, userDao.selectRestaurantUser(user.getRestaurantId(), key, state, Constant.Table.User.Type.NORMAL, page));
			response.setData(map);
		}else{
			response.setReason(Reason.ERR_ARG);
		}
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/ajax/user/addRestaurantUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/user/addRestaurantUser")
	public String addRestaurantUser(HttpSession httpSession, User newUser, Response response) {
		newUser.setRestaurantId(user.getRestaurantId());
		return addUser(newUser, response);
	}
	@RequestMapping(value = "/ajax/user/deleteRestaurantUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/user/deleteRestaurantUser")
	public String deleteRestaurantUser(HttpSession httpSession, Integer id, Response response) {
		if(StringUtil.isEmpty(id)){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			int rowNum = userDao.deleteUser(id, user.getRestaurantId());
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/user/addRestaurantManager", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/user/addRestaurantManager")
	public String addRestaurantManager(User newUser, Response response) {
		newUser.setType(Constant.Table.User.Type.RESTAURANT_MANAGER);
		return addUser(newUser, response);
	}
	
	private String addUser(User newUser, Response response){
		newUser.setType(Constant.Table.User.Type.NORMAL);
		String errorArg = checkAddArg(newUser);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int rowNum = userDao.insertUser(newUser);
			if(rowNum == 0){
				response.setReason(Reason.ACCOUNT_REPEATED);
			}else{
				response.setData(newUser);
			}
		}
		return response.toJsonString();
	}
	@RequestMapping(value = "/ajax/user/updateRestaurantUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/user/updateRestaurantUser")
	public String updateRestaurantUser(User newUser, Response response) {
		newUser.setRestaurantId(user.getRestaurantId());
		return updateUser(newUser, response);
	}
	
	@RequestMapping(value = "/ajax/user/updateRestaurantManager", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/user/updateRestaurantManager")
	public String updateRestaurantManager(User newUser, Response response) {
		return updateUser(newUser, response);
	}
	
	private String updateUser(User newUser, Response response){
		String errorArg = checkUpdateArg(newUser);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int rowNum = userDao.updateUser(newUser);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				response.setData(newUser);
			}
		}
		return response.toJsonString();
	
	}
	
	@RequestMapping(value = "/ajax/user/restaurantManagerDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/user/restaurantManagerDetail")
	public String restaurantManagerDetail(Integer id, Response response) {
		if(StringUtil.isEmpty(id)){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			User user= userDao.selectById(id);
			if(user == null){
				response.setReason(Reason.USER_NOT_EXIST);
			}else{
				response.setData(user);
			}
		}
		return response.toJsonString();
	}
	
	public String checkUpdateArg(User newUser){
		if(StringUtil.isEmpty(user.getId())){
			return "id";
		}
		return checkAddArg(newUser);
	}
	
	public String checkAddArg(User newUser){
		if(StringUtil.checkFail(newUser.getAccount(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "account";
		}
		if(StringUtil.checkFail(newUser.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(newUser.getPassword(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "password";
		}
		if(StringUtil.checkFail(newUser.getPhone(), 0, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "phone";
		}
		if(StringUtil.isEmpty(newUser.getRoleId())){
			return "roleId";
		}
		if(StringUtil.isEmpty(newUser.getRestaurantId())){
			return "restaurantId";
		}
		return null;
	}

}
