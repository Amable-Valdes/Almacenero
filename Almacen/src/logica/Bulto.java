package logica;

public class Bulto {

	private Producto producto;
	private int cantidad;
	
	public Bulto(Producto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public int getCantidad() {
		return cantidad;
	}
	
}
