package com.chrisfetz.gogetter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chrisfetz.gogetter.BackendExecutors;
import com.chrisfetz.gogetter.R;
import com.chrisfetz.gogetter.database.TodoDatabase;
import com.chrisfetz.gogetter.database.TodoTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    //The database instance
    private TodoDatabase mTdb;

    //UI Elements
    private FloatingActionButton mSubmit;
    private Button mButton;
    private EditText mTitle;
    private EditText mDescription;

    //The colors are pulled from this array
    TypedArray colors;

    //The color chosen by the user
    private int mColor = 0;

    //Activity name for logging
    private static final String TAG = AddTaskActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        setUpActivityFixtures();
    }

    /**
     * Initializes various variables used in the Activity
     */
    private void setUpActivityFixtures() {
        mTdb = TodoDatabase.getInstance(getApplicationContext());
        mTitle = findViewById(R.id.et_addtask_set_task_title);
        mDescription = findViewById(R.id.et_addtask_set_task_description);
        mSubmit = findViewById(R.id.fab_add_task);
        mButton = findViewById(R.id.button_addtask_set_task_color);
        colors = getResources().obtainTypedArray(R.array.color_values);

        mButton.setOnClickListener(getColorButtonListener());

        mSubmit.setOnClickListener(getSubmitListener());
    }

    /**
     * Creates a dialog to pick the task's color when the user presses the "SET COLOR" button.
     * Changes the color of the button to the user's choice.
     */
    private void showColorDialog() {
        String[] colorNames = getResources().getStringArray(R.array.color_names);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddTaskActivity.this);
        builder.setTitle(R.string.text_set_task_color);

        builder.setSingleChoiceItems(colorNames, mColor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Set the background of the color select button to the chosen color
                int blue =  ContextCompat.getColor(AddTaskActivity.this,
                        R.color.color_task_blue);
                mButton.setBackgroundColor(colors.getColor(which, blue));

                //Keep track of the chosen color for TodoTask object creation
                mColor = which;
                Log.d(TAG, "Color selection made!");
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * OnClick handling for the submit button.
     */
    private void onSubmit(){
        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        if (title.equals("")){
            Log.d(TAG, "TodoTask object creation rejected: No title!");
            Toast.makeText(AddTaskActivity.this, R.string.error_no_title,
                    Toast.LENGTH_LONG).show();
        } else {
            Date date = new Date();
            final TodoTask todoTask = new TodoTask(title, description, mColor, date, date);
            Log.d(TAG, "TodoTask created successfully. Running diskIO now...");
            BackendExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mTdb.todoDao().insertTask(todoTask);
                    finish();
                }
            });
        }
    }

    /**
     * @return mSubmit's OnClickListener
     */
    private View.OnClickListener getSubmitListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        };
    }

    /**
     * @return mButton's OnClickListener
     */
    private View.OnClickListener getColorButtonListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Color selection button pressed!");
                showColorDialog();
            }
        };
    }
}
