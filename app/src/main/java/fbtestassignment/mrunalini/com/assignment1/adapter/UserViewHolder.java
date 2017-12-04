package fbtestassignment.mrunalini.com.assignment1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import fbtestassignment.mrunalini.com.assignment1.R;
import fbtestassignment.mrunalini.com.assignment1.models.ResponseModel;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView duration;
    public TextView riseTime;
    public UserViewHolder(View itemView) {
        super(itemView);
        duration = (TextView)itemView.findViewById(R.id.duration_tv);
        riseTime = (TextView)itemView.findViewById(R.id.risetime_tv);
    }

    public void populateItems(ResponseModel userDetailsModel, int position) {
        duration.setText("Duration : "+userDetailsModel.getDuration());
        Date date = new Date(userDetailsModel.getRisetime());

        String dateStr = convertDateToStr(date);
        String userDate = formatDateToUser(dateStr);

        riseTime.setText("Rise Time : "+userDate);

    }
    public String convertUTCToDate(){
        String dateStr = "Jul 16, 2013 12:08:59 AM";
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.setTimeZone(TimeZone.getDefault());
        String formattedDate = df.format(date);
        return formattedDate;
    }
    public static String formatDateToUser(String str){
        String date = "";
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date date1 = myDateFormat.parse(str);
            SimpleDateFormat myDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
            date = myDateFormat2.format(date1);
        }catch (ParseException e){

        }
        return date;
    }


    public static String convertDateToStr(Date st_dob) {
        if (st_dob != null) {
            try {
                SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm"); // this is the existing format of date
                return myDateFormat.format(st_dob);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}