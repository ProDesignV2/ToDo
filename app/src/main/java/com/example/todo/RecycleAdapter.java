package com.example.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.SimpleViewHolder> {

    List<Integer> dataSource;
    public RecycleAdapter() {
        // Generate item
        dataSource = new ArrayList<>();
        for(int i = 0; i <= 10; i++)
            dataSource.add()
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row,parent,false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView dateText;
        public CheckBox checked;
//        public LinearLayout taskRow;
        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameView);
            dateText = itemView.findViewById(R.id.dateView);
            checked = itemView.findViewById(R.id.taskChecked);
//            taskRow = itemView.findViewById(R.id.taskRowLayout);
        }
    }
}
