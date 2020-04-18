package com.alonemusk.medicalapp.ui.Medicine_Result;

public class MedicineAttributes {
    private String medicine_name;
    private String medicine_id;
    private String COMPOSITION;
    private String GSTRATE;
    private String CURRENTPRICE;

    public MedicineAttributes(String medicine_name, String medicine_id, String COMPOSITION, String GSTRATE, String CURRENTPRICE) {
        this.medicine_name = medicine_name;
        this.medicine_id = medicine_id;
        this.COMPOSITION = COMPOSITION;
        this.GSTRATE = GSTRATE;
        this.CURRENTPRICE = CURRENTPRICE;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public void setMedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
    }

    public void setCOMPOSITION(String COMPOSITION) {
        this.COMPOSITION = COMPOSITION;
    }

    public void setGSTRATE(String GSTRATE) {
        this.GSTRATE = GSTRATE;
    }

    public void setCURRENTPRICE(String CURRENTPRICE) {
        this.CURRENTPRICE = CURRENTPRICE;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public String getCOMPOSITION() {
        return COMPOSITION;
    }

    public String getGSTRATE() {
        return GSTRATE;
    }

    public String getCURRENTPRICE() {
        return CURRENTPRICE;
    }
}