package logica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gestor {

	private Conexion c = new Conexion();
	private ArrayList<Producto> productos;

	private ArrayList<Subcategoria> subcategorias;
	private ArrayList<Integer> idSubcategorias;

	private ArrayList<Categoria> categorias;
	private ArrayList<Integer> idCategorias;

	private void cargarProductos() {
		c.crearConexion();
		try {
			PreparedStatement ps = c.getCon().prepareStatement(
					"SELECT * FROM `product`");
			ResultSet rs = ps.executeQuery();

			productos = new ArrayList<Producto>();
			idSubcategorias = new ArrayList<Integer>();

			while (rs.next()) {
				int idCategoria = rs.getInt("subcategory_id");
				if (!idSubcategorias.contains(idCategoria))
					idSubcategorias.add(idCategoria);
				Producto p = new Producto(rs.getString("product_name"),
						idCategoria, rs.getString("product_description"),
						rs.getInt("product_id"), rs.getDouble("precio"));
				productos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.cerraConexion();
	}

	private void cargarSubcategorias() {
		c.crearConexion();
		try {
			PreparedStatement ps = c.getCon().prepareStatement(
					"SELECT * FROM `subcategory`");
			ResultSet rs = ps.executeQuery();

			subcategorias = new ArrayList<Subcategoria>();
			idCategorias = new ArrayList<Integer>();

			while (rs.next()) {
				int idCategoria = rs.getInt("category_id");
				if (!idCategorias.contains(idCategoria))
					idCategorias.add(idCategoria);
				Subcategoria p = new Subcategoria(rs.getString("subcategory_name"),
						rs.getInt("subcategory_id"), idCategoria);
				subcategorias.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.cerraConexion();
	}

	private void cargarCategorias() {
		c.crearConexion();
		try {
			PreparedStatement ps = c.getCon().prepareStatement(
					"SELECT * FROM `category`");
			ResultSet rs = ps.executeQuery();
			categorias = new ArrayList<Categoria>();
			while (rs.next()) {
				Categoria c = new Categoria(rs.getString("category_name"),
						rs.getInt("category_id"));
				categorias.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.cerraConexion();
	}

	public void generaProductos() {
		cargarProductos();
	}

	public void generaSubcategorias() {
		cargarSubcategorias();
		for (Subcategoria c : subcategorias)
			if (idCategorias.contains(c.getId()))
				for (Producto p : productos)
					if (p.getCategoryId() == c.getId()){
						System.out.println(p.toString());
						c.add(p);
					}
		
	}

	public void generaCategorias() {
		cargarCategorias();
		for (Categoria c : categorias)
			if (idCategorias.contains(c.getId()))
				for (Subcategoria p : subcategorias)
					if (p.getIdCategoriaPadre() == c.getId())
						c.addSubcategoria(p);
	}

	public void muestraProductos() {
		System.out.println("Lista de productos");
		for (Producto p : productos)
			System.out.println(p.toString());
	}
	
	public ArrayList<Producto> getProductos() {
		generaProductos();
		return productos;
	}

	public ArrayList<Subcategoria> getSubcategorias() {
		generaSubcategorias();
		return subcategorias;
	}

	public ArrayList<Categoria> getCategorias() {
		generaCategorias();
		return categorias;
	}

}
