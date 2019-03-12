package com.example.gogetter;

import androidx.lifecycle.Observer;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    // Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();

    //Member variables
    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting onclick for Floating action button
        mFab = findViewById(R.id.fab_main);

        mFab.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                Intent goToAddTask = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTask);
            }
        });

        ArrayList<String> animals = new ArrayList<>();
        animals.add("Camel");
        animals.add("Hippo");
        animals.add("Zebra");
        
        // set up the RecyclerView
        mRecyclerView = findViewById(R.id.rvAnimals);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyRecyclerViewAdapter(this, animals);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        setupViewModel();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private void setupViewModel(){
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getStrings().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> strings) {
                Log.d(TAG, "List of items updated in the ViewModel by button!");
                mAdapter.setStrings(strings);
            }
        });
    }


}
