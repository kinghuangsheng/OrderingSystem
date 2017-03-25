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
	
	@RequestMapping(value = "/ajax/role/restaurantRoleList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/role/addRestaurantRole")
	public String restaurantUserList(HttpSession httpSession, String key, Page page, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.MapKey.COUNT, roleDao.selectRoleCount(user.getRestaurantId(), key));
		map.put(Constant.MapKey.LIST, roleDao.selectRole(user.getRestaurantId(), key, page));
		response.setData(map);
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/ajax/role/addRestaurantRole", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/role/addRestaurantRole")
	public String addRestaurantRole(HttpSession httpSession, Role newRole, String menuIds, Response response) {
		String errorArg = checkArg(newRole);
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
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			List<Integer> userMenuIds = roleDao.selectRoleMenuIds(user.getRoleId());
			if(userMenuIds == null || !userMenuIds.containsAll(roleMenuids)){
				response.setReason(Reason.ERR_ARG);
				response.setData("menuIds");
				return response.toJsonString();
			}
			newRole.setRestaurantId(user.getRestaurantId());
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
	
	
	
	public String checkArg(Role role){
		if(StringUtil.checkFail(role.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		return null;
	}

}
