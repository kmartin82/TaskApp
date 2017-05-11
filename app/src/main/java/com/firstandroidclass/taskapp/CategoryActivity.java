package com.firstandroidclass.taskapp;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CategoryActivity extends SingleFragmentActivity {
    public static final String EXTRA_CATEGORY_ID = "com.firstandroidclass.taskapp.category_id";

    public static Intent newIntent(Context context, UUID contactID) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, contactID);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID categoryID = (UUID) getIntent().getSerializableExtra(EXTRA_CATEGORY_ID);
        return CategoryFragment.newInstance(categoryID);
    }
}
