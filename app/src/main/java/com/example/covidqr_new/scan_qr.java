package com.example.covidqr_new;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;



public class scan_qr extends AppCompatActivity {

    public static final String url = "jdbc:mysql://10.0.2.2:3306/COVID_QR"; //ip of laptop and port of xampp
    public static final String user = "root";
    public static final String pass = "";
    static String loc = "";
    Button scan, user1, positive;
    Button status_tv;
    public String res;
    public static String res1 = "",res2 = "",res3 = "";
//public String location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr_ui);
        scan = findViewById(R.id.scan_qr_button);
        user1 = findViewById(R.id.button);
        positive = findViewById(R.id.tested_positive);
        status_tv = findViewById(R.id.btn_status);

        //  status_tv.setText(signin_class.status);


        status_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectorLogin1 login = new ConnectorLogin1();
                login.execute("");
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scan_qr.this, Positive_test.class);
                startActivity(intent);
            }
        });

        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("to user list");
                Intent intent = new Intent(scan_qr.this, Userlist.class);
                startActivity(intent);
            }
        });


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(scan_qr.this);
                integrator.setPrompt("Scan a barcode or QR Code");
                integrator.setOrientationLocked(true);
                integrator.setDesiredBarcodeFormats(integrator.QR_CODE);
                integrator.setCameraId(0);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
            } else {
                loc = result.getContents();

                Toast.makeText(this, loc, Toast.LENGTH_SHORT).show();

                Fetch fetch = new Fetch();
                fetch.execute("");

                DBHandler dbHandler = new DBHandler(scan_qr.this);
                // String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                // String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ");
                SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss ");
                String currentDate = sdf.format(new Date());
                String currentTime = sdf_time.format(new Date());
                System.out.println("current DATE" + currentDate);
                System.out.println("current TIME" + currentTime);

                dbHandler.addNewLocal(currentTime, currentDate);
                System.out.println("data to sqlite added ");
                System.out.println(dbHandler.getdata());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


    public class ConnectorLogin1 extends AsyncTask<String,Void,String> {
       public  String res = "";
         String output ="";
         String output2 ="";
         String output3 ="";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
              //  System.out.println("email login"+email_login);
              //  System.out.println("password"+password_login);

                ResultSet rs = st.executeQuery("SELECT * FROM `MOVEMENT` WHERE `USER_NAME` = '"+signin_class.username+"';");



                ResultSetMetaData rsmd = rs.getMetaData();
                // user user1 = new user();

                while (rs.next()) {                                         //-> to run with ddl
                  //  output += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    //output2 += rs.getString(2).toString() + "\n"; // TO
                    output += rs.getString(3).toString(); // TO

                }




                res = output;
                res1 = output;
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

                //todo next
                ConnectorLogin2 login2= new ConnectorLogin2();
                login2.execute("");

            }
            else {
                System.out.println("YOU ARE SAFE 1");
            }
        }
    }



    public class ConnectorLogin2 extends AsyncTask<String,Void,String> {
        String res = "";
         String output ="";
         String output2 ="";
         String output3 ="";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                //  System.out.println("email login"+email_login);
                //  System.out.println("password"+password_login);
                System.out.println("res1"+res1);
               String new_res = res1.toString();
                System.out.println("new res"+new_res);

                ResultSet rs = st.executeQuery("SELECT * FROM `MOVEMENT` WHERE `LOCATION` = '"+new_res+"' LIMIT 1;");



                ResultSetMetaData rsmd = rs.getMetaData();
                // user user1 = new user();

                while (rs.next()) {                                         //-> to run with ddl
                      output2 += rs.getString(2).toString(); // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    //output2 += rs.getString(2).toString() + "\n"; // TO
                   // output += rs.getString(3).toString() + "\n"; // TO

                }




                res = output2;
                res2 = output2;

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

                //todo next
//                Toast.makeText(scan_qr.this, "WELCOME BACK "+s, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(scan_qr.this, scan_qr.class);  //from and to ----------------------------!!!!!!!!
//                startActivity(intent);  //to open login page
                ConnectorLogin3 login3 = new ConnectorLogin3();
                login3.execute("");

            }
            else {
                System.out.println("YOU ARE SAFE 2");
            }
        }
    }



    public class ConnectorLogin3 extends AsyncTask<String,Void,String> {
        String res = "";
         String output ="";
         String output2 ="";
         String output3 ="";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                //  System.out.println("email login"+email_login);
                //  System.out.println("password"+password_login);
                System.out.println("res2"+res2);

                ResultSet rs = st.executeQuery("SELECT * FROM `POSITIVE` WHERE `NAME` = '"+res2+"' ; ");



                ResultSetMetaData rsmd = rs.getMetaData();
                // user user1 = new user();

                while (rs.next()) {                                         //-> to run with ddl
                    output += rs.getString(1).toString() ; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    //output2 += rs.getString(2).toString() + "\n"; // TO
                    // output += rs.getString(3).toString() + "\n"; // TO

                }




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



                System.out.println("flagged might have covid");
            }
            else {
                System.out.println("YOU ARE SAFE 3");
            }
        }
    }




}
