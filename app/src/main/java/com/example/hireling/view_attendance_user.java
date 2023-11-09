package com.example.hireling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class view_attendance_user extends AppCompatActivity {

    private DBHelperFinal4 dbHelperFinal;
    private StringBuffer Details = new StringBuffer();
    private LinearLayout linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_user);
        Intent i=getIntent();
        linear=findViewById(R.id.linearU);
        int userId=i.getIntExtra("userId",0);
        String fname=i.getStringExtra("fname");
        dbHelperFinal = new DBHelperFinal4(view_attendance_user.this);
        System.out.println(userId);
        Cursor c = dbHelperFinal.displayU(fname);

        while (c.moveToNext()) {
            TextView text = new TextView(view_attendance_user.this);
//            TextView text1 = new TextView(view_attendance_user.this);
//            TextView text2 = new TextView(view_attendance_user.this);
//            TableRow t1 = new TableRow(view_attendance_user.this);
//             text1.setText("{{1,2},{3,4}}");
//             text2.setText("{abcd}");
//            Employees.Eid,Employees.Fname,Employees.Lname,Attendance.DateofAttendance,Attendance.PA
            Details.append("Employee id:" + c.getString(0) + "\nNAME " + c.getString(1)+"  " + c.getString(2) + "\n" + "Date: " + c.getString(3) + "\n" + "P/A: " + c.getString(4) + "\n");
            text.setText(Details);
            text.setPadding(0, 10, 0, 0);
            text.setTextColor(Color.parseColor("#000000"));

//            t1.addView(text1,0);
//            t1.addView(text2,1);
            linear.addView(text);
//          linear.addView(t1);
            Details.delete(0, Details.length());
        }
    }
}