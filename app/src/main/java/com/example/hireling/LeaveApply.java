package com.example.hireling;

import static java.lang.Integer.valueOf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class LeaveApply extends AppCompatActivity {
    private EditText applyDate,fromDate,toDate,leaveReason;
    private Button dateApply,dateFrom,dateTo,apply;
    private int mYear, mMonth, mDay;
    private DBHelperFinal4 dbHelper;
    private int eid;
    private String selectedLeave;
    private   LocalDate datebefore,dateafter;
    private StringBuffer date1,date2;
    private TextView note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_apply);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Intent i=getIntent();
        String fname=i.getStringExtra("fname");

       actionBar.setTitle("Apply For a Leave");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //declaring edittextt
        note=findViewById(R.id.noteLeave);
        applyDate=findViewById(R.id.applyDate);
        fromDate=findViewById(R.id.fromDate);
        toDate=findViewById(R.id.toDate);
        leaveReason=findViewById(R.id.leaveReason);
        leaveReason=findViewById(R.id.leaveReason);

        //declaring buttons

        dateApply=findViewById(R.id.dateApply);
        dateFrom=findViewById(R.id.dateFrom);
        dateTo=findViewById(R.id.dateTo);
        apply=findViewById(R.id.apply);
        Spinner spinner = findViewById(R.id.leaves_type);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLeave=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });


        dbHelper = new DBHelperFinal4(LeaveApply.this);
        dateApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveApply.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                applyDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveApply.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                if((monthOfYear+1)/10==0 && dayOfMonth/10==0) {//month day dono mai zero
                                    fromDate.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
//                                fromDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                                else if ((monthOfYear+1)/10==0)//sirf month mai zero
                                {
                                    fromDate.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                                }
                                else if ((dayOfMonth)/10==0) //sirf day mai zerp
                                {
                                    fromDate.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                }
                                else //for oct nov dec date after 10
                                {
                                    fromDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }


                            }
                        }, mYear, mMonth, mDay);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    datebefore = LocalDate.parse(toDate.toString());
//                }
                datePickerDialog.show();


            }


        });
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveApply.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if((monthOfYear+1)/10==0 && dayOfMonth/10==0) {//month day dono mai zero
                                    toDate.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
//                                fromDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                                else if ((monthOfYear+1)/10==0)//sirf month mai zero
                                {
                                    toDate.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                                }
                                else if ((dayOfMonth)/10==0) //sirf day mai zerp
                                {
                                    toDate.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                }
                                else //for oct nov dec date after 10
                                {
                                    toDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }

                            }
                        }, mYear, mMonth, mDay);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    dateafter =LocalDate.parse(toDate.toString());
//              }
                datePickerDialog.show();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //private EditText applyDate,fromDate,toDate,leaveReason;
                Toast.makeText(LeaveApply.this, "fname: "+fname, Toast.LENGTH_SHORT).show();
                DateTimeFormatter formatter = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                };
//950+8580
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //String x =toDate.getText().toString();

                    datebefore = LocalDate.from(LocalDate.parse(fromDate.getText().toString(), DateTimeFormatter.ISO_LOCAL_DATE));
                    Log.d("{}", "onCreate: "+datebefore);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    dateafter = LocalDate.from(LocalDate.parse(toDate.getText().toString(),DateTimeFormatter.ISO_LOCAL_DATE));
                    Log.d("{}", "onCreate: "+dateafter);
                }

                long daysDiff = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    daysDiff = ChronoUnit.DAYS.between(datebefore, dateafter);
                }
                int days_diff2 = (int)daysDiff;
                Toast.makeText(LeaveApply.this,"The number of days between dates: " + daysDiff , Toast.LENGTH_SHORT).show();
//                System.out.println("The number of days between dates: " + daysDiff);

                boolean ans = true;
                if (selectedLeave.equals("Sick Leave") || selectedLeave.equals("Casual Leave") )
                {
                    selectedLeave="Paid Leaves";
                    dbHelper.deduct_leavesPaid(fname,days_diff2);
                }
                else
                {
                    selectedLeave="UnPaid Leaves";
                    dbHelper.deduct_leavesUnpaid(fname,days_diff2);
                }




                 ans =dbHelper.employeeLeave(applyDate.getText().toString(),fromDate.getText().toString(),toDate.getText().toString(),leaveReason.getText().toString(),valueOf(eid).intValue(),fname,selectedLeave);
                if(ans){
                    Toast.makeText(LeaveApply.this, "Applied for Leave Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LeaveApply.this,ForUser.class);

                    int userId=intent.getIntExtra("userId",0);

                    startActivity(intent);
                }
                else{
                    Toast.makeText(LeaveApply.this, "Cannot Apply! Please Try Again", Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(LeaveApply.this, "Details given are:-\n"+applyDate.getText()+"\n"+fromDate.getText()+"\n"+toDate.getText()+"\n"+leaveReason.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        Cursor cursor =dbHelper.getEmployee(fname);

            int paid = cursor.getInt(8);
            int unpaid = cursor.getInt(9);


//System.out.println("Your Paid leaves remaining are:"+String.valueOf(paid)+"\nUnpaid Leaves remaining are:"+String.valueOf(unpaid));
        note.setText("Your Paid leaves remaining are:"+String.valueOf(paid)+"\nUnpaid Leaves remaining are:"+String.valueOf(unpaid));


    }
}