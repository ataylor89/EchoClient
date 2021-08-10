package echoclient;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrewtaylor
 */
public class EchoClient {
    
    private Socket socket;
    private String hostname = "127.0.0.1";
    private int port = 8200;
    
    private NetworkAdapter networkReader;
    private KeyboardAdapter keyboardReader;
    
    public EchoClient() {
        
    }
    
    public void connect() {
        try {
            socket = new Socket(hostname, port);
        } catch (IOException ex) {
            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        networkReader = new NetworkAdapter(socket);
        keyboardReader = new KeyboardAdapter(socket);
        
        networkReader.start();
        keyboardReader.start();
    }
    
    public static void main(String[] args) throws IOException {
        EchoClient client = new EchoClient();
        client.connect();
    }
}
