package com.example.sekimsour.project_attandence.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sekimsour.project_attandence.model.Student;
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
    private String nameuser;
    private String iduser;

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
        int screenWidth = ((Activity) context).getWindowManager()
                .getDefaultDisplay().getWidth();
//            view.setMinimumHeight(screenHeight/3);
//            view.setMinimumWidth((int) ((screenHeight/3)*0.7));



        Student s =list.get(i);
        ImageView imageView = view.findViewById(R.id.iv_pro);
        Glide.with(context).load(s.getImg()).into(imageView);
        ImageView imgInfo = view.findViewById(R.id.imageView2);
        TextView tvname = view.findViewById(R.id.tv_name);
        tvname.setText(s.getName());
//        imageView.setImageResource(R.drawable.img_profile);

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
            view.setMinimumHeight((int) (screenWidth));
            imageView.setMinimumHeight(screenWidth+200);
//            imageView.setImageResource(R.drawable.employee2);
            Bitmap bMap = BitmapFactory.decodeResource(context.getResources(),R.drawable.employee);
            float factor = screenWidth / (float) bMap.getHeight();
            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, (int) (bMap.getWidth()*factor), screenWidth, true);
            imageView.setImageBitmap(bMapScaled);
            Glide.with(context).load(s.getImg()).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgInfo.setMinimumHeight((int) view.getContext().getResources().getDimension(R.dimen.img_info));
            imgInfo.setMinimumWidth((int) view.getContext().getResources().getDimension(R.dimen.img_info));
            tvname.setTextSize( 25);
        }else {
            view.setMinimumHeight((int) (screenWidth/3));
            imageView.setMinimumHeight((screenWidth/3));
            Bitmap bMap = BitmapFactory.decodeResource(context.getResources(),R.drawable.employee);

            float factor = (screenWidth/3) / (float) bMap.getHeight();

            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, (int) (bMap.getWidth()*factor), screenWidth/3, true);
            imageView.setImageBitmap(bMapScaled);
            Glide.with(context).load(s.getImg()).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgInfo.setMinimumHeight((int) view.getContext().getResources().getDimension(R.dimen.img_info_s));
            imgInfo.setMinimumWidth((int) view.getContext().getResources().getDimension(R.dimen.img_info_s));
            tvname.setTextSize( 15);

        }

        //--------------------------------------------> onclik show info of student in studenlist
        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                View mView = LayoutInflater.from(context).inflate(R.layout.view_profile, null);

                final CircleImageView profile= (CircleImageView) mView.findViewById(R.id.profile_in_alert);
                final TextView name= (TextView) mView.findViewById(R.id.name_in_alert);
                final TextView change = (TextView) mView.findViewById(R.id.change_password_in_alert);
                change.setText(" ");
                String url_profile="";
                for(int j=0; j<list.size(); j++){
                    if(i==j){
                        url_profile = list.get(i).getImg();
                        nameuser = list.get(i).getName();
                        iduser = list.get(i).getId();
                    }
                }
                name.setText(nameuser);
                Glide.with(context).load(url_profile).into(profile);
                final TextView id = (TextView) mView.findViewById(R.id.id_in_alert);
                id.setText(iduser);
                final ImageView back= (ImageView) mView.findViewById(R.id.back_in_alert);

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
