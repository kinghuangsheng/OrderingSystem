package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.response.Response;
import common.util.StringUtil;
import db.dao.RestaurantDao;
import db.pojo.Restaurant;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Privilege;

@Controller
@RequestMapping("/ajax/restaurant")
public class RestaurantController extends AbsController{

	@Resource
	private RestaurantDao restaurantDao;

	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.RESTAURANT_MANAGE)
	public String search(String key) {
		List<Map<String, Object>> object = restaurantDao.selectRestaurant(key, Constant.Role.RESTAURANT_MANAGER);
		Response response = new Response();
		response.setObject(object);
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.RESTAURANT_MANAGE)
	public String add(Restaurant restaurant) {
		String errorArg = checkArg(restaurant);
		Response response = null;
		if(errorArg != null){
			response = new Response(Reason.ERR_ARG);
			response.setObject(errorArg);
		}else{
			int rowNum = restaurantDao.insertRestaurant(restaurant);
			if(rowNum == 0){
				response = new Response(Reason.LICENSE_REPEATED);
			}else{
				response = new Response();
				response.setObject(restaurant);
			}
		}
		return response.toJsonString();
	}
	
	public String checkArg(Restaurant restaurant){
		if(StringUtil.checkFail(restaurant.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(restaurant.getLicense(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.LICENSE)){
			return "license";
		}
		return null;
	}
}
