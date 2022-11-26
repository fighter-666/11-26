package com.example.threadrandomservicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static double rand;
    private static TextView textView=null;
    private static Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.tv);
        Button btn1= (Button) findViewById(R.id.btn1);
        Button btn2=(Button) findViewById(R.id.btn2);
        final Intent serviceIntent=new Intent(this,RandomService.class);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(serviceIntent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });

    }
    public static void upd(double ref){
        rand=ref;
        handler.post(refble);
    }

    private static Runnable refble=new Runnable() {
        @Override
        public void run() {
            textView.setText(String.valueOf(rand));
        }
    };
}