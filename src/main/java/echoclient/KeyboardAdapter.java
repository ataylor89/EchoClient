package echoclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrewtaylor
 */
public class KeyboardAdapter extends Thread {
    private Socket socket;
    
    public KeyboardAdapter(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        PrintWriter out;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(KeyboardAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String line = in.readLine();
                
                if (line != null) {
                    out.println(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(KeyboardAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
