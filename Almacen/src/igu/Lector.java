package igu;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	private JTextArea txA_InfProd;
	private JPanel panel_Opciones;
	private JPanel panel_Cantidad;
	private JButton bt_Reducir;
	private JButton bt_Añadir;
	private JTextField txF_Cantidad;
	private JPanel panel_Empaquetar_O_Recoger;
	private JPanel panel_Salir;
	private JButton bt_Recoger;
	private JButton bt_Empaquetar;

	private Bulto productos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Producto> prodPrueba = new ArrayList<Producto>();
					prodPrueba.add(new Producto(1, "a", "bbb"));
					prodPrueba.add(new Producto(2, "a", "bbb"));
					prodPrueba.add(new Producto(3, "a", "bbb"));
					prodPrueba.add(new Producto(4, "a", "bbb"));
					Lector dialog = new Lector(new Bulto(prodPrueba));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		getContentPane().add(getPanel_Centro(), BorderLayout.CENTER);
		getContentPane().add(getPanel_BotonesDefecto(), BorderLayout.SOUTH);
		productos = bultoARecoger;
		lb_InformacionProducto.setText("Producto: "
				+ productos.getProductos().get(0).getOrder_product_name()
				+ "- " + productos.getCantidad() + " unidades");
		cargarInformacionBultos();
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
			panel_Centro.setLayout(new BorderLayout(0, 0));
			panel_Centro.add(getTxA_Informacion(), BorderLayout.NORTH);
			panel_Centro.add(getPanel_InfProd(), BorderLayout.CENTER);
		}
		return panel_Centro;
	}

	private JButton getBt_Salir() {
		if (bt_Salir == null) {
			bt_Salir = new JButton("Salir");
			bt_Salir.addActionListener(new ActionListener() {
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
					.setText("Introduzca el c\u00F3digo de barras a continuaci\u00F3n para verificar la recogida del producto");
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
			panel_InfProd.add(getTxA_InfProd(), BorderLayout.SOUTH);
		}
		return panel_InfProd;
	}

	private JLabel getLb_InformacionProducto() {
		if (lb_InformacionProducto == null) {
			lb_InformacionProducto = new JLabel("Producto X - Y unidades");
		}
		return lb_InformacionProducto;
	}

	private JTextArea getTxA_InfProd() {
		if (txA_InfProd == null) {
			txA_InfProd = new JTextArea();
			txA_InfProd.setWrapStyleWord(true);
			txA_InfProd.setLineWrap(true);
		}
		return txA_InfProd;
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
			panel_Cantidad.add(getBt_Añadir());
		}
		return panel_Cantidad;
	}

	private JButton getBt_Reducir() {
		if (bt_Reducir == null) {
			bt_Reducir = new JButton("-");
			bt_Reducir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (Integer.valueOf(txF_Cantidad.getText()) > 1)
						txF_Cantidad.setText((Integer.valueOf(txF_Cantidad
								.getText()) - 1) + "");
				}
			});
		}
		return bt_Reducir;
	}

	private JButton getBt_Añadir() {
		if (bt_Añadir == null) {
			bt_Añadir = new JButton("+");
			bt_Añadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (Integer.valueOf(txF_Cantidad.getText()) < productos
							.getCantidad())
						txF_Cantidad.setText((Integer.valueOf(txF_Cantidad
								.getText()) + 1) + "");
				}
			});
		}
		return bt_Añadir;
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
			panel_Salir.add(getBt_Salir());
		}
		return panel_Salir;
	}

	private JButton getBt_Recoger() {
		if (bt_Recoger == null) {
			bt_Recoger = new JButton("Recoger");
			bt_Recoger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int i = 0;
					for (i = 0; i < Integer.valueOf(txF_Cantidad.getText()); i++)
						productos.getProductos().get(i).recoger();
					txA_InfProd.setText(i + " productos recogidos");
					cargarInformacionBultos();
					crearNuevoLector();
				}
			});
		}
		return bt_Recoger;
	}

	private JButton getBt_Empaquetar() {
		if (bt_Empaquetar == null) {
			bt_Empaquetar = new JButton("Empaquetar");
			bt_Empaquetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int i = 0;
					for (i = 0; i < Integer.valueOf(txF_Cantidad.getText()); i++)
						productos.getProductos().get(i).empaquetar();
					txA_InfProd.setText(i + " productos empaquetados");
					cargarInformacionBultos();
					crearNuevoLector();
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
		if (quedaAlgunoPorRecoger()) {
			ArrayList<Producto> productosNuevos = new ArrayList<Producto>();
			for (Producto p : productos.getProductos()) {
				if (p.getEstado_producto() == Producto.POR_RECOGER) {
					productosNuevos.add(p);
				}
			}
			Lector dialog = new Lector(new Bulto(productosNuevos));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			this.setVisible(false);
		}
		else
		{
			if(quedaAlgunoPorEmpaquetar())
			{
				ArrayList<Producto> productosNuevos = new ArrayList<Producto>();
				for (Producto p : productos.getProductos()) {
					if (p.getEstado_producto() == Producto.RECOGIDO) {
						productosNuevos.add(p);
					}
				}
				Lector dialog = new Lector(new Bulto(productosNuevos));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				this.setVisible(false);
			}
		}
	}
}
