package com.chrisfetz.gogetter.utilities;

import android.app.Application;
import android.content.Context;

import com.chrisfetz.gogetter.BackendExecutors;
import com.chrisfetz.gogetter.ui.viewmodel.MainViewModelFactory;
import com.chrisfetz.gogetter.Repository;
import com.chrisfetz.gogetter.database.TodoDatabase;

/**
 * Provides items needed by Activities
 */
public class Injector {

    /**
     * Provides a Repository instance for an Activity
     * @param context the Activity's context
     * @return a Repository instance
     */
    public static Repository provideRepository(Context context){
        TodoDatabase database = TodoDatabase.getInstance(context.getApplicationContext());
        BackendExecutors executors = BackendExecutors.getInstance();
        return Repository.getInstance(database.todoDao(), executors);
    }

    /**
     * Provides a MainViewModelFactory for an Activity
     * @param application Gogetter, the application
     * @param context the Activity's context
     * @return a new MainViewModelFactory
     */
    public static MainViewModelFactory provideMainViewModelFactory(Application application, Context context){
        Repository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(application, repository);
    }
}
