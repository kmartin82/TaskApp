package com.firstandroidclass.taskapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sarahmcculley on 3/21/17.
 */

public class TaskCollectionFragment extends Fragment {
    private RecyclerView mTaskCollectionRecyclerView;
    private TaskAdapter mTaskAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_task_collection, container,
                false);

        mTaskCollectionRecyclerView =
                (RecyclerView) view.findViewById(R.id.task_collection_recycler_view);
        mTaskCollectionRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));

        updateUI();
         return view;
    }

    private void updateUI(){
        TaskCollection taskCollection = TaskCollection.get();
        List<Task> tasks = taskCollection.getTasks();
        mTaskAdapter = new TaskAdapter(tasks);
        mTaskCollectionRecyclerView.setAdapter(mTaskAdapter);
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        public TextView mTaskNameTextView;

        public TaskHolder(View itemView) {
            super(itemView);
            mTaskNameTextView = (TextView) itemView;
        }

    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(
                    android.R.layout.simple_list_item_1, parent, false);

            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.mTaskNameTextView.setText(task.getName());
        }
        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }


}
