package com.petHomeApps.petHome.Datamodels;

import android.os.Parcel;
import android.os.Parcelable;

public class PetModel implements Parcelable {
    String petid;
    String pet_selected_category;
    int selected_pet_spinner_index;
    String pet_name;
    String pet_breed;
    String pet_age_date;
    String pet_age_month;
    String pet_age_year;
    String gender_text;
    public PetModel(){

    }


    public PetModel(String petid, String pet_selected_category, int selected_pet_spinner_index, String pet_name, String pet_breed, String pet_age_date, String pet_age_month, String pet_age_year,String gender_text) {
        this.petid = petid;
        this.pet_selected_category = pet_selected_category;
        this.selected_pet_spinner_index = selected_pet_spinner_index;
        this.pet_name = pet_name;
        this.pet_breed = pet_breed;
        this.pet_age_date = pet_age_date;
        this.pet_age_month = pet_age_month;
        this.pet_age_year = pet_age_year;
        this.gender_text=gender_text;
    }

    protected PetModel(Parcel in) {
        petid = in.readString();
        pet_selected_category = in.readString();
        selected_pet_spinner_index = in.readInt();
        pet_name = in.readString();
        pet_breed = in.readString();
        pet_age_date = in.readString();
        pet_age_month = in.readString();
        pet_age_year = in.readString();
        gender_text = in.readString();
    }

    public static final Creator<PetModel> CREATOR = new Creator<PetModel>() {
        @Override
        public PetModel createFromParcel(Parcel in) {
            return new PetModel(in);
        }

        @Override
        public PetModel[] newArray(int size) {
            return new PetModel[size];
        }
    };

    public String getPetid() {
        return petid;
    }

    public void setPetid(String petid) {
        this.petid = petid;
    }

    public String getPet_selected_category() {
        return pet_selected_category;
    }

    public void setPet_selected_category(String pet_selected_category) {
        this.pet_selected_category = pet_selected_category;
    }

    public int getSelected_pet_spinner_index() {
        return selected_pet_spinner_index;
    }

    public void setSelected_pet_spinner_index(int selected_pet_spinner_index) {
        this.selected_pet_spinner_index = selected_pet_spinner_index;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getPet_breed() {
        return pet_breed;
    }

    public void setPet_breed(String pet_breed) {
        this.pet_breed = pet_breed;
    }

    public String getPet_age_date() {
        return pet_age_date;
    }

    public void setPet_age_date(String pet_age_date) {
        this.pet_age_date = pet_age_date;
    }

    public String getPet_age_month() {
        return pet_age_month;
    }

    public void setPet_age_month(String pet_age_month) {
        this.pet_age_month = pet_age_month;
    }

    public String getPet_age_year() {
        return pet_age_year;
    }

    public void setPet_age_year(String pet_age_year) {
        this.pet_age_year = pet_age_year;
    }

    public String getGender_text() {
        return gender_text;
    }

    public void setGender_text(String gender_text) {
        this.gender_text = gender_text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(petid);
        dest.writeString(pet_selected_category);
        dest.writeInt(selected_pet_spinner_index);
        dest.writeString(pet_name);
        dest.writeString(pet_breed);
        dest.writeString(pet_age_date);
        dest.writeString(pet_age_month);
        dest.writeString(pet_age_year);
        dest.writeString(gender_text);
    }
}
