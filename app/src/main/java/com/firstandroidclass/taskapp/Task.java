package com.firstandroidclass.taskapp;

import android.util.Log;
import java.util.UUID;

public class Task {
    private static final String TAG = Task.class.getSimpleName();
    private UUID mID;
    private String mName;
    private String mDescription;
    private boolean mIsComplete;
    private String mDueDate;
    private String mLocation;

    public Task(){
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        Log.i(TAG, "New name: " + name);
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        Log.i(TAG, "New description: " + description);
        mDescription = description;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public void setComplete(boolean complete) {
        mIsComplete = complete;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String dueDate) {
        mDueDate = dueDate;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }
}
