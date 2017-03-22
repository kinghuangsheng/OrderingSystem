package bean;

public class Page {
	public static final int PAGE_SIZE_2 = 2;
	public static final int PAGE_SIZE_10 = 10;
	public static final int PAGE_SIZE_20 = 20;
	public static final int PAGE_SIZE_50 = 50;
	public static final int PAGE_SIZE_100 = 100;
	
	
	private int pageNumber;
	private int pageSize;
	private int start;
	private String sortName;
	private String sortOrder;
	
	public Page(){
		this.pageNumber = 1;
		this.pageSize = PAGE_SIZE_2;
		this.sortOrder = "desc";
		calculateStartEnd();
		
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if(pageNumber < 1){
			return;
		}
		this.pageNumber = pageNumber;
		calculateStartEnd();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize != 2 && pageSize != 10 && pageSize != 20 && pageSize != 50 && pageSize != 100){
			return;
		}
		this.pageSize = pageSize;
		calculateStartEnd();
	}
	
	public void calculateStartEnd(){
		setStart((pageNumber - 1) * pageSize);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}


	
}
