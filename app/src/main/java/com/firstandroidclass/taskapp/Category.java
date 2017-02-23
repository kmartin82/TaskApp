package com.firstandroidclass.taskapp;

import java.util.UUID;

/**
 * Created by sarahmcculley on 2/22/17.
 */

enum Color {GREEN, YELLOW, RED}

public class Category {
    private UUID mID;
    private Color mColor;

    public Category() {
        mID = UUID.randomUUID();
    }

    public UUID getID() {
        return mID;
    }
}
