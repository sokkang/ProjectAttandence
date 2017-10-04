package com.example.sekimsour.project_attandence.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sokkang on 9/27/2017.
 */

public class ListSchedule {
   private TimeTable table;
    public ListSchedule(TimeTable table) {
        this.table = table;
    }
    public List<Session> getScheduleWeekly(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormat  = new SimpleDateFormat("ddd");
        List<Session> listSchedule = new ArrayList<>();
        int[] time = {7,8,9,10,1,2,3,4};
        int num=2;
        for (int i=0;i<=7;i++){

            try {
                cal.setTime(format.parse(table.getSchedule().get(i).getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if((int)cal.get(Calendar.DAY_OF_WEEK)==num++){
                Session daily = new Session();
                daily.setSub(cal.get(Calendar.DAY_OF_MONTH)+"");
                daily.setRoom(dayFormat.format(cal.getTime()));
                listSchedule.set(i,daily);

                for(int j=0;j<table.getSchedule().get(i).getDaily_schedules().size();j++){
                    int numtime=0;
                    daily_schedule currentday = table.getSchedule().get(i).getDaily_schedules().get(numtime);
                    Calendar calCurrentday = Calendar.getInstance();
                    SimpleDateFormat formatCurrentday = new SimpleDateFormat("H:m");
                    int timeStart = 0,timeEnd=0;
                    daily = new Session();

                    try {
                        calCurrentday.setTime(formatCurrentday.parse(currentday.getTime_start()));
                        timeStart = calCurrentday.get(Calendar.HOUR);
                        calCurrentday.setTime(formatCurrentday.parse(currentday.getTime_start()));
                        timeEnd = calCurrentday.get(Calendar.HOUR);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(time[j]==timeStart){
                      for (int k=0;k<timeEnd-timeStart;k++) {
                          daily = new Session();
                          daily.setSub(currentday.getSubject_name());
                          daily.setRoom(currentday.getRoom());
                          listSchedule.set(((j+1+k)*7)+i, daily);
                          j++;
                      }
                    }else {
                        daily = new Session();
                        daily.setSub("");
                        daily.setRoom("");
                        listSchedule.set(((j+1)*7)+i, daily);
                    }

                }

            }else {


            }
        }

        return listSchedule;
    }

}
