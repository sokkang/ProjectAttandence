package com.example.sekimsour.project_attandence.model;

import java.util.List;

/**
 * Created by SE KIMSOUR on 13/10/2017.
 */

public class ListStudent {
    String status;
    String message;
    List<Group> group_list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Group> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<Group> group_list) {
        this.group_list = group_list;
    }
}
