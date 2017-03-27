var ajaxPath = "http://localhost:8080/OrderingSystem/ajax/";
var ajaxUrl = {
		login: ajaxPath + "user/login",
		restaurantManagerDetail: ajaxPath + "user/restaurantManagerDetail",
		updateRestaurantManager: ajaxPath + "user/updateRestaurantManager",
		updateRestaurantUser: ajaxPath + "user/updateRestaurantUser",
		addRestaurantManager: ajaxPath + "user/addRestaurantManager",
		
		roleMenuList: ajaxPath + "menu/roleMenuList",
		authorizedRoleMenuList: ajaxPath + "menu/authorizedRoleMenuList",
		allMenuList: ajaxPath + "menu/allList",
		menuInterfaceList: ajaxPath + "menu/menuInterfaceList",
		updateMenu: ajaxPath + "menu/update",
		addMenu: ajaxPath + "menu/add",
		deleteMenu: ajaxPath + "menu/delete",
		
		restaurantList: ajaxPath + "restaurant/list",
		addRestaurant: ajaxPath + "restaurant/add",
		updateRestaurant: ajaxPath + "restaurant/update",
		deleteRestaurant: ajaxPath + "restaurant/delete",
		
		interfaceList: ajaxPath + "interface/list",
		addInterface: ajaxPath + "interface/add",
		updateInterface: ajaxPath + "interface/update",
		deleteInterface: ajaxPath + "interface/delete",
		
		
		restaurantRoleList: ajaxPath + "role/restaurantRoleList",
		restaurantManagerRoleList: ajaxPath + "role/restaurantManagerRoleList",
		
		
		restaurantUserList: ajaxPath + "user/restaurantUserList",
		addRestaurantUser: ajaxPath + "user/addRestaurantUser",
		
		
		addRestaurantRole: ajaxPath + "role/addRestaurantRole",
		addRestaurantManagerRole: ajaxPath + "role/addRestaurantManagerRole",
		updateRestaurantRole: ajaxPath + "role/updateRestaurantRole",
		updateRestaurantManagerRole: ajaxPath + "role/updateRestaurantManagerRole",
		
		restaurantSeatList: ajaxPath + "seat/restaurantSeatList",
		addRestaurantSeat: ajaxPath + "seat/addRestaurantSeat",
		
		
		
};

var pagePathPre = "http://localhost:8080/OrderingSystem/page";
var pageUrl = {
		main: pagePathPre + "/main/main.html"
};
