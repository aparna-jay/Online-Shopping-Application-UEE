package com.uee.onlineshoppingapplication.OnlineDB;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ShoppingCart {
    public String id;
    public String userId;
    public String image;
    public String itemName;
    public String unitPrice;
    public String quantity;

    public ShoppingCart() {
    }

    public ShoppingCart(String id, String userId, String image, String itemName, String unitPrice, String quantity) {
        this.id = id;
        this.userId = userId;
        this.itemName = itemName;
        this.image = image;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
