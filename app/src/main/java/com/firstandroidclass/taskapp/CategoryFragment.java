package com.firstandroidclass.taskapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.UUID;


public class CategoryFragment extends Fragment {
    private static final String ARG_CATEGORY_ID = "category_id";
    private Category mCategory;
    private EditText mNameField;

    public static CategoryFragment newInstance(UUID taskID) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CATEGORY_ID, taskID);
        categoryFragment.setArguments(args);
        return categoryFragment;
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID taskID = (UUID) getArguments().getSerializable(ARG_CATEGORY_ID);
        mCategory = CategoryCollection.get().getCategory(taskID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        mNameField = (EditText) view.findViewById(R.id.category_name);
        mNameField.setText(mCategory.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCategory.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }
}
