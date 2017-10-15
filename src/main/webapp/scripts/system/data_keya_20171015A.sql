/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : lms2017dba

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-10-15 22:12:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_keya`
-- ----------------------------
DROP TABLE IF EXISTS `data_keya`;
CREATE TABLE `data_keya` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `up_data_key_id` bigint(20) DEFAULT NULL,
  `data_key_type` varchar(255) NOT NULL,
  `column_number` int(11) NOT NULL,
  `data_unit` varchar(255) DEFAULT NULL,
  `column_seperator` varchar(255) NOT NULL,
  `append_parameter` varchar(255) DEFAULT NULL,
  `data_tag` varchar(255) NOT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `order_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKokoa47yvmx079u6sq1lu4dq9y` (`up_data_key_id`),
  KEY `FK9l4diqnlwnipgyqxai15oq3k6` (`dictionary_id`),
  CONSTRAINT `FK9l4diqnlwnipgyqxai15oq3k6` FOREIGN KEY (`dictionary_id`) REFERENCES `data_dictionary` (`id`),
  CONSTRAINT `FKokoa47yvmx079u6sq1lu4dq9y` FOREIGN KEY (`up_data_key_id`) REFERENCES `data_keya` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_keya
-- ----------------------------
INSERT INTO `data_keya` VALUES ('1', '1', null, 'dataKeyNormal', '1', '无量纲', ',', null, '学生信息', '1', '0');
INSERT INTO `data_keya` VALUES ('2', '0', '1', 'dataKeyNormal', '1', '无量纲', ',', null, '学号', '1', '0');
INSERT INTO `data_keya` VALUES ('3', '0', '1', 'dataKeyNormal', '1', '无量纲', ',', null, '姓名', '1', '0');
INSERT INTO `data_keya` VALUES ('4', '0', '1', 'dataKeyEnum', '1', '无量纲', ',', '本科,专业硕士,学术硕士,博士', '类型', '1', '0');
INSERT INTO `data_keya` VALUES ('5', '0', '1', 'dataKeyNormal', '1', '无量纲', ',', null, '年级', '1', '0');
INSERT INTO `data_keya` VALUES ('6', '0', '1', 'dataKeyNormal', '1', '无量纲', ',', null, '导师', '1', '0');
INSERT INTO `data_keya` VALUES ('7', '1', null, 'dataKeyNormal', '1', '无量纲', ',', null, '教师信息', '1', '0');
INSERT INTO `data_keya` VALUES ('8', '0', '7', 'dataKeyNormal', '1', '无量纲', ',', null, '工号', '1', '0');
INSERT INTO `data_keya` VALUES ('9', '0', '7', 'dataKeyNormal', '1', '无量纲', ',', null, '姓名', '1', '0');
INSERT INTO `data_keya` VALUES ('10', '0', '7', 'dataKeyEnum', '1', '无量纲', ',', '讲师,工程师,副教授,高级工程师,教授,教授级高工,', '职称', '1', '0');
INSERT INTO `data_keya` VALUES ('11', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '项目', '1', '0');
INSERT INTO `data_keya` VALUES ('12', '0', '11', 'dataKeyNormal', '1', '无量纲', ',', null, '项目名称', '1', '0');
INSERT INTO `data_keya` VALUES ('13', '0', '11', 'dataKeyEnum', '1', '无量纲', ',', '教改,横向,一般纵向,重大专项,面上基金,重点基金', '项目性质', '1', '0');
INSERT INTO `data_keya` VALUES ('14', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '研究小组', '1', '0');
INSERT INTO `data_keya` VALUES ('15', '0', '14', 'dataKeyRef', '1', '无量纲', ',', '11', '项目', '1', '0');
INSERT INTO `data_keya` VALUES ('16', '1', '14', 'dataKeyRef', '1', '无量纲', ',', '1', '学生', '1', '0');
INSERT INTO `data_keya` VALUES ('17', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '工作进展', '1', '0');
INSERT INTO `data_keya` VALUES ('18', '1', '17', 'dataKeyRef', '1', '无量纲', ',', '11', '项目', '1', '0');
INSERT INTO `data_keya` VALUES ('19', '0', '17', 'dataKeyRef', '1', '无量纲', ',', '1', '学生信息', '1', '0');
INSERT INTO `data_keya` VALUES ('20', '0', '17', 'dataKeyText', '1', '无量纲', ',', null, '本周情况描述', '1', '0');
INSERT INTO `data_keya` VALUES ('21', '0', '17', 'dataKeyText', '1', '无量纲', ',', null, '下周计划', '1', '0');
INSERT INTO `data_keya` VALUES ('22', '0', '17', 'dataKeyText', '1', '无量纲', ',', null, '本周总结', '1', '0');
INSERT INTO `data_keya` VALUES ('23', '0', '17', 'dataKeyFile', '1', '无量纲', ',', null, '支撑文档', '1', '0');
INSERT INTO `data_keya` VALUES ('24', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '测试性项目', '2', '0');
INSERT INTO `data_keya` VALUES ('25', '0', '24', 'dataKeyNormal', '1', '无量纲', ',', null, '普通数据1列', '2', '0');
INSERT INTO `data_keya` VALUES ('26', '0', '24', 'dataKeyNormal', '2', '无量纲', ',', '第一列,第二列', '普通数据多列', '2', '0');
INSERT INTO `data_keya` VALUES ('27', '0', '24', 'dataKeyText', '1', '无量纲', ',', null, '多行数据1列', '2', '0');
INSERT INTO `data_keya` VALUES ('28', '1', '24', 'dataKeyText', '2', '无量纲', ',', '第一,第二,第三', '多行数据多列', '2', '0');
INSERT INTO `data_keya` VALUES ('29', '0', '24', 'dataKeyDate', '1', '无量纲', ',', null, '日期', '2', '0');
INSERT INTO `data_keya` VALUES ('30', '0', '24', 'dataKeyDateTime', '1', '无量纲', ',', null, '日期时间', '2', '0');
INSERT INTO `data_keya` VALUES ('31', '0', '24', 'dataKeyEnum', '1', '无量纲', ',', '枚举值1,枚举值2', '枚举类型', '2', '0');
INSERT INTO `data_keya` VALUES ('32', '0', '24', 'dataKeyFile', '1', '无量纲', ',', null, '文件', '2', '0');
INSERT INTO `data_keya` VALUES ('33', '0', '24', 'dataKeyRef', '1', '无量纲', ',', '24', '引用', '2', '0');
INSERT INTO `data_keya` VALUES ('34', '0', '33', 'dataKeyNormal', '1', '无量纲', ',', null, '继承自引用', '2', '0');
