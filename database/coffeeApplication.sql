-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: coffeeapplication
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `BillDate` date DEFAULT NULL,
  `BillTotal` float DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `CustomerID` int NOT NULL,
  `UserID` int NOT NULL,
  `TableID` int NOT NULL,
  `PaymentID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `UserID` (`UserID`),
  KEY `TableID` (`TableID`),
  KEY `PaymentID` (`PaymentID`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`ID`),
  CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`),
  CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`TableID`) REFERENCES `tablee` (`ID`),
  CONSTRAINT `bill_ibfk_4` FOREIGN KEY (`PaymentID`) REFERENCES `payment` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,NULL,NULL,'Done',1,10,1,2),(2,NULL,NULL,'Done',2,19,2,1),(3,NULL,NULL,'Waiting',4,10,4,3),(4,NULL,NULL,'Waiting',5,19,6,3),(5,NULL,NULL,'Done',1,19,1,1);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billdetails`
--

DROP TABLE IF EXISTS `billdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billdetails` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `QuantityProduct` int NOT NULL,
  `BillID` int NOT NULL,
  `ProductID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `BillID` (`BillID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `billdetails_ibfk_1` FOREIGN KEY (`BillID`) REFERENCES `bill` (`ID`),
  CONSTRAINT `billdetails_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billdetails`
--

LOCK TABLES `billdetails` WRITE;
/*!40000 ALTER TABLE `billdetails` DISABLE KEYS */;
INSERT INTO `billdetails` VALUES (1,4,1,12),(2,1,2,15),(3,2,3,1),(4,4,4,20);
/*!40000 ALTER TABLE `billdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Bánh ',NULL),(2,'Coffe',NULL),(3,'Trà ',NULL),(4,'Đá Xay',NULL),(5,'PhinDi',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Hoàng Phương Vy','0347238789','Hà Nội','phuongvy@gmail.com'),(2,'Lê Hương','034686868','Hà Nội','lehuonglote@gmail.com'),(3,'Dương Quá','0567265476','Hà Nội','vocong@gmail.com'),(4,'Minh Triết','0909451777','Hà Nội','trietgia@gmail.com'),(5,'Hồng Hải','0956521332','TP Hồ Chí Minh','hai091224@gmail.com'),(6,'Công Đại','0934523113','TP Hồ Chí Minh','daibao@gmail.com'),(7,'Văn Hoàn','0123123456','Hà Nội','hoansayain@gmail.com'),(8,'Nhiếp An','0347786200','Hà Nội','nhiepan01@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `PaymentName` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PaymentName` (`PaymentName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'Cash'),(2,'Credit Card'),(3,'Credit Transfer');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Price` float NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Image` varchar(50) DEFAULT NULL,
  `CategoryID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name` (`Name`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,29000,'Bánh Su Kem',NULL,NULL,1),(2,29000,'Bánh Chuối',NULL,NULL,1),(3,29000,'Bánh Bông Lan Trứng Muối',NULL,NULL,1),(4,35000,'Mousse Đào',NULL,NULL,1),(5,35000,'Mousse Cacao',NULL,NULL,1),(6,35000,'Phô Mai Trà Xanh',NULL,NULL,1),(7,35000,'Phô Mai Caramel',NULL,NULL,1),(8,25000,'Bánh Mì Bò Sốt Phô Mai',NULL,NULL,1),(9,29000,'Coffe Sữa Đá',NULL,NULL,2),(10,29000,'Coffe Đen Đá',NULL,NULL,2),(11,29000,'Bạc Xỉu',NULL,NULL,2),(12,45000,'PhinDi Hạnh Nhân',NULL,NULL,5),(13,45000,'PhinDi Kem Sữa',NULL,NULL,5),(14,45000,'PhinDi Choco',NULL,NULL,5),(15,45000,'Trà Sen Vàng',NULL,NULL,3),(16,45000,'Trà Thạch Đào',NULL,NULL,3),(17,45000,'Trà Thanh Vải',NULL,NULL,3),(18,45000,'Trà Xanh Đậu Đỏ',NULL,NULL,3),(19,45000,'Trà Hoa Cúc',NULL,NULL,3),(20,55000,'Đá Xay Trà Xanh',NULL,NULL,4),(21,55000,'Đá Xay Caramel Phin',NULL,NULL,4),(22,55000,'Cookies & Cream',NULL,NULL,4),(23,55000,'Đá Xay Sô-cô-la',NULL,NULL,4);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `RoleID` int NOT NULL,
  `Creator` varchar(50) DEFAULT NULL,
  `RoleName` varchar(50) NOT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,'Admin'),(2,NULL,'Employee');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tablee`
--

DROP TABLE IF EXISTS `tablee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tablee` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TableNumber` varchar(255) NOT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `QuantityCustomer` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablee`
--

LOCK TABLES `tablee` WRITE;
/*!40000 ALTER TABLE `tablee` DISABLE KEYS */;
INSERT INTO `tablee` VALUES (1,'1','Full',2),(2,'2','Full',3),(3,'3','Available',4),(4,'4','Full',5),(5,'5','Available',10),(6,'6','Full',2),(7,'7','Available',3),(8,'8','Available',6);
/*!40000 ALTER TABLE `tablee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `DateWork` date DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `password` text NOT NULL,
  `FullName` varchar(50) DEFAULT NULL,
  `RoleID` int NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `image` text,
  PRIMARY KEY (`ID`),
  KEY `FK_Role` (`RoleID`),
  CONSTRAINT `FK_Role` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10,'trongan','094324332','Hồ Chí Minh','2021-07-12',NULL,'578822517693fd9ea381851d92b10886224a0aa5','Hoàng Trọng An',2,'','Verified','tronganfwork@gmail.com','Female','F:\\wordspace-estate-api\\CoffeeApplication\\src\\main\\java\\img\\portrait2.jpg'),(19,'an','08123123','Hà Nội','2020-01-16',NULL,'0358090c3c6527d222784821c5546cfc2734cc97','Phạm Thành An',2,'','Verified','ananh1973@gmail.com','Male',NULL),(26,'thanhan','0923712','Hà Nội','2020-03-24',NULL,'567541f86690e8419d974d4c1ddc411f0e5c10c1','Phạm Thành An',1,'','Verified','anthanhpham2403@gmail.com','Male',NULL),(34,'thanhhuyen','09231323','BN',NULL,'2024-03-29','0358090c3c6527d222784821c5546cfc2734cc97','Lê Thanh Huyền',2,'','Verified','1632004huyen@gmail.com','Female','F:\\wordspace-estate-api\\CoffeeApplication\\src\\main\\java\\img\\portrait2.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-29 22:08:55
