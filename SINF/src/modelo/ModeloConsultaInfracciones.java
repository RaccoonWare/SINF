package modelo;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

public class ModeloConsultaInfracciones {
	Workbook wb;
	private Calendar cal1, cal2, cal3;

	public Calendar getCal1() {
		return cal1;
	}
	public void setCal1(Date fecIni) {
		cal1 = new GregorianCalendar();
		if (fecIni!=null) {
			cal1.setTime(fecIni);
		}else {
			cal1=null;
		}

	}
	public Calendar getCal2() {
		return cal2;
	}
	public void setCal2(Date fecFin) {
		cal2 = new GregorianCalendar();
		if (fecFin!=null) {
			cal2.setTime(fecFin);
		}else {
			cal2=null;
		}

	}

	public String Importar(File archivo, JTable tablaD){
		String respuesta="No se pudo realizar la importación.";
		DefaultTableModel modeloT = new DefaultTableModel();
		tablaD.setModel(modeloT);
		tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		try {
			wb = WorkbookFactory.create(new FileInputStream(archivo));
			Sheet hoja = wb.getSheetAt(0);
			Iterator<Row> filaIterator = hoja.rowIterator();
			int indiceFila=-1;
			while (filaIterator.hasNext()) {                
				indiceFila++;
				Row fila = (Row) filaIterator.next();
				Iterator<Cell> columnaIterator = fila.cellIterator();
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
			respuesta="Importación exitosa";
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}
		return respuesta;
	}
	public String ImportarEspesifico(File archivo, JTable tablaD, String nBoleta, String nPlaca, String status) throws ParseException{
		String respuesta="No se pudo realizar la importación.";
		DefaultTableModel modeloT = new DefaultTableModel();
		tablaD.setModel(modeloT);
		tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		switch (status) {
		case "Activos":
			status="AC";
			break;
		case "Anulados":
			status="AN";
			break;
		default:
			status="A";
			break;	
		}

		try {
			wb = WorkbookFactory.create(new FileInputStream(archivo));
			Sheet hoja = wb.getSheetAt(0);
			Iterator<Row> filaIterator = hoja.rowIterator();
			int indiceFila=-1;
			while (filaIterator.hasNext()) {                
				indiceFila++;
				Row fila = (Row) filaIterator.next();
				Iterator<Cell> columnaIterator = fila.cellIterator();
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
								listaColumna[indiceColumna]= celda.getDateCellValue();
								break;
							}
							//System.out.println("col"+indiceColumna+" valor: true - "+celda+".");
						}                        
					}
				}
				if(indiceFila!=0) {
					DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
					Date fec = f.parse(String.valueOf(listaColumna[1])+"/"+
							String.valueOf(listaColumna[2])+"/"+
							String.valueOf(listaColumna[3]));
					cal3 = new GregorianCalendar();
					cal3.setTime(fec);
					if (nBoleta.equals("") && nPlaca.equals("") && cal1!=null && cal2!=null && String.valueOf(listaColumna[26]).contains(status)) {
						if (cal3.after(cal1) && cal3.before(cal2)) {
							modeloT.addRow(listaColumna);
						}

					}else if(nBoleta.equals("") && nPlaca.equals("") && cal1!=null && cal2==null && String.valueOf(listaColumna[26]).contains(status)) {
						if (cal1.equals(cal3)) {
							modeloT.addRow(listaColumna);
						}
					}else if(!nBoleta.equals("") && cal1!=null && cal2!=null && String.valueOf(listaColumna[26]).contains(status)) {
						if(String.valueOf(listaColumna[0]).contains(nBoleta) && 
								cal3.after(cal1) && cal3.before(cal2)) {
							modeloT.addRow(listaColumna);
						}
					}else if (!nPlaca.equals("") && cal1!=null && cal2!=null && String.valueOf(listaColumna[26]).contains(status)) {
						if(cal3.after(cal1) && cal3.before(cal2) &&
								String.valueOf(listaColumna[8]).contains(nPlaca)) {
							modeloT.addRow(listaColumna);
						}
					}else if(cal1==null | cal2==null && !nPlaca.equals("") && String.valueOf(listaColumna[26]).contains(status)){
						if(String.valueOf(listaColumna[8]).contains(nPlaca)) {
							modeloT.addRow(listaColumna);
						}
					}else if(cal1==null | cal2==null && !nBoleta.equals("") && String.valueOf(listaColumna[26]).contains(status)){
						if(String.valueOf(listaColumna[0]).equals(nBoleta)) {
							modeloT.addRow(listaColumna);
						}
					}else if(cal1!=null && cal2!=null && !nBoleta.equals("") && !nPlaca.equals("") && String.valueOf(listaColumna[26]).contains(status)){
						if(String.valueOf(listaColumna[0]).contains(nBoleta) &&
								String.valueOf(listaColumna[8]).contains(nPlaca) &&
								cal3.after(cal1) && cal3.before(cal2)) {
							modeloT.addRow(listaColumna);
						}
					}else {
						if(String.valueOf(listaColumna[26]).contains(status)) {
							modeloT.addRow(listaColumna);
						}
					}
				}

			}
			respuesta="Importación exitosa";
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}
		return respuesta;
	}

	public String Exportar(File archivo, JTable tablaD){
		String respuesta="No se realizo con exito la exportación.";
		int numFila=tablaD.getRowCount(), numColumna=tablaD.getColumnCount();
		if(archivo.getName().endsWith("xls")){
			wb = new HSSFWorkbook();
		}else{
			wb = new XSSFWorkbook();
		}
		Sheet hoja = wb.createSheet("Pruebita");
		try {
			for (int i = -1; i < numFila; i++) {
				Row fila = hoja.createRow(i+1);
				for (int j = 0; j < numColumna; j++) {
					Cell celda = fila.createCell(j);
					if(i==-1){
						celda.setCellValue(String.valueOf(tablaD.getColumnName(j)));
					}else{
						celda.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
					}
					wb.write(new FileOutputStream(archivo));
				}
			}
			respuesta="Exportación exitosa.";
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return respuesta;
	}

	public Object[] ImportarParaModificar(File archivo, JTable tablaD, String nBoleta) throws ParseException{
		DefaultTableModel modeloT = new DefaultTableModel();
		Object[] datos = null;
		tablaD.setModel(modeloT);
		tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		try {
			wb = WorkbookFactory.create(new FileInputStream(archivo));
			Sheet hoja = wb.getSheetAt(0);
			Iterator<Row> filaIterator = hoja.rowIterator();
			int indiceFila=-1;
			while (filaIterator.hasNext()) {                
				indiceFila++;
				Row fila = (Row) filaIterator.next();
				Iterator<Cell> columnaIterator = fila.cellIterator();
				Object[] listaColumna = new Object[30];
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
				if(indiceFila!=0) {
					DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
					Date fec = f.parse(String.valueOf(listaColumna[1])+"/"+
							String.valueOf(listaColumna[2])+"/"+
							String.valueOf(listaColumna[3]));
					cal3 = new GregorianCalendar();
					cal3.setTime(fec);
					if(String.valueOf(listaColumna[0]).equals(nBoleta)) {
						datos= listaColumna;
					}
					modeloT.addRow(listaColumna);
				}
			}
		} catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
			System.err.println(e.getMessage());
		}
		return datos;
	}
}
