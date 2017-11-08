package com.example.sekimsour.project_attandence.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sekimsour.project_attandence.adapter.MyAdapter;
import com.example.sekimsour.project_attandence.R;
import com.example.sekimsour.project_attandence.adapter.MyPagerAdapter;
import com.example.sekimsour.project_attandence.model.GetSchedule;
import com.example.sekimsour.project_attandence.model.ListSchedule;
import com.example.sekimsour.project_attandence.model.LoginResponse;
import com.example.sekimsour.project_attandence.model.Session;
import com.example.sekimsour.project_attandence.model.TimeTable;
import com.example.sekimsour.project_attandence.model.schedule;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static {
        Calendar calendar = Calendar.getInstance();

    }

    LinearLayout item1;
    LinearLayout item2;
    LinearLayout item3;
    LinearLayout item4;
    LinearLayout item5;

    LinearLayout itemSelect1;
    LinearLayout itemSelect2;
    LinearLayout itemSelect3;
    LinearLayout itemSelect4;
    LinearLayout itemSelect5;

    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    LinearLayout linearLayout4;
    LinearLayout linearLayout5;


    RelativeLayout relativeLayot;

    private TimeTable timeTable;
    private List<Session> list = new ArrayList<>();
    List<Fragment_Schedule> listFragment = new ArrayList<>();
    Map<Integer,Fragment_Schedule> listfragm = new HashMap<Integer,Fragment_Schedule>();
    static int p=50;
    MyPagerAdapter pageAdapter;
    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        setColorToMenu(R.id.menu1);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        item1 = (LinearLayout) findViewById(R.id.menu1);
        item2 = (LinearLayout) findViewById(R.id.menu2);
        item3 = (LinearLayout) findViewById(R.id.menu3);
        item4 = (LinearLayout) findViewById(R.id.menu4);
        item5 = (LinearLayout) findViewById(R.id.menu5);

        itemSelect1 = (LinearLayout) findViewById(R.id.item_menu1);
        itemSelect2 = (LinearLayout) findViewById(R.id.item_menu2);
        itemSelect3 = (LinearLayout) findViewById(R.id.item_menu3);
        itemSelect4 = (LinearLayout) findViewById(R.id.item_menu4);
        itemSelect5 = (LinearLayout) findViewById(R.id.item_menu5);

        linearLayout1 = (LinearLayout) findViewById(R.id.item_select1);
        linearLayout2 = (LinearLayout) findViewById(R.id.item_select2);
        linearLayout3 = (LinearLayout) findViewById(R.id.item_select3);
        linearLayout4 = (LinearLayout) findViewById(R.id.item_select4);
        linearLayout5 = (LinearLayout) findViewById(R.id.item_select5);

        setAttandence();

        //view profile -----------------------------------------------------------------------------
        CircleImageView ViewProfile = (CircleImageView) findViewById(R.id.v_profile);
        ViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.view_profile, null);

                final CircleImageView profile= (CircleImageView) mView.findViewById(R.id.profile_in_alert);
                final TextView name= (TextView) mView.findViewById(R.id.name_in_alert);
                final TextView id = (TextView) mView.findViewById(R.id.id_in_alert);
                final ImageView back= (ImageView) mView.findViewById(R.id.back_in_alert);
                final TextView change_password = (TextView) mView.findViewById(R.id.change_password_in_alert);


                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                change_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, MyAdapter.ChangePassword.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
                        finish();
                    }
                });
            }
        });
        //END view profile -----------------------------------------------------------------------------

        int actionBarHeight = 0;
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }



        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        int screenHeight = getWindowManager()
                .getDefaultDisplay().getHeight();
        View mainView = getLayoutInflater().inflate(R.layout.app_bar_main, null);

        TextView time = (TextView) findViewById(R.id.tv_time0);
        time.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time1 = (TextView) findViewById(R.id.tv_time1);
        time1.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time2 = (TextView) findViewById(R.id.tv_time2);
        time2.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time3 = (TextView) findViewById(R.id.tv_time3);
        time3.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time4 = (TextView) findViewById(R.id.tv_time4);
        time4.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time5 = (TextView) findViewById(R.id.tv_time5);
        time5.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time6 = (TextView) findViewById(R.id.tv_time6);
        time6.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time7 = (TextView) findViewById(R.id.tv_time7);
        time7.setHeight((screenHeight-actionBarHeight-result)/9);
        TextView time8 = (TextView) findViewById(R.id.tv_time8);
        time8.setHeight((screenHeight-actionBarHeight-result)/9);


//        for(int k=0;k<5;k++){
//            list.clear();
//            int num=0;
//            for(int i=0;i<9;i++){
//                for(int j=0;j<6;j++){
//                    if (num<6){
//                        list.add(new Session( j+"a"+k,i+"Day"));
//                    }
//                    else {
//                        list.add(new Session( j+"Sub"+k,i+"room"));
//                    }
//
//                }
//            }
//            // listFragment.add(new Fragment_Schedule(list));
//            listfragm.put(48+k,new Fragment_Schedule(list));
//
//
//        }
//        for(int i=0 ;i<3;i++){
//            RequestQueue queue = Volley.newRequestQueue(this);
//            String url = "http://192.168.1.108:8000/Getschedule";
//
//            final int finalI = i;
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Gson gson =new Gson();
//                            TimeTable table = new TimeTable();
//                            try {
//                                table = gson.fromJson(response,TimeTable.class);
//
//                            } catch (JsonSyntaxException e) {
//                                e.printStackTrace();
//                            }
//                            if(!table.getMessage().isEmpty()){
//                                ArrayList<schedule> a = table.getSchedule();
//                                ListSchedule nn = new ListSchedule(table);
//                                nn.getScheduleWeekly();
//
//
//                                listfragm.put(49+ finalI,new Fragment_Schedule(nn.getScheduleWeekly()));
//                                pageAdapter.notifyDataSetChanged();
//
//                                Log.d("11111", a.size()+"onResponse: "+response);
//                                Log.d("e33", listfragm.size()+"onResponse: "+nn.getScheduleWeekly().size());
//                            }
//
//
//
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(),"a:"+error.getMessage(),Toast.LENGTH_LONG).show();
//
//
//                }
//            }){
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
//                    HashMap<String,String> hashMap = new HashMap<>();
//                    hashMap.put("Accept","application/json");
//                    hashMap.put("Authorization",sharedPreferences.getString("token",""));
//
//                    return hashMap;
//                }
//
//            };
//            queue.add(stringRequest);
//        }

//        getsb(49);
//        getsb(50);
//        getsb(51);
//        getsb(52);
//        getsb(53);
//        getsb(54);
        listfragm=Log_In.scheduleList;
        Log.d("str", "onCreate: "+listfragm.size());
//        GetSchedule getSchedule = new GetSchedule(getApplication());
//        listfragm=getSchedule.getListfragm();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        pageAdapter = new MyPagerAdapter(fragmentManager,listfragm);
        pager = (ViewPager) findViewById(R.id.vp_schedule);
        pager.setAdapter(pageAdapter);
        pager.setCurrentItem(50);
        pageAdapter.notifyDataSetChanged();

        Log.d("00000", "onCreate: "+listfragm.size()+"   ,"+listfragm.containsKey(51));

        pageAdapter.notifyDataSetChanged();




        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this,pager.getCurrentItem()+ "Current Position" +position+" size ="+listfragm.size() , Toast.LENGTH_SHORT).show();
                if (position ==0){
                    for(int k=0;k<1;k++){
                        list.clear();
                        int num=0;
                        for(int i=0;i<9;i++){
                            for(int j=0;j<6;j++){
                                if (num<6){
                                    list.add(new Session( j+"q"+position,i+"Day"));
                                }
                                else {
                                    list.add(new Session( j+"Sub",i+"room"));
                                }

                            }
                        }
                        p=position;
//                        listFragment.add(position+1,new Fragment_Schedule(list));
//                        pageAdapter.notifyDataSetChanged();
//                        pager.setCurrentItem(1);


                    }

                }else if (position<p){
//                    for(int k=0;k<1;k++){
//                        list.clear();
//                        int num=0;
//                        for(int i=0;i<9;i++){
//                            for(int j=0;j<6;j++){
//                                if (num<6){
//                                    list.add(new Session( j+""+position,i+"Day"));
//                                }
//                                else {
//                                    list.add(new Session( j+"Sub",i+"room"));
//                                }
//
//                            }
//                        }
//                        p=position;
////                        listFragment.add(position-1,new Fragment_Schedule(list));
////                        pageAdapter.notifyDataSetChanged();
////                        pager.setCurrentItem(1);
//                        listfragm.put(position-1,new Fragment_Schedule(list));


//                    }
                    getsb(position-1);
                    listfragm.remove(position+2);
                    pageAdapter.notifyDataSetChanged();

                }else if (position>p){
//                    for(int k=0;k<1;k++){
//                        list.clear();
//                        int num=0;
//                        for(int i=0;i<9;i++){
//                            for(int j=0;j<6;j++){
//                                if (num<6){
//                                    list.add(new Session( j+""+position,i+"Day"));
//                                }
//                                else {
//                                    list.add(new Session( j+"Sub",i+"room"));
//                                }
//
//                            }
//                        }
//                        p=position;
////                        listFragment.add(position+1,new Fragment_Schedule(list));
////                        pageAdapter.notifyDataSetChanged();
////                        pager.setCurrentItem(1);
//                        listfragm.put(position+1,new Fragment_Schedule(list));

                    getsb(position+1);
                    listfragm.remove(position-2);



//                    }
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }
    public void doSelectItem1(View view) {
        setColorToMenu(R.id.menu1);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    public void doSelectItem2(View view) {
        setColorToMenu(R.id.menu2);
        Intent intent =new Intent(MainActivity.this, About.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
    }
    public void doSelectItem3(View view) {
        setColorToMenu(R.id.menu3);
        Intent intent =new Intent(MainActivity.this, Terms_and_Conditions.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
    }
    public void doSelectItem4(View view) {
        setColorToMenu(R.id.menu4);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/itckh/"));
        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    public void doSelectItem5(View view) {
        setColorToMenu(R.id.menu5);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.log_out, null);

        Button btn_cancel = (Button) mView.findViewById(R.id.a_btn_cancel);
        Button btn_logout = (Button) mView.findViewById(R.id.a_btn_logout);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                setColorToMenu(R.id.item_menu1);
                setAttandence();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Log_In.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setAttandence(){
        linearLayout1.setBackgroundColor(getResources().getColor(R.color.colorRed));
        itemSelect1.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
    }
    public void setColorToMenu(int item){
        itemSelect1.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect2.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect3.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect4.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect5.setBackgroundColor(getResources().getColor(R.color.colorBlue));

        linearLayout1.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout2.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout3.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout4.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout5.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        if(item1.getId() == item){
            linearLayout1.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect1.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item2.getId()){
            linearLayout2.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect2.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item3.getId()){
            linearLayout3.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect3.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item4.getId()){
            linearLayout4.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect4.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item5.getId()){
            linearLayout5.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect5.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
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
                        if(!table.getMessage().isEmpty()){
                            ArrayList<schedule> a = table.getSchedule();
                            ListSchedule nn = new ListSchedule(table);
                                nn.getScheduleWeekly();


                            listfragm.put(postion,new Fragment_Schedule(nn.getScheduleWeekly()));
                            pageAdapter.notifyDataSetChanged();

                            Log.d("11111", a.size()+"onResponse: "+response);
                            Log.d("e33", listfragm.size()+"onResponse: "+nn.getScheduleWeekly().size());
                        }




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
