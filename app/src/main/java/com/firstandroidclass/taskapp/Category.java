package com.firstandroidclass.taskapp;

import java.util.UUID;


public class Category {
    private UUID mID;
    private String mName;

    public Category(String name) {
        mID = UUID.randomUUID();
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
  
    public UUID getID() {
        return mID;
    }
}
