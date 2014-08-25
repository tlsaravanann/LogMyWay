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
import android.view.MenuItem;

 
public class RouteList extends ListActivity {
 
	static final String[] routes = new String[] { "Route A", "Route B", "Route C", "Route D", "Route E" };
	
	LogMyWayActivity baseActivity;
	RouteList selfActivity;
 
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		//setContentView(R.layout.routelist);		
 
		setListAdapter(new ArrayAdapter<String>(this, R.layout.routelist, routes));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);		
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				//String selectedRoute = "Your selected route is '"+ ((TextView) view).getText().toString() +"'";
				callKidList();
				//ShowAlert(getString(R.string.app_name), selectedRoute);
			    //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		}); 
	}
	
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