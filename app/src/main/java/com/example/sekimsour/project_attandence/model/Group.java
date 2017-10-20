package com.example.sekimsour.project_attandence.model;

import java.util.List;

/**
 * Created by SE KIMSOUR on 13/10/2017.
 */

public class Group {
    String group_name;
    int group_id;
    List<Students> student_list;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public List<Students> getStudent_list() {
        return student_list;
    }

    public void setStudent_list(List<Students> student_list) {
        this.student_list = student_list;
    }
}
