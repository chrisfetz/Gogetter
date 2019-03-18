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

    // Keys for TodoTask
    private static final String KEY_TODOTASK_ID = "todotask_id";

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

    /**
     * When a task in the RecyclerView is clicked, open a ViewTaskActivity for that task.
     * @param view The view within the AdapterView that was clicked (this will be a view provided by the adapter)
     * @param position The index in the TodoAdapter of the item clicked.
     */
    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "Recyclerview item clicked.");

        TodoTask todoTask = mAdapter.getContents().get(position);

        Intent intent = new Intent(MainActivity.this, ViewTaskActivity.class);
        intent.putExtra(KEY_TODOTASK_ID, todoTask.getId());
        startActivity(intent);
    }

    /**
     * Creates the ViewModel that supplies the TodoTask objects
     */
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
