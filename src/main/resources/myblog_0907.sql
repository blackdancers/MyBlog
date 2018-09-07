/*
Navicat MySQL Data Transfer

Source Server         : MY-ECS-MYSQL
Source Server Version : 50722
Source Host           : 47.93.216.252:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-09-07 15:03:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章标题',
  `article_content` text COLLATE utf8mb4_unicode_ci COMMENT '文章内容',
  `article_cover` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '封面图[url]',
  `article_flag` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标记',
  `article_blurb` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章简介',
  `views` int(11) DEFAULT NULL COMMENT '浏览次数',
  `appreciation` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否开启赞赏功能[0:是;1:否]',
  `share_statement` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '转载声明是否开启[0:是;1:否]',
  `is_comment` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否可评论[0:是;1:否]',
  `is_issue` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布[0:是;1:否]',
  `is_recommend` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐[0:是;1:否]',
  `class_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='博客文章';

-- ----------------------------
-- Table structure for article_ref_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_ref_tag`;
CREATE TABLE `article_ref_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章标签关联表 ';

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `class_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章分类 ';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `parent_comment_id` bigint(20) DEFAULT NULL COMMENT '评论父ID',
  `nick_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `comment_content` text COLLATE utf8mb4_unicode_ci COMMENT '评论内容',
  `admin_comment` bit(1) DEFAULT NULL COMMENT '管理员评论',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章评论';

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `description` varchar(200) DEFAULT NULL COMMENT '方法描述',
  `method` varchar(200) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(500) DEFAULT NULL COMMENT '方法参数',
  `type` char(2) DEFAULT NULL COMMENT '注解类型[0:控制器方法;1:service方法]',
  `module` varchar(20) DEFAULT '0' COMMENT '模块名称',
  `real_ip` varchar(20) DEFAULT NULL COMMENT '真实IP',
  `error_code` varchar(500) DEFAULT NULL COMMENT '错误码',
  `error_detail` text COMMENT '异常详情',
  `user_id` bigint(20) DEFAULT NULL COMMENT '操作者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `user_account` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
  `user_avatar` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `sex` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别[0:保密,1:男,2:女]',
  `mobile_phone` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
  `user_email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `user_type` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `user_status` char(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态[0:正常;1:禁用;2:删除]',
  `salt_value` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '盐值(加密)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_account_mobile_phone_unique` (`user_name`,`user_account`,`mobile_phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tag_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='博客标签';


INSERT INTO `myblog`.`system_user` (`id`, `user_name`, `user_account`, `user_avatar`, `password`, `sex`, `mobile_phone`, `user_email`, `user_type`, `user_status`, `salt_value`, `create_date`, `update_date`) VALUES ('1', '野猪佩奇', '18611899696', '/images/avatar.jpg', 'A668CC205F023E0BE82D6B021C8B3DCD', '0', '4734135B1E3223E9879F22F78F4851B5', '245675499@qq.com', NULL, '0', NULL, '2018-07-11 08:10:19', '2018-08-10 15:42:02');