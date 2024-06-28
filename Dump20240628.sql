-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydatabase
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `base`
--

DROP TABLE IF EXISTS `base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base` (
  `id_base` int NOT NULL,
  `base_description` varchar(45) NOT NULL,
  PRIMARY KEY (`id_base`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base`
--

LOCK TABLES `base` WRITE;
/*!40000 ALTER TABLE `base` DISABLE KEYS */;
INSERT INTO `base` VALUES (1,'green tea'),(2,'dark tea'),(465,'juise'),(512,'milk');
/*!40000 ALTER TABLE `base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boba`
--

DROP TABLE IF EXISTS `boba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boba` (
  `id_boba` int NOT NULL,
  `boba_description` varchar(45) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id_boba`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boba`
--

LOCK TABLES `boba` WRITE;
/*!40000 ALTER TABLE `boba` DISABLE KEYS */;
INSERT INTO `boba` VALUES (1,'orange',40),(2,'strawberry',50),(468,'apple',40),(505,'клюква',50),(514,'личи',40);
/*!40000 ALTER TABLE `boba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (629);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_content`
--

DROP TABLE IF EXISTS `order_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_content` (
  `order_id` int NOT NULL,
  `tea_boba_id` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`order_id`,`tea_boba_id`),
  KEY `FKqlr0rc8mlwekttrfvntgr8r1m` (`tea_boba_id`),
  CONSTRAINT `FK1wkby9xe10s135asp5ljalt15` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id_orders`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKqlr0rc8mlwekttrfvntgr8r1m` FOREIGN KEY (`tea_boba_id`) REFERENCES `tea_boba` (`id_tea_boba`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_content`
--

LOCK TABLES `order_content` WRITE;
/*!40000 ALTER TABLE `order_content` DISABLE KEYS */;
INSERT INTO `order_content` VALUES (620,619,250),(622,621,1920),(625,623,1440),(625,624,720),(627,626,480);
/*!40000 ALTER TABLE `order_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id_orders` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `order_date_time` datetime(6) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id_orders`),
  KEY `FKq0ny5rek18pjqb8a86pnnyt9d` (`user_id`),
  CONSTRAINT `FKq0ny5rek18pjqb8a86pnnyt9d` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (620,26,'2023-12-15 10:34:07.163096',250),(622,26,'2023-12-15 11:59:05.177729',1920),(625,26,'2023-12-15 11:59:41.576585',2160),(627,26,'2023-12-18 10:08:11.484623',480);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tea`
--

DROP TABLE IF EXISTS `tea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tea` (
  `id_tea` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `base_id` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id_tea`,`base_id`),
  KEY `id_base_idx` (`base_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `id_base` FOREIGN KEY (`base_id`) REFERENCES `base` (`id_base`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tea`
--

LOCK TABLES `tea` WRITE;
/*!40000 ALTER TABLE `tea` DISABLE KEYS */;
INSERT INTO `tea` VALUES (1,'bao','нежный молочный чай ',2,200),(3,'coconut','чай с кокосовым сиропом',1,200),(471,'banana tea','чай с банановым сиропом',465,200),(529,'лесные орехи','молочный напиток с ореховым вкусом',512,200);
/*!40000 ALTER TABLE `tea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tea_boba`
--

DROP TABLE IF EXISTS `tea_boba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tea_boba` (
  `id_tea_boba` int NOT NULL,
  `tea_id` int NOT NULL,
  `boba_id` int NOT NULL,
  `quantity` varchar(45) NOT NULL,
  `order_created` bit(1) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id_tea_boba`),
  KEY `tea_boba_tea_fk_idx` (`tea_id`) /*!80000 INVISIBLE */,
  KEY `tea_boba_boba_fk_idx` (`boba_id`) /*!80000 INVISIBLE */,
  KEY `FKnpvvfm6gptxun5esg20pnxet6` (`user_id`),
  CONSTRAINT `FKnpvvfm6gptxun5esg20pnxet6` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id_user`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tea_boba_boba_fk` FOREIGN KEY (`boba_id`) REFERENCES `boba` (`id_boba`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tea_boba_tea_fk` FOREIGN KEY (`tea_id`) REFERENCES `tea` (`id_tea`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tea_boba`
--

LOCK TABLES `tea_boba` WRITE;
/*!40000 ALTER TABLE `tea_boba` DISABLE KEYS */;
INSERT INTO `tea_boba` VALUES (619,1,505,'1',_binary '',26,0),(621,529,468,'8',_binary '',26,0),(623,471,1,'6',_binary '',26,0),(624,529,514,'3',_binary '',26,0),(626,3,1,'2',_binary '',26,0),(628,3,1,'1',_binary '\0',26,240);
/*!40000 ALTER TABLE `tea_boba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (3,'admin@gmail.com','admin','$2a$10$68CK445gq.BVDjxgGSLC9.JIMefytqQTKqlNZohiUQ8Y9uxztox7y','ROLE_ADMIN'),(26,'user@gmail.com','user','$2a$10$AhaV7vD0X13G.IX3SRbSceZJyfUnA5r5a2FQ8Whfsb02caSZEkTg.','ROLE_USER');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mydatabase'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-28 14:07:12
