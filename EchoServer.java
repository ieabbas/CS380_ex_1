/* CS 380 - Computer Networks
 * Assignment 1 : Server/Client
 * Ismail Abbas
 */
 
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * This class will represent the server and the corresponding interactions it will have with the client
 *
 */
public final class EchoServer {

    public static void main(String[] args) throws Exception {

        boolean initialRun = true;
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    
                    String address = socket.getInetAddress().getHostAddress();

					// IF it's the first run, then tell the user you conncted successfully
                    if(initialRun) {
                        System.out.printf("Client connected: %s%n", address);
                        initialRun = false;
                    }
						
                    // Jesus this took forever to put together right
                    InputStream inStr = socket.getInputStream();
                    InputStreamReader inStrRead = new InputStreamReader(inStr, "UTF-8");
                    BufferedReader br = new BufferedReader(inStrRead);
                    String message = br.readLine();
					// If the word of the day isn't "exit", don't leave the party early
                    if(!message.equals("exit")) { 
                        OutputStream outStr = socket.getOutputStream();
                        PrintStream outPrint = new PrintStream(outStr, true, "UTF-8");
                        String serverSend = "Server> " + message;
                        outPrint.println(serverSend);
						} else {
						// Tell the user you finished and get out of here
                        System.out.printf("Client disconnected: %s%n", address);
                        initialRun = true;
                    }
				}
					
			}
            
        }
    }
    
}