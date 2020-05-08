package edu.qc.seclass.RLM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class showLists extends AppCompatActivity {

    List<String> lists;
    ListView list;
    ArrayAdapter<String> adapter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists);

        //initialize the dbhelper
        db = new DBHelper(getApplicationContext());
        //get all Lists from the db
        lists = db.getLists();
        //display the lists
        list = findViewById(R.id.listOfListsXML);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, lists);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent (showLists.this, viewList.class);
                intent.putExtra("Name", temp);
                startActivity(intent);
            }
        });
        updateLists();
    }
    protected void updateLists(){
        //        initialize the dbhelper
        db = new DBHelper(getApplicationContext());

        //get all Lists from the db
        lists = db.getLists();
        //display the lists
        list = findViewById(R.id.listOfListsXML);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, lists);
        list.setAdapter(adapter);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        updateLists();
    }
}
