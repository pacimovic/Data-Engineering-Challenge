package com.example.backend.entities;

public class Country {

    private String country;
    private int activeUsersNumber;

    public Country(String country, int activeUsersNumber) {
        this.country = country;
        this.activeUsersNumber = activeUsersNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getActiveUsersNumber() {
        return activeUsersNumber;
    }

    public void setActiveUsersNumber(int activeUsersNumber) {
        this.activeUsersNumber = activeUsersNumber;
    }
}
