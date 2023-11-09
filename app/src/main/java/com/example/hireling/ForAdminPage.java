package com.example.hireling;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ForAdminPage extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private DBHelperFinal4 dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_admin_page);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        dbHelper=new DBHelperFinal4(ForAdminPage.this);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = dateFormat.format(calendar.getTime());


        actionBar.setTitle("Admin Menu");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));

        actionBar.setBackgroundDrawable(colorDrawable);

        Button button1 = findViewById(R.id.add_employe);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForAdminPage.this, "MOVING TO ADD EMPLOYEE PAGE", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForAdminPage.this,AddEmployeeForm.class);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.mark_att);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dbHelper.addColumnDateWise();
                dbHelper.addDetails(date);
                dbHelper.updateDate(date);
                Toast.makeText(ForAdminPage.this, "I'm going on a new pagee", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ForAdminPage.this,Attendance.class);

                startActivity(intent);
            }
        });
        Button button3 = findViewById(R.id.leave_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForAdminPage.this, "I'm going on a new pagee", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForAdminPage.this,LeaveApprove.class);
                startActivity(intent);
            }
        });
        Button button4 = findViewById(R.id.User_Logout);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForAdminPage.this, "I'm going on a new pagee", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForAdminPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button button5 = findViewById(R.id.att);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForAdminPage.this, "I'm going on a new pagee", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForAdminPage.this,table_A.class);
                startActivity(intent);
            }
        });

    }
}