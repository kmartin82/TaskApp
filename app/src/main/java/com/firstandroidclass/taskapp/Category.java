package com.firstandroidclass.taskapp;

enum Color {GREEN, YELLOW, RED}

public class Category {
    private UUID mID;
    private Color mColor;
    private String mName;

    public Category() {
        mID = UUID.randomUUID();
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
