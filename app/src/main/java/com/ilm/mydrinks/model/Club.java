package com.ilm.mydrinks.model;

/**
 * Created by ERD on 02/11/2016.
 */

public class Club {

    private String store_id;
    private String merchant_code;
    private String store_code;
    private String store_name;
    private String street;
    private String city_description;
    private String country_description;
    private String phone;
    private String record_status;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity_description() {
        return city_description;
    }

    public void setCity_description(String city_description) {
        this.city_description = city_description;
    }

    public String getCountry_description() {
        return country_description;
    }

    public void setCountry_description(String country_description) {
        this.country_description = country_description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecord_status() {
        return record_status;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

}
