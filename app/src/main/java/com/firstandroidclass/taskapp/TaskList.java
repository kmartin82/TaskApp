package com.firstandroidclass.taskapp;

/*
 * Created by Rick on 3/12/2017.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskList {
    private static TaskList sTaskList;
    private List<Task> mTasks;
    private Integer countComplete;
    private Integer countRemaining;

    public Integer getCountRemaining() {
        countRemaining = mTasks.size() - getCountComplete();
        return countRemaining;
    }

    public Integer getCountComplete() {
        countComplete = 0;
        for (Task task: mTasks) {
            if (task.isComplete()) {
                countComplete++;
            }
        }
        return countComplete;
    }

    private TaskList() {
        mTasks = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");


        Task task1 = new Task();
        task1.setName("New tires");
        task1.setDescription("Michelin XLT 225/75R15");
        //task1.setDueDate("05/04/2017");
        task1.setLocation("NTB Mill Run");
        task1.setComplete(false);
        //task1.setCategory("");
        mTasks.add(task1);

        Task task2 = new Task();
        task2.setName("File taxes");
        task2.setDescription("Fed State Local");
        //task2.setDueDate("4/15/2017");
        task2.setLocation("HR Block");
        task2.setComplete(true);
        //task2.setCategory("");
        mTasks.add(task2);

        Task task3 = new Task();
        task3.setName("Lawn treatment");
        task3.setDescription("Pre-emergent weeds");
        //task3.setDueDate("4/30/2017");
        task3.setLocation("Lowes Garden");
        task3.setComplete(false);
        //task3.setCategory("");
        mTasks.add(task3);

        Task task4 = new Task();
        task4.setName("De-winterize RV");
        task4.setDescription("Drain and fill fresh tanks");
        //task4.setDueDate("4/1/2017");
        task4.setLocation("NTB Milil Run");
        task4.setComplete(true);
        //task4.setCategory("");
        mTasks.add(task4);

        Task task5 = new Task();
        task5.setName("Deck boards");
        task5.setDescription("2x6x10 cut and place");
        //task5.setDueDate("5/18/2017");
        task5.setLocation("Linworth Lumber");
        task5.setComplete(false);
        //task5.setCategory("");
        mTasks.add(task5);

//        for (int i=0; i<6; i++) {
//            Task task = new Task();
//            task.setName("Task " + i);
//            //task.setEmail("Person" + i + "@email.com");
//
//            // set every 3rd as a completed
//            if (i % 3 == 0) {
//                task.setComplete(true);
//            }
//
//            mTasks.add(task);
//        }
    }

    public void add(Task task) {
        mTasks.add(task);
    }

    public static TaskList get() {
        if (sTaskList == null) {
            sTaskList = new TaskList();
        }
        return sTaskList;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();
        for (Task task: mTasks) {
            if (task.isComplete()) {
                completed.add(task);
            }
        }
        return completed;
    }

    public Task getTask(UUID id) {
        for (Task task: mTasks) {
            if (task.getID().equals(id)) {
                return task;
            }
        }
        return null;
    }
}
