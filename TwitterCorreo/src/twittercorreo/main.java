
package TwitterCorreo;
// Autor Jhony caro

import twitter4j.TwitterException;

public class main {

    public static void main(String[] args) throws TwitterException {
        TwitterCorreo TT = new TwitterCorreo();
        TT.Extraer();
        Gui Ventana = new Gui();
    }
    
}
