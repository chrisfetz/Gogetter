package com.example.gogetter;


import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

/**
 * The ViewModel that passes data from the Repository to the Activity.
 */
public class MainViewModel extends AndroidViewModel {

    private static MutableLiveData<ArrayList<String>> strings;

    public MainViewModel(Application application) {
        super(application);
        ArrayList<String> stringsList = new ArrayList<>();
        stringsList.add("Camel");
        stringsList.add("Hippo");
        stringsList.add("Zebra");
        strings = new MutableLiveData<>();
        strings.postValue(stringsList);
    }

    //getter for the MutableLiveData
    public MutableLiveData<ArrayList<String>> getStrings() {
        return strings;
    }

    //add a value to the MutableLiveData
    public static void addString(String newString){
        ArrayList<String> newList = strings.getValue();
        System.out.println(newList.toString());
        newList.add(newString);
        System.out.println(newList.toString());
        strings.postValue(newList);
    }
}
