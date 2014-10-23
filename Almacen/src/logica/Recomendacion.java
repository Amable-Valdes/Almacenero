package logica;

import java.util.ArrayList;

public class Recomendacion {
	private Integer id;
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private Integer idRecomendacion;

	public void añadirProducto(Producto producto) {
		productos.add(producto);
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdRecomendacion() {
		return idRecomendacion;
	}

	public void setIdRecomendacion(Integer idRecomendacion) {
		this.idRecomendacion = idRecomendacion;
	}

}

