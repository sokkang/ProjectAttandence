package com.example.sekimsour.project_attandence.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sekimsour.project_attandence.Fragment_Schedule;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Sokkang on 9/14/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    Map<Integer,Fragment_Schedule> listfragm = new HashMap<Integer,Fragment_Schedule>();


    public MyPagerAdapter(FragmentManager fm , Map<Integer,Fragment_Schedule> listfragm) {
            super(fm);
        this.listfragm = listfragm;
        }

        @Override
        public Fragment getItem(int pos) {

//            switch(pos) {
//
//                case 0:
//                    list.clear();
//                    int num=0;
//
//
//                    Fragment_Schedule f1 = new Fragment_Schedule(list);
//                    return f1;
//                case 1:
//                    list.clear();
//                    num=0;
//                    for(int i=0;i<9;i++){
//                        for(int j=0;j<7;j++){
//                            if(num++==0){
//                                list.add(new Session("1",""));
//                            }
//                            else if (num<8){
//                                list.add(new Session( j+7+"",i+"Day"));
//                            }
//                            else if (num%7==1){
//                                list.add(new Session(i+"-"+(i+1),i+""+j));
//                            }else {
//                                list.add(new Session( j+"Sub",i+"room"));
//                            }
//
//                        }
//                    }
//                    Fragment_Schedule f2 = new Fragment_Schedule(list);
//                    return f2;
//                case 2:
//                    list.clear();
//                    num=0;
//                    for(int i=0;i<9;i++){
//                        for(int j=0;j<7;j++){
//                            if(num++==0){
//                                list.add(new Session("2",""));
//                            }
//                            else if (num<8){
//                                list.add(new Session( j+14+"",i+"Day"));
//                            }
//                            else if (num%7==1){
//                                list.add(new Session(i+"-"+(i+1),i+""+j));
//                            }else {
//                                list.add(new Session( j+"Sub",i+"room"));
//                            }
//
//                        }
//                    }
//                    Fragment_Schedule f3 = new Fragment_Schedule(list);
//                    return f3;
//                default: return new Fragment_Schedule(list);
//            return listFragment.get(pos);
            return listfragm.get(pos);
        }

//            if(pos==51){
//                p--;
//                list.clear();
//                int num=0;
//                    for(int i=0;i<9;i++){
//                        for(int j=0;j<7;j++){
//                            if(num++==0){
//                                list.add(new Session("",""));
//                            }
//                            else if (num<8){
//                                list.add(new Session( j+"="+pos,i+"Day"));
//                            }
//                            else if (num%7==1){
//                                list.add(new Session(i+"-"+(i+1),i+""+j));
//                            }else {
//                                list.add(new Session( j+"Sub",i+"room"));
//                            }
//
//                        }
//                    }
//                return new Fragment_Schedule(list);
//            }
//            else if(pos>p){
//                p++;
//                list.clear();
//                int num=0;
//                for(int i=0;i<9;i++){
//                    for(int j=0;j<7;j++){
//                        if(num++==0){
//                            list.add(new Session("",""));
//                        }
//                        else if (num<8){
//                            list.add(new Session( j+"+"+pos,i+"Day"));
//                        }
//                        else if (num%7==1){
//                            list.add(new Session(i+"-"+(i+1),i+""+j));
//                        }else {
//                            list.add(new Session( j+"Sub",i+"room"));
//                        }
//
//                    }
//                }
//
//                return new Fragment_Schedule(list);
//            }
//            else{
//                p++;
//                list.clear();
//                int num=0;
//                for(int i=0;i<9;i++){
//                    for(int j=0;j<7;j++){
//                        if(num++==0){
//                            list.add(new Session("",""));
//                        }
//                        else if (num<8){
//                            list.add(new Session( j+"-"+pos,i+"Day"));
//                        }
//                        else if (num%7==1){
//                            list.add(new Session(i+"-"+(i+1),i+""+j));
//                        }else {
//                            list.add(new Session( j+"Sub",i+"room"));
//                        }
//
//                    }
//                }
//                return new Fragment_Schedule(list);
//            }




        @Override
        public int getCount() {
            return 100;
        }



}
