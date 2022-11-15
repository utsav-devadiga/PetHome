package com.petHomeApps.petHome.Datamodels;

public class DecoModel {
    String name;
    String price;
    String Description;


    public DecoModel(String name, String price, String description) {
        this.name = name;
        this.price = price;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

