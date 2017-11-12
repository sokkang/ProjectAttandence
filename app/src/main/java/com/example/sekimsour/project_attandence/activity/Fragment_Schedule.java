package com.example.sekimsour.project_attandence.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.sekimsour.project_attandence.R;
import com.example.sekimsour.project_attandence.adapter.MyAdapter;
import com.example.sekimsour.project_attandence.model.Session;
import com.example.sekimsour.project_attandence.model.TimeTable;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Sokkang on 9/8/2017.
 */

public class Fragment_Schedule extends Fragment {

    private TimeTable timeTable;
    private List<Session> list = new ArrayList<>();
    MyAdapter adapter;

    public Fragment_Schedule(List<Session> list) {
        this.list = list;
    }

    public Fragment_Schedule() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        final GridView gridView = (GridView)v.findViewById(R.id.Gv_list);
        adapter = new MyAdapter(getActivity(),list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               View view1 = LayoutInflater.from(getContext()).inflate(R.layout.adapter_header,null);
//                TextView num = view1.findViewById(R.id.tv_sub);
                if(i>5&&!list.get(i).getRoom().toString().equals("")) {
                    list.get(i).setStatus(2);
                    adapter.notifyDataSetChanged();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo", MODE_PRIVATE);
                    if(sharedPreferences.getInt("role",2)==1) {
                        Intent intent = new Intent(getContext(), Teacher_Home_Page.class);
                        getContext().startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
//                        getActivity().finish();
                    }
                }

            }
        });

        return v;
    }

}
