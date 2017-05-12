package com.firstandroidclass.taskapp;
/*
 * Created by Rick on 4/2/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;

public class TaskCollectionActivity extends SingleFragmentActivity
        implements TaskCollectionFragment.Callbacks, TaskFragment.Callbacks{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_listdetail;
    }

    @Override
    public void onTaskUpdated(Task task) {
        TaskCollectionFragment taskCollectionFragment = (TaskCollectionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        taskCollectionFragment.updateUI();
    }

    @Override
    protected Fragment createFragment() {
        return new TaskCollectionFragment();
    }

    @Override
    public void onTaskSelected(Task task) {
        // when viewed on a phone
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = TaskPagerActivity.newIntent(this, task.getID());
            startActivity(intent);
        }
        // when viewed on a tablet
        else {
            Fragment newDetail = TaskFragment.newInstance(task.getID());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}