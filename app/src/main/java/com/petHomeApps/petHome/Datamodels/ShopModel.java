package com.petHomeApps.petHome.Datamodels;


import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopModel implements Parcelable {
    boolean cat_item;
    String category;
    String description;
    boolean dog_item;
    String id;
    boolean instock;
    String name;
    String prd_img;
    String price;
    Map<String, Integer> size;
    Map<String, Integer> color;

    public ShopModel(boolean cat_item, String category, String description, boolean dog_item, String id, boolean instock, String name, String prd_img, String price, Map<String, Integer> size, Map<String, Integer> color) {
        this.cat_item = cat_item;
        this.category = category;
        this.description = description;
        this.dog_item = dog_item;
        this.id = id;
        this.instock = instock;
        this.name = name;
        this.prd_img = prd_img;
        this.price = price;
        this.size = size;
        this.color = color;
    }

    protected ShopModel(Parcel in) {
        cat_item = in.readByte() != 0;
        category = in.readString();
        description = in.readString();
        dog_item = in.readByte() != 0;
        id = in.readString();
        instock = in.readByte() != 0;
        name = in.readString();
        prd_img = in.readString();
        price = in.readString();
    }

    public static final Creator<ShopModel> CREATOR = new Creator<ShopModel>() {
        @Override
        public ShopModel createFromParcel(Parcel in) {
            return new ShopModel(in);
        }

        @Override
        public ShopModel[] newArray(int size) {
            return new ShopModel[size];
        }
    };

    public boolean isCat_item() {
        return cat_item;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDog_item() {
        return dog_item;
    }

    public String getId() {
        return id;
    }

    public boolean isInstock() {
        return instock;
    }

    public String getName() {
        return name;
    }

    public String getPrd_img() {
        return prd_img;
    }

    public String getPrice() {
        return price;
    }

    public Map<String, Integer> getSize() {
        return size;
    }

    public Map<String, Integer> getColor() {
        return color;
    }

    public ShopModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (cat_item ? 1 : 0));
        dest.writeString(category);
        dest.writeString(description);
        dest.writeByte((byte) (dog_item ? 1 : 0));
        dest.writeString(id);
        dest.writeByte((byte) (instock ? 1 : 0));
        dest.writeString(name);
        dest.writeString(prd_img);
        dest.writeString(price);
    }
}
