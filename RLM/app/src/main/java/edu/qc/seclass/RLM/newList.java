<<<<<<< HEAD:GLM/app/src/main/java/edu/qc/seclass/GLM/newList.java
package edu.qc.seclass.GLM;
=======
package edu.qc.seclass.RLM;
>>>>>>> dev:GroupProject/RLM/app/src/main/java/edu/qc/seclass/RLM/newList.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newList extends AppCompatActivity {

    String listName;
    EditText listNameInput;
    Button createListButton;
    DBHelper helper;

//    MainActivity main = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        createListButton =  findViewById(R.id.confirmButtonXML);
        createListButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                listNameInput = (EditText) findViewById(R.id.nameInput);
                listName = listNameInput.getText().toString();
                //check if listname was empty
                if(listName.equals("")) {Toast.makeText(newList.this, "No name was entered", Toast.LENGTH_LONG).show();}
                else {
                    //initialize the dbhelper
                    helper = new DBHelper(getApplicationContext());

                    //add the list to the db
                    helper.insertList(listName);

                    //print success message to screen
                    Toast.makeText(newList.this, listName + " list created successfully!", Toast.LENGTH_LONG).show();

//                    main.updateSpinner();
                    finish();
                }

            }

        });

    }


}