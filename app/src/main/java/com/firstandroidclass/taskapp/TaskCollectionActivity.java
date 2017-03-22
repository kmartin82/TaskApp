package com.firstandroidclass.taskapp;

import android.support.v4.app.Fragment;

/**
 * Created by sarahmcculley on 3/21/17.
 */

public class TaskCollectionActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TaskCollectionFragment();
    }
}
