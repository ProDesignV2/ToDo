package com.example.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.todo.models.Tasks;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.SimpleViewHolder> {

    private Context mContext;
    private List<Tasks> mTasks;

    RecycleAdapter(Context mContext, List<Tasks> mTasks) {
        this.mContext = mContext;
        this.mTasks = mTasks;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_row,viewGroup,false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int i) {
        // Set items in task row
        simpleViewHolder.nameText.setText(mTasks.get(i).getName());
        simpleViewHolder.dateText.setText(String.valueOf(mTasks.get(i).dateUntil()));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder{

        TextView nameText;
        TextView dateText;
        CheckBox checked;
//        public LinearLayout taskRow;

        SimpleViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameView);
            dateText = itemView.findViewById(R.id.dateView);
            checked = itemView.findViewById(R.id.taskChecked);
//            taskRow = itemView.findViewById(R.id.taskRowLayout);
        }
    }

    void addedItem(){
        // Load last task from database
        List<Tasks> task = Tasks.findWithQuery(Tasks.class,"SELECT * FROM TASKS ORDER BY ID DESC LIMIT ?","1");
        // Could add to specific location using other add function
        mTasks.add(task.get(0));
        notifyItemInserted(mTasks.size() - 1);
    }
}
