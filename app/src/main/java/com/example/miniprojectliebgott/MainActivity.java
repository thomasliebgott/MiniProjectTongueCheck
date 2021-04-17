package com.example.miniprojectliebgott;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog2; //instanciation of dialog
    Dialog myDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog2 = new Dialog(this); //creation of dialog
        myDialog1 = new Dialog(this); //creation of dialog
    }
    //class show infomarmation on the popUp
    public void showPopup2(View view){
        TextView textViewTitlePopup2;
        TextView textViewSubTitlePopUp2;
        TextView textViewDescruptionPopup2;
        myDialog2.setContentView(R.layout.popuplayout2);
        textViewTitlePopup2 = (TextView) myDialog2.findViewById(R.id.textViewTitlePopup2);
        textViewSubTitlePopUp2 = (TextView) myDialog2.findViewById(R.id.textViewSubTitlePopUp2);
        textViewDescruptionPopup2 = (TextView) myDialog2.findViewById(R.id.textViewDescruptionPopup2);
        myDialog2.show();
    }

    public void showPopup1(View view){
        TextView textViewTitlePopup1;
        TextView textViewSubTitlePopUp1;
        TextView textViewDescruptionPopup1;
        myDialog1.setContentView(R.layout.popuplayout1);
        textViewTitlePopup1 = (TextView) myDialog1.findViewById(R.id.textViewTitlePopup1);
        textViewSubTitlePopUp1 = (TextView) myDialog1.findViewById(R.id.textViewSubTitlePopUp1);
        textViewDescruptionPopup1 = (TextView) myDialog1.findViewById(R.id.textViewDescruptionPopup1);
        myDialog1.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void goToQuickAnalyse(View view){
        Intent intent = new Intent(
                MainActivity.this,
                QuickAnalyse.class
        );
        startActivity(intent);
    }

    public void goToDailyCheck(View view){
        //DataModel.getInstance().itemSelected = index; // va chercher l'info du contatct
        Intent intent = new Intent(
                MainActivity.this,
                DailyCheck.class
        );
        startActivity(intent);
    }
}