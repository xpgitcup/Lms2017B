/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : lms2017dba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-02-25 09:42:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i60mruj0y7a7vs99dqpiye7en` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '0', '100409E013', '油气管道SCADA系统');
INSERT INTO `course` VALUES ('2', '0', '100409T023', '油气储运工程最优化');
INSERT INTO `course` VALUES ('3', '0', '100409P009', '油气储运工程软件实训');
