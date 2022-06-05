package com.example.covidqr_new;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class signin_class extends AppCompatActivity {
    public static final String url = "jdbc:mysql://10.0.2.2:3306/COVID_QR"; //ip of laptop and port of xampp
    public static final String user = "root";
    public static final String pass = "";

    public static String username,email_detail,link,phone_num, email_id,status = "";



    String email_login,password_login;

    EditText phone,password;
    Button signin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);

        phone = findViewById(R.id.phone_login);
        password = findViewById(R.id.pass_login);
        signin = findViewById(R.id.login_btn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_login = phone.getText().toString();
                password_login=password.getText().toString();

                ConnectorLogin login = new ConnectorLogin();
                login.execute("");

                ConnectorLogin2 login2 = new ConnectorLogin2();
                login2.execute("");

            }
        });


    }


    public class ConnectorLogin extends AsyncTask<String,Void,String> {
        String res = "";
        public String output ="";
        public String output2 ="";
        public String output3 ="";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                System.out.println("email login"+email_login);
                System.out.println("password"+password_login);

                ResultSet rs = st.executeQuery("SELECT * FROM `USER` WHERE `PHONE` = '"+email_login+"' AND `PASSWORD` = '"+password_login+"';");



                ResultSetMetaData rsmd = rs.getMetaData();
               // user user1 = new user();

                while (rs.next()) {                                         //-> to run with ddl
                    output += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    output2 += rs.getString(2).toString() + "\n"; // TO
                    output3 += rs.getString(3).toString() + "\n"; // TO

                }



username = output;
                phone_num = output2;
                email_id = output3;
                res = output;

                System.out.println("res"+res+"res");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return res;
        }


        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);


            if (s!=""){
                Toast.makeText(signin_class.this, "WELCOME BACK "+s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signin_class.this, scan_qr.class);  //from and to ----------------------------!!!!!!!!
                startActivity(intent);  //to open login page

            }
            else {
                Toast.makeText(signin_class.this,"INVALID DETAILS QQ", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public class ConnectorLogin2 extends AsyncTask<String,Void,String> {
        String res = "";
        public String output ="";
        public String output2 ="";
        public String output3 ="";
        @Override
        protected String doInBackground(String... strings) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("INSIDE SECOND CONNECTOR FOR COVID CHECK");

            String result = "Database Connection Successful\n";
            Statement st = con.createStatement();
            System.out.println("email login"+email_login);
            System.out.println("password"+password_login);

            ResultSet rs = st.executeQuery("SELECT * FROM `POSITIVE` WHERE `EMAIL` = '"+email_id+"';");



            ResultSetMetaData rsmd = rs.getMetaData();
            // user user1 = new user();

            while (rs.next()) {                                         //-> to run with ddl
                output += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                output2 += rs.getString(2).toString() + "\n"; // TO
                output3 += rs.getString(3).toString() + "\n"; // TO

            }



//            username = output;
//            phone_num = output2;
//            email_id = output3;
            res = output;

            System.out.println("res"+res);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }


        @Override
        protected void onPostExecute(String s) {
        System.out.println(s);


        if (s!=""){
            Toast.makeText(signin_class.this, "YOU HAVE COVID!!!! "+s, Toast.LENGTH_SHORT).show();
            System.out.println("covid :YES");
            status = "YOU HAVE BEEN FLAGGED AS A CLOSE CONTACT, PLEASE ISOLATE YOURSELF";

        }
        else {
            Toast.makeText(signin_class.this,"NO COVID", Toast.LENGTH_SHORT).show();
            System.out.println("covid :NO");
            status = "NOT FLAGGED";
        }
    }
    }




}
