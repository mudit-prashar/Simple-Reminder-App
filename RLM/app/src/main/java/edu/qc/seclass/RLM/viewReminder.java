<<<<<<< HEAD:GLM/app/src/main/java/edu/qc/seclass/GLM/viewReminder.java
package edu.qc.seclass.GLM;
=======
package edu.qc.seclass.RLM;
>>>>>>> dev:GroupProject/RLM/app/src/main/java/edu/qc/seclass/RLM/viewReminder.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class viewReminder extends AppCompatActivity {

    Button backButton;
    Button editButton;
    Button deleteButton;
    String reminderName, listName, reminderType;
    EditText reminderNameInput, listNameInput, reminderTypeInput;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);
        //create an instance of the dbhelper
        helper = new DBHelper(getApplicationContext());
        reminderTypeInput = (EditText) findViewById(R.id.reminderTypeInputXML);
        reminderNameInput = (EditText) findViewById(R.id.reminderNameInputXML);
        listNameInput = (EditText) findViewById(R.id.listNameInputXML);
        //Set values of fields
        reminderNameInput.setText(getIntent().getStringExtra("Name"));
        reminderTypeInput.setText(getIntent().getStringExtra("Type"));
        listNameInput.setText(getIntent().getStringExtra("List"));

        backButton = findViewById(R.id.backButtonXML);
        editButton = findViewById(R.id.editButtonXML);
        deleteButton = findViewById(R.id.deleteButtonXML);


        reminderName = reminderNameInput.getText().toString();
        listName = listNameInput.getText().toString();
        reminderType = reminderTypeInput.getText().toString();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //execute the edit Reminder method from the dbhelper
                String newName = reminderNameInput.getText().toString();
                String newType = reminderTypeInput.getText().toString();
                helper.updateReminder(reminderName, reminderType, listName, newName);
                helper.updateType(newName, newType);
                Toast.makeText(viewReminder.this, "Reminder edited successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helper.deleteReminder(reminderName);
                Toast.makeText(viewReminder.this, reminderName + " reminder deleted successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // Prevents the user to edit the list name in view reminder activity
        listNameInput.setInputType(InputType.TYPE_NULL);
        listNameInput.setKeyListener(null);
    }

}
