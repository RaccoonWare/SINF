/**
 * ventana Consulta registro de infracciones
 * @author Mario
 */
package vista;
/* importación de librerias */
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
import com.toedter.calendar.JDateChooser;

import controlador.MVC;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
/**
 * clase prinicpal
 * @author Mario
 * @see MoeloConsultaInfracciones
 * @see ControladorConsultaInfracciones
 */
public class VistaConsultaInfraccion extends JInternalFrame {
	//variables de clase
	private static final long serialVersionUID = 1L;
	////variables de instancia
	public JTable tabla;//tabla
	public static String nomTabla;
	public static String dato, titulo;
	public JTextField txtFolio;//"Etiqueta Folio"
	public JTextField txtPlaca;//Etiqueta "Placa"
	public JButton btnEliminar, btnAnular;//botoes anular y eliminar
	public JButton btnConsultar;//boton consulatr
	
	////////Constructores e inicializadores
	/**
	 * constructor por defecto
	 */
	public VistaConsultaInfraccion() {
		//Configuraión ventana
		setBorder(new LineBorder(MVC.COLOR_BG, 5, true));//cambia el borde
		getContentPane().setBackground(Color.WHITE);//color de fondo
		setTitle(titulo);//titulo ventana
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 10, 577, 488);//cambia tamaño
		getContentPane().setLayout(new MigLayout("", "[106.00,grow]", "[top][grow,fill][bottom]"));//establece manejador de layout

		//Inicializa componentes
		
		//inicializa panel principal
		JPanel panel = new JPanel();
		panel.setBackground(MVC.COLOR_BG);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(245, 255, 250)));
		getContentPane().add(panel, "cell 0 0,growx,aligny top");
		panel.setLayout(new MigLayout("", "[][grow,fill][grow]", "[grow][grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(scrollPane, "cell 0 1,grow");
		//inicializa  etiquetas
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(MVC.FUENTE);
		lblFecha.setForeground(MVC.COLOR_HIGHLIGHT);
		lblFecha.setBackground(Color.RED);
		panel.add(lblFecha, "cell 0 0");
		
		JLabel lblFolio = new JLabel("Folio");
		lblFolio.setFont(MVC.FUENTE);
		lblFolio.setForeground(MVC.COLOR_HIGHLIGHT);
		panel.add(lblFolio, "flowx,cell 2 0");
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(MVC.FUENTE);
		lblPlaca.setForeground(MVC.COLOR_HIGHLIGHT);
		panel.add(lblPlaca, "flowx,cell 2 1,alignx left");
		
		JLabel lblRegistros = new JLabel("Registros");
		lblRegistros.setFont(MVC.FUENTE);
		lblRegistros.setForeground(MVC.COLOR_HIGHLIGHT);
		panel.add(lblRegistros, "cell 2 1");
		
		//Inicializa campos de texto
		
		txtFolio = new JTextField();
		txtFolio.setBorder(null);
		txtFolio.setFont(MVC.FUENTE);
		txtFolio.setBackground(MVC.COLOR_VALID);
		txtFolio.setColumns(10);
		panel.add(txtFolio, "cell 2 0,growx");
		
		txtPlaca = new JTextField();
		txtPlaca.setBorder(null);
		txtPlaca.setFont(MVC.FUENTE);
		txtPlaca.setBackground(MVC.COLOR_VALID);
		txtPlaca.setColumns(10);
		panel.add(txtPlaca, "cell 2 1,growx");
	
		//Inicializa selectores de fecha
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBorder(null);
		dateChooser.setToolTipText("Indicar la fecha.");
		dateChooser.setFont(MVC.FUENTE);
		dateChooser.setBackground(MVC.COLOR_VALID);
		panel.add(dateChooser, "cell 1 0,alignx trailing");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBorder(null);
		dateChooser_1.setToolTipText("Indicar la fecha.");
		dateChooser_1.setFont(MVC.FUENTE);
		dateChooser_1.setBackground(MVC.COLOR_VALID);
		panel.add(dateChooser_1, "cell 1 1,alignx trailing");
		
		//Inicializa combobox
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBorder(null);
		comboBox.setFont(MVC.FUENTE);
		comboBox.setForeground(MVC.COLOR_BG);
		comboBox.setBackground(MVC.COLOR_VALID);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ambos", "Activos", "Anulados"}));
		panel.add(comboBox, "cell 2 1");
		
		
		//incicializa tabla
		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabla.setBackground(Color.WHITE);
		tabla.setFont(MVC.FUENTE);
		scrollPane.setViewportView(tabla);
		
		//Inicializa botones
		
		JButton btnModficar = new JButton("Modficar");
		btnModficar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModficar.setBackground(Color.WHITE);
		btnModficar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModficar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModficar.setFont(MVC.FUENTE);
		
		JButton btnBusqueda = new JButton("");
		btnBusqueda.setIcon(new ImageIcon(VistaConsultaInfraccion.class.getResource("/iconos/icons8-b\u00FAsqueda-14(1).png")));
		btnBusqueda.setBackground(MVC.COLOR_HIGHLIGHT);
		panel.add(btnBusqueda, "cell 0 1,alignx trailing");
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setFont(MVC.FUENTE);
		btnEliminar.setBackground(MVC.COLOR_BG);
		btnEliminar.setForeground(MVC.COLOR_HIGHLIGHT);
		getContentPane().add(btnEliminar, "flowx,cell 0 2,growx");
		
		btnAnular = new JButton("Anular");		
		btnAnular.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnular.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnular.setFont(MVC.FUENTE);
		btnAnular.setBackground(MVC.COLOR_BG);
		btnAnular.setForeground(MVC.COLOR_HIGHLIGHT);
		getContentPane().add(btnAnular, "cell 0 2,growx");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConsultar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultar.setFont(MVC.FUENTE);
		btnConsultar.setBackground(MVC.COLOR_BG);
		btnConsultar.setForeground(MVC.COLOR_HIGHLIGHT);
		getContentPane().add(btnConsultar, "cell 0 2,growx");
	}//fin constructor por defecto
	
	
//Anteriormente se hacia la importación de datos desde esta vetana, ahora esa función se encuentra en el modelo y se llama desde el controlador	
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
