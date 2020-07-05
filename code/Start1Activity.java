package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Start1Activity extends AppCompatActivity{
    TextView id;
    TextView password;
    Button login;
    Button register;
    TextView mes;
    Client c = Client.getClient();
    Thread thread;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Start1Activity.this,MenuActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start1);

        thread = new Thread(runnable);
        thread.start();

        id = (TextView) findViewById(R.id.userID);
        id.setText(Client.getClient().getID());
        password = (TextView) findViewById(R.id.password);

        login = (Button) findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mes = "L#";
                mes += id.getText().toString();
                mes += "#" + password.getText().toString();
                if(c.isConnect()) {
                    Client.getClient().send(mes);
                } else {
                    Toast.makeText(getApplicationContext(), "The connection is disconnected, please check the network!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        register = (Button) findViewById(R.id.registerbtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start1Activity.this,Start2Activity.class);
                startActivity(intent);
            }
        });

    }

    Runnable runnable = new  Runnable(){
        @Override
        public void run() {
            while (true) {
                String str = Client.getClient().receive();
                if (str != null) {
                    String[] list = str.split("#");
                    int what = 0;
                    String msg = "";
                    if(list[0].equals("YL")) {
                        Client.getClient().setID(id.getText().toString());
                        Client.getClient().setName(list[1]);
                        Client.getClient().setPassword(password.getText().toString());
                        what = 1;
                        msg += "Log in successfully!";
                    } else if (list[0].equals("NL")) {
                        what = 2;
                        msg += list[1] + "!";
                    }
                    mHandler.sendMessage(mHandler.obtainMessage(what, msg));
                    if(what == 1) {
                        break;
                    }
                }
            }
        }
    };
}
