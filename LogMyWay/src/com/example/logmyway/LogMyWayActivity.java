package com.example.logmyway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LogMyWayActivity extends Activity {

	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.login);
		
		if (getIntent().getBooleanExtra("EXIT", false)) {            
        	finish();
        	return;
        }					
	}	
	
	public void callLogin(Context context) {
		intent = new Intent(context, Login.class);
		startActivity(intent);
	}
	
	public void callChangePassword(Context context) {
		intent = new Intent(context, ChangePassword.class);
		startActivity(intent);
	}
	
	public void callRouteList(Context context) {
		intent = new Intent(context, RouteList.class);
		startActivity(intent);
	}
	
	public void callKidList(Context context) {
		intent = new Intent(context, KidList.class);
		startActivity(intent);
	}
	
	public void callApplicationExit(Context context) {
		intent = new Intent(context, LogMyWayActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		intent.putExtra("EXIT", true);
		this.startActivity(intent);
	}
}
