package com.example.mypriorities;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

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
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;



public class TimeSheet extends Activity {
	
	String url="";
	private static final int DATE_DIALOG_ID = 1;
    TextView txSelectedDate;
    DatePicker dpDate;
    private int year;
    private int month;
    private int day;
    private String currentDate;    
    private ListView lsview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timesheet);
		
		url=Login.WebApiUrl;
		
		String UserName = Login.Username;
		TextView txtUserName = (TextView)findViewById(R.id.tsUsername);
		txtUserName.setText("Welcome "+UserName);
				
		txSelectedDate = (TextView)findViewById(R.id.lbDate);		
		
		 ArrayList<GridColumn> job_details = GetSearchResults(Login.UserId,"-1");
	        
		 lsview = (ListView) findViewById(R.id.grid);  
		 lsview.setAdapter(new DataAdapter(this,job_details));
	       
	       
		 lsview.setOnItemClickListener(new OnItemClickListener() {
	           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {	               
	               Object o = lsview.getItemAtPosition(position);
	           	GridColumn obj_itemDetails = (GridColumn)o;
	       		//Toast.makeText(TimeSheet.this, "You have chosen : " + " " + obj_itemDetails.getProjectName(), Toast.LENGTH_LONG).show();
	       		
		       	// Cursor c = adapter.retrieveRow(rowId); // retrieve row from Database
	             //Intent edit = new Intent(this,NewItem.class);
	           //  edit.putExtra(DBAdapter.KEY_ID, rowId);
	            // edit.putExtra(DBAdapter.Title, c.getString(c.getColumnIndex(DBAdapter.Title)));
	            //edit.putExtra(DBAdapter.DATE, c.getString(c.getColumnIndex(DBAdapter.DATE)));
	            // startActivity(edit);
	           }
	       });
	       
	       
	       Button btn1 = (Button) findViewById(R.id.btnDate);
			// add a click listener to the button
			btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
			});
	}
	
	private void updateDisplay() {
	    currentDate = new StringBuilder().append(month + 1).append("/")
	            .append(day).append("/").append(year).toString();
	    
	}
		
	OnDateSetListener myDateSetListener = new OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker datePicker, int i, int j, int k) {

	        year = i;
	        month = j;
	        day = k;
	        updateDisplay();
	        txSelectedDate.setText(currentDate);
	        ArrayList<GridColumn> job_details = GetSearchResults(Login.UserId,currentDate);			        
	        lsview.setAdapter(new DataAdapter(TimeSheet.this,job_details));
	        
	    }
	};



	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:	    		    	
	    	Calendar calendar = Calendar.getInstance();
	    	year = calendar.get(Calendar.YEAR);
	    	month = calendar.get(Calendar.MONTH);
	    	day = calendar.get(Calendar.DAY_OF_MONTH);
	        return new DatePickerDialog(this, myDateSetListener, year, month, day);
	    }
	    return null;
	}	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timesheet, menu);
		return true;
	}
		
	
	private ArrayList<GridColumn> GetSearchResults(int UserId, String WeekDate){
    	ArrayList<GridColumn> results = new ArrayList<GridColumn>();
    	    	
       HttpClient httpClient = new DefaultHttpClient();
  	   HttpContext localContext = new BasicHttpContext();
  	   HttpGet httpGet = new HttpGet(url + "TimeSheet/?EmployeeId="+UserId + "&WeekDate="+WeekDate);  	     	     	  
  	   
  	   try {
  	   HttpResponse response = httpClient.execute(httpGet, localContext);
  	   HttpEntity entity = response.getEntity();

  	   	//CONVERT RESPONSE TO STRING
  	   	String result = EntityUtils.toString(entity);

  	   	//CONVERT RESPONSE STRING TO JSON ARRAY
  	   	JSONArray ja = new JSONArray(result);
  	   	// ITERATE THROUGH AND RETRIEVE CLUB FIELDS
  	   	int n = ja.length();
  	   	for (int i = 0; i < n; i++) {
  	       // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
  	       JSONObject jo = ja.getJSONObject(i);
  	       // RETRIEVE EACH JSON OBJECT'S FIELDS
  	       GridColumn jd = new GridColumn();
  	       jd.setProjectName(jo.getString("ProjectName").trim());
  	       jd.setSat(jo.getString("Sat").trim());
  	       jd.setSun(jo.getString("Sun").trim());
  	       jd.setMon(jo.getString("Mon").trim());
  	       jd.setTue(jo.getString("Tue").trim());
  	       jd.setWed(jo.getString("Wed").trim());
  	       jd.setThr(jo.getString("Thr").trim());
  	       jd.setFri(jo.getString("Fri").trim());
  	       jd.setStatus(jo.getString("Approved").trim());
  	       results.add(jd); 
  	    
  	       String selWeekDate = jo.getString("Weekdate").trim();  	       
  	      // Date dt = Date.valueOf(selWeekDate);  	       
  	       //SimpleDateFormat  format = new SimpleDateFormat("MM-dd-yyyy");  
  	       //Date date = (Date) format.parse(selWeekDate);    	       
  	         
  	       txSelectedDate.setText(selWeekDate);
  	   	}    	   
  	   } catch (Exception e) {
  		  // String err = e.toString();
  		   //TextView txtError = (TextView) findViewById(R.id.txtError);
  		   //txtError.setText(err);
  		  return null;
  	   }    	    
    	return results;
    }

}
