package com.example.a2grahj29.accelerometermeaurement;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sMgr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor accel = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sMgr.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        DecimalFormat df = new DecimalFormat("#.##");

        TextView xacc = (TextView)findViewById(R.id.textViewX);
        TextView yacc = (TextView)findViewById(R.id.textViewY);
        TextView zacc = (TextView)findViewById(R.id.textViewZ);

        xacc.setText(df.format(event.values[0]));
        yacc.setText(df.format(event.values[1]));
        zacc.setText(df.format(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
