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

import logica.Bulto;
import logica.Producto;

public class Lector extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_BotonesDefecto;
	private JPanel panel_Centro;
	private JButton bt_Cancelar;
	private JButton bt_Continuar;
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
	
	private Bulto productos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lector dialog = new Lector(new Bulto(new Producto("a","bbb"),5));
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
		setBounds(100, 100, 300, 250);
		getContentPane().add(getPanel_Centro(), BorderLayout.CENTER);
		getContentPane().add(getPanel_BotonesDefecto(), BorderLayout.SOUTH);
		productos = bultoARecoger;
		lb_InformacionProducto.setText("Producto: "+productos.getProducto().getOrder_product_name()
				+"- "+productos.getCantidad()+" unidades");

	}
	private JPanel getPanel_BotonesDefecto() {
		if (panel_BotonesDefecto == null) {
			panel_BotonesDefecto = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_BotonesDefecto.getLayout();
			flowLayout.setHgap(10);
			panel_BotonesDefecto.add(getBt_Continuar());
			panel_BotonesDefecto.add(getBt_Cancelar());
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
	private JButton getBt_Cancelar() {
		if (bt_Cancelar == null) {
			bt_Cancelar = new JButton("Cancelar");
			bt_Cancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					salir();
				}
			});
		}
		return bt_Cancelar;
	}
	
	private void salir()
	{
		this.dispose();
	}
	
	private JButton getBt_Continuar() {
		if (bt_Continuar == null) {
			bt_Continuar = new JButton("Continuar");
			bt_Continuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(txF_CodBarras.getText().equals(productos.getProducto().getOrder_product_code()))
					{
						txA_InfProd.setText("Producto recogido");
					}else{
						txA_InfProd.setText("Error al recoger, código erroneo");
					}
				}
			});
		}
		return bt_Continuar;
	}
	private JTextArea getTxA_Informacion() {
		if (txA_Informacion == null) {
			txA_Informacion = new JTextArea();
			txA_Informacion.setEditable(false);
			txA_Informacion.setText("Introduzca el c\u00F3digo de barras a continuaci\u00F3n para verificar la recogida del producto");
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
					if(Integer.valueOf(txF_Cantidad.getText())>1)
						txF_Cantidad.setText((Integer.valueOf(txF_Cantidad.getText())-1)+"");
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
					if(Integer.valueOf(txF_Cantidad.getText())<productos.getCantidad())
					txF_Cantidad.setText((Integer.valueOf(txF_Cantidad.getText())+1)+"");
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
}
