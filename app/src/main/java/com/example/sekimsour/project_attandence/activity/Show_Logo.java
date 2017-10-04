package com.example.sekimsour.project_attandence.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sekimsour.project_attandence.R;

public class Show_Logo extends AppCompatActivity {
    TextView tv;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_logo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ImageView logo = (ImageView) findViewById(R.id.v_logo);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation animation_back = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate_back);

        final TextView textView = (TextView) findViewById(R.id.nameschool);
        final Animation animationtext = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation_text);

        textView.startAnimation(animationtext);
        logo.startAnimation(animation);
//        logo.startAnimation(animation_back);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Show_Logo.this, Log_In.class);
                startActivity(intent);
                overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
                finish();
            }
        }, 2800);
    }
}
