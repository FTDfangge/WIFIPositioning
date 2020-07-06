package com.example.schoolwifipositioning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolwifipositioning.CLASSES.Client;

public class Start2Activity extends AppCompatActivity {
    TextView name;
    TextView p1;
    TextView p2;
    Button register;
    Thread thread;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 3:
                    Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Start2Activity.this,Start1Activity.class);
                    startActivity(intent);
                    break;
                case 4:
                    Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start2);

        thread = new Thread(runnable);
        thread.start();

        name = (TextView) findViewById(R.id.editPersonName);
        p1 = (TextView) findViewById(R.id.editPassword1);
        p2 = (TextView) findViewById(R.id.editPassword2);

        register = (Button) findViewById(R.id.registerbtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password1 = p1.getText().toString();
                String password2 = p2.getText().toString();
                if (password1.equals(password2)) {
                    String n = name.getText().toString();
                    if(Client.getClient().isConnect()) {
                        Client.getClient().send("R#" + n + "#" + password1);
                    } else {
                        Toast.makeText(getApplicationContext(), "The connection is disconnected, please check the network!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "The entered passwords are inconsistent!", Toast.LENGTH_SHORT).show();
                }
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
                    String msg = "";if (list[0].equals("YR")) {
                        Client.getClient().setID(list[1]);
                        Client.getClient().setPassword(p1.getText().toString());
                        what = 3;
                        msg += "Register successfully, your id is " + list[1] + "!";
                    } else if (list[0].equals("NR")) {
                        what = 4;
                        msg += list[1] + "!";
                    }
                    mHandler.sendMessage(mHandler.obtainMessage(what, msg));
                    if(what == 3) {
                        break;
                    }
                }
            }
        }
    };
}