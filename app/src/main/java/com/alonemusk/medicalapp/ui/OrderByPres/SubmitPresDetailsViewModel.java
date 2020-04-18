package com.alonemusk.medicalapp.ui.OrderByPres;

import android.net.Uri;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

public class SubmitPresDetailsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
   static
    ArrayList<Uri> images=new ArrayList<>();

    public SubmitPresDetailsViewModel(ArrayList<Uri> images) {
        this.images = images;
    }

    public ArrayList<Uri> getImages() {
        return images;
    }

    public void setImages(ArrayList<Uri> images) {
        this.images = images;
    }
}
