package service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import db.dao.SeatDao;
import db.pojo.Seat;
import service.SeatService;

@Transactional
public class SeatServiceImpl implements SeatService {

	@Resource
	private SeatDao seatDao;
	
	@Transactional
	@Override
	public void addSeat(Seat newSeat) {
		int rowNum = seatDao.insertSeat(newSeat);
		throw new RuntimeException();
		
	}

}
