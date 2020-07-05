package com.example.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static Client c = new Client();
	private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;
    private String id;
    private String password;
    private String name;
    private boolean connect;
    private boolean quit;

    private Client () {
        id = "";
        password = "";
        name = "";
        connect = false;
        quit = false;
    }

    public void connect() {
        try {
            client = new Socket("192.168.101.12", 2000);
            client.setSoTimeout(5000);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            connect = true;
        } catch (IOException e) {
            connect = false;
        }
    }

    public void reConnect() {
        try {
            client = new Socket("192.168.101.12", 2000);
            reader =new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.flush();
            connect = true;
        } catch (IOException e) {
            connect = false;
        }
    }

    public void disConnect() {
        try {
            reader.close();
            writer.close();
            client.close();
            connect = false;
            quit = true;
        } catch (IOException e) {
            System.exit(0);
        }
    }

    public void send(String message) {
        final String msg = message;
        new Thread(new Runnable() {
            @Override
            public void run() {
                writer.println(msg);
                writer.flush();
            }
        }).start();
    }

    public String receive() {
        try {
            String mes = reader.readLine();
            return mes;
        } catch (IOException e) {
            return null;
        }
    }

    public static Client getClient() {
        return c;
    }

    //设置是否退出
    public void setQuit(boolean b) {
        quit = b;
    }

    //判断是否连接
    public boolean isConnect() {
        return connect;
    }

    //判断是否退出程序
    public boolean isQuit() {
        return quit;
    }

    //设置ID
    public void setID(String i) {
        id = i;
    }

    //设置密码
    public void setPassword(String p) {
        password = p;
    }

    //设置名称
    public void setName(String n) {
        name = n;
    }

    //获取ID
    public String getID() {
        return id;
    }

    //获取密码
    public String getPassword() {
        return password;
    }

    //获取名称
    public String getName() {
        return name;
    }
}
