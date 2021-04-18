package com.example.miniprojectliebgott;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomePage extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        // call welcome screen
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent welcomeIntent = new Intent(WelcomePage.this, MainActivity.class);
                startActivity(welcomeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}