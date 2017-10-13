package com.example.sekimsour.project_attandence.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sekimsour.project_attandence.activity.Fragment_Schedule;
import com.example.sekimsour.project_attandence.activity.MainActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Sokkang on 10/11/2017.
 */


public class GetSchedule {
    Context context;
    Map<Integer,Fragment_Schedule> listfragm = new HashMap<Integer,Fragment_Schedule>();

    public Map<Integer, Fragment_Schedule> getListfragm() {
        return listfragm;
    }

    public GetSchedule(Context context) {
        getsb(49);
        getsb(50);
        getsb(51);
        this.context = context;
    }


    public  Map<Integer,Fragment_Schedule> getsb(final int postion){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = "http://192.168.137.17:8000/Getschedule";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson =new Gson();
                        TimeTable table = new TimeTable();
                        try {
                            table = gson.fromJson(response,TimeTable.class);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(!table.getMessage().isEmpty()){
                            ArrayList<schedule> a = table.getSchedule();
                            ListSchedule nn = new ListSchedule(table);
                            nn.getScheduleWeekly();


                            listfragm.put(postion,new Fragment_Schedule(nn.getScheduleWeekly()));
                            Log.d("11111", a.size()+"onResponse: "+response);
                            Log.d("e33", listfragm.size()+"onResponse: "+nn.getScheduleWeekly().size());
                        }




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"a:"+error.getMessage(),Toast.LENGTH_LONG).show();


            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", MODE_PRIVATE);
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Accept","application/json");
                hashMap.put("Authorization",sharedPreferences.getString("token",""));

                return hashMap;
            }

        };
        queue.add(stringRequest);
        return listfragm;
    }
}
