/**
 * Controlador de eventos de la ventana de consulta de articulos
 * @author Mario
 * @author David
 */
package controlador;
/* importaci�n de archivos */

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import modelo.ModeloConsultaArticulos;
import vista.VistaConsultaArticulo;
import vista.VistaPrincipal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import exepciones.EmptyFieldExcepton;
/**
 * Clase principal
 * @author David
 * @see VistaConsultaArticulo
 * @see ModeloConsutaArticulos
 */
public class ControladorConsultaArticulos implements ActionListener,FocusListener,KeyListener,MouseListener,TableModelListener, ListSelectionListener{
	//variables de instncia
	VistaConsultaArticulo vistaConsultas;//= new VistaConsultaArticulo();
	ModeloConsultaArticulos modeloConsultas;// = new ModeloConsultaArticulos(vistaConsultas);	
	JFileChooser selecArchivo;// = new JFileChooser();
	File archivo;
	int contAccion=0;

	////////////////Construtroes e iniciadores
	/**
	 * Constructor por defecto
	 * @param vistaConsultas
	 * @param modeloConsultas
	 */
	public ControladorConsultaArticulos(VistaConsultaArticulo vistaConsultas, ModeloConsultaArticulos modeloConsultas){
		this.modeloConsultas=modeloConsultas;
		this.vistaConsultas= vistaConsultas;		
		this.AddListeners();
	}///fin contructor 
	
	/**
	 * inicializa la vista y modelo
	 */
	public void iniciar() {
		VistaPrincipal.dpEscritorio.add(vistaConsultas);
		modeloConsultas.iniciar();
		vistaConsultas.show();
		vistaConsultas.getContentPane().setFocusable(true);
		vistaConsultas.setFocusable(true);
		vistaConsultas.panel.setFocusable(true);
		//vistaConsultas.panel_1.setFocusable(true);
		vistaConsultas.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//archivo=new File(MVC.getConfig().getProperty("articulos"));
		//MVC.importar(archivo, vistaConsultas.tabla);
		
		
		/*Object[] campos= {vistaConsultas.txtArt,vistaConsultas.txtDesc,vistaConsultas.txtSanc};
		MVC.coloreaCampos(campos,MVC.COLOR_INVALID);*/
	}//fin iniciar

	/**
	 * @deprecated no se logro inicar correctamente, si el componenet es privado no permite mas recursividad, y los listeners no parecen funcionar, mejor usar el addListeners normal
	 * @param contenedo contenedor
	 * @param visitado //arreglo de elementos visitado
	 * @param n
	 * @see addListeners
	 */
	/*
	private void subComponentAddListener(Component[] contenedo,ArrayList<Component> visitado, int n) {
		for(Component o: this.vistaConsultas.getContentPane().getComponents()) {
			//System.out.println("Componente #"+ n +" - "+" Nombre: "+o.hashCode()+" \tclase: "+ o.getClass());
			if(o instanceof  JPanel) {
				((JPanel)o).setFocusable(true);
				((JPanel)o).addFocusListener(this);
				((JPanel)o).addMouseListener(this);
				((JPanel)o).addKeyListener(this);
				if(!visitado.contains(o)) {
					//System.out.println("\nSubcomponente "+ visitado.size()+"_"+visitado.indexOf(o));				
					visitado.add(o);
					subComponentAddListener(((JPanel) o).getComponents(),visitado,++n);					
				}
			}else if(o instanceof  JTextField) {
				((JTextComponent)o).addFocusListener(this);
				((JTextComponent)o).addMouseListener(this);
				((JTextComponent)o).addKeyListener(this);
			}
			if (!visitado.contains(o)) {
				if(o instanceof Container) {
					//System.out.println("\nSubcomponente "+ visitado.size()+"_"+visitado.indexOf(o));				
					visitado.add(o);
					subComponentAddListener(((Container) o).getComponents(),visitado,++n);					
				}
			}
			
		}
	}//fin subComponentAddListener*/
	
	/**
	 * inicializa los listeners
	 */
	protected void AddListeners() {
		//buscar manera de que los demas componentes(no solo los swing) obtengan un focus listener para poder hacer click en ellos y perdier foco
		//subComponentAddListener(vistaConsultas.getContentPane().getComponents(),new ArrayList<Component>(),0 );
		//listener de la venana general
		this.vistaConsultas.addFocusListener(this);
		//listeners de los paneles
		this.vistaConsultas.getContentPane().addMouseListener(this);
		this.vistaConsultas.getContentPane().addFocusListener(this);
		this.vistaConsultas.getContentPane().addKeyListener(this);		
		
		this.vistaConsultas.panel.addMouseListener(this);
		this.vistaConsultas.panel.addFocusListener(this);
		this.vistaConsultas.panel.addKeyListener(this);
		
		//this.vistaConsultas.panel_1.addMouseListener(this);
		//this.vistaConsultas.panel_1.addFocusListener(this);
		//this.vistaConsultas.panel_1.addKeyListener(this);
		//listeners del boton
		this.vistaConsultas.btnAccion.addActionListener(this);
		this.vistaConsultas.btnAccion.addMouseListener(this);
		//Listeners de los campos de texto
		this.vistaConsultas.txtArt.addKeyListener(this);
		this.vistaConsultas.txtArt.addMouseListener(this);
		this.vistaConsultas.txtArt.addFocusListener(this);
		
		this.vistaConsultas.txtDesc.addKeyListener(this);
		this.vistaConsultas.txtDesc.addMouseListener(this);
		this.vistaConsultas.txtDesc.addFocusListener(this);
		
		this.vistaConsultas.txtSanc.addKeyListener(this);
		this.vistaConsultas.txtSanc.addMouseListener(this);
		this.vistaConsultas.txtSanc.addFocusListener(this);
		//lsitenrs del campo de busqueda(JERoundTExField)
		this.vistaConsultas.txtBuscar.addKeyListener(this);
		this.vistaConsultas.txtBuscar.addFocusListener(this);
		
		//Listeners de la tabla
		this.vistaConsultas.tabla.addKeyListener(this);
		this.vistaConsultas.tabla.addFocusListener((FocusListener) this);
		this.vistaConsultas.tabla.addMouseListener(this);
		this.vistaConsultas.tabla.getSelectionModel().addListSelectionListener(this);
		//listeners del popupmenu de la talba
		this.vistaConsultas.itemAgregar.addActionListener(this);
		this.vistaConsultas.itemModificar.addActionListener(this);
		this.vistaConsultas.itemQuitar.addActionListener(this);
		//this.vistaConsultas.tabla.getModel().addTableModelListener((TableModelListener) this);
		//SelectionListener tableListener = new S
		//ListSelectionModel sm=this.vistaConsultas.tabla.getSelectionModel();
		//sm.addListSelectionListener((ListSelectionModel)this);
		//this.vistaConsultas.tabla.addSelectionListener(this);
		//this.vistaConsultas.btnRecargar.addActionListener(this);
	}//fin add Listeners

	////////////////////////Manejo de eventos
	///////////////Acciones
	/**
	 * Eventos de acci�n
	 * Originalmente solo erea el boton de acci�n
	 * pero se agregaron los elemenntos de JPopMenu
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		//variables de instancia
		contAccion++;
		Object[] camposTexto;//peque�o arreglo que agrupo los campos de texto relacionados a los datos de la tabla
		//Fuente Bot�n de acci�n, la acci�n dependeera de la etiqueta actual
		if(a.getSource() == vistaConsultas.btnAccion||a.getSource() == vistaConsultas.txtArt||a.getSource() == vistaConsultas.txtDesc||a.getSource() == vistaConsultas.txtSanc){
			//int fila= vistaConsultas.tabla.getSelectedRow();
			//System.
			camposTexto= new Object[] {vistaConsultas.txtArt,vistaConsultas.txtDesc,vistaConsultas.txtSanc};
			switch(vistaConsultas.btnAccion.getText()) {
				case("Agregar"):{
					try {
						modeloConsultas.validaCampos(camposTexto);
						modeloConsultas.agregaArticulo();
					} catch (EmptyFieldExcepton e) {
						// TODO Auto-generated catch block
						MVC.coloreaCampos(e.camposVacios, MVC.COLOR_INVALID);
					}
					break;
				}//fin acciones bot�n accion estaddo "Agregar"
				case("Modificar"):{
					try {
						modeloConsultas.validaCampos(camposTexto);
						modeloConsultas.modificaArticulo();
					}catch (EmptyFieldExcepton e) {
						// TODO Auto-generated catch block
						if(e.camposVacios.length==3)
							modeloConsultas.campoQuitable();
						else
							MVC.coloreaCampos(e.camposVacios, MVC.COLOR_INVALID);
					}
					break;
				}//fin acciones bot�n accion estaddo "Modificar"
				case("Eliminar"):{
					try {
						modeloConsultas.validaCampos(camposTexto);
						modeloConsultas.quitarCampo();
						vistaConsultas.btnAccion.setText("Modificar");
					} catch (EmptyFieldExcepton e) {
						// TODO Auto-generated catch block
						if(e.camposVacios.length==3) {
							if(vistaConsultas.tabla.getSelectedRow()>=0)
								modeloConsultas.quitarCampo();
							else 
								System.out.println("Error, codigo de Indice negativo");
						}else {
							MVC.coloreaCampos(e.camposVacios, MVC.COLOR_INVALID);
						}
					}
					break;
				}
				
				default:{System.out.println("Error acci�n");break;}
				
			}//fin acciones bot�n accion estaddo "Eliminar"
		//caso origien elemento popup
		}else if(a.getSource().equals(vistaConsultas.itemAgregar)) {
			vistaConsultas.tabla.clearSelection();
			modeloConsultas.limpiaCampos();
			vistaConsultas.btnAccion.setText("Agregar");
		}else if (a.getSource().equals(vistaConsultas.itemModificar)) {				
			modeloConsultas.llenaCampos();
			vistaConsultas.txtArt.requestFocus();
		}else if (a.getSource().equals(vistaConsultas.itemQuitar)) {
			modeloConsultas.quitarCampo();
			modeloConsultas.limpiaCampos();

		}
			//anteriormente eran tres botones de acci�n
			/*switch(((JButton)e.getSource()).getText()){
				case("Agregar"):{
					try {						
						modeloConsultas.validaCampos(campos);
						modeloConsultas.agregaArticulo(vistaConsultas);
						modeloConsultas.limpiaCampos(vistaConsultas);
					}catch(EmptyFieldExcepton ex) {
						MVC.coloreaCampos(campos, MVC.COLOR_INVALID);
					}
				}
					break;
				case("Modificar"):{										
					try {						
						modeloConsultas.validaCampos(campos);
						modeloConsultas.modificaArticulo(vistaConsultas);
						modeloConsultas.limpiaCampos(vistaConsultas);
					}catch(EmptyFieldExcepton ex) {
						MVC.coloreaCampos(campos, MVC.COLOR_INVALID);
					}
					;break;
				}
				case("Quitar"):{
						modeloConsultas.quitarCampo(vistaConsultas);
						modeloConsultas.limpiaCampos(vistaConsultas);
					;break;
					}
				default:{
					System.out.println("Error,Estado no reconocido");
				}
			}*/
			//System.out.println("Modificando");
			
			//archivo=new File(MVC.getConfig().getProperty("articulos"));
			//modeloConsultas.Importar(archivo, vistaConsultas.tabla);
		
			

//		if(e.getSource() == vistaConsultas.btnRecargar){
//			if(selecArchivo.showDialog(null, "Exportar")==JFileChooser.APPROVE_OPTION){
//				archivo=selecArchivo.getSelectedFile();
//				if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
//					JOptionPane.showMessageDialog(null, modeloConsultas.Exportar(archivo, vistaConsultas.tabla) + "\n Formato ."+ archivo.getName().substring(archivo.getName().lastIndexOf(".")+1));
//				}else{
//					JOptionPane.showMessageDialog(null, "Elija un formato valido.");
//				}
//			}
//		}
	}//fin actionPerformed
	///////////////Elementos en Foco	
	/**
	 * Evento foco ganado
	 * se usa principalemtne para cambios de colores de campos y para detectar si se selecciono la tabla
	 */
	@Override
	public void focusGained(FocusEvent f) {
		// TODO Auto-generated method stub
		Object campos[]= {vistaConsultas.txtArt,vistaConsultas.txtDesc,vistaConsultas.txtSanc};
		if(f.getSource().equals(vistaConsultas.tabla)||f.getSource().equals(vistaConsultas.tabla.getComponents())) {
			modeloConsultas.llenaCampos();		
		}else if (f.getSource() instanceof JTextComponent){			
			((JTextComponent)f.getSource()).setBackground(Color.WHITE);
		}
		
		/*	try{
				((JComponent)f.getSource()).setBackground(Color.WHITE);
				modeloConsultas.validaCampos(vistaConsultas);
			}catch(EmptyFieldExcepton e) {
				if(campos.equals(e.camposVacios))
					modeloConsultas.campoQuitable(vistaConsultas);
				else
					MVC.coloreaCampos(f.getSource(), MVC.COLOR_INVALID);
			}		
		}*/
		
	}//fin focusGained
	
	/**
	 * Evento foco perdido
	 * princplamente para cambio de colores de campos
	 */
	
	@Override
	public void focusLost(FocusEvent f) {		
		// TODO Auto-generated method stub
		if (!f.getComponent().getBackground().equals(MVC.COLOR_INVALID)){
			if(f.getSource().equals(vistaConsultas.txtArt)||f.getSource().equals(vistaConsultas.txtDesc)||f.getSource().equals(vistaConsultas.txtSanc)) {
				((JTextComponent)f.getSource()).setBackground(MVC.COLOR_VALID);
			}else if (f.getSource().equals(vistaConsultas.txtBuscar)){			
				((JTextComponent)f.getSource()).setBackground(Color.WHITE);
			}
		}
		/*if(e.getSource().equals(vistaConsultas.tabla)) {
			//limpiaCampos();
		}*/
			
	}//Fin focusLost
	///////////////Eventos de teclado	
	/**
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Evento tecla soltada
	 */
	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub

		///////////Tecla Escape: limpia los campos
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			modeloConsultas.limpiaCampos();
			vistaConsultas.btnAccion.setText("Agregar");
		}
		///////////Tecla Eliminar/suprimir y retroceso: si seta en un campo de texto no hace nada, en cualquier otro lado borra el elemento actual de la tabla
		if(k.getKeyCode() == KeyEvent.VK_DELETE || k.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			//System.out.println("se presiono la tecla suprimir");
			//System.out.println("fila= "+vistaConsultas.tabla.getSelectedRow());
			if((!vistaConsultas.txtArt.hasFocus() && !vistaConsultas.txtDesc.hasFocus() && !vistaConsultas.txtSanc.hasFocus() && !vistaConsultas.txtBuscar.hasFocus()) && vistaConsultas.tabla.getSelectedRow()>=0) {
				modeloConsultas.quitarCampo();
				modeloConsultas.limpiaCampos();
			}
		}
		
		////////////Origen campo Busqueda: realiza el filtrado
		if(k.getSource().equals(vistaConsultas.txtBuscar)) {
			modeloConsultas.filtrar(""+vistaConsultas.txtBuscar.getText());
			if(modeloConsultas.filtro.getViewRowCount()>0)
				vistaConsultas.txtBuscar.setBackground(MVC.COLOR_VALID);
			else
				vistaConsultas.txtBuscar.setBackground(MVC.COLOR_INVALID);
		}
		
		///////////Origen campos de texto relacionados a la tabla, los valida
		if((k.getSource().equals(vistaConsultas.txtArt))||(k.getSource().equals(vistaConsultas.txtDesc))||(k.getSource().equals(vistaConsultas.txtArt))){
			Object camposTexto[] ={vistaConsultas.txtArt,vistaConsultas.txtDesc,vistaConsultas.txtSanc};
			//Object camp[]= {k.getSource()};
			//comportamiendo  basado en el estado del boton de acci�n
			switch(vistaConsultas.btnAccion.getText()) {				
				case("Agregar"):{
					try {
						modeloConsultas.validaCampos(camposTexto);
						((JTextComponent)k.getSource()).setBackground(MVC.COLOR_VALID);
					} catch (EmptyFieldExcepton e) {
						// TODO Auto-generated catch block
						MVC.coloreaCampos(e.camposVacios, MVC.COLOR_INVALID);
					}
					break;
				}
				case("Modificar"):{
					try {
						modeloConsultas.validaCampos(camposTexto);
						((JTextComponent)k.getSource()).setBackground(MVC.COLOR_VALID);
					} catch (EmptyFieldExcepton e) {
						// TODO Auto-generated catch block
						if(e.camposVacios.equals(camposTexto))
							modeloConsultas.campoQuitable();
						else
							MVC.coloreaCampos(e.camposVacios, MVC.COLOR_INVALID);
					}
					break;
				}
				case("Eliminar"):{
					try {
						modeloConsultas.validaCampos(camposTexto);
						MVC.coloreaCampos(camposTexto, MVC.COLOR_VALID);
						vistaConsultas.btnAccion.setText("Modificar");
					} catch (EmptyFieldExcepton e) {
						// TODO Auto-generated catch block
						if(e.camposVacios.equals(camposTexto)) {
							if(vistaConsultas.tabla.getSelectedRow()>=0)
								((JTextComponent)k.getSource()).setBackground(MVC.COLOR_VALID);
							else 
								System.out.println("Error, codigo de Indice negativo");
						}else {
							MVC.coloreaCampos(e.camposVacios, MVC.COLOR_INVALID);
						}
					}
					break;
				}
				
				default:{
					System.out.println("Error tecla");
				}
			}
			
		}
		//antes solo checaba los campos relacionados a la tambla
		/*	try{
				modeloConsultas.validaCampos(campo);
				modeloConsultas.validaCampos(campos);
				MVC.coloreaCampo(campo, MVC.COLOR_VALID);
			}catch(EmptyFieldExcepton e) {
				if(campos.equals(e.camposVacios))
					modeloConsultas.campoQuitable(vistaConsultas);
				else
					MVC.coloreaCampo(campo, MVC.COLOR_INVALID);
					
			}			
		}*/
	}//fin KeyReleeased
	
	/**
	 * tecla presionada
	 * originalemtne aqui trataba de realizar aqu� las validaciones, pero debido a una desincronizaci�n con ls verificaciones se movieron las erificaciones a Keyreleased
	 */
	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
	}//fin keyTiped
	
	///////////////Eventos del Raton
	@Override
	/**
	 * 
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * evento mouse entra al area de un comonente
	 * si es un campo lo ilumnia
	 * precondici�n: no se encuentra ene stado de error
	 */
	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		if((m.getSource().equals(vistaConsultas.txtArt)||m.getSource().equals(vistaConsultas.txtDesc)||m.getSource().equals(vistaConsultas.txtSanc)) && !(((JComponent)m.getSource()).hasFocus()) && !(((JTextComponent)m.getSource()).getBackground().equals(MVC.COLOR_INVALID))) {
				((JTextComponent)m.getSource()).setBackground(MVC.COLOR_HIGHLIGHT);
		}
	}//fin mouseEntered
	
	/**
	 * evento cursor abandona el area un componente
	 * cambia el color a su estado normal
	 * precondici�n: el compnoente no se encuentra en estado de error 
	 */
	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub		
		if((m.getSource().equals(vistaConsultas.txtArt)||m.getSource().equals(vistaConsultas.txtDesc)||m.getSource().equals(vistaConsultas.txtSanc))&& !(((JComponent)m.getSource()).hasFocus()) && !(((JTextComponent)m.getSource()).getBackground().equals(MVC.COLOR_INVALID))) {
			if(!((JComponent)m.getSource()).hasFocus()&&!((JTextComponent)m.getSource()).getBackground().equals(MVC.COLOR_INVALID)){
				((JTextComponent)m.getSource()).setBackground(MVC.COLOR_VALID);
			}
		}
	}//fin mouse Exited
	
	/**
	 * evento se presiona el boton del mouse
	 * masque nada se utiliza en caso de que se clicke� un panel, este pide foco, lo que permite que se pueda presionar la tecla ESC en cualquier parte y que esta siga cumpliendo su funci�n de limppiar los campos
	 * cuando agregue el popup menu tambien detecta si se presionola tabla para mostrarlo y sus elementos*/
	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		//caso tabla origen tabla
		if(m.getSource().equals(vistaConsultas.tabla)) {
			vistaConsultas.tabla.requestFocus();
			//System.out.println("Herp");
			if (SwingUtilities.isRightMouseButton(m))
				vistaConsultas.popupMenu.show(m.getComponent(),m.getX(),m.getY());
			//selecciona la columna seleccionada (en caso que el se presione el boton derecho, con en izquierdo ocurre por default
			Point p= m.getPoint();
			int currentRow= vistaConsultas.tabla.rowAtPoint(p);
			vistaConsultas.tabla.setRowSelectionInterval(currentRow, currentRow);
		//si el elemento oprimido no es es la tabla oculta el menu popup
		}//else
		//	vistaConsultas.popupMenu.hide();
		

			//origen paneles en general			
		if (m.getSource() instanceof JPanel){			

			((JPanel)m.getSource()).requestFocus();
		}else if (m.getSource() == vistaConsultas.getContentPane()){			
			vistaConsultas.getContentPane().requestFocus();
		} 
		
		
	}//fin mousePressed
	
	/**
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	////////Eventos en el modelo de tabla
	/**
	 * detecto un cambio en el modelo de datos
	 * llena los campos en base al renglon seleccionado actualmente
	 */
	public void valueChanged(ListSelectionEvent t) {
		modeloConsultas.llenaCampos();
	}//fin valueChanged
	
	/**
	 * Evento de cambios en la tabla
	 * lo desactive para evitar bucle infinito de actualizaciones
	 */
	public void tableChanged(TableModelEvent t) {
		((Component)t.getSource()).setBackground(Color.LIGHT_GRAY);
		modeloConsultas.limpiaCampos();
	}//fin table changed
	
	
}// fin clase principal