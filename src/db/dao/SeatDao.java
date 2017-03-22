package db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import db.pojo.Seat;

public interface SeatDao {
	
	
	List<Map<String, Object>> selectSeat(@Param("restaurantId")int restaurantId, @Param("key")String key);
	
	int insertSeat(Seat seat);

}