package com.example.sekimsour.project_attandence.model;

import java.util.ArrayList;

public class schedule{
    private String date;
    private ArrayList<daily_schedule> daily_schedule;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<daily_schedule> getDaily_schedule() {
        return daily_schedule;
    }

    public void setDaily_schedule(ArrayList<daily_schedule> daily_schedule) {
        this.daily_schedule = daily_schedule;
    }
}
