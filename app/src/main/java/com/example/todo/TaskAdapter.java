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

    private boolean current_checked = false;
    private boolean current_deleted = false;
    private int task_count;

    TaskAdapter(Context context, String[] dummy) {
        super(context, R.layout.task_row, dummy);
        task_count = dummy.length;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater taskInflater = LayoutInflater.from(getContext());
        final View taskView = taskInflater.inflate(R.layout.task_row, parent, false);

        final TextView nameText = taskView.findViewById(R.id.nameView);
        TextView dateText = taskView.findViewById(R.id.dateView);
        CheckBox checked = taskView.findViewById(R.id.taskChecked);
        final LinearLayout taskRow = taskView.findViewById(R.id.taskRowLayout);

        // Set TextViews and checkbox from database
        final long row_id = task_count - position;
        nameText.setText(Tasks.findById(Tasks.class,row_id).getName());
        dateText.setText(String.valueOf(Tasks.findById(Tasks.class,row_id).dateUntil()));
        current_checked = Tasks.findById(Tasks.class,row_id).getChecked();
        current_deleted = Tasks.findById(Tasks.class,row_id).isDeleted();

//        if(current_deleted){ taskRow.setVisibility(View.GONE); }

        // Change opacity
        if(current_checked){ taskRow.setAlpha((float)0.25); }
        else{ taskRow.setAlpha(1); }
        // Ensure check status is correct
        checked.setChecked(current_checked);

        checked.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Update task details
                        Tasks task = Tasks.findById(Tasks.class,row_id);
                        task.setChecked(isChecked);
                        task.save();
                        // Update local flag
                        current_checked = isChecked;
                        // Grey out task row
                        if(current_checked){ taskRow.setAlpha((float)0.25); }
                        else{ taskRow.setAlpha(1); }
                    }
                }
        );

        taskRow.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Tasks task = Tasks.findById(Tasks.class,row_id);
                        task.setDeleted(true);
                        task.save();
//                        taskRow.setVisibility(View.GONE);
                        return true;
                    }
                }
        );

        if(current_deleted){ return null; }
        else{ return taskView; }
    }

}
