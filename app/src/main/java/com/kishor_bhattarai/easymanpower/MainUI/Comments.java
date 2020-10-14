package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kishor_bhattarai.easymanpower.Interfaces.ManpowerInterface;
import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.models.Review;
import com.kishor_bhattarai.easymanpower.models.ReviewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comments extends AppCompatActivity {
    public EditText review, rating, itemname;
    Button Review;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        review = findViewById(R.id.review);
        rating = findViewById(R.id.ratin);
        // Review = findViewById(R.id.btnreview1);
        itemname = findViewById(R.id.namee);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            id = bundle.getString("id");
        }

        Toast.makeText(this, "Id : " + id, Toast.LENGTH_SHORT).show();
        Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();

            }
        });


    }

    private void addReview() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ManpowerInterface bookApi = retrofit.create(ManpowerInterface.class);
        String r = review.getText().toString();
        String ratin = rating.getText().toString();
        String itemnam = itemname.getText().toString();

        com.kishor_bhattarai.easymanpower.models.Review review = new Review(Integer.parseInt(r), ratin, itemnam);


        Call<ReviewResponse> call = bookApi.postReview(review);

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Comments.this, "Done:" + response.code(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), com.kishor_bhattarai.easymanpower.MainUI.ViewReviews.class));

                    return;
                }

                ReviewResponse review = response.body();

                // for (ReviewResponse reviews : review) {
                String str = "";
                str += "Rating:" + review.getRating() + "/n";
                str += "reviews:" + review.getReview() + "/n";
                str += "itemname:" + review.getitemname() + "/n";


                Toast.makeText(Comments.this, "Review " + str, Toast.LENGTH_SHORT).show();


                // }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

                Toast.makeText(Comments.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

