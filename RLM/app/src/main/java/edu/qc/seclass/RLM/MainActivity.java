<<<<<<< HEAD:GLM/app/src/main/java/edu/qc/seclass/GLM/MainActivity.java
package edu.qc.seclass.GLM;
=======
package edu.qc.seclass.RLM;
>>>>>>> dev:GroupProject/RLM/app/src/main/java/edu/qc/seclass/RLM/MainActivity.java

import android.content.DialogInterface;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button newListButton;
    Button newReminderButton;
    Button deleteListButton;
    Button showRemindersButton;
    Button showListsButton;
    Button searchReminderButton;
    Spinner spinner3;
    String selectedList;
    DBHelper db;
    List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner3 = (Spinner) findViewById(R.id.spinner4);
        selectedList = "";

        //initialize dbhelper
        db = new DBHelper(this);

        spinner3.setPrompt("Most recent created reminder list");

        //grab the list names
        list = db.getListsForMain();

        //load the names into the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);

        //create an OnItemSelectedListener
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose a list ")) {
                    // do nothing
                }
                else{
                    //set the name of the List that was chosen
                    selectedList = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), "Selected List: " + selectedList, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        newReminderButton = (Button) findViewById(R.id.makeReminder);
        newReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCreateReminder();
            }
        });

        deleteListButton = (Button) findViewById(R.id.deleteList);
        deleteListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteList();
            }
        });

        showRemindersButton = (Button) findViewById(R.id.showReminders);
        showRemindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner3.getSelectedItemPosition() != 0)
                    switchShowReminders();
                else
                    Toast.makeText(getApplicationContext(),"A list is not selected", Toast.LENGTH_SHORT).show();
            }
        });

        newListButton = (Button) findViewById(R.id.makeList);
        newListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCreateList();
            }
        });

        showListsButton = (Button) findViewById(R.id.showLists);
        showListsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchShowLists();
            }
        });

        searchReminderButton = (Button) findViewById(R.id.searchReminder);
        searchReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSearchReminder();
            }
        });

    }
    public String getSelectedText (){
       return selectedList;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateSpinner();
    }

    public void updateSpinner () {
        //grab the list names
        list = db.getListsForMain();

        //load the names into the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
    }

    public void switchCreateList() {
        Intent intent = new Intent(this, newList.class);
        startActivity(intent);
        updateSpinner();
    }

    public void switchCreateReminder() {
        Intent intent = new Intent(this, newReminder.class);
        //get the list that was chosen from the dropdown.
        startActivity(intent);
    }

    public void switchShowReminders(){
        //Package selected list to send to showReminder intent
        Bundle bundleReminders = new Bundle();
        bundleReminders.putString("list",spinner3.getSelectedItem().toString());
        Intent intent = new Intent(this, showReminders.class);
        //Append Bundle to intent
//        intent.putExtras(bundleReminders);
        intent.putExtra("list",spinner3.getSelectedItem().toString());
        startActivity(intent);
    }
    public void deleteList(){
        //get the list that was chosen from the dropdown.
        if(spinner3.getSelectedItemPosition() == 0) {Toast.makeText(this,"No List was selected", Toast.LENGTH_LONG).show();}
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure you would like to delete " + getSelectedText() + "list?");

// Set up the buttons
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.deleteList(selectedList);
                    updateSpinner();
                }
            });
            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();

        }

    }

    public void switchShowLists() {
        Intent intent = new Intent (this, showLists.class);
        //get the list that was chosen from the dropdown.
        if(list.size() <= 1) {Toast.makeText(this,"No created lists", Toast.LENGTH_LONG).show();}
        else
            startActivity(intent);
    }

    public void switchSearchReminder() {
        Intent intent = new Intent (this, searchReminder.class);
        startActivity(intent);
        updateSpinner();
    }
}
     /*  private Button newListButton;

    @Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newListButton = (Button) findViewById(R.id.makeList);
        newListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCreateList();
            }
        });
    }

    public void switchCreateList() {
        Intent intent = new Intent(this, newList.class);
        startActivity(intent);
    }


}
*/