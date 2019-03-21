package com.chrisfetz.gogetter.utilities;

import android.app.Application;
import android.content.Context;

import com.chrisfetz.gogetter.BackendExecutors;
import com.chrisfetz.gogetter.ui.viewmodel.MainViewModelFactory;
import com.chrisfetz.gogetter.Repository;
import com.chrisfetz.gogetter.database.TodoDatabase;

public class Injector {

    public static Repository provideRepository(Context context){
        TodoDatabase database = TodoDatabase.getInstance(context.getApplicationContext());
        BackendExecutors executors = BackendExecutors.getInstance();
        return Repository.getInstance(database.todoDao(), executors);
    }

    public static MainViewModelFactory provideMainViewModelFactory(Application application, Context context){
        Repository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(application, repository);
    }
}
