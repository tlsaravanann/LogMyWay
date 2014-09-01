package com.example.logmyway;




import com.example.logmyway.R.string;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class RouteListAdapter extends ArrayAdapter<string> {

 private final Context context;
 private final String[] values;

 public RouteListAdapter(Context context, String[] value) {
  super(context, R.layout.routelist);
  this.context = context;
  this.values = value;
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent) {
  // TODO Auto-generated method stub
  LayoutInflater inflater = (LayoutInflater) context
    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  View rowView = inflater.inflate(R.layout.routelist, parent, false);
  TextView tv = (TextView) rowView.findViewById(R.id.lblRouteName);
  String item_value = values[position];

  tv.setText(item_value);

  if (position % 2 == 0) {
   rowView.setBackgroundColor(Color.parseColor("#ffffff"));
  } else {
   rowView.setBackgroundColor(Color.parseColor("#BCF7F0"));
  }
  return rowView;
 }
}