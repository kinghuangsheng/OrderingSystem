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
import db.dao.FoodDao;
import db.pojo.Food;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
public class FoodController extends AbsController{

	@Resource
	private FoodDao foodDao;
	
	@RequestMapping(value = "/ajax/food/restaurantFoodList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/food/restaurantFoodList")
	public String restaurantFoodList(HttpSession httpSession, String key, Page page, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		if(page.checkSortNameSuccess("name", "sale_price", "original_price", "id")){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.MapKey.COUNT, foodDao.selectFoodCount(user.getRestaurantId(), key));
			map.put(Constant.MapKey.LIST, foodDao.selectFood(user.getRestaurantId(), key, page));
			response.setData(map);
		}else{
			response.setReason(Reason.ERR_ARG);
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/food/restaurantFoodCategoryList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/food/restaurantFoodCategoryList")
	public String restaurantFoodCategoryList(HttpSession httpSession, Integer foodId, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		if(StringUtil.isEmpty(foodId)){
			response.setReason(Reason.ERR_ARG);
			response.setData("foodId");
			return response.toJsonString();
		}
		response.setData(foodDao.selectFoodCategoryList(user.getRestaurantId(), foodId));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/ajax/food/addRestaurantFood", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/food/addRestaurantFood")
	public String addRestaurantFood(HttpSession httpSession, Food newFood, String categoryIds, Response response) {
		String errorArg = checkAddArg(newFood);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newFood.setRestaurantId(user.getRestaurantId());
			int rowNum = foodDao.insertFood(newFood);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
				response.setData("name");
			}else{
				List<Integer> categoryIdList = null;
				try{
					categoryIdList = (List<Integer>) JsonUtil.parseArray(categoryIds, Integer.class);
				}catch(Exception e){
					categoryIdList = null;
				}
				if(categoryIdList == null || categoryIdList.isEmpty()){
					return response.toJsonString();
				}else{
					List<Integer> allCategoryIdList = foodDao.selectAllCategoryIds(user.getRestaurantId());
					if(!allCategoryIdList.containsAll(categoryIdList)){
						response.setReason(Reason.ERR_ARG);
						response.setData("categoryIds");
						return response.toJsonString();
					}
					foodDao.insertFoodCategory(newFood.getId(), categoryIdList);
				}
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/food/updateRestaurantFood", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/food/updateRestaurantFood")
	public String updateRestaurantFood(HttpSession httpSession, Food newFood, String categoryIds, Response response) {
		String errorArg = checkUpdateArg(newFood);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newFood.setRestaurantId(user.getRestaurantId());
			int rowNum = foodDao.updateFood(newFood);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				List<Integer> categoryIdList = null;
				try{
					categoryIdList = (List<Integer>) JsonUtil.parseArray(categoryIds, Integer.class);
				}catch(Exception e){
					categoryIdList = null;
				}
				if(categoryIdList == null || categoryIdList.isEmpty()){
					return response.toJsonString();
				}else{
					List<Integer> allCategoryIdList = foodDao.selectAllCategoryIds(user.getRestaurantId());
					if(!allCategoryIdList.containsAll(categoryIdList)){
						response.setReason(Reason.ERR_ARG);
						response.setData("categoryIds");
						return response.toJsonString();
					}
					foodDao.deleteFoodCategory(newFood.getId());
					foodDao.insertFoodCategory(newFood.getId(), categoryIdList);
				}
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = "/ajax/food/deleteRestaurantFood", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/food/deleteRestaurantFood")
	public String deleteRestaurantFood(HttpSession httpSession, Food food, Response response) {
		if(StringUtil.isEmpty(food.getId())){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			food.setRestaurantId(user.getRestaurantId());
			int rowNum = foodDao.deleteFood(food);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				foodDao.deleteFoodCategory(food.getId());
			}
		}
		return response.toJsonString();
	}
	
	
	public String checkUpdateArg(Food food){
		if(StringUtil.isEmpty(food.getId())){
			return "id";
		}
		return checkAddArg(food);
	}
	public String checkAddArg(Food food){
		if(StringUtil.checkFail(food.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.isEmpty(food.getSalePrice())){
			return "salePrice";
		}
		if(!StringUtil.isEmpty(food.getOriginalPrice()) && food.getOriginalPrice().compareTo(food.getSalePrice()) == -1){
			return "originalPrice";
		}
		if(StringUtil.checkFail(food.getUrl(), Constant.Length.DEFAULT_MIN, Constant.Length.PATH_MAX, null)){
			return "url";
		}
		return null;
	}

}
