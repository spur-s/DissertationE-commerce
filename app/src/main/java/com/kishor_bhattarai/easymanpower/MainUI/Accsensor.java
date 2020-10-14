package com.kishor_bhattarai.easymanpower.MainUI;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kishor_bhattarai.easymanpower.R;


public class Accsensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private boolean isMoved = false;
    private View view;
    private long lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accsensor);

       // view = findViewById(R.id.textView);
     //  view.setBackgroundColor(Color.GREEN);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();


    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();
//        Toast.makeText(getApplicationContext(),String.valueOf(accelationSquareRoot)+" "+
//                SensorManager.GRAVITY_EARTH, Toast.LENGTH_SHORT).show();

        if (accelationSquareRoot >= 2) //it will be executed if you shuffle
        {

            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle
            if (isMoved) {
                // view.setBackgroundColor(Color.GREEN);
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
                Toast.makeText(Accsensor.this,"Success", Toast.LENGTH_SHORT).show();

            } else {
               // view.setBackgroundColor(Color.RED);

               Toast.makeText(Accsensor.this,"Application Closed", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(Intent.ACTION_MAIN);
               intent.addCategory(Intent.CATEGORY_HOME);
             startActivity(intent);

         }
           isMoved = !isMoved;
      }
   }

  @Override
   protected void onResume() {
      super.onResume();
      // register this class as a listener for the orientation and
       //accelerometer sensors
      sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
           SensorManager.SENSOR_DELAY_NORMAL);
 }

   @Override
  protected void onPause() {
     // unregister listener
   super.onPause();
       sensorManager.unregisterListener(this);
  }

}

