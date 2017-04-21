var ajaxPath = "http://localhost:8080/OrderingSystem/ajax/";
var ajaxUrl = {
		

		restaurantCategoryList: ajaxPath + "category/list",
		addRestaurantCategory: ajaxPath + "category/add",
		updateRestaurantCategory: ajaxPath + "category/update",
		
		restaurantFoodList: ajaxPath + "food/list",
		restaurantFoodCategoryList: ajaxPath + "food/categoryList",
		addRestaurantFood: ajaxPath + "food/add",
		updateRestaurantFood: ajaxPath + "food/update",
		deleteRestaurantFood: ajaxPath + "food/delete",
		
		interfaceList: ajaxPath + "interface/list",
		addInterface: ajaxPath + "interface/add",
		updateInterface: ajaxPath + "interface/update",
		deleteInterface: ajaxPath + "interface/delete",
		
		allMenuList: ajaxPath + "menu/allList",
		menuInterfaceList: ajaxPath + "menu/interfaceList",
		updateMenu: ajaxPath + "menu/update",
		addMenu: ajaxPath + "menu/add",
		deleteMenu: ajaxPath + "menu/delete",
		
		login: ajaxPath + "user/login",
		updateRestaurantUser: ajaxPath + "user/update",
		deleteRestaurantUser: ajaxPath + "user/delete",
		restaurantUserList: ajaxPath + "user/list",
		addRestaurantUser: ajaxPath + "user/add",
		
		restaurantManagerDetail: ajaxPath + "manager/detail",
		updateRestaurantManager: ajaxPath + "manager/update",
		addRestaurantManager: ajaxPath + "manager/add",
		
		restaurantManagerRoleList: ajaxPath + "managerRole/list",
		addRestaurantManagerRole: ajaxPath + "managerRole/add",
		updateRestaurantManagerRole: ajaxPath + "managerRole/update",
		
		restaurantList: ajaxPath + "restaurant/list",
		addRestaurant: ajaxPath + "restaurant/add",
		updateRestaurant: ajaxPath + "restaurant/update",
		toggleRestaurantState: ajaxPath + "restaurant/toggleState",
		
		authorizedRoleMenuList: ajaxPath + "role/authorizedMenuList",
		restaurantRoleList: ajaxPath + "role/list",
		addRestaurantRole: ajaxPath + "role/add",
		updateRestaurantRole: ajaxPath + "role/update",
		roleMenuList: ajaxPath + "role/menuList",
		
		restaurantSeatList: ajaxPath + "seat/list",
		addRestaurantSeat: ajaxPath + "seat/add",
		updateRestaurantSeat: ajaxPath + "seat/update",
		deleteRestaurantSeat: ajaxPath + "seat/delete",
		refreshSeatSecret: ajaxPath + "seat/refreshSecret",

		foodImageUpload: ajaxPath + "file/foodImageUpload",
		
		
};

var pagePathPre = "http://localhost:8080/OrderingSystem/page";
var pageUrl = {
		main: pagePathPre + "/main/main.html"
};
