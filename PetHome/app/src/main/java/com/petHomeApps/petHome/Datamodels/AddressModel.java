package com.petHomeApps.petHome.Datamodels;

public class AddressModel {
    String pincode;
    String full_address;
    String landmark;
    String town;
    String city;
    String state;
    String country;
    String id;

    public AddressModel(String pincode, String full_address, String landmark, String town, String city, String state,  String country, String id) {
        this.pincode = pincode;
        this.full_address = full_address;
        this.landmark = landmark;
        this.town = town;
        this.city = city;
        this.state = state;
        this.country = country;
        this.id = id;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddressModel(String pincode, String fulladdress, String landmark, String towns, String city, String state, String country) {
    }
}