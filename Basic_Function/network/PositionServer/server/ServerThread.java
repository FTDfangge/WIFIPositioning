package server;

import java.net.Socket;

import customerList.CustomerList;
import positionList.PositionList;

public class ServerThread implements Runnable {
	private Socket client;
	private CustomerList listC;
	private PositionList listP;
	
	public ServerThread(Socket client, CustomerList listC, PositionList listP) {
		this.client = client;
		this.listC = listC;
		this.listP = listP;
	}

	@Override
	public void run() {
		
	}

}
