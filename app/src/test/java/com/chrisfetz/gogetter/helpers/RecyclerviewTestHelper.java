package com.chrisfetz.gogetter.helpers;

import android.content.Context;
import android.view.View;

import com.chrisfetz.gogetter.TodoAdapter;
import com.chrisfetz.gogetter.database.TodoTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;

public class RecyclerviewTestHelper {

    /**
     * Creates an array of valid TodoTask objects
     * @return A list of TodoTask objects
     */
    public static List<TodoTask> getList(){
        Date date = new Date();
        TodoTask first = new TodoTask(1, "title1", "", 0, date, date);
        TodoTask second = new TodoTask(2, "title2", "description", 1, date, date);
        TodoTask third = new TodoTask(3, "title3", "okay", 2, date, date);
        List<TodoTask> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        list.add(third);
        return list;
    }

    /**
     * Creates an empty ItemClickListener to satisfy the TodoAdapter constructor
     * @return An instance of ItemClickListener
     */
    public static TodoAdapter.ItemClickListener getEmptyListener(){
        TodoAdapter.ItemClickListener itemClickListener = new TodoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //do nothing :)
            }
        };
        return itemClickListener;
    }



    /**
     * Creates a TodoAdapter using the Instrumentation context and an empty ItemClickListener.
     * @return A TodoAdapter
     */
    public static TodoAdapter getAdapter(){
        Context instrumentationContext = InstrumentationRegistry.getInstrumentation().getContext();
        TodoAdapter.ItemClickListener listener = getEmptyListener();
        TodoAdapter adapter = new TodoAdapter(instrumentationContext, listener);
        return adapter;
    }
}
