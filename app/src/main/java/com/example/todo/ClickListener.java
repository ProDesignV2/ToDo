package com.example.todo;

public interface ClickListener {

    void onCheckBoxClicked(int position, boolean isChecked);
    void onLongClicked(int position);

}
