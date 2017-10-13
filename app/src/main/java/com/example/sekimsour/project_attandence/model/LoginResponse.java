package com.example.sekimsour.project_attandence.model;

/**
 * Created by Sokkang on 10/10/2017.
 */

public class LoginResponse {
    private String access_token;
    private String token_type;
    public String getToken(){
        return token_type+" "+access_token;
    }
    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }
}
