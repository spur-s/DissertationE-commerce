package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kishor_bhattarai.easymanpower.R;

public class Home extends Fragment {

    Button additem, viewitem1, postcomment, btnReviews,inqiry,needs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.activity_home, container, false);


        additem = root.findViewById(R.id.additem);
        viewitem1 = root.findViewById(R.id.viewitem1);
        postcomment = root.findViewById(R.id.postcomment);
        btnReviews = root.findViewById(R.id.btnreview11);
        inqiry = root.findViewById(R.id.inquiry);
        needs = root.findViewById(R.id.needs);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddManpowerActivity.class);
                startActivity(intent);
            }
        });

        viewitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowManpowerActivity.class);
                startActivity(intent);
            }
        });
        postcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowManpowerActivity.class);
                startActivity(intent);
            }
        });
        inqiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NumReport.class);
                startActivity(intent);
            }
        });

//        btnReviews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),Comments.class);
//                startActivity(intent);
//            }
//        });

        needs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ManpowerNeeded.class);
                startActivity(intent);
            }
        });



        return root;




    }
}
