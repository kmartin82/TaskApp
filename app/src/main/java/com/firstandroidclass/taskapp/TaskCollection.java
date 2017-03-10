package com.firstandroidclass.taskapp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by smcculley on 3/9/2017.
 */

public class TaskCollection {

        private static TaskCollection mTaskCollection;
        private List<Task> mTasks;

    private TaskCollection(){
        mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            Task task = new Task();
            task.setName("Task "+ i);
            task.setDescription("Description " + i);
            task.setDueDate("Due Date " +i);
            task.setLocation("Location " + i);

            mTasks.add(task);
        }
    }

    //singleton pattern
    public static TaskCollection get(){
        if(mTaskCollection == null){
            mTaskCollection = new TaskCollection();
        }
        return mTaskCollection;
    }
}
