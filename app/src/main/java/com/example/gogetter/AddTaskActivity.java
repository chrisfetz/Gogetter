package com.example.gogetter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gogetter.database.TodoDatabase;
import com.example.gogetter.database.TodoTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    private TodoDatabase tDb;

    //UI Elements
    private FloatingActionButton mSubmit;
    private Button mButton;
    private EditText mTitle;
    private EditText mDescription;

    //The colors are pulled from this array
    TypedArray colors;

    private int mColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        tDb = TodoDatabase.getInstance(getApplicationContext());

        mTitle = findViewById(R.id.et_set_task_title);
        mDescription = findViewById(R.id.et_set_task_description);
        mSubmit = findViewById(R.id.fab_add_task);
        mButton = findViewById(R.id.button_set_task_color);
        colors = getResources().obtainTypedArray(R.array.color_values);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorDialog();
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
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

                //Keep track of the chosen color for TodoTask object
                mColor = which;
                Toast.makeText(AddTaskActivity.this, "Color: "
                        + Integer.toString(which), Toast.LENGTH_LONG).show();
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
            Toast.makeText(AddTaskActivity.this, R.string.error_no_title,
                    Toast.LENGTH_LONG).show();
        } else {
//            Date date = new Date();
//            TodoTask todoTask = new TodoTask(title, description, mColor, date, date);
            //TODO: Write the todoTask to the database without using the main thread.
//            finish();
        }
    }
}
