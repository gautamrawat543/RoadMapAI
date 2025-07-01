package com.roadmap.Roadmap_AI.dto;

public class GoogleUserInfo {
    private String email;
    private String name;

    public GoogleUserInfo() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoogleUserInfo(String email, String name) {
        this.email = email;
        this.name = name;
    }
}