package com.ilm.mydrinks.model;

/**
 * Created by ERD on 15/11/2016.
 */

public class MyBottle {
    public String product_description;
    public String purchase_date;
    public String keep_start_date;
    public String keep_end_date;
    public String take_date;
    public String original_volume;
    public String last_volume;
    public String picture;

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getKeep_start_date() {
        return keep_start_date;
    }

    public void setKeep_start_date(String keep_start_date) {
        this.keep_start_date = keep_start_date;
    }

    public String getKeep_end_date() {
        return keep_end_date;
    }

    public void setKeep_end_date(String keep_end_date) {
        this.keep_end_date = keep_end_date;
    }

    public String getTake_date() {
        return take_date;
    }

    public void setTake_date(String take_date) {
        this.take_date = take_date;
    }

    public String getOriginal_volume() {
        return original_volume;
    }

    public void setOriginal_volume(String original_volume) {
        this.original_volume = original_volume;
    }

    public String getLast_volume() {
        return last_volume;
    }

    public void setLast_volume(String last_volume) {
        this.last_volume = last_volume;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
