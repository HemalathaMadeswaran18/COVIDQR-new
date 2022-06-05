package com.example.covidqr_new;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Fetch extends AsyncTask<String, Void, String> {
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


            int rs = st.executeUpdate("INSERT INTO `MOVEMENT` (`USER_ID`, `USER_NAME`, `LOCATION`) " +
                    "VALUES ('" + id + "', '" + signin_class.username + "', '" + scan_qr.loc + "');");


            res = result;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


}