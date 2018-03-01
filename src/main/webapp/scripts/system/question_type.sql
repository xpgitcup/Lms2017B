/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : lms2017dba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-03-01 17:53:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `question_type`
-- ----------------------------
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ei21extes3bkiv5byn0pfrg5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_type
-- ----------------------------
INSERT INTO `question_type` VALUES ('1', '0', '填空题');
INSERT INTO `question_type` VALUES ('2', '0', '选择题');
INSERT INTO `question_type` VALUES ('3', '0', '名词解释');
INSERT INTO `question_type` VALUES ('4', '0', '简答题');
INSERT INTO `question_type` VALUES ('5', '0', '简述题');
INSERT INTO `question_type` VALUES ('6', '0', '计算题');
