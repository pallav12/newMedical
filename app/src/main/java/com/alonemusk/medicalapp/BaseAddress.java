package com.alonemusk.medicalapp;

public class BaseAddress {
    private String baseurl="http://ec2-3-16-216-35.us-east-2.compute.amazonaws.com:3000";

    public BaseAddress() {
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }
}
