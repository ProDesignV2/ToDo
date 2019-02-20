package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todo.models.Tasks;

class TaskAdapter extends ArrayAdapter<String> {

    TaskAdapter(Context context, String[] dummy) {
        super(context, R.layout.task_row, dummy);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater taskInflater = LayoutInflater.from(getContext());
        View taskView = taskInflater.inflate(R.layout.task_row, parent, false);

        final TextView nameText = taskView.findViewById(R.id.nameView);
        TextView dateText = taskView.findViewById(R.id.dateView);
        CheckBox checked = taskView.findViewById(R.id.taskChecked);
        final LinearLayout taskRow = taskView.findViewById(R.id.taskRowLayout);

        // Set TextViews from database
        final long row_id = position + 1;
        nameText.setText(Tasks.findById(Tasks.class,row_id).getName());
        dateText.setText(String.valueOf(Tasks.findById(Tasks.class,row_id).dateUntil()));

        checked.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Tasks.findById(Tasks.class,row_id).setChecked(isChecked);
                        // Change this to grey out
                        if(isChecked){ taskRow.setAlpha((float)0.25); }
                        else{ taskRow.setAlpha(1); }
                    }
                }
        );

        return taskView;
    }

}
