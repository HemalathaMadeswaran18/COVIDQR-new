package com.example.covidqr_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton open_scan_btn;
    Button signout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

open_scan_btn=findViewById(R.id.floatingActionButton);
signout = findViewById(R.id.button3);
        open_scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, scan_qr.class);
                startActivity(intent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Signup_page.class);
                startActivity(intent);
            }
        });



    }
}