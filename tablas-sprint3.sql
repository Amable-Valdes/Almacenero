
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--estas son las tablas que he creado deacuerdo con el sprint 3, la base de
-- datos actual en el server es drnfrani_ips3vX

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE TABLE IF NOT EXISTS `categorias` (
  `id_categoria` int(11) unsigned NOT NULL,
  `categoria_nombre` varchar(255) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `subcategorias` (
  `id_subcategoria` int(11) unsigned NOT NULL,
  `id_categoria` int(11) unsigned NOT NULL DEFAULT '0',
  `subcategoria_nombre` varchar(255) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `pedidos` (
  `id_pedido` int(11) unsigned NOT NULL,
  `id_cliente` int(11) unsigned NOT NULL DEFAULT '0',
  `tipo_pago` varchar(255) NOT NULL DEFAULT 'tarjeta',
  `tipo_envio` varchar(255) NOT NULL DEFAULT 'normal',
  `estado` varchar(255) NOT NULL DEFAULT ' ',
  `direccion` varchar(255) NOT NULL DEFAULT ' ',
  `precio` int(10) NOT NULL DEFAULT '0'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tiposenvios` (
  `tipo_envio` varchar(255) NOT NULL DEFAULT ' ',
  `precio` int(11) unsigned NOT NULL DEFAULT '10'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `productos` (
  `id_producto` int(11) unsigned NOT NULL,
  `id_subcategoria` int(11) unsigned NOT NULL DEFAULT '0',
  `nombre` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `referencia` varchar(255) NOT NULL,
  `ancho` decimal(12,3) unsigned NOT NULL DEFAULT '0.000',
  `largo` decimal(12,3) NOT NULL DEFAULT '0.000',
  `altura` decimal(12,3) NOT NULL DEFAULT '0.000',
  `peso` decimal(12,3) NOT NULL DEFAULT '0.000',
  `posicion` int(11) unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `productosPedidos` (
  `id_pedido` int(11) unsigned NOT NULL DEFAULT '0',
  `id_producto` int(11) unsigned NOT NULL DEFAULT '0',
  `cantidad` int(11) unsigned NOT NULL DEFAULT '0',
  `fecha` varchar(255) NOT NULL DEFAULT 'fecha',
  `estado` varchar(255) NOT NULL DEFAULT 'CREADO'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` int(11) unsigned NOT NULL DEFAULT '0',
  `usuario` varchar(255) NOT NULL DEFAULT ' ',
  `contraseña` varchar(255) NOT NULL DEFAULT ' ',
  `direccion` varchar(255) NOT NULL DEFAULT ' '
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `minoristas` (
  `id_cliente` int(11) unsigned NOT NULL DEFAULT '0',
  `empresa` varchar(255) NOT NULL DEFAULT ' '
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `particulares` (
  `id_cliente` int(11) unsigned NOT NULL DEFAULT '0',
  `dni` varchar(255) NOT NULL DEFAULT ' ',
  `nombre` varchar(255) NOT NULL DEFAULT ' ',
  `apellidos` varchar(255) NOT NULL DEFAULT ' '
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `facturas` (
  `id_factura` int(11) unsigned NOT NULL DEFAULT '0',
  `id_cliente` int(11) unsigned NOT NULL DEFAULT '0',
  `id_pedido` int(11) unsigned NOT NULL DEFAULT '0',
  `fecha` varchar(255) NOT NULL DEFAULT 'fecha'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `albaranes` (
  `id_albaran` int(11) unsigned NOT NULL DEFAULT '0',
  `id_pedido` int(11) unsigned NOT NULL DEFAULT '0',
  `fecha` varchar(255) NOT NULL DEFAULT 'fecha',
  `transportista` varchar(255) NOT NULL DEFAULT 'habitual',
  `estado` varchar(255) NOT NULL DEFAULT 'generado'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tipospagos` (
  `tipo_pago` varchar(255) NOT NULL DEFAULT ' ',
  `precio` int(11) unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- asignación de claves

ALTER TABLE `categorias`
 ADD PRIMARY KEY (`id_categoria`), ADD UNIQUE KEY `categoria_nombre` (`categoria_nombre`);
 
ALTER TABLE `subcategorias`
 ADD PRIMARY KEY (`id_subcategoria`), ADD UNIQUE KEY `subcategoria_nombre` (`subcategoria_nombre`), ADD KEY `id_categoria` (`id_categoria`);

ALTER TABLE `productosPedidos`
 ADD PRIMARY KEY (`id_pedido`, `id_producto`), ADD KEY `id_pedido` (`id_pedido`), ADD KEY `id_producto` (`id_producto`);
 
ALTER TABLE `clientes`
 ADD PRIMARY KEY (`id_cliente`), ADD UNIQUE KEY `usuario` (`usuario`);
 
ALTER TABLE `particulares`
 ADD PRIMARY KEY (`id_cliente`), ADD KEY `id_cliente` (`id_cliente`), ADD UNIQUE KEY `dni` (`dni`); 

ALTER TABLE `minoristas`
 ADD PRIMARY KEY (`id_cliente`), ADD KEY `id_cliente` (`id_cliente`), ADD UNIQUE KEY `empresa` (`empresa`); 
 
ALTER TABLE `pedidos`
 ADD PRIMARY KEY (`id_pedido`, `id_cliente`), ADD KEY `tipo_pago` (`tipo_pago`), ADD KEY `id_cliente` (`id_cliente`), ADD KEY `tipo_envio` (`tipo_envio`);

ALTER TABLE `tiposenvios`
 ADD PRIMARY KEY (`tipo_envio`);
 
ALTER TABLE `productos`
 ADD PRIMARY KEY (`id_producto`), ADD UNIQUE KEY `referencia` (`referencia`), ADD KEY `id_subcategoria` (`id_subcategoria`);

ALTER TABLE `facturas`
 ADD PRIMARY KEY (`id_factura`, `id_cliente`, `id_pedido`), ADD KEY `id_cliente` (`id_cliente`), ADD KEY `id_pedido` (`id_pedido`);
 
ALTER TABLE `albaranes`
 ADD PRIMARY KEY (`id_albaran`, `id_pedido`), ADD KEY `id_pedido` (`id_pedido`);

 ALTER TABLE `tipospagos`
 ADD PRIMARY KEY (`tipo_pago`);
 
 -- autoincrementos
 
ALTER TABLE `categorias`
 MODIFY `id_categoria` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

ALTER TABLE `subcategorias`
 MODIFY `id_subcategoria` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

ALTER TABLE `pedidos`
 MODIFY `id_pedido` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1; 

ALTER TABLE `productos`
 MODIFY `id_producto` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1; 
 
ALTER TABLE `facturas`
 MODIFY `id_factura` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1; 

ALTER TABLE `albaranes`
 MODIFY `id_albaran` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;  


-- disparadores

 
 -- datos

INSERT INTO `categorias` (`id_categoria`, `categoria_nombre`) VALUES
(1, 'electronica'),
(2, 'lectores'),
(3, 'vigilancia'),
(4, 'Discos duros');

 INSERT INTO `subcategorias` (`id_subcategoria`, `id_categoria`, `subcategoria_nombre`) VALUES
(1, 1, 'diodos'),
(2, 1, 'rele'),
(3, 1, 'bombilla'),
(4, 2, 'camaras'),
(5, 2, 'microfonos');
 
 INSERT INTO `productos` (`id_producto`, `id_subcategoria`, `nombre`, `descripcion`, `precio`, `codigo`, `ancho`, `largo`, `altura`, `peso`, `posicion`) VALUES
 (1, 1, 'Diodo estandar', 'estandar', 2.0, '3424242', 1.0, 1.0, 1.0, 1.0, 123),
 (2, 1, 'Diodo Zener', 'zener', 2.5, '3424243', 1.0, 1.0, 1.0, 1.0, 124),
 (3, 3, 'Bombila Samsung 45W', '45W', 2.5, '3424244', 2.0, 1.0, 2.0, 1.0, 324),
 (4, 3, 'Bombila Samsung 60W', '60W', 3.5, '3424245', 2.0, 1.0, 2.0, 1.0, 325),
 (5, 3, 'Bombila Samsung 75W', '75W', 2.5, '3424246', 2.0, 1.0, 2.0, 1.0, 326),
 (6, 3, 'Bombila Samsung 50W', '50W', 3.5, '3424247', 2.0, 1.0, 2.0, 1.0, 325),
 (7, 3, 'Bombila Philips 60W', '60W', 5, '3424248', 2.0, 1.0, 2.0, 1.0, 327),
 (8, 3, 'Bombila Philips 100W', '100W', 15, '3424249', 2.0, 1.0, 2.0, 1.0, 328),
 (9, 3, 'Bombila Eco 60W', '60W super ahorro ecológico', 15, '3424250', 4.0, 1.0, 2.0, 1.0, 329);
 
 INSERT INTO `clientes` (`id_cliente`, `usuario`,`contraseña`, `direccion`) VALUES
 (1, 'diodos', '123456', 'C/ Buena vista n43 1b'),
 (2, 'federico', '123456', 'C/ Buena vista n41 1b'),
 (3, 'paco', '123456', 'C/ Buena vista n40 9b'),
 (4, 'bombillas', '123456', 'C/ Buena ventura n44 bajo c');
 
 INSERT INTO `minoristas` (`id_cliente`, `empresa`) VALUES
 (1, 'Diodos SA'),
 (4, 'Bombillas SA');

 INSERT INTO `particulares` (`id_cliente`, `dni`, `nombre`, `apellidos`) VALUES
 (2, '65231253v', 'Federico', 'Snape Suarez'),
 (3, '56842531c', 'Paco', 'Garcia Malavista');

 INSERT INTO `tipospagos` (`tipo_pago`, `precio`) VALUES
  ('tarjeta', 0),
  ('paypal', 0),
  ('transferencia', 2),
  ('contrareembolso', 3);

 INSERT INTO `tiposenvios` (`tipo_envio`, `precio`) VALUES
  ('normal', 5),
  ('urgente', 10);

 
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
