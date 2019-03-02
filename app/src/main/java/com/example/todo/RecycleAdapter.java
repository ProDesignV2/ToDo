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

    public RecycleAdapter(Context mContext, List<Tasks> mTasks) {
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

    public void swapArrayList(List<Tasks> newList){
        mTasks = newList;
        notifyDataSetChanged();
    }
}
