package logica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import logica.basesDatos.Conexion;

public class Gestor {

	private Conexion c1 = new Conexion();
	private Conexion c2 = new Conexion(); // conexion anidada
	private Conexion c3 = new Conexion(); // conexion anidada
	private ArrayList<Producto> productos;
	private ArrayList<Integer> idPedidos;
	private ArrayList<Pedido> pedidos;

	public void productoRecogido(int idProducto)
	{
		c1.crearConexion();
		String query = "update `product` set ??????????????????????? = ? WHERE `product_id`=?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = c1.getCon().prepareStatement(query);
			preparedStmt.setString(idProducto, "RECOGIDO");
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	
	public void productoEmpaquetado(int idProducto)
	{
		c1.crearConexion();
		String query = "update `product` set ??????????????????????? = ? WHERE `product_id`=?";
	    PreparedStatement preparedStmt;
		try {
			preparedStmt = c1.getCon().prepareStatement(query);
			preparedStmt.setString(idProducto, "EMPAQUETAR");
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * M�todo que lee de la base de datos
	 */
	private void cargarOrdenes() {
		c1.crearConexion();
		c2.crearConexion();
		c3.crearConexion();
		try {
			PreparedStatement ps = c1.getCon().prepareStatement(
					"SELECT * FROM pedidos");
			ResultSet rs = ps.executeQuery();
			idPedidos = new ArrayList<Integer>();
			productos = new ArrayList<Producto>();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("order_id"));
				if (!idPedidos.contains(id))
					idPedidos.add(id);
				int cantidad = Integer.parseInt(rs.getString("cantidad"));
				int idProducto = Integer.parseInt(rs.getString("product_id"));
				Context c = recogerCaracteristicas(idProducto);
				
				String patron = "dd/MM/yyyy";
			    SimpleDateFormat formato = new SimpleDateFormat(patron);
			    // formateo
			    String fecha =rs.getString("fecha");
				
//				String estadoPedido =rs.getString("estado_pedido");
				
				for (int i = 0; i < cantidad; i++) {
					// crear el objeto
					Producto p = new Producto(id,c.nombre,
							cantidad, c.posicion,fecha,
							Producto.POR_RECOGER);
					p.setCantidadTotalEnPedido(cantidad);
					productos.add(p);
					//System.out.println(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c3.cerraConexion();
		c2.cerraConexion();
		c1.cerraConexion();
	}

	// clase auxiliar para evitar 3 llamadas a la bd
	private class Context {
		String nombre;
		String codigo;
		String posicion;
		double peso;
		double largo;
		double ancho;
		double alto;
		
	}

	private Context recogerCaracteristicas(int idProducto) {
		Context c = new Context();
		try {
			PreparedStatement ps = c2
					.getCon()
					.prepareStatement(
							"SELECT * FROM `product` WHERE `product_id`=?");
			ps.setInt(1, idProducto);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.nombre = rs.getString("product_name");
				c.codigo = rs.getString("PRODUCT_CODE");
				c.posicion = rs.getString("PRODUCT_POSICION");
				c.peso = rs.getInt("product_weight");
				c.alto = rs.getInt("product_length");
				c.ancho = rs.getInt("product_width");
				c.largo = rs.getInt("product_height");
				
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return c;
	}

	/**
	 * Genera los pedidos a partir de todas los productos este es uno de los
	 * posibles m�todos de los que podr� leer la GUI
	 */
	public void generaPedidos() {
		cargarOrdenes();
		pedidos = new ArrayList<Pedido>(idPedidos.size());
		for (Integer idPedido : idPedidos) {
			Pedido p = new Pedido();
			p.setId(idPedido);
			for (Producto producto : productos) {
				if (producto.getOrder_id() == idPedido)
					p.a�adirProducto(producto);
			}
			pedidos.add(p);
		}
	}

	/*
	 * M�todo interno para comprobar pedidos
	 */
	public void compruebaPedidos() {
		for (Pedido pedido : pedidos) {
			pedido.muestra();
		}
	}

	/**
	 * Getters
	 */
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
