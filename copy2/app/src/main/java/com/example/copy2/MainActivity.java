package com.example.copy2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SensorManager mySensorManager;
    private SensorEventListener gyroListener;
    private Sensor myGyroscope;

    private double roll;
    private double pitch;
    private double yaw;

    private double timestamp = 0.0;
    private double dt;

    private double rad_to_dgr = 180 / Math.PI;
    private static final float NS2S = 1.0f/1000000000.0f;

    TextView x;
    TextView y;
    TextView z;

    private TextView tv_id, tv_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x=(TextView) findViewById(R.id.x);
        y=(TextView) findViewById(R.id.y);
        z=(TextView) findViewById(R.id.z);

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        myGyroscope = mySensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double gyroX = sensorEvent.values[0];
                double gyroY = sensorEvent.values[1];
                double gyroZ = sensorEvent.values[2];

                dt = (sensorEvent.timestamp - timestamp)*NS2S;
                timestamp = sensorEvent.timestamp;

                if(dt-timestamp*NS2S !=0){
                    pitch = pitch + gyroY*dt;
                    roll = roll + gyroY*dt;
                    yaw = yaw + gyroZ;

                    x.setText("[roll]"+String.format("%.lf", roll*rad_to_dgr));
                    y.setText("[Pitch]:"+String.format("%.lf",pitch*rad_to_dgr));
                    z.setText("[yaw]"+String.format("%.lf",yaw*rad_to_dgr));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };



        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);


        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");

        tv_id.setText(userID);
        tv_pass.setText(userPass);

    }
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(gyroListener, myGyroscope, SensorManager.SENSOR_DELAY_UI);
    }
    protected void onPause(){
        super.onPause();
        mySensorManager.unregisterListener(gyroListener);
    }

    protected void onStop(){
        super.onStop();
    }
}

