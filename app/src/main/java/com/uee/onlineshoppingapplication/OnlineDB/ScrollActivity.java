package com.uee.onlineshoppingapplication.OnlineDB;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ScrollActivity {
    public String id;
    public String pname;
    public String pprice;

    public ScrollActivity(){

    }

    public ScrollActivity(String id, String pname, String pprice) {
        this.id = id;
        this.pname = pname;
        this.pprice = pprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }
}
