package com.uee.onlineshoppingapplication.OnlineDB;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class paymentinfo {
    public String id;
    public String chName;
    public String expDate;
    public String cdType;
    public String cardNumber;
    public String cvv;

    public paymentinfo(String id, String chName, String expDate, String cdType,String cardNumber, String cvv) {
        this.id = id;
        this.chName = chName;
        this.expDate = expDate;
        this.cardNumber= cardNumber;
        this.cdType = cdType;
        this.cvv = cvv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCdType() {
        return cdType;
    }

    public void setCdType(String cdType) {
        this.cdType = cdType;
    }

    public String getCVV() {
        return cvv;
    }

    public void setCVV(String cvv) {
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
