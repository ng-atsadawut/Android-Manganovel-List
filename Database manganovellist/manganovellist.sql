-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.16-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table manganovellist.books
CREATE TABLE IF NOT EXISTS `books` (
  `bid` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `book_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(3) NOT NULL,
  `director` int(3) NOT NULL DEFAULT 0,
  `rate` int(3) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL,
  `path_img` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table manganovellist.books: ~5 rows (approximately)
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`bid`, `book_name`, `type`, `director`, `rate`, `status`, `path_img`) VALUES
	(001, 'One Piece', 6, 1, 2, 1, 'https://firebasestorage.googleapis.com/v0/b/manganovellist.appspot.com/o/one_piece.jpg?alt=media&token=d2ac2585-0e95-4fc8-9bca-4a16f8ba4077'),
	(002, 'NARUTO', 6, 1, 3, 2, 'https://firebasestorage.googleapis.com/v0/b/manganovellist.appspot.com/o/naruto.jpg?alt=media&token=21a2c913-cb8e-4b4a-b180-3111cdd6ce09'),
	(003, 'Sailor Moon', 5, 1, 4, 2, 'https://firebasestorage.googleapis.com/v0/b/manganovellist.appspot.com/o/sailor_moon.jpg?alt=media&token=cafa6b2d-3c5f-422f-a362-ec7abf03f9ff'),
	(004, 'Reborn', 1, 2, 1, 2, 'https://firebasestorage.googleapis.com/v0/b/manganovellist.appspot.com/o/reborn.jpg?alt=media&token=948a7712-de4d-4b40-ad7b-046b234d70a9'),
	(005, 'Attack on Titan', 1, 3, 4, 1, 'https://firebasestorage.googleapis.com/v0/b/manganovellist.appspot.com/o/titan.jpg?alt=media&token=3f487822-19b0-4737-a120-a4350b649c98');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table manganovellist.book_number
CREATE TABLE IF NOT EXISTS `book_number` (
  `nid` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `bid` int(3) unsigned zerofill NOT NULL,
  `book_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `book_no` int(3) NOT NULL DEFAULT 0,
  `price` int(3) NOT NULL,
  PRIMARY KEY (`nid`),
  KEY `bid` (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table manganovellist.book_number: ~48 rows (approximately)
/*!40000 ALTER TABLE `book_number` DISABLE KEYS */;
INSERT INTO `book_number` (`nid`, `bid`, `book_name`, `book_no`, `price`) VALUES
	(001, 001, 'One Piece', 1, 45),
	(002, 001, 'One Piece', 2, 45),
	(003, 001, 'One Piece', 3, 45),
	(004, 001, 'One Piece', 4, 45),
	(005, 001, 'One Piece', 5, 45),
	(006, 002, 'NARUTO', 1, 85),
	(007, 002, 'NARUTO', 3, 85),
	(008, 002, 'NARUTO', 4, 85),
	(009, 001, 'One Piece', 6, 45),
	(010, 001, 'One Piece', 7, 45),
	(011, 001, 'One Piece', 8, 45),
	(012, 001, 'One Piece', 9, 45),
	(013, 001, 'One Piece', 10, 45),
	(014, 001, 'One Piece', 11, 45),
	(015, 001, 'One Piece', 12, 45),
	(016, 001, 'One Piece', 13, 45),
	(017, 001, 'One Piece', 14, 45),
	(018, 001, 'One Piece', 15, 45),
	(019, 001, 'One Piece', 16, 45),
	(020, 001, 'One Piece', 17, 45),
	(021, 001, 'One Piece', 18, 45),
	(022, 001, 'One Piece', 19, 45),
	(023, 001, 'One Piece', 20, 45),
	(024, 001, 'One Piece', 21, 45),
	(025, 001, 'One Piece', 22, 45),
	(026, 001, 'One Piece', 23, 45),
	(027, 001, 'One Piece', 24, 45),
	(028, 001, 'One Piece', 25, 45),
	(029, 001, 'One Piece', 26, 45),
	(030, 001, 'One Piece', 27, 45),
	(031, 001, 'One Piece', 28, 45),
	(032, 001, 'One Piece', 29, 45),
	(033, 001, 'One Piece', 30, 45),
	(034, 004, 'Reborn', 1, 50),
	(035, 004, 'Reborn', 2, 50),
	(036, 004, 'Reborn', 3, 50),
	(037, 004, 'Reborn', 4, 50),
	(038, 004, 'Reborn', 5, 50),
	(039, 004, 'Reborn', 6, 50),
	(040, 004, 'Reborn', 7, 50),
	(041, 004, 'Reborn', 8, 50),
	(042, 004, 'Reborn', 9, 50),
	(043, 004, 'Reborn', 10, 50),
	(044, 004, 'Reborn', 11, 50),
	(045, 004, 'Reborn', 12, 50),
	(046, 004, 'Reborn', 13, 50),
	(047, 004, 'Reborn', 14, 50),
	(048, 004, 'Reborn', 15, 50),
	(049, 003, 'Sailor Moon', 1, 50),
	(050, 003, 'Sailor Moon', 2, 50),
	(051, 003, 'Sailor Moon', 3, 50),
	(052, 003, 'Sailor Moon', 4, 50),
	(053, 003, 'Sailor Moon', 5, 50),
	(054, 003, 'Sailor Moon', 6, 50),
	(055, 003, 'Sailor Moon', 7, 50),
	(056, 003, 'Sailor Moon', 8, 50),
	(057, 003, 'Sailor Moon', 9, 50),
	(058, 003, 'Sailor Moon', 10, 50),
	(059, 003, 'Sailor Moon', 11, 50),
	(060, 003, 'Sailor Moon', 12, 50),
	(061, 003, 'Sailor Moon', 13, 50),
	(062, 003, 'Sailor Moon', 14, 50),
	(063, 003, 'Sailor Moon', 15, 50),
	(064, 005, 'Attack on Titan', 1, 50),
	(065, 005, 'Attack on Titan', 2, 50),
	(066, 005, 'Attack on Titan', 3, 50),
	(067, 005, 'Attack on Titan', 4, 50),
	(068, 005, 'Attack on Titan', 5, 50),
	(069, 005, 'Attack on Titan', 6, 50),
	(070, 005, 'Attack on Titan', 7, 50),
	(071, 005, 'Attack on Titan', 8, 50),
	(072, 005, 'Attack on Titan', 9, 50),
	(073, 005, 'Attack on Titan', 10, 50),
	(074, 005, 'Attack on Titan', 11, 50),
	(075, 005, 'Attack on Titan', 12, 50),
	(076, 005, 'Attack on Titan', 13, 50),
	(077, 005, 'Attack on Titan', 14, 50),
	(078, 005, 'Attack on Titan', 15, 50);
/*!40000 ALTER TABLE `book_number` ENABLE KEYS */;

-- Dumping structure for table manganovellist.directors
CREATE TABLE IF NOT EXISTS `directors` (
  `director_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `director_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`director_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table manganovellist.directors: ~3 rows (approximately)
/*!40000 ALTER TABLE `directors` DISABLE KEYS */;
INSERT INTO `directors` (`director_id`, `director_name`) VALUES
	(1, 'Eiichiro Oda'),
	(2, 'Amano Akira'),
	(3, 'Tetsurō Araki');
/*!40000 ALTER TABLE `directors` ENABLE KEYS */;

-- Dumping structure for table manganovellist.keep
CREATE TABLE IF NOT EXISTS `keep` (
  `kid` int(3) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uid` varchar(28) COLLATE utf8_unicode_ci NOT NULL,
  `bid` int(3) unsigned zerofill NOT NULL,
  `nid` int(3) unsigned zerofill NOT NULL,
  PRIMARY KEY (`kid`),
  KEY `uid` (`uid`),
  KEY `bid` (`bid`),
  KEY `nid` (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table manganovellist.keep: ~14 rows (approximately)
/*!40000 ALTER TABLE `keep` DISABLE KEYS */;
INSERT INTO `keep` (`kid`, `uid`, `bid`, `nid`) VALUES
	(004, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 002, 006),
	(005, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 015),
	(006, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 003),
	(008, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 004),
	(009, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 001),
	(010, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 002),
	(011, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 005),
	(013, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 011),
	(015, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 014),
	(018, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 001, 013),
	(019, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 002, 008),
	(020, 'ZTqAyD0ZfrXwDwTRS5wk1WiTapu2', 002, 007);
/*!40000 ALTER TABLE `keep` ENABLE KEYS */;

-- Dumping structure for table manganovellist.rates
CREATE TABLE IF NOT EXISTS `rates` (
  `rate_id` int(11) NOT NULL AUTO_INCREMENT,
  `rate_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table manganovellist.rates: ~6 rows (approximately)
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` (`rate_id`, `rate_name`) VALUES
	(1, '12+'),
	(2, '14+'),
	(3, '15+'),
	(4, '16+'),
	(5, '18+'),
	(6, '20+');
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;

-- Dumping structure for table manganovellist.status
CREATE TABLE IF NOT EXISTS `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table manganovellist.status: ~2 rows (approximately)
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` (`status_id`, `status_name`) VALUES
	(1, 'on air'),
	(2, 'end');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;

-- Dumping structure for table manganovellist.types
CREATE TABLE IF NOT EXISTS `types` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table manganovellist.types: ~6 rows (approximately)
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` (`type_id`, `type_name`) VALUES
	(1, 'action'),
	(2, 'horror'),
	(3, 'commedy'),
	(4, 'romance'),
	(5, 'fantacy'),
	(6, 'adventure'),
	(7, 'erotic');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
