package com.example.gogetter;


import android.app.Application;

import com.example.gogetter.database.TodoDatabase;
import com.example.gogetter.database.TodoTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The ViewModel that passes data from the Repository to the Activity.
 */
public class MainViewModel extends AndroidViewModel {

    private LiveData<List<TodoTask>> tasks;
    private TodoDatabase mTdb;


    public MainViewModel(@NonNull Application application, TodoDatabase database){
        super(application);
        mTdb = database;
        tasks = mTdb.todoDao().loadAllTasks();
    }

    public LiveData<List<TodoTask>> getTasks() {
        return tasks;
    }
}
