package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import annotation.Permission;
import bean.Response;
import common.util.JsonUtil;
import common.util.StringUtil;
import db.dao.MenuDao;
import db.pojo.Menu;
import db.pojo.Role;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;

@Controller
public class MenuController extends AbsController{

	@Resource
	private MenuDao menuDao;
	
	private static List<Integer> allInterfaceIdList;

	
	@RequestMapping(value = Constant.RequestPath.MENU_ALL_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MENU_ALL_LIST)
	public String allList(HttpSession httpSession, Response response) {
		response.setData(menuDao.selectAllMenu());
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.MENU_INTERFACE_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MENU_INTERFACE_LIST)
	public String interfaceList(HttpSession httpSession, Integer menuId, Response response) {
		if(StringUtil.isEmpty(menuId)){
			response.setReason(Reason.ERR_ARG);
			response.setData("menuId");
			return response.toJsonString();
		}
		response.setData(menuDao.selectMenuInterface(menuId));
		return response.toJsonString();
	}
	

	@RequestMapping(value = Constant.RequestPath.MENU_ADD, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MENU_ADD)
	public String add(HttpSession httpSession, Menu menu, String interfaceIds, Response response) {
		String errorArg = checkAddArg(menu);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int num = menuDao.insertMenu(menu);
			if(num == 0){
				response.setReason(Reason.ERR_ARG);
				response.setData("parentId");
				return response.toJsonString();
			}
			num = menuDao.insertRoleMenu(menu, Constant.Table.Role.Id.DEVELOPER);
			if(num == 0){
				response.setReason(Reason.ERR_ARG);
				response.setData("interfaceIds");
				return response.toJsonString();
			}
			List<Integer> menuInterfaceIds = null;
			try{
				menuInterfaceIds = (List<Integer>) JsonUtil.parseArray(interfaceIds, Integer.class);
			}catch(Exception e){
				menuInterfaceIds = null;
			}
			if(menuInterfaceIds == null || menuInterfaceIds.isEmpty()){
				return response.toJsonString();
			}else{
				initAllInterfaces(menuDao);
				if(!allInterfaceIdList.containsAll(menuInterfaceIds)){
					response.setReason(Reason.ERR_ARG);
					response.setData("interfaceIds");
					return response.toJsonString();
				}
				menuDao.insertMenuInterface(menu, menuInterfaceIds);
				
			}
		}
		return response.toJsonString();
	}
	@RequestMapping(value = Constant.RequestPath.MENU_DELETE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MENU_DELETE)
	public String delete(HttpSession httpSession, Menu menu, String interfaceIds, Response response) {
		if(menu.getId() == null){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}
		int num = menuDao.deleteMenu(menu);
		if(num == 0){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			menuDao.deleteMenuInterface(menu);
			menuDao.deleteRoleMenu(menu);
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.MENU_UPDATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.MENU_UPDATE)
	public String update(HttpSession httpSession, Menu menu, String interfaceIds, Response response) {
		if(menu.getId() == null){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}
		String errorArg = checkAddArg(menu);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int num = menuDao.updateMenu(menu);
			if(num == 0){
				response.setReason(Reason.ERR_ARG);
				response.setData("interfaceIds");
				return response.toJsonString();
			}
			List<Integer> menuInterfaceIds = null;
			try{
				menuInterfaceIds = (List<Integer>) JsonUtil.parseArray(interfaceIds, Integer.class);
			}catch(Exception e){
				menuInterfaceIds = null;
			}
			if(menuInterfaceIds == null || menuInterfaceIds.isEmpty()){
				menuDao.deleteMenuInterface(menu);
				return response.toJsonString();
			}else{
				initAllInterfaces(menuDao);
				if(!allInterfaceIdList.containsAll(menuInterfaceIds)){
					response.setReason(Reason.ERR_ARG);
					response.setData("interfaceIds");
					return response.toJsonString();
				}
				menuDao.deleteMenuInterface(menu);
				menuDao.insertMenuInterface(menu, menuInterfaceIds);
			}
		}
		return response.toJsonString();
	}
	
	public static synchronized void initAllInterfaces(MenuDao menuDao){
		if(allInterfaceIdList == null){
			allInterfaceIdList = menuDao.selectAllInterfaceIds();
		}
	}
	
	public String checkAddArg(Menu menu){
		if(StringUtil.isEmpty(menu.getParentId())){
			return "parentId";
		}
		if(StringUtil.checkFail(menu.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.isEmpty(menu.getPath()) && StringUtil.checkFail(menu.getPath(), 0, Constant.Length.PATH_MAX, null)){
			return "path";
		}
		if(StringUtil.isEmpty(menu.getClassName())){
			return "className";
		}
		return null;
	}

}
