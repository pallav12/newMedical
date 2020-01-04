package com.alonemusk.medicalapp.ui.OrderByPres;

public class PrescriptionForm {
    private String status;
    private String Shipped;
    private String Delivered;
    private String name;
    private String description;

    public PrescriptionForm(String status, String shipped, String delivered, String name, String description) {
        this.status = status;
        Shipped = shipped;
        Delivered = delivered;
        this.name = name;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getShipped() {
        return Shipped;
    }

    public String getDelivered() {
        return Delivered;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
