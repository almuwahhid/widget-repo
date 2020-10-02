package com.gamatechno.pakaryo.widget.materialcalendar.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.gamatechno.pakaryo.widget.R;
import com.gamatechno.pakaryo.widget.materialcalendar.EventDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarViewHelper {
    public static List<String> getDaysRange(String startDate, String endDate, boolean onlybunessdays) throws ParseException {
        SimpleDateFormat sdf;
        SimpleDateFormat sdf1;
        List<Date> dates = new ArrayList<Date>();
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat checkformat = new SimpleDateFormat("yyyy-MM-dd");
        checkformat.applyPattern("EEE");  // to get Day of week
        try{
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String stdate = sdf1.format(sdf.parse(startDate));
            String enddate= sdf1.format(sdf.parse(endDate));

            Date  sDate = (Date)sdf1.parse( stdate);
            Date  eDate = (Date)sdf1.parse( enddate);
            long interval = 24*1000 * 60 * 60; // 1 hour in millis
            long endTime = eDate.getTime() ; // create your endtime here, possibly using Calendar or Date
            long curTime = sDate.getTime();
            while (curTime <= endTime) {
                dates.add(new Date(curTime));
                curTime += interval;
            }
            for(int i=0;i<dates.size();i++){
                Date lDate =(Date)dates.get(i);
                String ds = sdf1.format(lDate);
                if(onlybunessdays){
                    String day= checkformat.format(lDate);
                    if(!day.equalsIgnoreCase("Sat") && !day.equalsIgnoreCase("Sun")){
                        dateList.add(ds);
                    }
                }else{
                    dateList.add(ds);
                }

                //System.out.println(" Date is ..." + ds);

            }

        }catch(ParseException e){
            e.printStackTrace();
            throw e;
        }finally{
            sdf = null;
            sdf1 = null;
        }
        return dateList;
    }

    public static List<EventDay> parseListEventDates(List<String> datelist, Drawable drawable, int color){
        List<EventDay> events = new ArrayList<>();

        boolean isAddedAll = false;
        if(datelist.size() > 2){
            isAddedAll = true;
        } else {
            isAddedAll = false;
        }

        if(isAddedAll){
            for (int i = 1; i < datelist.size()-1; i++) {
                String d = datelist.get(i);
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.set(Integer.valueOf(d.split("-")[0]), Integer.valueOf(d.split("-")[1])-1, Integer.valueOf(d.split("-")[2]));
                } catch (Exception e){

                }
                events.add(new EventDay(calendar, drawable, color));
            }
        }
        Log.d("Logs", "parseListEventDates: "+events.size());
        return events;
    }
}
