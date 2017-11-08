package com.example.sekimsour.project_attandence.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.sekimsour.project_attandence.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void doBack(View view) {
        Intent intent = new Intent(About.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.from_right, R.anim.to_left);
    }
}
