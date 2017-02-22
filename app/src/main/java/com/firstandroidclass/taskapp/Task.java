package com.firstandroidclass.taskapp;


import android.util.Log;

import java.util.UUID;


public class Task {
    private static final String TAG = Task.class.getSimpleName();
    private UUID mID;
    private String mName;
    private String mDescription;

    public Task(){
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setName(String name) {
        Log.i(TAG, "new name: " + name);
        mName = name;
    }

    public void setDescription(String description) {
        Log.i(TAG, "new description: " + description);
        mDescription = description;
    }
}
