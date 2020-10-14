package com.kishor_bhattarai.easymanpower.MyBLL;

import com.kishor_bhattarai.easymanpower.Interfaces.UserInterface;
import com.kishor_bhattarai.easymanpower.models.LoginSignupResponse;
import com.kishor_bhattarai.easymanpower.strictmode.StrictModeClass;
import com.kishor_bhattarai.uri.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    private String username;
    private String password;
    boolean isSuccess = false;

    public LoginBLL(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean checkUer(){
        UserInterface userInterface = Url.getInstance().create(UserInterface.class);
        Call<LoginSignupResponse> usersCall = userInterface.checkUser(username,password);

        try {
            StrictModeClass.StrictMode();
            Response<LoginSignupResponse> imageResponseResponse = usersCall.execute();
            if (imageResponseResponse.body().getSuccess()) {
                Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                Url.userid = imageResponseResponse.body().getId();
                isSuccess = true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }

}
