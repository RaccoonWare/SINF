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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
public class VistaConsultaInfraccion extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable tabla;
	public static String nomTabla;
	public static String dato, titulo;
	private JTextField textField_2;
	private JTextField textField_3;
	public JButton btnEliminar, btnAnular, btnConsultar, btnRecargar;
	public VistaConsultaInfraccion() {
		getContentPane().setBackground(Color.WHITE);
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 10, 577, 488);
		getContentPane().setLayout(new MigLayout("", "[106.00,grow]", "[grow][grow][]"));

		JButton btnModficar = new JButton("Modficar");
		btnModficar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModficar.setBackground(Color.WHITE);
		btnModficar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModficar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModficar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consultas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow,fill][grow]", "[grow][grow][]"));
		
		JLabel lblFecha = new JLabel("Fecha");
		panel.add(lblFecha, "cell 0 0");
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setToolTipText("Indicar la fecha.");
		dateChooser.setFont(new Font("Arial", Font.BOLD, 12));
		dateChooser.setBackground(new Color(204, 255, 255));
		panel.add(dateChooser, "cell 1 0,alignx trailing");
		
		JLabel lblFolio = new JLabel("Folio");
		panel.add(lblFolio, "flowx,cell 2 0");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel.add(textField_2, "cell 2 0,growx");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setToolTipText("Indicar la fecha.");
		dateChooser_1.setFont(new Font("Arial", Font.BOLD, 12));
		dateChooser_1.setBackground(new Color(204, 255, 255));
		panel.add(dateChooser_1, "cell 1 1,alignx trailing");
		
		JLabel lblPlaca = new JLabel("Placa");
		panel.add(lblPlaca, "flowx,cell 2 1,alignx left");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel.add(textField_3, "cell 2 1,growx");
		
		JLabel lblRegistros = new JLabel("Registros");
		panel.add(lblRegistros, "cell 2 1");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Activos", "Anulados"}));
		panel.add(comboBox, "cell 2 1");
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 1,grow");
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
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
		tabla.setFont(new Font("Arial", Font.BOLD, 14));
		scrollPane.setViewportView(tabla);
		tabla.setComponentPopupMenu(popupMenu);
		
		btnRecargar = new JButton("Recargar");
		btnRecargar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRecargar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRecargar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRecargar.setBackground(Color.WHITE);
		getContentPane().add(btnRecargar, "flowx,cell 0 2,growx");
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBackground(Color.WHITE);
		getContentPane().add(btnEliminar, "cell 0 2,growx");
		
		btnAnular = new JButton("Anular");
		btnAnular.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnular.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnular.setFont(new Font("Arial", Font.BOLD, 14));
		btnAnular.setBackground(Color.WHITE);
		getContentPane().add(btnAnular, "cell 0 2,growx");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConsultar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConsultar.setBackground(Color.WHITE);
		getContentPane().add(btnConsultar, "cell 0 2,growx");
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
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
