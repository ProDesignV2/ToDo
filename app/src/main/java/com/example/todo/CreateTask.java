package com.example.todo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo.models.Tasks;

import java.util.Date;

public class CreateTask extends Activity {

    Button addTask;
    CalendarView dateChoose;
    EditText nameInput;
    Date chosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        nameInput = findViewById(R.id.input_task_name);
        addTask = findViewById(R.id.add_task_button);
        dateChoose = findViewById(R.id.calendarInput);
        chosenDate = new Date();

        dateChoose.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        chosenDate = new Date(year,month,dayOfMonth);
                    }
                }
        );

        addTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tasks newTask = new Tasks(nameInput.getText().toString(),chosenDate,false);
                        newTask.save();

                        Toast.makeText(CreateTask.this,Integer.toString(chosenDate.getYear()),Toast.LENGTH_LONG).show();

                        finish();
                    }
                }
        );
    }
}
