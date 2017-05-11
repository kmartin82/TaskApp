package com.firstandroidclass.taskapp.database;

/**
 * Created by sarahmcculley on 5/9/17.
 */

public class TaskDbSchema {
    public static final class TaskTable {
        public static final String NAME = "tasks";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String COMPLETE = "complete";
            public static final String DUE_DATE = "due_date";
            public static final String LOCATION = "location";
            public static final String CATEGORY = "category";
        }
    }
}
