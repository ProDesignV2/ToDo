package com.example.todo.models;

import android.util.Log;

import com.orm.SugarRecord;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        DateTime start = new DateTime();
        DateTime end = new DateTime(this.date);

        long diff = end.getMillis()-start.getMillis();
        int days = (int) (diff / (1000*60*60*24));

        Log.i("TESTING_START_DATE",Integer.toString(end.getYear()));

        // 1900 year offset?
        return days - 693690 - 269;

//        Interval interval = new Interval(new Instant(),end);
//
//        Log.i("DATE_DEBUG",Long.toString(interval.toDuration().getStandardDays()));
//        Log.i("TESTING",Long.toString(end.getMillis()));
//        Log.i("TESTING",(new DateTime().toString()));

//        return 1;
//        Duration duration = (new Interval(end, new Instant())).toDuration();
//        Log.i("DATE_DEBUG",Integer.toString(duration.toStandardDays().getDays()));


//        return duration.toStandardDays().getDays();
//        return Days.daysBetween(start.withTimeAtStartOfDay(),end.withTimeAtStartOfDay()).getDays();
    }
}
