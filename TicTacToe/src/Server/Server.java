// Java implementation of  Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 
package Server; 
import java.io.*; 
import java.util.*;

import application.TicTacToe;

import java.net.*; 
  
// Server class 
public class Server  { 
    // Vector to store active clients 
    static Vector<User> ar = new Vector<>(); 
    // counter for clients 
    static int i = 0; 
    
//	class User {
//        BufferedReader in;
//        PrintWriter out;
//        Socket conn; 
//        String move = ""; 
//		
//		public User(Socket conn, BufferedReader in, PrintWriter out, String move) {
//			this.in = in;
//			this.out = out;
//			this.conn = conn; 
//			this.move = move; 
//		}
//	}
  
    public static void main(String[] args) throws IOException  { 

        ServerSocket Listen = new ServerSocket(1234); 
        Socket newConnection; 
          

        while (true)  
        { 
        	newConnection = Listen.accept(); 
            System.out.println("New client request received : " + newConnection); 
             
            // obtain input and output streams 
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(newConnection.getInputStream()));
            PrintWriter outputStream = new PrintWriter(newConnection.getOutputStream()); 
              
            System.out.println("Creating a new handler for this client..."); 
            // Create a new handler object for handling this request. 
            User x = new User(newConnection, inputStream, outputStream, ""); 
  
//			@SuppressWarnings("deprecation")
//			String print = inputStream.readLine();
//			System.out.println(print); 
            
            // Create a new Thread with this object.
              
            System.out.println("Adding this client to active client list"); 
            // add this client to active clients list 
            ar.add(x); 
  
            
            if(ar.size() >= 2) {
            	TicTacToe board = new TicTacToe();
            	
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
            }
            i++; 
  
        } 
    }

} 
