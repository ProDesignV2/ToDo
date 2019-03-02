package com.example.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todo.models.Tasks;

import java.lang.ref.WeakReference;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.SimpleViewHolder> {

    private Context mContext;
    private List<Tasks> mTasks;
    private ClickListener mListener;

    RecycleAdapter(Context mContext, List<Tasks> mTasks, ClickListener mListener) {
        this.mContext = mContext;
        this.mTasks = mTasks;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_row,viewGroup,false);
        return new SimpleViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int i) {
        // Set items in task row
//        simpleViewHolder.nameText.setText(mTasks.get(i).getName());
        simpleViewHolder.checked.setText(mTasks.get(i).getName());
        simpleViewHolder.dateText.setText(String.valueOf(mTasks.get(i).dateUntil()));
        simpleViewHolder.checked.setChecked(mTasks.get(i).getChecked());
        // Set opacity of layout based on check status
        if(mTasks.get(i).getChecked()){ simpleViewHolder.taskRow.setAlpha((float)0.25); }
        else{ simpleViewHolder.taskRow.setAlpha(1); }
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, View.OnLongClickListener {

//        TextView nameText;
        TextView dateText;
        CheckBox checked;
        LinearLayout taskRow;
        WeakReference<ClickListener> listenerRef;

        SimpleViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);

//            nameText = itemView.findViewById(R.id.nameView);
            dateText = itemView.findViewById(R.id.dateView);
            checked = itemView.findViewById(R.id.taskChecked);
            taskRow = itemView.findViewById(R.id.taskRowLayout);
            listenerRef = new WeakReference<>(listener);

            // Set listeners
            taskRow.setOnLongClickListener(this);
            checked.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Change transparency of current task
            if(isChecked){ taskRow.setAlpha((float)0.25); } else { taskRow.setAlpha(1); }
            listenerRef.get().onCheckBoxClicked(getAdapterPosition(),isChecked);
        }

        @Override
        public boolean onLongClick(View v) {
            // Callback function defined in Main Activity
            listenerRef.get().onLongClicked(getAdapterPosition());
            return true;
        }
    }

    void addedItem(){
        // Load last task from database
        List<Tasks> task = Tasks.findWithQuery(Tasks.class,"SELECT * FROM TASKS ORDER BY ID DESC LIMIT ?","1");
        // Could add to specific location using other add function
        mTasks.add(task.get(0));
        notifyItemInserted(mTasks.size() - 1);
    }

    void deletedItem(int position){
        // Handle deletion of item
        Tasks task = Tasks.findById(Tasks.class, mTasks.get(position).getSugarID());
        mTasks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mTasks.size());
        task.delete();
    }

    void checkedItem(int position, boolean isChecked){
        // Handle checking of task item
        if(mTasks.get(position).getChecked() != isChecked) {
            mTasks.get(position).setChecked(isChecked);
            // Need to update database BUT do all at once before closing app
            Tasks task = Tasks.findById(Tasks.class, mTasks.get(position).getSugarID());
            task.setChecked(isChecked);
            task.save();
        }
    }
}
