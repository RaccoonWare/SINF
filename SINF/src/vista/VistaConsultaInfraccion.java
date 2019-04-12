package vista;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class VistaConsultaInfraccion extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable tabla;
	public static String nomTabla;
	public static String dato, titulo;
	public JTextField txtFolio;
	public JTextField txtPlaca;
	public JButton btnEliminar, btnAnular;
	public JButton btnConsultar;
	public VistaConsultaInfraccion() {
		setBorder(new LineBorder(new Color(58, 63, 64), 5, true));
		getContentPane().setBackground(Color.WHITE);
		setTitle(titulo);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 10, 577, 488);
		getContentPane().setLayout(new MigLayout("", "[106.00,grow]", "[top][grow,fill][bottom]"));

		JButton btnModficar = new JButton("Modficar");
		btnModficar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModficar.setBackground(Color.WHITE);
		btnModficar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModficar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModficar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(58, 63, 64));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(245, 255, 250)));
		getContentPane().add(panel, "cell 0 0,growx,aligny top");
		panel.setLayout(new MigLayout("", "[][grow,fill][grow]", "[grow][grow][]"));
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Arial", Font.BOLD, 14));
		lblFecha.setForeground(new Color(234, 254, 255));
		lblFecha.setBackground(Color.RED);
		panel.add(lblFecha, "cell 0 0");
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBorder(null);
		dateChooser.setToolTipText("Indicar la fecha.");
		dateChooser.setFont(new Font("Arial", Font.BOLD, 14));
		dateChooser.setBackground(new Color(187,202,204));
		panel.add(dateChooser, "cell 1 0,alignx trailing");
		
		JLabel lblFolio = new JLabel("Folio");
		lblFolio.setFont(new Font("Arial", Font.BOLD, 14));
		lblFolio.setForeground(new Color(234, 254, 255));
		panel.add(lblFolio, "flowx,cell 2 0");
		
		txtFolio = new JTextField();
		txtFolio.setBorder(null);
		txtFolio.setFont(new Font("Arial", Font.BOLD, 14));
		txtFolio.setBackground(new Color(187,202,204));
		txtFolio.setColumns(10);
		panel.add(txtFolio, "cell 2 0,growx");
		
		JButton btnBusqueda = new JButton("");
		btnBusqueda.setIcon(new ImageIcon(VistaConsultaInfraccion.class.getResource("/iconos/icons8-b\u00FAsqueda-14(1).png")));
		btnBusqueda.setBackground(new Color(234, 254, 255));
		panel.add(btnBusqueda, "cell 0 1,alignx trailing");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBorder(null);
		dateChooser_1.setToolTipText("Indicar la fecha.");
		dateChooser_1.setFont(new Font("Arial", Font.BOLD, 14));
		dateChooser_1.setBackground(new Color(187,202,204));
		panel.add(dateChooser_1, "cell 1 1,alignx trailing");
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Arial", Font.BOLD, 14));
		lblPlaca.setForeground(new Color(234, 254, 255));
		panel.add(lblPlaca, "flowx,cell 2 1,alignx left");
		
		txtPlaca = new JTextField();
		txtPlaca.setBorder(null);
		txtPlaca.setFont(new Font("Arial", Font.BOLD, 14));
		txtPlaca.setBackground(new Color(187,202,204));
		txtPlaca.setColumns(10);
		
		panel.add(txtPlaca, "cell 2 1,growx");
		
		JLabel lblRegistros = new JLabel("Registros");
		lblRegistros.setFont(new Font("Arial", Font.BOLD, 14));
		lblRegistros.setForeground(new Color(234, 254, 255));
		panel.add(lblRegistros, "cell 2 1");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBorder(null);
		comboBox.setFont(new Font("Arial", Font.BOLD, 14));
		comboBox.setForeground(new Color(58, 63, 64));
		comboBox.setBackground(new Color(187,202,204));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ambos", "Activos", "Anulados"}));
		panel.add(comboBox, "cell 2 1");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(scrollPane, "cell 0 1,grow");
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
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(58, 63, 64));
		btnEliminar.setForeground(new Color(234, 254, 255));
		getContentPane().add(btnEliminar, "flowx,cell 0 2,growx");
		
		btnAnular = new JButton("Anular");
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnular.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnular.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnular.setFont(new Font("Arial", Font.BOLD, 14));
		btnAnular.setBackground(new Color(58, 63, 64));
		btnAnular.setForeground(new Color(234, 254, 255));
		getContentPane().add(btnAnular, "cell 0 2,growx");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConsultar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConsultar.setBackground(new Color(58, 63, 64));
		btnConsultar.setForeground(new Color(234, 254, 255));
		getContentPane().add(btnConsultar, "cell 0 2,growx");
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
