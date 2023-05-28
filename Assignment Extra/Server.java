
import java.net.*;
import java.io.*;

public class Server {


    public static void main(String[] args)  {
        try {
            System.out.println("Server Waiting for Client");

            // Create a ServerSocket
            ServerSocket ss = new ServerSocket(6666); // port number of server
            
            // Wait for client to connect
            Socket soc = ss.accept(); // as it get connecting it will return socket object
            System.out.println("Connection established");

            // Reading Client Message
            BufferedReader clientMsg = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String str = clientMsg.readLine();
            System.out.println("Message received From Client: " + str);

            // Sending Response to Client
            PrintWriter send = new PrintWriter(soc.getOutputStream(),true);
            send.println("Server says: " + str);

            soc.close();
            ss.close();
            System.out.println("Connection closed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
