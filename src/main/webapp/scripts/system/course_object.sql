/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : lms2017dba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-02-25 16:19:35
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `course_object`
-- ----------------------------
DROP TABLE IF EXISTS `course_object`;
CREATE TABLE `course_object` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `context` varchar(255) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dtmxpeblddo4m50lfmtdoi99t` (`context`),
  KEY `FKkbi94q37oayagaq52svj4pbuo` (`course_id`),
  CONSTRAINT `FKkbi94q37oayagaq52svj4pbuo` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_object
-- ----------------------------
INSERT INTO `course_object` VALUES ('1', '0', '1)	掌握油气管道监控与数据采集（SCADA）系统的组成、功能。', '1');
INSERT INTO `course_object` VALUES ('2', '0', '2)	了解其在油气储运自动化的作用，与工艺设计、运行维护的关系。', '1');
INSERT INTO `course_object` VALUES ('3', '0', '3)	了解油气储运自动化中常用设备的电气控制原理。', '1');
INSERT INTO `course_object` VALUES ('4', '0', '4)	掌握油气储运自动化中最基本的PLC编程、以及基本的组态软件操作', '1');
INSERT INTO `course_object` VALUES ('5', '0', '5)	了解、掌握SCADA系统所涉及的网络通讯方面的基本知识。', '1');
INSERT INTO `course_object` VALUES ('6', '0', '6)	了解SCADA系统中数据库的功能，掌握初步的数据库操作方法。', '1');
INSERT INTO `course_object` VALUES ('7', '0', '7)	了解油气储运自动化中运行控制的基本方法和数字管道建设内容。', '1');
