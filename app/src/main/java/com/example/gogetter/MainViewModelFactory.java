package com.example.gogetter;

import android.app.Application;

import com.example.gogetter.database.TodoDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final TodoDatabase mTdb;
    private final Application mApplication;


    public MainViewModelFactory(@NonNull Application application, TodoDatabase database){
        mApplication = application;
        mTdb = database;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        //unchecked exception where cast is invalid
        return (T) new MainViewModel(mApplication, mTdb);
    }
}
