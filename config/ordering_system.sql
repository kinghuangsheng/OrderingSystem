/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : ordering_system

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-28 00:28:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '营业执照',
  `is_ok` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `license` (`path`),
  UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interface
-- ----------------------------
INSERT INTO `interface` VALUES ('5', '接口列表', '/ajax/interface/list', 'true');
INSERT INTO `interface` VALUES ('6', '所有菜单列表', '/ajax/menu/allList', 'true');
INSERT INTO `interface` VALUES ('7', '餐厅管理员添加', '/ajax/user/addRestaurantManager', 'true');
INSERT INTO `interface` VALUES ('8', '餐厅用户添加', '/ajax/user/addRestaurantUser', '');
INSERT INTO `interface` VALUES ('9', '餐厅角色列表', '/ajax/role/restaurantRoleList', 'true');
INSERT INTO `interface` VALUES ('10', '餐厅席位列表', '/ajax/seat/restaurantSeatList', '');
INSERT INTO `interface` VALUES ('11', '餐厅席位添加', '/ajax/seat/addRestaurantSeat', '');
INSERT INTO `interface` VALUES ('12', '餐厅列表', '/ajax/restaurant/list', 'true');
INSERT INTO `interface` VALUES ('13', '餐厅添加', '/ajax/restaurant/add', 'true');
INSERT INTO `interface` VALUES ('14', '餐厅更新', '/ajax/restaurant/update', 'true');
INSERT INTO `interface` VALUES ('15', '餐厅用户列表', '/ajax/user/restaurantUserList', 'true');
INSERT INTO `interface` VALUES ('16', '餐厅角色添加', '/ajax/role/addRestaurantRole', 'true');
INSERT INTO `interface` VALUES ('17', '餐厅删除', '/ajax/restaurant/delete', 'true');
INSERT INTO `interface` VALUES ('18', '餐厅用户更新', '/ajax/user/updateRestaurantUser', 'true');
INSERT INTO `interface` VALUES ('19', '餐厅管理员详细信息', '/ajax/user/restaurantManagerDetail', 'true');
INSERT INTO `interface` VALUES ('20', '餐厅角色更新', '/ajax/role/updateRestaurantRole', 'true');
INSERT INTO `interface` VALUES ('21', '接口添加', '/ajax/interface/add', 'true');
INSERT INTO `interface` VALUES ('22', '菜单更新', '/ajax/menu/update', 'true');
INSERT INTO `interface` VALUES ('23', '菜单接口列表', '/ajax/menu/menuInterfaceList', 'true');
INSERT INTO `interface` VALUES ('24', '菜单删除', '/ajax/menu/delete', 'true');
INSERT INTO `interface` VALUES ('25', '子菜单添加', '/ajax/menu/add', 'true');
INSERT INTO `interface` VALUES ('28', '接口更新', '/ajax/interface/update', 'true');
INSERT INTO `interface` VALUES ('30', '接口删除', '/ajax/interface/delete', 'true');
INSERT INTO `interface` VALUES ('33', '角色授权接口列表', '/ajax/menu/authorizedRoleMenuList', '');
INSERT INTO `interface` VALUES ('35', '餐厅管理员更新', '/ajax/user/updateRestaurantManager', '');
INSERT INTO `interface` VALUES ('36', '餐厅管理员角色更新', '/ajax/role/updateRestaurantManagerRole', '');
INSERT INTO `interface` VALUES ('37', '餐厅管理员角色添加', '/ajax/role/addRestaurantManagerRole', '');
INSERT INTO `interface` VALUES ('38', '餐厅管理员角色列表', '/ajax/role/restaurantManagerRoleList', '');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '请求路径',
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', '/restaurant-manage/user-manage.html', '10');
INSERT INTO `menu` VALUES ('2', '角色管理', '/restaurant-manage/role-manage.html', '10');
INSERT INTO `menu` VALUES ('5', '餐厅管理', '/system-manage/restaurant-manage.html', '7');
INSERT INTO `menu` VALUES ('6', '菜单管理', '/system-manage/menu-manage.html', '7');
INSERT INTO `menu` VALUES ('7', '系统管理', '', '8');
INSERT INTO `menu` VALUES ('8', '根结点', '', '0');
INSERT INTO `menu` VALUES ('9', '接口管理', '/system-manage/interface-manage.html', '7');
INSERT INTO `menu` VALUES ('10', '餐厅管理', '', '8');
INSERT INTO `menu` VALUES ('11', '级别管理', '/system-manage/level-manage.html', '7');

-- ----------------------------
-- Table structure for menu_interface
-- ----------------------------
DROP TABLE IF EXISTS `menu_interface`;
CREATE TABLE `menu_interface` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(10) unsigned NOT NULL DEFAULT '0',
  `interface_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_interface
-- ----------------------------
INSERT INTO `menu_interface` VALUES ('124', '6', '6');
INSERT INTO `menu_interface` VALUES ('125', '6', '22');
INSERT INTO `menu_interface` VALUES ('126', '6', '23');
INSERT INTO `menu_interface` VALUES ('127', '6', '24');
INSERT INTO `menu_interface` VALUES ('128', '6', '25');
INSERT INTO `menu_interface` VALUES ('129', '6', '5');
INSERT INTO `menu_interface` VALUES ('130', '9', '21');
INSERT INTO `menu_interface` VALUES ('131', '9', '28');
INSERT INTO `menu_interface` VALUES ('132', '9', '30');
INSERT INTO `menu_interface` VALUES ('136', '2', '16');
INSERT INTO `menu_interface` VALUES ('137', '2', '20');
INSERT INTO `menu_interface` VALUES ('138', '2', '9');
INSERT INTO `menu_interface` VALUES ('139', '2', '33');
INSERT INTO `menu_interface` VALUES ('159', '1', '8');
INSERT INTO `menu_interface` VALUES ('160', '1', '15');
INSERT INTO `menu_interface` VALUES ('161', '1', '18');
INSERT INTO `menu_interface` VALUES ('162', '1', '9');
INSERT INTO `menu_interface` VALUES ('163', '11', '36');
INSERT INTO `menu_interface` VALUES ('164', '11', '37');
INSERT INTO `menu_interface` VALUES ('165', '11', '38');
INSERT INTO `menu_interface` VALUES ('175', '5', '13');
INSERT INTO `menu_interface` VALUES ('176', '5', '14');
INSERT INTO `menu_interface` VALUES ('177', '5', '7');
INSERT INTO `menu_interface` VALUES ('178', '5', '17');
INSERT INTO `menu_interface` VALUES ('179', '5', '19');
INSERT INTO `menu_interface` VALUES ('180', '5', '12');
INSERT INTO `menu_interface` VALUES ('181', '5', '35');
INSERT INTO `menu_interface` VALUES ('182', '5', '38');

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `license` varchar(255) NOT NULL DEFAULT '' COMMENT '营业执照',
  `state` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态  0:正常， 1：停用',
  `type` int(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `license` (`license`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of restaurant
-- ----------------------------
INSERT INTO `restaurant` VALUES ('0', '系统开发', 'system-develop', '0', '1');
INSERT INTO `restaurant` VALUES ('1', '系统管理', 'system-manage', '0', '1');
INSERT INTO `restaurant` VALUES ('101', 'a', 'a', '0', '0');
INSERT INTO `restaurant` VALUES ('102', 'b', 'b', '0', '0');
INSERT INTO `restaurant` VALUES ('103', 'c', 'c', '0', '0');
INSERT INTO `restaurant` VALUES ('104', 'd', 'd', '0', '0');
INSERT INTO `restaurant` VALUES ('105', 'aaa', 'aaa', '0', '0');
INSERT INTO `restaurant` VALUES ('106', '龙门客栈', '12345678', '0', '0');
INSERT INTO `restaurant` VALUES ('107', '天上人间', 'tsrj', '0', '0');
INSERT INTO `restaurant` VALUES ('108', 'f', 'f', '0', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL DEFAULT '',
  `restaurant_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('4', 'a-role-1', '101');
INSERT INTO `role` VALUES ('5', 'role-2', '101');
INSERT INTO `role` VALUES ('3', '厨师', '102');
INSERT INTO `role` VALUES ('0', '开发者', '0');
INSERT INTO `role` VALUES ('1', '系统管理员', '0');
INSERT INTO `role` VALUES ('2', '金牌会员', '1');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL DEFAULT '0',
  `menu_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('28', '0', '5');
INSERT INTO `role_menu` VALUES ('29', '0', '6');
INSERT INTO `role_menu` VALUES ('30', '0', '7');
INSERT INTO `role_menu` VALUES ('31', '0', '9');
INSERT INTO `role_menu` VALUES ('43', '0', '2');
INSERT INTO `role_menu` VALUES ('79', '0', '1');
INSERT INTO `role_menu` VALUES ('83', '1', '7');
INSERT INTO `role_menu` VALUES ('84', '1', '5');
INSERT INTO `role_menu` VALUES ('85', '3', '7');
INSERT INTO `role_menu` VALUES ('86', '3', '2');
INSERT INTO `role_menu` VALUES ('99', '0', '10');
INSERT INTO `role_menu` VALUES ('100', '2', '10');
INSERT INTO `role_menu` VALUES ('101', '2', '2');
INSERT INTO `role_menu` VALUES ('102', '2', '1');
INSERT INTO `role_menu` VALUES ('103', '5', '10');
INSERT INTO `role_menu` VALUES ('104', '5', '1');
INSERT INTO `role_menu` VALUES ('105', '5', '2');
INSERT INTO `role_menu` VALUES ('109', '0', '11');
INSERT INTO `role_menu` VALUES ('110', '4', '10');
INSERT INTO `role_menu` VALUES ('111', '4', '1');

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL DEFAULT '',
  `restaurant_id` int(10) unsigned NOT NULL DEFAULT '0',
  `customer_num` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seat
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL DEFAULT '',
  `name` varchar(10) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `restaurant_id` int(10) unsigned NOT NULL DEFAULT '0',
  `role_id` int(10) NOT NULL DEFAULT '0',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '账号状态：0正常，1停用',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `type` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '0：普通用户  1：管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`,`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'p@ssw0rd', '0', '0', '0', '', '1');
INSERT INTO `user` VALUES ('29', 'a', 'a', 'a', '101', '2', '0', '123', '1');
INSERT INTO `user` VALUES ('30', 'b', 'b', 'b', '102', '2', '0', '13', '1');
INSERT INTO `user` VALUES ('31', 'sys1', 'sys1', 'sys1', '0', '1', '0', '1223', '0');
INSERT INTO `user` VALUES ('32', 'sys2', 'sys2', 'sys2', '0', '1', '0', '3322', '0');
INSERT INTO `user` VALUES ('33', 'sys3', 'sys3', 'sys3', '0', '1', '0', '1223', '0');
INSERT INTO `user` VALUES ('34', 'f', 'f', 'f', '108', '2', '0', '12333', '1');
INSERT INTO `user` VALUES ('35', 'ab', 'ab', 'ab', '101', '5', '0', '123', '0');
INSERT INTO `user` VALUES ('36', 'ac', 'ac', 'ac', '101', '4', '0', '123', '0');
