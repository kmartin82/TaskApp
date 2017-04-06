package com.firstandroidclass.taskapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CheckBox;

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
    private static final String ARG_TASK_ID = "task_id";
    private Task mTask;
    private EditText mNameField;
    private EditText mDescriptionField;
    private CheckBox mCompletionCheckBox;
    private EditText mDueDateField;
    private EditText mLocationField;
    private MapView mMapView;

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
        UUID taskID = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskCollection.get().getTask(taskID);
    }

    public static TaskFragment newInstance(UUID taskID){
        TaskFragment taskFragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskID);
        taskFragment.setArguments(args);
        return taskFragment;
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
