package controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Page;
import bean.Response;
import common.util.StringUtil;
import db.dao.InterfaceDao;
import db.pojo.Interface;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
public class InterfaceController extends AbsController{

	@Resource
	private InterfaceDao interfaceDao;
	
	@RequestMapping(value = "/ajax/interface/list", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/interface/list")
	public String list(HttpSession httpSession, String key, Page page, Response response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.MapKey.COUNT, interfaceDao.selectInterfaceCount(key));
		map.put(Constant.MapKey.LIST, interfaceDao.selectInterfaceList(key, page));
		response.setData(map);
		return response.toJsonString();
	}
	@RequestMapping(value = "/ajax/interface/add", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission("/ajax/interface/add")
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

	public String checkAddArg(Interface i){
		if(StringUtil.checkFail(i.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.checkFail(i.getPath(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "account";
		}
		return null;
	
	}
}
