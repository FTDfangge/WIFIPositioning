package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread implements Runnable {
	private Socket client;
	private BufferedReader in;
    private PrintWriter out;
    private Server ps ;
    private boolean isLogin;
    private int id;
    private String name;
    private String info;
    
	public ServerThread(Socket client, Server ps) {
		this.client = client;
		this.ps = ps;
		isLogin = false;
		try {
			out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String str) {
		System.out.println(client.getRemoteSocketAddress() + "¡û" + str);
		out.println(str);
		out.flush();
	}
	
	public String accept() {
		try {
			String str = in.readLine();
			System.out.println(client.getRemoteSocketAddress() + "¡ú" + str);
			return str;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
    public void run() {
        while (true) {
		    String x = accept();
		    if(x == null) {
		    	break;
		    }
		    String[] define = x.split("#");
		    String message = "";
			switch (define[0]) {
			case "L":
				info = ps.searchCustomer(Integer.parseInt(define[1]),define[2]);
		        if(info.equals("user id doesn't exist")){
		            message="NL" + "#" + "user id doesn't exist";
		        }else if(info.equals("password is wrong")){
		            message="NL" + "#" + "password is wrong";
		        }else{
		            isLogin = true;
		            String[] information = info.split("#");
		            id = Integer.parseInt(information[0]);
		            name = information[2];
		            message="YL#" + name;
		        }
		        send(message);
				break;
			case "R":
				info = ps.insertCustomer(define[1], define[2]);
				if (info != null) {
					message = "YR" + "#" + info;
				} else {
					message = "NR" + "#" + "Name already exists";
				}
				send(message);
				break;
			case "M":
				if(isLogin) {
					if (define[2].equals("--name--")) {
						ps.modifyCustomer(id, 'P', define[2]);
					} else {
						ps.modifyCustomer(id, 'N', define[2]);
					}
					message = "YM";
					send(message);
				}
				break;
			}
			System.out.println(message);
		}
        System.out.println(client.getRemoteSocketAddress() + " disconnect");
    }
}
