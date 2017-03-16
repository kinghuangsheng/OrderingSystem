package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.response.Response;
import common.util.StringUtil;
import db.dao.SeatDao;
import db.pojo.Seat;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Privilege;

@Controller
@RequestMapping("/ajax/seat")
public class SeatController extends AbsController{

	@Resource
	private SeatDao seatDao;
	
	@RequestMapping(value = "/restaurantSeatList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.SEAT_MANAGE)
	public String restaurantSeatList(HttpSession httpSession, String key) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		Response response = new Response();
		response.setObject(seatDao.selectSeat(user.getRestaurantId(), key));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/addRestaurantSeat", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Privilege(Constant.Privilege.SEAT_MANAGE)
	public String addRestaurantSeat(HttpSession httpSession, Seat newSeat) {
		String errorArg = checkArg(newSeat);
		Response response = null;
		if(errorArg != null){
			response = new Response(Reason.ERR_ARG);
			response.setObject(errorArg);
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newSeat.setRestaurantId(user.getRestaurantId());
			int rowNum = seatDao.insertSeat(newSeat);
			if(rowNum == 0){
				response = new Response(Reason.ACCOUNT_REPEATED);
			}else{
				response = new Response();
				response.setObject(newSeat);
			}
		}
		return response.toJsonString();
	}
	
	public String checkArg(Seat seat){
		if(StringUtil.checkFail(seat.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		return null;
	}

}
