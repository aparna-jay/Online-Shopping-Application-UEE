package com.uee.onlineshoppingapplication.OnlineDB;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Favourites {
    public String id;
    public String price;
    public String image;
    public String userId;


    public Favourites(String id, String price, String image, String userId) {

        this.id = id;
        this.price = price;
        this.image = image;
        this.userId = userId;
    }

    public Favourites() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
