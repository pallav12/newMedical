package com.alonemusk.medicalapp;

public class BaseAddress {
    private static String baseurl="http://ec2-13-235-73-199.ap-south-1.compute.amazonaws.com:3000";
private static String user_id="mahendra";
    public BaseAddress() {
    }

    public static String getBaseurl() {
        return baseurl;
    }

    public static void setBaseurl(String baseurl) {
        BaseAddress.baseurl = baseurl;
    }

    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        BaseAddress.user_id = user_id;
    }
}
