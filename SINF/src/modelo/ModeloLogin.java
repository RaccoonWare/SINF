/**
 * Logica de Negocio de ventana Login
 */
package modelo;
/* importaci�n de librerias */
import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;

import org.apache.poi.ss.usermodel.Workbook;
import controlador.MVC;
import vista.VistaLogin;

/**
 * 
 * @author David
 * @see VistaLogin
 * @see ControladorLogin
 * @version 1.1 
 */
public class ModeloLogin {
	//variables de instancia
	private VistaLogin vistaLogin;
	private Workbook wb;  
	//private File archivo=new File(MVC.getConfig().getProperty("usuarios"));	
	
	///////Construtores e inicializadores
	/**
	 * Constructor por defecto
	 * @param vista
	 */
	public ModeloLogin(VistaLogin vista) {
		vistaLogin=vista;
	}//fin constructor

	//////funciones de validadic�n
	/**
	 * valida que los campos de texto no tengan caracteres no admitidos
	 * @param campo
	 * @return true si los datos coiciden, false de lo cotrario
	 * @since 1.0
	 */
	public boolean validaCampo(Component campo) {
		//variable de funci�n
		boolean res= true;//bandera de resultado
		//Analiza contenido del campo
		////en caso de ser campo de tontrase�a la funci�n getPassword debuelve un arreglo de caracteres
		if (campo instanceof JPasswordField) {
			char[]pass = ((JPasswordField)campo).getPassword();
			int i=0;
			while(res== true && i< pass.length) {            	
				res= Character.isLetterOrDigit(pass[i++]);
            }
		////En caso de compontentes de texto normales hace la revision directamete
		}else{
			String texto = ((JTextComponent)campo).getText();
			int i=0;
			while(res== true && i< texto.length()) {            	
				res= Character.isLetterOrDigit(texto.charAt(i++));
            }
		}
		//regresa el resultado	
		return res;
	}//fin validaCampo
	
	/**
	 * valida los datos ingresados para permitir acceso
	 * @param vistaLogin
	 * @return
	 * @since 1.0
	 * anteriormente hacia referencia a un archivo excel, pero se modifico para que lea los datos de acceso desde el archivo de propiedades
	 */
	public boolean validaLogin(VistaLogin vistaLogin) {
		// TODO Auto-generated method stub
		//variables de funcion
		String usuario= vistaLogin.txtUsuario.getText();//texto del campo de usuario
        char[] pass= vistaLogin.txtPass.getPassword();//texto del campo de contrase�a (arrelo de caracteres)        
        String auxUser= MVC.getConfig().getProperty("usuario");//valor almacenado en propiedades de usuario
        String auxPass= MVC.getConfig().getProperty("contrase�a");//valor almacenado en propiedades de contras�a
		//antes cuando hacia referencia a un archivo adicional lo recorria en buqueda de usuarios y contrase�as validos
        /*try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            int i=-1;
            String auxUser, auxPass;
            while(res== false && i++ < hoja.getLastRowNum()) {            	
            	auxUser= hoja.getRow(i).getCell(0).getStringCellValue().toString();
            	auxPass= hoja.getRow(i).getCell(1).getStringCellValue().toString();            	
            	res= auxUser.equals(usuario)&& compara(auxPass,pass);            	
            }
            	
            
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.err.println(e.getMessage());
        }*/
		//ahora solo checa el archivo de propiedades, si coninciden valida el login		
		return usuario.equals(auxUser) && (new String(pass).equals(auxPass));
	}//fin validaLogin
	
	/////////Funciones Miscelaneas
	
	
	
}
