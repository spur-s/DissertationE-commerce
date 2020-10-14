package com.kishor_bhattarai.easymanpower.MyBLL;

import com.kishor_bhattarai.easymanpower.Interfaces.UserInterface;
import com.kishor_bhattarai.easymanpower.models.RegisterResponse;
import com.kishor_bhattarai.easymanpower.models.User;
import com.kishor_bhattarai.uri.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBLL {
    private String id;
    private String name;
    private String email;
    private String username;
    private String contact;
    private String password;
    boolean isSuccess = false;

    public RegisterBLL(String name, String email, String username, String contact, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.contact = contact;
        this.password = password;
    }

    public RegisterBLL()
    {

    }

    public boolean registerUser(User user) {
        UserInterface userInterface = Url.getInstance().create(UserInterface.class);
        Call<RegisterResponse> userCall = userInterface.registerUser(user);

        try {
            Response<RegisterResponse> imageResponseResponse = userCall.execute();
            if(imageResponseResponse.isSuccessful())
            {
                isSuccess = true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
