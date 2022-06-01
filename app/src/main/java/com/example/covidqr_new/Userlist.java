package com.example.covidqr_new;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class Userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> date,time,location;
    DBHandler DB;
    MyAdapter myAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHandler(this);
        date = new ArrayList<>();
        time = new ArrayList<>();
        location = new ArrayList<>();
        displayData();
        System.out.println("inside userlist");
        recyclerView = findViewById(R.id.recyclerview);
        myAdapter = new MyAdapter(this,date,time,location);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    private void displayData() {
        Cursor cursor = (Cursor) DB.getdataC();
        while (cursor.moveToNext()){
            date.add(cursor.getString(0));
            time.add(cursor.getString(1));
            location.add(cursor.getString(2));

        }

    }
}
