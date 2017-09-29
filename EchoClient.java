/* CS 380 - Computer Networks
 * Assignment 1 :Server/Client
 * Ismail Abbas
 */


import java.io.*;
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;

public final class EchoClient {

	private static Scanner kb;

    public static void main(String[] args) throws Exception {
        boolean initialRun;
        String exitStr = ""; 
        initialRun = true;      
        kb = new Scanner(System.in);
        
        	try (Socket socket = new Socket("localhost", 22222)) {
        
				 // Message is to be sent, remember that out = write out
				 // Sockets are the way java programs let a device communicate with a server that is listening
				 // "exit" leaves the program
                 while(!exitStr.equals("exit")) {         
				 
                    String address = socket.getInetAddress().getHostAddress();
                    OutputStream outStr = socket.getOutputStream();
                    PrintStream out = new PrintStream(outStr, true, "UTF-8");
                    
                    System.out.print("Client> ");
                    exitStr = kb.nextLine();
                    out.println(exitStr); 

                    // Message is to be received, remember that in = receive (kinda backwards but it works)
                    InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String serverMessage = br.readLine();
                    // if message from server is not null, echo message
                    if(serverMessage != null && !serverMessage.equals("Server> exit"))
                        System.out.println(serverMessage);
                }
        	}
        
    }

}

















