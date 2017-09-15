package com.example.sekimsour.project_attandence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChangePassword extends AppCompatActivity {
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
