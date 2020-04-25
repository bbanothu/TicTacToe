// Java implementation of  Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 
package Server; 
import java.io.*; 
import java.util.*;
import java.net.*; 
  
// Server class 
public class Server  { 
    static Vector<User> ar = new Vector<>(); 
    static int i = 0; 
    public static void main(String[] args) throws IOException  { 
        @SuppressWarnings("resource")
		ServerSocket Listen = new ServerSocket(1234); 
        Socket newConnection; 
        while (true){ 
        	newConnection = Listen.accept(); 
            System.out.println("New client request received: " + newConnection); 
             
            // obtain input and output streams 
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(newConnection.getInputStream()));
            PrintWriter outputStream = new PrintWriter(newConnection.getOutputStream()); 
              
            System.out.println("Creating a new handler for this client..."); 
            User x = new User(newConnection, inputStream, outputStream, ""); 
  
        
            System.out.println("Adding this client to active client list"); 
            ar.add(x); 
            i++;
  
            if(ar.size() >= 2) {
            	//TicTacToe board = new TicTacToe();
            	User p1 = ar.get(0);
            	User p2 = ar.get(1);
            	p1.move = "X";
            	p2.move = "O";
            	ar.remove(p1);
            	ar.remove(p2);
            	Game game = new Game(p1.conn, p2.conn, p1.out, p2.out, p1.in, p2.in); 
                Thread gameThread = new Thread(game);
                gameThread.start(); 
                System.out.println("Running Game!"); 
                i = i-2;
            } 
        } 
    }

} 
