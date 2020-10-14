package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kishor_bhattarai.easymanpower.MyBLL.LoginBLL;
import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.strictmode.StrictModeClass;
import com.kishor_bhattarai.uri.Url;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {

    EditText username, password;
    Button login, regsiter;
    private boolean isSuccess = false;

    private NotificationManagerCompat notificationManagerCompat;
    private SensorManager sensorManager;
    private boolean isMoved = false;
    private View view;
    private long lastUpdate;

    public String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

        username = findViewById(R.id.logusername);
        password = findViewById(R.id.logpassword);
        login = findViewById(R.id.btnlog);
        regsiter = (Button) findViewById(R.id.btnreg);

        regsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoginBLL bll = new LoginBLL(username.getText().toString(),password.getText().toString());
               StrictModeClass.StrictMode();

                if (bll.checkUer()) {
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    intent.putExtra("userid", Url.userid);
                    startActivity(intent);
                    finish();

                    notificationManagerCompat = NotificationManagerCompat.from(LoginActivity.this);
                    Create channel = new Create(LoginActivity.this);
                    channel.createChannel();

                    Notification notification = new NotificationCompat.Builder(LoginActivity.this, Create.CHANNEL_1)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("No Connection")
                            .setContentText("No Connectivity, Please connect ")
                            .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                            .build();
                    notificationManagerCompat.notify(1, notification);

                    Vibrator vb = (Vibrator) getSystemService(VIBRATOR_SERVICE);


                    if (Build.VERSION.SDK_INT >= 26) {
                        vb.vibrate(VibrationEffect.createOneShot(900, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vb.vibrate(200);
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
//                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
//                        .addConverterFactory(GsonConverterFactory.create()).build();
//
//                UserInterface userInterface = retrofit.create(UserInterface.class);
//
//                final User user = new User(username.getText().toString().trim(), password.getText().toString().trim());
//
//                Call<User> call = userInterface.userLogin(user);
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//
//                        if (response.isSuccessful()) {
//
//                            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
//                            intent.putExtra("userid", response.body().getId());
//                            startActivity(intent);
//
//                            Log.d("VAL", "success response");
//                        } else {
//                            try {
//                                Log.d("VAL", response.toString());
//
//                                Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                        Log.d("VAL", t.getLocalizedMessage());
//
//                    }
//                });
//


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

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
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

            } else {
                // view.setBackgroundColor(Color.RED);

                Toast.makeText(LoginActivity.this, "Application Closed", Toast.LENGTH_SHORT).show();
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
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public boolean nullValidation() {
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Required Field");
            return false;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Required Field");
            return false;
        }
        return true;
    }


}





