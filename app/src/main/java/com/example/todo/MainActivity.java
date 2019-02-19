package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.todo.models.Tasks;

public class MainActivity extends Activity {

    ImageButton goto_create_button;
    boolean tasks_updated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goto_create_button = findViewById(R.id.goto_round);

        goto_create_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tasks_updated = false;
                        startActivity(new Intent(MainActivity.this, CreateTask.class));
                    }
                }
        );

        // Populate list for first time
        update_list();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(!tasks_updated) {
            update_list();
        }
    }

    public void update_list(){
        // Get number of tasks
        long numberTasks = Tasks.listAll(Tasks.class).size();

        // Attach string array to ListView
        ListAdapter taskAdapter = new TaskAdapter(this,new String[(int)numberTasks]);
        ListView taskView = findViewById(R.id.taskView);
        taskView.setAdapter(taskAdapter);

        tasks_updated = true;
    }
}