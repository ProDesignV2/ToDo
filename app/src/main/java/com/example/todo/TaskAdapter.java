package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todo.models.Tasks;

class TaskAdapter extends ArrayAdapter<String> {

    TaskAdapter(Context context, String[] dummy) {
        super(context, R.layout.task_row, dummy);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater taskInflater = LayoutInflater.from(getContext());
        View taskView = taskInflater.inflate(R.layout.task_row, parent, false);

        TextView nameText = taskView.findViewById(R.id.nameView);
        TextView dateText = taskView.findViewById(R.id.dateView);

        // Set TextViews from database
        long row_id = position + 1;
        nameText.setText(Tasks.findById(Tasks.class,row_id).getName());
        dateText.setText(String.valueOf(Tasks.findById(Tasks.class,row_id).dateUntil()));

        return taskView;
    }

}
