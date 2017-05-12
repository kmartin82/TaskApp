package com.firstandroidclass.taskapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.firstandroidclass.taskapp.Category;
import com.firstandroidclass.taskapp.CategoryCollection;
import com.firstandroidclass.taskapp.Task;

import java.util.UUID;

/**
 * Created by sarahmcculley on 5/10/17.
 */

public class TaskCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        String uuid = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.UUID));
        String name = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.NAME));
        String email = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.DESCRIPTION));
        String complete = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.COMPLETE));
        String location = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.LOCATION));
        String categoryName = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.CATEGORY));

        CategoryCollection categoryCollection = CategoryCollection.get();
        Category category = categoryCollection.getCategory(categoryName);
        if (category == null) {

            category = categoryCollection.add(categoryName);
        }
        Task task = new Task(UUID.fromString(uuid));
        task.setName(name);
        task.setDescription(email);
        task.setComplete(complete.equals("true"));
        task.setLocation(location);
        task.setCategory(category);

        return task;
    }
}
