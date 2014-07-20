package com.example.logmyway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends LogMyWayActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepassword);
		
		final EditText txtChangePassword =(EditText)findViewById(R.id.txtChangePassword);
		final EditText txtRetypePassword =(EditText)findViewById(R.id.txtRetypePassword);
		
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) 
	        {
	        	String sChangePassword = txtChangePassword.getText().toString().trim();
	        	String sRetypePassword = txtRetypePassword.getText().toString().trim();
	        	
	        	if(sChangePassword.equals(""))
	        	{
	        		ShowAlert(getString(R.string.app_name), getString(R.string.msg_ChangePasswordEmpty));   	
	        	}
	        	else if(sRetypePassword.equals(""))
	        	{
	        		ShowAlert(getString(R.string.app_name), getString(R.string.msg_RetypePasswordEmpty));
	        	}
	        	else if(!sRetypePassword.equals(sChangePassword))
	        	{
	        		ShowAlert(getString(R.string.app_name), getString(R.string.msg_PasswordMismatched));
	        	}
	        	else
	        	{
	        		int IsExist = 1; //CheckLogIn(txtUsername.getText().toString(), txtPassword.getText().toString());
		        	if(IsExist > 0)
		        	{		        		
		        		//CallIntent();		        					        	
		        	}
		        	else
		  	   		{	  	   				 		        		
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
        //alertDialog.setIcon(R.drawable.login_failed);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {	                 
                }
        });

        alertDialog.show();  	
	}

}
