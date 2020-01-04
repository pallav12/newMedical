package com.alonemusk.medicalapp.ui.MyOrders;

public class OrderDetails {
    private int order_id;
    private int medicine_id;
    private int quantity;

    public OrderDetails(int order_id, int medicine_id, int quantity) {
        this.order_id = order_id;
        this.medicine_id = medicine_id;
        this.quantity = quantity;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public int getQuantity() {
        return quantity;
    }
}
