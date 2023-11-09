package com.example.hireling;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Attendance extends AppCompatActivity {
    private DBHelperFinal4 dbHelperFinal;
    private Button attDate;
    private EditText date_att;
    private int mYear, mMonth, mDay;
    private LinearLayout linear;
    private Calendar calendar;
    private int eid;
    private StringBuffer current = new StringBuffer();


    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        calendar=Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = dateFormat.format(calendar.getTime());


        actionBar.setTitle("View Attendance");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));

        actionBar.setBackgroundDrawable(colorDrawable);
        dbHelperFinal=new DBHelperFinal4(Attendance.this);
        linear=findViewById(R.id.linearAtt);
        attDate=findViewById(R.id.dateAtt);
        date_att=findViewById(R.id.date_att);
        date_att.setText(date);

        attDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Attendance.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                if((monthOfYear+1)/10==0 && dayOfMonth/10==0) {//month day dono mai zero
                                    date_att.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                    current.append(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
//                                fromDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                                else if ((monthOfYear+1)/10==0)//sirf month mai zero
                                {
                                    date_att.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                    current.append(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                                }
                                else if ((dayOfMonth)/10==0) //sirf day mai zerp
                                {
                                    date_att.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                    current.append(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                }
                                else //for oct nov dec date after 10
                                {
                                    date_att.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                    current.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }
//                                dbHelperFinal.addDetails(current.toString());


                            }
                        }, mYear, mMonth, mDay);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    datebefore = LocalDate.parse(toDate.toString());
//                }
                datePickerDialog.show();
                System.out.println(date_att);
                System.out.println(current);
//               dbHelperFinal.addDetails(date_att.getText().toString());
//              dbHelperFinal.updateDate(date_att.getText().toString());


            }


        }
        );

        Cursor c=dbHelperFinal.getAllEmployees();

        while(c.moveToNext()) {
            TextView text = new TextView(Attendance.this);
            Button absent=new Button(Attendance.this);
           StringBuffer EmployeeDetails =new StringBuffer();
            int id = c.getInt(0);
            String fname=c.getString(1);
            String lname=c.getString(2);
             EmployeeDetails.append(fname+" "+lname+" ");
            Log.d("", "onCreate of attendance page: "+EmployeeDetails);
            text.setText(EmployeeDetails);
            text.setPadding(10, 10, 0, 0);
            text.setTextColor(Color.parseColor("#000000"));
            absent.setText("Absent");
            absent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Attendance.this, "Absent clicked"+id, Toast.LENGTH_SHORT).show();
//                    dbHelperFinal.updateDate(date_att.getText().toString());
                    dbHelperFinal.changeDate(date,date_att.getText().toString());
                    dbHelperFinal.absentRecord(id,date_att.getText().toString());
//                    dbHelperFinal.changeDate(date,date_att.getText().toString());
                }
            });
            absent.setPadding(3,3,3,3);
            linear.addView(text);
            linear.addView(absent);
//             linear.addView(declineButton);

        }
        c.close();
    }
}