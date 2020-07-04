package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread implements Runnable {
	private Socket client;
	private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Server ps ;
    private boolean isLogin;
    private int id;
    private String password;
    private String name;
    private String info;
    //String info;
    
	public ServerThread(Socket client, Server ps) {
		this.client = client;
		this.ps = ps;
		isLogin = false;
		try {
			printWriter = new PrintWriter(client.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

	@Override
    public void run() {
        try {
            printWriter = new PrintWriter(client.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                String message = "";
                String x = bufferedReader.readLine();
                if(x == null){
                    break;
                }
                System.out.println(x);

                //筛选从客户端接收到的信息的类别
                String[] define = x.split("#");
				
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
                        password = information[1];
                        name = information[2];
                        message="YL#" + name;
                    }
                    printWriter.println(message);
                    printWriter.flush();
					break;
				case "R":
					info = ps.insertCustomer(define[1], define[2]);
					if (info != null) {
						message = "YR" + "#" + info;
					} else {
						message = "NR" + "#" + "Name already exists";
					}
					printWriter.println(message);
					printWriter.flush();
					break;
				case "M":
					if(isLogin) {
						if (define[2].equals("--name--")) {
							ps.modifyCustomer(id, 'P', define[2]);
						} else {
							ps.modifyCustomer(id, 'N', define[2]);
						}
						message = "YM";
						printWriter.println(message);
						printWriter.flush();
					}
					break;
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
