package com.alonemusk.medicalapp.ui.Cart;

public class CartDetails {
    public CartDetails(String user_id, int medicine_id, int quantity, String insert_at, String medicine_name, String COMPOSITION, int HSN_CODE, String GST, String price, int p_T_R, int p_T_S, String discount_price, String type, String product_description) {
        this.user_id = user_id;
        this.medicine_id = medicine_id;
        this.quantity = quantity;
        this.insert_at = insert_at;
        this.medicine_name = medicine_name;
        this.COMPOSITION = COMPOSITION;
        this.HSN_CODE = HSN_CODE;
        this.GST = GST;
        this.price = price;
        P_T_R = p_T_R;
        P_T_S = p_T_S;
        this.discount_price = discount_price;
        this.type = type;
        this.product_description = product_description;
    }

    private String user_id;
    private  int medicine_id;
    private int quantity;
    private String insert_at;
    private String medicine_name;
    private  String COMPOSITION;
    private float HSN_CODE;
    private String GST;
    private String price;
    private float P_T_R;
    private float P_T_S;
    private String discount_price;
    private String type;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInsert_at() {
        return insert_at;
    }

    public void setInsert_at(String insert_at) {
        this.insert_at = insert_at;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getCOMPOSITION() {
        return COMPOSITION;
    }

    public void setCOMPOSITION(String COMPOSITION) {
        this.COMPOSITION = COMPOSITION;
    }

    public float getHSN_CODE() {
        return HSN_CODE;
    }

    public void setHSN_CODE(int HSN_CODE) {
        this.HSN_CODE = HSN_CODE;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getP_T_R() {
        return P_T_R;
    }

    public void setP_T_R(int p_T_R) {
        P_T_R = p_T_R;
    }

    public float getP_T_S() {
        return P_T_S;
    }

    public void setP_T_S(int p_T_S) {
        P_T_S = p_T_S;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    private String product_description;
}
