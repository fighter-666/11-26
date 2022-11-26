package com.example.threadrandomservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RandomService extends Service {
    private Thread work;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "调用onCreate()", Toast.LENGTH_SHORT).show();
        work=new Thread(null,bgd,"WorkThread");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "调用onStart()", Toast.LENGTH_SHORT).show();
        if (!work.isAlive()){
            work.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "调用Destory()", Toast.LENGTH_SHORT).show();
        work.interrupt();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private Runnable bgd=new Runnable() {
        @Override
        public void run() {
            try{
                while (!Thread.interrupted()){
                    double rand =Math.random();
                    MainActivity.upd(rand);
                    Thread.sleep(1000);
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };
}
