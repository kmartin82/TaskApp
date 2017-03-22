package com.firstandroidclass.taskapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

public class TaskActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new TaskFragment();
    }

}
