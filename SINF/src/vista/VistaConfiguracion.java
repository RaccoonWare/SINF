package vista;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class VistaConfiguracion extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ButtonGroup g1 = new ButtonGroup();
	public JButton btnRutaArticulos;
	public JButton btnRutaInfracciones;
	public JButton btnUusarioContraseña;

	public VistaConfiguracion() {
		setBorder(new LineBorder(new Color(58, 63, 64), 5, true));
		getContentPane().setBackground(Color.WHITE);
		setForeground(new Color(0, 0, 0));
		setOpaque(true);
		setBackground(new Color(58, 63, 64));
		setTitle("Boleta de infracci\u00F3n");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 479, 114);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][]"));

		btnRutaArticulos = new JButton("Ruta Art\u00EDculos");
		btnRutaArticulos.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnRutaArticulos.setFont(new Font("Arial", Font.BOLD, 14));
		btnRutaArticulos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRutaArticulos.setForeground(new Color(234, 254, 255));
		btnRutaArticulos.setBackground(new Color(58, 63, 64));
		getContentPane().add(btnRutaArticulos, "flowx,cell 0 0,growx,aligny baseline");

		btnRutaInfracciones = new JButton("Ruta Infracciones");
		btnRutaInfracciones.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnRutaInfracciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRutaInfracciones.setForeground(new Color(234, 254, 255));
		btnRutaInfracciones.setFont(new Font("Arial", Font.BOLD, 14));
		btnRutaInfracciones.setBackground(new Color(58, 63, 64));
		getContentPane().add(btnRutaInfracciones, "cell 0 1,growx");

		btnUusarioContraseña = new JButton("Cambiar usuario y contrase\u00F1a");
		btnUusarioContraseña.setForeground(new Color(234, 254, 255));
		btnUusarioContraseña.setFont(new Font("Arial", Font.BOLD, 14));
		btnUusarioContraseña.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		btnUusarioContraseña.setBackground(new Color(58, 63, 64));
		getContentPane().add(btnUusarioContraseña, "cell 0 2,growx");

	}
}
