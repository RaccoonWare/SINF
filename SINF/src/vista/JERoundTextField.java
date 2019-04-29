/** 
 * campo de texto persoalizado, bordes redondeados
 * @author Edisoncor 
 */
package vista;
/* importación de librerias*/
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import java.awt.Paint; 
import java.awt.geom.RoundRectangle2D; 
import javax.swing.Icon; 
import javax.swing.ImageIcon; 
import javax.swing.JTextField; 
import javax.swing.border.EmptyBorder; 

/**
 * 
 * clase pricipal
 * @see javax.swing.JTextField
 */
public class JERoundTextField extends JTextField{ 
	//variables de clase
    private int arcw=20;//longitud de la curva 
    private int arch=20; //altura de la curva
    private Image image= new ImageIcon(//imagen del icono
            getClass().getResource("/iconos/buscar.png")).getImage(); 
    private Icon icon; //icono del campo

    /////////////Constructores e inicializadores
     /*
      * constructor por defecto
      */
    public JERoundTextField() { 
        setOpaque(false); 
        setBorder(new EmptyBorder(0,5,0,2)); 
        setPreferredSize(new Dimension(100,20)); 
    } 
    
    /**
     * sobreescritura de la función de renderizado del componente
     * @ g componente grafico
     */
    @Override 
     protected void paintComponent(Graphics g) { 

        Graphics2D g2 = (Graphics2D) g; 
        Paint oldPaint = g2.getPaint(); 

        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float( 
                0,0,getWidth(),getHeight(),arcw,arch); 
        g2.clip(r2d); 

        g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 
                0.0f, getHeight(), getBackground())); 
        g2.fillRect(0,0,getWidth(),getHeight()); 
        if(getImage()!=null){ 
            g2.drawImage(getImage(), 5, 2, getHeight()-3, getHeight()-3, null); 
            setBorder(new EmptyBorder(0,(int)(getHeight()*1.2),0,2)); 
        } 
        g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.BLACK, 
                0.0f, getHeight(), Color.BLACK)); 
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), arcw, arch); 

        g2.setPaint(oldPaint); 
        super.paintComponent(g); 

    }//fin paintComponent
    
    /////////accesores y modificadores

    /**
     * obtiene longitud horionte del arco
     * @return
     */
    public int getArcw() { 
        return arcw; 
    } //fin getArcw
    
    /**
     * modifica la longitud horizontal del arco
     * @param arcw	nueva longitud
     */
    public void setArcw(int arcw) { 
        this.arcw = arcw; 
    } //fin setArcw
    /**
     * obtiene longitud verdical del arco
     * @return
     */
    public int getArch() { 
        return arch; 
    } //fi getArch h
    /**
     * modifica la longitud vetical del arco
     * @param arch nueva altra
     */
    public void setArch(int arch) { 
        this.arch = arch; 
    }//fin set ArcH 
    /**
     * obtiene la imgaen que se usa
     * @return
     */
    public Image getImage() { 
        return image; 
    } //fin getImage
    /**
     * modifica la imagen que se utiliza
     * @param image nueva imagen
     */
    public void setImage(Image image) { 
        this.image = image; 
    } //fin setImage
    /**
     * obtiene el icono que se muetra
     * @return
     */
    public Icon getIcon() { 
        return icon; 
    } //fin getIcon
    /**
     * cambia el icono que se muetra
     * @param icon nuevo icono
     */
    public void setIcon(Icon icon){ 
        this.icon=icon; 
        setImage(((ImageIcon)icon).getImage()); 
    }// fin setIcon

}//fin clase principal


