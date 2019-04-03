package com.chrisfetz.gogetter.ui.viewmodel;


import android.app.Application;
import android.util.Log;

import com.chrisfetz.gogetter.Repository;
import com.chrisfetz.gogetter.database.TodoTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The ViewModel that passes data from the Repository to the Activity.
 */
public class MainViewModel extends AndroidViewModel {

    private static String TAG = MainViewModel.class.getSimpleName();
    private Repository mRepo;

    public MainViewModel(@NonNull Application application, Repository repo){
        super(application);
        mRepo = repo;
        Log.d(TAG, "Creating new MainViewModel using MainViewModel constructor.");
    }

    /**
     *
     * @return The list of tasks from the repo
     */
    public LiveData<List<TodoTask>> getTasks() {
        Log.d(TAG, "Getting tasks from Repository using MainViewModel.");
        return mRepo.getTasks();
    }

    /**
     *
     * @param id The id of the TodoTask in question
     * @return The TodoTask with the given id
     */
    public LiveData<TodoTask> loadTaskById(int id){
        return mRepo.loadTaskById(id);
    }

    /**
     * Deletes the given TodoTask
     * @param todoTask
     */
    public void deleteTask(TodoTask todoTask){
        mRepo.deleteTask(todoTask);
    }
}