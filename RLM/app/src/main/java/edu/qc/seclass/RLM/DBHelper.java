package edu.qc.seclass.RLM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    //This isn't important
    private static final int DATABASE_VERSION = 1;

    // Database Name, MUST BE FILLED IN WHEN DB IS MADE
    private static final String DATABASE_NAME = "ReminderApp.db";
    public static final String TABLE_NAME1 = "RListTable";
    public static final String TABLE_NAME3 = "RTable";
    public static final String TABLE_NAME4 = "RTypeTable";


    public static final String COL_1 = "ListID";
    public static final String COL_2 = "ListName";
    public static final String COL_3 = "RemID";
    public static final String COL_4 = "Reminder";
    public static final String COL_5 = "TypeID";
    public static final String COL_6 = "TypeName";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RTypeTable (\n" +
                "    TypeID INTEGER PRIMARY KEY \n" +
                "    ,TypeName TEXT NOT NULL \n" +
                "    );" );
        db.execSQL("CREATE TABLE RListTable (\n" +
                "    ListID INTEGER PRIMARY KEY  \n" +
                "    ,ListName TEXT NOT NULL UNIQUE\n" +
                "\n" +
                "    );" );
        db.execSQL("CREATE TABLE RTable (\n" +
                "    RemID INTEGER \n" +
                "    ,Reminder TEXT NOT NULL\n" +
                "    ,ListID INTEGER \n" +
                "    ,TypeID INTEGER \n" +
                "    ,isChecked BOOLEAN \n" +
                "    ,PRIMARY KEY (RemID)\n" +
                "    ,FOREIGN KEY (ListID)\n" +
                "    REFERENCES RListTable (ListID) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION\n" +
                "    ,FOREIGN KEY (TypeID)\n" +
                "    REFERENCES RTypeTable (TypeID) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION\n" +
                "    ); " );
        db.execSQL("CREATE TABLE RAlerts (\n" +
                "RemID INTEGER \n" +
                ",AlertID INTEGER \n "+
                ",AlertTime TIMESTRING \n" +
                ",AlertDay INTEGER \n" +
                "    ); " );
        db.execSQL("INSERT INTO RListTable (Listname) VALUES('Choose a list ')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop TABLE if EXISTS RListTable;" );
        db.execSQL("drop TABLE if EXISTS RTable;" );
        db.execSQL("drop TABLE if EXISTS RTypeTable;" );
        //drop table query here to drop the existing tables on upgrade
        onCreate(db);
    }

    public List<String> getListsForMain() {
        //this method will query all Lists from the List table
        //create List of Strings
        List<String> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" select " + COL_2 + "  from " + TABLE_NAME1 , null);
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(0));
            } while (cursor.moveToNext());

            //Get read-only db access: SQLiteDatabase db = this.getReadableDatabase();
            //Grab the data: Cursor cursor = db.rawQuery(Query, null);
        /* navigate the return data and add it to the list. This doesn't need to be done this way
           but here is an example regardless.
           if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }*/
            //close everything


        }
        cursor.close();
        db.close();
        return result;
    }


    public List<String> getLists() {
        //this method will query all Lists from the List table
        //create List of Strings
        List<String> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" select " + COL_2 + "  from " + TABLE_NAME1 + " WHERE ListID != ? ", new String[] {"1"});
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(0));
            } while (cursor.moveToNext());

            //Get read-only db access: SQLiteDatabase db = this.getReadableDatabase();
            //Grab the data: Cursor cursor = db.rawQuery(Query, null);
        /* navigate the return data and add it to the list. This doesn't need to be done this way
           but here is an example regardless.
           if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }*/
            //close everything


        }
        cursor.close();
        db.close();
        return result;
    }

    public List<List<String>> showReminders(String ListName) {
        List<List<String>> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(
                "SELECT RTable.Reminder, RListTable.ListName,  RTypeTable.TypeName FROM RTable " +
                "INNER JOIN RListTable ON RTable.ListID = RListTable.ListID INNER JOIN RTypeTable ON RTable.TypeID = RTypeTable.TypeID WHERE RListTable.ListName = ? ",
                new String[] {ListName});
        if (res.moveToFirst()) {
            do {
                List<String> item = new ArrayList<>();
                item.add(res.getString(0));
                item.add(res.getString(1));
                item.add(res.getString(2));
                result.add(item);
            } while (res.moveToNext());
        }
        res.close();
        db.close();
        return result;
    }


    public void checkReminder(String remName){
        //get writeable DB
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("isChecked", true);
        (db).update("RTable", contentValues, "Reminder = ?",new String[] {remName});
        db.close();
    }

    public void insertList(String Listname){
        //get a writeable db:
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Listname);
        //insert the value into the Table
       // db.insert(TABLE_NAME1, null, contentValues);
       (db).insert(TABLE_NAME1, null, contentValues);
        db.close();
    }
    public void insertType(String reminderType){
        //get a writeable db:
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6,reminderType);
        //insert the value into the Table
        // db.insert(TABLE_NAME1, null, contentValues);
        (db).insert(TABLE_NAME4, null, contentValues);

    }

    public void addReminder(String ReminderName, String ReminderType, String ListName, Boolean status){
        //get a writeable db:

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor listID =  db.rawQuery(" Select ListID From RListTable WHERE ListName = ? " , new String[] {ListName} );
        int listIDres = 0;

        
        if (listID.moveToFirst()) {
            do {
                listIDres = listID.getInt(0);
            } while (listID.moveToNext());
        }
        listID.close();

        insertType(ReminderType);


        Cursor typeID =  db.rawQuery(" Select TypeID From RTypeTable WHERE TypeName = ? " , new String[] {ReminderType} );
        int typeIDres = 0;


        if (typeID.moveToFirst()) {
            do {
                typeIDres = typeID.getInt(0);
            } while (typeID.moveToNext());
        }
        typeID.close();
        

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4,ReminderName);
        contentValues.put(COL_1, listIDres );
        contentValues.put(COL_5, typeIDres );
        contentValues.put("isChecked", status);
        //insert the value into the Table
        db.insert(TABLE_NAME3, null, contentValues);

//        contentValues.put(COL_6,ReminderType);
//        //insert the value into the Table
//        db.insert(TABLE_NAME4, null, contentValues);

        db.close();
    }
    public void deleteList(String listName){
        //get a writeable db
        SQLiteDatabase db = this.getWritableDatabase();
        // get all the reminders ids related to that list and delete them first
        Cursor result = db.rawQuery("SELECT RTable.RemID FROM RTable " +
                        "INNER JOIN RListTable ON RTable.ListID = RListTable.ListID INNER JOIN RTypeTable ON RTable.TypeID = RTypeTable.TypeID " +
                        "WHERE RListTable.ListName = ?  ",
                new String[]{listName});

        int remIDres = 0;


        if (result.moveToFirst()) {
            do {
                remIDres = result.getInt(0);
                db.delete(TABLE_NAME3, "RemID = ? ", new String[]{String.valueOf(remIDres)});
            } while (result.moveToNext());
        }

        //call delete method using the name
        db.delete(TABLE_NAME1,"ListName=?",new String[]{listName});
        result.close();
        db.close();
    }

    public void deleteReminder(String reminderName){
        //get a writeable db
        SQLiteDatabase db = this.getWritableDatabase();
        //call delete method using the name
        db.delete(TABLE_NAME3,"Reminder=?",new String[]{reminderName});
        db.close();
    }

    public List<List<String>> searchReminders(String reminderName) {
        List<List<String>> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(
                //Working for now
                "SELECT RTable.Reminder, RListTable.ListName,  RTypeTable.TypeName FROM RTable " +
                        "INNER JOIN RListTable ON RTable.ListID = RListTable.ListID INNER JOIN RTypeTable ON RTable.TypeID = RTypeTable.TypeID WHERE RTable.Reminder LIKE ? ",
                new String[] { reminderName + "%" });


        if (res.moveToFirst()) {
            do {
                List<String> item = new ArrayList<>();
                item.add(res.getString(0));
                item.add(res.getString(1));
                item.add(res.getString(2));
                result.add(item);
            } while (res.moveToNext());
        }
        res.close();
        db.close();
        return result;
    }
    public void updateReminder(String reminderName,  String reminderType, String listName , String newReminderName){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("SELECT RTable.RemID FROM RTable " +
                        "INNER JOIN RListTable ON RTable.ListID = RListTable.ListID INNER JOIN RTypeTable ON RTable.TypeID = RTypeTable.TypeID " +
                        "WHERE RListTable.ListName = ?  AND RTable.Reminder = ? AND RTypeTable.TypeName = ? ",
                new String[]{listName, reminderName, reminderType});

        int remIDres = 0;


        if (result.moveToFirst()) {
            do {
                remIDres = result.getInt(0);
            } while (result.moveToNext());
        }
        System.out.println(remIDres);
        ContentValues values = new ContentValues();
        values.put(COL_4, newReminderName);
        db.update(TABLE_NAME3, values, "RemID = ? ", new String[]{String.valueOf(remIDres)});
        result.close();
        db.close();

    }
    public void updateType(String reminderName, String newReminderType){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("SELECT TypeID \n" +
                        "FROM RTable \n" +
                        "WHERE Reminder = ?",
                new String[]{reminderName});

        int typeIDRes = 0;


        if (result.moveToFirst()) {
            do {
                typeIDRes = result.getInt(0);
            } while (result.moveToNext());
        }
        ContentValues values = new ContentValues();
        values.put(COL_6, newReminderType);
        db.update(TABLE_NAME4, values, "TypeID = ? ", new String[]{String.valueOf(typeIDRes)});
        result.close();
        db.close();

    }

    public void updateList(String oldName,String  newName){
        SQLiteDatabase db = this.getWritableDatabase();

        // Gets the ListID for specific listname that need to be updated


        Cursor listID =  db.rawQuery(" Select ListID From RListTable WHERE ListName = ? " , new String[] {oldName} );
        int listIDres = 0;


        if (listID.moveToFirst()) {
            do {
                listIDres = listID.getInt(0);
            } while (listID.moveToNext());
        }
         // sets the value in the column to the new updated name of the list.
        ContentValues values = new ContentValues();
        values.put(COL_2, newName);
        db.update(TABLE_NAME1, values, "ListID = ?", new String[]{String.valueOf(listIDres)});
        listID.close();
        db.close();

    }
}
