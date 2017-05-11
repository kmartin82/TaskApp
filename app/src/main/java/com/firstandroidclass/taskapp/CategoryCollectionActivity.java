package com.firstandroidclass.taskapp;


import android.support.v4.app.Fragment;

public class CategoryCollectionActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CategoryCollectionFragment();
    }
}
