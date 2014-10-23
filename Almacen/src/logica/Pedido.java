package logica;


import java.util.ArrayList;

public class Pedido {

	private Integer id;
	private ArrayList<Producto> productos = new ArrayList<Producto>();

	public void añadirProducto(Producto producto) {
		productos.add(producto);
	}

	public void remove(Producto producto) {
		productos.remove(producto);
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double peso() {
		double peso = 0;
		for (int i = 0; i < productos.size(); i++) {
			peso = peso + productos.get(i).getPeso();
		}
		return peso;
	}

	@Override
	public String toString() {
		return "Nº pedido: " + id + " - Nº productos: "
				+ productos.size() + " - Peso: " + peso();
	}

	public void muestra() {
		System.out.println("El pedido " + getId()
				+ " tiene los siguientes productos: ");
		for (Object producto : getProductos())
			System.out.println("----->" + producto.toString());
	}
}
