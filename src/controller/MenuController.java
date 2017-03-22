package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Response;
import db.dao.MenuDao;
import db.pojo.User;
import global.constant.Constant;
import permission.Permission;

@Controller
@RequestMapping("/ajax/menu")
public class MenuController extends AbsController{

	@Resource
	private MenuDao menuDao;

	@RequestMapping(value = "/roleMenuList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String roleMenuList(HttpSession httpSession, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		response.setData(menuDao.selectRoleMenu(user.getRoleId()));
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/allList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.ALL_MENU_LIST)
	public String allList(HttpSession httpSession, Response response) {
		response.setData(menuDao.selectAllMenu());
		return response.toJsonString();
	}
}
