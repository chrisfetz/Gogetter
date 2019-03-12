package com.example.gogetter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mButton = findViewById(R.id.button_set_task_color);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorDialog();
            }
        });
    }

    /**
     * Creates a dialog to pick the task's color when the user presses the "SET COLOR" button.
     *
     */
    private void showColorDialog() {
        String[] colorNames = getResources().getStringArray(R.array.color_names);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddTaskActivity.this);
        builder.setTitle(R.string.text_set_task_color);

        int chosenItem = 0;
        builder.setSingleChoiceItems(colorNames, chosenItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AddTaskActivity.this, "Position: " + which + " chosen ", Toast.LENGTH_LONG).show();
            }
        });

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
