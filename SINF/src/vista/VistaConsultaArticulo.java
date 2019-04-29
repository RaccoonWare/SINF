/**
 * Ventana consulta articulos
 * @author Mario 
 */
package vista;
/* importación librerias */
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controlador.MVC;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
 * Clase pricipal
 * @author David
 * @see ModeloConsultaArticulos
 * @see ControladorConsultaArticulos
 */
public class VistaConsultaArticulo extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	//Variables de clase
	public static String nomTabla;
	public static String dato, titulo;
	//Variables de instacia
	public JTextField txtBuscar;//campo de texto de busqueda (JERRoundTextfield)
	public JTextField txtArt,txtDesc, txtSanc;//campos de texto relacionados al contenido de la tabla
	public JButton btnAccion;//boton acción, dependiendo del contexto puede agregar, actualizar o quitar los articulos de la tabla
	public JTable tabla;
	public JPanel panel, panel_1;//paneles interos, panel_1 es solo estetico, panel cotiene los campos de edición
	
	/**
	 * Constructor por defecto
	 */
	public VistaConsultaArticulo() {
		//Configuración de la ventana
		setBorder(new LineBorder(MVC.COLOR_BG, 5, true));//cambia el borde
		getContentPane().setBackground(Color.GRAY);//fondo
		setTitle("Articulos");//titulo
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 10, 745, 488);//tamaño por defecto
		getContentPane().setLayout(new MigLayout("", "[106.00,grow]", "[][grow][grow][grow]"));//gestor de layers
		//inicializa componentes

		//inicializa paneles
		panel_1 = new JPanel();
		getContentPane().add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[]", "[]"));
		panel_1.setBackground(Color.DARK_GRAY);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar/Modificar Articulo", TitledBorder.LEADING, TitledBorder.TOP, null, MVC.COLOR_HIGHLIGHT));
		((javax.swing.border.TitledBorder)panel.getBorder()).setTitleFont(MVC.FUENTE);
		getContentPane().add(panel, "cell 0 3,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow][][]"));
		panel.setBackground(Color.DARK_GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 2,grow");
		
		//Inicializa Etiquetas
		JLabel lblPlaca = new JLabel("Articulo");
		lblPlaca.setFont(MVC.FUENTE);
		lblPlaca.setForeground(MVC.COLOR_HIGHLIGHT);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(MVC.FUENTE);
		lblDescripcin.setForeground(MVC.COLOR_HIGHLIGHT);
		
		JLabel lblRegistros = new JLabel("Sanci\u00F3n");
		lblRegistros.setFont(MVC.FUENTE);
		lblRegistros.setForeground(MVC.COLOR_HIGHLIGHT);
		
		
		//inicializa campos de texto
		////Campo de busqueda (JERRoudTextField)
		txtBuscar = new JERoundTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscar.setFont(new Font("Arial", Font.BOLD, 12));
		txtBuscar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(txtBuscar, "cell 0 0,growx");
		txtBuscar.setColumns(10);
		////Campos regulares
		txtArt = new JTextField();
		txtArt.setColumns(10);
		txtArt.setBackground(MVC.COLOR_VALID);
		txtArt.setFont(MVC.FUENTE);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBackground(MVC.COLOR_VALID);
		txtDesc.setFont(MVC.FUENTE);
		
		txtSanc = new JTextField();
		txtSanc.setColumns(10);
		txtSanc.setFont(MVC.FUENTE);
		txtSanc.setBackground(MVC.COLOR_VALID);
		
		////ordena los componenes en panel
		panel.add(lblPlaca, "flowx,cell 0 0,alignx left");
		panel.add(txtArt, "cell 0 0,alignx left");
		panel.add(lblDescripcin, "cell 0 0");
		panel.add(txtDesc, "cell 0 0,growx");
		panel.add(lblRegistros, "cell 0 0");
		panel.add(txtSanc, "cell 0 0,growx");
		
		
		//inicializa tabla
		tabla = new JTable();
		tabla.setFont(new Font("Arial", Font.BOLD, 14));
		tabla.setBackground(Color.WHITE);
		scrollPane.setViewportView(tabla);
				
		//Inicializa boton
		btnAccion = new JButton("Agregar");		
		btnAccion.setFont(MVC.FUENTE);
		
		panel.add(btnAccion, "cell 0 0,alignx right");
		btnAccion.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAccion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAccion.setFont(MVC.FUENTE);
		btnAccion.setBackground(Color.WHITE);
		
		//antes se llamaba boton modificar
				/*JButton btnModficar = new JButton("Modficar");
				btnModficar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnModficar.setBackground(Color.WHITE);
				btnModficar.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnModficar.setHorizontalTextPosition(SwingConstants.CENTER);
				btnModficar.setFont(MVC.FUENTE);*/
	}//fin constructor por defecto
	
//	Anteriormente la importación de datos se hacia desde aqui, ahora es una función del modelo	
//	public void traerdatos() {
//		int fila=tabla.getSelectedRow();//es paara traer la fila seleccionada el getSelectedRow
//		if (Principal.usuario=="Invitado") {//verificar el tipo de usuario Para asignarle permisos
//			JOptionPane.showMessageDialog(null, "Lo siento no tienes permisos para realizar esta accion");
//		}else {
//			switch (nomTabla){
//			case "persona":	
//				if (fila>-1) {//el getSelectedRow empieza del -1 es igual a ==0, indica que no tenemos ninguno seleccionado
//					persona Co= new persona();//para poder modificar una clase es necesario crear su clase MODIFICARLIBRO
//					Principal.dpEscritorio.add(Co);
//					Co.show();
//					dato=(String) tabla.getValueAt(fila, 0);
//					try {
//						conexiones.ModificarBD.rellenarpersona(dato);
//					} catch (HeadlessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//						//                                  } catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (ParseException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "No se a detectado ninguan fila seleccionada");
//				}
//				break;
//			case "revista":
//				if (fila>-1) {
//					revista Co= new revista();//para poder modificar una clase es necesario crear su clase MODIFICARLIBRO
//					Principal.dpEscritorio.add(Co);
//					Co.show();
//					dato=(String) tabla.getValueAt(fila, 0);
//					try {
//						conexiones.ModificarBD.rellenarrevista(dato);
//					} catch (HeadlessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (ParseException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "No se a detectado ninguan fila seleccionada");
//
//				}break;
//			case "disco":
//				if (fila>-1) {
//					disco Md= new disco();
//					Principal.dpEscritorio.add(Md);
//					Md.show();
//					dato=(String) tabla.getValueAt(fila, 0);
//					try {
//						conexiones.ModificarBD.rellenarDisco(dato);
//					} catch (HeadlessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (ParseException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "No se a detectado ninguan fila seleccionada");
//				}break;
//			case "libro":
//				if (fila>-1) {
//					libro Co= new libro();//para poder modificar una clase es necesario crear su clase MODIFICARLIBRO
//					Principal.dpEscritorio.add(Co);
//					Co.show();
//					dato=(String) tabla.getValueAt(fila, 0);
//					try {
//						conexiones.ModificarBD.rellenalibro(dato);
//					} catch (HeadlessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (ParseException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "No se a detectado ninguan fila seleccionada");
//
//				}break;
//			case "tesoreria":
//				if (fila>-1) {
//					tesoreria Co= new tesoreria();//para poder modificar una clase es necesario crear su clase MODIFICARLIBRO
//					Principal.dpEscritorio.add(Co);
//					Co.show();
//					dato=(String) tabla.getValueAt(fila, 0);
//					try {
//						conexiones.ModificarBD.rellenartesoreria(dato);
//					} catch (HeadlessException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (ParseException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "No se a detectado ninguan fila seleccionada");
//				}
//			}
//		}
//	}
}//fin clase principal
