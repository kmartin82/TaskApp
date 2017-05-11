package com.firstandroidclass.taskapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.List;
import java.util.UUID;


public class TaskPagerActivity extends AppCompatActivity implements TaskFragment.Callbacks{
    private static final String EXTRA_TASK_ID = "com.com.firstandroidclass.taskapp.contact_id";
    private ViewPager mViewPager;
    private List<Task> mTasks;

    public static Intent newIntent(Context packageContext, UUID contactID) {
        Intent intent = new Intent(packageContext, TaskPagerActivity.class);
        intent.putExtra(EXTRA_TASK_ID, contactID);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);

        mTasks = TaskCollection.get(this).getTasks();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(
                R.id.activity_task_pager_view_pager);

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Task task = mTasks.get(position);
                return TaskFragment.newInstance(task.getID()) ;
            }

            @Override
            public int getCount() {
                return mTasks.size();
            }
        });

        UUID taskID = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        for (int index = 0; index < mTasks.size(); index++) {
            if (mTasks.get(index).getID().equals(taskID)) {
                mViewPager.setCurrentItem(index);
                break;
            }
        }
    }

    @Override
    public void onTaskUpdated(Task task) {
    }
}
