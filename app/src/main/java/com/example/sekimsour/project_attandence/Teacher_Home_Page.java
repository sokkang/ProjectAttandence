package com.example.sekimsour.project_attandence;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sekimsour.project_attandence.Adapter.ListStudentAdapter;
import com.example.sekimsour.project_attandence.Model.Student;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Teacher_Home_Page extends AppCompatActivity {
    List<Student> list = new ArrayList<>();
    ListStudentAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__home__page);

        //view profile -----------------------------------------------------------------------------
        CircleImageView ViewProfile = (CircleImageView) findViewById(R.id.v_profile);
        ViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Teacher_Home_Page.this);
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
                        Intent intent = new Intent(Teacher_Home_Page.this, ChangePassword.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
                        finish();
                    }
                });
            }
        });
        //End view profile -----------------------------------------------------------------------------

        for (int i=0 ; i<40;i++){
            Student student = new Student();
            student.setName("name"+i);
            list.add(student);
        }
        gridView = (GridView) findViewById(R.id.Gv_student);
        adapter = new ListStudentAdapter(list,this,1);
        gridView.setAdapter(adapter);
    }

    public void doBack(View view) {
        Intent intent = new Intent(Teacher_Home_Page.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.from_right, R.anim.to_left);
    }

    public void changeGrid(View view) {
        int a=gridView.getFirstVisiblePosition();
        if(gridView.getNumColumns()>1){
            Log.d("11111", "changeView: "+a);
            gridView.setNumColumns(1);
            adapter.setColnum(1);

        }else {
            gridView.setNumColumns(3);
            adapter.setColnum(3);
        }
        gridView.setSelection(a);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetInvalidated();

    }
}