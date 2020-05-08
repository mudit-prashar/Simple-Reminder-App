package edu.qc.seclass.RLM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class showReminders extends AppCompatActivity {

    List<List<String>> reminders;
    List<String> reminderNames;
    ListView list;
    ArrayAdapter<String> adapter;
    DBHelper db;
    Spinner spinner;
    String listName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reminders);
        //Handle received data from MainActivity
        Bundle bundleReminder = getIntent().getExtras();
        listName = bundleReminder.getString("list");
        //Populate list
        updateReminders();
        //Handle item Clicked
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String reminderName = parent.getItemAtPosition(position).toString();
                String reminderType = reminders.get(position).get(2);
                Intent intent = new Intent(showReminders.this, viewReminder.class);
                intent.putExtra("Name", reminderName);
                intent.putExtra("Type", reminderType);
                intent.putExtra("List", listName);
                startActivity(intent);
            }
        });
    }
    protected void updateReminders(){
        //        initialize the dbhelper
        db = new DBHelper(getApplicationContext());
        spinner = findViewById(R.id.spinner4);
        //get all Lists from the db
        reminders = db.showReminders(listName);
        reminderNames = new ArrayList<>();
        for(List l : reminders)
            reminderNames.add(l.get(0).toString());
        //display the lists
        list = findViewById(R.id.listOfRemindersXML);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, reminderNames);
        list.setAdapter(adapter);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        updateReminders();
    }
}
