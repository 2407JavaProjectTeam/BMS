-- MySQL dump 10.13  Distrib 5.7.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library_management_system
-- ------------------------------------------------------
-- Server version	5.7.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_book`
--

DROP TABLE IF EXISTS `t_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `title` varchar(255) DEFAULT NULL COMMENT '图书标题',
  `book_img` varchar(255) DEFAULT NULL COMMENT '图书封面',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `publication_date` date DEFAULT NULL COMMENT '出版日期',
  `isbn` varchar(20) DEFAULT NULL COMMENT 'ISBN号',
  `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `book_status` tinyint(2) DEFAULT '1' COMMENT '状态（1：未借阅 2：已借阅）',
  PRIMARY KEY (`book_id`) USING BTREE,
  KEY `books_ibfk_1` (`category_id`) USING BTREE,
  CONSTRAINT `t_book_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book`
--

LOCK TABLES `t_book` WRITE;
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` VALUES (1,'活着',NULL,'余华','2024-07-01','1',3,1),(2,'基地',NULL,'艾萨克·阿西莫夫','1951-07-02','2',1,1),(3,'百年孤独',NULL,'加西亚·马尔克斯','2024-07-03','3',3,2),(4,'哲学简史',NULL,'康德','2024-07-02','4',4,1),(5,'明朝那些事儿',NULL,'当年明月','2024-07-19','5',2,1),(6,'资治通鉴',NULL,'司马光','2024-06-27','6',2,1);
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_borrow_record`
--

DROP TABLE IF EXISTS `t_borrow_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_borrow_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '借阅记录编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `book_id` int(11) DEFAULT NULL COMMENT '图书编号',
  `borrow_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借书日期',
  `return_date` timestamp NULL DEFAULT NULL COMMENT '还书日期',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态（1：未归还 2：已归还）',
  PRIMARY KEY (`record_id`) USING BTREE,
  KEY `borrow_records_ibfk_1` (`user_id`) USING BTREE,
  KEY `borrow_records_ibfk_2` (`book_id`) USING BTREE,
  CONSTRAINT `t_borrow_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_borrow_record_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_borrow_record`
--

LOCK TABLES `t_borrow_record` WRITE;
/*!40000 ALTER TABLE `t_borrow_record` DISABLE KEYS */;
INSERT INTO `t_borrow_record` VALUES (1,3,3,'2024-07-23 08:37:21',NULL,1),(2,4,2,'2024-07-23 08:38:03','2024-07-23 08:37:56',2),(3,4,1,'2024-07-23 08:38:52','2024-07-23 08:38:43',2);
/*!40000 ALTER TABLE `t_borrow_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
INSERT INTO `t_category` VALUES (1,'科幻类'),(2,'历史类'),(3,'文学类'),(4,'哲学类'),(5,'军事类');
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `permission_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `permission_icon` varchar(255) DEFAULT NULL COMMENT '权限icon样式',
  `permission_url` varchar(255) DEFAULT NULL COMMENT '权限url地址',
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'首页','el-icon-s-home','/index/home',0),(2,'图书管理','el-icon-s-tools','/index/sys',0),(3,'图书存储管理','el-icon-star-off','/index/store',3),(4,'图书分类管理','el-icon-star-off','/index/cgy',3),(5,'图书借阅管理','el-icon-star-off','/index/bors',3),(6,'用户管理','el-icon-user-solid','/index/user',0),(7,'图书借阅','el-icon-star-off','/index/bor',0),(8,'借阅历史','el-icon-star-off','/index/borh',0);
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'admin'),(2,'librarian'),(3,'member');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_premission`
--

DROP TABLE IF EXISTS `t_role_premission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_premission` (
  `role_premission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `premission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_premission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_premission`
--

LOCK TABLES `t_role_premission` WRITE;
/*!40000 ALTER TABLE `t_role_premission` DISABLE KEYS */;
INSERT INTO `t_role_premission` VALUES (20,1,1),(21,1,2),(22,1,3),(23,1,4),(24,1,5),(25,1,6),(26,1,7),(27,1,8),(28,2,1),(29,2,3),(30,2,4),(31,2,5),(32,3,1),(33,3,7),(34,3,8);
/*!40000 ALTER TABLE `t_role_premission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '账号名',
  `user_password` varchar(255) DEFAULT NULL COMMENT '密码',
  `user_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `user_rname` varchar(255) DEFAULT NULL COMMENT '姓名',
  `user_gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `user_tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `user_email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
  `user_status` tinyint(2) DEFAULT '1' COMMENT '状态（1：未冻结 2：冻结）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','$2a$12$0MIygcl3oAUGtbOe4M3b0OBFTBH67MPOK90VlIx.uaF.F7CQy/BQC',NULL,'admin','男','123123','123123@qq.com','2024-07-23 08:15:16',1),(2,'zs','$2a$12$0MIygcl3oAUGtbOe4M3b0OBFTBH67MPOK90VlIx.uaF.F7CQy/BQC',NULL,'zs','男','12333','12333@qq.com','2024-07-23 08:16:02',1),(3,'ls','$2a$12$0MIygcl3oAUGtbOe4M3b0OBFTBH67MPOK90VlIx.uaF.F7CQy/BQC',NULL,'ww','女','1341','41241@qq.com','2024-07-23 08:16:12',1),(4,'zl','$2a$12$0MIygcl3oAUGtbOe4M3b0OBFTBH67MPOK90VlIx.uaF.F7CQy/BQC',NULL,'zw','男','32234','3423@qq.com','2024-07-23 08:16:15',1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'User Role ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_role_id`) USING BTREE,
  UNIQUE KEY `unique_user_role` (`user_id`,`role_id`) USING BTREE,
  KEY `role_id_idx` (`role_id`) USING BTREE,
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1,1),(3,2,2),(7,3,3),(2,4,2);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-24 16:12:37
