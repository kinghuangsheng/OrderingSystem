package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import bean.response.Response;
import common.util.JsonUtil;
import common.util.StringUtil;
import db.dao.RoleDao;
import db.dao.RoleOperationPrivilegeDao;
import db.dao.UserDao;
import db.pojo.Role;
import db.pojo.RoleOperationPrivilege;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Privilege;

@Controller
@RequestMapping("/ajax/role")
public class RoleController extends AbsController{

	@Resource
	private RoleDao roleDao;
	@Resource
	private RoleOperationPrivilegeDao roleOperationPrivilegeDao;
	
	
	@RequestMapping(value = "/restaurantRoleList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.ROLE_MANAGE)
	public String restaurantUserList(HttpSession httpSession, String key) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		Response response = new Response();
		response.setObject(roleDao.selectRole(user.getRestaurantId(), key));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/addRestaurantRole", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.ROLE_MANAGE)
	public String addRole(HttpSession httpSession, Role newRole, String privilegeIds) {
		String errorArg = checkArg(newRole);
		Response response = null;
		if(errorArg != null){
			response = new Response(Reason.ERR_ARG);
			response.setObject(errorArg);
		}else{
			ArrayList<Integer> userPrivilegeIds = (ArrayList<Integer>)httpSession.getAttribute(Constant.MapKey.PRIVILEGE);
			List<Integer> privilegeIdList = null;
			try{
				privilegeIdList = (List<Integer>) JsonUtil.parseArray(privilegeIds, Integer.class);
			}catch(Exception e){
				privilegeIdList = null;
			}
			if(privilegeIdList == null || privilegeIdList.isEmpty() ||!userPrivilegeIds.containsAll(privilegeIdList)){
				response = new Response(Reason.ERR_ARG);
				response.setObject("privilegeIds");
				return response.toJsonString();
			}
    		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newRole.setRestaurantId(user.getRestaurantId());
			int rowNum = roleDao.insertRole(newRole);
			if(rowNum == 0){
				response = new Response(Reason.ACCOUNT_REPEATED);
			}else{
				rowNum = roleOperationPrivilegeDao.insertRoleIdPrivilegeIds(newRole.getId(), privilegeIdList);
				response = new Response();
				response.setObject(newRole);
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
