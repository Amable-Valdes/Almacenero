-- phpMyAdmin SQL Dump
-- version 4.2.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 13, 2014 at 03:08 AM
-- Server version: 5.5.40-cll
-- PHP Version: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `drnfrani_ips2`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
`category_id` int(255) unsigned NOT NULL,
  `category_name` varchar(255) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`) VALUES
(1, 'electronica'),
(2, 'lectores'),
(3, 'vigilancia'),
(4, 'Discos duros');

-- --------------------------------------------------------

--
-- Table structure for table `order_product`
--

CREATE TABLE IF NOT EXISTS `order_product` (
`order_product_id` int(10) unsigned NOT NULL,
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `product_id` int(10) unsigned NOT NULL DEFAULT '0',
  `order_product_quantity` int(10) unsigned NOT NULL DEFAULT '1'
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_product`
--

INSERT INTO `order_product` (`order_product_id`, `order_id`, `product_id`, `order_product_quantity`) VALUES
(1, 1, 1, 4),
(2, 2, 1, 8),
(3, 3, 1, 11),
(4, 4, 1, 6),
(5, 5, 1, 77),
(6, 6, 1, 7),
(7, 7, 1, 6),
(8, 8, 1, 16),
(9, 9, 1, 9),
(10, 10, 1, 15),
(11, 11, 2, 8),
(12, 11, 1, 16),
(13, 12, 2, 7),
(14, 12, 1, 9),
(15, 12, 3, 7),
(16, 13, 2, 11),
(17, 14, 2, 10),
(18, 14, 1, 8),
(19, 15, 2, 5),
(20, 16, 2, 7),
(21, 17, 2, 6),
(22, 17, 1, 11),
(23, 18, 2, 11),
(24, 18, 1, 10);

--
-- Triggers `order_product`
--
DELIMITER //
CREATE TRIGGER `pedidos_insert` BEFORE INSERT ON `order_product`
 FOR EACH ROW insert into pedidos(order_product_id, order_id, product_id, cantidad,fecha) values (new.order_product_id, new.order_id, new.product_id, new.order_product_quantity, now())
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pedidos`
--

CREATE TABLE IF NOT EXISTS `pedidos` (
`order_product_id` int(10) unsigned NOT NULL,
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `product_id` int(10) unsigned NOT NULL DEFAULT '0',
  `cantidad` int(10) unsigned NOT NULL DEFAULT '1',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `estado` varchar(10) NOT NULL DEFAULT 'CREADO',
  `estado_producto` int(11) NOT NULL DEFAULT '0',
  `estado_pedido` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pedidos`
--

INSERT INTO `pedidos` (`order_product_id`, `order_id`, `product_id`, `cantidad`, `fecha`, `estado`, `estado_producto`, `estado_pedido`) VALUES
(119, 5, 1, 77, '2014-11-06 19:37:58', 'CREADO', 0, 0),
(118, 4, 1, 6, '2014-11-06 19:35:25', 'CREADO', 0, 0),
(117, 3, 1, 11, '2014-11-06 19:34:33', 'CREADO', 0, 0),
(120, 6, 1, 7, '2014-11-06 19:40:17', 'CREADO', 0, 0),
(121, 7, 1, 6, '2014-11-06 19:41:22', 'CREADO', 0, 0),
(122, 8, 1, 16, '2014-11-06 19:42:28', 'CREADO', 0, 0),
(123, 9, 1, 9, '2014-11-06 19:45:06', 'CREADO', 0, 0),
(124, 10, 1, 15, '2014-11-06 19:46:31', 'CREADO', 0, 0),
(125, 11, 2, 8, '2014-11-06 21:38:13', 'CREADO', 0, 0),
(126, 11, 1, 16, '2014-11-06 21:38:13', 'CREADO', 0, 0),
(127, 12, 2, 7, '2014-11-06 22:10:34', 'CREADO', 0, 0),
(128, 12, 1, 9, '2014-11-06 22:10:34', 'CREADO', 0, 0),
(129, 12, 3, 7, '2014-11-06 22:10:35', 'CREADO', 0, 0),
(130, 13, 2, 11, '2014-11-06 22:14:18', 'CREADO', 0, 0),
(131, 14, 2, 10, '2014-11-06 22:29:43', 'CREADO', 0, 0),
(132, 14, 1, 8, '2014-11-06 22:29:43', 'CREADO', 0, 0),
(133, 15, 2, 5, '2014-11-08 00:29:08', 'CREADO', 0, 0),
(134, 16, 2, 7, '2014-11-10 12:01:10', 'CREADO', 0, 0),
(135, 17, 2, 6, '2014-11-12 08:00:52', 'CREADO', 0, 0),
(136, 17, 1, 11, '2014-11-12 08:00:52', 'CREADO', 0, 0),
(137, 18, 2, 11, '2014-11-13 08:02:43', 'CREADO', 0, 0),
(138, 18, 1, 10, '2014-11-13 08:02:43', 'CREADO', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
`product_id` int(11) unsigned NOT NULL,
  `subcategory_id` int(11) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_description` text NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `product_quantity` int(11) NOT NULL DEFAULT '-1',
  `product_code` varchar(255) NOT NULL,
  `product_weight` decimal(12,3) unsigned NOT NULL DEFAULT '0.000',
  `product_width` decimal(12,3) NOT NULL DEFAULT '0.000',
  `product_length` decimal(12,3) NOT NULL DEFAULT '0.000',
  `product_height` decimal(12,3) NOT NULL DEFAULT '0.000',
  `product_posicion` int(10) unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `subcategory_id`, `product_name`, `product_description`, `precio`, `product_quantity`, `product_code`, `product_weight`, `product_width`, `product_length`, `product_height`, `product_posicion`) VALUES
(1, 2, 'Bombilla Philips', 'Bombilla de 70W de rosca simple', '5', -1, '61556168', '10.000', '10.000', '10.000', '10.000', 111),
(2, 1, 'Diodo Zener', 'Diodo Zener estándar ', '2', -1, '61556145', '2.000', '1.000', '1.000', '1.000', 135),
(3, 4, 'Samsung 840 EVO ', 'Características de Samsung 840 Evo SSD Series 120GB SATA3\r\n\r\nTe presentamos el Samsung Evo Series, un disco duro en estado sólido de 120 GB de capacidad.\r\nCaracterísticas:\r\nExperimente una nueva marca, un rendimiento más rápido\r\nFiabilidad sin precedentes para un alto rendimiento continuo\r\nherramientas fáciles de usar y simple para una actualización cómoda\r\nSolución integrada con componentes de alta calidad\r\nSiempre sea por la vía rápida\r\nEspecificaciones:\r\nDisco duro\r\nDisco de estado sólido, capacidad* 120 GB\r\nInterno* Yes\r\nInterfaces de disco de estado sólido* Serial ATA III\r\nTamaño* 64 mm (2.5 ")\r\nUnidad, tamaño de búfer 256 MB\r\nTransmisión de datos\r\nVelocidad de lectura 540 MB/s\r\nVelocidad de escritura 410 MB/s\r\nEscritura aleatoria (4KB) 35000 IOPS\r\nLectura aleatoria (4KB) 94000 IOPS\r\nVelocidad de transferencia de datos 6 Gbit/s\r\nSistema operativo/software\r\nSistema operativo Windows soportado Yes\r\nPeso y dimensiones\r\nAncho 69.85 mm\r\nProfundidad 100 mm\r\nAltura 7 mm\r\nPeso 68 g\r\nDetalles técnicos\r\nColor del producto Titánio\r\nSoporte S.M.A.R.T. Yes\r\nSoporte TRIM Yes', '65', -1, '486486465', '10.000', '20.000', '20.000', '20.000', 262);

-- --------------------------------------------------------

--
-- Table structure for table `subcategory`
--

CREATE TABLE IF NOT EXISTS `subcategory` (
`subcategory_id` int(255) unsigned NOT NULL,
  `subcategory_name` varchar(255) NOT NULL,
  `category_id` int(255) unsigned NOT NULL DEFAULT '1'
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subcategory`
--

INSERT INTO `subcategory` (`subcategory_id`, `subcategory_name`, `category_id`) VALUES
(1, 'diodos', 1),
(2, 'bombillas', 1),
(3, 'HDD', 4),
(4, 'SSD', 4),
(5, 'Cámaras', 3),
(6, 'Microfonos', 3),
(7, 'DVD', 2),
(8, 'Blueray', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
 ADD PRIMARY KEY (`category_id`), ADD UNIQUE KEY `category_name` (`category_name`);

--
-- Indexes for table `order_product`
--
ALTER TABLE `order_product`
 ADD PRIMARY KEY (`order_product_id`);

--
-- Indexes for table `pedidos`
--
ALTER TABLE `pedidos`
 ADD PRIMARY KEY (`order_product_id`), ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
 ADD PRIMARY KEY (`product_id`), ADD UNIQUE KEY `product_code` (`product_code`), ADD KEY `subcategory_id` (`subcategory_id`);

--
-- Indexes for table `subcategory`
--
ALTER TABLE `subcategory`
 ADD PRIMARY KEY (`subcategory_id`), ADD UNIQUE KEY `subcategory_name` (`subcategory_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
MODIFY `category_id` int(255) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `order_product`
--
ALTER TABLE `order_product`
MODIFY `order_product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `pedidos`
--
ALTER TABLE `pedidos`
MODIFY `order_product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=139;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
MODIFY `product_id` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `subcategory`
--
ALTER TABLE `subcategory`
MODIFY `subcategory_id` int(255) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;




/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
