package com.example.handler2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView e2=findViewById(R.id.is);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
              if(msg.arg1==1){
                  e2.setText("是素数");
              }
              else
                  e2.setText("不是素数");
            }
        };
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                EditText e=findViewById(R.id.text);
                Message msg = new Message();
                String s=e.getText().toString();
                int x=Integer.parseInt(s);
                if(is(x)){
                    msg.arg1=1;

                }
                else{
                    msg.arg1=-1;
                }
                handler.sendMessage(msg);
            }
        };
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, runnable, "runnable");
                workThread.start();
            }
        });
    }
    public boolean is(int x){
        boolean isPrime = true;
        if(x ==1 || x %2 ==0 && x !=2 )
        {
            isPrime = false;
        }
        else
        {
            for( int i =3; i< Math.sqrt(x); i+=2)
            {
                if( x % i == 0)
                {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;

    }
}