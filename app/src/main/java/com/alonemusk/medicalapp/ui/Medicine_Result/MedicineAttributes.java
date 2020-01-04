package com.alonemusk.medicalapp.ui.Medicine_Result;

public class MedicineAttributes {
    private String medicine_name;
    private String medicine_id;
    private String type;
    private String product_discription;
    private String price;
    private String discount_price;

    public MedicineAttributes(String medicine_name, String medicine_id, String type, String product_discription, String price, String discount_price) {
        this.medicine_name = medicine_name;
        this.medicine_id = medicine_id;
        this.type = type;
        this.product_discription = product_discription;
        this.price = price;
        this.discount_price = discount_price;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public String getType() {
        return type;
    }

    public String getProduct_discription() {
        return product_discription;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount_price() {
        return discount_price;
    }
}