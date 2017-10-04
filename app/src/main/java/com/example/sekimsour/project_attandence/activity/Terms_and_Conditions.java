package com.example.sekimsour.project_attandence.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sekimsour.project_attandence.R;

public class Terms_and_Conditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and__conditions);
    }

    public void doBack(View view) {
        Intent intent = new Intent(Terms_and_Conditions.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.from_right, R.anim.to_left);
    }
}