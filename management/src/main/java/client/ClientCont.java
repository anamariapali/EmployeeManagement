package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import server.ClientServer;

;

public class ClientCont {
	 private final String hostName = "192.168.23.1";///"192.168.1.2"; // "192.168.0.227"; //"10.128.55.213";
     private final int portNumber = 1342;
     private Socket kkSocket = null;
     private ObjectOutputStream out = null;
     private ObjectInputStream in = null;
     
     private ClientServer cs;
     
     public
     void connect() {
         System.out.println("Client: try to connect");

         // connect to the server
         try {
             kkSocket = new Socket(hostName, portNumber);
             System.out.println("aici");
             out = new ObjectOutputStream(kkSocket.getOutputStream());
             System.out.println("aici out");
             in = new ObjectInputStream(kkSocket.getInputStream());
             System.out.println("Client: connected");
         } catch (UnknownHostException e) {
             System.err.println("Don't know about host " + hostName);
             System.exit(1);
         } catch (IOException e) {
             System.err.println("Client (connect): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         }

     }

}
