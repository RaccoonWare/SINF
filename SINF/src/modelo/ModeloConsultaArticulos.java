/**
 * Logica de negicón de ventana de consulta de articulos
 * @author David
 */
package modelo;
/* importación de librerias */
//librerias Swing
import java.io.*;//manejo archivos
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
//libreras awt
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//Librerias Apache poi  (libreria externa)
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;
//librerias y clases propias
import controlador.MVC;
import controlador.MVC.HiloArchivo;
import exepciones.EmptyFieldExcepton;
import vista.VistaConsultaArticulo;

/**
 * Clase principal
 * @author David
 * @see VistaConsultaArticulos
 * @see ControladorConsultaDescargas
 */
public class ModeloConsultaArticulos {
	//variables de clase
	VistaConsultaArticulo vistaConsultas;
	Workbook wb;//archivo de office
    //private DefaultTableModel modeloT;//Modelo de tabla, el que se encuentra en la vista es un placeholder, en este se manejan los datos reales
    //Variables de instancia
    public TableRowSorter<TableModel> filtro;//filtro para datos
    private File archivo;
    private Thread hiloArchivo, hiloTabla;//hilos para ejecución paralela
    private HiloArchivo runArchivo;
    
    ////////////////Constructores e iniciadores
    /**
     * Constructor por defecti
     * @param vistaArticulos
     */
    public ModeloConsultaArticulos(VistaConsultaArticulo vistaArticulos) {
    	this.vistaConsultas= vistaArticulos;
    	iniciar();

    }
    
    /**
     * inicializa los componentes
     */
    public void iniciar() {    	
    	//modeloT=  new DefaultTableModel();
    	archivo= MVC.ARTICULO;
    	MVC.importar(archivo, vistaConsultas.tabla);
    	vistaConsultas.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	//modeloT= (DefaultTableModel) vistaArticulos.tabla.getModel();
    	filtro= new TableRowSorter<TableModel>(((DefaultTableModel)vistaConsultas.tabla.getModel()));    	
    	vistaConsultas.tabla.setRowSorter(filtro);
    	filtro.setRowFilter(null);
    	runArchivo= new HiloArchivo(vistaConsultas.tabla,archivo);
    	hiloArchivo= new Thread(hiloArchivo);
    	hiloArchivo.start();    	    	
    }
    /**
     * Añade componenetes al popup menu, generado automaticamene
     * @param component
     * @param popup
     * @deprecated ...ver si puedo implementarlo manualemente con los demas elementos
     */
    /*private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}//Fin addPopup*/
    
    
    
	////////////////Manejo de formularios
    /**
     * llena los campos de texto para edición
     * @param vistaConsultas
     * pre-condición: se selecciono un elemento de la tabla
     * post-condicón: se limpian los campos
     * @see agregaArticulo
     * @see llenaCampos
     * @see modificaArticulo 
     * @see limpiaCampos
     * @see campoQuitable
     * @see quitaCampos
     * @see validaCampos
     */
	public void llenaCampos() {
		int row= vistaConsultas.tabla.getSelectedRow();
		if(row>=0) {
			vistaConsultas.btnAccion.setText("Modificar");
			if(row>=0){
				vistaConsultas.txtArt.setText(""+(String) vistaConsultas.tabla.getValueAt(row,0));
				vistaConsultas.txtArt.setBackground(MVC.COLOR_VALID);
				vistaConsultas.txtDesc.setText(""+(String) vistaConsultas.tabla.getValueAt(row,1));
				vistaConsultas.txtDesc.setBackground(MVC.COLOR_VALID);
				vistaConsultas.txtSanc.setText(""+(String) vistaConsultas.tabla.getValueAt(row,2));
				vistaConsultas.txtSanc.setBackground(MVC.COLOR_VALID);
			}			
		}
	}
    
	/**
	 * Limpia los campos de edición y la selección de grupo
	 * @param vistaConsultas
	 * pre-condición: ninguna
	 * post-condición: se limpia la selección en la tabla y se pone en estado para agregar
	 * @see agregaArticulo
     * @see llenaCampos
     * @see modificaArticulo 
     * @see limpiaCampos
     * @see campoQuitable
     * @see QuitaCampos
	 */
	public void limpiaCampos() {
		vistaConsultas.btnAccion.setText("Agregar");
		vistaConsultas.tabla.clearSelection();
		vistaConsultas.txtArt.setText("");
		vistaConsultas.txtDesc.setText("");
		vistaConsultas.txtSanc.setText("");
		vistaConsultas.txtBuscar.setText("");
		//filtrar(vistaConsultas.tabla, "");
		vistaConsultas.tabla.clearSelection();
		
	}//fin limpiarCampos
	
	/**
	 * Cambia el boton de acción al estado "Eliminar", si se vuelve a oprimir se eliminara el dato del modelo, si se vuelve a llenar los datos regresa al estado modificable * @param vistaConsultas
	 * pre-condición: los campos estan vacios
	 * @see agregaArticulo
     * @see llenaCampos
     * @see modificaArticulo 
     * @see limpiaCampos
     * @see campoQuitable
     * @see QuitaCampos
	 */
	public void campoQuitable() {
		vistaConsultas.txtArt.setBackground(Color.WHITE);
		vistaConsultas.txtDesc.setBackground(Color.WHITE);
		vistaConsultas.txtSanc.setBackground(Color.WHITE);
		vistaConsultas.btnAccion.setText("Eliminar");		
	}//fin campoQuitable
	
	/**
	 * verifica que haya ifnnformación en los datos
	 * @param vistaConsultas
	 * @throws EmptyFieldExcepton 
	 * @see validaCampos
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
	}//fin ValidaCampos
	

	/**
	 * Valida un solo campo
	 * @param vistaConsultas
	 * @return
	 * @throws EmptyFieldExcepton
	 * @see validaCampo
	 */
	public Object[] validaCampos() throws EmptyFieldExcepton {
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
	}//fin validaCampo
	
	///////////////////manejo datos tabla
	/**
	 * añade un articulo a la lista
	 * @param vistaConsultas
	 * @see agregaArticulo
     * @see llenaCampos
     * @see modificaArticulo 
     * @see limpiaCampos
     * @see campoQuitable
     * @see QuitaCampos
     * pre-condición: los tres campos estan llenos, el boton de acción esta en estado "Agregar"
     * postcondición articulo agregado al modelo, campos limpiados
     * cuando cambie la función exportar a MVC tuve que obtener el modelo de la tabla en vez de usar ModeloT (por alguna razón  importarlo/asignarlo desde MVC hace que no funcione localmente, por lo que ahora se hace casteo desde el mvc
	 */

	public void agregaArticulo() {
		RowFilter aux= filtro.getRowFilter();
		filtro.setRowFilter(null);
		//deloT.addRow(new Object[]{vistaConsultas.txtArt.getText(),vistaConsultas.txtDesc.getText(),vistaConsultas.txtSanc.getText()});		
		//modeloT.fireTableRowsInserted(modeloT.getRowCount()-1, modeloT.getRowCount()-1);
		((DefaultTableModel) vistaConsultas.tabla.getModel()).addRow(new Object[]{vistaConsultas.txtArt.getText(),vistaConsultas.txtDesc.getText(),vistaConsultas.txtSanc.getText()});		
		((DefaultTableModel) vistaConsultas.tabla.getModel()).fireTableRowsInserted(((DefaultTableModel)vistaConsultas.tabla.getModel()).getRowCount()-1, ((DefaultTableModel)vistaConsultas.tabla.getModel()).getRowCount()-1);
		//((DefaultTableModel) vistaConsultas.tabla.getModel()).fireTableRowsUpdated(((DefaultTableModel)vistaConsultas.tabla.getModel()).getRowCount()-1, ((DefaultTableModel)vistaConsultas.tabla.getModel()).getRowCount()-1);;
		filtro.setRowFilter(aux);
		vistaConsultas.txtArt.setText("");
		vistaConsultas.txtDesc.setText("");
		vistaConsultas.txtSanc.setText("");
		
		
		MVC.exportar(archivo,vistaConsultas.tabla);
		//runArchivo.activate();
	}//fin AgregaArticulo		

	/**
	 * modifica el articulo seleccionado
	 * @param vistaConsultas
	 * pre-condición: los tres capos estan llenos
	 * post-condición: modelo modificado y archivo actualizado
	 * @see agregaArticulo
     * @see llenaCampos
     * @see modificaArticulo 
     * @see limpiaCampos
     * @see campoQuitable
     * @see QuitaCampos    
     * cuando cambie la función exportar a MVC tuve que obtener el modelo de la tabla en vez de usar ModeloT (por alguna razón  importarlo desde MVC hace que no funcione localmente, por lo que ahora se hace casteo desde el mvc
     * se "modifico"  el codigo para restaurar la función cuando el elemento esta filtrado (se habia perdido cuando se combino con el codigo de mario y se deshabilito la variable modeloT
	 */
	public void modificaArticulo() {
		// TODO Auto-generated method stub
		//try {
		//	validaCampos(vistaConsultas);
			if(vistaConsultas.tabla.getModel().equals(((DefaultTableModel)vistaConsultas.tabla.getModel()))) {				
				((DefaultTableModel) vistaConsultas.tabla.getModel()).setValueAt(vistaConsultas.txtArt.getText(),filtro.convertRowIndexToModel(vistaConsultas.tabla.getSelectedRow()),0);//modifica el campo de articulos, pero en vez de hacerlo directamente basado en el modelo, lo hace basado en el filtro en caso que latabla se encuentre filtrada actualmente
				((DefaultTableModel) vistaConsultas.tabla.getModel()).setValueAt(vistaConsultas.txtDesc.getText(),filtro.convertRowIndexToModel(vistaConsultas.tabla.getSelectedRow()),1);//modifica el campo de descripción, pero en vez de hacerlo directamente basado en el modelo, lo hace basado en el filtro en caso que latabla se encuentre filtrada actualmente
				((DefaultTableModel) vistaConsultas.tabla.getModel()).setValueAt(vistaConsultas.txtSanc.getText(),filtro.convertRowIndexToModel(vistaConsultas.tabla.getSelectedRow()),2);//modifica el campo sanciones, pero en vez de hacerlo directamente basado en el modelo, lo hace basado en el filtro en caso que latabla se encuentre filtrada actualmente
				((DefaultTableModel) vistaConsultas.tabla.getModel()).fireTableRowsUpdated(vistaConsultas.tabla.convertColumnIndexToView(vistaConsultas.tabla.getSelectedRow()),vistaConsultas.tabla.convertColumnIndexToView(vistaConsultas.tabla.getSelectedRow()) );			
				MVC.exportar(archivo,vistaConsultas.tabla);
				//runArchivo.activate();
			}
		//}
	}//quitar Modificar Articulo
	
	/**
	 * quita los un dato del modelo y la tabla
	 * pre-condición: boton acción en estado Eliminar, campos vacios
	 * post-condición modelo, tabla y archivos  actualizados 
	 * @param vistaConsultas
	 * @see agregaArticulo
     * @see llenaCampos
     * @see modificaArticulo 
     * @see limpiaCampos
     * @see campoQuitable
     * @see QuitaCampos
     * cuando cambie la función exportar a MVC tuve que obtener el modelo de la tabla en vez de usar ModeloT (por alguna razón  importarlo desde MVC hace que no funcione localmente, por lo que ahora se hace casteo desde el mvc
     * se "modifico"  el codigo para restaurar la función cuando el elemento esta filtrado (se habia perdido cuando se combino con el codigo de mario y se deshabilito la variable modeloT
	 */
	public void quitarCampo() {
		// TODO Auto-generated method stub
		//if(vistaConsultas.tabla.getModel().equals(modeloT))
		((DefaultTableModel)vistaConsultas.tabla.getModel()).removeRow(filtro.convertRowIndexToModel(vistaConsultas.tabla.getSelectedRow()));//quita el campo, pero en vez de hacerlo directamente basado en el modelo, lo hace basado en el filtro en caso que latabla se encuentre filtrada actualmente			
		((DefaultTableModel)vistaConsultas.tabla.getModel()).fireTableDataChanged();
		limpiaCampos();
	
		MVC.exportar(archivo,vistaConsultas.tabla);
		//runArchivo.activate();
		vistaConsultas.txtBuscar.requestFocus();
		
	}//fin Quitar campo
	
	/**
	 * filtra el contenido de la tabla y solo muestra los datos que conuerdan
	 * @param tabla
	 * @param texto
	 * @see agregaArticulo
     * @see llenaCampos
     * @see modificaArticulo 
     * @see limpiaCampos
     * @see campoQuitable
     * @see QuitaCampos
     * pre-condicón: modelo iniciado
     * post-condición: tabla actualizada, pero modelo sin modificar
	 */
	public void filtrar(String texto) {		
		//filtro= new TableRowSorter<TableModel>(((DefaultTableModel)vistaConsultas.tabla.getModel()));    	
    	//vistaConsultas.tabla.setRowSorter(filtro);
		if(texto.length()==0) {
			filtro.setRowFilter(null);
		}else {
			filtro.setRowFilter(RowFilter.regexFilter(texto));
		}
		((DefaultTableModel)vistaConsultas.tabla.getModel()).fireTableDataChanged();
		
	}//fin filtrar
	/*public void newFilter(){
		RowFilter<modeloT,Object> filtro{
			
		}
	}*/
	
}// Fin clase principal
