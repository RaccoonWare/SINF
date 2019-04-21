package modelo;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import javax.swing.RowFilter;
import java.awt.Color;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

import controlador.ControladorConsultaArticulos;
import controlador.ControladorConsultaInfracciones;
import controlador.ControladorInfraccion;
import controlador.MVC;
import exepciones.EmptyFieldExcepton;
import vista.VistaConsultaArticulo;
import vista.VistaConsultaInfraccion;
import vista.VistaInfraccion;

public class ModeloConsultaArticulos {
	Workbook wb;    
    private static DefaultTableModel modeloT;
    public TableRowSorter<TableModel> filtro;
    private File archivo=new File(MVC.getConfig().getProperty("articulos"));
    

        
    
	////////////////Manejo de Archivos
	/**
	 * Carga contenido de tabla
	 * @param archivo
	 * @param tablaD
	 * @return
	 */
	public String importar(File archivo, JTable tablaD){
		
		String respuesta="No se pudo realizar la importaci�n.";
        modeloT = new DefaultTableModel() {
        	@Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };        
        tablaD.setModel(modeloT);
        try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila=-1;
            while (filaIterator.hasNext()) {                
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[hoja.getLastRowNum()];
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
                        }                        
                    }
                }
                if(indiceFila!=0)modeloT.addRow(listaColumna);
            }
            tablaD.getColumnModel().getColumn(0).setPreferredWidth(250);
            tablaD.getColumnModel().getColumn(1).setPreferredWidth(1000);
            tablaD.getColumnModel().getColumn(2).setPreferredWidth(250);
            respuesta="Importaci�n exitosa";            
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
    }
    
	/**
	 * Exporta la tabla a un archivo Excel
	 * @param archivo
	 * @param tablaD
	 * @return
	 */
    public String exportar(File archivo, JTable tablaD){
        String respuesta="No se realizo con exito la exportaci�n.";
        //System.out.println("Filas: "+tablaD.getRowCount()+" \t Columnas: "+tablaD.getColumnCount());
        int numFila=tablaD.getModel().getRowCount(), numColumna=tablaD.getModel().getColumnCount();
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
                        celda.setCellValue(String.valueOf(tablaD.getModel().getValueAt(i, j)));
                    }
                    wb.write(new FileOutputStream(archivo));
                }
            }
            respuesta="Exportaci�n exitosa.";
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
    }

	////////////////Manejo de formularios
    /**
     * llena los campos de texto para edici�n
     * @param vistaConsultas
     */
	public void llenaCampos(VistaConsultaArticulo vistaConsultas) {
		int row= vistaConsultas.tabla.getSelectedRow();
		vistaConsultas.btnAccion.setText("Modificar");		
		if(row>=0){
			vistaConsultas.txtArt.setText((String) vistaConsultas.tabla.getValueAt(row,0));
			vistaConsultas.txtArt.setBackground(MVC.COLOR_VALID);
			vistaConsultas.txtDesc.setText((String) vistaConsultas.tabla.getValueAt(row,1));
			vistaConsultas.txtDesc.setBackground(MVC.COLOR_VALID);
			vistaConsultas.txtSanc.setText((String) vistaConsultas.tabla.getValueAt(row,2));
			vistaConsultas.txtSanc.setBackground(MVC.COLOR_VALID);
		}
	}
    
	/**
	 * Limpia los campos de edici�n y la selecci�n de grupo
	 * @param vistaConsultas
	 */
	public void limpiaCampos(VistaConsultaArticulo vistaConsultas) {
		vistaConsultas.btnAccion.setText("Agregar");
		vistaConsultas.tabla.clearSelection();
		vistaConsultas.txtArt.setText("");
		vistaConsultas.txtDesc.setText("");
		vistaConsultas.txtSanc.setText("");
		vistaConsultas.txtBuscar.setText("");
		filtrar(vistaConsultas.tabla, "");
		vistaConsultas.tabla.clearSelection();
		
	}
	
	public void campoQuitable(VistaConsultaArticulo vistaConsultas) {
		vistaConsultas.txtArt.setBackground(Color.WHITE);
		vistaConsultas.txtDesc.setBackground(Color.WHITE);
		vistaConsultas.txtSanc.setBackground(Color.WHITE);
		vistaConsultas.btnAccion.setText("Quitar");		
	}
	
	/**
	 * a�ade un articulo a la lista
	 * @param vistaConsultas
	 */

	public void agregaArticulo(VistaConsultaArticulo vistaConsultas) {
		modeloT.addRow(new Object[]{vistaConsultas.txtArt.getText(),vistaConsultas.txtDesc.getText(),vistaConsultas.txtSanc.getText()});		
		modeloT.fireTableRowsInserted(modeloT.getRowCount()-1, modeloT.getRowCount()-1);
		vistaConsultas.txtArt.setText("");
		vistaConsultas.txtDesc.setText("");
		vistaConsultas.txtSanc.setText("");
		
		exportar(archivo,vistaConsultas.tabla);
	}
	
	/**
	 * verifica que haya ifnnformaci�n en los datos
	 * @param vistaConsultas
	 * @throws EmptyFieldExcepton 
	 */
	public void validaCampos(Object[] campos) throws EmptyFieldExcepton {
		Object aux[]= new Object[campos.length];
		int vacios=0;
		for(Object o: campos){
			if(o instanceof JTextComponent){
				if(((JTextComponent)o).getText().equals(""))
					aux[vacios++]=o;
			}
		}		
		Object res[]= arrayTrim(aux,vacios);
		if (res.length!=0) throw new EmptyFieldExcepton(res);
	}
	private Object[] arrayTrim(Object[] original,int size) {
		Object[] res= new Object[size];
		for(int i=0;i<size;i++)
			res[i]= original[i];
		return res;
	}
	

	
	public Object[] validaCampos(VistaConsultaArticulo vistaConsultas) throws EmptyFieldExcepton {
		Object campos[] = new Object[] {vistaConsultas.txtArt,vistaConsultas.txtDesc,vistaConsultas.txtSanc};
		Object aux[]= new Object[campos.length];
		int vacios=0;
		for(Object o: campos){
			if(o instanceof JTextComponent){
				if(((JTextComponent) o).getText().equals(""))
					aux[vacios++]=o;
			}
		}
		Object res[]=  arrayTrim(aux,vacios);
		if (res.length==0) return res;
		else throw new EmptyFieldExcepton(res);
	}

	public void quitarCampo(VistaConsultaArticulo vistaConsultas) {
		// TODO Auto-generated method stub
		//if(vistaConsultas.tabla.getModel().equals(modeloT))
		modeloT.removeRow(vistaConsultas.tabla.getSelectedRow());			
		modeloT.fireTableDataChanged();
		limpiaCampos(vistaConsultas);
		exportar(archivo,vistaConsultas.tabla);
		vistaConsultas.txtBuscar.requestFocus();
	}

	public void modificaArticulo(VistaConsultaArticulo vistaConsultas) {
		// TODO Auto-generated method stub
		//try {
		//	validaCampos(vistaConsultas);
			if(vistaConsultas.tabla.getModel().equals(modeloT)) {
				modeloT.setValueAt(vistaConsultas.txtArt.getText(),vistaConsultas.tabla.getSelectedRow(),0);
				modeloT.setValueAt(vistaConsultas.txtDesc.getText(),vistaConsultas.tabla.getSelectedRow(),1);
				modeloT.setValueAt(vistaConsultas.txtSanc.getText(),vistaConsultas.tabla.getSelectedRow(),2);
				modeloT.fireTableRowsUpdated(vistaConsultas.tabla.convertColumnIndexToView(vistaConsultas.tabla.getSelectedRow()),vistaConsultas.tabla.convertColumnIndexToView(vistaConsultas.tabla.getSelectedRow()) );			
				exportar(archivo,vistaConsultas.tabla);
			}
		//}
	}
	
	public void filtrar(JTable tabla, String texto) {
		filtro= new TableRowSorter<TableModel>(modeloT);
		tabla.setRowSorter(filtro);
		if(texto.length()==0) {
			filtro.setRowFilter(null);
		}else {
			filtro.setRowFilter(RowFilter.regexFilter(texto));
		}
		modeloT.fireTableDataChanged();
		
	}
	/*public void newFilter(){
		RowFilter<modeloT,Object> filtro{
			
		}
	}*/
	
}
