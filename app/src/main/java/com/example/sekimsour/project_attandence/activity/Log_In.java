package com.example.sekimsour.project_attandence.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sekimsour.project_attandence.R;
import com.example.sekimsour.project_attandence.model.GetSchedule;
import com.example.sekimsour.project_attandence.model.ListSchedule;
import com.example.sekimsour.project_attandence.model.LoginResponse;
import com.example.sekimsour.project_attandence.model.TimeTable;
import com.example.sekimsour.project_attandence.model.schedule;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.name;

public class Log_In extends AppCompatActivity {
    Button login;
    TextView tvUsername,tvpwd;
    static Map<Integer,Fragment_Schedule> scheduleList = new HashMap<>();
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        tvUsername = (TextView) findViewById(R.id.username);
        tvUsername.setText("abc@123.com");
        tvpwd = (TextView) findViewById(R.id.password);
        tvpwd.setText("123456789");
        login = (Button) findViewById(R.id.btnLogIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count==1){
               login();}
            }
        });
    }
    public void login(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.url)+"/oauth/token";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson =new Gson();
                        LoginResponse loginResponse= new LoginResponse();
                        try {
                           loginResponse = gson.fromJson(response,LoginResponse.class);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(!loginResponse.getToken_type().isEmpty()){
                            SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("login", true);
                            editor.putString("name", tvUsername.getText().toString());
                            editor.putString("pwd", tvpwd.getText().toString());
                            editor.putInt("role",tvUsername.getText().toString().contains("teacher")? 1:2);
                            editor.putString("token",loginResponse.getToken());

                            editor.apply();
                            getsb(49);
                            getsb(50);
                            getsb(51);
                            Log.d("log", "onResponse: "+scheduleList.size());

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"a:"+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("username",tvUsername.getText().toString());
                hashMap.put("password",tvpwd.getText().toString());
                hashMap.put("grant_type","password");
                hashMap.put("client_id","1");
                hashMap.put("client_secret","bDdQQQ0JodroeaoYkj5o4uFgCbJxmz8dlySODHyn");
                hashMap.put("scope","*");
                hashMap.put("redirect_url","localhost:8000");
                return hashMap;
            }
        };
        queue.add(stringRequest);
    }
    public void getsb(final int postion){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.url)+"/Getschedule";

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
//                        if(!table.getMessage().isEmpty()){
                            ArrayList<schedule> a = table.getSchedule();
                            ListSchedule nn = new ListSchedule(table);
                            nn.getScheduleWeekly();


                            scheduleList.put(postion,new Fragment_Schedule(nn.getScheduleWeekly()));

                            Log.d("log 11111", scheduleList.size()+"onResponse: "+response);
                            if (postion==51){
                                Intent intent = new Intent(Log_In.this, MainActivity.class);
                                finish();
                                startActivity(intent);
                                count=0;
                            }
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"a:"+error.getMessage(),Toast.LENGTH_LONG).show();


            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Accept","application/json");
                hashMap.put("Authorization",sharedPreferences.getString("token",""));

                return hashMap;
            }

        };
        queue.add(stringRequest);
    }
}