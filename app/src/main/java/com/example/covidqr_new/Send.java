package com.example.covidqr_new;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Send extends AsyncTask<String, Void, String> {
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


            int rs = st.executeUpdate("INSERT INTO `USER` (`NAME`, `PHONE`, `EMAIL`, `COLLEGE`, `PASSWORD`, `ID`) " +
                    "VALUES ('" + Signup_page.name + "', '" + Signup_page.phone + "', '" + Signup_page.email + "', '" + Signup_page.college + "', '" + Signup_page.password + "', '" + id + "');");
//TODO id is defaulted

            res = result;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


}