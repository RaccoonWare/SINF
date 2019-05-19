package controlador;
/* Importacion de archivos*/
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.ModeloArticulos;
import modelo.ModeloConsultaInfracciones;
import modelo.ModeloInfraccion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.VistaConsultaInfraccion;
import vista.VistaInfraccion;
import vista.VistaPrincipal;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Clase principal
 * Controlador de eventos de la vista VistaConsultaInfracciones
 * @author Mario Rangel A.
 * @see VistaConsultaInfracciones
 * @see ModeloConsultaInfracciones
 */
public class ControladorConsultaInfracciones implements ActionListener, MouseListener, KeyListener{
	ModeloConsultaInfracciones modeloConsultas = new ModeloConsultaInfracciones();
	VistaConsultaInfraccion vistaConsultas= new VistaConsultaInfraccion();
	JFileChooser selecArchivo = new JFileChooser();
	ModeloInfraccion MI = new ModeloInfraccion();
	static VistaInfraccion VI = new VistaInfraccion();	
	File archivo;
	int contAccion=0;
	//////////////////////Constructores e iniciadores

	/**
	 * Constructor por defecto
	 * @param vistaConsultas
	 * @param modeloConsultas
	 */
	public ControladorConsultaInfracciones(VistaConsultaInfraccion vistaConsultas, ModeloConsultaInfracciones modeloConsultas){
		this.vistaConsultas= vistaConsultas;
		this.modeloConsultas=modeloConsultas;
		this.vistaConsultas.btnConsultar.addActionListener(this);
		this.vistaConsultas.btnAnular.addActionListener(this);
		this.vistaConsultas.btnEliminar.addActionListener(this);
		this.vistaConsultas.btnBuscar.addActionListener(this);
		this.vistaConsultas.cbRegistros.addActionListener(this);

		this.vistaConsultas.txtFolio.addMouseListener(this);
		this.vistaConsultas.txtPlaca.addMouseListener(this);
		this.vistaConsultas.dcFin.addMouseListener(this);
		this.vistaConsultas.dcInicio.addMouseListener(this);
		this.vistaConsultas.cbRegistros.addMouseListener(this);

		this.vistaConsultas.btnConsultar.addMouseListener(this);
		this.vistaConsultas.btnAnular.addMouseListener(this);
		this.vistaConsultas.btnEliminar.addMouseListener(this);
		this.vistaConsultas.btnBuscar.addMouseListener(this);

		this.vistaConsultas.txtFolio.addKeyListener(this);
		this.vistaConsultas.txtPlaca.addKeyListener(this);

		this.vistaConsultas.imEstBarras.addActionListener(this);
	}

	/**
	 * Agregar esta vista a la vista principal
	 * Carga el archivo de configuracion para obtener la propiedad
	 * de la ubicacion del archivo de infracciones
	 * hace una consulta general de los datos de la hoja de Excel
	 * @since 1.0
	 * @see iniciar
	 */
	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(vistaConsultas);
		vistaConsultas.show();
		archivo=new File(MVC.getConfig().getProperty("infracciones"));
		modeloConsultas.Importar(archivo, vistaConsultas.tabla);
	}
	/**
	 * Iniciar las acciones a los eventos
	 * @throws ParseException lanza error por las fechas
	 * @see addActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/**	cargar el archivo con las configuraciones para obtener la propiedad
		 *	correspondiente al directorio donde se almacenan los datos de las
		 *	infracciones
		 */
		UIManager.put("OptionPane.background", MVC.COLOR_BG);
		UIManager.put("Panel.background", MVC.COLOR_BG);
		UIManager.put("Button.background", MVC.COLOR_HIGHLIGHT);
		UIManager.put("OptionPane.messageFont", MVC.FUENTE);
		UIManager.put("OptionPane.messageForeground", MVC.COLOR_HIGHLIGHT);
		UIManager.put("OptionPane.buttonFont", MVC.FUENTE);
		if(e.getSource() == vistaConsultas.btnBuscar | e.getSource() == vistaConsultas.cbRegistros){
			try {
				//Verificar que los campos de fecha no sean nulos para saber que tipo de busqueda vamos a realizar
				//En caso de que no sean nulos se obtiene la fecha
				if (vistaConsultas.dcInicio.getDate()!= null) {
					modeloConsultas.setCal1(vistaConsultas.dcInicio.getDate());
				}else {
					modeloConsultas.setCal1(null);
				}
				if (vistaConsultas.dcFin.getDate()!= null) {
					modeloConsultas.setCal2(vistaConsultas.dcFin.getDate());
				}else {
					modeloConsultas.setCal2(null);
				}
				/**
				 * Se manda la ruta del archivo
				 * La tabla onde se almacenaran los datos del archivo
				 * Los parametrso de busqueda
				 * * Folio
				 * * Placas
				 * * Estado de los registros
				 */
				modeloConsultas.ImportarEspesifico(
						archivo, 
						vistaConsultas.tabla, 
						vistaConsultas.txtFolio.getText().trim(), 
						vistaConsultas.txtPlaca.getText().trim(),
						(String)vistaConsultas.cbRegistros.getSelectedItem());
			} catch (ParseException e1) {
				// TODO Bloque catch generado automï¿½ticamente
				e1.printStackTrace();
			}
			/**
			 * accion del boton de consultar que nos va a permitir
			 * modificar los datos
			 */
		}if (e.getSource()==vistaConsultas.btnConsultar) {
			try {

				if (vistaConsultas.dcInicio.getDate()!= null && vistaConsultas.dcFin.getDate()!= null) {
					modeloConsultas.setCal1(vistaConsultas.dcInicio.getDate());
					modeloConsultas.setCal2(vistaConsultas.dcFin.getDate());
				}else {
					modeloConsultas.setCal1(null);
					modeloConsultas.setCal2(null);
				}
				if (vistaConsultas.tabla.getSelectedRow()!=-1) {
					VI.btnInsertar.setText("Actualizar");
					VI.txtNboleta.setEditable(false);
					ControladorInfraccion CI= new ControladorInfraccion(VI, MI);
					CI.iniciar();
					Object[] datos= modeloConsultas.ImportarParaModificar(
							archivo, 
							vistaConsultas.tabla, 
							String.valueOf(vistaConsultas.tabla.getValueAt(vistaConsultas.tabla.getSelectedRow(),0)));
					establecerdatos(datos);
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
				}


			} catch (ParseException e1) {
				// TODO Bloque catch generado automï¿½ticamente
				e1.printStackTrace();
			}
		}if (e.getSource()==vistaConsultas.btnEliminar) {
			if (vistaConsultas.tabla.getSelectedRow()!=-1) {
				DefaultTableModel modelo = (DefaultTableModel) vistaConsultas.tabla.getModel();
				String dato = String.valueOf(vistaConsultas.tabla.getValueAt(vistaConsultas.tabla.getSelectedRow(), 0));

				int n = JOptionPane.showConfirmDialog(vistaConsultas, "Ha seleccionado el registro: " + dato +"\r\n"+
						"Esta seguro que desea eliminarlo.\r\n", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
				if (n==JOptionPane.YES_OPTION) {
					modelo.removeRow(vistaConsultas.tabla.getSelectedRow());
					vistaConsultas.tabla.setModel(modelo);
					modeloConsultas.Exportar(archivo, vistaConsultas.tabla);
				}else {
					JOptionPane.showMessageDialog(vistaConsultas, "Acción cancelada");
				}}else {
					JOptionPane.showMessageDialog(vistaConsultas, "Seleccione una fila", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
				}

		}if (e.getSource()==vistaConsultas.imEstBarras) {
			Reporte();
		}if (e.getSource()== vistaConsultas.btnAnular) {
			if (vistaConsultas.tabla.getSelectedRow()!=-1) {
				DefaultTableModel modelo = (DefaultTableModel) vistaConsultas.tabla.getModel();
				String dato = (String) vistaConsultas.tabla.getValueAt(vistaConsultas.tabla.getSelectedRow(), 26);
				if (dato.equals("AN")) {
					modelo.setValueAt("AC", vistaConsultas.tabla.getSelectedRow(), 26);
				}else {
					modelo.setValueAt("AN", vistaConsultas.tabla.getSelectedRow(), 26);
				}
				vistaConsultas.tabla.setModel(modelo);
				modeloConsultas.Exportar(archivo, vistaConsultas.tabla);
			}else {
				JOptionPane.showMessageDialog(vistaConsultas, "Seleccione una fila", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == vistaConsultas.txtFolio){
			vistaConsultas.txtFolio.setBackground(new Color(234, 253, 255));			
		}else if (e.getSource() == vistaConsultas.txtPlaca) {
			vistaConsultas.txtPlaca.setBackground(new Color(234, 253, 255));
		}else if(e.getSource() == vistaConsultas.btnEliminar){
			vistaConsultas.btnEliminar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if(e.getSource() == vistaConsultas.btnAnular){
			vistaConsultas.btnAnular.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if(e.getSource() == vistaConsultas.btnConsultar){
			vistaConsultas.btnConsultar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if(e.getSource() == vistaConsultas.btnBuscar){
			vistaConsultas.btnBuscar.setBorder(new LineBorder(new Color(65,122,127), 3, true));
		}else if (e.getSource() == vistaConsultas.cbRegistros) {
			vistaConsultas.cbRegistros.setBackground(new Color(234, 253, 255));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if((e.getSource() == vistaConsultas.txtFolio)){
			vistaConsultas.txtFolio.setBackground(new Color(187,202,204));
		}else if (e.getSource() == vistaConsultas.txtPlaca) {
			vistaConsultas.txtPlaca.setBackground(new Color(187,202,204));
		}else if (e.getSource() == vistaConsultas.dcFin) {
			vistaConsultas.dcFin.setBackground(new Color(187,202,204));
			vistaConsultas.dcFin.setBorder(null);
		}else if (e.getSource() == vistaConsultas.dcInicio) {
			vistaConsultas.dcInicio.setBackground(new Color(187,202,204));
			vistaConsultas.dcInicio.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnAnular) {
			vistaConsultas.btnAnular.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnBuscar) {
			vistaConsultas.btnBuscar.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnConsultar) {
			vistaConsultas.btnConsultar.setBorder(null);
		}else if (e.getSource() == vistaConsultas.btnEliminar) {
			vistaConsultas.btnEliminar.setBorder(null);
		}else if (e.getSource() == vistaConsultas.cbRegistros) {
			vistaConsultas.cbRegistros.setBackground(new Color(187,202,204));
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Apï¿½ndice de mï¿½todo generado automï¿½ticamente

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Apï¿½ndice de mï¿½todo generado automï¿½ticamente

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Apï¿½ndice de mï¿½todo generado automï¿½ticamente

	}
	/**
	 * Se usa para capturar las acciones del teclado
	 * esto para que se este actualizando la busqueda
	 * conforme se escribe sobre el campo de texto
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		try {
			modeloConsultas.setCal1(vistaConsultas.dcInicio.getDate());
			modeloConsultas.setCal2(vistaConsultas.dcFin.getDate());
			modeloConsultas.ImportarEspesifico(archivo, vistaConsultas.tabla, 
					vistaConsultas.txtFolio.getText().trim(), 
					vistaConsultas.txtPlaca.getText().trim(),
					(String)vistaConsultas.cbRegistros.getSelectedItem());
		} catch (ParseException e1) {
			// TODO Bloque catch generado automï¿½ticamente
			e1.printStackTrace();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Apï¿½ndice de mï¿½todo generado automï¿½ticamente

	}
	/**
	 * Metodo que manda los datos a la vista VistaInfracciones
	 * para poder modificarlos
	 * @param datos
	 */
	public void establecerdatos(Object[] datos) {
	        Calendar fecha = new GregorianCalendar(
	        		Integer.valueOf(String.valueOf(datos[3])), 
	        		Integer.valueOf(String.valueOf(datos[2]))-1, 
	        		Integer.valueOf(String.valueOf(datos[1])));
	        
		VI.txtNboleta.setText(datos[0].toString());
		VI.dcFecha.setCalendar(fecha);;
		VI.timeChooser.setText((String)datos[4]);
		VI.cbMunicipio.setSelectedItem(datos[5].toString().split(", ")[0]);
		VI.txtEstado.setText(datos[5].toString().split(", ")[1]);
		VI.txtReferencias.setText((String)datos[6]);
		VI.cbInfraccion.setSelectedItem(datos[7]);
		VI.txtNplacas.setText((String)datos[8]);
		VI.txtPlacasEstado.setText((String)datos[9]);
		VI.txtMarca.setText((String)datos[10]);
		VI.txtModelo.setText((String)datos[11]);
		VI.txtNserie.setText((String)datos[12]);
		VI.txtNeconomico.setText((String)datos[13]);
		VI.txtRutaSitio.setText((String)datos[14]);
		VI.txtColor.setText((String)datos[15]);
		VI.txtNombreConductor.setText((String)datos[16]);
		VI.txtNombrePropietario.setText((String)datos[17]);
		VI.txtDomicilioConductor.setText((String)datos[18]);
		VI.txtDomicilioPropietario.setText((String)datos[19]);
		VI.txtNlicenciaConductor.setText((String)datos[20]);
		VI.txtArticulosViolados.setText((String)datos[21]);
		VI.txtRetencion.setText((String)datos[22]);
		VI.txtMarcaModelo.setText((String)datos[23]);
		VI.txtMotivo.setText((String)datos[24]);
		VI.txtNpolicia.setText((String)datos[25]);
		
	}
	/**
	 * Genera el reporte en base a los datos que se muestran
	 * en la tabla de la vista VistaConsultaInfraccinoes
	 */
	public void Reporte() {
		int cantidad=1;
		//Collection<?> data = new Collection[vistaConsultas.tabla.getRowCount()][0];
		DefaultTableModel modelo;
		//VARIABLE PARA CONTAR CUANTAS REPETICIONES HAY DE CADA ARTICULO
		ArrayList<Object> Lista = new ArrayList<Object>();
		//LISTA CON EL NOMBRE DEL ARTICULO Y LA CANTIDAD DE REPETICIONES ENCONTRADAS
		for (int i=0; i<vistaConsultas.tabla.getRowCount(); i++) {
			//CICLO PARA GUARDAR LOS NOMBRES DE LOS ARTICULOS
			for (int j=1; j<vistaConsultas.tabla.getRowCount(); j++) {
				//CICLO PARA GUARDAR LA CANTIDAD DE VECES QUE SE REPITE UN ARTICULO
				String dato1=(String)vistaConsultas.tabla.getValueAt(j, 21);
				String dato2=(String)vistaConsultas.tabla.getValueAt(i, 21);
				if (dato1.equals(dato2)) {
					modelo = (DefaultTableModel) vistaConsultas.tabla.getModel();
					modelo.removeRow(j);
					vistaConsultas.tabla.setModel(modelo);
					cantidad++;
					j=0;
				}
			}
			modelo = (DefaultTableModel) vistaConsultas.tabla.getModel();
			//GUARDAMOS LA CANTIDAD Y EL NOMBRE DEL ARTICULO PARA DESPUES ASIGNARLO A LA LISSTA				
			ModeloArticulos art	 = new ModeloArticulos(cantidad, (String)vistaConsultas.tabla.getValueAt(i, 21));
			int nFilas=vistaConsultas.tabla.getRowCount();
			if (nFilas!=1) {
				modelo.removeRow(i);
			}else {
				Lista.add(art);
				break;
			}
			vistaConsultas.tabla.setModel(modelo);
			i=-1;	
			Lista.add(art);
			cantidad=1;	
		}
		//JOptionPane.showMessageDialog(null,Lista);
		try {
			String master = "C:\\Users\\m4rio\\eclipse-workspace\\SINF\\src\\datos\\estadisticas.jasper";//paquete donde esta el jasper compilado
			JasperReport masterReport = null;
			JasperPrint jasperPrint;
			try{
				masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);
				jasperPrint = JasperFillManager.fillReport(masterReport, null, new JRBeanCollectionDataSource(Lista));
				JasperViewer.viewReport(jasperPrint, false);
				modeloConsultas.Importar(archivo, vistaConsultas.tabla);
			}catch (JRException e){
				JOptionPane.showMessageDialog(null,"Error cargando el reporte maestro: " + e.getMessage());
			} 
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}