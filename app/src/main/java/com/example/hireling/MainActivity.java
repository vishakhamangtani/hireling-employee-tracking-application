package com.example.hireling;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Button admin;
    Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

       // actionBar.setTitle("Adding New Employee");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));

        actionBar.setBackgroundDrawable(colorDrawable);

        admin = findViewById(R.id.button1);
        user = findViewById(R.id.button2);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go();
            }}
        );
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go2();
            }
        });
    }
    public void go(){
        navigate();
    }
    public void navigate(){
        Intent intent = new Intent(MainActivity.this,AdminLogin.class);
        startActivity(intent);
    }
    public void go2(){
       navigate2();
    }
    public void navigate2(){
        Intent intent1 = new Intent(MainActivity.this,UserLogin.class);
        startActivity(intent1);
    }
}