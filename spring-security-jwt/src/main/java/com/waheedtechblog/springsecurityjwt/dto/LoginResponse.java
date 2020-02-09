package com.waheedtechblog.springsecurityjwt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @JsonProperty("access_token")
    private final String accessToken;
    @JsonProperty("token_type")
    private final String token_type = "Bearer";
    @JsonProperty("expires_in")
    private final int expiresIn = 3600;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
