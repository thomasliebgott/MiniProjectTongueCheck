package com.example.miniprojectliebgott;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DailyCheck extends AppCompatActivity {

    RecyclerView recyclerView_Tongue;
    DailyCheckAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check);
        recyclerView_Tongue = findViewById(R.id.recyclerView_Tongue);
        adapter = new DailyCheckAdapter();
        recyclerView_Tongue.setAdapter(adapter);

        //set appearance of the recycle view
        recyclerView_Tongue.setLayoutManager(
                new LinearLayoutManager(DailyCheck.this)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged(); //update data si on add
    }

    public void goToTongueDetails(View view){
        //DataModel.getInstance().itemSelected = index; // va chercher l'info du contatct
        Intent intent = new Intent(
                DailyCheck.this,
                TongueDetail.class
        );
        startActivity(intent);
    }

}