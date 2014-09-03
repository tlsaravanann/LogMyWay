package com.example.logmyway;

import java.util.Locale;

import org.apache.http.util.LangUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 
public class GPSActivity extends Activity {
     
    Button btnShowLocation;
     
    // GPSTracker class
    GPSTracker gps;
    TextView lblLat;
    TextView lblLang;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);
         
        btnShowLocation = (Button) findViewById(R.id.btnCancel);
        
        lblLat = (TextView) findViewById(R.id.lbllat);
        lblLang = (TextView) findViewById(R.id.lbllang);
         
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {        
                // create class object
                gps = new GPSTracker(GPSActivity.this);
 
                // check if GPS enabled     
                if(gps.canGetLocation()){
                     
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                     
                    //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                    //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    //startActivity(intent);
                    
                    /*
                    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f", latitude, longitude);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);
                    
                    */
                    
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=%d&q=%f,%f (%s)", 
                            latitude, longitude, 8, latitude, longitude, "Guindy");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                    
                    lblLat.setText("Lat"+ latitude);
                    lblLang.setText("Long"+ longitude);
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
                 
            }
        });
    }
     
}
