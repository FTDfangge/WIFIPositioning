import java.io.*;
import java.net.*;

/**
 * 作者：何毛鑫
 * 学号：18301040
 * 邮箱：18301040@bjtu.edu.cn
 **/
public class To_Server implements Runnable{
    private static Socket client;
    private BufferedReader bufferedReader;
    private static public PrintWriter writer;

    public void send_to_server(String message){

        writer.println(message);
        writer.flush();
    }
    
    public String recive_server(){
        String x="";
        new Thread(){public void run()
        {             
            try {
            InputStreamReader inputStreamReader=new InputStreamReader(client.getInputStream());
            bufferedReader=new BufferedReader(inputStreamReader);
                while(true){
            x=bufferedReader.readLine();
            System.out.println(x);}
        }catch (Exception e){
            e.printStackTrace();
        }
      
        };
                    }.start();
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
}

