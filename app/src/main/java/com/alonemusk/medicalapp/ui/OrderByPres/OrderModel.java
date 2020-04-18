package com.alonemusk.medicalapp.ui.OrderByPres;

public class OrderModel {
    private int order_id;
    private String user_id;
    private String imagefile_refernce;
    private int address_id;
    private String status;

    public OrderModel(int order_id, String user_id, String imagefile_refernce, int address_id, String status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.imagefile_refernce = imagefile_refernce;
        this.address_id = address_id;
        status = status;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setImagefile_refernce(String imagefile_refernce) {
        this.imagefile_refernce = imagefile_refernce;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public void setStatus(String status) {
        status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getImagefile_refernce() {
        return imagefile_refernce;
    }

    public int getAddress_id() {
        return address_id;
    }

    public String getStatus() {
        return status;
    }
}
