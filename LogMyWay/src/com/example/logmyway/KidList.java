package com.example.logmyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

@SuppressWarnings("unused")
public class KidList extends Activity {
	
	LogMyWayActivity baseActivity;
	
	// All static variables
	
		
	
	//static final String URL = "http://api.androidhive.info/music/music.xml";
	
	static final String URL = "file:///android_asset/Student.xml";
	// XML node keys
	static final String KEY_LIST = "list"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "name";
	static final String KEY_ARTIST = "stop";
	static final String KEY_DURATION = "area";
	static final String KEY_THUMB_URL = "photo";
	
	ListView list;
    KidListAdapter adapter;
    
    
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		  TextView switchStatus = (TextView) findViewById(R.id.duration);
		  Switch mySwitch = (Switch)findViewById(R.id.switch1);
		 
		  //set the switch to ON 
		  //mySwitch.setChecked(true);
		  //attach a listener to check for changes in state
		  
		  /*
		  mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			  
			   @Override
			   public void onCheckedChanged(CompoundButton buttonView,
			     boolean isChecked) {
			 
			    if(isChecked){
			     switchStatus.setText("Available");
			    }else{
			     switchStatus.setText("Not Available");
			    }
			 
			   }
			  }); 
		  
		  */
		//check the current state before we display the screen
		  if(mySwitch.isChecked()){
		   switchStatus.setText("Available");
		  }
		  else {
		   switchStatus.setText("Not Available");
		  }
		
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

			
		XMLParser parser = new XMLParser();
		
		String xml = getFileName();
		
		//String xml = parser.getXmlFromUrl(newPath); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element
		
		NodeList nl = doc.getElementsByTagName(KEY_LIST);
		// looping through all song nodes <song>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
			map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));			
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

			// adding HashList to ArrayList
			songsList.add(map);
		}
		
		list=(ListView)findViewById(R.id.list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new KidListAdapter(this, songsList);        
        list.setAdapter(adapter);
        
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					
			}
		});		
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;		
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
	
	private String getFileName()
	{
		AssetManager assManager = getAssets();
		String xmlString;
		
		try {
			String[] files = assManager.list("");
			for(int i=0; i<files.length; i++)            
			{               
				if(files[i].contains(".xml"))
				{
					InputStream is = assManager.open(files[i]);
			        int length = is.available();
			        byte[] data = new byte[length];
			        is.read(data);
			        xmlString = new String(data);
					return xmlString;
				}
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return "";
	}
}