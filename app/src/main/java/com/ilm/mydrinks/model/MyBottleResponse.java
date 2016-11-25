package com.ilm.mydrinks.model;

import java.util.List;

/**
 * Created by ERD on 15/11/2016.
 */

public class MyBottleResponse {
    private boolean status;
    private String messages;
    private List<MyBottle> my_bottles;

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

    public List<MyBottle> getMy_bottles() {
        return my_bottles;
    }

    public void setMy_bottles(List<MyBottle> my_bottles) {
        this.my_bottles = my_bottles;
    }
}
