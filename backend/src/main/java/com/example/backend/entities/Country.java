package com.example.backend.entities;

public class Country {

    private String country;
    private int intValue;
    private float floatValue;

    public Country(String country, int intValue) {
        this.country = country;
        this.intValue = intValue;
    }

    public Country(String country, float floatValue) {
        this.country = country;
        this.floatValue = floatValue;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }
}
