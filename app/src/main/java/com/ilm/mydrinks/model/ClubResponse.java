package com.ilm.mydrinks.model;

import java.util.List;

/**
 * Created by ERD on 02/11/2016.
 */

public class ClubResponse {

    private boolean status;
    private String message;
    private List<Club> clubs;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}
