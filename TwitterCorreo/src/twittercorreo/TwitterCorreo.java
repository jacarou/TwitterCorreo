
package TwitterCorreo;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

// Autor Jhony Caro
public class TwitterCorreo {
    
    private static String TextTwit=null;
    private static int likes=0;
    
    public String getTextTwit() {
        return TextTwit;
    }

    public void setTextTwit(String TextTwit) {
        this.TextTwit = TextTwit;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void EnviarCorreo()throws javax.mail.internet.AddressException, javax.mail.MessagingException {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host","smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth","true");
        
        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "*******";
        String contrasena = "*******";
        String receptor = "cristianthrashx@gmail.com";
        String asunto = "POP Jhony Caro Cod 20191020055";
        String mensaje=this.TextTwit;
        
        MimeMessage mail = new MimeMessage(sesion);
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setText("El twit con mayor cantidad de \"me gusta\" es:\n "+mensaje+"\nLa cantidad de \"me gustas\" es: "+this.likes);
            
            javax.mail.Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado correctamente");
    }
    
    public void Extraer()throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("**************")
            .setOAuthConsumerSecret("**************")
            .setOAuthAccessToken("**************")
            .setOAuthAccessTokenSecret("**************");
	TwitterFactory tf = new TwitterFactory(cb.build());
	Twitter twitter = tf.getInstance();

        List<Status> statuses = twitter.getUserTimeline("udistrital", new Paging(1, 40));
	for (Status status : statuses) {
	    if(status.getFavoriteCount()>this.likes) {
        	this.TextTwit = status.getText();
            	this.likes=status.getFavoriteCount();    
            }
        }
    }
}
