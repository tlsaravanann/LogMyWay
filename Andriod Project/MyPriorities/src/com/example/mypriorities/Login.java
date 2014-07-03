package com.example.mypriorities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends Activity {

	public static final String WebApiUrl = "http://uirandd.uiasiapac.com/MyPriorities/api/";	
	public static final String PREFS_NAME = "MyPrefsFile";    //unique identifier for our Preferences
	private static final String PREF_USERNAME = "username"; 
	private static final String PREF_PASSWORD = "password";
	private static final String PREF_CHECKED = "checked";
	
	//User Defined Global Variables
	public static String Username;
	public static int UserId;
	
	TextView lbWaiting;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);	
		
		final CheckBox checkBox=(CheckBox)findViewById(R.id.checkBox1);
		final EditText userID=(EditText)findViewById(R.id.txtUsername);
		final EditText password=(EditText)findViewById(R.id.txtPassword);		
		lbWaiting = (TextView)findViewById(R.id.lbWaiting); 
		 
		SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);   
		String username = pref.getString(PREF_USERNAME, null);
		String passwordshare = pref.getString(PREF_PASSWORD, null);
		//String checked=pref.getString(PREF_CHECKED, "FALSE");
		
		if(username !=null && passwordshare !=null)
		{
			userID.setText(username);
			password.setText(passwordshare);
			checkBox.setChecked(true);
		}
		
		Button btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	            finish();
	            System.exit(0);
	        }
	    });
		
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) 
	        {
	        	if(userID.getText().toString().isEmpty())
	        	{
	        		ShowAlert("Login", "Please Enter your Email!");   	
	        	}
	        	else if(password.getText().toString().isEmpty())
	        	{
	        		ShowAlert("Login", "Please Enter your Password!");   	
	        	}
	        	else
	        	{
	        		int IsExist = CheckLogIn(userID.getText().toString(), password.getText().toString());
		        	if(IsExist > 0)
		        	{
		        		lbWaiting.setVisibility(View.VISIBLE);
		        		if(checkBox.isChecked())
		        		{
			    			getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
			    	        .edit()
			    	        .putString(PREF_USERNAME, userID.getText().toString())
			    	        .putString(PREF_PASSWORD, password.getText().toString())
			    	        .putString(PREF_CHECKED,"TRUE")
			    	        .commit();
			    		}
		        		else if(!checkBox.isChecked())
		        		{
		        			getSharedPreferences(PREFS_NAME,MODE_PRIVATE).edit().clear().commit();
		        		}
		        		lbWaiting.setVisibility(View.GONE);
		        		//Calling TimeSheet page
			        	CallTimeSheet();
		        	}
		        	else
		  	   		{	  	   				 
		        		getSharedPreferences(PREFS_NAME,MODE_PRIVATE).edit().clear().commit();
			  	   		ShowAlert("Login Failed", "Invalid Username or Password!");   	
			  	   	}		 
	        	}
	        	       	    		        
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
		
	
	private int CheckLogIn(String EmailId, String Password){    	
    	
			//Show Progressbar
			int AuthUser = 0;
			
		 	ProgressDialog progress = null; 
		 	progress = ProgressDialog.show(this, "Login", "Processing", false, true);	        
		
	       HttpClient httpClient = new DefaultHttpClient();
	  	   HttpContext localContext = new BasicHttpContext();
	  	   String URL= WebApiUrl+"login/?Email="+EmailId+"&Password="+Password;
	  	   HttpGet httpGet = new HttpGet(URL);  	     	     	  
	  	   
	  	   try {
	  	   HttpResponse response = httpClient.execute(httpGet, localContext);
	  	   HttpEntity entity = response.getEntity();
	  	   String result = EntityUtils.toString(entity);
	  	   JSONArray ja = new JSONArray(result);	  	   
	  	   	int n = ja.length();	  	   		  		  	   	
	  	   	if(n>0)
	  	   	{
	  	   		JSONObject jh = ja.getJSONObject(0);
	  	   		AuthUser =  Integer.parseInt(jh.getString("IsExist"));
	  	   		progress.dismiss();
	  	   		if(AuthUser > 0)
	  	   		{
	  	   			Username = jh.getString("UserName"); 
	  	   			UserId = Integer.parseInt(jh.getString("UserId"));	  	   				  	   			
	  	   		}	  	   		  	   		
	  	   	}	  	   	
	  	   } catch (Exception e) {	  		   
	  		   AuthUser = -1;
	  	   }
	  	   return AuthUser;
	    }
		
		private void ShowAlert(String Title, String Message)
		{
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	        alertDialog.setTitle(Title);
	        alertDialog.setMessage(Message);
	        alertDialog.setIcon(R.drawable.login_failed);
	        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {	                 
	                }
	        });

	        alertDialog.show();  	
		}
		
		private void CallTimeSheet()
		{			
	   		Intent intent = new Intent(this, TimeSheet.class);
	   		startActivity(intent);
		}
}
