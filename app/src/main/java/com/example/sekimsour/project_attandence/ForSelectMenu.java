package com.example.sekimsour.project_attandence;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by SE KIMSOUR on 07/09/2017.
 */

public class ForSelectMenu extends MainActivity{
    public void setColorToMenu(int item){
        LinearLayout itemSelect1 = (LinearLayout) findViewById(R.id.item_menu1);
        LinearLayout itemSelect2 = (LinearLayout) findViewById(R.id.item_menu2);
        LinearLayout itemSelect3 = (LinearLayout) findViewById(R.id.item_menu3);
        LinearLayout itemSelect4 = (LinearLayout) findViewById(R.id.item_menu4);
        LinearLayout itemSelect5 = (LinearLayout) findViewById(R.id.item_menu5);

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.item_select1);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.item_select2);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.item_select3);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.item_select4);
        LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.item_select5);

        itemSelect1.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect2.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect3.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect4.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        itemSelect5.setBackgroundColor(getResources().getColor(R.color.colorBlue));

        linearLayout1.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout2.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout3.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout4.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        linearLayout5.setBackgroundColor(getResources().getColor(R.color.colorBlue));

        if(item1.getId() == item){
            linearLayout1.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect1.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item2.getId()){
            linearLayout2.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect2.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item3.getId()){
            linearLayout3.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect3.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item4.getId()){
            linearLayout4.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect4.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
        else if(item == item5.getId()){
            linearLayout5.setBackgroundColor(getResources().getColor(R.color.colorRed));
            itemSelect5.setBackgroundColor(getResources().getColor(R.color.colorWasSelect));
        }
    }

}
