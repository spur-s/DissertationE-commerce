package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kishor_bhattarai.easymanpower.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManpowerDetailsActivity extends AppCompatActivity {
    TextView name, desc, price;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manpower_details);

        name = findViewById(R.id.detailName);
        desc = findViewById(R.id.detailDescription);
        price = findViewById(R.id.detailPrice);
        circleImageView = findViewById(R.id.detailImgView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String image = bundle.getString("image");
            Picasso.with(this).load("http://10.0.2.2:3000/uploads/" + image).into(circleImageView);
            name.setText(bundle.getString("name"));
            desc.setText(bundle.getString("desc"));
            price.setText(bundle.getString("price"));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onDialBtn(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:9860100588"));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
}
