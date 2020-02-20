package edu.qc.seclass.RLM;

import android.app.TimePickerDialog;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Calendar;
import java.util.List;

public class newReminder extends AppCompatActivity {

    Button backButton, confirmButton, timeButton;
    RadioGroup ampm, alertGroup, repeatGroup;
    RadioButton alertButton, noAlertButton;
    RadioButton am, pm, repeat, noRepeat;
    CheckBox monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    String reminderName;
    String reminderType;
    String listName;
    TextView listSelected;
    EditText reminderNameInput, reminderTypeInput;
    Spinner spinner;
    int day;
    TextView colon;
    EditText hour;
    EditText minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        /*
         *   Generate activity to add Reminder to some list
         *   Adds Name and Type of Reminder
         *   List should be generated from a db
         */

        alertGroup = findViewById(R.id.alertGroup);
        repeatGroup = findViewById(R.id.repeatGroup);
        colon = findViewById(R.id.alertColon);
        hour = findViewById(R.id.hourInput);
        minute = findViewById(R.id.minuteInput);
        ampm = findViewById(R.id.ampmToggle);
        am = findViewById(R.id.amToggle);
        pm = findViewById(R.id.pmToggle);
        repeat = findViewById(R.id.repeatToggle);
        monday = findViewById(R.id.mondayBox);
        tuesday = findViewById(R.id.tuesdayBox);
        wednesday = findViewById(R.id.wednesdayBox);
        thursday = findViewById(R.id.thursdayBox);
        friday = findViewById(R.id.fridayBox);
        saturday = findViewById(R.id.saturdayBox);
        sunday = findViewById(R.id.sundayBox);
        colon.setVisibility(View.GONE);
        hour.setVisibility(View.GONE);
        minute.setVisibility(View.GONE);
        ampm.setVisibility(View.GONE);
        repeatGroup.setVisibility(View.GONE);
        monday.setVisibility(View.GONE);
        tuesday.setVisibility(View.GONE);
        wednesday.setVisibility(View.GONE);
        thursday.setVisibility(View.GONE);
        friday.setVisibility(View.GONE);
        saturday.setVisibility(View.GONE);
        sunday.setVisibility(View.GONE);

        backButton = findViewById(R.id.backButtonXML);
        confirmButton = findViewById(R.id.confirmButtonXML);
        timeButton = findViewById(R.id.selectTimeButton);
        //button onclick creates a TimePicker object, then returns the user picked time to the textbox
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int calHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int calMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(newReminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedHour>=12){//if not AM, set toggle PM
                            ampm.check(pm.getId());
                            if(selectedHour!=12)selectedHour = selectedHour - 12;
                        }
                        else {
                            ampm.check(am.getId());
                            if(selectedHour == 0) selectedHour+=12;
                        }
                        hour.setText(""+selectedHour);//The plus empty string converts the int to a string
                        minute.setText(""+selectedMinute);
                    }
                }, calHour, calMinute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        timeButton.setVisibility(View.GONE);
        alertButton = findViewById(R.id.alertToggle);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colon.setVisibility(View.VISIBLE);
                hour.setVisibility(View.VISIBLE);
                minute.setVisibility(View.VISIBLE);
                ampm.setVisibility(View.VISIBLE);
                repeatGroup.setVisibility(View.VISIBLE);
                monday.setVisibility(View.VISIBLE);
                tuesday.setVisibility(View.VISIBLE);
                wednesday.setVisibility(View.VISIBLE);
                thursday.setVisibility(View.VISIBLE);
                friday.setVisibility(View.VISIBLE);
                saturday.setVisibility(View.VISIBLE);
                sunday.setVisibility(View.VISIBLE);
                timeButton.setVisibility(View.VISIBLE);
            }
        });

        noAlertButton = findViewById(R.id.noAlertToggle);
        noAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colon.setVisibility(View.GONE);
                hour.setVisibility(View.GONE);
                minute.setVisibility(View.GONE);
                ampm.setVisibility(View.GONE);
                repeatGroup.setVisibility(View.GONE);
                timeButton.setVisibility(View.GONE);
                monday.setVisibility(View.GONE);
                tuesday.setVisibility(View.GONE);
                wednesday.setVisibility(View.GONE);
                thursday.setVisibility(View.GONE);
                friday.setVisibility(View.GONE);
                saturday.setVisibility(View.GONE);
                sunday.setVisibility(View.GONE);
            }
        });

        spinner = findViewById(R.id.newReminderSpin);
//      listSelected = (TextView) findViewById(R.id.ItemSelectText);
        listName = "";

        //create a db helper object
        final DBHelper db = new DBHelper(getApplicationContext());

        //grab the list names
        List<String> list = db.getLists();

        //load the names into the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //create an OnItemSelectedListener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //set the name of the List that was chosen
                listName = parent.getItemAtPosition(position).toString();

                //update the activity to show that list was chosen
//              listSelected.setText(listName + " was Selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                reminderNameInput = (EditText) findViewById(R.id.reminderNameInputXML);
                reminderTypeInput = (EditText) findViewById(R.id.reminderTypeInputXML);
                reminderName = reminderNameInput.getText().toString();//These will return an empty string if nothing was input
                reminderType = reminderTypeInput.getText().toString();// ^


                //if nothing was selected, this should throw a toast error message
                if(listName.equals("")) {Toast.makeText(newReminder.this,"No List was selected", Toast.LENGTH_LONG).show();}
                else if(reminderName.equals("")){ Toast.makeText(newReminder.this,"No Reminder was designated", Toast.LENGTH_LONG).show();}
                else if(reminderType.equals("")) {Toast.makeText(newReminder.this,"No Type was designated", Toast.LENGTH_LONG).show();}
                else {
                    //commit the reminder to the Reminder table
                    db.addReminder(reminderName,reminderType,listName, false);
                    //print the confirmation to screen
                    Toast.makeText(newReminder.this, reminderName + " - " + reminderType + " reminder saved to " + listName + " list", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
