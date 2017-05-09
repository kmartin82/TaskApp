package com.firstandroidclass.taskapp;

/*
 * Created by Rick on 4/2/2017.
 */

import android.support.v4.app.Fragment;

public class TaskCollectionActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TaskCollectionFragment();
    }
}