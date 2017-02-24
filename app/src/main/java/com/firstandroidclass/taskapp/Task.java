package com.firstandroidclass.taskapp;

/*
 * Created by Rick on 2/13/2017.
 */

import java.util.UUID;
import java.util.Date;

import android.text.method.DateTimeKeyListener;
import android.util.Log;

import java.util.UUID;

public class Task {
    private static final String TAG = Task.class.getSimpleName();
    private UUID mID;
    private String mTaskName;
    private String mTaskDesc;
    private Boolean mTaskComplete;
    private Date mDueDate;
    private String mLocation;
    private String mCategory;

    public Task() {
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
        Log.i(TAG, "New name: " + taskName);
    }

    public String getTaskDesc() {
        return mTaskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        mTaskDesc = taskDesc;
        Log.i(TAG, "New description: " + taskDesc);
    }

    public Boolean getTaskComplete() {
        return mTaskComplete;
    }

    public void setTaskComplete(Boolean taskComplete) {
        mTaskComplete = taskComplete;
        Log.i(TAG, "New complete: " + mTaskComplete);
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
        Log.i(TAG, "New duedate: " + mDueDate);
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
        Log.i(TAG, "New location: " + mLocation);
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
        Log.i(TAG, "New category: " + mCategory);
    }
}
