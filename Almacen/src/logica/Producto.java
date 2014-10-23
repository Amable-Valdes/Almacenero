package logica;
import java.sql.Date;

public class Producto implements Comparable<Producto> {
	public static final int RECOGIDO = 0;
	public static final int POR_RECOGER = 1;
	public static final int PESO_GRANDE = 30;
	public static final int PESO_PEQUE�O = 20;
	
	private int order_id;
	private String product_id;
	private String order_product_name;
	private String order_product_code;
	private double ancho;
	private double largo;
	private double alto;
	private double peso;
	private double volumen;
	private Date fecha;
	private int estado_producto;
	private int estado_pedido;
	private String location;
	private int cantidadTotalEnPedido;

	public Producto(int order_id, String order_product_name, String location,
			String order_product_code, double ancho, double largo, double alto, double peso, int estadoProd) {
		super();
		this.order_id = order_id;
		this.order_product_name = order_product_name;
		this.order_product_code = order_product_code;
		this.location = location;
		this.ancho = ancho;
		this.largo = largo;
		this.alto = alto;
		volumen = ancho * largo * alto;
		this.peso = peso;
		this.estado_producto = estadoProd;
	}

	/*
	 * Constructor para pruebas
	 */
	public Producto(int peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Producto producto2) {

		String[] pasilloP1String = this.location.split("-");
		String[] pasilloP2String = producto2.getLocation().split("-");

		int[] pasilloP1 = new int[3];
		int[] pasilloP2 = new int[3];

		for (int i = 0; i < 3; i++) {
			pasilloP1[i] = Integer.parseInt(pasilloP1String[i]);
			pasilloP2[i] = Integer.parseInt(pasilloP2String[i]);
		}

		// Pasillo iguales
		if (pasilloP1[0] == pasilloP2[0]) {
			// Estanterias Iguales
			if (pasilloP1[1] == pasilloP2[1]) {
				return 0;
			}

			// Estanteria 1 < Estanter�a 2
			if (pasilloP1[1] < pasilloP2[1]) {
				return -1;
			}

			// Estanteria 1 > Estanter�a 2
			if (pasilloP1[1] > pasilloP2[1]) {
				return 1;
			}
		}

		// Pasillo 1 < Pasillo 2
		if (pasilloP1[0] < pasilloP2[0]) {
			return -1;
		}

		// Pasillo 1 > Pasillo 2
		if (pasilloP1[0] > pasilloP2[0]) {
			return 1;
		}

		return 0;
	}

	public String getLocation() {
		return location;
	}

	public int getOrder_id() {
		return order_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public String getOrder_product_name() {
		return order_product_name;
	}

	public String getOrder_product_code() {
		return order_product_code;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getEstado_producto() {
		return estado_producto;
	}
	
	public void setEstado_producto(int estado) {
		this.estado_producto = estado;
	}

	public int getEstado_pedido() {
		return estado_pedido;
	}

	@Override
	public String toString() {
		return "Producto: " + order_product_name + " - Estado: "+ ((estado_producto==RECOGIDO)?"Recogido":"NO recogido");
	}

	@Override
	public boolean equals(Object i) {
		if (this.toString().equals(i.toString()))
			return true;
		return false;

	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	public int getCantidadTotalEnPedido() {
		return cantidadTotalEnPedido;
	}

	public void setCantidadTotalEnPedido(int cantidadTotalEnPedido) {
		this.cantidadTotalEnPedido = cantidadTotalEnPedido;
	}
}
