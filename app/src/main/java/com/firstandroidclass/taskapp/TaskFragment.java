package com.firstandroidclass.taskapp;

/*
 * Created by Rick on 2/14/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TaskFragment extends Fragment{
    private Task mTask;
    private EditText mNameField;
    private EditText mDescriptionField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTask = new Task();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

        mNameField = (EditText) v.findViewById(R.id.task_name);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //No new behavior
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                mTask.setTaskName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                //No new behavior
            }
        });

        mDescriptionField = (EditText) v.findViewById(R.id.task_description);
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //No new behavior
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                mTask.setTaskName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                //No new behavior
            }
        });

        return v;

    }
}
