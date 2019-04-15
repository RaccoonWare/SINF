package vista;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.Button;
public class VistaConsultaArticulo extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String nomTabla;
	public static String dato, titulo;
	public JTextField txtBuscar;
	public JTextField txtArt;
	public JButton btnAccion;
	public JTextField txtDesc;
	public JTextField txtSanc;
	public JTable tabla;
	public JPanel panel, panel_1;
	public VistaConsultaArticulo() {
		getContentPane().setBackground(Color.GRAY);
		setTitle("Articulos");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 10, 745, 488);
		getContentPane().setLayout(new MigLayout("", "[106.00,grow]", "[][grow][grow][grow]"));

		JButton btnModficar = new JButton("Modficar");
		btnModficar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModficar.setBackground(Color.WHITE);
		btnModficar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModficar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModficar.setFont(MVC.FUENTE);
		
		txtBuscar = new JERoundTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscar.setFont(new Font("Arial", Font.BOLD, 12));
		txtBuscar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(txtBuscar, "cell 0 0,growx");
		txtBuscar.setColumns(10);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[]", "[]"));
		panel_1.setBackground(Color.DARK_GRAY);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar/Modificar Articulo", TitledBorder.LEADING, TitledBorder.TOP, null, MVC.COLOR_LETRA));
		((javax.swing.border.TitledBorder)panel.getBorder()).setTitleFont(MVC.FUENTE);
		getContentPane().add(panel, "cell 0 3,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow][][]"));
		panel.setBackground(Color.DARK_GRAY);
		
		JLabel lblPlaca = new JLabel("Articulo");
		lblPlaca.setFont(MVC.FUENTE);
		lblPlaca.setForeground(MVC.COLOR_LETRA);
		panel.add(lblPlaca, "flowx,cell 0 0,alignx left");
		
		txtArt = new JTextField();
		txtArt.setColumns(10);
		txtArt.setBackground(MVC.COLOR_VALID);
		txtArt.setFont(MVC.FUENTE);
		panel.add(txtArt, "cell 0 0,alignx left");
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setFont(MVC.FUENTE);
		lblDescripcin.setForeground(MVC.COLOR_LETRA);
		panel.add(lblDescripcin, "cell 0 0");
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBackground(MVC.COLOR_VALID);
		txtDesc.setFont(MVC.FUENTE);
		panel.add(txtDesc, "cell 0 0,growx");
		
		JLabel lblRegistros = new JLabel("Sanci\u00F3n");
		lblRegistros.setFont(MVC.FUENTE);
		lblRegistros.setForeground(MVC.COLOR_LETRA);
		panel.add(lblRegistros, "cell 0 0");
		
		
		txtSanc = new JTextField();
		txtSanc.setColumns(10);
		txtSanc.setFont(MVC.FUENTE);
		txtSanc.setBackground(MVC.COLOR_VALID);
		panel.add(txtSanc, "cell 0 0,growx");
		
		btnAccion = new JButton("Agregar");		
		btnAccion.setFont(MVC.FUENTE);
		
		panel.add(btnAccion, "cell 0 0,alignx right");
		btnAccion.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAccion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAccion.setFont(new Font("Arial", Font.BOLD, 14));
		btnAccion.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 2,grow");
		
		tabla = new JTable();
		tabla.setFont(new Font("Arial", Font.BOLD, 14));
		tabla.setBackground(Color.WHITE);
		scrollPane.setViewportView(tabla);
	}
	
	
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
}
