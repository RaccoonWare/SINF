package modelo;
import org.apache.poi.ss.usermodel.*;

import static org.junit.Assert.fail;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;
import org.omg.CORBA.PRIVATE_MEMBER;


public class ModeloInfraccion{
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
	//AC = activo AN = anulado
	private String estatus="AC";

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNboleta() {
		return Nboleta;
	}

	public void setNboleta(String nboleta) {
		Nboleta = nboleta;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getNplacas() {
		return Nplacas;
	}

	public void setNplacas(String nplacas) {
		Nplacas = nplacas;
	}

	public String getNserie() {
		return Nserie;
	}

	public void setNserie(String nserie) {
		Nserie = nserie;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getReferencias() {
		return Referencias;
	}

	public void setReferencias(String referencias) {
		Referencias = referencias;
	}

	public String getPlacasEstado() {
		return PlacasEstado;
	}

	public void setPlacasEstado(String placasEstado) {
		PlacasEstado = placasEstado;
	}

	public String getArticulosViolados() {
		return ArticulosViolados;
	}

	public void setArticulosViolados(String articulosViolados) {
		ArticulosViolados = articulosViolados;
	}

	public String getNpolicia() {
		return Npolicia;
	}

	public void setNpolicia(String npolicia) {
		Npolicia = npolicia;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getNeconomico() {
		return Neconomico;
	}

	public void setNeconomico(String neconomico) {
		Neconomico = neconomico;
	}

	public String getRutaSitio() {
		return RutaSitio;
	}

	public void setRutaSitio(String rutaSitio) {
		RutaSitio = rutaSitio;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getNombreConductor() {
		return NombreConductor;
	}

	public void setNombreConductor(String nombreConductor) {
		NombreConductor = nombreConductor;
	}

	public String getDomicilioConductor() {
		return DomicilioConductor;
	}

	public void setDomicilioConductor(String domicilioConductor) {
		DomicilioConductor = domicilioConductor;
	}

	public String getNlicenciaConductor() {
		return NlicenciaConductor;
	}

	public void setNlicenciaConductor(String nlicenciaConductor) {
		NlicenciaConductor = nlicenciaConductor;
	}

	public String getNombrePropietario() {
		return NombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		NombrePropietario = nombrePropietario;
	}

	public String getDomicilioPropietario() {
		return DomicilioPropietario;
	}

	public void setDomicilioPropietario(String domicilioPropietario) {
		DomicilioPropietario = domicilioPropietario;
	}

	public String getMarcaModelo() {
		return MarcaModelo;
	}

	public void setMarcaModelo(String marcaModelo) {
		MarcaModelo = marcaModelo;
	}

	public String getRetencion() {
		return Retencion;
	}

	public void setRetencion(String retencion) {
		Retencion = retencion;
	}

	public String getMotivo() {
		return Motivo;
	}

	public void setMotivo(String motivo) {
		Motivo = motivo;
	}

	public String getInfraccion() {
		return Infraccion;
	}

	public void setInfraccion(String infraccion) {
		Infraccion = infraccion;
	}

	public String getMunicipio() {
		return Municipio;
	}

	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(Date fec) {
		try {
			java.util.Date inicio = fec;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Fecha=sdf.format(inicio);
			Fecha=Fecha.substring(0,2).contains("/")?"0"+Fecha:Fecha;
		} catch(NullPointerException ex) {
			System.err.println("Error en la fecha "+ex);
		}
	}


	public void nFilas(File archivo){
		DefaultTableModel modeloT = new DefaultTableModel();
		tabla = new JTable();
		tabla.setModel(modeloT);
		//tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int indiceFila=-1;
		try {
			wb = WorkbookFactory.create(new FileInputStream(archivo));
			Sheet hoja = wb.getSheetAt(0);
			@SuppressWarnings("rawtypes")
			Iterator filaIterator = hoja.rowIterator();

			while (filaIterator.hasNext()) {                
				indiceFila++;
				Row fila = (Row) filaIterator.next();
				@SuppressWarnings("rawtypes")
				Iterator columnaIterator = fila.cellIterator();
				Object[] listaColumna = new Object[1000];
				int indiceColumna=-1;
				while (columnaIterator.hasNext()) {                    
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
				if(indiceFila!=0)modeloT.addRow(listaColumna);
			}
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}
	}

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
	}
}
