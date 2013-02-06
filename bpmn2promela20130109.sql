# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: bpmn2promela
# ------------------------------------------------------
# Server version 5.1.36-community

DROP DATABASE IF EXISTS `bpmn2promela`;
CREATE DATABASE `bpmn2promela` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bpmn2promela`;

#
# Table structure for table b2p_users
#

CREATE TABLE `b2p_users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '',
  `user_type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `b2p_users` VALUES (1,'admin','admin',0);
INSERT INTO `b2p_users` VALUES (2,'carlos','carlos',1);
INSERT INTO `b2p_users` VALUES (3,'ellis','ellis',1);
/*!40000 ALTER TABLE `b2p_users` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table bpmn_choreography
#

CREATE TABLE `bpmn_choreography` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `file_path` varchar(255) NOT NULL DEFAULT '',
  `image_path` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information of choreographies';
/*!40000 ALTER TABLE `bpmn_choreography` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table bpmn_choreography_message_info
#

CREATE TABLE `bpmn_choreography_message_info` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `choreography_id` int(11) NOT NULL DEFAULT '0',
  `message` varchar(64) NOT NULL DEFAULT '',
  `ltl_symbol` varchar(64) NOT NULL DEFAULT '',
  `bool_message` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40000 ALTER TABLE `bpmn_choreography_message_info` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table ltl_formula_definition
#

CREATE TABLE `ltl_formula_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) NOT NULL DEFAULT '',
  `formula` varchar(256) NOT NULL DEFAULT '',
  `nickname` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40000 ALTER TABLE `ltl_formula_definition` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table ltl_formula_instance
#

CREATE TABLE `ltl_formula_instance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `choreography_id` int(11) NOT NULL DEFAULT '0',
  `definition_id` int(11) NOT NULL DEFAULT '0',
  `specific_description` varchar(255) DEFAULT '',
  `specific_formula` varchar(255) DEFAULT '',
  `is_setup` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40000 ALTER TABLE `ltl_formula_instance` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
