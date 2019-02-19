package com.example.todo.models;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

public class Tasks extends SugarRecord<Tasks> {

    private String name;
    private Date date;
    private Boolean checked;

    public Tasks(String name, Date date, Boolean checked) {
        this.name = name;
        this.date = date;
        this.checked = checked;
    }

    public Tasks() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public int dateUntil(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        long until = Math.abs(this.date.getTime() - today.getTime());
    }
}
