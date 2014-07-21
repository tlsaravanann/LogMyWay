package com.example.logmyway;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
 
public class RouteList extends ListActivity {
 
	static final String[] routes = new String[] { "Route A", "Route B", "Route C", "Route D", "Route E" };
 
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
				String selectedRoute = "Your selected route is '"+ ((TextView) view).getText().toString() +"'";
				ShowAlert(getString(R.string.app_name), selectedRoute);
			    //Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		}); 
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