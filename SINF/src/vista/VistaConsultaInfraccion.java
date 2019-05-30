package vista;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
public class VistaConsultaInfraccion extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable tabla;
	public String nomTabla;
	public JTextField txtFolio;
	public JTextField txtPlaca;
	public JButton btnEliminar, btnAnular, btnConsultar, btnBuscar;
	public JDateChooser dcInicio, dcFin;
	public JComboBox<?> cbRegistros;

	public VistaConsultaInfraccion() {
		setBorder(new LineBorder(new Color(58, 63, 64), 5, true));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Consulta infracciones");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(10, 10, 577, 488);
		getContentPane().setLayout(new MigLayout("", "[106.00,grow]", "[top][grow,fill][bottom]"));

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

		dcInicio = new JDateChooser();
		dcInicio.setBorder(null);
		dcInicio.setToolTipText("Indicar la fecha.");
		dcInicio.setFont(new Font("Arial", Font.BOLD, 14));
		dcInicio.setBackground(new Color(187,202,204));
		panel.add(dcInicio, "cell 1 0,alignx trailing");

		JLabel lblFolio = new JLabel("Folio");
		lblFolio.setFont(new Font("Arial", Font.BOLD, 14));
		lblFolio.setForeground(new Color(234, 254, 255));
		panel.add(lblFolio, "flowx,cell 2 0");

		txtFolio = new JTextField();
		txtFolio.setForeground(new Color(58, 63, 64));
		txtFolio.setBorder(null);
		txtFolio.setFont(new Font("Arial", Font.BOLD, 14));
		txtFolio.setBackground(new Color(187,202,204));
		txtFolio.setColumns(10);
		panel.add(txtFolio, "cell 2 0,growx");

		btnBuscar = new JButton(/*"Buscar"*/);
		btnBuscar.setBorder(null);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setIcon(new ImageIcon(VistaConsultaInfraccion.class.getResource("/iconos/buscar.png")));
		btnBuscar.setBackground(new Color(234, 254, 255));
		panel.add(btnBuscar, "cell 0 1,growx");

		dcFin = new JDateChooser();
		dcFin.setBorder(null);
		dcFin.setToolTipText("Indicar la fecha.");
		dcFin.setFont(new Font("Arial", Font.BOLD, 14));
		dcFin.setBackground(new Color(187,202,204));
		panel.add(dcFin, "cell 1 1,alignx trailing");

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Arial", Font.BOLD, 14));
		lblPlaca.setForeground(new Color(234, 254, 255));
		panel.add(lblPlaca, "flowx,cell 2 1,alignx left");

		txtPlaca = new JTextField();
		txtPlaca.setForeground(new Color(58, 63, 64));
		txtPlaca.setBorder(null);
		txtPlaca.setFont(new Font("Arial", Font.BOLD, 14));
		txtPlaca.setBackground(new Color(187,202,204));
		txtPlaca.setColumns(10);

		panel.add(txtPlaca, "cell 2 1,growx");

		JLabel lblRegistros = new JLabel("Registros");
		lblRegistros.setFont(new Font("Arial", Font.BOLD, 14));
		lblRegistros.setForeground(new Color(234, 254, 255));
		panel.add(lblRegistros, "cell 2 1");

		cbRegistros = new JComboBox();
		cbRegistros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbRegistros.setBorder(null);
		cbRegistros.setFont(new Font("Arial", Font.BOLD, 14));
		cbRegistros.setForeground(new Color(58, 63, 64));
		cbRegistros.setBackground(new Color(187,202,204));
		cbRegistros.setModel(new DefaultComboBoxModel(new String[] {"Ambos", "Activos", "Anulados"}));
		panel.add(cbRegistros, "cell 2 1");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(scrollPane, "cell 0 1,grow");
		tabla = new JTable();
		tabla.setFocusable(false);
		tabla.setFillsViewportHeight(true);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tabla.setSelectionForeground(new Color(234, 254, 255));
		tabla.setSelectionBackground(new Color(58, 63, 64));
		tabla.getTableHeader().setReorderingAllowed(false) ;
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabla.setBackground(Color.WHITE);
		tabla.setFont(new Font("Arial", Font.BOLD, 14));
		scrollPane.setViewportView(tabla);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBorder(null);
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(58, 63, 64));
		btnEliminar.setForeground(new Color(234, 254, 255));
		getContentPane().add(btnEliminar, "flowx,cell 0 2,growx");

		btnAnular = new JButton("Anular");
		btnAnular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnular.setBorder(null);
		btnAnular.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnular.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnular.setFont(new Font("Arial", Font.BOLD, 14));
		btnAnular.setBackground(new Color(58, 63, 64));
		btnAnular.setForeground(new Color(234, 254, 255));
		getContentPane().add(btnAnular, "cell 0 2,growx");

		btnConsultar = new JButton("Consultar");
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar.setBorder(null);
		btnConsultar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConsultar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConsultar.setBackground(new Color(58, 63, 64));
		btnConsultar.setForeground(new Color(234, 254, 255));
		getContentPane().add(btnConsultar, "cell 0 2,growx");
	}
}
