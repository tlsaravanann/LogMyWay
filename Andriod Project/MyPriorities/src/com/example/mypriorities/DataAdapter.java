package com.example.mypriorities;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class DataAdapter extends BaseAdapter
{
       Context mContext;       
       private LayoutInflater mInflater;
       
       private static ArrayList<GridColumn> itemDetailsrrayList;
       
       public DataAdapter(Context c)
       {
              mContext=c;
              mInflater = LayoutInflater.from(c);
       }
       
       
       public DataAdapter(Context context, ArrayList<GridColumn> results) {
   		itemDetailsrrayList = results;
   		mInflater = LayoutInflater.from(context);
   	}

   	public int getCount() {
   		return itemDetailsrrayList.size();
   	}

   	public Object getItem(int position) {
   		return itemDetailsrrayList.get(position);
   	}
       
              
       public long getItemId(int position)
       {
              return position;
       }
       public View getView(int position, View convertView, ViewGroup parent)
       {                            
            ViewHolder holder;
      		if (convertView == null) {
      			convertView = mInflater.inflate(R.layout.customgrid, null);
      			holder = new ViewHolder();
      			      			      			
      			
      			holder.txtProjectName = (TextView) convertView.findViewById(R.id.txtProjectName);
      			holder.txtProjectName.setPadding(8,8,8,8);
      			
      			
      			holder.txtSat = (TextView) convertView.findViewById(R.id.txtSat);
      			holder.txtSat.setPadding(8,8,8,8);
      			
      			holder.txtSun = (TextView) convertView.findViewById(R.id.txtSun);
      			holder.txtSun.setPadding(8,8,8,8);
      			
      			holder.txtMon = (TextView) convertView.findViewById(R.id.txtMon);
      			holder.txtMon.setPadding(8,8,8,8);
      			
      			holder.txtTue = (TextView) convertView.findViewById(R.id.txtTue);
      			holder.txtTue.setPadding(8,8,8,8);
      			
      			holder.txtWed = (TextView) convertView.findViewById(R.id.txtWed);
      			holder.txtWed.setPadding(8,8,8,8);
      			
      			holder.txtThr = (TextView) convertView.findViewById(R.id.txtThr);
      			holder.txtThr.setPadding(8,8,8,8);
      			
      			holder.txtFri = (TextView) convertView.findViewById(R.id.txtFri);
      			holder.txtFri.setPadding(8,8,8,8);
      			
      			holder.imgStatus = (ImageView) convertView.findViewById(R.id.imgStatus);
      			holder.imgStatus.setPadding(8,8,8,8);
      			      			

      			convertView.setTag(holder);
      		} else {
      			holder = (ViewHolder) convertView.getTag();
      		}
      		
      		holder.txtProjectName.setText(itemDetailsrrayList.get(position).getProjectName());
      		holder.txtSat.setText(itemDetailsrrayList.get(position).getSat());
      		holder.txtSun.setText(itemDetailsrrayList.get(position).getSun());
      		holder.txtMon.setText(itemDetailsrrayList.get(position).getMon());
      		holder.txtTue.setText(itemDetailsrrayList.get(position).getTue());
      		holder.txtWed.setText(itemDetailsrrayList.get(position).getWed());
      		holder.txtThr.setText(itemDetailsrrayList.get(position).getThr());
      		holder.txtFri.setText(itemDetailsrrayList.get(position).getFri());
      		int Status = Integer.parseInt(itemDetailsrrayList.get(position).getStatus());
      		if(Status==0)
      		{
      			holder.imgStatus.setImageResource(R.drawable.logo_pending);
      		}
      		else
      			holder.imgStatus.setImageResource(R.drawable.logo_approve);
      		
      		

      		return convertView;
       }
       static class ViewHolder
       {        
              TextView txtProjectName;        
              TextView txtSat;
              TextView txtSun;
              TextView txtMon;
              TextView txtTue;
              TextView txtWed;
              TextView txtThr;
              TextView txtFri;              
              TextView txtWeekDate;
              ImageView imgStatus;
       }
            
}