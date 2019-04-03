package com.chrisfetz.gogetter.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chrisfetz.gogetter.BackendExecutors;
import com.chrisfetz.gogetter.R;
import com.chrisfetz.gogetter.Repository;
import com.chrisfetz.gogetter.database.TodoDatabase;
import com.chrisfetz.gogetter.database.TodoTask;
import com.chrisfetz.gogetter.ui.viewmodel.MainViewModel;
import com.chrisfetz.gogetter.ui.viewmodel.MainViewModelFactory;
import com.chrisfetz.gogetter.utilities.Injector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ViewTaskActivity extends AppCompatActivity {

    private static final String TAG = ViewTaskActivity.class.getSimpleName();

    private TextView mTitle;
    private TextView mDescription;
    private TextView mCreatedAt;
    private TextView mUpdatedAt;

    private TodoDatabase mTdb;
    private static final int DEFAULT_TASK_ID = -1;
    private int TASK_ID = DEFAULT_TASK_ID;

    // Keys for TodoTask
    private static final String KEY_TODOTASK_ID = "todotask_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        mTitle = findViewById(R.id.tv_viewtask_title);
        mDescription = findViewById(R.id.tv_viewtask_description);
        mCreatedAt = findViewById(R.id.tv_viewtask_created_at);
        mUpdatedAt = findViewById(R.id.tv_viewtask_updated_at);

        mTdb = TodoDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(KEY_TODOTASK_ID)){
            TASK_ID = intent.getIntExtra(KEY_TODOTASK_ID, DEFAULT_TASK_ID);
            setupViewModel(TASK_ID);
        } else {
            Intent goToATA = new Intent(this, AddTaskActivity.class);
            startActivity(goToATA);
            Log.d(TAG, "No id found.");
        }

    }

    /**
     * Creates the ViewModel that supplies the TodoTask objects
     */
    private void setupViewModel(int id){
        final int taskId = id;
        Context context = getApplicationContext();
        Repository myRepo = Injector.provideRepository(context);
        MainViewModelFactory factory = Injector.provideMainViewModelFactory(getApplication(), context);

        final MainViewModel mViewModel = factory.create(new MainViewModel(getApplication(), myRepo));

        mViewModel.loadTaskById(id).observe(this, new Observer<TodoTask>() {
            @Override
            public void onChanged(@Nullable TodoTask task) {
                Log.d(TAG, "Loaded TodoTask from the database with id: " + taskId);
                Log.d(TAG, "Task title: " + task.getTitle());
                Log.d(TAG, "Task description: " + task.getDescription());
                Log.d(TAG, "Task createdAt: " + task.getCreatedAt().toString());
                Log.d(TAG, "Task updatedAt: " + task.getUpdatedAt().toString());


                mTitle.setText(task.getTitle());
                mDescription.setText(task.getDescription());
                mCreatedAt.setText(getApplicationContext().getString(R.string.text_created_at,
                        task.getCreatedAt().toString()));

                //Possible colors of tasks
                int blue = ContextCompat.getColor(ViewTaskActivity.this, R.color.color_task_blue);
                int red = ContextCompat.getColor(ViewTaskActivity.this, R.color.color_task_red);
                int yellow = ContextCompat.getColor(ViewTaskActivity.this, R.color.color_task_yellow);
                int green = ContextCompat.getColor(ViewTaskActivity.this, R.color.color_task_green);

                int color = task.getColor();
                switch (color) {
                    case 0: mTitle.setBackgroundColor(blue);
                        break;
                    case 1: mTitle.setBackgroundColor(red);
                        break;
                    case 2: mTitle.setBackgroundColor(yellow);
                        break;
                    case 3: mTitle.setBackgroundColor(green);
                        break;
                    default: mTitle.setBackgroundColor(blue);
                }
            }
        });
    }
}
