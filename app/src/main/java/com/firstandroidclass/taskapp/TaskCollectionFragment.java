package com.firstandroidclass.taskapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
    public class TaskCollectionFragment extends Fragment {
        private RecyclerView mTaskListRecyclerView;
        private TaskAdapter mTaskAdapter;
        private boolean mShowFavoritesOnly = false;
        private TextView mTextViewComplete;
        private TextView mTextViewRemaining;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.fragment_task_collection, menu);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_task_collection, container,
                    false);
            mTaskListRecyclerView =
                    (RecyclerView) view.findViewById(R.id.task_list_recycler_view);
            mTaskListRecyclerView.setLayoutManager(
                    new LinearLayoutManager(getActivity()));
            mTextViewComplete = (TextView) view.findViewById(R.id.tasks_complete_count);
            mTextViewRemaining = (TextView) view.findViewById(R.id.tasks_remaining_count);
             updateUI();
            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            updateUI();
        }

        private void updateUI() {
            TaskCollection taskCollection = TaskCollection.get();
            List<Task> tasks = taskCollection.getTasks();
            if (mTaskAdapter == null) {
                mTaskAdapter = new TaskAdapter(tasks);
                mTaskListRecyclerView.setAdapter(mTaskAdapter);
            } else {
                mTaskAdapter.notifyDataSetChanged();
            }
            Integer completeCount = taskCollection.getCountComplete();
            mTextViewComplete.setText(completeCount.toString());
            Integer remainingCount = taskCollection.getCountRemaining();
            mTextViewRemaining.setText(remainingCount.toString());
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_item_create_task:
                    Task task = new Task();
                    TaskCollection.get().add(task);
                    Intent intent = TaskPagerActivity.newIntent(getActivity(), task.getID());
                    startActivity(intent);
                    return true;
                case R.id.menu_item_toggle_complete:
                    mShowFavoritesOnly = !mShowFavoritesOnly;
                    if (mShowFavoritesOnly) {
                        item.setTitle(R.string.show_all);
                        mTaskAdapter.mTasks = TaskCollection.get().getCompletedTasks();
                    } else {
                        item.setTitle(R.string.show_complete);
                        mTaskAdapter.mTasks = TaskCollection.get().getTasks();
                    }
                    mTaskAdapter.notifyDataSetChanged();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        private class TaskHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {
            private TextView mTaskNameTextView;
            private Task mTask;

            public TaskHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                mTaskNameTextView = (TextView) itemView;
            }

            public void bindContact(Task task) {
                mTask = task;
                mTaskNameTextView.setText(task.getName());
            }

            @Override
            public void onClick(View v) {
                Intent intent = TaskPagerActivity.newIntent(getActivity(), mTask.getID());
                startActivity(intent);
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
                holder.bindContact(task);
            }

            @Override
            public int getItemCount() {
                return mTasks.size();
            }
        }
    }