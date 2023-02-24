package com.example.graph2;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by taewoo on 2019-11-16.
 */

public class Upitem {

    @SerializedName("ymd")
    @Expose
    public
    int ymd;

    @SerializedName("price")
    @Expose
    public
    int price;

    public Upitem(int ymd, int price) {
        this.ymd = ymd;
        this.price = price;
    }


    public int getYmd() {
        return ymd;
    }

    public void setYmd(int ymd) {
        this.ymd = ymd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}