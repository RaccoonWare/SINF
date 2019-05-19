package vista;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import time.*;
import java.awt.SystemColor;

public class VistaInfraccion extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txtNplacas;
	public JTextField txtNserie;
	public JTextField txtModelo;
	public JComboBox<?> cbInfraccion;
	ButtonGroup g1 = new ButtonGroup();
	public JDateChooser dcFecha;
	public JTextField txtReferencias;
	public JTextField txtPlacasEstado;
	private JLabel lblActividadesADesarrollar;
	public JTextPane txtArticulosViolados;
	private JLabel lblElServicio;
	private JLabel lblDonde;
	private JLabel lblResponsableDelPrograma;
	public JTextField txtNpolicia;
	public JTextField txtMarca;
	private JLabel lblNoEconomico;
	public JTextField txtNeconomico;
	private JLabel lblRutaOSitio;
	private JLabel lblColor;
	public JTextField txtRutaSitio;
	public JTextField txtColor;
	public JTextField txtNombreConductor;
	public JTextField txtDomicilioConductor;
	private JLabel lblNoLicensiaDel;
	public JTextField txtNlicenciaConductor;
	private JLabel lblNombreDelPropietario;
	public JTextField txtNombrePropietario;
	private JLabel lblDomicilioDelPropietario;
	public JTextField txtDomicilioPropietario;
	private JLabel lblArticulosViolados;
	private JLabel lblRetencionDe;
	public JTextField txtRetencion;
	public JTextField txtMarcaModelo;
	private JLabel lblMotivocircunstanciaDe;
	public JTextPane txtMotivo;
	public JButton btnInsertar;
	public JButton btnLimpiar;
	public TimeChooser timeChooser;
	private JLabel lblHora;
	public JComboBox<?> cbMunicipio;
	private JLabel lblMunicipio;
	public JTextField txtEstado;
	private JLabel lblNoBoleta;
	public JTextField txtNboleta;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VistaInfraccion() {
		setBorder(new LineBorder(new Color(58, 63, 64), 5, true));
		getContentPane().setBackground(Color.WHITE);
		setForeground(new Color(0, 0, 0));
		setOpaque(true);
		setBackground(new Color(58, 63, 64));
		setTitle("Boleta de infracci\u00F3n");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 724, 624);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][grow][][][][][grow][][][]"));


		JLabel lblNombreCompleto = new JLabel("Estado:");
		lblNombreCompleto.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblNombreCompleto, "flowx,cell 0 1,alignx left");

		JLabel lblEscolaridad = new JLabel("DESCRIPCI\u00D3N DEL VEH\u00CDCULO");
		lblEscolaridad.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblEscolaridad, "cell 0 3");

		lblRutaOSitio = new JLabel("Ruta o sitio:");
		lblRutaOSitio.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblRutaOSitio, "flowx,cell 0 7");

		JLabel lblDatosDelPrograma = new JLabel("DATOS PERSONALES");
		lblDatosDelPrograma.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblDatosDelPrograma, "cell 0 8,alignx left");

		JLabel lblDependenciaOficial = new JLabel("Modelo:");
		lblDependenciaOficial.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblDependenciaOficial, "flowx,cell 0 6,alignx center");

		lblActividadesADesarrollar = new JLabel("Nombre del conductor:");
		lblActividadesADesarrollar.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblActividadesADesarrollar, "flowx,cell 0 9");

		JLabel lblCarrera = new JLabel("Marca y submarca:");
		lblCarrera.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblCarrera, "flowx,cell 0 5");

		txtModelo = new JTextField();
		txtModelo.setBorder(null);
		txtModelo.setForeground(new Color(58, 63, 64));
		txtModelo.setFont(new Font("Arial", Font.BOLD, 14));
		txtModelo.setBackground(new Color(187,202,204));
		txtModelo.setToolTipText("");
		txtModelo.setColumns(10);
		getContentPane().add(txtModelo, "cell 0 6,growx");

		txtArticulosViolados = new JTextPane();
		txtArticulosViolados.setForeground(new Color(58, 63, 64));
		txtArticulosViolados.setFont(new Font("Arial", Font.BOLD, 14));
		txtArticulosViolados.setBackground(new Color(187,202,204));
		txtArticulosViolados.setToolTipText("");

		lblDonde = new JLabel("Domicilio del conductor:");
		lblDonde.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblDonde, "flowx,cell 0 10");

		lblNoLicensiaDel = new JLabel("N\u00B0 licensia del conductor:");
		lblNoLicensiaDel.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblNoLicensiaDel, "flowx,cell 0 11");
		getContentPane().add(txtArticulosViolados, "cell 0 13,grow");

		lblElServicio = new JLabel("Marca y modelo del dispositivo de alcohol\u00EDmetro");
		lblElServicio.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblElServicio, "flowx,cell 0 16");

		txtMotivo = new JTextPane();
		txtMotivo.setForeground(new Color(58, 63, 64));
		txtMotivo.setToolTipText("");
		txtMotivo.setFont(new Font("Arial", Font.BOLD, 14));
		txtMotivo.setBackground(new Color(187,202,204));
		getContentPane().add(txtMotivo, "cell 0 18,grow");

		lblResponsableDelPrograma = new JLabel("No. de polic\u00EDa de suguridad vial:");
		lblResponsableDelPrograma.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblResponsableDelPrograma, "flowx,cell 0 19");

		btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 14));
		btnInsertar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsertar.setForeground(new Color(234, 254, 255));
		btnInsertar.setBackground(new Color(58, 63, 64));
		getContentPane().add(btnInsertar, "flowx,cell 0 21,growx,aligny baseline");

		JLabel lblNoDeControl = new JLabel("N\u00B0 de placas:");
		lblNoDeControl.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblNoDeControl, "flowx,cell 0 4,alignx left");

		txtNplacas = new JTextField();
		txtNplacas.setBorder(null);
		txtNplacas.setForeground(new Color(58, 63, 64));
		txtNplacas.setFont(new Font("Arial", Font.BOLD, 14));
		txtNplacas.setBackground(new Color(187,202,204));
		txtNplacas.setToolTipText("");
		txtNplacas.setColumns(10);
		getContentPane().add(txtNplacas, "cell 0 4,growx");

		JLabel lblCreditosAprobados = new JLabel("Placas del estado de:");
		lblCreditosAprobados.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblCreditosAprobados, "cell 0 4");

		txtPlacasEstado = new JTextField();
		txtPlacasEstado.setBorder(null);
		txtPlacasEstado.setForeground(new Color(58, 63, 64));
		txtPlacasEstado.setFont(new Font("Arial", Font.BOLD, 14));
		txtPlacasEstado.setBackground(new Color(187,202,204));
		txtPlacasEstado.setToolTipText("");
		txtPlacasEstado.setColumns(10);
		getContentPane().add(txtPlacasEstado, "cell 0 4,growx");
		
		lblNoBoleta = new JLabel("N\u00B0 boleta");
		lblNoBoleta.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblNoBoleta, "flowx,cell 0 0");
		
		txtNboleta = new JTextField();
		txtNboleta.setBorder(null);
		txtNboleta.setToolTipText("");
		txtNboleta.setForeground(new Color(58, 63, 64));
		txtNboleta.setFont(new Font("Arial", Font.BOLD, 14));
		txtNboleta.setColumns(10);
		txtNboleta.setBackground(new Color(187,202,204));
		getContentPane().add(txtNboleta, "cell 0 0,growx");

		JLabel lblFechaDeInicio = new JLabel("Fecha");
		lblFechaDeInicio.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblFechaDeInicio, "cell 0 0");
		
		
		dcFecha = new JDateChooser();
		dcFecha.setFont(new Font("Arial", Font.BOLD, 12));
		dcFecha.setBackground(new Color(187,202,204));
		dcFecha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dcFecha.setToolTipText("Indicar la fecha.");
		getContentPane().add(dcFecha, "cell 0 0,growx");

		JLabel lblEdad = new JLabel("En la calle (referencias):");
		lblEdad.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblEdad, "flowx,cell 0 2");

		txtReferencias = new JTextField();
		txtReferencias.setBorder(null);
		txtReferencias.setForeground(new Color(58, 63, 64));
		txtReferencias.setFont(new Font("Arial", Font.BOLD, 14));
		txtReferencias.setBackground(new Color(187,202,204));
		txtReferencias.setToolTipText("");
		txtReferencias.setColumns(10);
		getContentPane().add(txtReferencias, "cell 0 2,growx");

		JLabel lblSexo = new JLabel("Infracci\u00F3n al:");
		lblSexo.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblSexo, "cell 0 2,alignx left");

		cbInfraccion = new JComboBox();
		cbInfraccion.setBorder(null);
		cbInfraccion.setForeground(new Color(58, 63, 64));
		cbInfraccion.setFont(new Font("Arial", Font.BOLD, 14));
		cbInfraccion.setBackground(new Color(187,202,204));
		cbInfraccion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbInfraccion.setModel(new DefaultComboBoxModel(new String[] {"Conductor", "Propietario", "Concesionario", "Permisionario"}));
		cbInfraccion.setToolTipText("");
		getContentPane().add(cbInfraccion, "cell 0 2");

		txtMarca = new JTextField();
		txtMarca.setBorder(null);
		txtMarca.setForeground(new Color(58, 63, 64));
		txtMarca.setToolTipText("");
		txtMarca.setFont(new Font("Arial", Font.BOLD, 14));
		txtMarca.setColumns(10);
		txtMarca.setBackground(new Color(187,202,204));
		getContentPane().add(txtMarca, "cell 0 5,growx");

		JLabel lblTitularDeLa = new JLabel("N\u00B0 serie:");
		lblTitularDeLa.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblTitularDeLa, "cell 0 6");

		txtNserie = new JTextField();
		txtNserie.setBorder(null);
		txtNserie.setForeground(new Color(58, 63, 64));
		txtNserie.setFont(new Font("Arial", Font.BOLD, 14));
		txtNserie.setBackground(new Color(187,202,204));
		txtNserie.setToolTipText("");
		txtNserie.setColumns(10);
		getContentPane().add(txtNserie, "cell 0 6,growx");

		lblMotivocircunstanciaDe = new JLabel("Motivo (circunstancia de los echos):");
		lblMotivocircunstanciaDe.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblMotivocircunstanciaDe, "flowx,cell 0 17");

		txtNpolicia = new JTextField();
		txtNpolicia.setForeground(new Color(58, 63, 64));
		txtNpolicia.setFont(new Font("Arial", Font.BOLD, 14));
		txtNpolicia.setBackground(new Color(187,202,204));
		txtNpolicia.setToolTipText("");
		txtNpolicia.setColumns(10);
		getContentPane().add(txtNpolicia, "cell 0 19,growx");
		
		lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblHora, "cell 0 0,alignx right");
		
		timeChooser = new TimeChooser();
		timeChooser.setBorder(null);
		timeChooser.setText("");
		timeChooser.setBackground(new Color(187,202,204));
		timeChooser.setForeground(new Color(58, 63, 64));
		getContentPane().add(timeChooser, "cell 0 0,alignx left");

		lblNoEconomico = new JLabel("N\u00B0 econ\u00F3mico:");
		lblNoEconomico.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblNoEconomico, "cell 0 6");

		txtNeconomico = new JTextField();
		txtNeconomico.setBorder(null);
		txtNeconomico.setForeground(new Color(58, 63, 64));
		txtNeconomico.setToolTipText("");
		txtNeconomico.setFont(new Font("Arial", Font.BOLD, 14));
		txtNeconomico.setColumns(10);
		txtNeconomico.setBackground(new Color(187,202,204));
		getContentPane().add(txtNeconomico, "cell 0 6");

		txtRutaSitio = new JTextField();
		txtRutaSitio.setForeground(new Color(58, 63, 64));
		txtRutaSitio.setToolTipText("");
		txtRutaSitio.setFont(new Font("Arial", Font.BOLD, 14));
		txtRutaSitio.setColumns(10);
		txtRutaSitio.setBackground(new Color(187,202,204));
		getContentPane().add(txtRutaSitio, "cell 0 7,grow");

		lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblColor, "cell 0 7");

		txtColor = new JTextField();
		txtColor.setForeground(new Color(58, 63, 64));
		txtColor.setToolTipText("");
		txtColor.setFont(new Font("Arial", Font.BOLD, 14));
		txtColor.setColumns(10);
		txtColor.setBackground(new Color(187,202,204));
		getContentPane().add(txtColor, "cell 0 7,grow");

		txtNombreConductor = new JTextField();
		txtNombreConductor.setForeground(new Color(58, 63, 64));
		txtNombreConductor.setToolTipText("");
		txtNombreConductor.setFont(new Font("Arial", Font.BOLD, 14));
		txtNombreConductor.setColumns(10);
		txtNombreConductor.setBackground(new Color(187,202,204));
		getContentPane().add(txtNombreConductor, "cell 0 9,grow");

		txtNlicenciaConductor = new JTextField();
		txtNlicenciaConductor.setForeground(new Color(58, 63, 64));
		txtNlicenciaConductor.setToolTipText("");
		txtNlicenciaConductor.setFont(new Font("Arial", Font.BOLD, 14));
		txtNlicenciaConductor.setColumns(10);
		txtNlicenciaConductor.setBackground(new Color(187,202,204));
		getContentPane().add(txtNlicenciaConductor, "cell 0 11,growx");

		lblArticulosViolados = new JLabel("Art\u00EDculos violados:");
		lblArticulosViolados.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblArticulosViolados, "cell 0 12");

		lblRetencionDe = new JLabel("Retenci\u00F3n de:");
		lblRetencionDe.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblRetencionDe, "flowx,cell 0 15");

		txtRetencion = new JTextField();
		txtRetencion.setForeground(new Color(58, 63, 64));
		txtRetencion.setToolTipText("");
		txtRetencion.setFont(new Font("Arial", Font.BOLD, 14));
		txtRetencion.setColumns(10);
		txtRetencion.setBackground(new Color(187,202,204));
		getContentPane().add(txtRetencion, "cell 0 15,growx");

		txtMarcaModelo = new JTextField();
		txtMarcaModelo.setForeground(new Color(58, 63, 64));
		txtMarcaModelo.setToolTipText("");
		txtMarcaModelo.setFont(new Font("Arial", Font.BOLD, 14));
		txtMarcaModelo.setColumns(10);
		txtMarcaModelo.setBackground(new Color(187,202,204));
		getContentPane().add(txtMarcaModelo, "cell 0 16,growx");

		txtDomicilioConductor = new JTextField();
		txtDomicilioConductor.setForeground(new Color(58, 63, 64));
		txtDomicilioConductor.setToolTipText("");
		txtDomicilioConductor.setFont(new Font("Arial", Font.BOLD, 14));
		txtDomicilioConductor.setColumns(10);
		txtDomicilioConductor.setBackground(new Color(187,202,204));
		getContentPane().add(txtDomicilioConductor, "cell 0 10,grow");

		lblNombreDelPropietario = new JLabel("Nombre del propietario:");
		lblNombreDelPropietario.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblNombreDelPropietario, "cell 0 9");

		txtNombrePropietario = new JTextField();
		txtNombrePropietario.setForeground(new Color(58, 63, 64));
		txtNombrePropietario.setToolTipText("");
		txtNombrePropietario.setFont(new Font("Arial", Font.BOLD, 12));
		txtNombrePropietario.setColumns(10);
		txtNombrePropietario.setBackground(new Color(187,202,204));
		getContentPane().add(txtNombrePropietario, "cell 0 9,growx");

		lblDomicilioDelPropietario = new JLabel("Domicilio del propietario:");
		lblDomicilioDelPropietario.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblDomicilioDelPropietario, "cell 0 10");

		txtDomicilioPropietario = new JTextField();
		txtDomicilioPropietario.setForeground(new Color(58, 63, 64));
		txtDomicilioPropietario.setToolTipText("");
		txtDomicilioPropietario.setFont(new Font("Arial", Font.BOLD, 14));
		txtDomicilioPropietario.setColumns(10);
		txtDomicilioPropietario.setBackground(new Color(187,202,204));
		getContentPane().add(txtDomicilioPropietario, "cell 0 10,growx");

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setForeground(new Color(234, 254, 255));
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpiar.setBackground(new Color(58, 63, 64));
		getContentPane().add(btnLimpiar, "cell 0 21,growx");
		
		txtEstado = new JTextField();
		txtEstado.setBorder(null);
		txtEstado.setText("Zacatecas");
		txtEstado.setToolTipText("");
		txtEstado.setForeground(new Color(58, 63, 64));
		txtEstado.setFont(new Font("Arial", Font.BOLD, 14));
		txtEstado.setColumns(10);
		txtEstado.setBackground(new Color(187,202,204));
		getContentPane().add(txtEstado, "cell 0 1,growx");
		
		lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setFont(new Font("Arial", Font.BOLD, 14));
		getContentPane().add(lblMunicipio, "cell 0 1");
		
		cbMunicipio = new JComboBox();
		cbMunicipio.setBorder(null);
		cbMunicipio.setModel(new DefaultComboBoxModel(new String[] {"Zacatecas", "Apozol", "Apulco", "Atolinga ", "Benito Ju\u00E1rez", "Calera", "Ca\u00F1itas de Felipe Pescador", "Chalchihuites", "Concepci\u00F3n del Oro", "Cuauht\u00E9moc ", "El Plateado de J. Amaro", "El Salvador", "Fresnillo", "Genaro Codina", "General Enrique Estrada", "Gral. Francisco R. Murgu\u00EDa", "General P\u00E1nfilo Natera", "Guadalupe ", "Huanusco", "Jalpa", "Jerez", "Jim\u00E9nez del Teul", "Juan Aldama ", "Juchipila", "Loreto", "Luis Moya", "Mazapil", "Melchor Ocampo", "Mezquital del Oro", "Miguel Auza ", "Momax", "Monte Escobedo", "Morelos", "Moyahua de Estrada ", "Nochistl\u00E1n de Mej\u00EDa", "Noria de \u00C1ngeles", "Ojocaliente", "P\u00E1nuco", "Pinos ", "R\u00EDo Grande ", "Sa\u00EDn Alto ", "Santa Mar\u00EDa de la Paz", "Sombrerete", "Susticac\u00E1n", "Tabasco", "Tepechitl\u00E1n", "Tepetongo", "Te\u00FAl de Gonz\u00E1lez Ortega ", "Tlaltenango de S\u00E1nchez Rom\u00E1n", "Trancoso ", "Trinidad Garc\u00EDa de la Cadena", "Valpara\u00EDso", "Vetagrande", "Villa de Cos", "Villa Garc\u00EDa", "Villa Gonz\u00E1lez Ortega", "Villa Hidalgo", "Villanueva"}));
		cbMunicipio.setToolTipText("");
		cbMunicipio.setForeground(new Color(58, 63, 64));
		cbMunicipio.setFont(new Font("Arial", Font.BOLD, 14));
		cbMunicipio.setBackground(new Color(187,202,204));
		getContentPane().add(cbMunicipio, "cell 0 1,growx");

	}
}
