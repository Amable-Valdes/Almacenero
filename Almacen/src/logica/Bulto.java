package logica;

import java.util.List;

public class Bulto {

	private List<Producto> productos;
	private int cantidad;
	
	public Bulto(List<Producto> producto) {
		super();
		this.productos = producto;
		this.cantidad = productos.size();
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public int getCantidad() {
		return cantidad;
	}
	
}
