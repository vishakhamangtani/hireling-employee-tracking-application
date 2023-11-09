package com.example.hireling;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

       // actionBar.setTitle("Adding New Employee");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        };
        thread.start();
    }
}