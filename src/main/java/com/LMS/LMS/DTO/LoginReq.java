package com.LMS.LMS.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginReq {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public LoginReq(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
