CREATE DATABASE  IF NOT EXISTS `bibliotecadigitale` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bibliotecadigitale`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotecadigitale
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Romantico');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immagine`
--

DROP TABLE IF EXISTS `immagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `immagine` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numero_pagina` smallint(6) NOT NULL,
  `statoI` enum('in caricamento','in acquisizione','in attesa supervisione','in caricamento acquisizione','in revisione acquisizione','acquisito','eliminata') NOT NULL,
  `url` varchar(150) NOT NULL,
  `ID_utente` int(10) unsigned DEFAULT NULL,
  `ID_opera` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `immagine_utente` (`ID_utente`),
  KEY `immagine_opera` (`ID_opera`),
  CONSTRAINT `immagine_opera` FOREIGN KEY (`ID_opera`) REFERENCES `opera` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `immagine_utente` FOREIGN KEY (`ID_utente`) REFERENCES `utente` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES (1,1,'acquisito','C:\\Users\\Sara\\Desktop\\Drive\\img\\Salterio_diurno_del_XVII_secolo.jpg',4,1),(2,1,'acquisito','C:\\Users\\Sara\\Desktop\\Drive\\img\\8b2f73e2-56da-4ef4-93f6-272e15ac8ff5.jpg',4,2),(3,2,'acquisito','C:\\Users\\Sara\\Desktop\\Drive\\img\\images.jpg',4,2),(4,3,'acquisito','C:\\Users\\Sara\\Desktop\\Drive\\img\\Salterio_diurno_del_XVII_secolo.jpg',4,2),(5,1,'acquisito','C:\\Users\\Sara\\Desktop\\Drive\\img\\Salterio_diurno_del_XVII_secolo.jpg',4,3),(6,2,'acquisito','C:\\Users\\Sara\\Desktop\\Drive\\img\\8b2f73e2-56da-4ef4-93f6-272e15ac8ff5.jpg',4,3),(7,3,'acquisito','C:\\Users\\Sara\\Desktop\\Drive\\img\\images.jpg',4,3);
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifica`
--

DROP TABLE IF EXISTS `notifica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifica` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orario` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `descrizione` varchar(450) DEFAULT NULL,
  `vista` tinyint(1) DEFAULT '0',
  `IDutentenot` int(10) unsigned NOT NULL,
  `IDruolonot` int(10) unsigned NOT NULL,
  `ID_utente` int(10) unsigned DEFAULT NULL,
  `IDimmagine` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `notifica_utente` (`ID_utente`),
  CONSTRAINT `notifica_utente` FOREIGN KEY (`ID_utente`) REFERENCES `utente` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifica`
--

LOCK TABLES `notifica` WRITE;
/*!40000 ALTER TABLE `notifica` DISABLE KEYS */;
INSERT INTO `notifica` VALUES (8,'2019-02-08 10:02:30','E\' stata assegnata una trascrizione!! Clicca qui o su  Trascrivi \" \"',1,6,0,8,1),(9,'2019-02-08 10:03:17','E\' stata assegnata una trascrizione!! Clicca qui o su  Trascrivi \" \"',1,9,0,8,3),(10,'2019-02-08 10:02:46','E\' stata assegnata una trascrizione!! Clicca qui o su  Trascrivi \" \"',1,6,0,8,3),(11,'2019-02-08 10:03:34','E\' stata assegnata una trascrizione!! Clicca qui o su  Trascrivi \" \"',1,9,0,8,7),(12,'2019-02-08 10:04:19','E\' stata caricata una trascrizione!! E\' richiesto il suo consenso!! Clicca qui o su  Consenti revisione \" \"',1,0,7,6,1),(13,'2019-02-08 10:04:35','E\' stata caricata una trascrizione!! E\' richiesto il suo consenso!! Clicca qui o su  Consenti revisione \" \"',1,0,7,6,3),(14,'2019-02-08 10:04:35','E\' stata caricata una trascrizione!! E\' richiesto il suo consenso!! Clicca qui o su  Consenti revisione \" \"',1,0,7,9,3),(15,'2019-02-08 10:04:25','E\' stata caricata una trascrizione!! E\' richiesto il suo consenso!! Clicca qui o su  Consenti revisione \" \"',1,0,7,9,7),(16,'2019-02-08 10:08:34','E\' stata caricata una trascrizione!! Clicca qui o su  Revisiona \" \"',1,0,6,8,1),(17,'2019-02-08 10:10:55','E\' stata caricata una trascrizione!! Clicca qui o su  Revisiona \" \"',1,0,6,8,3),(18,'2019-02-08 10:05:32','E\' stata assegnata una trascrizione!! Clicca qui o su  Trascrivi \" \"',1,9,0,8,7),(19,'2019-02-08 10:05:47','E\' stata caricata una trascrizione!! E\' richiesto il suo consenso!! Clicca qui o su  Consenti revisione \" \"',1,0,7,9,7),(20,'2019-02-08 10:10:41','E\' stata caricata una trascrizione!! Clicca qui o su  Revisiona \" \"',1,0,6,8,7),(21,'2019-02-08 10:25:22','E\' stata revisionata una trascrizione!! Clicca qui o su  Consenti pubblicazione \"\"',1,0,7,7,1),(22,'2019-02-08 10:11:39','E\' stata revisionata una trascrizione!! Clicca qui o su  Consenti pubblicazione \"\"',1,0,7,7,7),(23,'2019-02-08 10:25:40','E\' stata revisionata una trascrizione!! Clicca qui o su  Consenti pubblicazione \"\"',1,0,7,7,3),(28,'2019-02-08 10:15:17','E\' stata assegnata una trascrizione!! Clicca qui o su  Trascrivi \" \"',1,9,0,8,7),(29,'2019-02-08 10:15:34','E\' stata caricata una trascrizione!! E\' richiesto il suo consenso!! Clicca qui o su  Consenti revisione \" \"',1,0,7,9,7),(30,'2019-02-08 10:15:53','E\' stata caricata una trascrizione!! Clicca qui o su  Revisiona \" \"',1,0,6,8,7),(31,'2019-02-08 10:16:28','E\' stata revisionata una trascrizione!! Clicca qui o su  Consenti pubblicazione \"\"',1,0,7,7,7),(32,'2019-02-08 10:26:03','E\' stato dato il consenso per la pubblicazione!! Clicca qui o su  Pubblica \" \"',1,0,6,8,1),(33,'2019-02-08 10:26:19','E\' stato dato il consenso per la pubblicazione!! Clicca qui o su  Pubblica \" \"',1,0,6,8,3);
/*!40000 ALTER TABLE `notifica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera`
--

DROP TABLE IF EXISTS `opera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opera` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titolo` varchar(50) NOT NULL,
  `anno` varchar(10) DEFAULT NULL,
  `autore` varchar(50) NOT NULL,
  `pagine_totali` int(11) NOT NULL,
  `statoO` enum('in fase di acquisizione','pubblicata') NOT NULL,
  `ID_categoria` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `opera_unica` (`titolo`,`anno`,`autore`),
  KEY `opera_categoria` (`ID_categoria`),
  CONSTRAINT `opera_categoria` FOREIGN KEY (`ID_categoria`) REFERENCES `categoria` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera`
--

LOCK TABLES `opera` WRITE;
/*!40000 ALTER TABLE `opera` DISABLE KEYS */;
INSERT INTO `opera` VALUES (1,'Salterio Diurno','XVII','sconosciuto',1,'pubblicata',NULL),(2,'Ciao','200','Sara',3,'pubblicata',1),(3,'Ciao 1','300','Sara',3,'pubblicata',1);
/*!40000 ALTER TABLE `opera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS `ruolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruolo` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_ruolo` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruolo`
--

LOCK TABLES `ruolo` WRITE;
/*!40000 ALTER TABLE `ruolo` DISABLE KEYS */;
INSERT INTO `ruolo` VALUES (1,'Utente Base'),(2,'Utente Privilegiato'),(3,'Acquisitore'),(4,'Supervisore'),(5,'Trascrittore'),(6,'Revisore'),(7,'Manager'),(8,'Amministratore');
/*!40000 ALTER TABLE `ruolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testo_digitale`
--

DROP TABLE IF EXISTS `testo_digitale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testo_digitale` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `testo` varchar(50000) DEFAULT NULL,
  `statoT` enum('in trascrizione','in attesa revisione','in revisione','trascritto','pubblicato') DEFAULT NULL,
  `ID_utente` int(10) unsigned DEFAULT NULL,
  `ID_immagine` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `testo_digitale_utente` (`ID_utente`),
  KEY `testo_digitale_imamgine` (`ID_immagine`),
  CONSTRAINT `testo_digitale_imamgine` FOREIGN KEY (`ID_immagine`) REFERENCES `immagine` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testo_digitale_utente` FOREIGN KEY (`ID_utente`) REFERENCES `utente` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testo_digitale`
--

LOCK TABLES `testo_digitale` WRITE;
/*!40000 ALTER TABLE `testo_digitale` DISABLE KEYS */;
INSERT INTO `testo_digitale` VALUES (6,'<html dir=ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">Salterio Diurno pag 1</font></p></body></html>\"','pubblicato',7,1),(8,'<html dir=ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">Ciao pag 2</font></p><br></body></html>\"','pubblicato',7,3);
/*!40000 ALTER TABLE `testo_digitale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `indirizzo` varchar(100) DEFAULT NULL,
  `data_nascita` date NOT NULL,
  `titolo_studio` varchar(100) NOT NULL,
  `professione` varchar(100) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` char(20) NOT NULL,
  `attivo` tinyint(1) DEFAULT '0',
  `statodomanda` enum('in attesa','accettata','rifiutata') DEFAULT NULL,
  `livello` enum('1','2','3','4','5') DEFAULT NULL,
  `ID_ruolo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`),
  KEY `utente_ruolo` (`ID_ruolo`),
  CONSTRAINT `utente_ruolo` FOREIGN KEY (`ID_ruolo`) REFERENCES `ruolo` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Mario','Rossi','via dei gelsomini 9','1960-11-23','Informatico','amministratore','mario.rossi@gmail.com','mario',0,NULL,NULL,8),(2,'aa','bb','','2018-08-27','cc','','d','e',0,NULL,NULL,1),(3,'ff','gg','','2016-07-06','hh','','i','l',0,NULL,NULL,2),(4,'mm','nn','','2011-11-17','oo','','p','q',0,NULL,NULL,3),(5,'rr','ss','','2016-01-05','tt','','u','v',0,NULL,NULL,4),(6,'zz','ab','','2018-08-27','aabb','','zzab','ab',0,NULL,NULL,5),(7,'bc','cd','','2015-10-08','ccdd','','bccd','cd',0,NULL,NULL,6),(8,'Sara','Di Berardino','','1997-12-10','ragioniera','','sara.9712@hotmail.it','sara',0,NULL,NULL,7),(9,'ciao','ciao',NULL,'1997-12-10','ciao',NULL,'ciao','ciao',0,NULL,NULL,5);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bibliotecadigitale'
--

--
-- Dumping routines for database 'bibliotecadigitale'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-08 16:46:13
