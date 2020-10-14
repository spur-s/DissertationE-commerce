package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kishor_bhattarai.easymanpower.MyBLL.RegisterBLL;
import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.models.User;
import com.kishor_bhattarai.easymanpower.strictmode.StrictModeClass;

public class RegisterActivity extends AppCompatActivity{

    EditText name, email,username, contact, password;
    Button register1, login1;
    private boolean isSuccess = false;
    public String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor proximitysensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (sensorEvent.values[0] < proximitysensor.getMaximumRange()) {


                    Toast.makeText(RegisterActivity.this,"Application Closed",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Far",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(sensorEventListener,proximitysensor,2*1000*1000);


        name = findViewById(R.id.regfullname);
        email = findViewById(R.id.regemail);
        username = findViewById(R.id.regusername);
        contact = findViewById(R.id.regcontact);
        password = findViewById(R.id.regpassword);

        register1 = findViewById(R.id.btnregister);
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictModeClass.StrictMode();

                User registerBLL = new User(
                        name.getText().toString(),
                        email.getText().toString(),
                        username.getText().toString(),
                        contact.getText().toString(),
                        password.getText().toString()
                );

                RegisterBLL bll = new RegisterBLL();
                if(bll.registerUser(registerBLL)){
                    name.setText("");
                    email.setText("");
                    username.setText("");
                    contact.setText("");
                    password.setText("");
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }

        });

        login1 = findViewById(R.id.btnlogin);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);

            }
        });
    }



    public boolean nullValidation(){
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(email.getText().toString())){
            email.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(username.getText().toString())){
            username.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(contact.getText().toString())){
            contact.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required Field");
            return false;
        }

        return true;
    }


}

