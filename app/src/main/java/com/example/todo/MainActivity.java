package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.todo.models.Tasks;

public class MainActivity extends Activity {

    private AppCompatImageButton goto_create_button;
    private RecycleAdapter taskAdapter;
    private long taskCount;

    @Override
    protected void onResume() {
        super.onResume();
        if(Tasks.count(Tasks.class,null,null) > taskCount){
            taskAdapter.swapArrayList(Tasks.listAll(Tasks.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goto_create_button = findViewById(R.id.goto_round);
        goto_create_button.setVisibility(View.VISIBLE);

        RecyclerView taskView = findViewById(R.id.taskView);
        taskView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new RecycleAdapter(this,Tasks.listAll(Tasks.class));
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

//        taskView.setOnScrollListener(
//                new AbsListView.OnScrollListener() {
//                    @Override
//                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}
//
//                    @Override
//                    public void onScrollStateChanged(AbsListView view, int scrollState) {
//                        if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){ goto_create_button.setVisibility(View.GONE); }
//                        else{ goto_create_button.setVisibility(View.VISIBLE); }
//                    }
//                }
//        );
    }
}