package com.chrisfetz.gogetter.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import com.chrisfetz.gogetter.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static String TAG = MainViewModel.class.getSimpleName();
    private final Application mApplication;
    private Repository mRepo;


    public MainViewModelFactory(@NonNull Application application, Repository repo){
        mApplication = application;
        mRepo = repo;

        Log.d(TAG, "Creating a new MainViewModelFactory.");
    }

    public MainViewModel create(MainViewModel modelClass){
        Log.d(TAG, "Creating a new MainViewModel using the MainViewModelFactory.");
        return new MainViewModel(mApplication, mRepo);
    }
}