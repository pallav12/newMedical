package com.alonemusk.medicalapp;

public class BaseAddress {
    private String baseurl="http://ec2-13-235-73-199.ap-south-1.compute.amazonaws.com:3000/";

    public BaseAddress() {
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }
}
