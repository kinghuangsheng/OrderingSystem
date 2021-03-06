package controller;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import annotation.Permission;
import bean.Page;
import bean.Response;
import common.util.StringUtil;
import db.dao.SeatDao;
import db.pojo.Seat;
import db.pojo.User;
import global.constant.Constant;
import global.constant.Reason;
import running.data.BookingData;
import running.data.GlobalData;

@Controller
public class SeatController extends AbsController{

	@Resource
	private SeatDao seatDao;
	
	@RequestMapping(value = Constant.RequestPath.SEAT_LIST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.SEAT_LIST)
	public String list(HttpSession httpSession, String key, Page page, Response response) {
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		if(page.checkSortNameSuccess("name", "customer_num", "id")){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.MapKey.COUNT, seatDao.selectSeatCount(user.getRestaurantId(), key));
			map.put(Constant.MapKey.LIST, seatDao.selectSeat(user.getRestaurantId(), key, page));
			response.setData(map);
		}else{
			response.setReason(Reason.ERR_ARG);
		}
		return response.toJsonString();
	}
	
	
	@RequestMapping(value = Constant.RequestPath.SEAT_ADD, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.SEAT_ADD)
	public String add(HttpSession httpSession, Seat newSeat, Response response) {
		String errorArg = checkAddArg(newSeat);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newSeat.setRestaurantId(user.getRestaurantId());
			int rowNum = seatDao.insertSeat(newSeat);
			if(rowNum == 0){
				response.setReason(Reason.SEAT_REPEATED);
			}else{
				response.setData(newSeat);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.SEAT_UPDATE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.SEAT_UPDATE)
	public String update(HttpSession httpSession, Seat newSeat, Response response) {
		String errorArg = checkUpdateArg(newSeat);
		if(errorArg != null){
			response.setReason(Reason.ERR_ARG);
			response.setData(errorArg);
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			newSeat.setRestaurantId(user.getRestaurantId());
			int rowNum = seatDao.updateSeat(newSeat);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}else{
				response.setData(newSeat);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.SEAT_DELETE, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.SEAT_DELETE)
	public String delete(HttpSession httpSession, Seat seat, Response response) {
		if(StringUtil.isEmpty(seat.getId())){
			response.setReason(Reason.ERR_ARG);
			response.setData("id");
		}else{
			User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
			seat.setRestaurantId(user.getRestaurantId());
			int rowNum = seatDao.deleteSeat(seat);
			if(rowNum == 0){
				response.setReason(Reason.ERR_ARG);
			}
		}
		return response.toJsonString();
	}
	
	@RequestMapping(value = Constant.RequestPath.SEAT_REFRESH_SECRET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@Permission(Constant.RequestPath.SEAT_REFRESH_SECRET)
	public String refreshSecret(HttpSession httpSession, Seat seat, Response response){
		User user = (User) httpSession.getAttribute(Constant.MapKey.USER);
		if(seat.getId() == null){
			response.setReason(Reason.ERR_ARG);
		}else{
			seat = seatDao.selectSeatById(user.getRestaurantId(), seat.getId());
			if(seat == null){
				response.setReason(Reason.ERR_ARG);
			}else{
				UUID uuid = UUID.randomUUID();
				BookingData bookingData = GlobalData.getBookingData(seat.getId());
				bookingData.setSecret(uuid.toString());
				response.setData(uuid.toString());
			}
		}
		return response.toJsonString();
	}
	
	public String checkUpdateArg(Seat seat){
		if(StringUtil.isEmpty(seat.getId())){
			return "id";
		}
		return checkAddArg(seat);
	}
	public String checkAddArg(Seat seat){
		if(StringUtil.checkFail(seat.getName(), Constant.Length.DEFAULT_MIN, Constant.Length.DEFAULT_MAX, Constant.Pattern.DEFAULT)){
			return "name";
		}
		if(StringUtil.isEmpty(seat.getCustomerNum())){
			return "customerNum";
		}
		return null;
	}

}
