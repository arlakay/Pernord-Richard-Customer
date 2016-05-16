package com.erd.reblood.model;

/**
 * Created by ILM on 5/12/2016.
 */
public class Event {
    public String idstore;
    public String merchant_id;
    public String store_id;
    public String store_name;
    public String description;
    public String street;
    public String city;
    public String country;
    public String phone;

    public Event() {
    }

    public Event(String idstore, String merchant_id, String store_id, String store_name,
                 String description, String street, String city, String country, String phone) {
        this.idstore = idstore;
        this.merchant_id = merchant_id;
        this.store_id = store_id;
        this.store_name = store_name;
        this.description = description;
        this.street = street;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public String getIdstore() {
        return idstore;
    }

    public void setIdstore(String idstore) {
        this.idstore = idstore;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
