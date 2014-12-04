package logica;


import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private Integer id;
	private List<Producto> productos = new ArrayList<Producto>();

	public void añadirProducto(Producto producto) {
		productos.add(producto);
	}

	public void remove(Producto producto) {
		productos.remove(producto);
	}

	public List<Producto> getProductos() {
		return productos;
	}
	
	public List<Bulto> getBultos() {
		List<Bulto> bultos = new ArrayList<Bulto>();
		for(Producto p : productos)
		{
			List<Producto> bultoEspecifico = new ArrayList<Producto>();
			bultoEspecifico.add(p);
			for(Producto producto : productos)
			{
				if(p.getOrder_product_code().equals(producto.getOrder_product_code()))
				{
					bultoEspecifico.add(producto);
				}
			}
			bultos.add(new Bulto(bultoEspecifico));
		}
		return bultos;
	}

	public void setProductos(List<Producto> listaProductos) {
		this.productos = listaProductos;
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
		return "Nº pedido: " + id + " - Cantidad: "
				+ productos.size() + " - Peso: " + peso();
	}

	public void muestra() {
		System.out.println("El pedido " + getId()
				+ " tiene los siguientes productos: ");
		for (Object producto : getProductos())
			System.out.println("----->" + producto.toString());
	}
}
