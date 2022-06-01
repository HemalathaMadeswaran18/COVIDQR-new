package com.example.covidqr_new;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
 static String loc = "";
    Button scan,user;
public String res;
//public String location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_qr_ui);
        scan = findViewById(R.id.scan_qr_button);
        user = findViewById(R.id.button);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(scan_qr.this,Userlist.class);
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
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null){
            if(result.getContents()==null){
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
            }
            else {
                loc  = result.getContents();

                Toast.makeText(this, loc, Toast.LENGTH_SHORT).show();

                Fetch fetch = new Fetch();
                fetch.execute("");

                DBHandler dbHandler = new DBHandler(scan_qr.this);
               // String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
               // String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                String currentTime = "12.00";
                String currentDate = "12/3/2022";
                dbHandler.addNewLocal(currentTime, currentDate);


                System.out.println("data to sqlite added ");
                System.out.println(dbHandler.getdata());


            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }

    }
}
