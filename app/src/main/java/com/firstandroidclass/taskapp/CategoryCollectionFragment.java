package com.firstandroidclass.taskapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class CategoryCollectionFragment extends Fragment {
    private RecyclerView mCategoryCollectionRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_collection, container, false);
        mCategoryCollectionRecyclerView = (RecyclerView) view.findViewById(R.id.category_collection_recycler_view);
        mCategoryCollectionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CategoryCollection categoryCollection = CategoryCollection.get();
        List<Category> categories = categoryCollection.getCategories();
        if (mCategoryAdapter == null) {
            mCategoryAdapter = new CategoryAdapter(categories);
            mCategoryCollectionRecyclerView.setAdapter(mCategoryAdapter);
        }
        else {
            mCategoryAdapter.notifyDataSetChanged();
        }
    }

    private class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCategoryNameTextView;
        private Category mCategory;

        public CategoryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mCategoryNameTextView = (TextView) itemView;
        }

        public void bindCategory(Category category) {
            mCategory = category;
            mCategoryNameTextView.setText(mCategory.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = CategoryPagerActivity.newIntent(getActivity(), mCategory.getID());
            startActivity(intent);
        }
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder>{
        private List<Category> mCategories;

        public CategoryAdapter(List<Category> categories){mCategories = categories;}

        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view =  layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new CategoryHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position) {
            Category category = mCategories.get(position);
            holder.bindCategory(category);
        }

        @Override
        public int getItemCount() {
            return mCategories.size();
        }
    }

}
