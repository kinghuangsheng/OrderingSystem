package controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import annotation.Permission;
import bean.Page;
import bean.Response;
import common.util.StringUtil;
import db.dao.InterfaceDao;
import db.pojo.Interface;
import global.constant.Constant;
import global.constant.Reason;

@Controller
public class InterfaceController extends AbsController{

	@Resource
	private InterfaceDao interfaceDao;
	
	@RequestMapping(value = Constant.RequestPath.INTERFACE_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.INTERFACE_LIST)
	public String list(HttpSession httpSession, String key, Page page, Response response) {
		if(page.checkSortNameSuccess("name", "path", "id")){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.MapKey.COUNT, interfaceDao.selectInterfaceCount(key));
			map.put(Constant.MapKey.LIST, interfaceDao.selectInterfaceList(key, page));
			response.setData(map);
		}else{
			response.setReason(Reason.ERR_ARG);
		}
		
		return response.toJsonString();
	}
	@RequestMapping(value = Constant.RequestPath.INTERFACE_ADD, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.INTERFACE_ADD)
	public String add(HttpSession httpSession, Interface i, Response response) {
		String errorArg = checkAddArg(i);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int rowNum = interfaceDao.insertInterface(i);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				response.setData(i);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.INTERFACE_UPDATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.INTERFACE_UPDATE)
	public String update(HttpSession httpSession, Interface i, Response response) {
		String errorArg = checkUpdateArg(i);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			int rowNum = interfaceDao.updateInterface(i);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				response.setData(i);
			}
		}
		return response.toJsonString();
	}
	@RequestMapping(value = Constant.RequestPath.INTERFACE_DELETE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.INTERFACE_DELETE)
	public String delete(HttpSession httpSession, Integer id, Response response) {
		if(StringUtil.isEmpty(id)){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			int rowNum = interfaceDao.deleteInterface(id);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				interfaceDao.deleteInterfaceMenu(id);
			}
		}
		return response.toJsonString();
	}
	private String checkUpdateArg(Interface i){
		if(StringUtil.isEmpty(i.getId())){
			return "id";
		}
		return checkAddArg(i);
	
	}
	
	private String checkAddArg(Interface i){
		if(StringUtil.checkFail(i.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(i.getPath(), Constant.Length.DEFAULT_MIN, Constant.Length.PATH_MAX, null)){
			return "path";
		}
		return null;
	
	}
}
