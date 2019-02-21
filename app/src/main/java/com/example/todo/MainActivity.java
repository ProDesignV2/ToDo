package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.todo.models.Tasks;

public class MainActivity extends Activity {

    AppCompatImageButton goto_create_button;
    SharedPreferences prefs;
    ListView taskView;

    ListAdapter taskAdapter;
    String[] dummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goto_create_button = findViewById(R.id.goto_round);
        taskView = findViewById(R.id.taskView);
        goto_create_button.setVisibility(View.VISIBLE);

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

//        // Get number of tasks and save count
//        long numberTasks = Tasks.listAll(Tasks.class).size();
//        dummy = new String[(int)numberTasks];
//
//        // Attach string array to ListView
//        taskAdapter = new TaskAdapter(this,dummy);
//        taskView.setAdapter(taskAdapter);

        taskView.setOnScrollListener(
                new AbsListView.OnScrollListener() {
                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}

                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){ goto_create_button.setVisibility(View.GONE); }
                        else{ goto_create_button.setVisibility(View.VISIBLE); }
                    }
                }
        );
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
        // Get number of tasks and save count
        long numberTasks = Tasks.listAll(Tasks.class).size();
        dummy = new String[(int)numberTasks];

//        ((BaseAdapter)taskAdapter).notifyDataSetChanged();

        // Attach string array to ListView
        ListAdapter taskAdapter = new TaskAdapter(this,new String[(int)numberTasks]);
        taskView.setAdapter(taskAdapter);
    }
}