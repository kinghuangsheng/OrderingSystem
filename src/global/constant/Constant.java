package global.constant;

public class Constant {
	
	public final static String PROJECT_NAME = "OrderingSystem";
	
	public class MapKey{
		
		public final static String USER = "user";
		public final static String INTERFACES = "interfaces";
		public final static String MENUS = "menus";
		public final static String COUNT = "count";
		public final static String LIST = "list";
		
	}
	
	public class Length{
		
		public final static int DEFAULT_MIN = 1;
		public final static int DEFAULT_MAX = 8;
		public final static int PATH_MAX = 255;
		
	}

	public class Pattern{
		
		public final static String DEFAULT = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
		public final static String PATH = "([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
//		public final static String LICENSE = "\\d{8}";
		public final static String LICENSE = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
		
	}
	
	public class Role{
		public final static int SYSTEM_MANAGER = 1;
		public final static int RESTAURANT_MANAGER = 2;
	}
	public class Restuarant{
		public final static int RESTUARANT_STATE_NORMAL = 0;
		public final static int RESTUARANT_STATE_DELETE = 1;
	}
	
}
