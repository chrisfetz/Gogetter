package com.chrisfetz.gogetter;

import android.content.Context;
import android.util.Log;

import com.chrisfetz.gogetter.database.TodoDao;
import com.chrisfetz.gogetter.database.TodoDatabase;
import com.chrisfetz.gogetter.database.TodoTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {

    private static Repository instance;
    private static String TAG = Repository.class.getSimpleName();

    private TodoDatabase mTdb;
    private static LiveData<List<TodoTask>> tasks;
    private static TodoDao mTodoDao;
    private static BackendExecutors mBackendExecutors;

    private Repository(TodoDao todoDao, BackendExecutors backendExecutors){
        mTodoDao = todoDao;
        mBackendExecutors = backendExecutors;
        tasks = todoDao.loadAllTasks();
    }

    /**
     * Creates an instance of Repository if one doesn't exist already.
     * @return An instance of Repository
     */
    public static Repository getInstance(TodoDao todoDao, BackendExecutors backendExecutors) {
        if (instance == null){
            synchronized (Repository.class) {
                if (instance == null) {
                    instance = new Repository(todoDao, backendExecutors);
                    Log.d(TAG, "Creating a new instance of Repository.");
                }
            }
        }
        Log.d(TAG, "Returning instance of Repository.");
        return instance;
    }

    /**
     *
     * @return Every TodoTask that exists in the database.
     */
    public LiveData<List<TodoTask>> getTasks() {
        return tasks;
    }

    /**
     *
     * @param id The id of the TodoTask in question
     * @return The TodoTask with the given id
     */
    public LiveData<TodoTask> loadTaskById(int id){
        return mTodoDao.loadTaskById(id);
    }

    /**
     * Deletes the given TodoTask
     * @param todoTask
     */
    public void deleteTask(TodoTask todoTask){
        mTodoDao.deleteTask(todoTask);
    }

}
