package com.petHomeApps.petHome.Datamodels;

import java.util.ArrayList;

public class SpinnerColors {
    String colorCode;

    ArrayList<SpinnerColors> colorList = new ArrayList<>();

    public SpinnerColors(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public ArrayList<SpinnerColors> getColorList() {
        return colorList;
    }

}
