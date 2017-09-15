package com.example.sekimsour.project_attandence.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sekimsour.project_attandence.Model.Student;
import com.example.sekimsour.project_attandence.R;

import java.util.List;


/**
 * Created by Sokkang on 9/11/2017.
 */

public class ListStudentAdapter extends BaseAdapter{
    List<Student> list;
    Context context;
    int colnum;

    public ListStudentAdapter(List<Student> list, Context context,int colnum) {
        this.list = list;
        this.context = context;
        this.colnum = colnum;
    }

    public void setColnum(int colnum) {
        notifyDataSetChanged();
        this.colnum = colnum;
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
        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.adapter_student,null);


        }
        int screenHeight = ((Activity) context).getWindowManager()
                .getDefaultDisplay().getWidth();
//            view.setMinimumHeight(screenHeight/3);
//            view.setMinimumWidth((int) ((screenHeight/3)*0.7));

        Log.d("111111111111", "getViewh: "+view.getMeasuredHeightAndState());

        Student s =list.get(i);
        ImageView imageView = view.findViewById(R.id.iv_pro);
        ImageView imgInfo = view.findViewById(R.id.imageView2);
        TextView tvname = view.findViewById(R.id.tv_name);
        tvname.setText(s.getName());
        if (colnum==1) {
            view.setMinimumHeight((int) (screenHeight));
            imageView.setMinimumHeight(screenHeight);
            imgInfo.setMinimumHeight((int) view.getContext().getResources().getDimension(R.dimen.img_info));
            imgInfo.setMinimumWidth((int) view.getContext().getResources().getDimension(R.dimen.img_info));
        }else {
            view.setMinimumHeight((int) ((screenHeight/3)));
            imageView.setMinimumHeight(screenHeight/3);
            imgInfo.setMinimumHeight((int) view.getContext().getResources().getDimension(R.dimen.img_info_s));
            imgInfo.setMinimumWidth((int) view.getContext().getResources().getDimension(R.dimen.img_info_s));

        }

        return view;
    }
}
