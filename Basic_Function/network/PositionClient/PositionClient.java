import java.io.*;
import java.net.*;

/**
 * 作者：何毛鑫
 * 学号：18301040
 * 邮箱：18301040@bjtu.edu.cn
 **/
public class To_Server implements Runnable{
    static Socket client;
    BufferedReader bufferedReader;
    static public PrintWriter writer;

    public void send_to_server(String message){

        writer.println(message);
        writer.flush();
    }
    public String recive_server(){
        String x="";
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(client.getInputStream());
            bufferedReader=new BufferedReader(inputStreamReader);
            x=bufferedReader.readLine();
            System.out.println(x);
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }
public void run()
{
     try {
            client = new Socket("", 2000);
            writer = new PrintWriter(client.getOutputStream(), true);
            writer.flush();
            System.out.println("successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    
  /*  public void connect(String[] args) {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            client = new Socket(serverName, port);

            writer = new PrintWriter(client.getOutputStream(), true);
            writer.flush();
            System.out.println("successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
 }*/

