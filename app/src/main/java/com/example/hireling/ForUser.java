package com.example.hireling;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ForUser extends AppCompatActivity {
    private DBHelperFinal4 dbHelperFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.for_user);

        dbHelperFinal = new DBHelperFinal4(ForUser.this);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Intent i=getIntent();
        int userId=i.getIntExtra("userId",0);
        String fname=i.getStringExtra("fname");

        Log.d("{TAG}", "onCreate: "+userId);
        Log.d("{TAG}", "fname: "+fname);

        //actionBar.setTitle("Employ");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Button button1 = findViewById(R.id.my_prof);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForUser.this, "MOVING TO PROFILE"+userId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForUser.this, employee_profile.class);
                intent.putExtra("userID",userId);
                intent.putExtra("fname",fname);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.view_att);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForUser.this, "I'm going on a new pagee", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForUser.this,table_try.class);
                intent.putExtra("userID",userId);
                intent.putExtra("fname",fname);
                startActivity(intent);
            }
        });
        Button button3 = findViewById(R.id.leave_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForUser.this, "I'm going on a Leave pagee", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForUser.this,LeaveApply.class);
                intent.putExtra("fname",fname);
                intent.putExtra("userId",0);

                startActivity(intent);
            }
        });
        Button button4 = findViewById(R.id.logout);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForUser.this, "Logging Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForUser.this,MainActivity.class);
                startActivity(intent);
            }
        });
//        Button button5 = findViewById(R.id.check);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Will Put a toast whether leave is generated or not
//
//            }
//        });
        Button button6 = findViewById(R.id.update_password);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Toast.makeText(ForUser.this, "Updating Password Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForUser.this,Update_Password.class);

                intent.putExtra("userID",userId);
                intent.putExtra("fname",fname);
                startActivity(intent);

            }
        });
    }
}