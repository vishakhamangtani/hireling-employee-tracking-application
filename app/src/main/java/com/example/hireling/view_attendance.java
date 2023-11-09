package com.example.hireling;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class view_attendance extends AppCompatActivity {
    private DBHelperFinal4 dbHelperFinal;
    private StringBuffer Details = new StringBuffer();
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        linear=findViewById(R.id.linearA);

        dbHelperFinal = new DBHelperFinal4(view_attendance.this);
        Cursor c = dbHelperFinal.displayA();

        while (c.moveToNext()) {
            TextView text = new TextView(view_attendance.this);

//            Employees.Eid,Employees.Fname,Employees.Lname,Attendance.DateofAttendance,Attendance.PA
            Details.append("Employee id:" + c.getString(0) + "\nNAME " + c.getString(1)+"  " + c.getString(2) + "\n" + "Date: " + c.getString(3) + "\n" + "P/A: " + c.getString(4) + "\n");
            text.setText(Details);
            text.setPadding(0, 10, 0, 0);
            text.setTextColor(Color.parseColor("#000000"));
            text.setTextSize(20);

            linear.addView(text);
            Details.delete(0, Details.length());
        }
    }
}