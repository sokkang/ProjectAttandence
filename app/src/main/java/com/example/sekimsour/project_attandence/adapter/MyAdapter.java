package com.example.sekimsour.project_attandence.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sekimsour.project_attandence.activity.MainActivity;
import com.example.sekimsour.project_attandence.model.Session;
import com.example.sekimsour.project_attandence.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Sokkang on 9/7/2017.
 */

public class MyAdapter extends BaseAdapter{
    List<Session> list;
    Context context;

    public MyAdapter( Context context,List<Session> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Session s = list.get(i);
        int actionBarHeight = 0;

        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }



        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        if(i<6){
            if(view==null){
                view = LayoutInflater.from(context).inflate(R.layout.adapter_header,null);
                int screenHeight = ((Activity) context).getWindowManager()
                        .getDefaultDisplay().getHeight();
                View view1 = LayoutInflater.from(context).inflate(R.layout.activity_main,null);
                LinearLayout linearLayout = view1.findViewById(R.id.lllll);
                linearLayout.setMinimumHeight(screenHeight-actionBarHeight-result);

                view.setMinimumHeight((screenHeight-actionBarHeight-result)/9);
                if(i==5){
                    view.setBackgroundResource(R.drawable.s_header_table_end);

                }else {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo", MODE_PRIVATE);
                    if (s.getSub().equals(sharedPreferences.getString("today",""))){
                        view.setBackgroundResource(R.drawable.s_header_table_today);
                    }
                }
            }
            TextView num = view.findViewById(R.id.tv_num);
            num.setText(s.getSub());
            TextView day = view.findViewById(R.id.tv_day);
            day.setText(s.getRoom());
        }else {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.adapter, null);
                int screenHeight = ((Activity) context).getWindowManager()
                        .getDefaultDisplay().getHeight();

                view.setMinimumHeight((screenHeight-actionBarHeight-result)/9);

                if(i%6==5){
                    switch (s.getStatus()){
                        case 0:
                            view.setBackgroundResource(R.drawable.s_bg_table_end);
                            break;
                        case 1:
                            view.setBackgroundResource(R.drawable.s_bg_table_end_as);
                            break;
                        case 2:
                            view.setBackgroundResource(R.drawable.s_bg_table_end_pr);
                            break;
                        case 3:
                            view.setBackgroundResource(R.drawable.s_bg_table_end_la);
                            break;
                    }
                }

            }
              switch (s.getStatus()){
                  case 0:
                      view.setBackgroundResource(R.drawable.s_bg_table);
                      break;
                  case 1:
                      view.setBackgroundResource(R.drawable.s_bg_table_click_ab);
                      break;
                  case 2:
                      view.setBackgroundResource(R.drawable.s_bg_table_click_pr);
                      break;
                  case 3:
                      view.setBackgroundResource(R.drawable.s_bg_table_click_la);
                      break;
              }


            TextView num = view.findViewById(R.id.tv_sub);
            num.setText(s.getSub());
            TextView day = view.findViewById(R.id.tv_room);
            day.setText(s.getRoom());
        }

        return view;
    }


//    public MyAdapter(@NonNull Context context, List<Session> list) {
//        super(context,R.layout.adapter,list);
//        this.list = list;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
////
//
//        return super.getView(position, convertView, parent);
//    }

    public static class ChangePassword extends AppCompatActivity {
        Button changepassword;
        ImageView back;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_change_password);

            changepassword = (Button) findViewById(R.id.btn_change_password);
            back = (ImageView) findViewById(R.id.back);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.from_right, R.anim.to_left);
                }
            });
            changepassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
                    finish();
                }
            });
        }
    }
}
