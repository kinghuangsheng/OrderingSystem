package global.constant;

public class Constant {
	
	public final static String PROJECT_NAME = "OrderingSystem";
	
	public class MapKey{
		
		public final static String USER = "user";
		public final static String PERMISSION = "privilege";
		
	}
	
	public class Length{
		
		public final static int DEFAULT_MIN = 1;
		public final static int DEFAULT_MAX = 8;
		
	}

	public class Pattern{
		
		public final static String DEFAULT = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
		public final static String LICENSE = "\\d{8}";
		
	}
	
	public class Interface {
		
		public final static int INTERFACE_LIST = 5;
		public final static int ALL_MENU_LIST = 6;
		public final static int ADD_RESTAURANT_MANAGER = 7;
		public final static int ADD_RESTAURANT_USER = 8;
		public final static int RESTAURANT_ROLE_LIST = 9;
		
		public final static int RESTAURANT_SEAT_LIST = 10;
		public final static int ADD_RESTAURANT_SEAT = 11;
		public final static int RESTAURANT_LIST = 12;
		public final static int ADD_RESTAURANT = 13;
		public final static int UPDATE_RESTAURANT = 14;
		public final static int RESTAURANT_USER_LIST = 15;
		public final static int ADD_RESTAURANT_ROLE = 16;
		
		
		

	}
	
	public class Role{
		public final static int SYSTEM_MANAGER = 1;
		public final static int RESTAURANT_MANAGER = 2;
	}
	
}
