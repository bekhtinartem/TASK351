package com.example.task351;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class Light extends Service implements SensorEventListener {
    SensorManager sensorManager;
    MediaPlayer mp;
    double value_start=-1;
    boolean isplayoing=false;
    float[] data=new float[100];
    @Override
    public void onCreate(){
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_UI );
        System.out.println("Service work");
        mp = MediaPlayer.create(this, R.raw.music);
    }
    private void loadSensorData(SensorEvent event) {
        final int type = event.sensor.getType();
        if (type == Sensor.TYPE_LIGHT) {
            data = event.values.clone();
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        loadSensorData(sensorEvent);
        if(value_start==-1){
            value_start=data[0];

        }
        if(data[0]<value_start){
            System.out.println("start music");
            makeMusic();
        }else{
            if(isplayoing){
                mp.stop();
                isplayoing=false;
            }
        }
        for(int i=0;i<data.length;i++){
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    public void onDestroy(){
        mp.stop();
    }
    private void makeMusic(){
        if(!isplayoing) {
            mp = MediaPlayer.create(this, R.raw.music);


            mp.start();
            isplayoing=true;
        }

    }

}
