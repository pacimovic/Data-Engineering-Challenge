package com.example.backend.entities;

public class Country {

    private String country;
    private int number;

    private float revenue;

    public Country(String country, int number) {
        this.country = country;
        this.number = number;
    }

    public Country(String country, float revenue) {
        this.country = country;
        this.revenue = revenue;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
