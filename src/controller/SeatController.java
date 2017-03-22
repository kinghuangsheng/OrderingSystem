package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Response;
import common.util.StringUtil;
import db.dao.SeatDao;
import db.pojo.Seat;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import permission.Permission;

@Controller
@RequestMapping("/ajax/seat")
public class SeatController extends AbsController{

	@Resource
	private SeatDao seatDao;
	
	@RequestMapping(value = "/restaurantSeatList", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.RESTAURANT_SEAT_LIST)
	public String restaurantSeatList(HttpSession httpSession, String key) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		Response response = new Response();
		response.setData(seatDao.selectSeat(user.getRestaurantId(), key));
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = "/addRestaurantSeat", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.Interface.ADD_RESTAURANT_SEAT)
	public String addRestaurantSeat(HttpSession httpSession, Seat newSeat, Response response) {
		String errorArg = checkArg(newSeat);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newSeat.setRestaurantId(user.getRestaurantId());
			int rowNum = seatDao.insertSeat(newSeat);
			if(rowNum == 0){
				response.setReason(Reason.ACCOUNT_REPEATED);
			}else{
				response.setData(newSeat);
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
