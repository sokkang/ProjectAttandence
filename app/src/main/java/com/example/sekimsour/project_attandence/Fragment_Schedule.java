package com.example.sekimsour.project_attandence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.sekimsour.project_attandence.adapter.MyAdapter;
import com.example.sekimsour.project_attandence.model.Session;
import com.example.sekimsour.project_attandence.model.TimeTable;
import com.example.sekimsour.project_attandence.activity.Teacher_Home_Page;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        final GridView gridView = (GridView)v.findViewById(R.id.Gv_list);
        adapter = new MyAdapter(getActivity(),list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundResource(R.drawable.s_bg_table_click);
                Intent intent = new Intent(getContext(),Teacher_Home_Page.class);
                getContext().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.change_activity, R.anim.change_activity);
                getActivity().finish();


            }
        });

        return v;
    }

}
