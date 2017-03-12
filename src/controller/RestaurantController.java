package controller;

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

import bean.response.Response;
import db.dao.RestaurantDao;
import db.dao.RoleOperationPrivilegeDao;
import db.dao.UserDao;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Privilege;

@Controller
@RequestMapping("/ajax/restaurant")
public class RestaurantController extends AbsController{

	private static Logger logger = Logger.getLogger(RestaurantController.class);
	@Resource
	private RestaurantDao restaurantDao;

	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.RESTAURANT_MANAGE)
	public String search(String name, String license) {
		List<Map> object = restaurantDao.selectRestaurant(name, license);
		Response response = new Response();
		response.setObject(object);
		return response.toJsonString();
	}
}
