package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User {
    BufferedReader in;
    PrintWriter out;
    Socket conn; 
    String move = ""; 
	
	public User(Socket conn, BufferedReader in, PrintWriter out, String move) {
		this.in = in;
		this.out = out;
		this.conn = conn; 
		this.move = move; 
	}

}
