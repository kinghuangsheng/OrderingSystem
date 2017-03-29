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
		public final static int DEFAULT_MAX = 20;
		public final static int PATH_MAX = 255;
	}

	public class Pattern{
		
		public final static String DEFAULT = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
		public final static String PATH = "([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
//		public final static String LICENSE = "\\d{8}";
		public final static String LICENSE = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
		
	}
	
	
	public class Table{
		public class Restaurant{
			public class State{
				public final static int NORMAL = 0;
				public final static int FORBIDDEN = 1;
			}
			public class Id{
				public final static int SYSTEM_DEVELOP = 0;
				public final static int SYSTEM_MANAGER = 1;
			}
			public class Type{
				public final static int NORMAL_RESTAURANT = 0;
			}
		}
		public class User{
			public class State{
				public final static int NORMAL = 0;
				public final static int FORBIDDEN = 1;
			}
			
			public class Type{
				public final static int NORMAL = 0;
				public final static int RESTAURANT_MANAGER = 1;
			}
		}
		public class Role{
			public class Id{
				public final static int DEVELOPER = 0;
			}
		}
	}
	
	
}
