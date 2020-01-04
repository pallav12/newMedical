package com.alonemusk.medicalapp.ui.MyOrders;

public class Order {
    private int order_id;
    private String user_id;
    private String address_id;
    private String status;
    private String insert_at;

    public Order(int order_id, String user_id, String address_id, String status, String insert_at) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.address_id = address_id;
        this.status = status;
        this.insert_at = insert_at;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInsert_at(String insert_at) {
        this.insert_at = insert_at;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public String getStatus() {
        return status;
    }

    public String getInsert_at() {
        return insert_at;
    }
}
