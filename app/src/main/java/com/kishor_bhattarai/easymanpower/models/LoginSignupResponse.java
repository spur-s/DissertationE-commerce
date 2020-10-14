package com.kishor_bhattarai.easymanpower.models;

public class LoginSignupResponse {
    private boolean success;
    private String status;
    private String user;

    public boolean getSuccess(){return success;}
    public String getStatus(){return status;}

    public String getId(){
        return user;
    }
    public void setId(String user){
        this.user = user;
    }
}
