package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.todo.models.Tasks;

public class MainActivity extends Activity {

    AppCompatImageButton goto_create_button;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goto_create_button = findViewById(R.id.goto_round);

        prefs = getSharedPreferences("TO_DO_PREFS", MODE_PRIVATE);
        prefs.edit().putBoolean("task_added",false).apply();

        goto_create_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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

        if(prefs.getBoolean("task_added",false)) {
            update_list();
            prefs.edit().putBoolean("task_added",false).apply();
        }

    }

    public void update_list(){
        // Get number of tasks
        long numberTasks = Tasks.listAll(Tasks.class).size();

        // Attach string array to ListView
        ListAdapter taskAdapter = new TaskAdapter(this,new String[(int)numberTasks]);
        ListView taskView = findViewById(R.id.taskView);
        taskView.setAdapter(taskAdapter);
    }
}