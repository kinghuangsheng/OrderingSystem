package global.constant;

public class Constant {
	
	public final static String PROJECT_NAME = "OrderingSystem";
	
	public class MapKey{
		
		public final static String USER = "user";
		public final static String INTERFACES = "interfaces";
		public final static String MENUS = "menus";
		public final static String COUNT = "count";
		public final static String LIST = "list";
		public final static String BOOKING_DATA = "bookingData";
		
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
	
	
	public class RequestPath{
		
		public final static String CATEGORY_ADD = "/ajax/category/add";
		public final static String CATEGORY_LIST = "/ajax/category/list";
		public final static String CATEGORY_UPDATE = "/ajax/category/update";
		
		public final static String FOOD_ADD = "/ajax/food/add";
		public final static String FOOD_CATEGORY_LIST = "/ajax/food/categoryList";
		public final static String FOOD_DELETE = "/ajax/food/delete";
		public final static String FOOD_LIST = "/ajax/food/list";
		public final static String FOOD_UPDATE = "/ajax/food/update";
		
		public final static String INTERFACE_ADD = "/ajax/interface/add";
		public final static String INTERFACE_DELETE = "/ajax/interface/delete";
		public final static String INTERFACE_LIST = "/ajax/interface/list";
		public final static String INTERFACE_UPDATE = "/ajax/interface/update";
		
		public final static String MANAGER_ADD = "/ajax/manager/add";
		public final static String MANAGER_DETAIL = "/ajax/manager/detail";
		public final static String MANAGER_UPDATE = "/ajax/manager/update";
		
		public final static String MANAGERROLE_ADD = "/ajax/managerRole/add";
		public final static String MANAGERROLE_LIST = "/ajax/managerRole/list";
		public final static String MANAGERROLE_UPDATE = "/ajax/managerRole/update";
		
		public final static String MENU_ADD = "/ajax/menu/add";
		public final static String MENU_ALL_LIST = "/ajax/menu/allList";
		public final static String MENU_DELETE = "/ajax/menu/delete";
		public final static String MENU_INTERFACE_LIST = "/ajax/menu/interfaceList";
		public final static String MENU_UPDATE= "/ajax/menu/update";
		
		public final static String RESTAURANT_ADD = "/ajax/restaurant/add";
		public final static String RESTAURANT_LIST = "/ajax/restaurant/list";
		public final static String RESTAURANT_TOGGLE_STATE = "/ajax/restaurant/toggleState";
		public final static String RESTAURANT_UPDATE = "/ajax/restaurant/update";
		
		public final static String ROLE_ADD = "/ajax/role/add";
		public final static String ROLE_AUTHORIZED_MENU_LIST = "/ajax/role/authorizedMenuList";
		public final static String ROLE_LIST = "/ajax/role/list";
		public final static String ROLE_UPDATE = "/ajax/role/update";
		public final static String ROLE_MENU_LIST = "/ajax/role/menuList";
		
		public final static String SEAT_ADD = "/ajax/seat/add";
		public final static String SEAT_DELETE = "/ajax/seat/delete";
		public final static String SEAT_REFRESH_SECRET = "/ajax/seat/refreshSecret";
		public final static String SEAT_LIST = "/ajax/seat/list";
		public final static String SEAT_UPDATE = "/ajax/seat/update";
		
		public final static String USER_LOGIN = "/ajax/user/login";
		public final static String USER_ADD = "/ajax/user/add";
		public final static String USER_DELETE = "/ajax/user/delete";
		public final static String USER_LIST = "/ajax/user/list";
		public final static String USER_UPDATE = "/ajax/user/update";
		
		public final static String FOOD_IMAGE_UPLOAD = "/ajax/file/foodImageUpload";
		
		
	}
	
}
