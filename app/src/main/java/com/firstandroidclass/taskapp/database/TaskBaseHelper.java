package com.firstandroidclass.taskapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sarahmcculley on 5/9/17.
 */

public class TaskBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "TaskBase.db";

    public TaskBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TaskDbSchema.TaskTable.NAME + "( " +
                "_id integer primary key autoincrement, " +
                TaskDbSchema.TaskTable.Cols.UUID + ", " +
                TaskDbSchema.TaskTable.Cols.NAME + ", " +
                TaskDbSchema.TaskTable.Cols.DESCRIPTION + ", " +
                TaskDbSchema.TaskTable.Cols.COMPLETE + ", " +
                TaskDbSchema.TaskTable.Cols.DUE_DATE + ", " +
                TaskDbSchema.TaskTable.Cols.LOCATION + ", " +
                TaskDbSchema.TaskTable.Cols.CATEGORY +
                ")";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
