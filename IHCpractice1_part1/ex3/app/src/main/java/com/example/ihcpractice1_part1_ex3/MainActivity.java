package com.example.ihcpractice1_part1_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText coord_x, coord_y, coord_z;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float sensorX=0, sensorY=0, sensorZ=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coord_x = findViewById(R.id.coord_x_field);
        coord_y = findViewById(R.id.coord_y_field);
        coord_z = findViewById(R.id.coord_z_field);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            if(
                    ((Math.abs(sensorX) - Math.abs(event.values[0])) > 2) ||
                            ((Math.abs(sensorY) - Math.abs(event.values[1])) > 2) ||
                            ((Math.abs(sensorZ) - Math.abs(event.values[2])) > 2)
            ) {
                Intent intent = new Intent(this, SuccessActivity.class);
                startActivity(intent);
            }
            sensorX = event.values[0];
            sensorY = event.values[1];
            sensorZ = event.values[2];
            coord_x.setText(String.valueOf(sensorX));
            coord_y.setText(String.valueOf(sensorY));
            coord_z.setText(String.valueOf(sensorZ));
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

}
