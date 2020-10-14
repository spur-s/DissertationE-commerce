package com.kishor_bhattarai.easymanpower.MainUI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kishor_bhattarai.easymanpower.Interfaces.ManpowerInterface;
import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.models.ImageResponse;
import com.kishor_bhattarai.easymanpower.models.Manpower;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddManpowerActivity extends AppCompatActivity {

    public EditText name, desc, price;
    ImageView imageView;
    public Button add, confirm;
    TextView imageName;
    Uri uri;
    Bitmap bitmap;
    Retrofit retrofit;
    ManpowerInterface manpowerInterface;
    private static final int PICK_IMAGE = 1;

    public String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manpower);

        name = findViewById(R.id.itemName);
        desc = findViewById(R.id.itemDescription);
        imageView = findViewById(R.id.imgPhoto);
        price = findViewById(R.id.itemprice);

        imageName = findViewById(R.id.tvImagename);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });


        confirm = findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(bitmap);
                Toast.makeText(AddManpowerActivity.this, "Image Confirmed", Toast.LENGTH_SHORT).show();
            }
        });

        add = findViewById(R.id.btnAddItem);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addManpower();
            }
        });
    }

    private void BrowseImage() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
    }

    private void addImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {
            File file = new File(this.getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            RequestBody requestBody = RequestBody.
                    create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.
                    createFormData("imageFile", file.getName(), requestBody);


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            manpowerInterface = retrofit.create(ManpowerInterface.class);
            Call<ImageResponse> imgCall = manpowerInterface.uploadImage(body);
            imgCall.enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    imageName.setText(response.body().getFilename());
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error is" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap
                        (getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addManpower() {
        if (nullValidation()) {

            String sname = name.getText().toString();
            String sdesc = desc.getText().toString();
            String simageName = imageName.getText().toString();
            String sprice = price.getText().toString();

            Manpower manpower = new Manpower(sname, sdesc, simageName, sprice);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ManpowerInterface manpowerInterface = retrofit.create(ManpowerInterface.class);

            Call<Void> call = manpowerInterface.addManpower(manpower);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("VAL", "success ");

                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "ITEM ADDED", Toast.LENGTH_SHORT).show();

                        Log.d("VAL", "success response ");

//                        name.setText("");
//
//                        description.setText("");
//                        imagename.setText("");

//                        imageView.setImageResource(R.drawable.noimage);
                        //             startActivity(new Intent(getApplicationContext(), ShowEventActivity.class));
                    } else {
                        try {
                            Log.d("VAL", response.toString());

                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error ", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public boolean nullValidation() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("Required Field");
            return false;

        } else if (TextUtils.isEmpty(desc.getText().toString())) {

            desc.setError("Required Field");
            return false;
        } else if (TextUtils.isEmpty(price.getText().toString())) {

            price.setError("Required Field");
            return false;
        }

        return true;
    }
}


