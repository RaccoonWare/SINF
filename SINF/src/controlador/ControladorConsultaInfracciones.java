package controlador;
/* Importacion de archivos*/
import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import modelo.ModeloConsultaInfracciones;
import modelo.ModeloInfraccion;
import vista.VistaConsultaInfraccion;
import vista.VistaInfraccion;
import vista.VistaPrincipal;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;
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
		this.vistaConsultas.imEstLineas.addActionListener(this);
		this.vistaConsultas.imEstPastel.addActionListener(this);
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
		modeloConsultas.setTablaD(vistaConsultas.tabla);
		archivo=MVC.INFRACCIONES;
		modeloConsultas.setAccion("Importar");
		modeloConsultas.start();
	
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
					modeloConsultas = new ModeloConsultaInfracciones();
					modeloConsultas.setTablaD(vistaConsultas.tabla);
					modeloConsultas.setAccion("Exportar");
					modeloConsultas.start();
				}else {
					JOptionPane.showMessageDialog(vistaConsultas, "Acción cancelada");
				}}else {
					JOptionPane.showMessageDialog(vistaConsultas, "Seleccione una fila", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
				}

		}if (e.getSource()==vistaConsultas.imEstBarras) {
			writeChartToPDF("Barra");
			modeloConsultas.Importar();

		}if (e.getSource()==vistaConsultas.imEstLineas) {
			writeChartToPDF("Linea");
			modeloConsultas.Importar();

		}if (e.getSource()==vistaConsultas.imEstPastel) {
			writeChartToPDF("Pastel");
			modeloConsultas.Importar();

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
				modeloConsultas = new ModeloConsultaInfracciones();
				modeloConsultas.setTablaD(vistaConsultas.tabla);
				modeloConsultas.setAccion("Exportar");
				modeloConsultas.start();
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
		// TODO Apóndice de método generado automáticamente

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Apéndice de método generado automáticamente

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
			modeloConsultas.ImportarEspesifico(vistaConsultas.tabla, 
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
	 * @return 
	 */
	public DefaultCategoryDataset datosReporteBaLi() {
		int cantidad=1;
		JTable tabla = vistaConsultas.tabla;
		//Collection<?> data = new Collection[vistaConsultas.tabla.getRowCount()][0];
		DefaultTableModel modelo;
		//VARIABLE PARA CONTAR CUANTAS REPETICIONES HAY DE CADA ARTICULO
		DefaultCategoryDataset datos = new DefaultCategoryDataset();
		//LISTA CON EL NOMBRE DEL ARTICULO Y LA CANTIDAD DE REPETICIONES ENCONTRADAS
		for (int i=0; i<tabla.getRowCount(); i++) {
			//CICLO PARA GUARDAR LOS NOMBRES DE LOS ARTICULOS
			for (int j=1; j<tabla.getRowCount(); j++) {
				//CICLO PARA GUARDAR LA CANTIDAD DE VECES QUE SE REPITE UN ARTICULO
				String dato1=(String)tabla.getValueAt(j, 21);
				String dato2=(String)tabla.getValueAt(i, 21);
				if (dato1.equals(dato2)) {
					modelo = (DefaultTableModel) tabla.getModel();
					modelo.removeRow(j);
					tabla.setModel(modelo);
					cantidad++;
					j=0;
				}
			}
			modelo = (DefaultTableModel) tabla.getModel();
			//GUARDAMOS LA CANTIDAD Y EL NOMBRE DEL ARTICULO PARA DESPUES ASIGNARLO A LA LISSTA				
			String nomobreArticulo = (String)tabla.getValueAt(i, 21);
			int nFilas=tabla.getRowCount();
			if (nFilas!=1) {
				modelo.removeRow(i);
			}else {
				datos.addValue(cantidad,"Cantidad de infracciones por articulo", nomobreArticulo);
				break;
			}
			tabla.setModel(modelo);
			i=-1;	
			datos.addValue(cantidad,"Cantidad de infracciones por articulo",nomobreArticulo);
			cantidad=1;	
		}
		return datos;
	}

	/**
	 * Creamos los datos para la grfica
	 * son de tipo PieDataset por que sera
	 * grafica de pastel
	 * @return
	 */
	public PieDataset datosReportePastel() {
		int cantidad=1;
		JTable tabla = vistaConsultas.tabla;
		//Collection<?> data = new Collection[vistaConsultas.tabla.getRowCount()][0];
		DefaultTableModel modelo;
		//VARIABLE PARA CONTAR CUANTAS REPETICIONES HAY DE CADA ARTICULO
		DefaultPieDataset datos = new DefaultPieDataset();
		//LISTA CON EL NOMBRE DEL ARTICULO Y LA CANTIDAD DE REPETICIONES ENCONTRADAS
		for (int i=0; i<tabla.getRowCount(); i++) {
			//CICLO PARA GUARDAR LOS NOMBRES DE LOS ARTICULOS
			for (int j=1; j<tabla.getRowCount(); j++) {
				//CICLO PARA GUARDAR LA CANTIDAD DE VECES QUE SE REPITE UN ARTICULO
				String dato1=(String)tabla.getValueAt(j, 21);
				String dato2=(String)tabla.getValueAt(i, 21);
				if (dato1.equals(dato2)) {
					modelo = (DefaultTableModel) tabla.getModel();
					modelo.removeRow(j);
					tabla.setModel(modelo);
					cantidad++;
					j=0;
				}
			}
			modelo = (DefaultTableModel) tabla.getModel();
			//GUARDAMOS LA CANTIDAD Y EL NOMBRE DEL ARTICULO PARA DESPUES ASIGNARLO A LA LISSTA				
			String nomobreArticulo = (String)tabla.getValueAt(i, 21);
			int nFilas=tabla.getRowCount();
			if (nFilas!=1) {
				modelo.removeRow(i);
			}else {
				datos.setValue(nomobreArticulo, cantidad);
				break;
			}
			tabla.setModel(modelo);
			i=-1;	
			datos.setValue(nomobreArticulo, cantidad);
			cantidad=1;	
		}
		return datos;
	}


	public void writeChartToPDF(String tipoReporte) {
		PDFDocument pdfDoc = new PDFDocument();
		JFreeChart grafica= null;

		switch (tipoReporte) {
		case "Barra":
			grafica = ChartFactory.createBarChart(
					"Estadistica de infracciones",
					"Nombre de los articulos",
					"Numero de infracciones",
					datosReporteBaLi(),
					PlotOrientation.VERTICAL, true, true, false);
			break;
		case "Linea":
			grafica = ChartFactory.createLineChart(
					"Estadistica de infracciones",
					"Nombre de los articulos",
					"Numero de infracciones",
					datosReporteBaLi(),
					PlotOrientation.VERTICAL, true, true, false);
			break;
		case "Pastel":
			grafica = ChartFactory.createPieChart(
					"Estadistica de Infracciones",
					datosReportePastel(),
					true, true, false);
			break;
		}
		pdfDoc.setTitle("Grafico de infracciones");
		//Tamaño de una hoja tipo carta 11x5.5 pulgadas = 792 x 612 pixeles
		Page page = pdfDoc.createPage(new Rectangle(792, 612));
		PDFGraphics2D g2 = page.getGraphics2D();
		grafica.draw(g2, new Rectangle(20, 20, 772, 592));
		File archivo = modeloConsultas.establecerArchivo((Container)ControladorPrincipal.VI);
		try{ 
			if (archivo!=null) {
				pdfDoc.writeToFile(archivo);
				//definiendo la ruta en la propiedad file
				Runtime.getRuntime().exec("cmd /c start "+archivo);
			}else {
				JOptionPane.showMessageDialog(vistaConsultas, 
						(Object)"Oops!, Operación cancelada",
						"Cancelado", JOptionPane.OK_CANCEL_OPTION);
			}
		}catch(IOException e){
			JOptionPane.showMessageDialog(vistaConsultas, 
					(Object)"El archivo se guardo correctamente ",
					"Guardado", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}