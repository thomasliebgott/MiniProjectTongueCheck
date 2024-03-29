package com.example.miniprojectliebgott;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // get the reference of the dialog
    Dialog myDialog1;
    Dialog myDialog2;
    Dialog myDialog3;
    Dialog myDialog4;
    Dialog myDialog5;
    Dialog myDialog6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog2 = new Dialog(this); //creation of dialog object
        myDialog1 = new Dialog(this);
        myDialog4 = new Dialog(this);
        myDialog3 = new Dialog(this);
        myDialog5 = new Dialog(this);
        myDialog6 = new Dialog(this);
    }

    //class show information on the popUp
    public void showPopup1(View view){
//        TextView textViewTitlePopup1;
//        TextView textViewSubTitlePopUp1;
//        TextView textViewDescruptionPopup1;
        myDialog1.setContentView(R.layout.popuplayout1);
//        textViewTitlePopup1 = (TextView) myDialog1.findViewById(R.id.textViewTitlePopup6);
//        textViewSubTitlePopUp1 = (TextView) myDialog1.findViewById(R.id.textViewSubTitlePopUp6);
//        textViewDescruptionPopup1 = (TextView) myDialog1.findViewById(R.id.textViewDescruptionPopup6);
        myDialog1.show();
    }

    public void showPopup2(View view){
//        TextView textViewTitlePopup2;
//        TextView textViewSubTitlePopUp2;
//        TextView textViewDescruptionPopup2;
        myDialog2.setContentView(R.layout.popuplayout2);
//        textViewTitlePopup2 = (TextView) myDialog2.findViewById(R.id.textViewTitlePopup2);
//        textViewSubTitlePopUp2 = (TextView) myDialog2.findViewById(R.id.textViewSubTitlePopUp2);
//        textViewDescruptionPopup2 = (TextView) myDialog2.findViewById(R.id.textViewDescruptionPopup2);
        myDialog2.show();
    }

    public void showPopup3(View view){
//        TextView textViewTitlePopup3;
//        TextView textViewSubTitlePopUp3;
//        TextView textViewDescruptionPopup3;
        myDialog3.setContentView(R.layout.popuplayout3);
//        textViewTitlePopup3 = (TextView) myDialog3.findViewById(R.id.textViewTitlePopup6);
//        textViewSubTitlePopUp3 = (TextView) myDialog3.findViewById(R.id.textViewSubTitlePopUp6);
//        textViewDescruptionPopup3 = (TextView) myDialog3.findViewById(R.id.textViewDescruptionPopup6);
        myDialog3.show();
    }

    public void showPopup4(View view){
//        TextView textViewTitlePopup4;
//        TextView textViewSubTitlePopUp4;
//        TextView textViewDescruptionPopup4;
        myDialog4.setContentView(R.layout.popuplayout4);
//        textViewTitlePopup4 = (TextView) myDialog4.findViewById(R.id.textViewTitlePopup4);
//        textViewSubTitlePopUp4 = (TextView) myDialog4.findViewById(R.id.textViewSubTitlePopUp4);
//        textViewDescruptionPopup4 = (TextView) myDialog4.findViewById(R.id.textViewDescruptionPopup4);
        myDialog4.show();
    }

    public void showPopup5(View view){
//        TextView textViewTitlePopup5;
//        TextView textViewSubTitlePopUp5;
//        TextView textViewDescruptionPopup5;
        myDialog5.setContentView(R.layout.popuplayout5);
//        textViewTitlePopup5 = (TextView) myDialog5.findViewById(R.id.textViewTitlePopup5);
//        textViewSubTitlePopUp5 = (TextView) myDialog5.findViewById(R.id.textViewSubTitlePopUp5);
//        textViewDescruptionPopup5 = (TextView) myDialog5.findViewById(R.id.textViewDescruptionPopup5);
        myDialog5.show();
    }

    public void showPopup6(View view){
//        TextView textViewTitlePopup6;
//        TextView textViewSubTitlePopUp6;
//        TextView textViewDescruptionPopup6;
        myDialog6.setContentView(R.layout.popuplayout6);
//        textViewTitlePopup6 = (TextView) myDialog6.findViewById(R.id.textViewTitlePopup6);
//        textViewSubTitlePopUp6 = (TextView) myDialog6.findViewById(R.id.textViewSubTitlePopUp6);
//        textViewDescruptionPopup6 = (TextView) myDialog6.findViewById(R.id.textViewDescruptionPopup6);
        myDialog6.show();
    }

    @Override
    protected void onResume(){
            super.onResume();
    }

    // go to the daulyCheck activity
    public void goToDailyCheck(View view){
        //DataModel.getInstance().itemSelected = index;
        Intent intent = new Intent( // beggiging of the communication between the two activity
                MainActivity.this,
                DailyCheck.class
        );
        startActivity(intent);
    }
}