package com.chrisfetz.gogetter;

import android.content.Context;
import android.view.View;

import com.chrisfetz.gogetter.database.TodoTask;
import com.chrisfetz.gogetter.helpers.RecyclerviewTestHelper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RecyclerViewTests {

    @Test
    public void initValuesSetsUpValues(){
        List<TodoTask> list = RecyclerviewTestHelper.getList();
        assertThat(list.get(0).getId(), is(equalTo(1)));
    }

    @Test
    public void setAndGetContentsShouldWork(){
        TodoAdapter adapter = RecyclerviewTestHelper.getAdapter();
        List<TodoTask> list = RecyclerviewTestHelper.getList();

        adapter.setContents(list);

        assertThat(list, is(equalTo(adapter.getContents())));
    }

    @Test
    public void setClickListenerShouldChangeListener(){
        TodoAdapter adapter = RecyclerviewTestHelper.getAdapter();
        TodoAdapter.ItemClickListener listener = new TodoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //do nothing!!
            }
        };

        adapter.setClickListener(listener);

        assertThat(listener, is(equalTo(adapter.getClickListener())));
    }

    @Test
    public void getItemCountShouldReturnZeroIfNoContents(){
        TodoAdapter adapter = RecyclerviewTestHelper.getAdapter();
        assertThat(adapter.getItemCount(), is(equalTo(0)));
    }

    @Test
    public void getItemCountShouldReturnSizeOfContents(){
        TodoAdapter adapter = RecyclerviewTestHelper.getAdapter();
        List<TodoTask> list = RecyclerviewTestHelper.getList();

        adapter.setContents(list);

        assertThat(adapter.getItemCount(), is(equalTo(3)));
    }


}
