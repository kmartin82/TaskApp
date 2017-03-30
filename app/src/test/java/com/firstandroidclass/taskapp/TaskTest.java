package com.firstandroidclass.taskapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kmartin82 on 3/30/2017.
 */

public class TaskTest {
    @Test
    public void TaskNameTest(){
        // need to comment out log messages before running test
        String name = "testname";
        Task task = new Task();
        task.setName(name);
        assertEquals(name, task.getName());
    }

    @Test
    public void TaskDescriptionTest(){
        // need to comment out log messages before running test
        String description = "test description";
        Task task = new Task();
        task.setDescription(description);
        assertEquals(description, task.getDescription());
    }

    @Test
    public void UUIDtest(){
        Task task = new Task();
        assertNotNull(task.getID());
    }
}
