package com.example.sekimsour.project_attandence.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.R.attr.format;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

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

        for (int i=2;i<=7;i++){

            try {
                cal.setTime(format.parse(table.getSchedule().get(i).getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if((int)cal.get(Calendar.DAY_OF_WEEK)==i){
                Session daily = new Session();
                daily.setSub(cal.get(Calendar.DAY_OF_MONTH)+"");
                daily.setRoom(dayFormat.format(cal.getTime()));
                listSchedule.set(i,daily);

                for(int j=0;j<8;j++){
                    daily_schedule currentday = table.getSchedule().get(i).getDaily_schedules().get(j);
                    Calendar calCurrentday = Calendar.getInstance();
                    SimpleDateFormat formatCurrentday = new SimpleDateFormat("H:m");
                    try {
                        calCurrentday.setTime(formatCurrentday.parse(currentday.getTime_start()));
                        int timeStart = calCurrentday.get(Calendar.HOUR);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    if()
                }
            }else {


            }
        }

        return listSchedule;
    }

}
