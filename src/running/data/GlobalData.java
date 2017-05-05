package running.data;

import java.util.HashMap;

public class GlobalData {
	
	
	private static HashMap<Integer, BookingData> seatIdBookingDataMap = new HashMap<Integer, BookingData>();

	public static synchronized BookingData getBookingData(int seatId){
		if(seatIdBookingDataMap.containsKey(seatId)){
			return seatIdBookingDataMap.get(seatId);
		}else{
			BookingData bookingData = new BookingData(seatId);
			seatIdBookingDataMap.put(seatId, bookingData);
			return bookingData;
		}
	}
	
	
	
}
