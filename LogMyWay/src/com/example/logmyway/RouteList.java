package com.example.logmyway;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.view.MenuItem;

 
public class RouteList extends ListActivity {
 
	static final String[] routes = new String[] { "Route A", "Route B", "Route C", "Route D", "Route E" };
		
 
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		//setContentView(R.layout.routelist);		
 
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.routelist, routes));
 
		//ListView listView = getListView();
		//listView.setTextFilterEnabled(true);
		
				
		  RouteListAdapter rla = new RouteListAdapter(this, routes);
		  setListAdapter(rla);			
		 
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		  super.onListItemClick(l, v, position, id);

		  // getting the value of clicked item
		  String clicked_item = (String) getListAdapter().getItem(position);
		  Toast.makeText(this, "You clicked : " + clicked_item,
		    Toast.LENGTH_SHORT).show();
		  
		  callKidList();
		  
		 }
	
	/*
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
	    // When clicked, show a toast with the TextView text
		//String selectedRoute = "Your selected route is '"+ ((TextView) view).getText().toString() +"'";
		callKidList();
		//ShowAlert(getString(R.string.app_name), selectedRoute);
	    //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
	}*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onBackPressed() {		
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {		
		case R.id.menu_ChangePassword:
			intent = new Intent(this, ChangePassword.class);
			startActivity(intent);			
			break;
		case R.id.menu_logout:
			intent = new Intent(this, Login.class);
			startActivity(intent);
			break;
		case R.id.menu_Exit:
			intent = new Intent(this, LogMyWayActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
			intent.putExtra("EXIT", true);
			this.startActivity(intent);
			break;		
		}
		return true;
	}
	
	
	public void callKidList() {
		Intent intent = new Intent(this, KidList.class);		
		startActivity(intent);
	}
		
}