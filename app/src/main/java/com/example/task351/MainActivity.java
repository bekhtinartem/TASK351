package com.example.task351;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;

public class MainActivity extends AppCompatActivity {
    boolean working=false;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public  void start(android.view.View view){
        if(!working) {
            Intent intent = new Intent(this, Light.class);

            startService(intent);
            working=true;
        }
    }
    public void stop(android.view.View view){

            Intent intent = new Intent(this, Light.class);

            stopService(intent);
            working=false;
        }




}
