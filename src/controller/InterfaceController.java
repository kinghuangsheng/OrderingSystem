package controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.BootStrapTableDataResponse;
import bean.Page;
import db.dao.InterfaceDao;
import global.constant.Constant;
import permission.Permission;

@Controller
@RequestMapping("/ajax/interface")
public class InterfaceController extends AbsController{

	@Resource
	private InterfaceDao interfaceDao;
	
	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.INTERFACE_LIST)
	public String list(HttpSession httpSession, String key, Page page, BootStrapTableDataResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", interfaceDao.selectInterFaceCount(key));
		map.put("list", interfaceDao.selectInterFaceList(key, page));
		response.setData(map);
		return response.toJsonString();
	}
	

}
