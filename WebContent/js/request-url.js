var ajaxPath = "http://localhost:8080/OrderingSystem/ajax/";
var ajaxUrl = {
		login: ajaxPath + "user/login",
		privilege: ajaxPath + "user/privilege",
		
		managerList: ajaxPath + "user/restaurantManagerList",
		addManager: ajaxPath + "user/addRestaurantManager",
		
		restaurantUserList: ajaxPath + "user/restaurantUserList",
		addRestaurantUser: ajaxPath + "user/addRestaurantUser",
		
		restaurantRoleList: ajaxPath + "role/restaurantRoleList",
		addRestaurantRole: ajaxPath + "role/addRestaurantRole",
		
		restaurantSeatList: ajaxPath + "seat/restaurantSeatList",
		addRestaurantSeat: ajaxPath + "seat/addRestaurantSeat",
		
		restaurantList: ajaxPath + "restaurant/list",
		restaurantAdd: ajaxPath + "restaurant/add",
};

var pagePath = "http://localhost:8080/OrderingSystem/page/";
var pageUrl = {
		main: pagePath + "main.html"
};
