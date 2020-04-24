package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import application.TicTacToe;

// ClientHandler class 
class Game implements Runnable {
	TicTacToe game;
	Socket p1;
	Socket p2;
	private PrintWriter p1Out;
	private PrintWriter p2Out;
	private BufferedReader p1In;
	private BufferedReader p2In;

	public Game(Socket p1, Socket p2, PrintWriter p1Out, PrintWriter p2Out, BufferedReader p1In, BufferedReader p2In) {
		this.p1 = p1;
		this.p2 = p2;
		this.p1Out = p1Out;
		this.p1In = p1In;
		this.p2Out = p2Out;
		this.p2In = p2In;
	}

	private void sendMessage(PrintWriter out, String msg) {
		out.println(msg);
		out.write(msg);
		out.flush();
	}

	@Override
	public void run() {
		;
		sendMessage(p1Out, "p1");
		sendMessage(p2Out, "p2");
		while (true) {
			try {
				String p1move = p1In.readLine();
				p1move = p1move.substring(p1move.length() - 3);
				sendMessage(p2Out, p1move);
				String p2move = p2In.readLine();
				p2move = p2move.substring(p2move.length() - 3);
				sendMessage(p1Out, p2move);
			} catch (IOException e) {
			}
		}
	}
}
