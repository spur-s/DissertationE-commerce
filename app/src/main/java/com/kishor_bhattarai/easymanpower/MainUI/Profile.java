package com.kishor_bhattarai.easymanpower.MainUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kishor_bhattarai.easymanpower.Interfaces.UserInterface;
import com.kishor_bhattarai.easymanpower.R;
import com.kishor_bhattarai.easymanpower.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends Fragment {
    TextView name1,email1,username1;
    EditText name2,email2,username2,contact,password;
    Bundle bundle=getArguments();
    Button update;
    public String BASE_URL ="http://10.0.2.2:3000/";
    String uid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_profile, container, false);

        name1=v.findViewById(R.id.name1);
        email1=v.findViewById(R.id.email1);
        username1=v.findViewById(R.id.username1);
        name2=v.findViewById(R.id.name2);
        email2=v.findViewById(R.id.email2);
        username2=v.findViewById(R.id.username2);
        contact=v.findViewById(R.id.contact);
        password=v.findViewById(R.id.password);

        uid = getArguments().getString("userid");

        loadProfile() ;
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (bundle !=null) {
            contact=getView().findViewById(R.id.contact);
            contact.setText(bundle.getString("contact", "err"));
        }
    }
    private void loadProfile(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        Call<User> userCall= userInterface.getUserProfile(uid);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                name1.setText(response.body().getName());
                email1.setText(response.body().getEmail());
                username1.setText(response.body().getUsername());
                name2.setText(response.body().getName());
                email2.setText(response.body().getEmail());
                username2.setText(response.body().getUsername());
                contact.setText(response.body().getContact());
                password.setText(response.body().getPassword());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

