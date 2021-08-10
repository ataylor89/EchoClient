package echoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrewtaylor
 */
public class NetworkAdapter extends Thread {
    
    private Socket socket;
    
    public NetworkAdapter(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));       
        } catch (IOException ex) {
            Logger.getLogger(NetworkAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        while (true) {
            try {
                String line = in.readLine();
                if (line != null)
                    System.out.println(line);
            } catch (IOException ex) {
                Logger.getLogger(NetworkAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
