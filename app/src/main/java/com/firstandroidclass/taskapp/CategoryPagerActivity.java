package com.firstandroidclass.taskapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.List;
import java.util.UUID;



public class CategoryPagerActivity extends AppCompatActivity {
    private static final String EXTRA_CATEGORY_ID = "com.com.firstandroidclass.taskapp.category_id";
    private ViewPager mViewPager;
    private List<Category> mCategories;

    public static Intent newIntent(Context packageContext, UUID contactID) {
        Intent intent = new Intent(packageContext, CategoryPagerActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID , contactID);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_pager);

        mCategories = CategoryCollection.get().getCategories();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(
                R.id.activity_category_pager_view_pager);

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Category category = mCategories.get(position);
                return CategoryFragment.newInstance(category.getID()) ;
            }

            @Override
            public int getCount() {
                return mCategories.size();
            }
        });

        UUID categoryID = (UUID) getIntent().getSerializableExtra(EXTRA_CATEGORY_ID);
        for (int index = 0; index < mCategories.size(); index++) {
            if (mCategories.get(index).getID().equals(categoryID)) {
                mViewPager.setCurrentItem(index);
                break;
            }
        }
    }


}
