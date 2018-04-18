package com.example.a2grahj29.accelerometermeaurement;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor accel, magField;
    float[] accelValues, magFieldValues, orientationMatrix, orientations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sMgr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accel = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magField = sMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sMgr.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
        sMgr.registerListener(this, magField, SensorManager.SENSOR_DELAY_UI);

        accelValues = new float[3];
        magFieldValues = new float[3];
        orientationMatrix = new float[16];
        orientations = new float[3];

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        DecimalFormat df = new DecimalFormat("#.##");

        TextView xacc = (TextView)findViewById(R.id.textViewX);
        TextView yacc = (TextView)findViewById(R.id.textViewY);
        TextView zacc = (TextView)findViewById(R.id.textViewZ);

        if(event.sensor == accel){
            for (int i=0; i<3; i++)
            accelValues[i] = event.values[i];
        }
        else if (event.sensor == magField){
            for (int i=0; i<3; i++)
                magFieldValues[i] = event.values[i];
        }

        SensorManager.getRotationMatrix(orientationMatrix, null, accelValues, magFieldValues);
        SensorManager.getOrientation (orientationMatrix, orientations);

        xacc.setText(df.format(orientations[0] * 180/Math.PI));
        yacc.setText(df.format(orientations[1] * 180/Math.PI));
        zacc.setText(df.format(orientations[2] * 180/Math.PI));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
