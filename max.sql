/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : max

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 28/08/2019 22:18:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支持的银行名称',
  `ico` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '银行图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支持的银行' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bank
-- ----------------------------
INSERT INTO `bank` VALUES (1, '中国银行', '/img.img');
INSERT INTO `bank` VALUES (2, '建设银行', '/img.img');

-- ----------------------------
-- Table structure for category_game
-- ----------------------------
DROP TABLE IF EXISTS `category_game`;
CREATE TABLE `category_game`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '游戏分类ID',
  `category_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '游戏分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category_game
-- ----------------------------
INSERT INTO `category_game` VALUES (1, '体育下注');
INSERT INTO `category_game` VALUES (2, '真人视讯');

-- ----------------------------
-- Table structure for game_user
-- ----------------------------
DROP TABLE IF EXISTS `game_user`;
CREATE TABLE `game_user`  (
  `game_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务提供客户信息ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `game_user_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务提供客户名',
  `game_user_passwd` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务提供客户密码',
  `game_balance` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '余额',
  `supplier_id` int(11) NOT NULL COMMENT '服务提供ID',
  PRIMARY KEY (`game_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '游戏角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_bank
-- ----------------------------
DROP TABLE IF EXISTS `log_bank`;
CREATE TABLE `log_bank`  (
  `log_bank_id` int(11) NOT NULL,
  `bank_id` int(11) NOT NULL COMMENT '支持的银行ID',
  `user_id` int(11) NOT NULL COMMENT '使用人',
  `bank_user_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '持卡人姓名',
  `bank_account` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '卡号',
  `bank_type` int(1) NOT NULL COMMENT '类型：2充值成功 3绑定卡 1充值订单 ',
  PRIMARY KEY (`log_bank_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户银行卡记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_game
-- ----------------------------
DROP TABLE IF EXISTS `log_game`;
CREATE TABLE `log_game`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '游戏记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_user
-- ----------------------------
DROP TABLE IF EXISTS `log_user`;
CREATE TABLE `log_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_wallet_limit
-- ----------------------------
DROP TABLE IF EXISTS `log_wallet_limit`;
CREATE TABLE `log_wallet_limit`  (
  `log_wallet_limit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '限制记录ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `wallet_limit_id` int(11) NULL DEFAULT NULL COMMENT '限制内容ID',
  `changed_value` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '变动值，区分正负，元角分厘',
  PRIMARY KEY (`log_wallet_limit_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '钱包变动记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for relation_category_view
-- ----------------------------
DROP TABLE IF EXISTS `relation_category_view`;
CREATE TABLE `relation_category_view`  (
  `id` int(11) NOT NULL,
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `view_id` int(11) NULL DEFAULT NULL COMMENT '视图ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类与视图关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation_category_view
-- ----------------------------
INSERT INTO `relation_category_view` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int(11) NOT NULL COMMENT '服务提供ID',
  `supplier_type_id` int(11) NULL DEFAULT NULL COMMENT '服务提供类型ID',
  `supplier_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务提供名字',
  `private_key` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '私钥',
  `supplier_public_key` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务提供公钥',
  `merchant_no` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务提供的商户号',
  `host` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务提供网关地址',
  `version` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '服务提供' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (2, 2, 'Cooda微信', NULL, '7d8ea9121c47b5de751b6e0ea9920606', '130', 'http://www.chundapay.com:9002/pay', NULL);
INSERT INTO `supplier` VALUES (3, 2, 'Cooda银联', NULL, '5e3b1a1375077507f2c182ec36e524b9', '131', 'http://www.chundapay.com:9002/pay', NULL);
INSERT INTO `supplier` VALUES (4, 2, '测试', NULL, 'erjmfdjer5643rtfgeirtgQWR654', '1005708855', 'https://mmafpvahtt.6785151.com', NULL);
INSERT INTO `supplier` VALUES (5, 1, 'X 竞彩', NULL, NULL, '105101011WF6wTsG', 'http://nb2.nbbets.com', NULL);

-- ----------------------------
-- Table structure for supplier_type
-- ----------------------------
DROP TABLE IF EXISTS `supplier_type`;
CREATE TABLE `supplier_type`  (
  `supplier_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务提供类型ID',
  `supplier_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务类型',
  PRIMARY KEY (`supplier_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '服务提供类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier_type
-- ----------------------------
INSERT INTO `supplier_type` VALUES (1, '游戏');
INSERT INTO `supplier_type` VALUES (2, '充值');
INSERT INTO `supplier_type` VALUES (3, '短信');
INSERT INTO `supplier_type` VALUES (4, '消息');
INSERT INTO `supplier_type` VALUES (5, '提现');
INSERT INTO `supplier_type` VALUES (6, '活动');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，手机号',
  `passwd` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nick` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  `balance` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '钱包余额，字符元角分厘',
  `status` int(2) NOT NULL COMMENT '账号状态 0 正常 ',
  `withdraw_passwd` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资金密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_name`(`name`) USING BTREE COMMENT '用户名，手机号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test', 'test', 'test', '2019-08-08 15:36:40', '0', 0, NULL);
INSERT INTO `user` VALUES (2, '18959200001', '123456', '公良纯烟', '2019-08-27 07:16:49', '0', 0, NULL);

-- ----------------------------
-- Table structure for view
-- ----------------------------
DROP TABLE IF EXISTS `view`;
CREATE TABLE `view`  (
  `view_id` int(11) NOT NULL AUTO_INCREMENT,
  `view_type` int(1) NULL DEFAULT NULL COMMENT '界面元素类型：1 公告 2 轮播图 3 弹窗 4游戏 5活动 6充值方式',
  `view_title` varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `view_title_type` int(1) NULL DEFAULT NULL COMMENT '标题类型 0 文本 1图片链接',
  `view_content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `view_content_type` int(11) NULL DEFAULT NULL COMMENT '内容类型 0文本 1图片链接',
  `view_jump` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '跳转内容',
  `view_jump_type` int(1) NULL DEFAULT NULL COMMENT '跳转类型 0无跳转 1站内 2链接',
  `view_topping` int(1) NULL DEFAULT NULL COMMENT '置顶:0普通 1置顶',
  `view_order` int(2) NULL DEFAULT NULL COMMENT '顺序位置',
  `view_status` int(1) NULL DEFAULT NULL COMMENT '状态：0开启 1关闭',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`view_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '界面元素视图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wallet_limit
-- ----------------------------
DROP TABLE IF EXISTS `wallet_limit`;
CREATE TABLE `wallet_limit`  (
  `wallet_limitid_id` int(11) NOT NULL,
  `wallet_limit_way` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '限制方式',
  `wallet_limit_value` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '限制值',
  PRIMARY KEY (`wallet_limitid_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统级别提现设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wallet_order
-- ----------------------------
DROP TABLE IF EXISTS `wallet_order`;
CREATE TABLE `wallet_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `wallet_order_type` int(2) NULL DEFAULT NULL COMMENT '操作：1充值 2提现 3转入游戏 4转出游戏 5活动入款',
  `wallet_order_money` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '金额,元角分厘',
  `wallet_order_series` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单号',
  `wallet_order_status` int(2) NULL DEFAULT NULL COMMENT '订单状态 0 处理中 1已结束',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int(1) NULL DEFAULT NULL COMMENT '删除 1 已删除',
  `log_bank_id` int(11) NULL DEFAULT NULL COMMENT '银行信息',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `unique`(`user_id`, `wallet_order_type`, `wallet_order_series`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wallet_series
-- ----------------------------
DROP TABLE IF EXISTS `wallet_series`;
CREATE TABLE `wallet_series`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `series` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '钱包流水号',
  `type` int(2) NULL DEFAULT NULL COMMENT '1 充值 2 提现 3 转入游戏 4 转出游戏',
  `status` int(2) NULL DEFAULT NULL COMMENT '所处状态 0 处理中 1已用完',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`user_id`, `series`, `type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '钱包流水号' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
