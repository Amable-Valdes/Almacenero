package logica;

import java.util.ArrayList;

public class Subcategoria {

	private int id;
	private String nombre;
	private ArrayList<Producto> productos;
	private int idCategoriaPadre;

	public Subcategoria(String nombre, int id, int idPadre) {
		this.nombre = nombre;
		this.id = id;
		productos = new ArrayList<Producto>();
		setIdCategoriaPadre(idPadre);
	}

	public void add(Producto p) {
		productos.add(p);
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "------->id: " + id + "----nombre: " + nombre+ "-----numero de productos: "+productos.size()+"\n"+productos.toString();
	}

	public int getIdCategoriaPadre() {
		return idCategoriaPadre;
	}

	public void setIdCategoriaPadre(int idCategoriaPadre) {
		this.idCategoriaPadre = idCategoriaPadre;
	}
}
