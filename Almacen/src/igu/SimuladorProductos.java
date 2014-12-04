package igu;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logica.Bulto;
import logica.Gestor;
import logica.Pedido;
import logica.Producto;


public class SimuladorProductos extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private List<Bulto> listaProductos;
	private ModeloNoEditable modeloTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimuladorProductos frame = new SimuladorProductos();
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
	public SimuladorProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		setTitle("PDA del almacenero");
	}

	private void cargarProductos() {
		Gestor g = new Gestor();
		//g.generaPedidos();
		//listaProductos = g.getPedidos().get(0).getProductos();
		List<Producto> listaProductosPrueba = new ArrayList<Producto>();
		int estado = Producto.RECOGIDO;
		listaProductosPrueba.add(new Producto(1, "Producto almacén1", "A-301",
				estado));
		listaProductosPrueba.add(new Producto(2, "Producto almacén2", "A-302",
				estado));
		listaProductosPrueba.add(new Producto(3, "Producto almacén3", "A-303",
				estado));
		listaProductosPrueba.add(new Producto(4, "Producto almacén4", "A-304",
				estado));
		listaProductosPrueba.add(new Producto(4, "Producto almacén4", "A-304",
				estado));
		
		Pedido pedido = new Pedido();
		pedido.setProductos(listaProductosPrueba);
		
		listaProductos = pedido.getBultos();
		
		añadirDatosTablaProductos();
	}
	
	private void añadirDatosTablaProductos() {
		modeloTabla.getDataVector().clear();
		modeloTabla.fireTableDataChanged();
		Object[] nuevaFila = new Object[3];
		for (int i = 0; i < listaProductos.size(); i++) {
			nuevaFila[0] = listaProductos.get(i).getProductos().get(0).getOrder_product_name();
			nuevaFila[1] = listaProductos.get(i).getProductos().get(0).getOrder_product_code();
			switch (listaProductos.get(i).getProductos().get(0).getEstado_producto()) {
			case Producto.POR_RECOGER:
				nuevaFila[2] = "Por recoger, en estantería";
				break;
				
			case Producto.RECOGIDO:
				nuevaFila[2] = "En el carrito";
				break;
				
			case Producto.EMPAQUETADO:
				nuevaFila[2] = "Empaquetado";
				break;

			default:
				break;
			}
			
			modeloTabla.addRow(nuevaFila);
		}
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			String[] nombreColumnas = { "Nombre", "Código", "Estado" };
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			table = new JTable(modeloTabla);
			cargarProductos();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (table.getSelectedRow() != -1) {
						new Lector(listaProductos.get(table.getSelectedRow())).setVisible(true);
					} else {
					
					}
				}
			});
		}
		return table;
	}
}
