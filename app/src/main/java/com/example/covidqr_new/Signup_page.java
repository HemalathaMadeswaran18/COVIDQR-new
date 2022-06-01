package com.example.covidqr_new;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Signup_page extends AppCompatActivity {


    EditText ed_name, ed_phone, ed_email, ed_college , ed_password, ed_conf_password;
    Button signup_btn;
    public static String name;
    public static String email;
    public static String phone;
    public static String college;
    public static String password;
    public String confirm_pass;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        ed_name = findViewById(R.id.ed_su_name);
        ed_email = findViewById(R.id.ed_su_email);
        ed_phone = findViewById(R.id.ed_su_phone);
        ed_college = findViewById(R.id.ed_su_college);
        ed_password = findViewById(R.id.ed_su_password);
        ed_conf_password = findViewById(R.id.ed_su_pass_conf);



        name = ed_name.getText().toString();
        email = ed_email.getText().toString();

        phone = ed_phone.getText().toString();

        college = ed_college.getText().toString();

        password = ed_password.getText().toString();

        confirm_pass = ed_conf_password.getText().toString();



        signup_btn = findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send send  = new Send();
                send.execute("");
            }
        });


    }
}
