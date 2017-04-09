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
import db.dao.CategoryDao;
import db.pojo.Category;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
public class CategoryController extends AbsController{

	@Resource
	private CategoryDao categoryDao;
	
	@RequestMapping(value = Constant.RequestPath.CATEGORY_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.CATEGORY_LIST)
	public String list(HttpSession httpSession, String key, Page page, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		return categoryList(key, user.getRestaurantId(), page, response);
	}
	
	private String categoryList(String key, Integer restaurantId, Page page, Response response){
		if(page.checkSortNameSuccess("name", "id")){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.MapKey.COUNT, categoryDao.selectCategoryCount(restaurantId, key));
			map.put(Constant.MapKey.LIST, categoryDao.selectCategory(restaurantId, key, page));
			response.setData(map);
		}else{
			response.setReason(Reason.ERR_ARG);
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.CATEGORY_ADD, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.CATEGORY_ADD)
	public String add(HttpSession httpSession, Category newCategory, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		String errorArg = checkAddArg(newCategory);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			newCategory.setRestaurantId(user.getRestaurantId());
			int rowNum = categoryDao.insertCategory(newCategory);
			if(rowNum == 0){
				response.setReason(Reason.ROLE_REPEATED);
			}
		}
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = Constant.RequestPath.CATEGORY_UPDATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.CATEGORY_UPDATE)
	public String update(HttpSession httpSession, Category newCategory, String menuIds, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		String errorArg = checkUpdateArg(newCategory);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			newCategory.setRestaurantId(user.getRestaurantId());
			int rowNum = categoryDao.updateCategory(newCategory);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}
		}
		return response.toJsonString();
	}
	
	
	private String checkUpdateArg(Category category){
		if(StringUtil.isEmpty(category.getId())){
			return "id";
		}
		return checkAddArg(category);
	}
	
	private String checkAddArg(Category category){
		if(StringUtil.checkFail(category.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		return null;
	}

}
