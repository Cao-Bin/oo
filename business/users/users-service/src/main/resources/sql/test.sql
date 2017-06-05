/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-05 20:10:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', '111111', '23', '2017-06-05 12:56:32', 'MALE', '1');
INSERT INTO `user` VALUES ('2', 'lishi', '222222', '22', '2017-06-04 15:29:53', 'FEMALE', '1');
INSERT INTO `user` VALUES ('3', 'wangwu', '111111', '22', '2017-06-05 11:06:08', 'FEMALE', '1');
INSERT INTO `user` VALUES ('4', 'wangwu', '111111', '22', '2017-06-05 11:18:50', 'FEMALE', '1');
INSERT INTO `user` VALUES ('5', 'wangwu', '111111', '22', '2017-06-05 12:54:13', 'FEMALE', '1');
