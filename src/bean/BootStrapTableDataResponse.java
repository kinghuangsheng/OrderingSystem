package bean;

public class BootStrapTableDataResponse extends Response {
	
	private int total;
	private Object rows;
	
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
	

}