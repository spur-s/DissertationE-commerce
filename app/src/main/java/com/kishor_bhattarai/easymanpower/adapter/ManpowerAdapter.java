package com.kishor_bhattarai.easymanpower.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kishor_bhattarai.easymanpower.MainUI.ManpowerDetailsActivity;
import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.models.Manpower;
import com.kishor_bhattarai.easymanpower.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManpowerAdapter extends RecyclerView.Adapter<ManpowerAdapter.ManpowerViewHolder> {
    Context mContext;
    List<Manpower> manpowerList;
    //    Event eventModel;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public ManpowerAdapter(Context mContext, List<Manpower> manpowerList) {
        this.mContext = mContext;
        this.manpowerList = manpowerList;
    }
    @NonNull
    @Override
    public ManpowerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.manpower_view,viewGroup,false);
        return new ManpowerViewHolder(view);
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull ManpowerViewHolder manpowerViewHolder, final int i) {

        final Manpower manpowerModel = manpowerList.get(i);


        manpowerViewHolder.name.setText(manpowerModel.getName());
        manpowerViewHolder.desc.setText(manpowerModel.getDesc());
        manpowerViewHolder.price.setText(manpowerModel.getPrice());


        StrictMode();
        String images = manpowerList.get(i).getImageName();
        final String path = BASE_URL+"/uploads"+"/"+images;
        Log.d("aaa", "onBindViewHolder: " + manpowerList.get(i).getImageName());
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            manpowerViewHolder.imgview.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        manpowerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ManpowerDetailsActivity.class);
                intent.putExtra("image", manpowerModel.getImageName());
                intent.putExtra("name", manpowerModel.getName());
                intent.putExtra("desc", manpowerModel.getDesc());
                intent.putExtra("price", manpowerModel.getPrice());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return manpowerList.size();

    }




    public class ManpowerViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgview;
        TextView name, desc, price;

        public ManpowerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview=itemView.findViewById(R.id.imgview);
            name=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.price);
        }
    }
}
