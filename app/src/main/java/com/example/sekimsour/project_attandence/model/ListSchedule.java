package com.example.sekimsour.project_attandence.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sokkang on 9/27/2017.
 */

public class ListSchedule {
   private TimeTable table =new TimeTable();
    public ListSchedule(TimeTable table) {
        this.table = table;
    }
    public List<Session> getScheduleWeekly(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormat  = new SimpleDateFormat("EEE");
        List<Session> listSchedule1 = new ArrayList<>();
        Session listSchedule[] = new Session[54];

        int[] time = {7,8,9,10,1,2,3,4};
        int num=2;
        for (int i=0;i<6;i++){

            try {
                cal.setTime(format.parse(table.getSchedule().get(i).getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if((int)cal.get(Calendar.DAY_OF_WEEK)==num++){
                Session daily = new Session();
                daily.setSub(cal.get(Calendar.DAY_OF_MONTH)+"");
                daily.setRoom(dayFormat.format(cal.getTime()));
                Log.d("t333", "getScheduleWeekly: "+table.getMessage());
              //  listSchedule.add(i,daily);
                listSchedule[i]=daily;
                ArrayList<schedule> schedule = table.getSchedule();
//                Log.d("h444", "getScheduleWeekly: "+schedule.get(i).getDate());
                ArrayList<daily_schedule> daily_schedules =schedule.get(i).getDaily_schedule();
//                Log.d("h55", "getScheduleWeekly: "+daily_schedules.get(i).getRoom());
                int numtime=0;
                for(int j = 0; j<time.length; j++){

                    daily_schedule currentday = null;
                    if(numtime<table.getSchedule().get(i).getDaily_schedule().size()){
                    currentday = table.getSchedule().get(i).getDaily_schedule().get(numtime);}
                    Calendar calCurrentday = Calendar.getInstance();
                    SimpleDateFormat formatCurrentday = new SimpleDateFormat("H:m");
                    int timeStart = 0,timeEnd=0;
                    daily = new Session();


                    try {
                        if(numtime<table.getSchedule().get(i).getDaily_schedule().size()){
                        calCurrentday.setTime(formatCurrentday.parse(currentday.getTime_start()));
                        timeStart = calCurrentday.get(Calendar.HOUR);
                        calCurrentday.setTime(formatCurrentday.parse(currentday.getTime_end()));
                        timeEnd = calCurrentday.get(Calendar.HOUR);}
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(time[j]==timeStart){
                      for (int k=0;k<timeEnd-timeStart;k++) {
                          daily = new Session();
                          daily.setSub(currentday.getSubject_name());
                          daily.setRoom(currentday.getRoom());
//                          listSchedule.add(((j+1+k)*7)+i, daily);
                          listSchedule[((j+1)*6)+i]=daily;
                          if(k!=timeEnd-timeStart-1)
                          j++;
                      }
                      numtime++;
                    }else {
                        daily = new Session();
                        daily.setSub("");
                        daily.setRoom("");
//                        listSchedule.add(((j+1)*7)+i,daily);
                        listSchedule[((j+1)*6)+i]=daily;
                    }

                }

            }else {


            }
        }
        String str="";
        for(int i = 0 ;i<listSchedule.length;i++){
            str+= listSchedule[i].getRoom()+", ";
            listSchedule1.add(listSchedule[i]);
        }
        Log.d("55555", "getScheduleWeekly: "+str);
        return listSchedule1;
    }

}
