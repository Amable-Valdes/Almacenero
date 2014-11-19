package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.GridLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;

import logica.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.SwingConstants;

public class InformacionProducto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_Informacion;
	private JLabel lbIDPed;
	private JLabel lbIDPedInf;
	private JPanel panel_Avanzar;
	private JButton btSiguiente;
	private JButton btAtras;
	private JPanel panel_Imagenes;
	private JLabel lbNombreInf;
	private JLabel lbCodigo;
	private JLabel lbCodigoInf;
	private JLabel lbPasillo;
	private JLabel lbPasilloInf;
	private JLabel lbEstanteria;
	private JLabel lbEstanteriaInf;
	private JLabel lbAltura;
	private JLabel lbAlturaInf;
	private JLabel lbImagenProducto;
	private JButton btSalir;
	private JPanel panel_Salir;
	private JPanel panel_Opciones;
	private JPanel panel_Datos;
	private JPanel panel_Borrar;
	private JButton btRecogido;
	

	private List<Producto> listaProductos;
	private int productoSeleccionado = 0;
	
	private Main ventanaPrincipal;
	private JScrollPane scrollPane;
	private JLabel lbDimensiones;
	private JLabel lbDimensionesInf;
	private JLabel lbPeso;
	private JLabel lbPesoInf;
	/**
	 * Create the dialog.
	 */
	public InformacionProducto(Main m,List<Producto> _listaProductos) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(m);
		setDefaultCloseOperation(0);
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setSize(300,300);
		getContentPane().add(getPanel_Avanzar(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_Datos(), BorderLayout.CENTER);
		
		ventanaPrincipal = m;
		listaProductos = _listaProductos;
		Collections.sort(listaProductos);
		cargarInformacionProducto();
		cargarBotones();
	}
	
	private void cargarInformacionProducto()
	{
		
		lbIDPedInf.setText(""+listaProductos.get(productoSeleccionado).getOrder_id());
		lbNombreInf.setText(""+listaProductos.get(productoSeleccionado).getOrder_product_name());
		lbCodigoInf.setText(""+listaProductos.get(productoSeleccionado).getOrder_product_code());
		lbPasilloInf.setText(""+listaProductos.get(productoSeleccionado).getLocation().split("-")[0]);
		lbEstanteriaInf.setText(""+listaProductos.get(productoSeleccionado).getLocation().split("-")[1]);
		lbAlturaInf.setText(""+listaProductos.get(productoSeleccionado).getLocation().split("-")[2]);
		lbDimensionesInf.setText(""+listaProductos.get(productoSeleccionado).getAncho() + "m x " +
				listaProductos.get(productoSeleccionado).getAlto() + "m x " +
				listaProductos.get(productoSeleccionado).getLargo() + "m");
		lbPesoInf.setText(""+listaProductos.get(productoSeleccionado).getPeso()+" kg");
		
		if(listaProductos.get(productoSeleccionado).getEstado_producto()==Producto.RECOGIDO)
		{
			btRecogido.setEnabled(false);
			btRecogido.setText("Ya Recogido");
		}
		else{
			btRecogido.setEnabled(true);
			btRecogido.setText("Producto Recogido");
		}
	}
	
	private JPanel getPanel_Informacion() {
		if (panel_Informacion == null) {
			panel_Informacion = new JPanel();
			panel_Informacion.setLayout(new GridLayout(0, 2, 0, 0));
			panel_Informacion.add(getLbIDPed());
			panel_Informacion.add(getLbIDPedInf());
			panel_Informacion.add(getLbCodigo());
			panel_Informacion.add(getLbCodigoInf());
			panel_Informacion.add(getLbPasillo());
			panel_Informacion.add(getLbPasilloInf());
			panel_Informacion.add(getLbEstanteria());
			panel_Informacion.add(getLbEstanteriaInf());
			panel_Informacion.add(getLbAltura());
			panel_Informacion.add(getLbAlturaInf());
			panel_Informacion.add(getLbDimensiones());
			panel_Informacion.add(getLbDimensionesInf());
			panel_Informacion.add(getLbPeso());
			panel_Informacion.add(getLbPesoInf());
		}
		return panel_Informacion;
	}
	private JLabel getLbIDPed() {
		if (lbIDPed == null) {
			lbIDPed = new JLabel("ID Pedido:");
		}
		return lbIDPed;
	}
	private JLabel getLbIDPedInf() {
		if (lbIDPedInf == null) {
			lbIDPedInf = new JLabel();
		}
		return lbIDPedInf;
	}
	private JPanel getPanel_Avanzar() {
		if (panel_Avanzar == null) {
			panel_Avanzar = new JPanel();
			panel_Avanzar.setLayout(new BorderLayout(5, 5));
			panel_Avanzar.add(getPanel_Opciones(), BorderLayout.CENTER);
			panel_Avanzar.add(getPanel_Salir(), BorderLayout.EAST);
		}
		return panel_Avanzar;
	}
	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("Siguiente");
			btSiguiente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(productoSeleccionado<listaProductos.size()-1)
					{
						productoSeleccionado++;
					}
					cargarInformacionProducto();
					
					cargarBotones();
				}
			});
		}
		return btSiguiente;
	}
	private JButton getBtAtras() {
		if (btAtras == null) {
			btAtras = new JButton("Atras");
			btAtras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(productoSeleccionado>0)
					{
						productoSeleccionado--;
					}
					cargarInformacionProducto();
					
					cargarBotones();
				}
			});
		}
		return btAtras;
	}
	
	private void cargarBotones()
	{
		btSiguiente.setEnabled(true);
		btAtras.setEnabled(true);
		
		if(productoSeleccionado == 0)
		{
			btSiguiente.setEnabled(true);
			btAtras.setEnabled(false);
		}
		if(productoSeleccionado == (listaProductos.size()-1)){
			btSiguiente.setEnabled(false);
			btAtras.setEnabled(true);
		}
		
		if(productoSeleccionado == (listaProductos.size()-1) && productoSeleccionado == 0){
			btSiguiente.setEnabled(false);
			btAtras.setEnabled(false);
		}
	}
	
	private JPanel getPanel_Imagenes() {
		if (panel_Imagenes == null) {
			panel_Imagenes = new JPanel();
			panel_Imagenes.setLayout(new GridLayout(0, 2, 0, 0));
			panel_Imagenes.add(getLbImagenProducto());
		}
		return panel_Imagenes;
	}
	private JLabel getLbNombreInf() {
		if (lbNombreInf == null) {
			lbNombreInf = new JLabel("");
			lbNombreInf.setHorizontalAlignment(SwingConstants.CENTER);
			lbNombreInf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		}
		return lbNombreInf;
	}
	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("C\u00F3digo Producto:");
		}
		return lbCodigo;
	}
	private JLabel getLbCodigoInf() {
		if (lbCodigoInf == null) {
			lbCodigoInf = new JLabel("");
		}
		return lbCodigoInf;
	}
	private JLabel getLbPasillo() {
		if (lbPasillo == null) {
			lbPasillo = new JLabel("Pasillo Producto");
		}
		return lbPasillo;
	}
	private JLabel getLbPasilloInf() {
		if (lbPasilloInf == null) {
			lbPasilloInf = new JLabel("");
		}
		return lbPasilloInf;
	}
	private JLabel getLbEstanteria() {
		if (lbEstanteria == null) {
			lbEstanteria = new JLabel("Estanter\u00EDa Producto");
		}
		return lbEstanteria;
	}
	private JLabel getLbEstanteriaInf() {
		if (lbEstanteriaInf == null) {
			lbEstanteriaInf = new JLabel("");
		}
		return lbEstanteriaInf;
	}
	private JLabel getLbAltura() {
		if (lbAltura == null) {
			lbAltura = new JLabel("Altura Producto");
		}
		return lbAltura;
	}
	private JLabel getLbAlturaInf() {
		if (lbAlturaInf == null) {
			lbAlturaInf = new JLabel("");
		}
		return lbAlturaInf;
	}
	private JLabel getLbImagenProducto() {
		if (lbImagenProducto == null) {
			lbImagenProducto = new JLabel("");
		}
		return lbImagenProducto;
	}
	private JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton("Volver");
			btSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btSalir;
	}
	private JPanel getPanel_Salir() {
		if (panel_Salir == null) {
			panel_Salir = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_Salir.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel_Salir.add(getBtSalir());
		}
		return panel_Salir;
	}
	private JPanel getPanel_Opciones() {
		if (panel_Opciones == null) {
			panel_Opciones = new JPanel();
			panel_Opciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_Opciones.add(getBtSiguiente());
			panel_Opciones.add(getBtAtras());
		}
		return panel_Opciones;
	}
	private JPanel getPanel_Datos() {
		if (panel_Datos == null) {
			panel_Datos = new JPanel();
			panel_Datos.setLayout(new BorderLayout(5, 5));
			panel_Datos.add(getLbNombreInf(), BorderLayout.NORTH);
			panel_Datos.add(getPanel_Borrar(), BorderLayout.SOUTH);
			panel_Datos.add(getPanel_Imagenes(), BorderLayout.EAST);
			panel_Datos.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel_Datos;
	}
	private JPanel getPanel_Borrar() {
		if (panel_Borrar == null) {
			panel_Borrar = new JPanel();
			panel_Borrar.add(getBtRecogido());
		}
		return panel_Borrar;
	}
	private JButton getBtRecogido() {
		if (btRecogido == null) {
			btRecogido = new JButton("Producto Recogido");
			btRecogido.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ventanaPrincipal.MarcarDatoLista(listaProductos.get(productoSeleccionado));
					
					if(listaProductos.get(productoSeleccionado).getEstado_producto()==Producto.RECOGIDO)
					{
						btRecogido.setEnabled(false);
						btRecogido.setText("Ya Recogido");
					}
					else{
						btRecogido.setEnabled(true);
						btRecogido.setText("Producto Recogido");
					}
				}
			});
		}
		return btRecogido;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getPanel_Informacion());
		}
		return scrollPane;
	}
	private JLabel getLbDimensiones() {
		if (lbDimensiones == null) {
			lbDimensiones = new JLabel("Dimensiones:");
		}
		return lbDimensiones;
	}
	private JLabel getLbDimensionesInf() {
		if (lbDimensionesInf == null) {
			lbDimensionesInf = new JLabel("");
		}
		return lbDimensionesInf;
	}
	private JLabel getLbPeso() {
		if (lbPeso == null) {
			lbPeso = new JLabel("Peso:");
		}
		return lbPeso;
	}
	private JLabel getLbPesoInf() {
		if (lbPesoInf == null) {
			lbPesoInf = new JLabel("");
		}
		return lbPesoInf;
	}
}
