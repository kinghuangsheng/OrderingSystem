var ajaxPath = "http://localhost:8080/OrderingSystem/ajax/";
var ajaxUrl = {
		login: ajaxPath + "user/login",
		roleMenuList: ajaxPath + "menu/roleMenuList",
		
		
		interfaceList: ajaxPath + "interface/list",
		allMenuList: ajaxPath + "menu/allList",
		
		addRestaurantManager: ajaxPath + "user/addRestaurantManager",
		
		restaurantUserList: ajaxPath + "user/restaurantUserList",
		addRestaurantUser: ajaxPath + "user/addRestaurantUser",
		
		restaurantRoleList: ajaxPath + "role/restaurantRoleList",
		addRestaurantRole: ajaxPath + "role/addRestaurantRole",
		
		restaurantSeatList: ajaxPath + "seat/restaurantSeatList",
		addRestaurantSeat: ajaxPath + "seat/addRestaurantSeat",
		
		restaurantList: ajaxPath + "restaurant/list",
		addRestaurant: ajaxPath + "restaurant/add",
		updateRestaurant: ajaxPath + "restaurant/update",
		deleteRestaurant: ajaxPath + "restaurant/delete",
		restaurantManagerDetail: ajaxPath + "user/restaurantManagerDetail",
		updateRestaurantManager: ajaxPath + "user/updateRestaurantManager",
		
};

var pagePathPre = "http://localhost:8080/OrderingSystem/page";
var pageUrl = {
		main: pagePathPre + "/main/main.html"
};
