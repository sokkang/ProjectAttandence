package com.example.sekimsour.project_attandence.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
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

import de.hdodenhof.circleimageview.CircleImageView;


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
    public View getView(final int i, View view, ViewGroup viewGroup) {
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

        switch (s.getStatus()) {
            case 1:
                view.setBackgroundResource(R.drawable.s_bg_item);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.s_bg_item_pr);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.s_bg_item_late);
                break;
        }
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

        //--------------------------------------------> onclik show info of student in studenlist
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                View mView = LayoutInflater.from(context).inflate(R.layout.view_profile, null);

                final CircleImageView profile= (CircleImageView) mView.findViewById(R.id.profile_in_alert);
                final TextView name= (TextView) mView.findViewById(R.id.name_in_alert);
                name.setText("name"+i);
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
            }
        });
        //end onclik show info of student in studenlist <------------------------------------------------

        return view;
    }
}
