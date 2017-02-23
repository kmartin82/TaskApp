package com.firstandroidclass.taskapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by sarahmcculley on 2/23/17.
 */

public class TaskFragment extends Fragment {
    private Task mTask;
    private EditText mNameField;
    private EditText mDescriptionField;
    private EditText mCompletionField;
    private EditText mDueDateField;
    private EditText mLocationField;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTask = new Task();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        mNameField = (EditText) view.findViewById(R.id.task_name);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescriptionField = (EditText) view.findViewById(R.id.task_description);
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;


    }
}
