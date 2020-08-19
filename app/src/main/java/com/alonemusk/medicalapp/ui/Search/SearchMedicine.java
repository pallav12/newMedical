package com.alonemusk.medicalapp.ui.Search;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine_table")
public class SearchMedicine {
    @PrimaryKey
    @NonNull
    public String hns;
    public String name;
    public Boolean pres_needed;

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPtr() {
        return ptr;
    }

    public void setPtr(String ptr) {
        this.ptr = ptr;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String composition;
    public String cost;
    public String discount;
    public String ptr;

    public SearchMedicine(@NonNull String hns, String name, Boolean pres_needed, String composition, String cost, String discount, String PRT, String PTS) {
        this.hns = hns;
        this.name = name;
        this.pres_needed = pres_needed;
        this.composition = composition;
        this.cost = cost;
        this.discount = discount;
        this.ptr = PRT;
        this.pts = PTS;
    }

    public String pts;

    @Override
    public String toString() {
        return "MedicineNameId{" +
                "medicine_id=" + hns +
                ", medicine_name='" + name + '\'' +
                '}';
    }

    public SearchMedicine() {
        this.hns = "not_defined";
        this.name = "not_defined";
        this.pres_needed = false;
        this.composition = "not_defined";
        this.cost = "not_defined";
        this.discount = "0";
        this.ptr = "0";
        this.pts = "0";
    }

    public String getHns() {
        return hns;
    }

    public void setHns(String hns) {
        this.hns = hns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
