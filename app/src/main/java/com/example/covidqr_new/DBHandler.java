package com.example.covidqr_new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "LOCALDB";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "LOCAL_MOVEMENT";


    // below variable id for our course duration column.
    private static final String TIME = "TIME";

    // below variable for our course description column.
    private static final String DATE = "DATE";

    // below variable is for our course tracks column.
    private static final String LOCATION = "LOCATION";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + TIME + " TEXT, "
                + DATE + " TEXT,"
                + LOCATION + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewLocal(String time, String date) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TIME, time);
        values.put(DATE, date);
        values.put(LOCATION, scan_qr.loc);


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<String> getdata(){
         SQLiteDatabase db = getWritableDatabase();
        Cursor c1 = db.rawQuery("select * from LOCAL_MOVEMENT",new String[]{});
        ArrayList<String> result = new ArrayList<>();
        while(c1.moveToNext()){
            result.add(c1.getString(2)); //column  number

        }
        c1.close();
        return result;

    }

    public Cursor getdataC(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from LOCAL_MOVEMENT",null);


        System.out.printf("cursot"+cursor);
        return cursor;
    }



}

