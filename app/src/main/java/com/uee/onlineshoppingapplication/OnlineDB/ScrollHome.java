package com.uee.onlineshoppingapplication.OnlineDB;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ScrollHome {
    public String id;
    public String name;
    public String price;
    public String image;
    public String description;
    public String userId;

    public ScrollHome(){

    }

    public ScrollHome(String id, String name, String price, String image, String description, String userId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.userId = userId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
