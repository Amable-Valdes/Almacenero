package logica;

public class Producto {
	private int categoryId;
	private int product_id;
	private String product_name;
	private String product_description;
	private double precio;
	private int cantidad;
	
	
	public Producto(String product_name, int categoryId, String descripcion, int product_id,
			double precio) {
		this.product_name = product_name;
		this.categoryId = categoryId;
		this.product_description = descripcion;
		this.precio = precio;
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return product_name + "\t\t" + cantidad+"\n";
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

}
