package com.example.miniprojectliebgott;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    // test 18h44
    
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