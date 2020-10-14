package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.kishor_bhattarai.easymanpower.Interfaces.ManpowerInterface;
import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.adapter.ReviewAdapter;
import com.kishor_bhattarai.easymanpower.models.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewReviews extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);
        reviewBooks();

    }

    private void reviewBooks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ManpowerInterface bookApi = retrofit.create(ManpowerInterface.class);
        Call<List<Review>> listCall = bookApi.getAllReview();
        listCall.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code:" + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Review> reviewList = response.body();
                Toast.makeText(ViewReviews.this, String.valueOf(reviewList.size()),Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new ReviewAdapter(getApplicationContext(),reviewList));
                recyclerView.setLayoutManager(new GridLayoutManager(ViewReviews.this,2));
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error"+ t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}


