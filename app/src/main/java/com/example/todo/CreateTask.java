package com.example.todo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todo.models.Tasks;

import java.util.Calendar;

public class CreateTask extends Activity {

    Button addTask;
    CalendarView dateChoose;
    EditText nameInput;
    long selected_date;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        nameInput = findViewById(R.id.input_task_name);
        addTask = findViewById(R.id.add_task_button);
        dateChoose = findViewById(R.id.calendarInput);
        selected_date = System.currentTimeMillis();
        editor = getSharedPreferences("TO_DO_PREFS", MODE_PRIVATE).edit();

        dateChoose.setMinDate(selected_date);

        dateChoose.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year,month,dayOfMonth);
                        selected_date = calendar.getTimeInMillis();
                    }
                }
        );

        addTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!nameInput.getText().toString().matches("")) {
                            Tasks newTask = new Tasks(nameInput.getText().toString(), selected_date, false);
                            newTask.save();
                            // Flag that new task has been added
                            editor.putBoolean("task_added", true).apply();
                            finish();
                        }
                        else{ nameInput.setHintTextColor(Color.rgb(255,0,0)); }
                    }
                }
        );
    }
}
