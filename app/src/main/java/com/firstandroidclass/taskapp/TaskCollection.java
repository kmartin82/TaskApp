package com.firstandroidclass.taskapp;

/*
 * Created by Rick on 3/12/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.firstandroidclass.taskapp.database.TaskBaseHelper;
import com.firstandroidclass.taskapp.database.TaskCursorWrapper;
import com.firstandroidclass.taskapp.database.TaskDbSchema;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskCollection {
    private static TaskCollection sTaskCollection;
    private Integer countComplete;
    private Integer countRemaining;
    private SQLiteDatabase mDatabase;

    public Integer getCountRemaining() {
        return getTasks().size();
    }

    public Integer getCountComplete() {
        return getCompletedTasks().size();
    }

    private TaskCollection(Context context) {
        mDatabase = new TaskBaseHelper(context).getWritableDatabase();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");


        Task task1 = new Task();
        task1.setName("New tires");
        task1.setDescription("Michelin XLT 225/75R15");
        //task1.setDueDate("05/04/2017");
        task1.setLocation("NTB Mill Run");
        task1.setComplete(false);
        //task1.setCategory("");


        Task task2 = new Task();
        task2.setName("File taxes");
        task2.setDescription("Fed State Local");
        //task2.setDueDate("4/15/2017");
        task2.setLocation("HR Block");
        task2.setComplete(true);
        //task2.setCategory("");


        Task task3 = new Task();
        task3.setName("Lawn treatment");
        task3.setDescription("Pre-emergent weeds");
        //task3.setDueDate("4/30/2017");
        task3.setLocation("Lowes Garden");
        task3.setComplete(false);
        //task3.setCategory("");


        Task task4 = new Task();
        task4.setName("De-winterize RV");
        task4.setDescription("Drain and fill fresh tanks");
        //task4.setDueDate("4/1/2017");
        task4.setLocation("NTB Milil Run");
        task4.setComplete(true);
        //task4.setCategory("");


        Task task5 = new Task();
        task5.setName("Deck boards");
        task5.setDescription("2x6x10 cut and place");
        //task5.setDueDate("5/18/2017");
        task5.setLocation("Linworth Lumber");
        task5.setComplete(false);
        //task5.setCategory("");


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
        ContentValues values = getContentValues(task);
        mDatabase.insert(TaskDbSchema.TaskTable.NAME, null, values);

    }
    public void deleteTask(Task task) {
        String uuidString = task.getID().toString();
        mDatabase.delete(TaskDbSchema.TaskTable.NAME,
                TaskDbSchema.TaskTable.Cols.UUID  + " = ?",
                new String[] { uuidString });
    }

    public void updateContact(Task task) {
        String uuidString = task.getID().toString();
        ContentValues values = getContentValues(task);
        mDatabase.update(TaskDbSchema.TaskTable.NAME, values,
                TaskDbSchema.TaskTable.Cols.UUID  + " = ?",
                new String[] { uuidString });
    }

    public static TaskCollection get(Context context) {
        if (sTaskCollection == null) {
            sTaskCollection = new TaskCollection(context);
        }
        return sTaskCollection;
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        // get all contacts
        TaskCursorWrapper cursorWrapper = queryTasks(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                tasks.add(cursorWrapper.getTask());
                cursorWrapper.moveToNext();
            }
        }
        finally {
            cursorWrapper.close();
        }
        return tasks;

    }

    public List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();
        // get only contacts with completed == 1

        TaskCursorWrapper cursorWrapper = queryTasks(
                TaskDbSchema.TaskTable.Cols.COMPLETE + " = ?",
                new String[] { "true" }
        );
        try {
            if (cursorWrapper.getCount() == 0) {
                return completed;
            }

            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                completed.add(cursorWrapper.getTask());
                cursorWrapper.moveToNext();
            }
        }
        finally {
            cursorWrapper.close();
        }
        return completed;
    }

    public Task getTask(UUID id) {
        // get only contacts with matching UUID
        TaskCursorWrapper cursorWrapper = queryTasks(
                TaskDbSchema.TaskTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getTask();
        }
        finally {
            cursorWrapper.close();
        }
    }

    private static ContentValues getContentValues(Task task) {
        // convert image to a byte array for storage
        //byte[] imageData = {};
        //if (task.getImage() != null) {
           // ByteArrayOutputStream stream = new ByteArrayOutputStream();
           // task.getImage().compress(Bitmap.CompressFormat.PNG, 0, stream);
            //imageData = stream.toByteArray();
        //}

        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.TaskTable.Cols.UUID, task.getID().toString());
        values.put(TaskDbSchema.TaskTable.Cols.NAME, task.getName());
        values.put(TaskDbSchema.TaskTable.Cols.DESCRIPTION, task.getDescription());
        values.put(TaskDbSchema.TaskTable.Cols.COMPLETE, task.isComplete() ? "true" : "false");
        values.put(TaskDbSchema.TaskTable.Cols.LOCATION, task.getLocation());
        if (task.getCategory() != null) {
            values.put(TaskDbSchema.TaskTable.Cols.CATEGORY, task.getCategory().getName());
        }
        return values;
    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(TaskDbSchema.TaskTable.NAME, null, whereClause, whereArgs,
                null, null, null, null);
        return new TaskCursorWrapper(cursor);
    }
}
