package com.example.covidqr_new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Positive_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positive_test);

        Button positive ;
        positive = findViewById(R.id.sure_positive);

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sendpos login1 = new Sendpos();
                login1.execute("");





            }
        });




    }
}







class Sendpos extends AsyncTask<String, Void, String> {
    String res = "";
    String name = "default";
    String id = "1234";
    public static final String url = "jdbc:mysql://10.0.2.2:3306/COVID_QR"; //ip of laptop and port of xampp
    public static final String user = "root";
    public static final String pass = "";

    @Override
    protected String doInBackground(String... strings) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Databaseection success");


            String result = "Database Connection Successful\n";
            Statement st = con.createStatement();
String status = "POSITIVE";

            int rs = st.executeUpdate("INSERT INTO `POSITIVE` (`NAME`, `EMAIL`, `STATUS`) " +
                    "VALUES ('" + signin_class.username + "', '" + signin_class.email_id + "', '" + status + "');");
//TODO id is defaulted

            res = result;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


}