package com.kishor_bhattarai.easymanpower.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewList> {
    Context mContext;
    List<Review> myReviewList;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    public ReviewAdapter(Context mContext, List<Review> reviewList) {
        this.mContext = mContext;
        this.myReviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_layout, viewGroup, false);
        return new ReviewList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewList reviewList, int i) {
        final Review reviewModel = myReviewList.get(i);

        reviewList.rating.setText(String.valueOf(reviewModel.getRating()));
        reviewList.review.setText(reviewModel.getReview());
        reviewList.itemname.setText(reviewModel.getitemname());


//        String images = myReviewList.get(i).getImgview();
//        Log.d("val", "onBindReviewHolder:" + images);
//        final String path = BASE_URL + "/uploads" + "/" + images;
//
//        Log.d("aaa", "onBindReviewHolder:" + reviewList.get(i).getReview());
//        System.out.println("Path: " + path);
//        try {
//            URL uri = new URL(path);
//            bitmap = BitmapFactory.decodeStream((InputStream) uri.getContent());
//            Review.imgview.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public int getItemCount() {
        return myReviewList.size();
    }

    public class ReviewList extends RecyclerView.ViewHolder {

        TextView rating, review,itemname;


        public ReviewList(View view) {
            super(view);
            itemname = itemView.findViewById(R.id.namee);
            rating = itemView.findViewById(R.id.ratin);
            review = itemView.findViewById(R.id.review);
        }
    }
}





