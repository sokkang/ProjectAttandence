package com.example.sekimsour.project_attandence.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.sekimsour.project_attandence.activity.Fragment_Schedule;

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
            Log.d("pos1111", "getItem: "+pos);
            if (listfragm.get(pos)==null){return new Fragment_Schedule();
            }
            return listfragm.get(pos);
        }





        @Override
        public int getCount() {
            return 100;
        }



}
