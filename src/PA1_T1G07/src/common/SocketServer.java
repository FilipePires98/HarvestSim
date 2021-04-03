package common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class SocketServer implements Runnable{
    
    /**
     * Port assigned to the server.
     */
    private int port;
    
    /**
     * Instance of the message processor assigned to the server.
     */
    private MessageProcessor mp;

    /**
     * Class constructor for the server definition.
     * @param port IP address assigned to the server.
     * @param mp Port assigned to the server.
     */
    public SocketServer(int port, MessageProcessor mp) {
        this.port=port;
        this.mp = mp;
    }
    
    /**
     * Executes the life-cycle of the socket server.
     * When receiving a new message the server passes the message to the respective message processor.
     */
    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(this.port);
            Socket inSocket = socket.accept();
            DataInputStream socketInputStream = new DataInputStream(inSocket.getInputStream());
            DataOutputStream socketOutputStream = new DataOutputStream(inSocket.getOutputStream());
            String receivedMessage="a";
            while(!receivedMessage.equals("endSimulationOrder")){
                receivedMessage=socketInputStream.readUTF();
                System.out.println("Transmitted Message: "+receivedMessage);
                this.mp.processMessage(receivedMessage);
                socketOutputStream.writeUTF("Message Processed");
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
