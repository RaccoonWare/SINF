package modelo;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import controlador.MVC;
import vista.VistaLogin;

public class ModeloLogin {
	private VistaLogin vistaLogin;
	private Workbook wb;  
	//private File archivo=new File(MVC.getConfig().getProperty("usuarios"));	
	public ModeloLogin(VistaLogin vista) {
		vistaLogin=vista;
	}

	public boolean validaCampo(Component campo) {
		boolean res= true;
		if (campo instanceof JPasswordField) {
			char[]pass = ((JPasswordField)campo).getPassword();
			int i=0;
			while(res== true && i< pass.length) {            	
				res= Character.isLetterOrDigit(pass[i++]);
            }
		}else{
			String texto = ((JTextComponent)campo).getText();
			int i=0;
			while(res== true && i< texto.length()) {            	
				res= Character.isLetterOrDigit(texto.charAt(i++));
            }
		}
			
		return res;
	}
	public boolean validaLogin(VistaLogin vistaLogin) {
		// TODO Auto-generated method stub
		//boolean res= false;
		String usuario= vistaLogin.txtUsuario.getText();
        char[] pass= vistaLogin.txtPass.getPassword();
        //System.out.println("usuario ingresado:"+usuario+" \t contrseña ingresada: "+pass.toString());
        String auxUser= MVC.getConfig().getProperty("usuario");
        String auxPass= MVC.getConfig().getProperty("contraseña");
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
				
		return usuario.equals(auxUser) && (new String(pass).equals(auxPass));
	}
	
	private boolean compara(String cadena, char[]arreglo) {
		boolean res=false;
		//char[] cadenaArreglo= cadena.toCharArray();
		if(cadena.length() == arreglo.length) {
			int i=0;
			while(res==false && i++ < cadena.length()) {
				if(Character.isLetterOrDigit(cadena.charAt(i))) res= cadena.charAt(i)== arreglo[i];
				else break;
			}
			return res;
		}else
			return res;
	}
	
}
