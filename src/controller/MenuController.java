package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Response;
import common.util.JsonUtil;
import common.util.StringUtil;
import db.dao.MenuDao;
import db.pojo.Menu;
import db.pojo.Role;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
public class MenuController extends AbsController{

	@Resource
	private MenuDao menuDao;
	
	private static List<Integer> allInterfaceIdList;

	@RequestMapping(value = "/ajax/menu/roleMenuList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String roleMenuList(HttpSession httpSession, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		response.setData(menuDao.selectRoleMenu(user.getRoleId()));
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/menu/allList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/menu/allList")
	public String allList(HttpSession httpSession, Response response) {
		response.setData(menuDao.selectAllMenu());
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/menu/menuInterfaceList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/menu/menuInterfaceList")
	public String menuInterfaceList(HttpSession httpSession, int menuId, Response response) {
		response.setData(menuDao.selectMenuInterface(menuId));
		return response.toJsonString();
	}
	

	@RequestMapping(value = "/ajax/menu/add", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/menu/add")
	public String add(HttpSession httpSession, Menu menu, String interfaceIds, Response response) {
		String errorArg = checkAddArg(menu);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			menuDao.updateMenu(menu);
			
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
	
	@RequestMapping(value = "/ajax/menu/update", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/menu/update")
	public String update(HttpSession httpSession, Menu menu, String interfaceIds, Response response) {
		String errorArg = checkAddArg(menu);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			menuDao.updateMenu(menu);
			
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
		if(menu.getId() == null){
			return "id";
		}
		if(StringUtil.checkFail(menu.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(menu.getPath(), Constant.Length.DEFAULT_MIN, Constant.Length.PATH_MAX, null)){
			return "path";
		}
		return null;
	}

}
