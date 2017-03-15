var ajaxPath = "http://localhost:8080/OrderingSystem/ajax/";
var ajaxUrl = {
		login: ajaxPath + "user/login",
		privilege: ajaxPath + "user/privilege",
		manager_list: ajaxPath + "user/restaurantManagerList",
		add_manager: ajaxPath + "user/addRestaurantManager",
		restaurant_user_list: ajaxPath + "user/restaurantUserList",
		add_restaurant_user: ajaxPath + "user/addRestaurantUser",
		
		restaurant_list: ajaxPath + "restaurant/list",
		restaurant_add: ajaxPath + "restaurant/add",
};

var pagePath = "http://localhost:8080/OrderingSystem/page/";
var pageUrl = {
		main: pagePath + "main.html"
};
