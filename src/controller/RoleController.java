package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/ajax/role")
public class RoleController extends AbsController{

	@Resource
	private RoleDao roleDao;
	
	@RequestMapping(value = "/restaurantRoleList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.RESTAURANT_ROLE_LIST)
	public String restaurantUserList(HttpSession httpSession, String key, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		response.setData(roleDao.selectRole(user.getRestaurantId(), key));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/addRestaurantRole", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.ADD_RESTAURANT_ROLE)
	public String addRestaurantRole(HttpSession httpSession, Role newRole, String menuIds, Response response) {
		String errorArg = checkArg(newRole);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			ArrayList<Integer> userPrivilegeIds = (ArrayList<Integer>)httpSession.getAttribute(Constant.MapKey.PERMISSION);
			List<Integer> privilegeIdList = null;
			try{
				privilegeIdList = (List<Integer>) JsonUtil.parseArray(menuIds, Integer.class);
			}catch(Exception e){
				privilegeIdList = null;
			}
			if(privilegeIdList == null || privilegeIdList.isEmpty() ||!userPrivilegeIds.containsAll(privilegeIdList)){
				response.setReason(Reason.ERR_ARG);
				response.setData("privilegeIds");
				return response.toJsonString();
			}
    		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newRole.setRestaurantId(user.getRestaurantId());
			int rowNum = roleDao.insertRole(newRole);
			if(rowNum == 0){
				response.setReason(Reason.ACCOUNT_REPEATED);
			}else{
				rowNum = roleDao.insertRoleMenu(newRole.getId(), privilegeIdList);
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
