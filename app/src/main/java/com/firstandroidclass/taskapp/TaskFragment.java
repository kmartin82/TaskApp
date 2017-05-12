package com.firstandroidclass.taskapp;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class TaskFragment extends Fragment {
    private Task mTask;
    private EditText mNameField;
    private EditText mDescriptionField;
    private CheckBox mCompletionCheckBox;
    private EditText mDueDateField;
    private EditText mLocationField;
    private MapView mMapView;
    private Callbacks mCallbacks;
    private Spinner mCategorySpinner;

    public interface Callbacks {
        void onTaskUpdated(Task task);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks)context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
    private static final String ARG_TASK_ID = "task_id";

    public static TaskFragment newInstance(UUID taskID) {
        TaskFragment taskFragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskID);
        taskFragment.setArguments(args);
        return taskFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID taskID = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskCollection.get(getContext()).getTask(taskID);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_task, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_item_delete_task:
                TaskCollection.get(getContext()).deleteTask(mTask);
                Intent intent = new Intent(getContext(), TaskCollectionActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        mNameField = (EditText) view.findViewById(R.id.task_name);
        mNameField.setText(mTask.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setName(s.toString());
                //TaskCollection.get(getContext()).updateTask(mTask);
                TaskCollection.get(getContext()).updateContact(mTask);
                mCallbacks.onTaskUpdated(mTask);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescriptionField = (EditText) view.findViewById(R.id.task_description);
        mDescriptionField.setText(mTask.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCompletionCheckBox = (CheckBox)view.findViewById(R.id.task_complete);
        mCompletionCheckBox.setChecked(mTask.isComplete());
        mCompletionCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTask.setComplete(isChecked);
            }
        });

        mDueDateField = (EditText) view.findViewById(R.id.task_date);
        mDueDateField.setText(mTask.getDueDate());
        mDueDateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setDueDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mLocationField = (EditText) view.findViewById(R.id.task_location);
        mLocationField.setText(mTask.getLocation());
        mLocationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setLocation(s.toString());
                //updateMap();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mCategorySpinner = (Spinner) view.findViewById(R.id.task_category);
        final CategoryCollection categoryCollection = CategoryCollection.get();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item);
        for (Category c: categoryCollection) {
            arrayAdapter.add(c.getName());
        }
        mCategorySpinner.setAdapter(arrayAdapter);
        mCategorySpinner.setSelection(categoryCollection.getIndex(mTask.getCategory()));
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTask.setCategory(categoryCollection.getCategoryByIndex(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mLocationField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    updateMap();
                }
            }
        });
        mMapView = (MapView)view.findViewById(R.id.task_map);
        mMapView.onCreate(savedInstanceState);
        updateMap();

        return view;
    }

    private void updateMap() {
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Geocoder geo = new Geocoder(getContext());
                try {
                    List<Address> addresses =
                            geo.getFromLocationName(mLocationField.getText().toString(), 3);
                    googleMap.clear();
                    for (Address address: addresses) {
                        LatLng latLng = new LatLng(address.getLatitude(),
                                address.getLongitude());
                        MarkerOptions marker = new MarkerOptions().position(latLng);
                        googleMap.addMarker(marker);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    }
                } catch (IOException e) {
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        TaskCollection.get(getContext()).updateContact(mTask);
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}