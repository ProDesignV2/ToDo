package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;

import com.example.todo.models.Tasks;

public class MainActivity extends Activity {

    private RecycleAdapter taskAdapter;
    private LinearLayoutManager linearLayoutManager;
    private long taskCount;
    AppCompatImageButton goto_create_button;

    @Override
    protected void onResume() {
        super.onResume();
        if(Tasks.count(Tasks.class,null,null) > taskCount){
            taskCount++;
            taskAdapter.addedItem();
            linearLayoutManager.scrollToPositionWithOffset(0,0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goto_create_button = findViewById(R.id.goto_round);
        goto_create_button.setVisibility(View.VISIBLE);

        ClickListener listener = new ClickListener() {
            @Override
            public void onCheckBoxClicked(int position, boolean isChecked) {
                // Deal with checkbox change
                taskAdapter.checkedItem(position,isChecked);
            }

            @Override
            public void onLongClicked(int position) {
                // Deal with long click
                taskCount--;
                taskAdapter.deletedItem(position);
            }
        };

        RecyclerView taskView = findViewById(R.id.taskView);
        linearLayoutManager = new LinearLayoutManager(this);
        taskView.setLayoutManager(linearLayoutManager);
        taskAdapter = new RecycleAdapter(this,Tasks.findWithQuery(Tasks.class,"SELECT * FROM TASKS ORDER BY ID DESC",(String[])null),listener);
        taskView.setAdapter(taskAdapter);

        taskCount = Tasks.count(Tasks.class,null,null);

        goto_create_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, CreateTask.class));
                    }
                }
        );

        taskView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){ goto_create_button.setVisibility(View.GONE); }
                else{ goto_create_button.setVisibility(View.VISIBLE); }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}