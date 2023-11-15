package com.example.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionEvent {

    @Id
    private int event_id;
    private int event_timestamp;
    private String event_type;
    private String user_id;
    private float transaction_amount;
    private String transaction_currency;

    public TransactionEvent() {
    }

    public TransactionEvent(int event_id, int event_timestamp, String event_type, String user_id, float transaction_amount, String transaction_currency) {
        this.event_id = event_id;
        this.event_timestamp = event_timestamp;
        this.event_type = event_type;
        this.user_id = user_id;
        this.transaction_amount = transaction_amount;
        this.transaction_currency = transaction_currency;
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

    public float getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(float transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_currency() {
        return transaction_currency;
    }

    public void setTransaction_currency(String transaction_currency) {
        this.transaction_currency = transaction_currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "event_id=" + event_id +
                ", event_timestamp=" + event_timestamp +
                ", event_type='" + event_type + '\'' +
                ", user_id='" + user_id + '\'' +
                ", transaction_amount=" + transaction_amount +
                ", transaction_currency='" + transaction_currency + '\'' +
                '}';
    }
}
