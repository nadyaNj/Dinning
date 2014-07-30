USE cafeteria;

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
LOCK TABLES `category` WRITE;
INSERT INTO `cafeteria`.`category` VALUES  (1,'ճաշ','2011-12-16 17:54:44','2011-12-16 17:54:44'),
 (2,'գառնիր','2011-12-16 17:54:44','2011-12-16 17:54:44');
UNLOCK TABLES;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

--
-- Dumping data for table `cafeteria`.`mesurement`
--

/*!40000 ALTER TABLE `mesurement` DISABLE KEYS */;
LOCK TABLES `mesurement` WRITE;
INSERT INTO `cafeteria`.`mesurement` VALUES  (1,'կգ','2011-12-16 17:54:17','2011-12-16 17:54:19');
UNLOCK TABLES;
/*!40000 ALTER TABLE `mesurement` ENABLE KEYS */;
