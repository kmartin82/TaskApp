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
            task.setDueDate("Due Date " + i);
            task.setLocation("Location " + i);
            if (i % 10 == 0){
                task.setComplete(true);
            }

            List<Category> allCategories =  CategoryCollection.get().getCategories();
            Category category = allCategories.get(i % allCategories.size());
            task.setCategory(category);

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

    public List<Task> getTasks(){
        return mTasks;
    }

    public Task getTask(UUID id){
        for (Task task: mTasks){
            if(task.getID().equals(id)){
                return task;
            }
        }
        return null;
    }
}
