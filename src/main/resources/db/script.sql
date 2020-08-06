-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: vehicle_db
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `exam8`
--

DROP TABLE IF EXISTS `exam8`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam8` (
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam8`
--

LOCK TABLES `exam8` WRITE;
/*!40000 ALTER TABLE `exam8` DISABLE KEYS */;
INSERT INTO `exam8` VALUES ('larry','harry@gmail.com','male'),('harry','larry@gmail.com','Male'),('asdsad','sdasdada@gmail.com','Male'),('','','Male');
/*!40000 ALTER TABLE `exam8` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iuser_login_tbl`
--

DROP TABLE IF EXISTS `iuser_login_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iuser_login_tbl` (
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `photo` longblob,
  `gender` varchar(7) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iuser_login_tbl`
--

LOCK TABLES `iuser_login_tbl` WRITE;
/*!40000 ALTER TABLE `iuser_login_tbl` DISABLE KEYS */;
INSERT INTO `iuser_login_tbl` VALUES ('RakimDODoubleG','yoyo','Rakim Haka','haka@gmail.com','Bachelors','548568545',_binary 'C:Usersp_lamOneDriveDesktoppicture.png','male',NULL);
/*!40000 ALTER TABLE `iuser_login_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login_tbl`
--

DROP TABLE IF EXISTS `user_login_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_login_tbl` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  `mobile` varchar(12) DEFAULT NULL,
  `photo` varchar(1000) DEFAULT NULL,
  `gender` varchar(7) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login_tbl`
--

LOCK TABLES `user_login_tbl` WRITE;
/*!40000 ALTER TABLE `user_login_tbl` DISABLE KEYS */;
INSERT INTO `user_login_tbl` VALUES ('alish','test','BAlish','alish@gmail.com','Bachelors','939848484','https://www.csi.cuny.edu/sites/default/files/styles/inner_page_slider/public/images/slider/shutterstock_106224497.jpg?itok=kNdZz8B8','Male','2020-06-12 23:11:05'),('manish','Test1234','TechnoHunk','technohunk100@gmail.com','B.Tech','324324423',NULL,'Male','2020-07-27 19:33:57'),('p_lamsal2020@yahoo.com','SkYiC','vasaada','p_lamsal2020@hotmail.com','High School','4568725','https://images.unsplash.com/photo-1494548162494-384bba4ab999?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80','Male','2020-06-17 02:34:19'),('raza','Test1234','TechnoHunk','technohunk100@gmail.com','B.Tech','324324423',NULL,'Male','2020-07-14 15:39:00'),('raza1','Test1234','TechnoHunk','technohunk100@gmail.com','B.Tech','324324423',NULL,'Male','2020-07-14 15:40:08');
/*!40000 ALTER TABLE `user_login_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 13:00:47
