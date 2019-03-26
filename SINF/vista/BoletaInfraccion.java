package vista;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class BoletaInfraccion extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMunicipioEstado;
	private JTextField txtNplacas;
	private JTextField txtNserie;
	private JTextField txtModelo;
	private JComboBox<?> cbSexo;
	ButtonGroup g1 = new ButtonGroup();
	private JDateChooser dcFecha;
	private JTextField txtReferencias;
	private JTextField txtPlacasEstado;
	private JLabel lblActividadesADesarrollar;
	private JTextPane txtArticulosViolados;
	private JLabel lblElServicio;
	private JLabel lblDonde;
	private JLabel lblResponsableDelPrograma;
	private JTextField txtNpolicia;
	private JTextField textMarca;
	private JLabel lblNoEconomico;
	private JTextField txtNeconomico;
	private JLabel lblRutaOSitio;
	private JLabel lblColor;
	private JTextField txtRutaSitio;
	private JTextField txtColor;
	private JTextField txtNombreConductor;
	private JTextField txtDomicilioConductor;
	private JLabel lblNoLicensiaDel;
	private JTextField txtNlicenciaConductor;
	private JLabel lblNombreDelPropietario;
	private JTextField txtNombrePropietario;
	private JLabel lblDomicilioDelPropietario;
	private JTextField txtDomicilioPropietario;
	private JLabel lblArticulosViolados;
	private JLabel lblRetencionDe;
	private JTextField txtRetencion;
	private JTextField txtMarcaModelo;
	private JLabel lblMotivocircunstanciaDe;
	private JTextPane txtMotivo;
	public JButton btnInsertar;
	private JLabel lblRetenciones;
	private JButton btnLimpiar;
	private JButton btnAnular;
	private JButton btnCancelar;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BoletaInfraccion() {
		//setFrameIcon(new ImageIcon(BoletaInfraccion.class.getResource("/Iconos/GE2_24.png")));
		setForeground(new Color(0, 0, 0));
		setOpaque(true);
		setBackground(new Color(255, 255, 255));
		setTitle("Boleta de infracci\u00F3n");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 575, 586);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][grow][][][][][grow][][][]"));

		JLabel lblEscolaridad = new JLabel("DATOS DEL VEH\u00CDCULO");
		lblEscolaridad.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblEscolaridad, "cell 0 2");
		
		lblRutaOSitio = new JLabel("Ruta o sitio:");
		lblRutaOSitio.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblRutaOSitio, "flowx,cell 0 6");

		JLabel lblDatosDelPrograma = new JLabel("DATOS PERSONALES");
		lblDatosDelPrograma.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblDatosDelPrograma, "cell 0 7,alignx left");

		JLabel lblDependenciaOficial = new JLabel("Modelo:");
		lblDependenciaOficial.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblDependenciaOficial, "flowx,cell 0 5,alignx center");

		lblActividadesADesarrollar = new JLabel("Nombre del conductor:");
		lblActividadesADesarrollar.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblActividadesADesarrollar, "flowx,cell 0 8");

		JLabel lblCarrera = new JLabel("Marca y submarca:");
		lblCarrera.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblCarrera, "flowx,cell 0 4");

		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Arial", Font.BOLD, 12));
		txtModelo.setBackground(new Color(204, 255, 255));
		txtModelo.setToolTipText("Anotar el nombre del programa de servicio que desarrollar\u00E1 en la dependencia u organismo.");
		txtModelo.setColumns(10);
		txtModelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtModelo.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtModelo.setBackground(new Color(204, 255, 255));
			}
		});
		getContentPane().add(txtModelo, "cell 0 5,growx");

		txtArticulosViolados = new JTextPane();
		txtArticulosViolados.setFont(new Font("Arial", Font.BOLD, 12));
		txtArticulosViolados.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(51, 255, 255), new Color(51, 255, 255), new Color(51, 255, 255), new Color(51, 255, 255)));
		txtArticulosViolados.setBackground(new Color(204, 255, 255));
		txtArticulosViolados.setToolTipText("Anotar a manera de listado las actividades que desarrollar\u00E1 el prestante de servicio social.");
		txtArticulosViolados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtArticulosViolados.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtArticulosViolados.setBackground(new Color(204, 255, 255));
			}
		});
		
				lblDonde = new JLabel("Domicilio del conductor:");
				lblDonde.setFont(new Font("Arial", Font.BOLD, 12));
				getContentPane().add(lblDonde, "flowx,cell 0 9");
		
		lblNoLicensiaDel = new JLabel("No. licensia del conductor:");
		lblNoLicensiaDel.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblNoLicensiaDel, "flowx,cell 0 10");
		getContentPane().add(txtArticulosViolados, "cell 0 12,grow");
		
		lblRetenciones = new JLabel("RETENCIONES");
		lblRetenciones.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblRetenciones, "cell 0 13");

		lblElServicio = new JLabel("Marca y modelo del dispositivo de alcol\u00EDmetro:");
		lblElServicio.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblElServicio, "flowx,cell 0 15");
		
		txtMotivo = new JTextPane();
		txtMotivo.setToolTipText("Anotar a manera de listado las actividades que desarrollar\u00E1 el prestante de servicio social.");
		txtMotivo.setFont(new Font("Arial", Font.BOLD, 12));
		txtMotivo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(51, 255, 255), new Color(51, 255, 255), new Color(51, 255, 255), new Color(51, 255, 255)));
		txtMotivo.setBackground(new Color(204, 255, 255));
		getContentPane().add(txtMotivo, "cell 0 17,grow");

		lblResponsableDelPrograma = new JLabel("No. de polic\u00EDa de suguridad vial:");
		lblResponsableDelPrograma.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblResponsableDelPrograma, "flowx,cell 0 18");

		btnInsertar = new JButton("Agregar");
		btnInsertar.setFont(new Font("Arial", Font.BOLD, 12));
		btnInsertar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsertar.setForeground(new Color(0, 0, 0));
		//btnInsertar.setIcon(new ImageIcon(BoletaInfraccion.class.getResource("/Iconos/icons8-show-property-filled-14.png")));
		btnInsertar.setBackground(new Color(205, 92, 92));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*try {
					guardar();
				} catch (JRException e) {
					e.printStackTrace();
				}*/
			}
		});
		getContentPane().add(btnInsertar, "flowx,cell 0 20,growx,aligny baseline");

		JLabel lblNoDeControl = new JLabel("No. de placas:");
		lblNoDeControl.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblNoDeControl, "flowx,cell 0 3,alignx left");

		txtNplacas = new JTextField();
		txtNplacas.setFont(new Font("Arial", Font.BOLD, 12));
		txtNplacas.setBackground(new Color(204, 255, 255));
		txtNplacas.setToolTipText("Escribir el n\u00FAmero de control del prestante.");
		txtNplacas.setColumns(10);
		txtNplacas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtNplacas.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtNplacas.setBackground(new Color(204, 255, 255));
			}
		});
		getContentPane().add(txtNplacas, "cell 0 3,growx");

		JLabel lblCreditosAprobados = new JLabel("Placas del estado de:");
		lblCreditosAprobados.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblCreditosAprobados, "cell 0 3");

		txtPlacasEstado = new JTextField();
		txtPlacasEstado.setFont(new Font("Arial", Font.BOLD, 12));
		txtPlacasEstado.setBackground(new Color(204, 255, 255));
		txtPlacasEstado.setToolTipText("Indicar el n\u00FAmero de cr\u00E9ditos cubiertos.");
		txtPlacasEstado.setColumns(10);
		txtPlacasEstado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtPlacasEstado.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtPlacasEstado.setBackground(new Color(204, 255, 255));
			}
		});
		getContentPane().add(txtPlacasEstado, "cell 0 3,growx");
		
				JLabel lblFechaDeInicio = new JLabel("Fecha");
				lblFechaDeInicio.setFont(new Font("Arial", Font.BOLD, 12));
				getContentPane().add(lblFechaDeInicio, "flowx,cell 0 0");
		
				dcFecha = new JDateChooser();
				dcFecha.setFont(new Font("Arial", Font.BOLD, 12));
				dcFecha.setBackground(new Color(204, 255, 255));
				dcFecha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				dcFecha.setToolTipText("Indicar la fecha.");
				dcFecha.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						dcFecha.setBackground(new Color(205, 92, 92));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						dcFecha.setBackground(new Color(204, 255, 255));
					}
				});
				getContentPane().add(dcFecha, "cell 0 0,alignx left");

		

		JLabel lblEdad = new JLabel("En la calle (referencias):");
		lblEdad.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblEdad, "flowx,cell 0 1");

		txtReferencias = new JTextField();
		txtReferencias.setFont(new Font("Arial", Font.BOLD, 12));
		txtReferencias.setBackground(new Color(204, 255, 255));
		txtReferencias.setToolTipText("Escribir con d\u00EDgitos la edad del prestante.");
		txtReferencias.setColumns(10);
		txtReferencias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtReferencias.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtReferencias.setBackground(new Color(204, 255, 255));
			}
		});
		getContentPane().add(txtReferencias, "cell 0 1,growx");

		JLabel lblSexo = new JLabel("Infracci\u00F3n al:");
		lblSexo.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblSexo, "cell 0 1,alignx left");

		cbSexo = new JComboBox();
		cbSexo.setFont(new Font("Arial", Font.BOLD, 12));
		cbSexo.setBackground(new Color(204, 255, 255));
		cbSexo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"Conductor", "Propietario", "Concesionario", "Permisionario"}));
		cbSexo.setToolTipText("Indicar el sexo: H Hombre M Mujer.");
		cbSexo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				cbSexo.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cbSexo.setBackground(new Color(204, 255, 255));
			}
		});
		getContentPane().add(cbSexo, "cell 0 1");
		
		textMarca = new JTextField();
		textMarca.setToolTipText("Indicar el n\u00FAmero de cr\u00E9ditos cubiertos.");
		textMarca.setFont(new Font("Arial", Font.BOLD, 12));
		textMarca.setColumns(10);
		textMarca.setBackground(new Color(204, 255, 255));
		getContentPane().add(textMarca, "cell 0 4,growx");

		JLabel lblTitularDeLa = new JLabel("No. serie:");
		lblTitularDeLa.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblTitularDeLa, "cell 0 5");

		txtNserie = new JTextField();
		txtNserie.setFont(new Font("Arial", Font.BOLD, 12));
		txtNserie.setBackground(new Color(204, 255, 255));
		txtNserie.setToolTipText("Indicar el objetivo del programa de servicio social a desarrollar en la dependencia u organismo.");
		txtNserie.setColumns(10);
		txtNserie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtNserie.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtNserie.setBackground(new Color(204, 255, 255));
			}
		});
		getContentPane().add(txtNserie, "cell 0 5,growx");
		
		lblMotivocircunstanciaDe = new JLabel("Motivo (circunstancia de los echos):");
		lblMotivocircunstanciaDe.setFont(new Font("Arial", Font.BOLD, 12));
		getContentPane().add(lblMotivocircunstanciaDe, "flowx,cell 0 16");

		txtNpolicia = new JTextField();
		txtNpolicia.setFont(new Font("Arial", Font.BOLD, 12));
		txtNpolicia.setBackground(new Color(204, 255, 255));
		txtNpolicia.setToolTipText("Anotar el nombre del responsable del programa en la Instituci\u00F3n.");
		txtNpolicia.setColumns(10);
		txtNpolicia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtNpolicia.setBackground(new Color(205, 92, 92));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtNpolicia.setBackground(new Color(204, 255, 255));
			}
		});
		getContentPane().add(txtNpolicia, "cell 0 18,growx");
		
				JLabel lblNombreCompleto = new JLabel("Municipo y estado:");
				lblNombreCompleto.setFont(new Font("Arial", Font.BOLD, 12));
				getContentPane().add(lblNombreCompleto, "cell 0 0,alignx left");
				
						txtMunicipioEstado = new JTextField();
						txtMunicipioEstado.setFont(new Font("Arial", Font.BOLD, 12));
						txtMunicipioEstado.setBackground(new Color(204, 255, 255));
						txtMunicipioEstado.setToolTipText("Anotar el nombre completo del prestante, iniciando por el apellido paterno, materno y nombre (s)");
						txtMunicipioEstado.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								txtMunicipioEstado.setBackground(new Color(205, 92, 92));
							}
							@Override
							public void mouseExited(MouseEvent e) {
								txtMunicipioEstado.setBackground(new Color(204, 255, 255));
							}
						});
						getContentPane().add(txtMunicipioEstado, "cell 0 0,growx");
						txtMunicipioEstado.setColumns(10);
						
						lblNoEconomico = new JLabel("No. econ\u00F3mico:");
						lblNoEconomico.setFont(new Font("Arial", Font.BOLD, 12));
						getContentPane().add(lblNoEconomico, "cell 0 5");
						
						txtNeconomico = new JTextField();
						txtNeconomico.setToolTipText("Indicar el objetivo del programa de servicio social a desarrollar en la dependencia u organismo.");
						txtNeconomico.setFont(new Font("Arial", Font.BOLD, 12));
						txtNeconomico.setColumns(10);
						txtNeconomico.setBackground(new Color(204, 255, 255));
						getContentPane().add(txtNeconomico, "cell 0 5");
						
						txtRutaSitio = new JTextField();
						txtRutaSitio.setToolTipText("Anotar el nombre del programa de servicio que desarrollar\u00E1 en la dependencia u organismo.");
						txtRutaSitio.setFont(new Font("Arial", Font.BOLD, 12));
						txtRutaSitio.setColumns(10);
						txtRutaSitio.setBackground(new Color(204, 255, 255));
						getContentPane().add(txtRutaSitio, "cell 0 6,grow");
						
						lblColor = new JLabel("Color:");
						lblColor.setFont(new Font("Arial", Font.BOLD, 12));
						getContentPane().add(lblColor, "cell 0 6");
						
						txtColor = new JTextField();
						txtColor.setToolTipText("Indicar el objetivo del programa de servicio social a desarrollar en la dependencia u organismo.");
						txtColor.setFont(new Font("Arial", Font.BOLD, 12));
						txtColor.setColumns(10);
						txtColor.setBackground(new Color(204, 255, 255));
						getContentPane().add(txtColor, "cell 0 6,grow");
						
						txtNombreConductor = new JTextField();
						txtNombreConductor.setToolTipText("En caso de que no realice sus actividades de servicio social dentro de las instalaciones de la dependencia, anotar el lugar en donde las realizar\u00E1.");
						txtNombreConductor.setFont(new Font("Arial", Font.BOLD, 12));
						txtNombreConductor.setColumns(10);
						txtNombreConductor.setBackground(new Color(204, 255, 255));
						getContentPane().add(txtNombreConductor, "cell 0 8,grow");
						
						txtNlicenciaConductor = new JTextField();
						txtNlicenciaConductor.setToolTipText("En caso de que no realice sus actividades de servicio social dentro de las instalaciones de la dependencia, anotar el lugar en donde las realizar\u00E1.");
						txtNlicenciaConductor.setFont(new Font("Arial", Font.BOLD, 12));
						txtNlicenciaConductor.setColumns(10);
						txtNlicenciaConductor.setBackground(new Color(204, 255, 255));
						getContentPane().add(txtNlicenciaConductor, "cell 0 10,growx");
						
						lblArticulosViolados = new JLabel("Articulos violados:");
						lblArticulosViolados.setFont(new Font("Arial", Font.BOLD, 12));
						getContentPane().add(lblArticulosViolados, "cell 0 11");
						
						lblRetencionDe = new JLabel("Retencion de:");
						lblRetencionDe.setFont(new Font("Arial", Font.BOLD, 12));
						getContentPane().add(lblRetencionDe, "flowx,cell 0 14");
						
						txtRetencion = new JTextField();
						txtRetencion.setToolTipText("Anotar el nombre del responsable del programa en la Instituci\u00F3n.");
						txtRetencion.setFont(new Font("Arial", Font.BOLD, 12));
						txtRetencion.setColumns(10);
						txtRetencion.setBackground(new Color(204, 255, 255));
						getContentPane().add(txtRetencion, "cell 0 14,growx");
						
						txtMarcaModelo = new JTextField();
						txtMarcaModelo.setToolTipText("En caso de que no realice sus actividades de servicio social dentro de las instalaciones de la dependencia, anotar el lugar en donde las realizar\u00E1.");
						txtMarcaModelo.setFont(new Font("Arial", Font.BOLD, 12));
						txtMarcaModelo.setColumns(10);
						txtMarcaModelo.setBackground(new Color(204, 255, 255));
						getContentPane().add(txtMarcaModelo, "cell 0 15,growx");
								
								txtDomicilioConductor = new JTextField();
								txtDomicilioConductor.setToolTipText("En caso de que no realice sus actividades de servicio social dentro de las instalaciones de la dependencia, anotar el lugar en donde las realizar\u00E1.");
								txtDomicilioConductor.setFont(new Font("Arial", Font.BOLD, 12));
								txtDomicilioConductor.setColumns(10);
								txtDomicilioConductor.setBackground(new Color(204, 255, 255));
								getContentPane().add(txtDomicilioConductor, "cell 0 9,grow");
								
								lblNombreDelPropietario = new JLabel("Nombre del propietario:");
								lblNombreDelPropietario.setFont(new Font("Arial", Font.BOLD, 12));
								getContentPane().add(lblNombreDelPropietario, "cell 0 8");
								
								txtNombrePropietario = new JTextField();
								txtNombrePropietario.setToolTipText("En caso de que no realice sus actividades de servicio social dentro de las instalaciones de la dependencia, anotar el lugar en donde las realizar\u00E1.");
								txtNombrePropietario.setFont(new Font("Arial", Font.BOLD, 12));
								txtNombrePropietario.setColumns(10);
								txtNombrePropietario.setBackground(new Color(204, 255, 255));
								getContentPane().add(txtNombrePropietario, "cell 0 8,growx");
								
								lblDomicilioDelPropietario = new JLabel("Domicilio del propietario:");
								lblDomicilioDelPropietario.setFont(new Font("Arial", Font.BOLD, 12));
								getContentPane().add(lblDomicilioDelPropietario, "cell 0 9");
								
								txtDomicilioPropietario = new JTextField();
								txtDomicilioPropietario.setToolTipText("En caso de que no realice sus actividades de servicio social dentro de las instalaciones de la dependencia, anotar el lugar en donde las realizar\u00E1.");
								txtDomicilioPropietario.setFont(new Font("Arial", Font.BOLD, 12));
								txtDomicilioPropietario.setColumns(10);
								txtDomicilioPropietario.setBackground(new Color(204, 255, 255));
								getContentPane().add(txtDomicilioPropietario, "cell 0 9,growx");
								
								btnLimpiar = new JButton("Limpiar");
								btnLimpiar.setForeground(Color.BLACK);
								btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
								btnLimpiar.setBackground(new Color(205, 92, 92));
								getContentPane().add(btnLimpiar, "cell 0 20,growx");
								
								btnAnular = new JButton("Anular");
								btnAnular.setForeground(Color.BLACK);
								btnAnular.setFont(new Font("Arial", Font.BOLD, 12));
								btnAnular.setBackground(new Color(205, 92, 92));
								getContentPane().add(btnAnular, "cell 0 20,growx");
								
								btnCancelar = new JButton("Cancelar");
								btnCancelar.setForeground(Color.BLACK);
								btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
								btnCancelar.setBackground(new Color(205, 92, 92));
								getContentPane().add(btnCancelar, "cell 0 20,growx");

	}
/*	private void guardar() throws JRException {
		String master = "C:/SS/Documentos/carta_asignacion.jasper";
		SalidasEntradas.setNombre(txtMunicipioEstado.getText().trim());
		SalidasEntradas.setNomTEC(txtNombreIT.getText().trim());
		SalidasEntradas.setEdad(txtReferencias.getText().trim());
		SalidasEntradas.setTelefono(txtTelefono.getText().trim());
		SalidasEntradas.setnControl(txtNplacas.getText().trim());
		SalidasEntradas.setActividades(txtArticulosViolados.getText().trim());
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nom_tec", SalidasEntradas.getNomTEC());
		parametros.put("nom_rep", SalidasEntradas.getNombre());
		parametros.put("eda_rep", SalidasEntradas.getEdad());
		if (cbSexo.getSelectedIndex()==0) {
			parametros.put("m_rep", "(X)");
			parametros.put("f_rep", "(  )");

		}else {
			parametros.put("m_rep", "(  )");
			parametros.put("f_rep", "(X)");
		}
		SalidasEntradas.setCalle(txtCalle.getText().trim());
		parametros.put("cal_rep",SalidasEntradas.getCalle());
		SalidasEntradas.setColonia(txtColonia.getText().trim());
		parametros.put("col_rep",SalidasEntradas.getColonia());
		SalidasEntradas.setEstado(txtCiudad.getText().trim());
		parametros.put("cd_rep",SalidasEntradas.getEstado());
		SalidasEntradas.setTelefono(txtTelefono.getText().trim());
		parametros.put("tel_rep",SalidasEntradas.getTelefono());
		parametros.put("car_rep",cbCarrera.getSelectedItem());
		parametros.put("sem_rep",cbSemestre.getSelectedItem());
		parametros.put("ctrl_rep",SalidasEntradas.getnControl());
		SalidasEntradas.setCreditos(txtPlacasEstado.getText().trim());
		parametros.put("cred_rep",SalidasEntradas.getCreditos());
		SalidasEntradas.setNomPro(txtModelo.getText().trim());
		parametros.put("nom_pro",SalidasEntradas.getNomPro());
		SalidasEntradas.setObjetivo(txtNserie.getText().trim());
		parametros.put("obj_pro",SalidasEntradas.getObjetivo());
		parametros.put("tip_pro", SalidasEntradas.getActividades());
		parametros.put("ad_pro", "(  )");
		parametros.put("tec_pro", "(  )");
		parametros.put("ase_pro", "(  )");
		parametros.put("inv_pro", "(  )");
		parametros.put("doc_pro", "(  )");
		if (rbAD.isSelected()) {
			parametros.put("ad_pro", "(X)");
		}
		if (rbTE.isSelected()) {
			parametros.put("tec_pro", "(X)");
		}
		if (rbAS.isSelected()) {
			parametros.put("ase_pro", "(X)");
		}
		if (rbIN.isSelected()) {
			parametros.put("inv_pro", "(X)");
		}
		if (rbDO.isSelected()) {
			parametros.put("doc_pro", "(X)");
		}
		parametros.put("otr_pro",txtOtro.getText());
		if (cbSiNo.getSelectedIndex()==0) {
			parametros.put("si","(X)");
			parametros.put("no","(  )");
		}else {
			parametros.put("si","(  )");
			parametros.put("no","(X)");
		}
		SalidasEntradas.setDonde(txtDonde.getText().trim());
		parametros.put("donde",SalidasEntradas.getDonde());
		SalidasEntradas.setResponsable(txtNpolicia.getText().trim());
		parametros.put("respo",SalidasEntradas.getResponsable());
		SalidasEntradas.setJefe(txtJefe.getText().trim());
		parametros.put("jefe",SalidasEntradas.getJefe());
		SalidasEntradas.setFecha(dcFecha.getDate());
		parametros.put("fecha",SalidasEntradas.getFecha());

		if (SalidasEntradas.evaluacion == null) {
			try {
				SalidasEntradas.evaluacion=null;
				JasperPrint informe = JasperFillManager.fillReport(master, parametros, new JREmptyDataSource());				
				JasperViewer.viewReport(informe, false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(null, SalidasEntradas.evaluacion.replace("null", ""), "Porfavor verifique...", JOptionPane.WARNING_MESSAGE);
			SalidasEntradas.evaluacion=null;
		}


	}*/

}
