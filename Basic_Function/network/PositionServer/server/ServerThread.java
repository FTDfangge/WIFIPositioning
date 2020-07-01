/**
 * 作者：何毛鑫
 * 学号：18301040
 * 邮箱：18301040@bjtu.edu.cn
 **/
import com.sun.corba.se.spi.activation.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Sever implements Runnable {
    private ServerSocket server;
    private Socket socket;
    private BufferedReader bufferedReader;
    PrintWriter printWriter;
    String log_in_TF;
    String info;
    PositionServer2 PS=new PositionServer2();

    public void run() {
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {

                String message = "";
                String x = bufferedReader.readLine();
                if(x==null){
                    bufferedReader.close();
                    break;
                }
                System.out.println(x);

                //筛选从客户端接收到的信息的类别
                String[] define = x.split("#");
                if(define[0].equals("L")){
                    log_in_TF=PS.searchCustomer(Integer.parseInt(define[1]),define[2]);
                    if(log_in_TF.equals("user id doesn't exist")){
                        message="NL" + "#" + "user id doesn't exist";
                    }else if(log_in_TF.equals("password is wrong")){
                        message="NL" + "#" + "password is wrong";
                    }else{
                        message="YL";
                        info=log_in_TF;
                    }
                    printWriter.println(message);
                    printWriter.flush();
                }

                String[] information=info.split("#");
                switch (define[0]) {
                    case "R":
                        log_in_TF=PS.searchCustomer(Integer.parseInt(define[1]),define[2]);
                        if(log_in_TF==null){
                            message = "YR" + "#" + Integer.parseInt(define[1]);
                        }
                        else{
                            message="NR" + "#" + "Name already exists";
                        }
                        printWriter.println(message);
                        printWriter.flush();
                        break;
                    case "M":
                        if(define[2].equals("--name--")){
                            PS.modifyCustomer(Integer.parseInt(information[0]),'P',define[2]);
                        }
                        else{
                            PS.modifyCustomer(Integer.parseInt(information[0]),'N',define[2]);
                        }
                        message="YM";
                        printWriter.println(message);
                        printWriter.flush();
                        break;

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

