package com.useo.demo.dtos;

// This service contain the logic for register new user

public class RegisterUserDto {

    private String email;
    private String password;
    private String fullName;

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getFullName() {
        return fullName;
    }
}
