package com.example.sekimsour.project_attandence.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sekimsour.project_attandence.adapter.ListStudentAdapter;
import com.example.sekimsour.project_attandence.adapter.MyAdapter;
import com.example.sekimsour.project_attandence.R;
import com.example.sekimsour.project_attandence.model.Group;
import com.example.sekimsour.project_attandence.model.ListSchedule;
import com.example.sekimsour.project_attandence.model.ListStudent;
import com.example.sekimsour.project_attandence.model.Student;
import com.example.sekimsour.project_attandence.model.Students;
import com.example.sekimsour.project_attandence.model.TimeTable;
import com.example.sekimsour.project_attandence.model.schedule;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Teacher_Home_Page extends AppCompatActivity {
    List<Student> list = new ArrayList<>();
    ArrayList<Student> studentlist = new ArrayList<>();
    ListStudentAdapter adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__home__page);
        GetStudentList();

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
                        Intent intent = new Intent(Teacher_Home_Page.this, MyAdapter.ChangePassword.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
                        finish();
                    }
                });
            }
        });
        //End view profile -----------------------------------------------------------------------------

        gridView = (GridView) findViewById(R.id.Gv_student);
        adapter = new ListStudentAdapter(list,this,1);
        gridView.setAdapter(adapter);
        count_student();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //1 ab
                //2 pre
                //3 late
                switch (list.get(i).getStatus()) {
                    case 1:
                        list.get(i).setStatus(2);
                        break;
                    case 2:
                        list.get(i).setStatus(3);
                        break;
                    case 3:
                        list.get(i).setStatus(1);
                        break;
                }
                count_student();
                adapter.notifyDataSetChanged();

            }
        });
    }

    public void count_student(){
        int total_student = list.size();
        int student_pre=0;
        for(int i=0 ; i<total_student ; i++){
            if(list.get(i).getStatus()==2 || list.get(i).getStatus()==3){
                student_pre ++;
            }
        }
        TextView total = (TextView)findViewById(R.id.show_all_students);
        total.setText("Student : "+student_pre+"/"+total_student);
    }

    public void GetStudentList(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.url)+"/GetStudentList";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson =new Gson();
                        ListStudent listStudent = new ListStudent();
                        try {
                            listStudent = gson.fromJson(response, ListStudent.class);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(!listStudent.getMessage().isEmpty()){
                            for(int i=0 ; i<listStudent.getGroup_list().size(); i++){
                                Group group = listStudent.getGroup_list().get(i);
//                                Toast.makeText(Teacher_Home_Page.this, listStudent.getGroup_list().get(0).getStudent_list().get(0).getLast_name(), Toast.LENGTH_LONG).show();
                                for(int j=0 ; j<group.getStudent_list().size(); j++){
                                    Students students = new Students();
                                    Student student = new Student();
//                                  student.setStatus(j%3+1);
                                    student.setStatus(group.getStudent_list().get(j).getStatus());
                                    student.setId(group.getStudent_list().get(j).getStudent_id());
                                    student.setName(group.getStudent_list().get(j).getFirst_name()+" "+group.getStudent_list().get(j).getLast_name());
                                    student.setImg(group.getStudent_list().get(j).getPhoto());
                                    list.add(student);
                                }
                                count_student();
                                adapter.notifyDataSetChanged();
                            }
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
            adapter.notifyDataSetChanged();

        }else {
            gridView.setNumColumns(3);
            adapter.setColnum(3);
        }
        gridView.setSelection(a);
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetInvalidated();

    }
}