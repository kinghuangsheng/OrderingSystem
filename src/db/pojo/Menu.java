package db.pojo;

public class Menu {
    private Integer id;

    private String name;

    private String path;
    
    private String className;

    private Integer parentId;

    private Integer assignable;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAssignable() {
        return assignable;
    }

    public void setAssignable(Integer assignable) {
        this.assignable = assignable;
    }

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
    
    
}