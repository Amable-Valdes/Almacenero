package igu;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import logica.Bulto;
import logica.Producto;

public class Lector extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel panel_BotonesDefecto;
	private JPanel panel_Centro;
	private JButton bt_Salir;
	private JTextArea txA_Informacion;
	private JTextField txF_CodBarras;
	private JPanel panel_CodigoBarras;
	private JLabel lb_CodBarras;
	private JPanel panel_InfProd;
	private JLabel lb_InformacionProducto;
	private JPanel panel_Opciones;
	private JPanel panel_Cantidad;
	private JButton bt_Reducir;
	private JButton bt_A�adir;
	private JTextField txF_Cantidad;
	private JPanel panel_Empaquetar_O_Recoger;
	private JPanel panel_Salir;
	private JButton bt_Recoger;
	private JButton bt_Empaquetar;

	private Bulto productos;
	private int modoEjecuci�n;

	private static int RECOGER = 0;
	private static int EMPAQUETAR = 1;
	private JButton bt_AvisarIncidencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ArrayList<Producto> prodPrueba = new ArrayList<Producto>();
					int estado = Producto.RECOGIDO;
					prodPrueba.add(new Producto(1, "Producto almac�n", "aaa",
							estado));
					prodPrueba.add(new Producto(2, "Producto almac�n", "aaa",
							estado));
					prodPrueba.add(new Producto(3, "Producto almac�n", "aaa",
							estado));
					prodPrueba.add(new Producto(4, "Producto almac�n", "aaa",
							estado));
					Lector dialog = new Lector(new Bulto(prodPrueba));
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Lector(Bulto bultoARecoger) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		getContentPane().add(getPanel_Centro(), BorderLayout.CENTER);
		getContentPane().add(getPanel_BotonesDefecto(), BorderLayout.SOUTH);
		productos = bultoARecoger;
		modoEjecuci�n = SeleccionarModoEjecucion();
		lb_InformacionProducto.setText("Producto: "
				+ productos.getProductos().get(0).getOrder_product_name()
				+ "- " + productos.getCantidad() + " unidades");
		cargarInformacionBultos();
	}

	private int SeleccionarModoEjecucion() {
		if (quedaAlgunoPorRecoger()) {
			seleccionarProductosSinRecoger();
			return RECOGER;
		} else {
			seleccionarProductosRecogidos();
			return EMPAQUETAR;
		}
	}

	private void seleccionarProductosRecogidos() {
		ArrayList<Producto> productosNuevos = new ArrayList<Producto>();
		for (Producto p : productos.getProductos()) {
			if (p.getEstado_producto() == Producto.RECOGIDO) {
				productosNuevos.add(p);
			}
		}
		productos = new Bulto(productosNuevos);
	}

	private void seleccionarProductosSinRecoger() {
		ArrayList<Producto> productosNuevos = new ArrayList<Producto>();
		for (Producto p : productos.getProductos()) {
			if (p.getEstado_producto() == Producto.POR_RECOGER) {
				productosNuevos.add(p);
			}
		}
		productos = new Bulto(productosNuevos);
	}

	private void cargarInformacionBultos() {
		if (productos.getProductos().get(0).getEstado_producto() == Producto.POR_RECOGER) {
			bt_Recoger.setEnabled(true);
			bt_Empaquetar.setEnabled(false);
		}
		if (productos.getProductos().get(0).getEstado_producto() == Producto.RECOGIDO) {
			bt_Recoger.setEnabled(false);
			bt_Empaquetar.setEnabled(true);
		}
		if (productos.getProductos().get(0).getEstado_producto() == Producto.EMPAQUETADO) {
			bt_Recoger.setEnabled(false);
			bt_Empaquetar.setEnabled(false);
		}
	}

	private JPanel getPanel_BotonesDefecto() {
		if (panel_BotonesDefecto == null) {
			panel_BotonesDefecto = new JPanel();
			panel_BotonesDefecto.setLayout(new BorderLayout(0, 0));
			panel_BotonesDefecto.add(getPanel_Empaquetar_O_Recoger());
			panel_BotonesDefecto.add(getPanel_Salir(), BorderLayout.SOUTH);
		}
		return panel_BotonesDefecto;
	}

	private JPanel getPanel_Centro() {
		if (panel_Centro == null) {
			panel_Centro = new JPanel();
			panel_Centro.setLayout(new BorderLayout(5, 5));
			panel_Centro.add(getTxA_Informacion(), BorderLayout.NORTH);
			panel_Centro.add(getPanel_InfProd(), BorderLayout.CENTER);
		}
		return panel_Centro;
	}

	private JButton getBt_Salir() {
		if (bt_Salir == null) {
			bt_Salir = new JButton("Salir");
			bt_Salir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					salir();
				}
			});
		}
		return bt_Salir;
	}

	private void salir() {
		this.dispose();
	}

	private JTextArea getTxA_Informacion() {
		if (txA_Informacion == null) {
			txA_Informacion = new JTextArea();
			txA_Informacion.setEditable(false);
			txA_Informacion
					.setText("Introduzca el c\u00F3digo de barras a continuaci\u00F3n para verificar la operaci�n");
			txA_Informacion.setWrapStyleWord(true);
			txA_Informacion.setLineWrap(true);
		}
		return txA_Informacion;
	}

	private JTextField getTxF_CodBarras() {
		if (txF_CodBarras == null) {
			txF_CodBarras = new JTextField();
			txF_CodBarras.setColumns(10);
		}
		return txF_CodBarras;
	}

	private JPanel getPanel_CodigoBarras() {
		if (panel_CodigoBarras == null) {
			panel_CodigoBarras = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_CodigoBarras.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(10);
			panel_CodigoBarras.add(getLb_CodBarras());
			panel_CodigoBarras.add(getTxF_CodBarras());
		}
		return panel_CodigoBarras;
	}

	private JLabel getLb_CodBarras() {
		if (lb_CodBarras == null) {
			lb_CodBarras = new JLabel("Introduzca el c\u00F3digo de barras:");
		}
		return lb_CodBarras;
	}

	private JPanel getPanel_InfProd() {
		if (panel_InfProd == null) {
			panel_InfProd = new JPanel();
			panel_InfProd.setLayout(new BorderLayout(10, 10));
			panel_InfProd.add(getPanel_Opciones(), BorderLayout.CENTER);
			panel_InfProd.add(getLb_InformacionProducto(), BorderLayout.NORTH);
		}
		return panel_InfProd;
	}

	private JLabel getLb_InformacionProducto() {
		if (lb_InformacionProducto == null) {
			lb_InformacionProducto = new JLabel("Producto X - Y unidades");
		}
		return lb_InformacionProducto;
	}

	private JPanel getPanel_Opciones() {
		if (panel_Opciones == null) {
			panel_Opciones = new JPanel();
			panel_Opciones.setLayout(new BorderLayout(0, 0));
			panel_Opciones.add(getPanel_CodigoBarras(), BorderLayout.CENTER);
			panel_Opciones.add(getPanel_Cantidad(), BorderLayout.SOUTH);
		}
		return panel_Opciones;
	}

	private JPanel getPanel_Cantidad() {
		if (panel_Cantidad == null) {
			panel_Cantidad = new JPanel();
			panel_Cantidad.add(getBt_Reducir());
			panel_Cantidad.add(getTxF_Cantidad());
			panel_Cantidad.add(getBt_A�adir());
		}
		return panel_Cantidad;
	}

	private JButton getBt_Reducir() {
		if (bt_Reducir == null) {
			bt_Reducir = new JButton("-");
			bt_Reducir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (Integer.valueOf(txF_Cantidad.getText()) > 1)
						txF_Cantidad.setText((Integer.valueOf(txF_Cantidad
								.getText()) - 1) + "");
				}
			});
		}
		return bt_Reducir;
	}

	private JButton getBt_A�adir() {
		if (bt_A�adir == null) {
			bt_A�adir = new JButton("+");
			bt_A�adir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (Integer.valueOf(txF_Cantidad.getText()) < productos
							.getCantidad())
						txF_Cantidad.setText((Integer.valueOf(txF_Cantidad
								.getText()) + 1) + "");
				}
			});
		}
		return bt_A�adir;
	}

	private JTextField getTxF_Cantidad() {
		if (txF_Cantidad == null) {
			txF_Cantidad = new JTextField();
			txF_Cantidad.setText("1");
			txF_Cantidad.setHorizontalAlignment(SwingConstants.CENTER);
			txF_Cantidad.setEditable(false);
			txF_Cantidad.setColumns(10);
		}
		return txF_Cantidad;
	}

	private JPanel getPanel_Empaquetar_O_Recoger() {
		if (panel_Empaquetar_O_Recoger == null) {
			panel_Empaquetar_O_Recoger = new JPanel();
			@SuppressWarnings("unused")
			FlowLayout flowLayout = (FlowLayout) panel_Empaquetar_O_Recoger
					.getLayout();
			panel_Empaquetar_O_Recoger.add(getBt_Recoger());
			panel_Empaquetar_O_Recoger.add(getBt_Empaquetar());
		}
		return panel_Empaquetar_O_Recoger;
	}

	private JPanel getPanel_Salir() {
		if (panel_Salir == null) {
			panel_Salir = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_Salir.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel_Salir.add(getBt_AvisarIncidencia());
			panel_Salir.add(getBt_Salir());
		}
		return panel_Salir;
	}

	private JButton getBt_Recoger() {
		if (bt_Recoger == null) {
			bt_Recoger = new JButton("Recoger");
			bt_Recoger.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (txF_CodBarras.getText().equals(
							productos.getProductos().get(0)
									.getOrder_product_code())) {
						int i = 0;
						for (i = 0; i < Integer.valueOf(txF_Cantidad.getText()); i++)
							productos.getProductos().get(i).recoger();
						JOptionPane.showMessageDialog(null, i
								+ " productos recogidos");
						cargarInformacionBultos();
						crearNuevoLector();
					} else {
						txA_Informacion.setText("Codigo de barras incorrecto");
						JOptionPane
								.showMessageDialog(null,
										"El codigo de barras introducido es incorrecto, vuelva a introducirlo");
					}
				}
			});
		}
		return bt_Recoger;
	}

	private JButton getBt_Empaquetar() {
		if (bt_Empaquetar == null) {
			bt_Empaquetar = new JButton("Empaquetar");
			bt_Empaquetar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (txF_CodBarras.getText().equals(
							productos.getProductos().get(0)
									.getOrder_product_code())) {
						int i = 0;
						for (i = 0; i < Integer.valueOf(txF_Cantidad.getText()); i++)
							productos.getProductos().get(i).empaquetar();
						JOptionPane.showMessageDialog(null, i
								+ " productos empaquetados");
						cargarInformacionBultos();
						crearNuevoLector();
					} else {
						txA_Informacion.setText("Codigo de barras incorrecto");
						JOptionPane
								.showMessageDialog(null,
										"El codigo de barras introducido es incorrecto, vuelva a introducirlo");
					}
				}
			});
		}
		return bt_Empaquetar;
	}

	private boolean quedaAlgunoPorRecoger() {
		for (Producto p : productos.getProductos()) {
			if (p.getEstado_producto() == Producto.POR_RECOGER)
				return true;
		}
		return false;
	}

	private boolean quedaAlgunoPorEmpaquetar() {
		for (Producto p : productos.getProductos()) {
			if (p.getEstado_producto() == Producto.RECOGIDO)
				return true;
		}
		return false;
	}

	private void crearNuevoLector() {
		if (quedaAlgunoPorRecoger() && modoEjecuci�n == RECOGER) {
			ArrayList<Producto> productosNuevos = new ArrayList<Producto>();
			for (Producto p : productos.getProductos()) {
				if (p.getEstado_producto() == Producto.POR_RECOGER) {
					productosNuevos.add(p);
				}
			}
			Lector dialog = new Lector(new Bulto(productosNuevos));
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			this.setVisible(false);
		} else {
			if (quedaAlgunoPorEmpaquetar() && modoEjecuci�n == EMPAQUETAR) {
				ArrayList<Producto> productosNuevos = new ArrayList<Producto>();
				for (Producto p : productos.getProductos()) {
					if (p.getEstado_producto() == Producto.RECOGIDO) {
						productosNuevos.add(p);
					}
				}
				Lector dialog = new Lector(new Bulto(productosNuevos));
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				this.setVisible(false);
			} else
				JOptionPane.showMessageDialog(null,
						"No quedan m�s productos que "
								+ ((modoEjecuci�n == RECOGER) ? "recoger"
										: "empaquetar")
								+ ". Saliendo del programa");
			if(modoEjecuci�n == EMPAQUETAR)
				JOptionPane.showMessageDialog(null,"Pegatina generada");
		}
		this.salir();
	}

	private JButton getBt_AvisarIncidencia() {
		if (bt_AvisarIncidencia == null) {
			bt_AvisarIncidencia = new JButton("Avisar de una incidencia");
			bt_AvisarIncidencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String seleccion = JOptionPane.showInputDialog(null,
							"�Que ha ocurrido?", "",
							JOptionPane.QUESTION_MESSAGE);
					if (seleccion != null)
						JOptionPane.showMessageDialog(null,
								"Aviso de incidencia realizado");
				}
			});
		}
		return bt_AvisarIncidencia;
	}
}
