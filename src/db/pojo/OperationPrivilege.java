package db.pojo;

public class OperationPrivilege {
    private Integer id;

    private String name;
    
    private int assignable;

    public int getAssignable() {
		return assignable;
	}

	public void setAssignable(int assignable) {
		this.assignable = assignable;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}