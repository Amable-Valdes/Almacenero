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

public class Lector extends JDialog {
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lector dialog = new Lector();
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
	public Lector() {
		setBounds(100, 100, 300, 296);
		getContentPane().add(getPanel_Centro(), BorderLayout.CENTER);
		getContentPane().add(getPanel_BotonesDefecto(), BorderLayout.SOUTH);

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
		}
		return bt_Cancelar;
	}
	private JButton getBt_Continuar() {
		if (bt_Continuar == null) {
			bt_Continuar = new JButton("Continuar");
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
			panel_InfProd.add(getPanel_CodigoBarras());
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
			txA_InfProd.setText("Si CodBarras == true {Producto recogido} else {error al recoger, boton continuar a no-enabled o se cambia el texto a \"salir\"}");
			txA_InfProd.setWrapStyleWord(true);
			txA_InfProd.setLineWrap(true);
		}
		return txA_InfProd;
	}
}
