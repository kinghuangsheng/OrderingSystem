package running.data;

public class BookingData {
	
	
	private Integer seatId;
	private String secret;

	public BookingData(Integer seatId) {
		this.seatId = seatId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	

}
