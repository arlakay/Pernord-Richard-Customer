package com.ilm.mydrinks.model;

/**
 * Created by ERD on 11/18/2016.
 */

public class Profile {
    private boolean status;
    private String messages;
    private String customer_id;
    private String customer_code;
    private String password;
    private String first_name;
    private String last_name;
    private String birth_date;
    private String gender;
    private String email;
    private String phone_number;
    private String street;
    private String picture;
    private String record_status;
    private String point_balance;
    private String last_update_point;
    private String city_description;
    private String country_description;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRecord_status() {
        return record_status;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

    public String getPoint_balance() {
        return point_balance;
    }

    public void setPoint_balance(String point_balance) {
        this.point_balance = point_balance;
    }

    public String getLast_update_point() {
        return last_update_point;
    }

    public void setLast_update_point(String last_update_point) {
        this.last_update_point = last_update_point;
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
}
