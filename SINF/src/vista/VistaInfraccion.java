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

import controlador.MVC;

import javax.swing.JTextPane;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import time.*;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

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
	public JButton btnAnular;
	public JButton btnImprimir;
	public TimeChooser timeChooser;
	private JLabel lblHora;
	public JComboBox<?> cbMunicipio;
	private JLabel lblMunicipio;
	public JTextField txtEstado;
	private JLabel lblNoBoleta;
	public JTextField txtNboleta;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VistaInfraccion() {
		setBorder(new LineBorder(MVC.COLOR_BG, 5, true));
		getContentPane().setBackground(Color.GRAY);
		setForeground(new Color(0, 0, 0));
		setOpaque(true);
		setBackground(MVC.COLOR_BG);
		setTitle("Boleta de infracci\u00F3n");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 724, 624);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][grow][][][][][grow][][][]"));
		

		JLabel lblNombreCompleto = new JLabel("Estado:");
		lblNombreCompleto.setFont(MVC.FUENTE);
		getContentPane().add(lblNombreCompleto, "flowx,cell 0 1,alignx left");

		JLabel lblEscolaridad = new JLabel("DESCRIPCI\u00D3N DEL VEH\u00CDCULO");
		lblEscolaridad.setFont(MVC.FUENTE);
		getContentPane().add(lblEscolaridad, "cell 0 3");

		lblRutaOSitio = new JLabel("Ruta o sitio:");
		lblRutaOSitio.setFont(MVC.FUENTE);
		getContentPane().add(lblRutaOSitio, "flowx,cell 0 7");

		JLabel lblDatosDelPrograma = new JLabel("DATOS PERSONALES");
		lblDatosDelPrograma.setFont(MVC.FUENTE);
		getContentPane().add(lblDatosDelPrograma, "cell 0 8,alignx left");

		JLabel lblDependenciaOficial = new JLabel("Modelo:");
		lblDependenciaOficial.setFont(MVC.FUENTE);
		getContentPane().add(lblDependenciaOficial, "flowx,cell 0 6,alignx center");

		lblActividadesADesarrollar = new JLabel("Nombre del conductor:");
		lblActividadesADesarrollar.setFont(MVC.FUENTE);
		getContentPane().add(lblActividadesADesarrollar, "flowx,cell 0 9");

		JLabel lblCarrera = new JLabel("Marca y submarca:");
		lblCarrera.setFont(MVC.FUENTE);
		getContentPane().add(lblCarrera, "flowx,cell 0 5");

		txtModelo = new JTextField();
		txtModelo.setBorder(null);
		txtModelo.setForeground(MVC.COLOR_BG);
		txtModelo.setFont(MVC.FUENTE);
		txtModelo.setBackground(MVC.COLOR_VALID);
		txtModelo.setToolTipText("");
		txtModelo.setColumns(10);
		getContentPane().add(txtModelo, "cell 0 6,growx");

		lblDonde = new JLabel("Domicilio del conductor:");
		lblDonde.setFont(MVC.FUENTE);
		getContentPane().add(lblDonde, "flowx,cell 0 10");

		lblNoLicensiaDel = new JLabel("N\u00B0 licensia del conductor:");
		lblNoLicensiaDel.setFont(MVC.FUENTE);
		getContentPane().add(lblNoLicensiaDel, "flowx,cell 0 11");
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 13,grow");
		
				txtArticulosViolados = new JTextPane();
				scrollPane.setViewportView(txtArticulosViolados);
				txtArticulosViolados.setForeground(MVC.COLOR_BG);
				txtArticulosViolados.setFont(MVC.FUENTE);
				txtArticulosViolados.setBackground(MVC.COLOR_VALID);
				txtArticulosViolados.setToolTipText("");

		lblElServicio = new JLabel("Marca y modelo del dispositivo de alcohol\u00EDmetro");
		lblElServicio.setFont(MVC.FUENTE);
		getContentPane().add(lblElServicio, "flowx,cell 0 16");
		
		scrollPane_1 = new JScrollPane();
		getContentPane().add(scrollPane_1, "cell 0 18,grow");
		
		txtMotivo = new JTextPane();
		scrollPane_1.setViewportView(txtMotivo);
		txtMotivo.setForeground(MVC.COLOR_BG);
		txtMotivo.setToolTipText("");
		txtMotivo.setFont(MVC.FUENTE);
		txtMotivo.setBackground(MVC.COLOR_VALID);

		lblResponsableDelPrograma = new JLabel("No. de polic\u00EDa de suguridad vial:");
		lblResponsableDelPrograma.setFont(MVC.FUENTE);
		getContentPane().add(lblResponsableDelPrograma, "flowx,cell 0 19");

		btnInsertar = new JButton("Insertar");
		btnInsertar.setBorder(new LineBorder(MVC.COLOR_VALID, 2, true));
		btnInsertar.setFont(MVC.FUENTE);
		btnInsertar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsertar.setForeground(MVC.COLOR_LETRA);
		btnInsertar.setBackground(MVC.COLOR_BG);
		getContentPane().add(btnInsertar, "flowx,cell 0 21,growx,aligny baseline");

		JLabel lblNoDeControl = new JLabel("N\u00B0 de placas:");
		lblNoDeControl.setFont(MVC.FUENTE);
		getContentPane().add(lblNoDeControl, "flowx,cell 0 4,alignx left");

		txtNplacas = new JTextField();
		txtNplacas.setBorder(null);
		txtNplacas.setForeground(MVC.COLOR_BG);
		txtNplacas.setFont(MVC.FUENTE);
		txtNplacas.setBackground(MVC.COLOR_VALID);
		txtNplacas.setToolTipText("");
		txtNplacas.setColumns(10);
		getContentPane().add(txtNplacas, "cell 0 4,growx");

		JLabel lblCreditosAprobados = new JLabel("Placas del estado de:");
		lblCreditosAprobados.setFont(MVC.FUENTE);
		getContentPane().add(lblCreditosAprobados, "cell 0 4");

		txtPlacasEstado = new JTextField();
		txtPlacasEstado.setBorder(null);
		txtPlacasEstado.setForeground(MVC.COLOR_BG);
		txtPlacasEstado.setFont(MVC.FUENTE);
		txtPlacasEstado.setBackground(MVC.COLOR_VALID);
		txtPlacasEstado.setToolTipText("");
		txtPlacasEstado.setColumns(10);
		getContentPane().add(txtPlacasEstado, "cell 0 4,growx");
		
		lblNoBoleta = new JLabel("N\u00B0 boleta");
		lblNoBoleta.setFont(MVC.FUENTE);
		getContentPane().add(lblNoBoleta, "flowx,cell 0 0");
		
		txtNboleta = new JTextField();
		txtNboleta.setBorder(null);
		txtNboleta.setToolTipText("");
		txtNboleta.setForeground(MVC.COLOR_BG);
		txtNboleta.setFont(MVC.FUENTE);
		txtNboleta.setColumns(10);
		txtNboleta.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtNboleta, "cell 0 0,growx");

		JLabel lblFechaDeInicio = new JLabel("Fecha");
		lblFechaDeInicio.setFont(MVC.FUENTE);
		getContentPane().add(lblFechaDeInicio, "cell 0 0");
		
		
		dcFecha = new JDateChooser();
		dcFecha.setFont(MVC.FUENTE);
		dcFecha.setBackground(MVC.COLOR_VALID);
		dcFecha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dcFecha.setToolTipText("Indicar la fecha.");
		getContentPane().add(dcFecha, "cell 0 0,growx");

		JLabel lblEdad = new JLabel("En la calle (referencias):");
		lblEdad.setFont(MVC.FUENTE);
		getContentPane().add(lblEdad, "flowx,cell 0 2");

		txtReferencias = new JTextField();
		txtReferencias.setBorder(null);
		txtReferencias.setForeground(MVC.COLOR_BG);
		txtReferencias.setFont(MVC.FUENTE);
		txtReferencias.setBackground(MVC.COLOR_VALID);
		txtReferencias.setToolTipText("");
		txtReferencias.setColumns(10);
		getContentPane().add(txtReferencias, "cell 0 2,growx");

		JLabel lblSexo = new JLabel("Infracci\u00F3n al:");
		lblSexo.setFont(MVC.FUENTE);
		getContentPane().add(lblSexo, "cell 0 2,alignx left");

		cbInfraccion = new JComboBox();
		cbInfraccion.setBorder(null);
		cbInfraccion.setForeground(MVC.COLOR_BG);
		cbInfraccion.setFont(MVC.FUENTE);
		cbInfraccion.setBackground(MVC.COLOR_VALID);		
		cbInfraccion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbInfraccion.setModel(new DefaultComboBoxModel(new String[] {"Conductor", "Propietario", "Concesionario", "Permisionario"}));
		cbInfraccion.setToolTipText("");
		getContentPane().add(cbInfraccion, "cell 0 2");

		txtMarca = new JTextField();
		txtMarca.setBorder(null);
		txtMarca.setForeground(MVC.COLOR_BG);
		txtMarca.setToolTipText("");
		txtMarca.setFont(MVC.FUENTE);
		txtMarca.setColumns(10);
		txtMarca.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtMarca, "cell 0 5,growx");

		JLabel lblTitularDeLa = new JLabel("N\u00B0 serie:");
		lblTitularDeLa.setFont(MVC.FUENTE);
		getContentPane().add(lblTitularDeLa, "cell 0 6");

		txtNserie = new JTextField();
		txtNserie.setBorder(null);
		txtNserie.setForeground(MVC.COLOR_BG);
		txtNserie.setFont(MVC.FUENTE);
		txtNserie.setBackground(MVC.COLOR_VALID);
		txtNserie.setToolTipText("");
		txtNserie.setColumns(10);
		getContentPane().add(txtNserie, "cell 0 6,growx");

		lblMotivocircunstanciaDe = new JLabel("Motivo (circunstancia de los echos):");
		lblMotivocircunstanciaDe.setFont(MVC.FUENTE);
		getContentPane().add(lblMotivocircunstanciaDe, "flowx,cell 0 17");

		txtNpolicia = new JTextField();
		txtNpolicia.setForeground(MVC.COLOR_BG);
		txtNpolicia.setFont(MVC.FUENTE);
		txtNpolicia.setBackground(MVC.COLOR_VALID);
		txtNpolicia.setToolTipText("");
		txtNpolicia.setColumns(10);
		getContentPane().add(txtNpolicia, "cell 0 19,growx");
		
		lblHora = new JLabel("Hora");
		lblHora.setFont(MVC.FUENTE);
		getContentPane().add(lblHora, "cell 0 0,alignx right");
		
		timeChooser = new TimeChooser();
		timeChooser.setBorder(null);
		timeChooser.setText("");
		timeChooser.setHorizontalAlignment(JTextField.CENTER);
		timeChooser.setBackground(MVC.COLOR_VALID);
		timeChooser.setForeground(MVC.COLOR_BG);
		getContentPane().add(timeChooser, "cell 0 0,alignx left");

		lblNoEconomico = new JLabel("N\u00B0 econ\u00F3mico:");
		lblNoEconomico.setFont(MVC.FUENTE);
		getContentPane().add(lblNoEconomico, "cell 0 6");

		txtNeconomico = new JTextField();
		txtNeconomico.setBorder(null);
		txtNeconomico.setForeground(MVC.COLOR_BG);
		txtNeconomico.setToolTipText("");
		txtNeconomico.setFont(MVC.FUENTE);
		txtNeconomico.setColumns(10);
		txtNeconomico.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtNeconomico, "cell 0 6");

		txtRutaSitio = new JTextField();
		txtRutaSitio.setForeground(MVC.COLOR_BG);
		txtRutaSitio.setToolTipText("");
		txtRutaSitio.setFont(MVC.FUENTE);
		txtRutaSitio.setColumns(10);
		txtRutaSitio.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtRutaSitio, "cell 0 7,grow");

		lblColor = new JLabel("Color:");
		lblColor.setFont(MVC.FUENTE);
		getContentPane().add(lblColor, "cell 0 7");

		txtColor = new JTextField();
		txtColor.setForeground(MVC.COLOR_BG);
		txtColor.setToolTipText("");
		txtColor.setFont(MVC.FUENTE);
		txtColor.setColumns(10);
		txtColor.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtColor, "cell 0 7,grow");

		txtNombreConductor = new JTextField();
		txtNombreConductor.setForeground(MVC.COLOR_BG);
		txtNombreConductor.setToolTipText("");
		txtNombreConductor.setFont(MVC.FUENTE);
		txtNombreConductor.setColumns(10);
		txtNombreConductor.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtNombreConductor, "cell 0 9,grow");

		txtNlicenciaConductor = new JTextField();
		txtNlicenciaConductor.setForeground(MVC.COLOR_BG);
		txtNlicenciaConductor.setToolTipText("");
		txtNlicenciaConductor.setFont(MVC.FUENTE);
		txtNlicenciaConductor.setColumns(10);
		txtNlicenciaConductor.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtNlicenciaConductor, "cell 0 11,growx");

		lblArticulosViolados = new JLabel("Art\u00EDculos violados:");
		lblArticulosViolados.setFont(MVC.FUENTE);
		getContentPane().add(lblArticulosViolados, "cell 0 12");

		lblRetencionDe = new JLabel("Retenci\u00F3n de:");
		lblRetencionDe.setFont(MVC.FUENTE);
		getContentPane().add(lblRetencionDe, "flowx,cell 0 15");

		txtRetencion = new JTextField();
		txtRetencion.setForeground(MVC.COLOR_BG);
		txtRetencion.setToolTipText("");
		txtRetencion.setFont(MVC.FUENTE);
		txtRetencion.setColumns(10);
		txtRetencion.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtRetencion, "cell 0 15,growx");

		txtMarcaModelo = new JTextField();
		txtMarcaModelo.setForeground(MVC.COLOR_BG);
		txtMarcaModelo.setToolTipText("");
		txtMarcaModelo.setFont(MVC.FUENTE);
		txtMarcaModelo.setColumns(10);
		txtMarcaModelo.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtMarcaModelo, "cell 0 16,growx");

		txtDomicilioConductor = new JTextField();
		txtDomicilioConductor.setForeground(MVC.COLOR_BG);
		txtDomicilioConductor.setToolTipText("");
		txtDomicilioConductor.setFont(MVC.FUENTE);
		txtDomicilioConductor.setColumns(10);
		txtDomicilioConductor.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtDomicilioConductor, "cell 0 10,grow");

		lblNombreDelPropietario = new JLabel("Nombre del propietario:");
		lblNombreDelPropietario.setFont(MVC.FUENTE);
		getContentPane().add(lblNombreDelPropietario, "cell 0 9");

		txtNombrePropietario = new JTextField();
		txtNombrePropietario.setForeground(MVC.COLOR_BG);
		txtNombrePropietario.setToolTipText("");
		txtNombrePropietario.setFont(new Font("Arial", Font.BOLD, 12));
		txtNombrePropietario.setColumns(10);
		txtNombrePropietario.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtNombrePropietario, "cell 0 9,growx");

		lblDomicilioDelPropietario = new JLabel("Domicilio del propietario:");
		lblDomicilioDelPropietario.setFont(MVC.FUENTE);
		getContentPane().add(lblDomicilioDelPropietario, "cell 0 10");

		txtDomicilioPropietario = new JTextField();
		txtDomicilioPropietario.setForeground(MVC.COLOR_BG);
		txtDomicilioPropietario.setToolTipText("");
		txtDomicilioPropietario.setFont(MVC.FUENTE);
		txtDomicilioPropietario.setColumns(10);
		txtDomicilioPropietario.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtDomicilioPropietario, "cell 0 10,growx");

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(new LineBorder(MVC.COLOR_VALID, 2, true));
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setForeground(MVC.COLOR_LETRA);
		btnLimpiar.setFont(MVC.FUENTE);
		btnLimpiar.setBackground(MVC.COLOR_BG);
		getContentPane().add(btnLimpiar, "cell 0 21,growx");

		btnAnular = new JButton("Anular");
		btnAnular.setBorder(new LineBorder(MVC.COLOR_VALID, 2, true));
		btnAnular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnular.setForeground(MVC.COLOR_LETRA);
		btnAnular.setFont(MVC.FUENTE);
		btnAnular.setBackground(MVC.COLOR_BG);
		getContentPane().add(btnAnular, "cell 0 21,growx");

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBorder(new LineBorder(MVC.COLOR_VALID, 2, true));
		btnImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImprimir.setForeground(MVC.COLOR_LETRA);
		btnImprimir.setFont(MVC.FUENTE);
		btnImprimir.setBackground(MVC.COLOR_BG);
		getContentPane().add(btnImprimir, "cell 0 21,growx");
		
		txtEstado = new JTextField();
		txtEstado.setBorder(null);
		txtEstado.setText("Zacatecas");
		txtEstado.setToolTipText("");
		txtEstado.setForeground(MVC.COLOR_BG);
		txtEstado.setFont(MVC.FUENTE);
		txtEstado.setColumns(10);
		txtEstado.setBackground(MVC.COLOR_VALID);
		getContentPane().add(txtEstado, "cell 0 1,growx");
		
		lblMunicipio = new JLabel("Municipio:");
		lblMunicipio.setFont(MVC.FUENTE);
		getContentPane().add(lblMunicipio, "cell 0 1");
		
		cbMunicipio = new JComboBox();
		cbMunicipio.setBorder(null);
		cbMunicipio.setModel(new DefaultComboBoxModel(new String[] {"Apozol", "Apulco", "Atolinga ", "Benito Ju\u00E1rez", "Calera", "Ca\u00F1itas de Felipe Pescador", "Chalchihuites", "Concepci\u00F3n del Oro", "Cuauht\u00E9moc ", "El Plateado de J. Amaro", "El Salvador", "Fresnillo", "Genaro Codina", "General Enrique Estrada", "Gral. Francisco R. Murgu\u00EDa", "General P\u00E1nfilo Natera", "Guadalupe ", "Huanusco", "Jalpa", "Jerez", "Jim\u00E9nez del Teul", "Juan Aldama ", "Juchipila", "Loreto", "Luis Moya", "Mazapil", "Melchor Ocampo", "Mezquital del Oro", "Miguel Auza ", "Momax", "Monte Escobedo", "Morelos", "Moyahua de Estrada ", "Nochistl\u00E1n de Mej\u00EDa", "Noria de \u00C1ngeles", "Ojocaliente", "P\u00E1nuco", "Pinos ", "R\u00EDo Grande ", "Sa\u00EDn Alto ", "Santa Mar\u00EDa de la Paz", "Sombrerete", "Susticac\u00E1n", "Tabasco", "Tepechitl\u00E1n", "Tepetongo", "Te\u00FAl de Gonz\u00E1lez Ortega ", "Tlaltenango de S\u00E1nchez Rom\u00E1n", "Trancoso ", "Trinidad Garc\u00EDa de la Cadena", "Valpara\u00EDso", "Vetagrande", "Villa de Cos", "Villa Garc\u00EDa", "Villa Gonz\u00E1lez Ortega", "Villa Hidalgo", "Villanueva","Zacatecas"}));
		cbMunicipio.setSelectedIndex(cbMunicipio.getItemCount()-1);
		cbMunicipio.setToolTipText("");
		cbMunicipio.setForeground(MVC.COLOR_BG);
		cbMunicipio.setFont(MVC.FUENTE);
		cbMunicipio.setBackground(MVC.COLOR_VALID);
		getContentPane().add(cbMunicipio, "cell 0 1,growx");

	}
}
