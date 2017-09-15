package com.example.sekimsour.project_attandence.Model;

import java.util.ArrayList;

/**
 * Created by Sokkang on 9/7/2017.
 */

public class TimeTable {
    private boolean status;
    private String message;
    private ArrayList<schedule> schedule;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<schedule> schedule) {
        this.schedule = schedule;
    }
}

class schedule{
    private String date;
    private ArrayList<daily_schedule> daily_schedules;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<daily_schedule> getDaily_schedules() {
        return daily_schedules;
    }

    public void setDaily_schedules(ArrayList<daily_schedule> daily_schedules) {
        this.daily_schedules = daily_schedules;
    }
}

class daily_schedule{
    private int status;
    private String time_start;
    private String time_end;
    private String subject_name;
    private String room;
    private String teacher_name;
    private String session_id;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
