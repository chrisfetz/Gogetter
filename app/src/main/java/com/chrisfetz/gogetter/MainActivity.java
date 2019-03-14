package com.chrisfetz.gogetter;

import androidx.lifecycle.Observer;
import android.content.Intent;
import androidx.annotation.Nullable;

import com.chrisfetz.gogetter.database.TodoDatabase;
import com.chrisfetz.gogetter.database.TodoTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements TodoAdapter.ItemClickListener {

    // Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();

    //Member variables
    private RecyclerView mRecyclerView;
    private TodoAdapter mAdapter;
    private FloatingActionButton mFab;
    private TodoDatabase mTdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting onclick for Floating action button
        mFab = findViewById(R.id.fab_main);

        mFab.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                Log.d(TAG, "Floating action Button pressed.");
                Intent goToAddTask = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTask);
            }
        });
        
        // set up the RecyclerView
        mRecyclerView = findViewById(R.id.rvTasks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TodoAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        mTdb = TodoDatabase.getInstance(getApplicationContext());
        setupViewModel();
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "Recyclerview item clicked.");
        //Todo: add onItemClick
    }

    private void setupViewModel(){
        MainViewModelFactory factory = new MainViewModelFactory(getApplication(), mTdb);
        final MainViewModel mViewModel = ViewModelProviders.of(this, factory)
                .get(MainViewModel.class);
        mViewModel.getTasks().observe(this, new Observer<List<TodoTask>>() {
            @Override
            public void onChanged(@Nullable List<TodoTask> tasks) {
                Log.d(TAG, "List of items set/updated in the ViewModel!");
                mAdapter.setContents(tasks);
            }
        });
    }


}
