package com.example.todo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.todo.models.Tasks;

import java.util.Date;

public class CreateTask extends Activity {

    Button addTask;
    CalendarView dateChoose;
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        nameInput = findViewById(R.id.input_task_name);
        addTask = findViewById(R.id.add_task_button);
        dateChoose = findViewById(R.id.calendarInput);

        addTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tasks newTask = new Tasks(nameInput.getText().toString(),new Date(dateChoose.getDate()),false );
                        newTask.save();

                        finish();
                    }
                }
        );
    }
}
