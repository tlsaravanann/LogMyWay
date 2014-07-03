package com.example.mypriorities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		
		TextView txtDateTime = (TextView)findViewById(R.id.lblDateTime);
		txtDateTime.setText(dateFormat.format(date));
		
		postLoginData();
		
		GetSearchResults();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void GetSearchResults(){    	
    	    	
       HttpClient httpClient = new DefaultHttpClient();
  	   HttpContext localContext = new BasicHttpContext();
  	   HttpGet httpGet = new HttpGet("http://192.168.206.169/TestWebApi/api/employee/?id=1&all=all");  	     	     	  
  	   
  	   try {
  	   HttpResponse response = httpClient.execute(httpGet, localContext);
  	   HttpEntity entity = response.getEntity();

  	   	//CONVERT RESPONSE TO STRING
  	   	String result = EntityUtils.toString(entity);

  	   	//CONVERT RESPONSE STRING TO JSON ARRAY
  	   	JSONArray ja = new JSONArray(result);
  	   	// ITERATE THROUGH AND RETRIEVE CLUB FIELDS
  	   	int n = ja.length();
  	   	
  	  LinearLayout llChild = (LinearLayout)findViewById(R.id.grd_Child);
   		llChild.removeAllViews();
  	   	
  	   	if(n>0)
  	   	{
  	   		JSONObject jh = ja.getJSONObject(0);
  	   		TextView txtUserName = (TextView)findViewById(R.id.lblUsername);
  	   		txtUserName.setText(jh.getString("EmployeeName"));
  	   		
  	   		
  	   	}
  	   	for (int i = 0; i < n; i++) {
  	       // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
  	       JSONObject jo = ja.getJSONObject(i);
  	         	       
  	       //Set Project Name
  	       TextView txtProjectName = new TextView(this);
  	       txtProjectName.setText(jo.getString("ProjectName"));
  	       
  	       //Set Hours in Days
  	       
  	       EditText edSat = new EditText(this);
  	       edSat.setText(jo.getString("Sat"));
  	       
  	     EditText edSun = new EditText(this);
  	     edSun.setText(jo.getString("Sun"));
	       
	       EditText edMon = new EditText(this);
	       edMon.setText(jo.getString("Mon"));
  	       
  	     EditText edTue = new EditText(this);
  	     edTue.setText(jo.getString("Tue"));
	       
	       EditText edWed = new EditText(this);
	       edWed.setText(jo.getString("Wed"));
  	       
  	     EditText edThr = new EditText(this);
  	   edThr.setText(jo.getString("Thr"));
	       
	       EditText edFri = new EditText(this);
  	       edFri.setText(jo.getString("Fri"));
  	       
  	     TextView txtStatus = new TextView(this);
	       txtStatus.setText(jo.getString("Status"));
  	       
	       llChild.addView(txtProjectName, i);
	       llChild.addView(edSat, i);
	       llChild.addView(edSun, i);
	       llChild.addView(edMon, i);
	       llChild.addView(edTue, i);
	       llChild.addView(edWed, i);
	       llChild.addView(edThr, i);
	       llChild.addView(edFri, i);
	       llChild.addView(txtStatus, i);	       
	       
	         	       
  	       //jd.setJobDesc(jo.getString("JobPostedDate"));
  	      //results.add(jd); 
  	           	     
  	   	}    	   
  	   } catch (Exception e) {
  		   String err = e.toString();
  		   LinearLayout llChild = (LinearLayout)findViewById(R.id.grd_Child);
  		 TextView txtError = new TextView(this);
  		txtError.setText(err);
  		llChild.addView(txtError);
  	   }
    }
	
	 
    public void postLoginData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();                  
                  
        HttpPost httppost = new HttpPost("http://192.10.1.40/TestWebApi/api/employee");
 
        try {
                  
         String id = "2";
          
         String all = "all";
          
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("id", id));
            nameValuePairs.add(new BasicNameValuePair("all", all));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
            // Execute HTTP Post Request
         //   Log.w("SENCIDE", "Execute HTTP Post Request");
            HttpResponse response = httpclient.execute(httppost);
            
            HttpEntity entity = response.getEntity();

      	   	//CONVERT RESPONSE TO STRING
      	   	//String result = EntityUtils.toString(entity);
             
            String str = inputStreamToString(entity.getContent()).toString();
           // Log.w("SENCIDE", str);
            /* 
            if(str.toString().equalsIgnoreCase("true"))
            {
             //Log.w("SENCIDE", "TRUE");
             result.setText("Login successful");   
            }else
            {
           //  Log.w("SENCIDE", "FALSE");
             result.setText(str);             
            }
 			*/
        } catch (ClientProtocolException e) {
         e.printStackTrace();
        } catch (IOException e) {
         e.printStackTrace();
        }
    } 
   
    private StringBuilder inputStreamToString(InputStream is) {
     String line = "";
     StringBuilder total = new StringBuilder();
     // Wrap a BufferedReader around the InputStream
     BufferedReader rd = new BufferedReader(new InputStreamReader(is));
     // Read response until the end
     try {
      while ((line = rd.readLine()) != null) { 
        total.append(line); 
      }
     } catch (IOException e) {
      e.printStackTrace();
     }
     // Return full string
     return total;
    }

}
