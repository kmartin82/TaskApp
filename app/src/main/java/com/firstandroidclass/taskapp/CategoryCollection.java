package com.firstandroidclass.taskapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by sarahmcculley on 3/13/17.
 */

public class CategoryCollection implements Iterable<Category> {

    private static CategoryCollection mCategoryCollection;
    private List<Category> mCategories = new ArrayList<>();

    private CategoryCollection(){
        mCategories.add(new Category("Work"));
        mCategories.add(new Category("Personal"));
        mCategories.add(new Category("School"));
        mCategories.add(new Category("Other"));
    }

    //singleton pattern
    public static CategoryCollection get(){
        if(mCategoryCollection == null){
            mCategoryCollection = new CategoryCollection();
        }
        return mCategoryCollection;
    }

    public List<Category> getCategories(){
        return mCategories;
    }

    public Category getCategory(UUID id){
        for (Category category: mCategories){
            if(category.getID().equals(id)){
                return category;
            }
        }
        return null;
    }

    public int getIndex(Category category) {
        return mCategories.indexOf(category);
    }

    public Category getCategoryByIndex(int index) {
        return mCategories.get(index);
    }

        @Override
        public Iterator<Category> iterator() {
            return mCategories.iterator();
        }

}
