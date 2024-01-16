import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;

public class Frame extends JFrame{
    Frame(){
         
        this.setTitle("Login"); //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit out of application
        this.setResizable(false); //prrevent frame from being resized
        this.setSize(500,700); //sets the x-dimension and y-dimension of frame
        this.setVisible(true);// make frame visible

        ImageIcon logo = new ImageIcon("logo.JPG");
        this.setIconImage(logo.getImage());
        this.getContentPane().setBackground(new Color(42,139,224)); //change color of background

        
    }
}
