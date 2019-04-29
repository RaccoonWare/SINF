/**
 * @author Mario
 */
package modelo;
/* importación de librerias*/
import org.apache.poi.ss.usermodel.*;
import exepciones.*;
import vista.VistaInfraccion;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

/**
 * Clase principal
 * @author Mario
 * @see VistaInfraccion
 * @see ControladorInfraccion
 */
public class ModeloInfraccion{
	//Variables de instancia
	Workbook wb;
	private String Estado;
	private String Nplacas;
	private String Nserie;
	private String Modelo;
	private String Referencias;
	private String PlacasEstado;
	private String ArticulosViolados;
	private String Npolicia;
	private String Marca;
	private String Neconomico;
	private String RutaSitio;
	private String Color;
	private String NombreConductor;
	private String DomicilioConductor;
	private String NlicenciaConductor;
	private String NombrePropietario;
	private String DomicilioPropietario;
	private String MarcaModelo;
	private String Retencion;
	private String Motivo;
	private String Infraccion;
	private String Municipio;
	private String Fecha;
	private JTable tabla;
	private String hora;
	private String Nboleta;
	private String estatus="AC";//Estado de la multa, AC = activo AN = anulado

	///////////////Accesores y Modificadores	
	/**
	 * 
	 * @return
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * 
	 * @param estatus
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * 
	 * @return
	 */
	public String getNboleta() {
		return Nboleta;
	}
	/**
	 * modifica el numero de la boleta
	 * @param nboleta numero de la boleta
	 * @throws Exception exceppcion generica
	 * @throws ErroresCaptura//en caso que el formato sea incorrecto
	 */
	public void setNboleta(String nboleta) throws Exception , ErroresCaptura{
			//variables de función
			Pattern pattern = Pattern.compile("^[0-9]+$");
			Matcher mather = pattern.matcher(nboleta);
			
			if (mather.find() == true) {
				Nboleta = nboleta;
			} else {
				throw new ErroresCaptura(1);
			}	
	}
	/**
	 * 
	 * @return
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * 
	 * @param hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	/**
	 * 
	 * @return
	 */
	public String getEstado() {
		return Estado;
	}
	/**
	 * 
	 * @param estado
	 */
	public void setEstado(String estado) {
		Estado = estado;
	}
	/**
	 * 
	 * @return
	 */
	public String getNplacas() {
		return Nplacas;
	}
	/**
	 * 
	 * @param nplacas
	 */
	public void setNplacas(String nplacas) {
		Nplacas = nplacas;
	}
	/**
	 * 
	 * @return
	 */
	public String getNserie() {
		return Nserie;
	}
	/**
	 * 
	 * @param nserie
	 */
	public void setNserie(String nserie) {
		Nserie = nserie;
	}
	/**
	 * 
	 * @return
	 */
	public String getModelo() {
		return Modelo;
	}
	/**
	 * 
	 * @param modelo
	 */
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	/**
	 * 
	 * @return
	 */
	public String getReferencias() {
		return Referencias;
	}
	/**
	 * 
	 * @param referencias
	 */
	public void setReferencias(String referencias) {
		Referencias = referencias;
	}
	/**
	 * 
	 * @return
	 */
	public String getPlacasEstado() {
		return PlacasEstado;
	}
	/**
	 * 
	 * @param placasEstado
	 */
	public void setPlacasEstado(String placasEstado) {
		PlacasEstado = placasEstado;
	}
	/**
	 * 
	 * @return
	 */
	public String getArticulosViolados() {
		return ArticulosViolados;
	}
	/**
	 * 
	 * @param articulosViolados
	 */
	public void setArticulosViolados(String articulosViolados) {
		ArticulosViolados = articulosViolados;
	}
	/**
	 * 
	 * @return
	 */
	public String getNpolicia() {
		return Npolicia;
	}
	/**
	 * 
	 * @param npolicia
	 */
	public void setNpolicia(String npolicia) {
		Npolicia = npolicia;
	}
	/**
	 * 
	 * @return
	 */
	public String getMarca() {
		return Marca;
	}
	/**
	 * 
	 * @param marca
	 */
	public void setMarca(String marca) {
		Marca = marca;
	}
	/**
	 * 
	 * @return
	 */
	public String getNeconomico() {
		return Neconomico;
	}
	/**
	 * 
	 * @param neconomico
	 */
	public void setNeconomico(String neconomico) {
		Neconomico = neconomico;
	}
	/**
	 * 
	 * @return
	 */
	public String getRutaSitio() {
		return RutaSitio;
	}
	/**
	 * 
	 * @param rutaSitio
	 */
	public void setRutaSitio(String rutaSitio) {
		RutaSitio = rutaSitio;
	}
	/**
	 * 
	 * @return
	 */
	public String getColor() {
		return Color;
	}
	/**
	 * 
	 * @param color
	 */
	public void setColor(String color) {
		Color = color;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombreConductor() {
		return NombreConductor;
	}
	/**
	 * 
	 * @param nombreConductor
	 */
	public void setNombreConductor(String nombreConductor) {
		NombreConductor = nombreConductor;
	}
	/**
	 * 
	 * @return
	 */
	public String getDomicilioConductor() {
		return DomicilioConductor;
	}
	/**
	 * 
	 * @param domicilioConductor
	 */
	public void setDomicilioConductor(String domicilioConductor) {
		DomicilioConductor = domicilioConductor;
	}
	/**
	 * 
	 * @return
	 */
	public String getNlicenciaConductor() {
		return NlicenciaConductor;
	}
	/**
	 * 
	 * @param nlicenciaConductor
	 */
	public void setNlicenciaConductor(String nlicenciaConductor) {
		NlicenciaConductor = nlicenciaConductor;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombrePropietario() {
		return NombrePropietario;
	}
	/**
	 * 
	 * @param nombrePropietario
	 */
	public void setNombrePropietario(String nombrePropietario) {
		NombrePropietario = nombrePropietario;
	}
	/**
	 * 
	 * @return
	 */
	public String getDomicilioPropietario() {
		return DomicilioPropietario;
	}
	/**
	 * 
	 * @param domicilioPropietario
	 */
	public void setDomicilioPropietario(String domicilioPropietario) {
		DomicilioPropietario = domicilioPropietario;
	}
	/**
	 * 
	 * @return
	 */
	public String getMarcaModelo() {
		return MarcaModelo;
	}
	/**
	 * 
	 * @param marcaModelo
	 */
	public void setMarcaModelo(String marcaModelo) {
		MarcaModelo = marcaModelo;
	}
	/**
	 * 
	 * @return
	 */
	public String getRetencion() {
		return Retencion;
	}
	/**
	 * 	
	 * @param retencion
	 */
	public void setRetencion(String retencion) {
		Retencion = retencion;
	}
	/**
	 * 
	 * @return
	 */
	public String getMotivo() {
		return Motivo;
	}
	/**
	 * 
	 * @param motivo
	 */
	public void setMotivo(String motivo) {
		Motivo = motivo;
	}
	/**
	 * 
	 * @return
	 */
	public String getInfraccion() {
		return Infraccion;
	}
	/**
	 * 
	 * @param infraccion
	 */
	public void setInfraccion(String infraccion) {
		Infraccion = infraccion;
	}
	/**
	 * 
	 * @return
	 */
	public String getMunicipio() {
		return Municipio;
	}
	/**
	 * 
	 * @param municipio
	 */
	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}
	/**
	 * 
	 * @return
	 */
	public String getFecha() {
		return Fecha;
	}
	/**
	 * Establece la fecha
	 * @param fec	fecha deseada
	 */
	public void setFecha(Date fec) {
		try {
			//variables de función
			java.util.Date inicio = fec;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Fecha=sdf.format(inicio);
			Fecha=Fecha.substring(0,2).contains("/")?"0"+Fecha:Fecha;
			
		//manejo de errores
		} catch(NullPointerException ex) {
			System.err.println("Error en la fecha "+ex);
		}
	}//fin setFecha

	/**
	 * determina el nuero de filas que ocuparan las tablas
	 * @param archivo archivo a analizar
	 */
	public void nFilas(File archivo){
		//variable de función
		DefaultTableModel modeloT = new DefaultTableModel();//modelo de tabla
		tabla = new JTable();//tabla
		
		tabla.setModel(modeloT);
		//tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int indiceFila=-1;
		try {
			wb = WorkbookFactory.create(new FileInputStream(archivo));//abre el archivo
			Sheet hoja = wb.getSheetAt(0);//obtiene la hoja 0
			@SuppressWarnings("rawtypes")
			Iterator filaIterator = hoja.rowIterator();//iterador de renglones
			//recorre los renglones
			while (filaIterator.hasNext()) {                
				indiceFila++;
				Row fila = (Row) filaIterator.next();
				@SuppressWarnings("rawtypes")
				Iterator columnaIterator = fila.cellIterator();
				Object[] listaColumna = new Object[1000];
				int indiceColumna=-1;
				while (columnaIterator.hasNext()) {//recorre las celdas                    
					indiceColumna++;
					Cell celda = (Cell) columnaIterator.next();
					if(indiceFila==0){
						modeloT.addColumn(celda.getStringCellValue());
					}else{
						if(celda!=null){
							switch(celda.getCellType()){
							case Cell.CELL_TYPE_NUMERIC:
								listaColumna[indiceColumna]= (int)Math.round(celda.getNumericCellValue());
								break;
							case Cell.CELL_TYPE_STRING:
								listaColumna[indiceColumna]= celda.getStringCellValue();
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								listaColumna[indiceColumna]= celda.getBooleanCellValue();
								break;
							default:
								listaColumna[indiceColumna]=celda.getDateCellValue();
								break;
							}
							//System.out.println("col"+indiceColumna+" valor: true - "+celda+".");
						}                        
					}
				}
				//si el modelo existe se agregan datos
				if(indiceFila!=0)modeloT.addRow(listaColumna);
			}
			//Manejo de errores
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}
	}//fin nFilas

	/**
	 * exporta los datos despues de añadir una nueva entrada
	 * @param archivo
	 * @return
	 */
	public String Exportar(File archivo){
		String respuesta="Algo a salido mal y no pudimos guardar los datos";
		nFilas(archivo);
		int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
		int j = 0;
		int i = -1;
		Row fila=null;
		if(archivo.getName().endsWith("xls")){
			wb = new HSSFWorkbook();
		}else{
			wb = new XSSFWorkbook();
		}
		Sheet hoja = wb.createSheet("Pruebita");
		try {
			for (i = -1; i < numFila; i++) {
				fila = hoja.createRow(i+1);
				for (j=0; j < numColumna; j++) {
					Cell celda = fila.createCell(j);
					if(i==-1){
						celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
						wb.write(new FileOutputStream(archivo));
					}else{
						celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
						String dato = (String) tabla.getValueAt(i, j);
						String nb = (String) tabla.getValueAt(i, 0);
						if (!dato.equals("")) {
							if (!nb.equals(Nboleta)) {
								wb.write(new FileOutputStream(archivo));
							}else {
								respuesta="Este número de boleta ya esta registrado\n intenta con otro número de boleta";
							}
						}
					}	
				}
			}
			fila = hoja.createRow(i+1);
			//N° BOLETA
			Cell celda = fila.createCell(0);
			celda.setCellValue(Nboleta);
			//DIA
			celda = fila.createCell(1);
			celda.setCellValue(Fecha.substring(0,2));
			//MES	
			celda = fila.createCell(2);
			celda.setCellValue(Fecha.substring(3,5));
			//AÑO	
			celda = fila.createCell(3);
			celda.setCellValue(Fecha.substring(6,10));
			//HORA	
			celda = fila.createCell(4);
			celda.setCellValue(hora);
			//MUNICIPIO Y ESTADO	
			celda = fila.createCell(5);
			celda.setCellValue(Municipio+", "+Estado);
			//EN LA CALLE	
			celda = fila.createCell(6);
			celda.setCellValue(Referencias);
			//INFRACCIÓN AL	
			celda = fila.createCell(7);
			celda.setCellValue(Infraccion);
			//N° PLACAS	
			celda = fila.createCell(8);
			celda.setCellValue(Nplacas);
			//PLACAS DEL ESTADO DE	
			celda = fila.createCell(9);
			celda.setCellValue(PlacasEstado);
			//MARCA Y SUBMARCA	
			celda = fila.createCell(10);
			celda.setCellValue(Marca);
			//MODELO	
			celda = fila.createCell(11);
			celda.setCellValue(Modelo);
			//N° DE SERIE	
			celda = fila.createCell(12);
			celda.setCellValue(Nserie);
			//N° ECONÓMICO	
			celda = fila.createCell(13);
			celda.setCellValue(Neconomico);
			//RUTA O SITIO	
			celda = fila.createCell(14);
			celda.setCellValue(RutaSitio);
			//COLOR	
			celda = fila.createCell(15);
			celda.setCellValue(Color);
			//NOMBRE DEL CONDUCTOR	
			celda = fila.createCell(16);
			celda.setCellValue(NombreConductor);
			//DOMICILIO DEL CONDUCTOR	
			celda = fila.createCell(17);
			celda.setCellValue(DomicilioConductor);
			//N° LICENCIA DEL CONDUCTOR	
			celda = fila.createCell(18);
			celda.setCellValue(NlicenciaConductor);
			//NOMBRE DEL PROPIETARIO	
			celda = fila.createCell(19);
			celda.setCellValue(NombrePropietario);
			//DOMICILIO DEL PROPIETARIO	
			celda = fila.createCell(20);
			celda.setCellValue(DomicilioPropietario);
			//ARTICULOS VIOLADOS	
			celda = fila.createCell(21);
			celda.setCellValue(ArticulosViolados);
			//RETENCION DE	
			celda = fila.createCell(22);
			celda.setCellValue(Retencion);
			//MARCA Y MODELO DEL DISPOSITIVO DE ALCOHOLÍMETRO	
			celda = fila.createCell(23);
			celda.setCellValue(MarcaModelo);
			//MOTIVO
			celda = fila.createCell(24);
			celda.setCellValue(Motivo);
			//N° DE POLICIA DE SEGURIDAD VIAL
			celda = fila.createCell(25);
			celda.setCellValue(Npolicia);
			//N° DE POLICIA DE SEGURIDAD VIAL
			celda = fila.createCell(26);
			celda.setCellValue(estatus);

			wb.write(new FileOutputStream(archivo));
			respuesta="Se guardaron los datos con exito";
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return respuesta;
	}//fin Exportar
	
	////////Funciones Miscelaneas
	/**
	 * limpia las los campos
	 * @param vistaInfraccion
	 */	
	public void limpiarDatos(VistaInfraccion vistaInfraccion) {
		// TODO Auto-generated method stub
		vistaInfraccion.txtNboleta.setText("");
		vistaInfraccion.cbInfraccion.setSelectedIndex(vistaInfraccion.cbMunicipio.getComponentCount()-1);
		vistaInfraccion.txtEstado.setText("");
		vistaInfraccion.txtNplacas.setText("");
		vistaInfraccion.txtNserie.setText("");
		vistaInfraccion.txtModelo.setText("");
		vistaInfraccion.txtReferencias.setText("");
		vistaInfraccion.txtPlacasEstado.setText("");
		vistaInfraccion.txtArticulosViolados.setText("");
		vistaInfraccion.txtNpolicia.setText("");
		vistaInfraccion.txtMarca.setText("");
		vistaInfraccion.txtNeconomico.setText("");
		vistaInfraccion.txtRutaSitio.setText("");
		vistaInfraccion.txtColor.setText("");
		vistaInfraccion.txtNombreConductor.setText("");
		vistaInfraccion.txtDomicilioConductor.setText("");
		vistaInfraccion.txtNlicenciaConductor.setText("");
		vistaInfraccion.txtNombrePropietario.setText("");
		vistaInfraccion.txtDomicilioPropietario.setText("");
		vistaInfraccion.txtMarcaModelo.setText("");
		vistaInfraccion.txtRetencion.setText("");
		vistaInfraccion.txtMotivo.setText("");
		vistaInfraccion.cbInfraccion.setSelectedItem(0);
		vistaInfraccion.cbMunicipio.setSelectedItem(0);
		vistaInfraccion.dcFecha.setDate(null);
		vistaInfraccion.timeChooser.setText("");
		//ModeloBoleta.setNboleta(VistaInfraccion.txtNboleta.getText());
	}//fin limpiarDatos
}
