-- phpMyAdmin SQL Dump
-- version 4.1.8
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 27-10-2014 a las 14:38:38
-- Versión del servidor: 5.5.37-cll
-- Versión de PHP: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `drnfrani_ips2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`category_id`, `category_name`) VALUES
(1, 'electronica'),
(2, 'lectores'),
(3, 'vigilancia'),
(4, 'Discos duros');

--
-- Estructura de tabla para la tabla `subcategory`
--

CREATE TABLE IF NOT EXISTS `subcategory` (
  `subcategory_id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `subcategory_name` varchar(255) NOT NULL,
  `category_id` int(255) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`subcategory_id`),
  UNIQUE KEY `subcategory_name` (`subcategory_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `subcategory`
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order_product`
--

CREATE TABLE IF NOT EXISTS `order_product` (
  `order_product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `product_id` int(10) unsigned NOT NULL DEFAULT '0',
  `order_product_quantity` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`order_product_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



--
-- Disparadores `order_product`
-- inserta una copia añadiendo la fecha actual a los pedidos
--
DROP TRIGGER IF EXISTS `pedidos_insert`;
DELIMITER //
CREATE TRIGGER `pedidos_insert` BEFORE INSERT ON `order_product`
 FOR EACH ROW insert into pedidos(order_product_id, order_id, product_id, cantidad,fecha) values (new.order_product_id, new.order_id, new.product_id, new.order_product_quantity, now())
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE IF NOT EXISTS `pedidos` (
  `order_product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `product_id` int(10) unsigned NOT NULL DEFAULT '0',
  `cantidad` int(10) unsigned NOT NULL DEFAULT '1',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `estado` varchar(10) NOT NULL DEFAULT 'CREADO',
  `estado_producto` int(11) NOT NULL DEFAULT '0',
  `estado_pedido` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_product_id`),
  KEY `order_id` (`order_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`order_product_id`, `order_id`, `product_id`, `cantidad`, `fecha`, `estado`, `estado_producto`, `estado_pedido`) VALUES
(115, 1000, 2, 1, '2014-10-27 18:35:00', 'CREADO', 0, 0),
(114, 1000, 1, 1, '2014-10-27 18:35:00', 'CREADO', 0, 0),
(113, 0, 1, 5, '2014-10-27 18:34:11', 'CREADO', 0, 0),
(112, 0, 1, 2, '2014-10-27 18:29:53', 'CREADO', 0, 0),
(111, 0, 3, 3, '2014-10-27 18:29:52', 'CREADO', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
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
  `product_posicion` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_code` (`product_code`),
  KEY `subcategory_id` (`category_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`product_id`, `category_id`, `product_name`, `product_description`, `precio`, `product_quantity`, `product_code`, `product_weight`, `product_width`, `product_length`, `product_height`, `product_posicion`) VALUES
(1, 1, 'Rele', '', '10', -1, '1213532', '10.000', '10.000', '10.000', '10.000', 153),
(2, 1, 'Diodo', 'Diodo Zener ', '2', -1, '1233582', '2.000', '2.000', '2.000', '2.000', 136),
(3, 2, 'Lector liteon', 'lector dvd de marca liteon', '20', -1, '6515616812', '15.000', '5.000', '10.000', '20.000', 536);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
