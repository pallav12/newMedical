package com.alonemusk.medicalapp.ui.Cart;

public class CartDetails {
    private String medicine_name;
   private int cart_id;
   private  int user_id;
    private  int medicine_id;
    private int quantity;
    private int discount_price;

    public CartDetails(int cart_id, int user_id, int medicine_id,String medicine_name,int quantity,int discount_price) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.medicine_id = medicine_id;
        this.medicine_name=medicine_name;
        this.quantity=quantity;
        this.discount_price=discount_price;

    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount_price(int discount_price) {
        this.discount_price = discount_price;
    }

    public int getDiscount_price() {
        return discount_price;
    }

    public int getCart_id() {
        return cart_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public int getQuantity() {
        return quantity;
    }
}
