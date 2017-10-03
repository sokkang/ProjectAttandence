package com.example.sekimsour.project_attandence.Model;

public class Session{
    private String sub;
    private String room;
    private String sessionID;
    private int status;


    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Session() {
    }

    public Session(String sub, String room) {
        this.sub = sub;
        this.room = room;
    }
    public String toString(){
        return room+sub;
    }
}
