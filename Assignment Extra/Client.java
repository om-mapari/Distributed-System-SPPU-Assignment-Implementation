
import java.net.*;
import java.io.*;

public class Client {
    
    public static void main(String[] args) {
        try{
            // Socket connection
            System.out.println("Client Started");
            Socket soc = new Socket("localhost", 6666);
            
            // Read Keyboard Input
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter A String: ");
            String str = userInput.readLine();

            // Send Input to the server
            PrintWriter send = new PrintWriter(soc.getOutputStream(),true);
            send.println(str);

            // Get Response From the Server
            BufferedReader serverMsg = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            System.out.println(serverMsg.readLine());

            soc.close();

        } catch (Exception e){
            System.out.println("Server not found: " + e.getMessage());
        }
    }
}


// reading
// BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

// sending
// PrintWriter send = new PrintWriter(soc.getOutputStream(),true);
// send.println(str);
