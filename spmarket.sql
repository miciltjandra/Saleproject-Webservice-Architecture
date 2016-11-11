-- MySQL dump 10.16  Distrib 10.1.16-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: saleprojectmarket
-- ------------------------------------------------------
-- Server version	10.1.16-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `liked`
--

DROP TABLE IF EXISTS `liked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liked` (
  `product_id` int(10) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  KEY `product_id` (`product_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked`
--

LOCK TABLES `liked` WRITE;
/*!40000 ALTER TABLE `liked` DISABLE KEYS */;
INSERT INTO `liked` VALUES (15,13),(15,14),(16,14),(17,14),(17,13),(16,13),(20,13),(18,14),(15,12),(17,12),(20,12),(15,1),(18,1);
/*!40000 ALTER TABLE `liked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `likes` int(10) DEFAULT '0',
  `added_date` datetime DEFAULT NULL,
  `price` decimal(15,0) NOT NULL,
  `image` varchar(300) DEFAULT NULL,
  `seller_id` int(10) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `seller` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (15,'Oluben Playbe 5H','Wireless earphone from Oluben. Great sounds with great battery. Playbe 5H supports music playback for 8 hours straight.',4,'2016-10-08 12:23:00',280000,'images/Bang-Olufsens-Beoplay-H5-Wireless-Earphones-3.jpg',14,'Viniell'),(16,'Vike Zoom KB 5','Basketball shoes specially created for those who needs super speed from Vike',2,'2016-10-08 12:27:00',420000,'images/Nike Zoom Kobe V (5)-5 IDd5928.jpg',13,'Betatestah'),(17,'Goltech L502 Gaming Mouse','Awesome gaming mouse from Goltech. L502 comes with removeable weight to support different gamers need. Programmable DPI until 12000 DPI.',3,'2016-10-08 12:33:00',1280000,'images/Goltech L502-Protecore.png',13,'Betatestah'),(18,'Stewna & Daughters Grand Piano A-12','At 188 cm, the Model A is known for delivering a â€œgrandâ€ sound in a medium-scale piano. Seven octaves grand piano',2,'2016-10-08 12:41:00',86000000,'images/steinway.jpg',14,'Viniell'),(20,'Pigura lagi','besar juga',2,'2016-10-17 10:28:00',89898,'images/Capture.jpg',12,'SimpliCty');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `purchase_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_purchased` int(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `buyer_id` int(10) DEFAULT NULL,
  `consignee` varchar(100) DEFAULT NULL,
  `deliv_address` varchar(100) DEFAULT NULL,
  `postalcode` varchar(5) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `creditcard` varchar(12) DEFAULT NULL,
  `verification` varchar(3) DEFAULT NULL,
  `purchase_date` datetime DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `price` decimal(15,0) DEFAULT NULL,
  `seller_id` int(10) DEFAULT NULL,
  `image` varchar(300) DEFAULT NULL,
  `buyer_username` varchar(32) DEFAULT NULL,
  `seller_username` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`purchase_id`),
  KEY `product_purchased` (`product_purchased`),
  KEY `purchase_ibfk_2_idx` (`buyer_id`),
  KEY `purchase_ibfk_2_idx1` (`buyer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (6,15,2,13,'Joshua','Jalan Batununggal Something no 12. Bandung.','14012','084124123123','987912487871','324','2016-10-08 12:29:00','Oluben Playbe 5H',280000,14,'images/Bang-Olufsens-Beoplay-H5-Wireless-Earphones-3.jpg','Betatestah','Viniell'),(7,17,1,12,'Nikolas Wangsaputra','Jalan Baladewi no 11. Bandung','14048','081213514048','089124124981','421','2016-10-08 12:45:00','Goltech L502 Gaming Mouse',1280000,13,'images/Goltech L502-Protecore.png','SimpliCty','Betatestah'),(8,15,8,12,'Nikolas Wangsaputra','Jalan Baladewi no 11. Bandung','14048','081213514048','412948178419','124','2016-10-08 12:46:00','Oluben Playbe 5H',280000,14,'images/Bang-Olufsens-Beoplay-H5-Wireless-Earphones-3.jpg','SimpliCty','Viniell'),(9,18,1,12,'Mashiro Shiina','Sakurasou dorm. Tokyo.','65723','054329582542','958579235792','241','2016-10-08 12:46:00','Stewna & Daughters Grand Piano A-12',86000000,14,'images/steinway.jpg','SimpliCty','Viniell'),(10,18,10,12,'Nikolas Wangsaputra','adsfasd','12345','081233451231','123456789012','120','2016-10-17 10:23:00','Stewna & Daughters Grand Piano A-12',86000000,14,'images/steinway.jpg','SimpliCty','Viniell');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-11 22:45:45
