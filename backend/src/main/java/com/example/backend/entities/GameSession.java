package com.example.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity()
@Table(name = "sessions")
public class GameSession {

    @Id
    private int sessionId;
    private String user_id;
    private int logInTime;
    private int logOutTime;

    public GameSession(int sessionId, String user_id, int logInTime, int logOutTime) {
        this.sessionId = sessionId;
        this.user_id = user_id;
        this.logInTime = logInTime;
        this.logOutTime = logOutTime;
    }

    public GameSession() {
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getLogInTime() {
        return logInTime;
    }

    public void setLogInTime(int logInTime) {
        this.logInTime = logInTime;
    }

    public int getLogOutTime() {
        return logOutTime;
    }

    public void setLogOutTime(int logOutTime) {
        this.logOutTime = logOutTime;
    }

}
