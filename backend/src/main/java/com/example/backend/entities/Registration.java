package com.example.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "registrations")
public class Registration {

    @Id
    private int event_id;
    private int event_timestamp;
    private String event_type;
    private String user_id;
    private String country;
    private String name;
    private String device_os;
    private String marketing_campaign;

    public Registration() {
    }

    public Registration(int event_id, int event_timestamp, String event_type, String user_id, String country, String name, String device_os, String marketing_campaign) {
        this.event_id = event_id;
        this.event_timestamp = event_timestamp;
        this.event_type = event_type;
        this.user_id = user_id;
        this.country = country;
        this.name = name;
        this.device_os = device_os;
        this.marketing_campaign = marketing_campaign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getEvent_timestamp() {
        return event_timestamp;
    }

    public void setEvent_timestamp(int event_timestamp) {
        this.event_timestamp = event_timestamp;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }

    public String getMarketing_campaign() {
        return marketing_campaign;
    }

    public void setMarketing_campaign(String marketing_campaign) {
        this.marketing_campaign = marketing_campaign;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "event_id=" + event_id +
                ", event_timestamp=" + event_timestamp +
                ", event_type='" + event_type + '\'' +
                ", user_id='" + user_id + '\'' +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", device_os='" + device_os + '\'' +
                ", marketing_campaign='" + marketing_campaign + '\'' +
                '}';
    }
}
