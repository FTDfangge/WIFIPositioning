package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class PositionClient {
	private Socket server;
	private BufferedReader in;
	private PrintWriter out;
	
	private int id;
	private String name;
	
	public PositionClient() {
		try {
			server = new Socket(/*IPµÿ÷∑*/InetAddress.getLocalHost(), 2000);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
