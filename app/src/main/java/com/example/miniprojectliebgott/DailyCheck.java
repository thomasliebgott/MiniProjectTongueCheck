package com.example.miniprojectliebgott;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DailyCheck extends AppCompatActivity {

    RecyclerView recyclerView_Tongue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        recyclerView_Tongue = findViewById(R.id.recyclerView_Tongue);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}