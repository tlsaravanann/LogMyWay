package com.example.logmyway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login extends Activity {

	public static final String Pref_Name = "PrefData";    //unique identifier for our Preferences
	private static final String Pref_Username = "username"; 
	private static final String Pref_Password = "password";
	//private static final String Pref_Rememberme = "rememberme";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		final CheckBox chkRememberMe =(CheckBox)findViewById(R.id.chkRememberMe);
		final EditText txtUsername =(EditText)findViewById(R.id.txtUsername);
		final EditText txtPassword =(EditText)findViewById(R.id.txtPassword);		
		 
		 
		SharedPreferences pref = getSharedPreferences(Pref_Name, MODE_PRIVATE);   
		String username = pref.getString(Pref_Username, null);
		String passwordshare = pref.getString(Pref_Password, null);		
		
		if(username != null && passwordshare != null)
		{
			txtUsername.setText(username);
			txtPassword.setText(passwordshare);
			chkRememberMe.setChecked(true);
		}
		
		Button btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {	            
	            finish();
	            System.exit(0);
	        }
	    });
		
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) 
	        {
	        	String sUsername = txtUsername.getText().toString().trim();
	        	String sPassword = txtPassword.getText().toString().trim();
	        	
	        	if(sUsername.equals(""))
	        	{
	        		ShowAlert(getString(R.string.app_name), getString(R.string.msg_UsernameEmpty));   	
	        	}
	        	else if(sPassword.equals(""))
	        	{
	        		ShowAlert(getString(R.string.app_name), getString(R.string.msg_PasswordEmpty));
	        	}
	        	else
	        	{
	        		int IsExist = 1; //CheckLogIn(txtUsername.getText().toString(), txtPassword.getText().toString());
		        	if(IsExist > 0)
		        	{
		        		if(chkRememberMe.isChecked())
		        		{
			    			getSharedPreferences(Pref_Name, MODE_PRIVATE)
			    	        .edit()
			    	        .putString(Pref_Username, sUsername)
			    	        .putString(Pref_Password, sPassword)			    	        
			    	        .commit();
			    		}
		        		else if(!chkRememberMe.isChecked())
		        		{
		        			getSharedPreferences(Pref_Name, MODE_PRIVATE).edit().clear().commit();
		        		}
		        		CallIntent();		        					        	
		        	}
		        	else
		  	   		{	  	   				 
		        		getSharedPreferences(Pref_Name, MODE_PRIVATE).edit().clear().commit();
			  	   		ShowAlert(getString(R.string.app_name), getString(R.string.msg_LoginFailed));   	
			  	   	}		 
	        	}	        	       	    		       
	        }
	    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@SuppressWarnings("deprecation")
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
	
	private void CallIntent() {
		Intent intent = new Intent(this, ChangePassword.class);
		startActivity(intent);


	}

}
