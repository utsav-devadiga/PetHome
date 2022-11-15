package com.petHomeApps.petHome.Datamodels;

public class UserModel {

    String full_name;
    String phone_num;
    String email;
    String full_address;
    String city_text;
    String town_text;
    String landmark;
    String pincode;
    String state;
    String country;
    long timestamp;

    public UserModel(String full_name, String phone_num, String email, String full_address, String city_text, String town_text, String landmark, String pincode, String state, String country, long timestamp) {
        this.full_name = full_name;
        this.phone_num = phone_num;
        this.email = email;
        this.full_address = full_address;
        this.city_text = city_text;
        this.town_text = town_text;
        this.landmark = landmark;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
        this.timestamp = timestamp;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getCity_text() {
        return city_text;
    }

    public void setCity_text(String city_text) {
        this.city_text = city_text;
    }

    public String getTown_text() {
        return town_text;
    }

    public void setTown_text(String town_text) {
        this.town_text = town_text;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public UserModel() {
    }


}
