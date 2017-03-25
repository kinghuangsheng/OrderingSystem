package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Page;
import bean.Response;
import common.util.StringUtil;
import db.dao.RestaurantDao;
import db.pojo.Restaurant;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
public class RestaurantController extends AbsController{

	@Resource
	private RestaurantDao restaurantDao;

	@RequestMapping(value = "/ajax/restaurant/list", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/restaurant/list")
	public String search(String key, Page page, Response response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.MapKey.COUNT, restaurantDao.selectRestaurantCount(key));
		map.put(Constant.MapKey.LIST, restaurantDao.selectRestaurant(key, page));
		response.setData(map);
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/restaurant/add", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/restaurant/add")
	public String add(Restaurant restaurant, Response response) {
		String errorArg = checkAddArg(restaurant);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int rowNum = restaurantDao.insertRestaurant(restaurant);
			if(rowNum == 0){
				response.setReason(Reason.LICENSE_REPEATED);
			}else{
				response.setData(restaurant);
			}
		}
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/ajax/restaurant/update", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/restaurant/update")
	public String update(Restaurant restaurant, Response response) {
		String errorArg = checkUpdateArg(restaurant);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int rowNum = restaurantDao.updateRestaurant(restaurant);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/restaurant/delete", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/restaurant/delete")
	public String delete(Restaurant restaurant, Response response) {
		if(restaurant.getId() == null){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			int rowNum = restaurantDao.deleteRestaurant(restaurant.getId(), Constant.Restuarant.RESTUARANT_STATE_DELETE);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				response.setData(restaurant);
			}
		}
		return response.toJsonString();
	}
	
	public String checkUpdateArg(Restaurant restaurant){
		if(StringUtil.checkFail(restaurant.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(restaurant.getLicense(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.LICENSE)){
			return "license";
		}
		if(restaurant.getId() == null){
			return "id";
		}
		return null;
	}
	public String checkAddArg(Restaurant restaurant){
		if(StringUtil.checkFail(restaurant.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(restaurant.getLicense(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.LICENSE)){
			return "license";
		}
		return null;
	}
}
