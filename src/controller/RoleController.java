package controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Page;
import bean.Response;
import common.util.JsonUtil;
import common.util.StringUtil;
import db.dao.RoleDao;
import db.pojo.Role;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
public class RoleController extends AbsController{

	@Resource
	private RoleDao roleDao;
	
	
	@RequestMapping(value = Constant.RequestPath.ROLE_MENU_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String roleMenuList(HttpSession httpSession, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		response.setData(roleDao.selectRoleMenu(user.getRoleId()));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = Constant.RequestPath.ROLE_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.ROLE_LIST)
	public String restaurantUserList(HttpSession httpSession, String key, Page page, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		return roleList(key, user.getRestaurantId(), page, response);
	}
	@RequestMapping(value = Constant.RequestPath.MANAGERROLE_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MANAGERROLE_LIST)
	public String restaurantManagerRoleList(HttpSession httpSession, String key, Page page, Response response) {
		return roleList(key, Constant.Table.Restaurant.Id.SYSTEM_MANAGER, page, response);
	}
	private String roleList(String key, Integer restaurantId, Page page, Response response){
		if(page.checkSortNameSuccess("name", "id")){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.MapKey.COUNT, roleDao.selectRoleCount(restaurantId, key));
			map.put(Constant.MapKey.LIST, roleDao.selectRole(restaurantId, key, page));
			response.setData(map);
		}else{
			response.setReason(Reason.ERR_ARG);
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.ROLE_ADD, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.ROLE_ADD)
	public String addRestaurantRole(HttpSession httpSession, Role newRole, String menuIds, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		return addRole(newRole, menuIds, user.getRoleId(), user.getRestaurantId(), response);
	}
	
	@RequestMapping(value = Constant.RequestPath.MANAGERROLE_ADD, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MANAGERROLE_ADD)
	public String addRestaurantManagerRole(HttpSession httpSession, Role newRole, String menuIds, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		return addRole(newRole, menuIds, user.getRoleId(), Constant.Table.Restaurant.Id.SYSTEM_MANAGER, response);
	}
	
	private String addRole(Role newRole, String menuIds, int userRoleId, int restaurantId, Response response){
		String errorArg = checkAddArg(newRole);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			List<Integer> roleMenuids = null;
			try{
				roleMenuids = (List<Integer>) JsonUtil.parseArray(menuIds, Integer.class);
			}catch(Exception e){
				roleMenuids = null;
			}
			if(roleMenuids == null || roleMenuids.isEmpty()){
				response.setReason(Reason.ERR_ARG);
				response.setData("menuIds");
				return response.toJsonString();
			}
			
			List<Integer> userMenuIds = roleDao.selectRoleMenuIds(userRoleId);
			if(userMenuIds == null || !userMenuIds.containsAll(roleMenuids)){
				response.setReason(Reason.ERR_ARG);
				response.setData("menuIds");
				return response.toJsonString();
			}
			newRole.setRestaurantId(restaurantId);
			int rowNum = roleDao.insertRole(newRole);
			if(rowNum == 0){
				response.setReason(Reason.ROLE_REPEATED);
			}else{
				rowNum = roleDao.insertRoleMenu(newRole.getId(), roleMenuids);
				response.setData(newRole);
			}
		}
		return response.toJsonString();
	}
	@RequestMapping(value = Constant.RequestPath.ROLE_UPDATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.ROLE_UPDATE)
	public String updateRestaurantRole(HttpSession httpSession, Role newRole, String menuIds, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		return updateRole(newRole, menuIds, user.getRoleId(), user.getRestaurantId(), response);
	}
	
	@RequestMapping(value = Constant.RequestPath.MANAGERROLE_UPDATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MANAGERROLE_UPDATE)
	public String updateRestaurantManagerRole(HttpSession httpSession, Role newRole, String menuIds, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		return updateRole(newRole, menuIds, user.getRoleId(), Constant.Table.Restaurant.Id.SYSTEM_MANAGER, response);
	}
	
	@RequestMapping(value = Constant.RequestPath.ROLE_AUTHORIZED_MENU_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.ROLE_AUTHORIZED_MENU_LIST)
	public String authorizedRoleMenuList(HttpSession httpSession, Integer roleId, Response response) {
		if(StringUtil.isEmpty(roleId)){
			response.setReason(Reason.ERR_ARG);
			response.setData("roleId");
			return response.toJsonString();
		}
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		response.setData(roleDao.selectAuthorizedRoleMenu(user.getRoleId(), roleId));
		return response.toJsonString();
	}
	
	private String updateRole(Role newRole, String menuIds, int roleId, int restaurantId, Response response){
		String errorArg = checkUpdateArg(newRole);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			List<Integer> roleMenuids = null;
			try{
				roleMenuids = (List<Integer>) JsonUtil.parseArray(menuIds, Integer.class);
			}catch(Exception e){
				roleMenuids = null;
			}
			if(roleMenuids == null || roleMenuids.isEmpty()){
				response.setReason(Reason.ERR_ARG);
				response.setData("menuIds");
				return response.toJsonString();
			}
			
			List<Integer> userMenuIds = roleDao.selectRoleMenuIds(roleId);
			if(userMenuIds == null || !userMenuIds.containsAll(roleMenuids)){
				response.setReason(Reason.ERR_ARG);
				response.setData("menuIds");
				return response.toJsonString();
			}
			newRole.setRestaurantId(restaurantId);
			int rowNum = roleDao.updateRole(newRole);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				roleDao.deleteRoleMenu(newRole.getId());
				rowNum = roleDao.insertRoleMenu(newRole.getId(), roleMenuids);
			}
		}
		return response.toJsonString();
	}
	
	public String checkUpdateArg(Role role){
		if(StringUtil.isEmpty(role.getId())){
			return "id";
		}
		return checkAddArg(role);
	}
	
	public String checkAddArg(Role role){
		if(StringUtil.checkFail(role.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		return null;
	}

}
