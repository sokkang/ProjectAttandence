package com.example.sekimsour.project_attandence.Model;

public class Session{
    String sub;
    String room;

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

    public Session(String sub, String room) {
        this.sub = sub;
        this.room = room;
    }
    public String toString(){
        return room+sub;
    }
}
