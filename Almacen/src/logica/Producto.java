package logica;

public class Producto implements Comparable<Producto> {
	public static final int RECOGIDO = 0;
	public static final int POR_RECOGER = 1;
	public static final int EMPAQUETADO = 2;
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
	private String fecha;
	private int estado_producto;
	private int estado_pedido;
	private String location;
	private int cantidadTotalEnPedido;


	public Producto(int order_id, String nombre, int cantidad, String posicion, String fecha2,
			int porRecoger) {
		super();
		this.order_id = order_id;
		this.cantidadTotalEnPedido = cantidad;
		this.fecha = fecha2;
		this.location = posicion;
		this.order_product_name = nombre;
		this.estado_producto = porRecoger;
	}

	/*
	 * Constructor para pruebas
	 
	public Producto(int peso, int cantidad, Date fecha2, int porRecoger) {
		this.peso = peso;
		this.cantidadTotalEnPedido = cantidad;
		this.fecha = fecha2;
		this.estado_producto = porRecoger;
	}*/
	
	//Constructor para pruebas
	public Producto(int id, String nombre,String code, int estado) {
		this.order_id = id;
		this.order_product_name = nombre;
		this.order_product_code = code;
		this.estado_producto = estado;
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

	public String getFecha() {
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
	
	public void recoger()
	{
		/*
		Gestor g = new Gestor();
		g.productoRecogido(this.order_id);
		*/
		this.estado_producto = Producto.RECOGIDO;
	}
	
	public void empaquetar()
	{
		/*
		Gestor g = new Gestor();
		g.productoEmpaquetado(this.order_id);
		*/
		this.estado_producto = Producto.EMPAQUETADO;
	}
}

