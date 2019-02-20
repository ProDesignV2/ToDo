package com.example.todo.models;

import com.orm.SugarRecord;

public class Tasks extends SugarRecord<Tasks> {

    private String name;
    private long dateTime;
    private boolean checked;

    public Tasks() {
    }

    public Tasks(String name, long dateTime, boolean checked) {
        this.name = name;
        this.dateTime = dateTime;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int dateUntil(){
        // Calculate milliseconds till due date
        long diff = this.dateTime - System.currentTimeMillis();
        // Convert to days and round up
        diff = diff / (1000 * 60 * 60 * 24);
        return (int) diff + 1;
    }
}
