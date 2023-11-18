package com.example.backend.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_in_outs")
public class LogInOut implements Comparable<LogInOut>{

    @Id
    private int event_id;
    private Integer event_timestamp;
    private String event_type;
    private String user_id;

    public LogInOut() {
    }

    public LogInOut(int event_id, Integer event_timestamp, String event_type, String user_id) {
        this.event_id = event_id;
        this.event_timestamp = event_timestamp;
        this.event_type = event_type;
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Integer getEvent_timestamp() {
        return event_timestamp;
    }

    public void setEvent_timestamp(Integer event_timestamp) {
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

    @Override
    public String toString() {
        return "LogInOut{" +
                "event_id=" + event_id +
                ", event_timestamp=" + event_timestamp +
                ", event_type='" + event_type + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    @Override
    public int compareTo(LogInOut log) {
        return this.event_timestamp.compareTo(log.getEvent_timestamp());
    }
}
