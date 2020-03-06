
package TwitterCorreo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Gui extends JFrame implements ActionListener{
    TwitterCorreo twitter = new TwitterCorreo();
    public Gui() {    
    
    // Creacion de componenetes
        this.setTitle("Analisis de Twitter");
        JLabel Titulo = new JLabel();
        JLabel Icono = new JLabel(new ImageIcon("C:/Users/USUARIO/Documents/NetBeansProjects/TwitterUD/src/Image1.png"));
        JLabel Message = new JLabel();
        JButton Salir = new JButton("Salir");
        Salir.addActionListener(this);
        Salir.setActionCommand("Salir");
        JButton enviar = new JButton("Enviar Correo");
        enviar.addActionListener(this);
        enviar.setActionCommand("enviar");
        JLabel CantLik = new JLabel();
       this.setSize(700,300);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
// Colores de los componentes
        Color colorGris=new Color(65,63,64);
        this.getContentPane().setBackground(colorGris);
        Color colorAzul1=new Color(54,162,238);
        Titulo.setForeground(colorAzul1);
        Color colorAzul2=new Color(144,210,246);
        Message.setForeground(colorAzul2);
        CantLik.setForeground(colorAzul2);
// Coordenadas de los componentes
        CantLik.setBounds(160, 180, 300, 30);
        Icono.setBounds(20, 0, 102, 102);
        Titulo.setBounds(170, 30, 400, 40);      
        Salir.setBounds(400,215,80,30);
        enviar.setBounds(240, 215, 140, 30);
// Fuente de los componentes
        Titulo.setFont(new Font("Berlin Sans FB",Font.PLAIN,24));
        Message.setFont(new Font("Berlin Sans FB",Font.PLAIN,18));
        CantLik.setFont(new Font("Berlin Sans FB",Font.PLAIN,18));
// Modificacion de texto        
        Titulo.setText("Twit con mayor cantidad de likes:");
        Message.setText("<html>"+twitter.getTextTwit()+"<html>");
        CantLik.setText("La cantidad de \"Me gustas\" es: "+twitter.getLikes());
//Implementarlos a la ventana emergente        
        this.add(Salir);
        this.add(enviar);
        this.add(CantLik);
        this.add(Titulo);
        this.add(Icono);
        this.add(Message);
        this.setVisible(true);
    }    

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salir")){
            this.dispose();
        }
        if (e.getActionCommand().equals("enviar")){
            try {
                twitter.EnviarCorreo();
            } catch (MessagingException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
