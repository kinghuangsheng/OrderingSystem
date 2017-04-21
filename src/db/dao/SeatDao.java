package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.Page;
import db.pojo.Seat;

public interface SeatDao {
	
	
	List<Map<String, Object>> selectSeatCount(@Param("restaurantId")int restaurantId, @Param("key")String key);
	List<Map<String, Object>> selectSeat(@Param("restaurantId")int restaurantId, @Param("key")String key, @Param("page")Page page);
	
	Seat selectSeatById(@Param("restaurantId")int restaurantId, @Param("seatId")int seatId);
	int insertSeat(Seat seat);
	int updateSeat(Seat seat);
	int deleteSeat(Seat seat);

}