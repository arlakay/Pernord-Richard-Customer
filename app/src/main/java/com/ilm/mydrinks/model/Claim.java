package com.ilm.mydrinks.model;

/**
 * Created by ERD on 11/18/2016.
 */

public class Claim {
    private boolean status;
    private String messages;

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
}
