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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
-- Table structure for table `pedidos`
--

CREATE TABLE IF NOT EXISTS `pedidos` (
`order_product_id` int(10) unsigned NOT NULL,
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `product_id` int(10) unsigned NOT NULL DEFAULT '0',
  `cantidad` int(10) unsigned NOT NULL DEFAULT '1',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `estado` varchar(10) NOT NULL DEFAULT 'CREADO'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- No codigo de pruebas se genera con la aplicacion del cliente

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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `product` (`product_id`, `subcategory_id`, `product_name`, `product_description`, `precio`, `product_quantity`, `product_code`, `product_weight`, `product_width`, `product_length`, `product_height`, `product_posicion`) VALUES
(1, 2, 'Bombilla Philips', 'Bombilla de 70W de rosca simple', '5', -1, '61556168', '10.000', '10.000', '10.000', '10.000', 111),
(2, 1, 'Diodo Zener', 'Diodo Zener estándar ', '2', -1, '61556145', '2.000', '1.000', '1.000', '1.000', 135),
(3, 4, 'Samsung 840 EVO ', 'Características de Samsung 840 Evo SSD Series 120GB SATA3\r\n\r\nTe presentamos el Samsung Evo Series, un disco duro en estado sólido de 120 GB de capacidad.\r\nCaracterísticas:\r\nExperimente una nueva marca, un rendimiento más rápido\r\nFiabilidad sin precedentes para un alto rendimiento continuo\r\nherramientas fáciles de usar y simple para una actualización cómoda\r\nSolución integrada con componentes de alta calidad\r\nSiempre sea por la vía rápida\r\nEspecificaciones:\r\nDisco duro\r\nDisco de estado sólido, capacidad* 120 GB\r\nInterno* Yes\r\nInterfaces de disco de estado sólido* Serial ATA III\r\nTamaño* 64 mm (2.5 ")\r\nUnidad, tamaño de búfer 256 MB\r\nTransmisión de datos\r\nVelocidad de lectura 540 MB/s\r\nVelocidad de escritura 410 MB/s\r\nEscritura aleatoria (4KB) 35000 IOPS\r\nLectura aleatoria (4KB) 94000 IOPS\r\nVelocidad de transferencia de datos 6 Gbit/s\r\nSistema operativo/software\r\nSistema operativo Windows soportado Yes\r\nPeso y dimensiones\r\nAncho 69.85 mm\r\nProfundidad 100 mm\r\nAltura 7 mm\r\nPeso 68 g\r\nDetalles técnicos\r\nColor del producto Titánio\r\nSoporte S.M.A.R.T. Yes\r\nSoporte TRIM Yes', '65', -1, '486486465', '10.000', '20.000', '20.000', '20.000', 262);

-- --------------------------------------------------------

-- asignación de claves

--
-- Indexes for table `category`
--
ALTER TABLE `category`
 ADD PRIMARY KEY (`category_id`), ADD UNIQUE KEY `category_name` (`category_name`);

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


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
