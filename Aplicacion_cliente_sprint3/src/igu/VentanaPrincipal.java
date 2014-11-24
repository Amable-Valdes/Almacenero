package igu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Categoria;
import logica.Conexion;
import logica.Gestor;
import logica.Producto;
import logica.Subcategoria;
import javax.swing.JTextArea;

public class VentanaPrincipal extends JFrame {

	private Gestor gestor;
	private ArrayList<Producto> productos;
	private ArrayList<Subcategoria> subcategorias;
	private ArrayList<Categoria> categorias;
	private JPanel contentPane;

	private JPanel panelBase;

	// tabla categorias
	private JPanel pn1;
	private JScrollPane scrollPaneCategoria;
	private JTable tablaCategorias;
	private DefaultTableModel modeloTablaCategorias;
	int idCategoriaSeleccionada = 0;

	// tabla subcategorias
	private JPanel pnSubcategorias;
	private JScrollPane scrollPaneSubcategoria;
	private JTable tablaSubcategorias;
	private DefaultTableModel modeloTablaSubcategorias;
	int idSubcategoriaSeleccionada = 0;

	// tabla productos
	private JPanel pn2;
	private JButton btAnterior2;
	private JScrollPane scrollPaneProductos;
	private JTable tableProductos;
	private DefaultTableModel modeloTablaProductos;
	private JScrollPane scrollPaneDescripcionProducto;
	private JTextPane textPaneDescripcionProducto;
	private JLabel labelCategoriaNombre;
	int idProductoSeleccionado = 0;

	// carrito
	private JPanel panelProductosSeleccionados;
	private JScrollPane scrollPaneCarrito;
	private JLabel lblLogo;
	private JButton btnAdd;
	private JButton btnVaciar;
	private JTable tableCarrito;
	private DefaultTableModel modeloTablaCarrito;
	private ArrayList<Producto> productosEnCarrito;
	private Producto productoSeleccionado;
	private ArrayList<Producto> adds;
	private JButton btnProcederConLa;
	private JLabel lblPrecioTotal;
	private JTextField textFieldPrecioTotal;
	private JButton btnModificar;
	private JLabel labelCategoria;
	private JButton btnVolver;
	private JTextPane textPaneAyuda;
	private JSpinner spinnerAdd;
	private JPanel panelConfirma;
	private JButton buttonEliminar;
	private JLabel lblConfirmacin;
	private JTextPane textPaneConfirmacion;
	private JLabel lblCarrito;
	private JButton btnPagar;
	private JButton btnSeguirComprando;
	private JTextArea textAreaConfirma;
	private JTextArea textAreaNombre;
	private JTextField textFieldPrecioFinalConfirmacion;
	private JLabel lblPrecioTotal_1;
	private JPanel panelLogin;
	private JPanel panelAntesLogin;
	private JPanel panelDespuesLogin;
	private JButton btnRegistrarse;
	private JButton btnIdentificarse;
	private JLabel lblUsuario;
	private JTextField textFieldUsuario;
	private JLabel lblContrasea;
	private JTextField textFieldPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		// logica
		gestor = new Gestor();
		productos = gestor.getProductos();
		subcategorias = gestor.getSubcategorias();

//		for (Subcategoria s : subcategorias)
//			System.out.println(s.toString());

		categorias = gestor.getCategorias();

//		for (Categoria c : categorias)
//			System.out.println(c.toString());

		productosEnCarrito = new ArrayList<Producto>();
		adds = new ArrayList<Producto>();

		// igu
		setResizable(false);
		setTitle("App usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelProductosSeleccionados());
		contentPane.add(getLblLogo());
		contentPane.add(getPanelBase());
		contentPane.add(getPanelLogin());

	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("Aplicaci\u00F3n de usuario");
			lblLogo.setFont(new Font("Sitka Small", Font.PLAIN, 22));
			lblLogo.setBounds(60, 11, 409, 38);
		}
		return lblLogo;
	}

	// clases adaptadoras
	class ClickFilasCategoria extends MouseAdapter {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			clickFilaCategoria();
		}
	}

	class ClickFilasSubcategoria extends MouseAdapter {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			clickFilaSubCategoria();
		}
	}

	class ClickFilasProducto extends MouseAdapter {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			clickFilaProducto();
		}
	}

	class ClickFilasCarrito extends MouseAdapter {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			clickFilaCarrito();
		}
	}

	// metodos clases adaptadoras
	private void clickFilaCategoria() {
		idCategoriaSeleccionada = tablaCategorias.getSelectedRow();
		System.out.println(idCategoriaSeleccionada);
		scrollPaneSubcategoria
				.setViewportView(getTablaSubcategorias(idCategoriaSeleccionada));
		((CardLayout) panelBase.getLayout()).show(panelBase,
				"panelSubcategorias");
		labelCategoria.setText(categorias.get(idCategoriaSeleccionada)
				.getNombre());
	}

	private void clickFilaSubCategoria() {
		idSubcategoriaSeleccionada = tablaSubcategorias.getSelectedRow();
		String nombreSub = modeloTablaSubcategorias.getValueAt(
				idSubcategoriaSeleccionada, 0).toString();
		for (Subcategoria s : subcategorias)
			if (s.getNombre() == nombreSub)
				idSubcategoriaSeleccionada = s.getId() - 1;
		// aqui esta el fallo
		System.out.println(subcategorias.get(idSubcategoriaSeleccionada)
				.getNombre());
		scrollPaneProductos
				.setViewportView(getTableProductos(idSubcategoriaSeleccionada));
		((CardLayout) panelBase.getLayout()).show(panelBase, "panel2");
		labelCategoriaNombre.setText(nombreSub);
	}

	private void clickFilaProducto() {
		System.out.println("" + tableProductos.getSelectedRow());
		productoSeleccionado = subcategorias.get(idSubcategoriaSeleccionada)
				.getProductos().get(tableProductos.getSelectedRow());
		btnAdd.setEnabled(true);
		textPaneDescripcionProducto.setText(productoSeleccionado
				.getProduct_description());
		spinnerAdd.setEnabled(true);
	}

	private void clickFilaCarrito() {
		tableProductos.getSelectedRow();
		buttonEliminar.setEnabled(true);
	}

	// metodos de las tablas
	private void addFilasCategorias() {
		Object[] nuevaFila = new Object[1];
		for (Categoria c : categorias) {
			nuevaFila[0] = c.getNombre();
			modeloTablaCategorias.addRow(nuevaFila);
		}
	}

	private void addFilasSubcategorias(int idCategoria) {
		Object[] nuevaFila = new Object[1];
		for (Subcategoria c : subcategorias) {
			int valor = idCategoria + 1;
			if (valor == c.getIdCategoriaPadre()) {
				nuevaFila[0] = c.getNombre();
				modeloTablaSubcategorias.addRow(nuevaFila);
			}
		}
	}

	private void addFilasProductos(int idCategoria) {
		Object[] nuevaFila = new Object[2];
		for (Producto p : productos) {
			int valor = idCategoria + 1;
			if (valor == p.getCategoryId()) {
				nuevaFila[0] = p.getProduct_name();
				nuevaFila[1] = p.getPrecio();
				modeloTablaProductos.addRow(nuevaFila);
			}
		}
	}

	private void addFilasCarrito() {
		Object[] nuevaFila = new Object[3];
		if (!adds.contains(productoSeleccionado)) {
			nuevaFila[0] = productoSeleccionado.getProduct_name();
			nuevaFila[1] = (Integer) spinnerAdd.getValue();
			nuevaFila[2] = productoSeleccionado.getPrecio();
			modeloTablaCarrito.addRow(nuevaFila);
			productoSeleccionado.setCantidad((Integer) spinnerAdd.getValue());
			adds.add(productoSeleccionado);
		} else {
			int posicion = buscarEnCarrito(productoSeleccionado);
			adds.get(posicion).setCantidad(
					adds.get(posicion).getCantidad()
							+ (Integer) spinnerAdd.getValue());
			modificarCarrito();
		}
	}

	private int buscarEnCarrito(Producto p) {
		int posicion = -1;
		for (int i = 0; i < adds.size(); i++) {
			if (adds.get(i).getProduct_name() == p.getProduct_name())
				posicion = i;
		}
		return posicion;
	}

	private void actionModificado() {
		for (int i = 0; i < modeloTablaCarrito.getRowCount(); i++) {
			try {
				int nuevoValor = Integer.parseInt(modeloTablaCarrito
						.getValueAt(i, 1).toString());
				if (nuevoValor == 0) {
					adds.remove(i);
				} else if (nuevoValor > 0) {
					adds.get(i).setCantidad(nuevoValor);
				} else {
					JOptionPane.showMessageDialog(this,
							"Introduce un nï¿½mero vï¿½lido", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Introduce un número",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		modificarCarrito();
	}

	private void modificarCarrito() {
		while (modeloTablaCarrito.getRowCount() > 0)
			modeloTablaCarrito.removeRow(0);
		Object[] nuevaFila = new Object[3];
		for (Producto p : adds) {
			nuevaFila[0] = p.getProduct_name();
			nuevaFila[1] = p.getCantidad();
			nuevaFila[2] = p.getPrecio() * p.getCantidad();
			modeloTablaCarrito.addRow(nuevaFila);
		}
	}

	private String calcularPrecioTotal() {
		double total = 0;
		for (int i = 0; i < adds.size(); i++)
			total += adds.get(i).getPrecio() * adds.get(i).getCantidad();
		return "" + total;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////
	// TABLAS///
	private JPanel getPanelBase() {
		if (panelBase == null) {
			panelBase = new JPanel();
			panelBase.setBounds(10, 84, 568, 454);
			panelBase.setLayout(new CardLayout(0, 0));
			panelBase.add(getPn1(), "panel1");
			panelBase.add(getPnSubcategorias(), "panelSubcategorias");
			panelBase.add(getPn2(), "panel2");
			panelBase.add(getPanelConfirma(), "confirmacion");
		}
		return panelBase;
	}

	// categorias
	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setLayout(null);
			pn1.setName("panel1");
			pn1.add(getScrollPaneCategoria());
		}
		return pn1;
	}

	private JScrollPane getScrollPaneCategoria() {
		if (scrollPaneCategoria == null) {
			scrollPaneCategoria = new JScrollPane();
			scrollPaneCategoria.setBounds(0, 0, 567, 449);
			scrollPaneCategoria.setViewportView(getTablaCategorias());
		}
		return scrollPaneCategoria;
	}

	private JTable getTablaCategorias() {
		if (tablaCategorias == null) {
			String[] nombreColumnas = { "Nombre" };
			modeloTablaCategorias = new ModeloNoEditable(nombreColumnas, 0);
			tablaCategorias = new JTable(modeloTablaCategorias);
			tablaCategorias
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			addFilasCategorias();
			tablaCategorias.addMouseListener(new ClickFilasCategoria());
			tablaCategorias.setRowHeight(20);
			tablaCategorias.getTableHeader().getColumnModel().getColumn(0)
					.setPreferredWidth(15);
			tablaCategorias.getTableHeader().setReorderingAllowed(false);
		}
		return tablaCategorias;
	}

	// subcategorias
	private JPanel getPnSubcategorias() {
		if (pnSubcategorias == null) {
			pnSubcategorias = new JPanel();
			pnSubcategorias.setLayout(null);
			pnSubcategorias.setName("panel1");
			pnSubcategorias.add(getScrollPaneSubcategoria());
			pnSubcategorias.add(getLabelCategoria());
			pnSubcategorias.add(getBtnVolver());
		}
		return pnSubcategorias;
	}

	private JScrollPane getScrollPaneSubcategoria() {
		if (scrollPaneSubcategoria == null) {
			scrollPaneSubcategoria = new JScrollPane();
			scrollPaneSubcategoria.setBounds(0, 46, 567, 361);
		}
		return scrollPaneSubcategoria;
	}

	private JTable getTablaSubcategorias(int idcategoria) {
		if (tablaSubcategorias == null) {
			String[] nombreColumnas = { "Nombre" };
			modeloTablaSubcategorias = new ModeloNoEditable(nombreColumnas, 0);
			tablaSubcategorias = new JTable(modeloTablaSubcategorias);
			addFilasSubcategorias(idcategoria);
			tablaSubcategorias.addMouseListener(new ClickFilasSubcategoria());
			tablaSubcategorias.setRowHeight(20);
			tablaSubcategorias.getTableHeader().getColumnModel().getColumn(0)
					.setPreferredWidth(15);
			tablaSubcategorias.getTableHeader().setReorderingAllowed(false);
		}
		return tablaSubcategorias;
	}

	private JLabel getLabelCategoria() {
		if (labelCategoria == null) {
			labelCategoria = new JLabel("");
			labelCategoria.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelCategoria.setBounds(0, 0, 567, 28);
		}
		return labelCategoria;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panelBase.getLayout()).show(panelBase,
							"panel1");
					tablaSubcategorias = null;
				}
			});
			btnVolver.setBounds(468, 418, 89, 23);
		}
		return btnVolver;
	}

	// productos
	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setName("panel2");
			pn2.setLayout(null);
			pn2.add(getScrollPaneProductos());
			pn2.add(getBtAnterior2());
			pn2.add(getScrollPaneDescripcionProducto());
			pn2.add(getLabelCategoriaNombre());
			pn2.add(getBtnAdd());
			pn2.add(getTextPaneAyuda());
			pn2.add(getSpinnerAdd());
			pn2.setVisible(false);
		}
		return pn2;
	}

	private JScrollPane getScrollPaneProductos() {
		if (scrollPaneProductos == null) {
			scrollPaneProductos = new JScrollPane();
			scrollPaneProductos.setBounds(0, 112, 567, 246);
		}
		return scrollPaneProductos;
	}

	private JTable getTableProductos(int idSubcategoria) {
		if (tableProductos == null) {
			String[] nombreColumnas = { "Nombre", "Precio" };
			modeloTablaProductos = new ModeloNoEditable(nombreColumnas, 0);
			tableProductos = new JTable(modeloTablaProductos);
			tableProductos.addMouseListener(new ClickFilasProducto());
			addFilasProductos(idSubcategoria);
			tableProductos.setRowHeight(20);
			tableProductos.getTableHeader().getColumnModel().getColumn(0)
					.setPreferredWidth(15);
			tableProductos.getTableHeader().setReorderingAllowed(false);
		}
		return tableProductos;
	}

	private JScrollPane getScrollPaneDescripcionProducto() {
		if (scrollPaneDescripcionProducto == null) {
			scrollPaneDescripcionProducto = new JScrollPane();
			scrollPaneDescripcionProducto.setBorder(new TitledBorder(null,
					"Descripcion", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			scrollPaneDescripcionProducto.setBounds(0, 358, 407, 91);
			scrollPaneDescripcionProducto
					.setViewportView(getTextPaneDescripcionProducto());
		}
		return scrollPaneDescripcionProducto;
	}

	private JTextPane getTextPaneDescripcionProducto() {
		if (textPaneDescripcionProducto == null) {
			textPaneDescripcionProducto = new JTextPane();
			textPaneDescripcionProducto.setBorder(null);
			textPaneDescripcionProducto.setBackground(SystemColor.control);
			textPaneDescripcionProducto.setSelectedTextColor(Color.WHITE);
			textPaneDescripcionProducto.setEditable(false);
		}
		return textPaneDescripcionProducto;
	}

	private JButton getBtAnterior2() {
		if (btAnterior2 == null) {
			btAnterior2 = new JButton("Volver");
			btAnterior2.setBounds(474, 410, 93, 28);
			btAnterior2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout) panelBase.getLayout()).show(panelBase,
							"panelSubcategorias");
					tableProductos = null;
					textPaneDescripcionProducto.setText("");
				}
			});
			btAnterior2.setForeground(new Color(102, 0, 0));
			btAnterior2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btAnterior2;
	}

	private JLabel getLabelCategoriaNombre() {
		if (labelCategoriaNombre == null) {
			labelCategoriaNombre = new JLabel("");
			labelCategoriaNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			labelCategoriaNombre.setBounds(0, 0, 567, 28);
		}
		return labelCategoriaNombre;
	}

	// panel confirmacion
	private JPanel getPanelConfirma() {
		if (panelConfirma == null) {
			panelConfirma = new JPanel();
			panelConfirma.setLayout(null);
			panelConfirma.add(getLblConfirmacin());
			panelConfirma.add(getTextPaneConfirmacion());
			panelConfirma.add(getBtnPagar());
			panelConfirma.add(getBtnSeguirComprando());
			panelConfirma.add(getTextAreaConfirma());
			panelConfirma.add(getTextAreaNombre());
			panelConfirma.add(getTextFieldPrecioFinalConfirmacion());
			panelConfirma.add(getLblPrecioTotal_1());
		}
		return panelConfirma;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// carrito //
	private JPanel getPanelProductosSeleccionados() {
		if (panelProductosSeleccionados == null) {
			panelProductosSeleccionados = new JPanel();
			panelProductosSeleccionados.setBorder(null);
			panelProductosSeleccionados.setBounds(593, 180, 255, 358);
			panelProductosSeleccionados.setLayout(null);
			panelProductosSeleccionados.add(getLblCarrito());
			panelProductosSeleccionados.add(getScrollPaneCarrito());
			panelProductosSeleccionados.add(getBtnVaciar());
			panelProductosSeleccionados.add(getBtnProcederConLa());
			panelProductosSeleccionados.add(getLblPrecioTotal());
			panelProductosSeleccionados.add(getTextFieldPrecioTotal());
			panelProductosSeleccionados.add(getBtnModificar());
			panelProductosSeleccionados.add(getButtonEliminar());
		}
		return panelProductosSeleccionados;
	}

	private JScrollPane getScrollPaneCarrito() {
		if (scrollPaneCarrito == null) {
			scrollPaneCarrito = new JScrollPane();
			scrollPaneCarrito.setBounds(10, 31, 235, 184);
			String[] nombreColumnas = { "Nombre", "Cantidad", "Precio" };
			modeloTablaCarrito = new ModeloEditableCarrito(nombreColumnas, 0);
			scrollPaneCarrito.setViewportView(getTableCarrito());
		}
		return scrollPaneCarrito;
	}

	private JTable getTableCarrito() {
		if (tableCarrito == null) {
			tableCarrito = new JTable(modeloTablaCarrito);
			tableCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableCarrito.addMouseListener(new ClickFilasCarrito());
			tableCarrito.getTableHeader().setReorderingAllowed(false);
		}
		return tableCarrito;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("A\u00F1adir");
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAdd.setBounds(474, 371, 93, 28);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addFilasCarrito();
					tableCarrito = null;
					productosEnCarrito.add(productoSeleccionado);
					getTableCarrito();
					btnProcederConLa.setEnabled(true);
					btnVaciar.setEnabled(true);
					textFieldPrecioTotal.setText(calcularPrecioTotal());
					btnModificar.setEnabled(true);
				}
			});
			btnAdd.setEnabled(false);
		}
		return btnAdd;
	}

	private JSpinner getSpinnerAdd() {
		if (spinnerAdd == null) {
			spinnerAdd = new JSpinner();
			spinnerAdd.setEnabled(false);
			spinnerAdd.setModel(new SpinnerNumberModel(new Integer(1),
					new Integer(1), null, new Integer(1)));
			spinnerAdd.setToolTipText("");
			spinnerAdd.setBounds(428, 376, 29, 20);
		}
		return spinnerAdd;
	}

	private JButton getBtnVaciar() {
		if (btnVaciar == null) {
			btnVaciar = new JButton("Vaciar carrito");
			btnVaciar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnVaciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while (modeloTablaCarrito.getRowCount() > 0)
						modeloTablaCarrito.removeRow(0);
					tableCarrito = new JTable(modeloTablaCarrito);
					btnProcederConLa.setEnabled(false);
					btnVaciar.setEnabled(false);
					adds.clear();
					textFieldPrecioTotal.setText("");
					btnModificar.setEnabled(false);
				}
			});
			btnVaciar.setEnabled(false);
			btnVaciar.setBounds(20, 300, 214, 23);
		}
		return btnVaciar;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actionModificado();
					textFieldPrecioTotal.setText(calcularPrecioTotal());
				}
			});
			btnModificar.setEnabled(false);
			btnModificar.setBounds(20, 275, 214, 23);
		}
		return btnModificar;
	}

	private JButton getButtonEliminar() {
		if (buttonEliminar == null) {
			buttonEliminar = new JButton("Eliminar");
			buttonEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (adds.size() == 1) {
						buttonEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						btnVaciar.setEnabled(false);
						btnProcederConLa.setEnabled(false);
					}
					String nombreProducto = modeloTablaCarrito.getValueAt(
							idProductoSeleccionado, 0).toString();
					Producto borrar = null;
					for (Producto p : adds)
						if (p.getProduct_name() == nombreProducto)
							borrar = p;
					if (borrar != null)
						adds.remove(borrar);

					modificarCarrito();
					tableCarrito = null;
					getTableCarrito();
					textFieldPrecioTotal.setText(calcularPrecioTotal());
				}
			});
			buttonEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonEliminar.setEnabled(false);
			buttonEliminar.setBounds(20, 250, 214, 23);
		}
		return buttonEliminar;
	}

	private JButton getBtnProcederConLa() {
		if (btnProcederConLa == null) {
			btnProcederConLa = new JButton("Proceder con la compra");
			btnProcederConLa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String t = "";
					String c = "";
					((CardLayout) panelBase.getLayout()).show(panelBase,
							"confirmacion");
					panelProductosSeleccionados.setEnabled(false);
					panelProductosSeleccionados.setVisible(false);
					panelBase.setBounds(10, 84, 810, 454);
					t+= "\nNombre\n\n";
					c+= "\nCantidad\n\n";
					for (Producto p : adds){
						t += p.getProduct_name()+"\n";
						c += p.getCantidad()+"\n";
					}
					getTextAreaConfirma().setText(t);
					getTextAreaNombre().setText(c);
					textFieldPrecioFinalConfirmacion.setText(calcularPrecioTotal());
				}
			});
			btnProcederConLa.setEnabled(false);
			btnProcederConLa.setBounds(20, 324, 214, 23);

		}
		return btnProcederConLa;
	}

	private JLabel getLblPrecioTotal() {
		if (lblPrecioTotal == null) {
			lblPrecioTotal = new JLabel("Precio total:");
			lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPrecioTotal.setBounds(20, 214, 96, 35);
		}
		return lblPrecioTotal;
	}

	private JTextField getTextFieldPrecioTotal() {
		if (textFieldPrecioTotal == null) {
			textFieldPrecioTotal = new JTextField();
			textFieldPrecioTotal.setEditable(false);
			textFieldPrecioTotal.setBounds(114, 223, 120, 20);
			textFieldPrecioTotal.setColumns(10);
		}
		return textFieldPrecioTotal;
	}

	public void crearPedido() {
		int numeroPedido = seleccionarUltimoIDPedido() + 1;
		System.out.println("Generando pedido n:" + numeroPedido);
		Conexion c = new Conexion();
		c.crearConexion();
		try {
			for (Producto p : adds) {
				System.out.println("CREANDO EL PEDIDO...");
				PreparedStatement stmt = c
						.getCon()
						.prepareStatement(
								"INSERT INTO order_product (order_product_id, order_id, product_id, order_product_quantity) VALUES (null, ?, ?, ?)");
				int product_id = p.getProduct_id();
				int order_product_quantity = p.getCantidad();

				stmt.setInt(1, numeroPedido);
				stmt.setInt(2, product_id);
				stmt.setInt(3, order_product_quantity);

				stmt.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.cerraConexion();
	}

	private int seleccionarUltimoIDPedido() {
		int id = 0;
		Conexion c = new Conexion();
		try {
			c.crearConexion();
			PreparedStatement ps = c.getCon().prepareCall(
					"SELECT max(order_id) AS 'salida' FROM order_product");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("salida");
			}
			c.cerraConexion();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return id;
	}

	private JTextPane getTextPaneAyuda() {
		if (textPaneAyuda == null) {
			textPaneAyuda = new JTextPane();
			textPaneAyuda.setBackground(SystemColor.control);
			textPaneAyuda.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textPaneAyuda
					.setText("Para a\u00F1adir un producto al carrito haz click en la fila del producto que quieras, despu\u00E9s a\u00F1ade la cantidad del producto a a\u00F1adir y luego presiona sobre el bot\u00F3n a\u00F1adir.\r\nPara eliminar un producto haz click en la fila del producto (en el carrito) que quieras eliminar, luego pulsa sobre eliminar. Para borrar todos pincha sobre vaciar carrito.\r\nModificar sirve para guardar las unidades introducidad en el campo cantidad de carrito.");
			textPaneAyuda.setBounds(0, 26, 567, 86);
		}
		return textPaneAyuda;
	}

	private JLabel getLblConfirmacin() {
		if (lblConfirmacin == null) {
			lblConfirmacin = new JLabel("Confirmaci\u00F3n");
			lblConfirmacin.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblConfirmacin.setBounds(10, 11, 547, 37);
		}
		return lblConfirmacin;
	}

	private JTextPane getTextPaneConfirmacion() {
		if (textPaneConfirmacion == null) {
			textPaneConfirmacion = new JTextPane();
			textPaneConfirmacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textPaneConfirmacion.setBackground(SystemColor.control);
			textPaneConfirmacion
					.setText("Por favor asegurese que la siguiente informaci\u00F3n relacionada con su pedido es correcta");
			textPaneConfirmacion.setEditable(false);
			textPaneConfirmacion.setBounds(10, 52, 785, 37);
		}
		return textPaneConfirmacion;
	}

	private JLabel getLblCarrito() {
		if (lblCarrito == null) {
			lblCarrito = new JLabel("Productos seleccionados:");
			lblCarrito.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblCarrito.setBounds(10, 0, 202, 20);
		}
		return lblCarrito;
	}

	private JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("Pagar");
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearPedido();
					// limpio todo
					adds.clear();
					((CardLayout) panelBase.getLayout()).show(panelBase,
							"panel1");
					tableProductos = null;
					while (modeloTablaCarrito.getRowCount() > 0)
						modeloTablaCarrito.removeRow(0);
					tableCarrito = new JTable(modeloTablaCarrito);
					tableCarrito = null;
					textFieldPrecioTotal.setText("");
					btnProcederConLa.setEnabled(false);
					btnVaciar.setEnabled(false);
					adds.clear();
					btnModificar.setEnabled(false);
					btnAdd.setEnabled(false);
					spinnerAdd.setEnabled(false);
					panelBase.setBounds(10, 84, 568, 454);
					panelProductosSeleccionados.setEnabled(true);
					panelProductosSeleccionados.setVisible(true);
					((CardLayout) panelBase.getLayout()).show(panelBase,
							"panel1");
				}
			});
			btnPagar.setBounds(10, 420, 89, 23);
		}
		return btnPagar;
	}

	private JButton getBtnSeguirComprando() {
		if (btnSeguirComprando == null) {
			btnSeguirComprando = new JButton("Seguir comprando");
			btnSeguirComprando.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelBase.setBounds(10, 84, 568, 454);
					panelProductosSeleccionados.setEnabled(true);
					panelProductosSeleccionados.setVisible(true);
					((CardLayout) panelBase.getLayout()).show(panelBase,
							"panel2");
				}
			});
			btnSeguirComprando.setBounds(107, 420, 151, 23);
		}
		return btnSeguirComprando;
	}

	private JTextArea getTextAreaConfirma() {
		if (textAreaConfirma == null) {
			textAreaConfirma = new JTextArea();
			textAreaConfirma.setEditable(false);
			textAreaConfirma.setBackground(SystemColor.control);
			textAreaConfirma.setBounds(10, 96, 248, 268);
		}
		return textAreaConfirma;
	}
	private JTextArea getTextAreaNombre() {
		if (textAreaNombre == null) {
			textAreaNombre = new JTextArea();
			textAreaNombre.setEditable(false);
			textAreaNombre.setBackground(SystemColor.menu);
			textAreaNombre.setBounds(263, 96, 248, 268);
		}
		return textAreaNombre;
	}
	private JTextField getTextFieldPrecioFinalConfirmacion() {
		if (textFieldPrecioFinalConfirmacion == null) {
			textFieldPrecioFinalConfirmacion = new JTextField();
			textFieldPrecioFinalConfirmacion.setEditable(false);
			textFieldPrecioFinalConfirmacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldPrecioFinalConfirmacion.setBounds(388, 378, 86, 20);
			textFieldPrecioFinalConfirmacion.setColumns(10);
		}
		return textFieldPrecioFinalConfirmacion;
	}
	private JLabel getLblPrecioTotal_1() {
		if (lblPrecioTotal_1 == null) {
			lblPrecioTotal_1 = new JLabel("Precio total");
			lblPrecioTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPrecioTotal_1.setBounds(289, 368, 108, 37);
		}
		return lblPrecioTotal_1;
	}
	private JPanel getPanelLogin() {
		if (panelLogin == null) {
			panelLogin = new JPanel();
			panelLogin.setBounds(593, 41, 255, 128);
			panelLogin.setLayout(null);
			panelLogin.add(getPanel_1());
			panelLogin.add(getPanel_2());
		}
		return panelLogin;
	}
	private JPanel getPanel_1() {
		if (panelAntesLogin == null) {
			panelAntesLogin = new JPanel();
			panelAntesLogin.setBounds(0, 5, 259, 125);
			panelAntesLogin.setLayout(null);
			panelAntesLogin.add(getLblUsuario());
			panelAntesLogin.add(getLblContrasea());
			panelAntesLogin.add(getBtnRegistrarse());
			panelAntesLogin.add(getBtnIdentificarse());
			panelAntesLogin.add(getTextFieldUsuario());
			panelAntesLogin.add(getTextFieldPass());
		}
		return panelAntesLogin;
	}
	private JPanel getPanel_2() {
		if (panelDespuesLogin == null) {
			panelDespuesLogin = new JPanel();
			panelDespuesLogin.setLayout(null);
			panelDespuesLogin.setBounds(0, 0, 259, 130);
		}
		return panelDespuesLogin;
	}
	private JButton getBtnRegistrarse() {
		if (btnRegistrarse == null) {
			btnRegistrarse = new JButton("Registrarse");
			btnRegistrarse.setBounds(10, 91, 112, 23);
		}
		return btnRegistrarse;
	}
	private JButton getBtnIdentificarse() {
		if (btnIdentificarse == null) {
			btnIdentificarse = new JButton("Identificarse");
			btnIdentificarse.setBounds(140, 91, 109, 23);
		}
		return btnIdentificarse;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblUsuario.setBounds(10, 11, 115, 20);
		}
		return lblUsuario;
	}
	private JTextField getTextFieldUsuario() {
		if (textFieldUsuario == null) {
			textFieldUsuario = new JTextField();
			textFieldUsuario.setBounds(135, 11, 114, 20);
			textFieldUsuario.setColumns(10);
		}
		return textFieldUsuario;
	}
	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblContrasea.setBounds(10, 54, 115, 25);
		}
		return lblContrasea;
	}
	private JTextField getTextFieldPass() {
		if (textFieldPass == null) {
			textFieldPass = new JTextField();
			textFieldPass.setText("");
			textFieldPass.setBounds(135, 54, 114, 20);
			textFieldPass.setColumns(10);
		}
		return textFieldPass;
	}
}