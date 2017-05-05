package controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import annotation.Permission;
import bean.Page;
import bean.Response;
import common.util.StringUtil;
import db.dao.RestaurantDao;
import db.pojo.Restaurant;
import global.constant.Constant;
import global.constant.Reason;

@Controller
public class RestaurantController extends AbsController{

	@Resource
	private RestaurantDao restaurantDao;

	@RequestMapping(value = Constant.RequestPath.RESTAURANT_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.RESTAURANT_LIST)
	public String list(String key, Page page, Integer state, Response response) {
		if(page.checkSortNameSuccess("name", "license", "id")){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.MapKey.COUNT, restaurantDao.selectRestaurantCount(key, state, Constant.Table.Restaurant.Type.NORMAL_RESTAURANT));
			map.put(Constant.MapKey.LIST, restaurantDao.selectRestaurant(key, state, Constant.Table.Restaurant.Type.NORMAL_RESTAURANT, page));
			response.setData(map);
		}else{
			response.setReason(Reason.ERR_ARG);
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.RESTAURANT_ADD, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.RESTAURANT_ADD)
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
	
	
	@RequestMapping(value = Constant.RequestPath.RESTAURANT_UPDATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.RESTAURANT_UPDATE)
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
	
	@RequestMapping(value = Constant.RequestPath.RESTAURANT_TOGGLE_STATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.RESTAURANT_TOGGLE_STATE)
	public String toggleState(Restaurant restaurant, Response response) {
		if(StringUtil.isEmpty(restaurant.getId())){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			restaurant = restaurantDao.selectRestaurantById(restaurant.getId());
			if(restaurant == null){
				response.setReason(Reason.ERR_ARG);
				response.setData("id");
			}else{
				if(restaurant.getState() == Constant.Table.Restaurant.State.FORBIDDEN){
					restaurantDao.setRestaurantState(restaurant.getId(), Constant.Table.Restaurant.State.NORMAL);
				}else{
					restaurantDao.setRestaurantState(restaurant.getId(), Constant.Table.Restaurant.State.FORBIDDEN);
				}
			}
		}
		return response.toJsonString();
	}
	
	private String checkUpdateArg(Restaurant restaurant){
		if(StringUtil.isEmpty(restaurant.getId())){
			return "id";
		}
		return checkAddArg(restaurant);
	}
	private String checkAddArg(Restaurant restaurant){
		if(StringUtil.checkFail(restaurant.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(restaurant.getLicense(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.LICENSE)){
			return "license";
		}
		return null;
	}
}
