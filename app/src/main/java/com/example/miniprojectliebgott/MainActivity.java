package com.example.miniprojectliebgott;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Dialog myDialog; //instanciation of dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this); //creation of dialog
    }
    //class show infomarmation on the popUp
    public void showPopup(View view){
        TextView textViewTitlePopup;
        TextView textViewSubTitlePopUp;
        TextView textViewDescruptionPopup;
        myDialog.setContentView(R.layout.popuplayout1);

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