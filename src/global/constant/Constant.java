package global.constant;

public class Constant {
	
	public final static String PROJECT_NAME = "OrderingSystem";
	
	public class MapKey{
		
		public final static String USER = "user";
		public final static String PRIVILEGE = "privilege";
		
	}
	
	public class Length{
		
		public final static int DEFAULT_MIN = 1;
		public final static int DEFAULT_MAX = 3;
		
	}

	public class Pattern{
		
		public final static String DEFAULT = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
		public final static String LICENSE = "\\d{3}";
		
	}
	
	public class Privilege {
		
		public final static int USER_MANAGE = 1;
		public final static int ROLE_MANAGE = 2;
		public final static int SEAT_MANAGE = 4;
		public final static int RESTAURANT_MANAGE = 5;
		public final static int RESTAURANT_MANAGER_MANAGE = 6;

	}
	
	public class Role{
		public final static int SYSTEM_MANAGER = 1;
		public final static int RESTAURANT_MANAGER = 2;
	}
	
}
