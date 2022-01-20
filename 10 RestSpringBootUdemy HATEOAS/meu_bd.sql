-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.27 - MySQL Community Server - GPL
-- OS do Servidor:               Linux
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para rest_with_spring_boot_udemy_2
CREATE DATABASE IF NOT EXISTS `rest_with_spring_boot_udemy_2` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rest_with_spring_boot_udemy_2`;

-- Copiando estrutura para tabela rest_with_spring_boot_udemy_2.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `first_name` varchar(80) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela rest_with_spring_boot_udemy_2.person: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `address`, `first_name`, `gender`, `last_name`) VALUES
	(1, 'rua rio negro 226', 'yuri', 'male', 'resende'),
	(2, 'rua rio negro 226', 'Grabriela', 'female', 'Costa'),
	(8, 'rua rio negro 226', 'Fernanda', 'female', 'Cardoso Cavalcante'),
	(9, 'rua rio claro 3940', 'Julio', 'male', 'Antonino');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
