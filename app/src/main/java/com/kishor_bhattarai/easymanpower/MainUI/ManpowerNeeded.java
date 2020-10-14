package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kishor_bhattarai.easymanpower.R;

public class ManpowerNeeded extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manpower_needed);
        Button storeinformation = (Button) findViewById(R.id.storeinformation);
        Button showinformation = (Button) findViewById(R.id.showinformation);
        textView = (TextView) findViewById(R.id.txtPrefs);

        View.OnClickListener listener = new  View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case  R.id.storeinformation:
                        Intent intent = new Intent (ManpowerNeeded.this,PrefsActivity.class);
                        startActivity(intent);
                        break;

                    case  R.id.showinformation :
                        displaySharedPreperances ();
                        break;
                    default:
                        break;
                }
            }
        };
        storeinformation.setOnClickListener(listener);
        showinformation.setOnClickListener(listener);

    }

    private  void displaySharedPreperances () {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(ManpowerNeeded.this);
        String itemname = prefs.getString ("Itemname","Default Nickname");
        String category = prefs.getString("category","Default Category");

        String listPrefs = prefs.getString("listpref","Default list prefs");

        StringBuilder builder = new StringBuilder();
        builder.append("Itemname:" + itemname+"\n");
        builder.append("Category:" + category + "\n");
        builder.append("List preference:" +listPrefs);
        textView.setText(builder.toString());

    }
}