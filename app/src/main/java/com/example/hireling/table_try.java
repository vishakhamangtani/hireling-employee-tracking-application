package com.example.hireling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class table_try extends AppCompatActivity {
    private DBHelperFinal4 dbHelperFinal;
    private StringBuffer Details = new StringBuffer();
    TableLayout t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_try);
        Intent i=getIntent();
        int userId=i.getIntExtra("userId",0);
        String fname=i.getStringExtra("fname");
        dbHelperFinal = new DBHelperFinal4(table_try.this);
        System.out.println(userId);
        Cursor c = dbHelperFinal.displayU(fname);
        t1=(TableLayout) findViewById(R.id.table);
        t1.setColumnStretchable(0,true);
        t1.setColumnStretchable(1,true);
        t1.setColumnStretchable(2,true);
        t1.setColumnStretchable(3,true);
        TableRow tr = new TableRow(table_try.this);
        TextView head1=new TextView(table_try.this);
        TextView head2=new TextView(table_try.this);
        TextView head3=new TextView(table_try.this);
        TextView head4=new TextView(table_try.this);

        head1.setText("Eid");
        head2.setText("Name");
        head3.setText("Date");
        head4.setText("P/A");
        head1.setTextSize(20);
        head2.setTextSize(20);
        head3.setTextSize(20);
        head4.setTextSize(20);
        head1.setTypeface(null, Typeface.BOLD);
        head2.setTypeface(null, Typeface.BOLD);
        head3.setTypeface(null, Typeface.BOLD);
        head4.setTypeface(null, Typeface.BOLD);


        tr.addView(head1);
        tr.addView(head2);
        tr.addView(head3);
        tr.addView(head4);

        t1.addView(tr);
        while (c.moveToNext()) {
            TextView id1 = new TextView(table_try.this);
            TextView name1 = new TextView(table_try.this);
            TextView date1 = new TextView(table_try.this);
            TextView action = new TextView(table_try.this);
            TableRow ts = new TableRow(table_try.this);

//            TextView text1 = new TextView(view_attendance_user.this);
//            TextView text2 = new TextView(view_attendance_user.this);
//            TableRow t1 = new TableRow(view_attendance_user.this);
//             text.setText("{{1,2},{3,4}}");
//             text2.setText("{abcd}");
//            Employees.Eid,Employees.Fname,Employees.Lname,Attendance.DateofAttendance,Attendance.PA
            String name=c.getString(1);
            String id=c.getString(0);
            String date=c.getString(3);
            String pa=c.getString(4);
//id,name,date,P/A
             Details.append("Employee id:" + c.getString(0) + "\nNAME " + c.getString(1)+"  " + c.getString(2) + "\n" + "Date: " + c.getString(3) + "\n" + "P/A: " + c.getString(4) + "\n");
            name1.setText(name);
            id1.setText(id);
            date1.setText(date);
            action.setText(pa);
            ts.addView(id1);
            ts.addView(name1);
            ts.addView(date1);
            ts.addView(action);

            t1.addView(ts);
//            text.setText(Details);
//            text.setPadding(0, 10, 0, 0);
//            text.setTextColor(Color.parseColor("#000000"));

//            t1.addView(text1,0);
//            t1.addView(text2,1);
//            linear.addView(text);
//          linear.addView(t1);
            Details.delete(0, Details.length());
        }
    }

}
