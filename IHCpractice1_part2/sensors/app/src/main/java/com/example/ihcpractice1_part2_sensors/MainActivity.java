package com.example.ihcpractice1_part2_sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private TextView temp_text, light_text;
    private SensorManager mSensorManager;
    private Sensor mTemperature, mLight;
    private float temp=0, lux=0;
    private GPSTracker gps_tracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gps_tracker = new GPSTracker(getApplicationContext());

        temp_text = findViewById(R.id.temperature_field);
        light_text = findViewById(R.id.light_field);
        Button gps_button = findViewById(R.id.gps_button);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        gps_button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        String s1 = "Temperature: " + temp;
        String s2 = "Light: " + lux;
        temp_text.setText(s1);
        light_text.setText(s2);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType())
        {
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                temp = event.values[0];
                String s1 = "Temperature: " + temp;
                temp_text.setText(s1);
                break;

            case  Sensor.TYPE_LIGHT:
                lux = event.values[0];
                String s2 = "Light: " + lux;
                light_text.setText(s2);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onClick(View view)
    {

        if (view.getId() == R.id.gps_button) {
            Location l = gps_tracker.getLocation();
            if (l != null) {
                Toast.makeText(getApplicationContext(), "LAT: " + l.getLatitude() + "\nLONG: " + l.getLongitude(), Toast.LENGTH_LONG).show();
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

}
